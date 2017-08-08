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

class usersController extends Controller {
    public $RequireSSL = array();

    public function change() {
        /*** POST CODE ***/
        if(isset($_POST['exec']) && isset($_POST['remindercode'])) {

            if($this->Application->User->ValidateReminderCode($_POST['remindercode'])) {

                if(!$this->Application->checkCSRFAccessToken()) {
                    $this->Application->Template->errormsg = 'Cross Site Request Forgery Detected!';
                } elseif(!$this->Application->checkCAPTCHA()) {
                    $this->Application->Template->errormsg = 'Incorrect CAPTCHA letters!';
                } elseif(empty($_POST['password'])) {
                    $this->Application->Template->errormsg = 'Please enter your Password!';
                } elseif(empty($_POST['confirm'])) {
                    $this->Application->Template->errormsg = 'Please enter your Confirmation Password!';
                } elseif($_POST['password']!=$_POST['confirm']) {
                    $this->Application->Template->errormsg = 'Passwords do not match!';
                } elseif(strlen($_POST['password'])<scmsAccount_MinimumPasswordLength) {
                    $this->Application->Template->errormsg = 'Password must be at least '.scmsAccount_MinimumPasswordLength.' characters!';
                } elseif(!preg_match('/[A-Z]/',$_POST['password']) || !preg_match('/[a-z]/',$_POST['password']) || !preg_match('/[0-9]/',$_POST['password']) ) {
                    $this->Application->Template->errormsg = 'Strong Passwords (Contains capital, small and number characters) required!';
                } else {
                /*** Change Password ***/
                    $Values = array();

                    $Password = $_POST['password'];
                    $ReminderCode = $this->Application->User->CleanInput($_POST['remindercode']);
                    $EncryptionType = strtolower(scmsEncryption_Type);
                    switch($EncryptionType) {
                        case 'aes':
                            $Key = scmsEncryption_Key;
                            $Values = array('scms_uLogin'=>"AES_ENCRYPT('$Password','$Key')");
                        break;
                        case 'des':
                            $Key = scmsEncryption_Key;
                            $Values = array('scms_uLogin'=>"DES_ENCRYPT('$Password','$Key')");
                        break;
                        case 'supported':
                            $Values = array('scms_uLogin'=>hash($EncryptionType,$Password));
                        break;
                        default:
                        /*** Most Compatable MD5 ***/
                        $Values = array('scms_uLogin'=>md5($Password));
                    }

                    $WhereClause = array('scms_PasswdRequest'=>$ReminderCode);

                    if($this->Application->User->Update($Values,$WhereClause)) {
                        /*** Reset Token And Login Age***/
                        $this->Application->User->executeSQL('Update scms_Users SET scms_uLoginAge='.scmsAccount_LoginAge.", scms_PasswdRequest='' Where scms_PasswdRequest='$ReminderCode';");
                        $this->Application->Template->errormsg = 'You Have Successfully Changed Your Password!';
                        $this->Application->Template->show('login');
                        exit;
                    } else {
                        $this->Application->Template->errormsg = 'Failed To Change Your Password!';
                    }
                }
            } else {
                $this->Application->Template->errormsg = 'Invalid Password Change Request Token.<br />Please renew your token!';
                $this->Application->Template->url = 'http://'.$_SERVER['SERVER_NAME'].'/users/remind';
                $this->Application->Template->title = 'SCMS Password Reminder';
                $this->Application->Template->PAGE_TITLE = 'SCMS Password Reminder';
                $this->Application->Template->show('reminder');
                /*** Early Exit ***/
                exit;
            }/*** Change Password ***/            
            $this->Application->Template->url = 'http://'.$_SERVER['SERVER_NAME'].'/users/change';
            $this->Application->Template->title = 'SCMS Password Change';
            $this->Application->Template->PAGE_TITLE = 'SCMS Password Change';
            $this->Application->Template->remindercode = htmlentities($_POST['remindercode'],ENT_QUOTES);

            $this->Application->Template->show('changer');

        } else {
        /*** Redirect To Login ***/
            $URL = 'http://'.$_SERVER['SERVER_NAME'].'/login';
            header('location: '.$URL);
        }/*** POST CODE ***/

    }

    public function remind($reminderCode=false) {
        /*** POST CODE ***/
        if(isset($_POST['exec'])) {
            $User = $_POST['user'];
            $Email = $_POST['email'];

            if(!$this->Application->checkCSRFAccessToken()) {
                $this->Application->Template->errormsg = 'Cross Site Request Forgery Detected!';
            } elseif(!$this->Application->checkCAPTCHA()) {
                $this->Application->Template->errormsg = 'Incorrect CAPTCHA letters!';
            } elseif(empty($User)) {
                $this->Application->Template->errormsg = 'Please enter your Username!';
            } elseif(empty($Email)) {
                $this->Application->Template->errormsg = 'Please enter your Email!';
            } elseif(preg_match("/^[a-z0-9,!#\$%&'\*\+\=\?\^_`\{\|}~-]+(\.[a-z0-9,!#\$%&'\*\+\=\?\^_`\{\|}~-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*\.([a-z]{2,4})$/",$Email)!=1) {
                $this->Application->Template->errormsg = 'Please enter a valid Email!';
            } else {
                /*** DO NOT GIVE ANY CLUES TO ATTACKERS!!! ***/
                if($this->Application->User->RenewPassword($User,$Email)) {
                    $this->Application->Template->errormsg = 'Password Reminder Sent';
                }
            }
            
        }/*** POST CODE ***/

        if($reminderCode && $this->Application->User->ValidateReminderCode($reminderCode) && !$this->Application->User->PasswordChangeRequestExpired($reminderCode) ) {
            $this->Application->Template->url = 'http://'.$_SERVER['SERVER_NAME'].'/users/change';
            $this->Application->Template->PAGE_TITLE = 'SCMS Password Change';
            $this->Application->Template->remindercode = htmlentities($reminderCode,ENT_QUOTES);
            $this->Application->Template->show('changer');
            exit;
        } elseif($reminderCode!==false) {
            $this->Application->Template->errormsg = 'Incorrect or Expired Password Change Request Token.<br />Please renew your token!';
        }

        $this->Application->Template->url = 'http://'.$_SERVER['SERVER_NAME'].'/users/remind';
        $this->Application->Template->PAGE_TITLE = 'SCMS Password Reminder';
        $this->Application->Template->show('reminder');
    }

    public function index() {        
      $this->view();
    }

    public function view($page=1) {
      if($this->Application->Session->GetSessionStatus(session_id())!='Active') {
        $this->Application->redirectTo('/login?errno=7');
      }
      $this->Application->Role->IsRootRole();

      $page = (intval($page))?$page:1;
      $this->Application->Template->url = 'http://'.$_SERVER['SERVER_NAME'].'/users/edit/';
      $this->Application->Template->paging_url = 'http://'.$_SERVER['SERVER_NAME'].'/users/view/';
      $this->Application->Template->current_page = $page;
      $this->Application->Template->PAGE_TITLE = 'SCMS All Users';
      /*** Get All Records ***/
      $RecCount = $this->Application->User->Read('count(scms_UID) as RecCount');
      $this->Application->Template->TotalRecordCount = $RecCount[0]->RecCount;
      $this->Application->Template->AllRows = $this->Application->User->Read('scms_UID,scms_uName',null,array('scms_UID','ASC'),$page);
      $this->Application->Template->show('user');
    }

    public function update() {
      if($this->Application->Session->GetSessionStatus(session_id())!='Active') {
        $this->Application->redirectTo('/login?errno=7');
      }
      $this->Application->Role->IsRootRole();
      /*** POST CODE ***/
      if(isset($_POST['id']) && isset($_POST['exec'])) {
          $PrimaryKeyValue = abs(intval($_POST['id']));

          if(!$this->Application->checkCSRFAccessToken()) {
              $this->Application->Template->errormsg = 'Cross Site Request Forgery Detected!';
          } elseif(!$this->Application->checkCAPTCHA()) {
              $this->Application->Template->errormsg = 'Incorrect CAPTCHA letters!';
          } elseif(!$this->Validate($Msg,false)) {
              $this->Application->Template->errormsg = $Msg;
          } else {
              switch($_POST['exec']) {
                  case 'update':
                      $Values = array('scms_RID'=>abs(intval($_POST['role'])),'scms_uName'=>$_POST['user'],'scms_uEmail'=>$_POST['email'],'scms_uLoginAge'=>abs(intval($_POST['loginage'])));

                      if(!empty($_POST['password'])) {
                          $Password = $_POST['password'];
                          $SaltedPassword = $Password . $this->getSalt($_POST['user'].':'.$_POST['role'].':'.$_POST['email']);
                          switch(strtolower(scmsEncryption_Type)) {
                              case 'aes':
                                $customQuery = "SELECT AES_ENCRYPT(:Passwd,:CryptKey) as 'crypted';";
                                $opts = array(':Passwd'=>$Password,':CryptKey'=>scmsEncryption_Key);
                                $result = $this->Application->User->executeSQL($customQuery,$opts,$pdoStatement);
                                foreach($pdoStatement->fetchAll() as $row) {
                                  $Values['scms_uLogin'] = $row['crypted'];
                                }
                              break;
                              case 'des':
                                $customQuery = "SELECT DES_ENCRYPT(:Passwd,:CryptKey) as 'crypted';";
                                $opts = array(':Passwd'=>$Password,':CryptKey'=>scmsEncryption_Key);
                                $result = $this->Application->User->executeSQL($customQuery,$opts,$pdoStatement);
                                foreach($pdoStatement->fetchAll() as $row) {
                                  $Values['scms_uLogin'] = $row['crypted'];
                                }
                              break;
                              default:
                                  if($this->Application->isSupportedHash(scmsEncryption_Type)) {
                                    $Values['scms_uLogin'] = hash(scmsEncryption_Type,$SaltedPassword);
                                  } else {
                                  /*** Most Compatable MD5 ***/
                                    $Values['scms_uLogin'] = md5($SaltedPassword);
                                  }
                          }
                      }

                      $WhereClause = array('scms_UID'=>$PrimaryKeyValue);

                      if($PrimaryKeyValue>2) {
                          if($this->Application->User->Update($Values,$WhereClause)) {
                              $this->Application->Template->errormsg = 'User Updated Successfully!';
                          } else {
                               $this->Application->Template->errormsg = 'Failed To Update User!';
                          }
                      } else {
                           $this->Application->Template->errormsg = 'Please do not delete the SCMS demo users!';
                      }
                  break;
                  case 'delete':
                      $PrimaryKeyValue = intval($_POST['id']);

                      if($PrimaryKeyValue>2) {
                          if($this->Application->User->Delete($PrimaryKeyValue)) {
                              $this->Application->Template->errormsg = 'User Deleted Successfully!';
                          } else {
                               $this->Application->Template->errormsg = 'Failed To Delete User!';
                          }
                      } else {
                           $this->Application->Template->errormsg = 'Please do not delete the SCMS demo users!';
                      }
                  break;
              }/*** End Switch ***/
          }
      }/*** POST CODE ***/
      $this->view();
    }

    public function edit($Id=false) {
      if($this->Application->Session->GetSessionStatus(session_id())!='Active') {
        $this->Application->redirectTo('/login?errno=7');
      }
      $this->Application->Role->IsRootRole();
      
      $Id = intval($Id);

      if($Id) {
          $this->Application->Template->url = 'http://'.$_SERVER['SERVER_NAME'].'/users/update';
          $this->Application->Template->PAGE_TITLE = 'SCMS User';
          /*** Get All Records ***/
          $this->Application->Template->AllRows = $this->Application->User->Read('',"scms_UID='$Id'");
          $this->Application->Template->AllRoles = $this->Application->Role->Read();
          $this->Application->Template->show('user');
      } else {
          $this->view();
      }
    }

    private function Validate(&$msg,$NewRecord) {
        if(!is_numeric($_POST['role']) || $_POST['role']<=0) {
            $msg='Please select a valid role!';
            return false;
        }

        if(strlen($_POST['user'])<4 || strlen($_POST['user'])>20) {
            $msg='Username must be between 4 and 20 characters!';
            return false;
        }

        //Only Allow A-z and 0-9 in user name
        if(!preg_match('/^[A-z0-9]*$/',$_POST['user']))  {
            $msg='Username can only consist of Alphanumeric (A-z and 0-9) characters!';
            return false;
        }

        if($NewRecord) {
            if(strlen($_POST['password'])<scmsAccount_MinimumPasswordLength) {
                $msg='Password must be at least '.scmsAccount_MinimumPasswordLength.' characters!';
                return false;
            }

        } elseif(!is_numeric($_POST['loginage']) || $_POST['loginage']<10 || $_POST['loginage']>999) {
                $msg='Login Age must be a digit between 10 and 999!';
                return false;
        } elseif(strlen($_POST['password'])>0) {
            if(strlen($_POST['password'])<scmsAccount_MinimumPasswordLength) {
                $msg='Password must be at least '.scmsAccount_MinimumPasswordLength.' characters!';
                return false;
            }

            if(!preg_match('/[A-Z]/',$_POST['password']) ||
           !preg_match('/[a-z]/',$_POST['password']) ||
               !preg_match('/[0-9]/',$_POST['password']) ) {
                $msg='Strong Passwords (Contains capital, small and number characters) required!';
                return false;
            }
        }



        if(strlen($_POST['email'])<8 || strlen($_POST['email'])>50) {
            $msg='Email must be between 8 and 50 characters!';
            return false;
        }

        if(function_exists('filter_var')) {
            if(!filter_var($_POST['email'], FILTER_VALIDATE_EMAIL)) {
                $msg='Please enter a valid Email!';
                return false;                
            }            
        } else if(!preg_match('^[a-z0-9][a-z0-9_\.-]{0,}[a-z0-9]@[a-z0-9][a-z0-9_\.-]{0,}[a-z0-9][\.][a-z0-9]{2,4}$', $_POST['email'])) {
                $msg='Please enter a valid Email!';
                return false;            
        }

        /*** Valid User ***/
        return true;
    }

    private function getSalt($xor) {
        $salt = '';

        for($i=0;$i<strlen(scmsEncryption_Salt);$i++) {
            $salt .= chr( ord( substr($xor, $i,1)^substr(strrev(scmsEncryption_Salt), $i,1) ) );
        }
        return $salt;
    }

    public function create() {
      if($this->Application->Session->GetSessionStatus(session_id())!='Active') {
        $this->Application->redirectTo('/login?errno=7');
      }
      $this->Application->Role->IsRootRole();

      /*** POST CODE ***/
      if(isset($_POST['exec'])) {
          if(!$this->Application->checkCSRFAccessToken()) {
              $this->Application->Template->errormsg = 'Cross Site Request Forgery Detected!';
          } elseif(!$this->Application->checkCAPTCHA()) {
              $this->Application->Template->errormsg = 'Incorrect CAPTCHA letters!';
          } elseif(!$this->Validate($Msg,true)) {
                  $this->Application->Template->errormsg = $Msg;
          } else {
              $Password = $_POST['password'];
              $newUser = array('scms_RID'=>$_POST['role'],'scms_uName'=>$_POST['user'],'scms_uEmail'=>$_POST['email'],'scms_uLogin'=>'');
              $EncryptionType=strtolower(scmsEncryption_Type);
              $Key = scmsEncryption_Key;
              switch($EncryptionType) {
                  case 'aes':
                    $newUser['scms_uLogin'] = "AES_ENCRYPT('$Password','$Key')";
                  break;
                  case 'des':
                    $newUser['scms_uLogin'] = "DES_ENCRYPT('$Password','$Key')";
                  break;
                  default:
                    $SaltedPassword = $Password . $this->getSalt($_POST['user'].':'.$_POST['role'].':'.$_POST['email']);
                    if($this->Application->isSupportedHash($EncryptionType)) {
                      $newUser['scms_uLogin'] = hash($EncryptionType,$SaltedPassword);
                    } else {
                    /*** Most Compatable MD5 ***/
                      $newUser['scms_uLogin'] = md5($SaltedPassword);
                    }
              }

              if($this->Application->User->Create($newUser)) {
                $this->Application->Template->errormsg = 'New User Created Successfully!';
              } else {
                 $this->Application->Template->errormsg = 'Failed To Create New User!';
              }
          }
      }/*** POST CODE ***/

      $this->displayform();
    }

    private function displayform() {
        $this->Application->Template->url = 'http://'.$_SERVER['SERVER_NAME'].'/users/create';
        $this->Application->Template->PAGE_TITLE = 'SCMS New User';
        $this->Application->Template->AllRoles = $this->Application->Role->Read();
        $this->Application->Template->show('newuser');
    }

    public function add() {
      if($this->Application->Session->GetSessionStatus(session_id())!='Active') {
        $this->Application->redirectTo('/login?errno=7');
      }
      $this->Application->Role->IsRootRole();

      $this->displayform();
    }


}

?>
