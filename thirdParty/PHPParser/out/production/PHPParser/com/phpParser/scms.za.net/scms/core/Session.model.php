<?php
/*
THE FOLLOWING FILE IS PART OF SCMS 1.0
http://scms.za.net/

Written By Dash Shendy (admin@dash.za.net)

Copyleft (c) 2011 Greyhat InfoSec Systems.
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

    * Redistributions of source code must retain the above
      copyright notice, this list of conditions and the following
      disclaimer in the documentation and/or other materials provided
      with the distribution.

    * Neither the name of Greyhat InfoSec Systems nor the names of its contributors (i.e. Dash Shendy)
      may be used to endorse or promote products derived from this
      software without specific prior written permission.

    * The link "Powered By SCMS" remains unchanged and is included in all implementations of SCMS.

THIS SOFTWARE IS RELEASED UNDER THE GPL (GENERAL PUBLIC LICENCE),
PLEASE FIND THE GLP INCLUDED, OR VIEW ONLINE AT:

http://www.gnu.org/copyleft/gpl.html

THIS SOFTWARE IS PROVIDED BY THE COPYLEFT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

class Session extends Model {
    public $tableName = 'scms_Sessions';
    public $tableFields  = array(
        'scms_SID',
        'scms_UID',
        'scms_sStatus',
        'scms_sCreated',
        'scms_sLastAccess',
        'scms_sDestroyed',
        'scms_SecureSession',
        'scms_sIP',
        'scms_sX_FWD_IP',
        'scms_sUserAgent',
        'scms_sDATA'      
    );
    private $SID = -1;
    private $Application;
    private static $instance = null;
    public $Secure = false;

    private function InitSession() {
        if(count($this->Read('scms_SID',"scms_SID = '".$this->SID."' "))) {
            error_log('Session::InitSession(): Updating '.($this->Secure?'Secure':'').' Session');
            /*** Update This Session ***/
            return $this->UpdateSession($this->SID);
        } else {
        error_log('Session::InitSession(): Initializing New '.($this->Secure?'Secure':'').' Session');
            $x_ffwd_4_ip = 'UNKNOWN';
            if(isset($_SERVER['HTTP_X_FORWARDED_FOR'])) {
                $x_ffwd_4_ip = trim( $_SERVER['HTTP_X_FORWARDED_FOR'] );
                $ips = preg_split('/,/',$x_ffwd_4_ip);
                $x_ffwd_4_ip = (is_array($ips) && count($ips))?substr($ips[0],0,15):substr($_SERVER['HTTP_X_FORWARDED_FOR'],0,15);
            } 
        $Session = array(
            'scms_SID'          => $this->SID,
            'scms_sStatus'      => 'NotAuth',
            'scms_SecureSession'=> $this->Secure,
            'scms_sCreated'     => 'CURRENT_TIMESTAMP',
            'scms_sLastAccess'  => 'CURRENT_TIMESTAMP',
            'scms_sIP'          => $_SERVER['REMOTE_ADDR'],
                'scms_sX_FWD_IP'    => $x_ffwd_4_ip,
            'scms_sUserAgent'   => (isset($_SERVER['HTTP_USER_AGENT'])?$_SERVER['HTTP_USER_AGENT']:'UNKNOWN')
        );
            return $this->Create($Session);
        }
    }

    public function GetSessionStatus($SID) {
        $Rows = $this->Read('scms_SecureSession,scms_sStatus',"scms_SID = '".$this->SID."' ");
        if(count($Rows)) {
            error_log('Session::GetSessionStatus(): '.$Rows[0]->scms_sStatus);
          return $Rows[0]->scms_sStatus;
        }
        return 0;
    }

    public function GetSessionIPs($SID) {
        $Rows = $this->Read('scms_sIP, scms_sX_FWD_IP', "scms_SID='$SID'");
        if (count($Rows) == 1) {
            return $Rows[0];
        }
        return false;
    }    
    
    private function UpdateSession($SID) {
        $STATUS = $this->GetSessionStatus($SID);
        switch($STATUS) {
            case 'Active':
            case 'NotAuth':
                return $this->Update(array('scms_sLastAccess'=>'CURRENT_TIMESTAMP'),array('scms_SID'=>$SID));
            break;
            case 'Expired':
            case 'Destroyed':
                $this->Regen(true);
                $this->Application->redirectTo('/?errno=4');
                exit;
            break;
            case 'Invalid':
                $this->Application->redirectTo('/?errno=7');
                exit;
            break;
        }
        return false;
    }

    public static function getInstance($Application) {
        if (!self::$instance) {
            self::$instance = new Session($Application);
        }
        return self::$instance;
    }

    protected function __construct($Application) {
        parent::__construct();
        $this->Application = $Application;
        //Overload Session
        session_set_save_handler(
            array(&$this,'open'),
            array(&$this,'close'),
            array(&$this,'get'),
            array(&$this,'write'),
            array(&$this,'destroy'),
            array(&$this,'gc')
        );
    }
    
    public function __destruct() {
        parent::__destruct();
    } 

    public function Start($Secure=false) {
        $this->Secure = $Secure;
        /*** Configure Session Name ***/
        session_name(($Secure?'s':'').scmsSession_Name);
        /*** Configure Session Cookie ***/
        ini_set('session.cookie_secure',$Secure);
        /*** Start/Resume Session ***/
        session_start();
        /*** Only Update Session Cookie Lifetime If Cookie Is Already Set! ***
        //time()+scmsSession_IdleTimeout
        if(isset($_COOKIE[session_name()]) && !setcookie(session_name(),session_id(),0,'/',$_SERVER['SERVER_NAME'],$Secure,true)) {
            trigger_error('Error Updating Session Lifetime!');exit;
        }
        */
        /*** Log it ***/
        error_log('Session::Start(): Updated Cookie Lifetime');        
        /*** Get Session ID ***/
        $this->SID = session_id();
        /*** Log it ***/
        error_log('Session::Start(): '.($this->Secure?'SSL/TLS':'PLAIN'));
        if(!$this->InitSession()) {
            trigger_error('Could Not Initiate Current Session!');
            exit;
        }
    }

    public function Regen($delete) {
        if(!session_regenerate_id($delete)) {
            trigger_error('Could Not Regenerate Session!');
            return false;
        }
        $this->SID = session_id();
        error_log('Session::Regen(): Regenerated Session Id');
        return true;
    }

    public function open($save_path, $session_name) {
    /*** Ignored ***/
        return true;
    }

    public function close() {
    /*** Ignored ***/
        return true;
    }

    public function get($SID) {
        if(scmsSession_DataEncrypt) {
            $Key=scmsEncryption_Key;
                $SelectFields = "AES_DECRYPT(scms_sDATA,'$Key') as 'scms_sDATA'";
            } else {
                $SelectFields = 'scms_sDATA';
            }
            error_log('Session::read()');
            $Rows = $this->Read($SelectFields, "scms_SID = '".$SID."' ");
            if(count($Rows)) {
                return stripslashes($Rows[0]->scms_sDATA);
            } else {
                return '';
            }
    }

    public function write($SID,$sDATA) {
        error_log('Session::write()'.((DebugMode)?': '.$sDATA:''));
        $Object = array(
            'scms_sDATA' => (scmsSession_DataEncrypt?"AES_ENCRYPT('$sDATA','".scmsEncryption_Key."')":$sDATA)
        );
        $result = $this->Update($Object,array('scms_SID'=>$SID));
        session_write_close();
        return $result;
    }

    public function gc() {
        error_log('Session::gc(): Cleaning session older than '.scmsSession_IdleTimeout.' seconds');
        $sqlQuery = 'Update scms_Sessions set scms_sStatus=:status,scms_sDestroyed=CURRENT_TIMESTAMP Where ( ( TIME_TO_SEC(CURTIME())-TIME_TO_SEC(scms_sLastAccess) ) > '.scmsSession_IdleTimeout.' OR ( TIME_TO_SEC(CURTIME())-TIME_TO_SEC(scms_sLastAccess) ) < 0)';
        $opts = array(
            ':status'=>'Expired'
        );

        return $this->executeSQL($sqlQuery,$opts);
    }

    public function destroy() {
        error_log('Session::destroy(): Destroying Session');
        $Values = array(
          'scms_sStatus'=>'Destroyed',
          'scms_sDestroyed'=>'CURRENT_TIMESTAMP'
        );
        $WhereClause = array('scms_SID'=>session_id());
        return $this->Update($Values,$WhereClause);
    }
}
?>