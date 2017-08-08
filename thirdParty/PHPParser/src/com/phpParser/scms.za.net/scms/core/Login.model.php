<?php
class Login extends Model {
    private $Application;
    private static $instance = null;    
    //Model Table Name
    public $tableName = 'scms_Logins';
    //Model Fields
    public $tableFields = array(
      'scms_LID',
      'scms_SID',
      'scms_lTried',
      'scms_lSuccess',
      'scms_lIP',
      'scms_lX_FWD_IP',
      'scms_lUserAgent'
    );
    
    public static function getInstance($Application) {
        if (!self::$instance) {
            self::$instance = new Login($Application);
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
}
?>
