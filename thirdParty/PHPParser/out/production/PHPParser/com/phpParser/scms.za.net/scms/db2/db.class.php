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

require_once(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'scms'.DIRECTORY_SEPARATOR.'base'.DIRECTORY_SEPARATOR.'Basedb.class.php');

class db extends Basedb {
    /*** This instance ***/
    private static $instance = NULL;
    private $Application;
    private $reservedWords = array(
        'CURRENT_TIMESTAMP',
        'AES_ENCRYPT',
        'DES_ENCRYPT',
        'AES_DECRYPT',
        'DES_DECRYPT'
    );
    public $sqlQuery = null;
    public $opts = array();

    protected final function printStackTrace($e) {
      $trace = $e->getTraceAsString();
      $lines = preg_split('/#/', $trace);
      $options = '';
      foreach($this->opts as $k=>$v) {
        $this->sqlQuery = preg_replace('/'.$k.'/', $v, $this->sqlQuery);
        //$options .= 'option:'.$k.'='.$v.'<br />';
      }
      echo '
      -Begin-SQL-Query---<br />'
      .$this->sqlQuery.'<br />'
      .$options.'
      -End-SQL-Query-----<br />
      -Begin-Stack-Trace---<br />
      <div style="color:white;background-color:red;">
      ';
      foreach($lines as $line) {
        echo (strlen($line))?'#'.$line.'<br />':'';
      }
      echo '
      </div>
      -End-Stack-Trace-----<br />
      ';
    }

    protected final function __construct($Application) {
      try {
        parent::__construct();
        $this->Application = $Application;
      } catch(Exception $e) {
            trigger_error('__construct() failed: ' . $e->getMessage() . (DebugMode?$this->printStackTrace($e):''));
      }
    }

    public static final function getInstance($Application) {
        if (!self::$instance) {
            self::$instance = new db($Application);
        }
        return self::$instance;
    }

    private final function __clone(){
        /*** no clones! ***/
    }

    public final function __destruct() {
        //parent::__destruct();
    }
    
    public final function beginTransaction() {
        return parent::$link->beginTransaction();
    }
    
    public final function commitTransaction() {
        return parent::$link->commit();
    }    
    
    public final function rollbackTransaction() {
        return parent::$link->rollBack();
    }        

    public final function getLink() {
      try {
        return parent::$link;
      } catch(Exception $e) {
            trigger_error('getLink() failed: ' . $e->getMessage() . (DebugMode?$this->printStackTrace($e):''));
      }
    }

    public final function quoteString($String) {
      try {
        return parent::$link->quote($String);
      } catch(Exception $e) {
        trigger_error('quoteString() failed: ' . $e->getMessage() . (DebugMode?$this->printStackTrace($e):''));
      }
    }


    public final function CleanInput($Input) {
      try {
        return addcslashes($Input, "\0..\37\41..\57\72..\77\133..\136\140\173..\377");
      } catch(Exception $e) {
        trigger_error('CleanInput() failed: ' . $e->getMessage() . (DebugMode?$this->printStackTrace($e):''));
      }
    }

    private final function isReservedWord($value) {
      $Reserved = false;
      foreach($this->reservedWords as $word) {
        if(strpos($value, $word)!==false) {
          $Reserved = true;
        }
      }
      return $Reserved;
    }
    
    private function createModel($ModelName) {
        $m = 'm_'.$ModelName;
        return new $m();        
    } 

    public final function Create($Model, $dbObject) {
      try {        
        $m = $this->createModel($Model);
        $fieldCount = count($m->table_Fields);
        $FIELDS = '';$VALUES = '';$f=0;$this->opts=array();$skipped=1;
        for($f=0;$f<$fieldCount;$f++) {
          if(isset($dbObject[$m->table_Fields[$f]])) {
            $FIELDS .= $m->table_Fields[$f];
            if(!$this->isReservedWord($dbObject[$m->table_Fields[$f]])) {
              $VALUES .= ':'.$m->table_Fields[$f];
              $this->opts[':'.$m->table_Fields[$f]] = $dbObject[$m->table_Fields[$f]];
            } else {
              $VALUES .= $dbObject[$m->table_Fields[$f]];
            }
            $FIELDS .=',';$VALUES .=',';
          }
        }
        $FIELDS = substr($FIELDS, 0,strlen($FIELDS)-1);$VALUES = substr($VALUES, 0,strlen($VALUES)-1);
        $this->sqlQuery = 'INSERT INTO '.$m->table_Name.'('.$FIELDS.') Values ('.$VALUES.');';
        if(DebugMode) { error_log('db::Create():SQL Query: '.$this->sqlQuery); }
        $pdoStatement = parent::$link->prepare($this->sqlQuery);
        return $pdoStatement->execute($this->opts);
      } catch(Exception $e) {
        if(DebugMode) {
          trigger_error($this->printStackTrace($e));
        } else {
          trigger_error('Create() failed: ' . $e->getMessage());
        }
      }
    }

    public final function Read($Model,$SelectFields='*',$Where='',$OrderBy=array(),$Page=0) {
        try {
            $m = $this->createModel($Model);
            $this->opts = array();
            $Page=intval($Page);
            if(is_array($Where) && count($Where)) {
                foreach($Where as $field=>$value) {
                  if(substr($field, 0,6) == 'LENGTH') {
                    $sql[] = $field.'=:LEN';
                    $this->opts[':LEN'] = $value;
                  } else {
                    $sql[] = $field.'=:'.$field;
                    $this->opts[':'.$field] = $value;
                  }
                }
            }
            if(is_numeric($Page) && $Page) {
                $this->opts[':limitStart']    = ((RecordsPerPage*$Page)-RecordsPerPage);
                $this->opts[':limitEnd']      = RecordsPerPage;
            }
            $this->sqlQuery = 'SELECT '.($SelectFields?$SelectFields:'*').' FROM '.$m->table_Name.((strlen($Where))?' Where '.$Where:'').(is_array($OrderBy)&&count($OrderBy)==2?' Order By '.$OrderBy[0].' '.$OrderBy[1]:'').($Page?' limit :limitStart, :limitEnd':'');
            if(DebugMode) { error_log('db::Read():SQL Query: '.$this->sqlQuery); }
            $pdoStatement = parent::$link->prepare($this->sqlQuery);
            $pdoStatement->execute($this->opts);
            $AllRows = array();
            while($row = $pdoStatement->fetch(PDO::FETCH_OBJ)) { array_push($AllRows, $row); }
            return $AllRows;
        } catch(Exception $e) {
          if(DebugMode) {
            trigger_error($this->printStackTrace($e));
          } else {
            trigger_error('Read() failed: ' . $e->getMessage());
          }
        }
    }

    public final function Update($Model,$Values=array(),$Where=array()) {
      try {
        $m = $this->createModel($Model);
        $this->opts = array();
        if(is_array($Where) && count($Where)) {
            foreach($Where as $field=>$value) {
               $sql[] = $field.'=:'.$field;
               $this->opts[':'.$field]=$value;
            }
        }
        $fieldCount = count($Values);
        $this->sqlQuery = 'UPDATE '.$m->table_Name.' SET ';
        $f=0;
        foreach ($Values as $field=>$value) {
          if(!$this->isReservedWord($value)) {
            $this->sqlQuery .= $field.'=:'.$field;
            $this->opts[':'.$field] = $value;
          } else {
            $this->sqlQuery .= "$field =$value";
          }
          if($f < $fieldCount-1) { $this->sqlQuery .=','; }
          $f++;
        }
        $this->sqlQuery .= (is_array($Where) && count($Where)?' Where '.join(' AND ',$sql) : '');
        if(DebugMode) { error_log('db::Update():SQL Query: '.$this->sqlQuery); }
        $pdoStatement = parent::$link->prepare($this->sqlQuery);
        return $pdoStatement->execute($this->opts);
      } catch(Exception $e) {
        if(DebugMode) {
          trigger_error($this->printStackTrace($e));
        } else {
          trigger_error('Update() failed: ' . $e->getMessage());
        }
      }
    }

    /*** If $PrimaryKeyValue eq all Then Delete all ***/
    public final function Delete($Model,$PK_Value) {
      try {
        $m = $this->createModel($Model);
        $this->sqlQuery = 'DELETE FROM '.$m->table_Name;
        if(strtolower($PK_Value) == 'all') {
          //Return Affected Rows
          return parent::$link->exec($this->sqlQuery);
        } else {
          $this->sqlQuery .= ' Where '.$m->table_Fields[0].'=?';
          if(DebugMode) { error_log('db::Delete():SQL Query: '.$this->sqlQuery); }
          $pdoStatement = parent::$link->prepare($this->sqlQuery);
          return $pdoStatement->execute(array($PK_Value));
        }
      } catch(Exception $e) {
        if(DebugMode) {
          trigger_error($this->printStackTrace($e));
        } else {
          trigger_error('Delete() failed: ' . $e->getMessage());
        }
      }
    }

    public final function executeSQL($sqlQuery,$opts=array(),&$pdoStatement=false) {
      try {
        $this->opts = $opts;
        $this->sqlQuery = $sqlQuery;
        $pdoStatement = parent::$link->prepare($this->sqlQuery);
        if(DebugMode) { error_log('db::customQuery():SQL Query: '.$this->sqlQuery); }
        return $pdoStatement->execute($this->opts);
      } catch(Exception $e) {
        if(DebugMode) {
          trigger_error($this->printStackTrace($e));
        } else {
          trigger_error('customQuery() failed: ' . $e->getMessage());
        }
      }
    }

    public final function getDBErrors() {
      try {
        return parent::$link->errorInfo();
      } catch(Exception $e) {
        if(DebugMode) {
          trigger_error($this->printStackTrace($e));
        } else {
          trigger_error('getDBErrors() failed: ' . $e->getMessage());
        }
      }
    }

}

?>
