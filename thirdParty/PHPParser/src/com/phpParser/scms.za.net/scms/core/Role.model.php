<?php
class Role extends Model {
    private $Application;
    private static $instance = null;    
    //Model Table Name
    public $tableName = 'scms_Roles';
    //Model Fields
    public $tableFields = array(
      'scms_RID',
      'scms_RoleName',
      'scms_RoleDesc'
    );
    
    public static function getInstance($Application) {
        if (!self::$instance) {
            self::$instance = new Role($Application);
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
    
    public function GetRolenameBySID($SID) {
        $SID = $this->CleanInput($SID);
        $Rows = $this->Application->Session->Read('scms_UID', "scms_SID='$SID'");
        if (count($Rows)) {
            $Rows = $this->Application->User->Read('scms_RID', "scms_UID='" . $Rows[0]->scms_UID . "'");
            if (count($Rows)) {
                $Rows = $this->Read('scms_RoleName', "scms_RID='" . $Rows[0]->scms_RID . "'");
                if (count($Rows)) {
                    return $Rows[0]->scms_RoleName;
                }
            }
        }
        return false;
    }      
    
    public function IsRootRole() {
        if ($this->GetRolenameBySID(session_id()) != 'ROOT') {
            /*             * * Log Out This Session! ** */
            if (!$this->Application->Session->destroy($SID)) {
                trigger_error('Error Destroying Session!');
            }
            if (!$this->Application->Session->Regen(true)) {
                trigger_error('Error Regenerating Session!');
            }
            $this->Application->redirectTo('/login?errno=8');
        }
    }    
}
?>
