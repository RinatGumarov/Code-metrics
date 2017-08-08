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

class Application {
    private static $instance = null;
    private $Classes = array();

    public static function getInstance() {
        if (!self::$instance) {
            self::$instance = new Application();
        }
        return self::$instance;
    }

    private function __construct() {
        /*** Empty ***/
    }

    public function __set($key,$value) {
        $this->Classes[$key] = $value;
    }

    public function __get($key) {
        return $this->Classes[$key];
    }

    public function redirectTo($url) {
        header("location: $url");
        exit;
    }

    public function checkCSRFAccessToken($token = null, $value = null) {
        if (!isset($_SESSION['CSRF_Token']) || !isset($_SESSION['CSRF_Value'])) {
            trigger_error('Session CAPTCHA/CSRF Storage Failure!');
        }

        if (!is_null($token) && !is_null($value) && $token == $_SESSION['CSRF_Token'] && $_SESSION['CSRF_Value'] == $value) {
            return true;
        }

        if (isset($_POST[$_SESSION['CSRF_Token']]) && $_POST[$_SESSION['CSRF_Token']] === $_SESSION['CSRF_Value']) {
            return true;
        }

        return false;
    }

    public function checkCAPTCHA() {
        /*         * * CAPTCHA Check ** */
        if (!EnableCAPTCHA) {
            return true;
        }
        if ((isset($_POST['captcha']) && strlen($_POST['captcha']) == 5) && (strtoupper($_POST['captcha']) == $_SESSION['SCMS_CAPTCHA'])) {
            return true;
        }
        return false;
    }

    

    private function GenerateToken() {
        $_SESSION['CSRF_Token'] = $this->RegenPassword(rand(8, 32));
    }

    private function GenerateValue() {
        $_SESSION['CSRF_Value'] = $this->RegenPassword(rand(16, 48));
    }

    public function GenerateAntiCSRFToken() {
        //$this->GenerateToken();
        $_SESSION['CSRF_Token'] = $this->User->RegenPassword(rand(8, 32));
        //$this->GenerateValue();
        $_SESSION['CSRF_Value'] = $this->User->RegenPassword(rand(16, 48));
    }    
}
?>