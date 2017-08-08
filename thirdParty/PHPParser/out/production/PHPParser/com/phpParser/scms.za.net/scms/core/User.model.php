<?php
class User extends Model {
    private $Application;
    private static $instance = null;    
    //Model Table Name
    public $tableName = 'scms_Users';
    //Model Fields
    public $tableFields = array(
        'scms_UID',
        'scms_RID',
        'scms_uName',
        'scms_uLogin',
        'scms_uLoginAge',
        'scms_uInvalidLogins',
        'scms_PasswdRequest',
        'scms_PasswordRequestTime',
        'scms_UnlocKey',
        'scms_uLockedTime',
        'scms_uEmail'
    );
    
    public static function getInstance($Application) {
        if (!self::$instance) {
            self::$instance = new User($Application);
        }
        return self::$instance;
    }

    protected function __construct($Application) {
        parent::__construct();
        $this->Application = $Application;
    }
    
    public function __destruct() {
        parent::__destruct();
    }     
    
    public function RenewPassword($User, $Email) {
        $Row = $this->GetUserByName($User, 'scms_uEmail, scms_UID');
        /*         * * Valid Credentials ** */
        if ($Row && $Row->scms_uEmail == $Email) {
            $REMINDER_KEY = hash(scmsAccount_LockingHashAlgo, $this->RegenPassword(32));
            $this->SetUserPasswdRequestKey($Row->scms_UID, $REMINDER_KEY);

            $URL = 'http://' . $_SERVER['SERVER_NAME'] .'/users/remind/' . $REMINDER_KEY . '/';

            $headers = "From: scms\r\n";
            $headers.="Content-type: text/html\r\n";

            $body = '<html>';
            $body.='<body>';
            $body.='Dear SCMS User,<br /><br />';
            $body.='You or somebody else has requested a password change<br />';
            $body.='Please follow this link to change your password:<br />';
            $body.="<a href='" . $URL . "'>CHANGE MY PASSWORD</a><br /><br />";
            $body.='If the above link does not work, copy and paste this url into your browser\'s address bar:<br />';
            $body.= $URL . '<br /><br />';
            $body.='Thank You!<br /><a href="http://scms.za.net./?ref=' . 'http://' . $_SERVER['SERVER_NAME'] . '/">SCMS<a>';
            $body.='</body>';
            $body.='</html>';

            $subject = 'Password Change Requested!';

            if (mail($Email, $subject, $body, $headers)) {
                return true;
            }
        }/*         * * Valid Credentials ** */

        return false;
    }    
    
    public function UnlockAccountViaCode($UnlocKey) {
        $UnlocKey = $this->CleanInput($UnlocKey);
        $Rows = $this->Read('scms_UID', "scms_UnlocKey='$UnlocKey'");
        if (count($Rows) > 0) {
            if ($this->LockAccount($Rows[0]->scms_UID, $UnlocKey, false)) {
                return true;
            }
        }
        return false;
    }    
    
    public function ValidateReminderCode($ReminderCode) {
        $ReminderCode = $this->CleanInput($ReminderCode);
        $ReminderDuration = scmsAccount_ReminderDuration;
        $Rows = $this->Read('scms_PasswdRequest', "scms_PasswdRequest='$ReminderCode'");

        if (count($Rows) == 1) {
            /*             * * Valid ** */
            return true;
        }
        return false;
    }    
    
    public function PasswordChangeRequestExpired($ReminderCode) {
        $ReminderCode = $this->CleanInput($ReminderCode);
        $ReminderDuration = scmsAccount_ReminderDuration;
        $Rows = $this->Read('scms_UID', "scms_PasswdRequest = '$ReminderCode' AND ( TIME_TO_SEC(CURTIME()) - TIME_TO_SEC(scms_PasswordRequestTime) ) > '$ReminderDuration' ");
        if (count($Rows) == 1) {
            /*             * * Expired ** */
            return true;
        }
        return false;
    }    
    
    public function SetUserPasswdRequestKey($UID, $KEY) {
        $UID = abs(intval($UID));
        $Values = array('scms_PasswdRequest' => $KEY, 'scms_PasswordRequestTime' => 'CURRENT_TIMESTAMP');
        return $this->Update($Values, array('scms_UID' => $UID));
    }    
    
    public function ValidatePasswdRequestKey($KEY) {
        $KEY = $this->CleanInput($KEY);
        $Rows = $this->Read('scms_UID', "scms_PasswdRequest = '$KEY' ");
        if (count($Rows) == 1) {
            return $Rows[0]->scms_UID;
        }
        return false;
    }    
    
    public function GetUserByName($uName, $Fields = '*') {
        $uName = $this->CleanInput($uName);
        $Rows = $this->Read($Fields, "scms_uName='$uName'");
        if (count($Rows) == 1) {
            return $Rows[0];
        }
        return false;
    }       
    
    private function LogIT($Success) {
        $SID = session_id();
        $scms_lSuccess = intval($Success);
        $Login = array(
            'scms_SID' => $SID,
            'scms_lIP' => $_SERVER['REMOTE_ADDR'],
            'scms_lX_FWD_IP' => (isset($_SERVER['HTTP_X_FORWARDED_FOR']) ? $_SERVER['HTTP_X_FORWARDED_FOR'] : ''),
            'scms_lUserAgent' => (isset($_SERVER['HTTP_USER_AGENT']) ? $_SERVER['HTTP_USER_AGENT'] : 'UNKNOWN'),
            'scms_lSuccess' => $scms_lSuccess,
            'scms_lTried' => 'CURRENT_TIMESTAMP'
        );
        return $this->Application->Login->Create($Login);
    }    
    
    public function isUserACLocked($uName) {
        $uName = $this->CleanInput($uName);
        $Rows = $this->Read('scms_UID', "scms_uName = '$uName' AND LENGTH(scms_UnlocKey) > 0");
        if (count($Rows) > 0) {
            return true;
        }
        return false;
    }
    
    public function LockExpired($UID) {
        $UID = abs(intval($UID));
        $sqlQuery = "Select TIME_TO_SEC(CURTIME())-TIME_TO_SEC(scms_uLockedTime) as 'LockedTime' from scms_Users where scms_UID=:UID AND LENGTH(scms_UnlocKey)>0";
        $opts = array(
            ':UID' => $UID
        );
        $this->executeSQL($sqlQuery, $opts, $pdoStatement);
        $pdoStatement->execute();
        $Rows = $pdoStatement->fetchAll();
        if ($Rows[0]['LockedTime'] >= scmsAccount_LockDuration) {
            return true;
        }
        return false;
    }    
    
    public function LockAccount($UID, $UnlocKey, $LockIt = true) {
        $UID = intval($UID);
        $LockIt = (bool) $LockIt;
        if ($LockIt) {
            $Values = array(
                'scms_uLockedTime' => 'CURRENT_TIMESTAMP',
                'scms_UnlocKey' => $UnlocKey
            );
        } else {
            $Values = array(
                'scms_uInvalidLogins' => 0,
                'scms_UnlocKey' => ''
            );
        }

        if (!$this->Update($Values, array('scms_UID' => $UID))) {
            return false;
        }

        if ($LockIt) {
            return $this->SendUnlocKey($UID);
        }
        return true;
    }
    
    public function GetUserByID($UID, $Fields = '*') {
        $UID = abs(intval($UID));
        $Rows = $this->Read($Fields, "scms_UID='$UID'");
        if (count($Rows) == 1) {
            return $Rows[0];
        }
        return false;
    }    
    
    public function SendUnlocKey($UID) {
        $Fields = 'scms_UnlocKey, scms_uEmail';
        $UserRow = $this->GetUserByID($UID, $Fields);
        $UNLOCK_KEY = $UserRow->scms_UnlocKey;
        $EMAIL = $UserRow->scms_uEmail;

        $URL = 'http://' . $_SERVER['SERVER_NAME'] . '/login/' . $UNLOCK_KEY . '/';

        $headers = "From: scms\r\n";
        $headers.="Content-type: text/html\r\n";

        $body = '<html>';
        $body.='<body>';
        $body.='Dear SCMS User,<br /><br />';
        $body.='You or somebody else has tried to log into SCMS<br />';
        $body.='more than ' . scmsAccount_LockingThreshold . ' times incorrectly.<br /><br />';
        $body.='To protect you, we have locked your account.<br />';
        $body.='Please follow this link to unlock your account:<br />';
        $body.="<a href='" . $URL . "'>UNLOCK MY ACCOUNT</a><br /><br />";
        $body.='<br />If the above link does not work, copy and paste this url into your browser\'s address bar:<br />';
        $body.= $URL . '<br /><br />';
        $body.='Thank You!<br /><a href="http://scms.za.net./?ref=' . 'http://' . $_SERVER['SERVER_NAME'] . '/">SCMS<a>';
        $body.='</body>';
        $body.='</html>';

        $subject = 'Your Account Has Been Locked!';

        if (mail($EMAIL, $subject, $body, $headers)) {
            return true;
        }

        return false;
    }    
    
    public function ResetLockOutCounter($UID) {
        $UID = abs(intval($UID));
        $Values = array('scms_uInvalidLogins' => 0);
        $WhereClause = array('scms_UID' => $UID);
        return $this->Update($Values, "scms_UID = '$UID'");
    }

    private function GetLockOutCounter($UID) {
        $UID = abs(intval($UID));
        $Values = array('scms_uInvalidLogins' => 0);

        $Rows = $this->Read('scms_uInvalidLogins', "scms_UID = '$UID'");
        if (count($Rows) == 1) {
            return $Rows[0]->scms_uInvalidLogins;
        }
    }

    private function IncreaseLockOutCounter($UID) {
        $UID = intval($UID);
        $LoginsCount = $this->GetLockOutCounter($UID);
        $LoginsCount++;
        $Values = array('scms_uInvalidLogins' => $LoginsCount);
        $WhereClause = array('scms_UID' => $UID);
        return $this->Update($Values, $WhereClause);
    }

    private function GetLoginAge($UID) {
        $UID = intval($UID);
        $Rows = $this->Read('scms_uLoginAge', "scms_UID = '$UID'");
        if (count($Rows) == 1) {
            return $Rows[0]->scms_uLoginAge;
        }
        return 0;
    }

    private function DecreaseLoginAge($UID) {
        $UID = abs(intval($UID));
        $LoginAge = $this->GetLoginAge($UID);
        $LoginAge--;
        $Values = array('scms_uLoginAge' => $LoginAge);
        return $this->Update($Values, array('scms_UID' => $UID));
    }

    private function hasLoginAgeExpired($UID) {
        $UID = abs(intval($UID));
        $LoginAge = $this->GetLoginAge($UID);
        if ($LoginAge <= 0) {
            return true;
        }
        return false;
    }    
    
    public function isSupportedHash($HASH) {
        return in_array($HASH, hash_algos());
    }
    
    public function GetUserBySID($SID) {
        $SID = $this->CleanInput($SID);
        $Rows = $this->Application->Session->Read('scms_UID', "scms_SID='$SID'");
        if (count($Rows) == 1) {
            $Rows = $this->Read('*', "scms_UID='" . $Rows[0]->scms_UID . "'");
            if (count($Rows) == 1) {
                return $Rows[0];
            }
        }
        return false;
    }    
    
public function RegenPassword($len, $specialchars = false) {
        $Password = NULL;

        $MaxChars = 2;
        if ($specialchars) {
            $MaxChars = 3;
        }

        for ($i = 0; $i < ($len); $i++) {
            //Randomly choose what type of character to generate
            $Char2Gen = rand(0, 2);

            switch ($Char2Gen) {
                case 0:
                    /*                     * * Number ** */
                    $Password.=chr(rand(48, 57));
                    break;
                case 1:
                    /*                     * * Small Char ** */
                    $Password.=chr(rand(97, 122));
                    break;
                case 2:
                    /*                     * * Capital Char ** */
                    $Password.=chr(rand(65, 90));
                    break;
                case 3:
                    /*                     * * Special Char ** */
                    $Password.=chr(rand(33, 38));
                    break;
            }
        }

        return $Password;
    }    
    
    public function Authenticate($uName, $uLogin, &$REMINDER_KEY = '') {
        $uName = $this->CleanInput($uName);
        if (strlen($uName) == 0) {
            /*** No Username Entered ***/
            $this->LogIT(false);
            return 'AUTH_BLANK_USERNAME';
        } elseif (strlen($uLogin) == 0) {
            /*             * * No Password Entered ** */
            $this->LogIT(false);
            return 'AUTH_BLANK_PASSWORD';
        }

        $User = $this->GetUserByName($uName);
        $UID = $User->scms_UID;
        if (!$UID) {
            /*             * * Invalid Account ** */
            $this->LogIT(false);
            return 'AUTH_INVALID_LOGIN';
        }

        /*         * * Account is Locked ** */
        if ($this->isUserACLocked($uName)) {
            /*             * * Check Whether To Unlock It ** */
            if ($this->LockExpired($UID)) {
                /*                 * * Unlock It ** */
                $this->LockAccount($UID, '', false);
            } else {
                /*                 * * User Account Is Locked ** */
                $this->LogIT(false);
                return 'AUTH_LOCKED_ACCOUNT';
            }
            /*             * * Account is not Locked ** */
        } elseif (1 + $this->GetLockOutCounter($UID) >= scmsAccount_LockingThreshold) {
            /*             * * Login Threshold Exceeded! Lock Account! ** */
            $UnlocKey = hash(scmsAccount_LockingHashAlgo, $this->RegenPassword(32, true));
            $this->LockAccount($UID, $UnlocKey);
            $this->LogIT(false);
            $this->IncreaseLockOutCounter($UID);
            return 'AUTH_INVALID_LOGIN';
        }
        $WhereClause = "scms_uName='$uName' AND ";

        $EncryptionType = strtolower(scmsEncryption_Type);
        if ($this->isSupportedHash($EncryptionType)) {
            $EncryptionType = 'supported';
        }

        switch ($EncryptionType) {
            case 'supported':
                $uLogin = hash(strtolower(scmsEncryption_Type), $uLogin);
                $WhereClause .= "scms_uLogin='$uLogin'";
                break;
            case 'aes':
                $Key = scmsEncryption_Key;
                $WhereClause .= "AES_DECRYPT(scms_uLogin,'$Key')='$uLogin'";
                break;
            case 'des':
                $Key = scmsEncryption_Key;
                $WhereClause .= "DES_DECRYPT(scms_uLogin,'$Key')='$uLogin'";
                break;
            default:
                /*                 * * Most Compatable MD5 ** */
                $uLogin = md5($uLogin);
                $WhereClause .= "scms_uLogin='$uLogin'";
        }

        $Rows = $this->Read('scms_UID', $WhereClause);

        if (!count($Rows)) {
            /*             * * Authentication Failed ** */
            $this->LogIT(false);
            $this->IncreaseLockOutCounter($UID);
            return 'AUTH_INVALID_LOGIN';
        }

        /*         * * CAPTCHA Check ** */
        if (EnableCAPTCHA && !$this->Application->checkCAPTCHA()) {
            return 'AUTH_CAPTCHA_ERROR';
        }
        if (scmsAccount_LoginAge > 0) {
            $this->DecreaseLoginAge($UID);
            if ($this->hasLoginAgeExpired($UID)) {
                /*                 * * Password has Expired ** */
                $this->LogIT(false);
                $REMINDER_KEY = hash(scmsAccount_LockingHashAlgo, $this->RegenPassword(32));
                $this->SetUserPasswdRequestKey($UID, $REMINDER_KEY);
                return 'AUTH_LOGIN_EXPIRED';
            }
        }

        /*         * * Regen Session Id ** */
        $Success = $this->Application->Session->Regen(true);
        if (!$Success) {
            trigger_error('Error Regenerating Session!');
            return false;
        }

        /*         * * Authenticated ** */
        $Session = array(
            'scms_SID' => session_id(),
            'scms_UID' => $UID,
            'scms_SecureSession' => ini_get('session.cookie_secure'),
            'scms_sCreated' => 'CURRENT_TIMESTAMP',
            'scms_sLastAccess' => 'CURRENT_TIMESTAMP',
            'scms_sIP' => $_SERVER['REMOTE_ADDR'],
            'scms_sX_FWD_IP' => (isset($_SERVER['HTTP_X_FORWARDED_FOR']) ? $_SERVER['HTTP_X_FORWARDED_FOR'] : 'NULL'),
            'scms_sUserAgent' => (isset($_SERVER['HTTP_USER_AGENT']) ? $_SERVER['HTTP_USER_AGENT'] : 'UNKNOWN'),
            'scms_sStatus' => 'Active'
        );

        if (!$this->Application->Session->Create($Session)) {
            trigger_error('Could Not Create New Session!');
            exit;
        }

        $this->LogIT(true);
        /*         * * Reset Login Attempts Counter ** */
        $this->ResetLockOutCounter($UID);
        return 'AUTH_AUTHENTICATED';
    }    
}
?>
