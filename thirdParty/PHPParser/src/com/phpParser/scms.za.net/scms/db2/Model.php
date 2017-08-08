<?php

/*
  THE FOLLOWING FILE IS PART OF SCMS 1.0
  http://scms.za.net/

  Written By Dash Shendy (admin@dash.za.net)

  Copyleft (c) 2012 Greyhat InfoSec Systems.
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

abstract class Model extends dbMysql {

    private $Application;
    private static $instance = null;
    //Model Table Name
    public $tableName = '';
    //Model Fields
    public $tableFields = array();
    private $reservedWords = array(
        'CURRENT_TIMESTAMP',
        'AES_ENCRYPT',
        'DES_ENCRYPT',
        'AES_DECRYPT',
        'DES_DECRYPT'
    );
    public $sqlQuery = null;
    public $opts = array();

    private function __clone() {
        /*         * * no clones allowed! ** */
    }

    public function getLink() {
        return parent::$link;
    }

    protected function __construct() {
        parent::__construct();
    }

    public function __destruct() {
        parent::__destruct();
    }

    public final function quoteString($String) {
        try {
            return parent::$link->quote($String);
        } catch (Exception $e) {
            trigger_error('quoteString() failed: ' . $e->getMessage() . (DebugMode ? $this->printStackTrace($e) : ''));
        }
    }

    public final function CleanInput($Input) {
        try {
            return addcslashes($Input, "\0..\37\41..\57\72..\77\133..\136\140\173..\377");
        } catch (Exception $e) {
            trigger_error('CleanInput() failed: ' . $e->getMessage() . (DebugMode ? $this->printStackTrace($e) : ''));
        }
    }

    private final function isReservedWord($value) {
        $Reserved = false;
        foreach ($this->reservedWords as $word) {
            if (strpos($value, $word) !== false) {
                $Reserved = true;
            }
        }
        return $Reserved;
    }

    protected final function printStackTrace($e) {
        $trace = $e->getTraceAsString();
        $lines = preg_split('/#/', $trace);
        $options = '';
        foreach ($this->opts as $k => $v) {
            $this->sqlQuery = preg_replace('/' . $k . '/', $v, $this->sqlQuery);
            //$options .= 'option:'.$k.'='.$v.'<br />';
        }
        echo '
      -Begin-SQL-Query---<br />'
        . $this->sqlQuery . '<br />'
        . $options . '
      -End-SQL-Query-----<br />
      -Begin-Stack-Trace---<br />
      <div style="color:white;background-color:red;">
      ';
        foreach ($lines as $line) {
            echo (strlen($line)) ? '#' . $line . '<br />' : '';
        }
        echo '
      </div>
      -End-Stack-Trace-----<br />
      ';
    }

    public final function executeSQL($sqlQuery, $opts = array(), &$pdoStatement = false) {
        try {
            $this->opts = $opts;
            $this->sqlQuery = $sqlQuery;
            $pdoStatement = parent::$link->prepare($this->sqlQuery);
            if (DebugMode) {
                error_log('db::customQuery():SQL Query: ' . $this->sqlQuery);
            }
            return $pdoStatement->execute($this->opts);
        } catch (Exception $e) {
            if (DebugMode) {
                trigger_error($this->printStackTrace($e));
            } else {
                trigger_error('customQuery() failed: ' . $e->getMessage());
            }
        }
    }

    public final function Create($dbObject) {
        try {
            $fieldCount = count($this->tableFields);
            $FIELDS = '';
            $VALUES = '';
            $f = 0;
            $this->opts = array();
            $skipped = 1;
            for ($f = 0; $f < $fieldCount; $f++) {
                if (isset($dbObject[$this->tableFields[$f]])) {
                    $FIELDS .= $this->tableFields[$f];
                    if (!$this->isReservedWord($dbObject[$this->tableFields[$f]])) {
                        $VALUES .= ':' . $this->tableFields[$f];
                        $this->opts[':' . $this->tableFields[$f]] = $dbObject[$this->tableFields[$f]];
                    } else {
                        $VALUES .= $dbObject[$this->tableFields[$f]];
                    }
                    $FIELDS .=',';
                    $VALUES .=',';
                }
            }
            $FIELDS = substr($FIELDS, 0, strlen($FIELDS) - 1);
            $VALUES = substr($VALUES, 0, strlen($VALUES) - 1);
            $this->sqlQuery = 'INSERT INTO ' . $this->tableName . '(' . $FIELDS . ') Values (' . $VALUES . ');';
            if (DebugMode) {
                error_log('db::Create():SQL Query: ' . $this->sqlQuery);
            }
            $pdoStatement = parent::$link->prepare($this->sqlQuery);
            return $pdoStatement->execute($this->opts);
        } catch (Exception $e) {
            if (DebugMode) {
                trigger_error($this->printStackTrace($e));
            } else {
                trigger_error('Create() failed: ' . $e->getMessage());
            }
        }
    }

    public final function Read($SelectFields = '*', $Where = '', $OrderBy = array(), $Page = 0) {
        try {
            $this->opts = array();
            $Page = intval($Page);
            if (is_array($Where) && count($Where)) {
                foreach ($Where as $field => $value) {
                    if (substr($field, 0, 6) == 'LENGTH') {
                        $sql[] = $field . '=:LEN';
                        $this->opts[':LEN'] = $value;
                    } else {
                        $sql[] = $field . '=:' . $field;
                        $this->opts[':' . $field] = $value;
                    }
                }
            }
            if (is_numeric($Page) && $Page) {
                $this->opts[':limitStart'] = ((RecordsPerPage * $Page) - RecordsPerPage);
                $this->opts[':limitEnd'] = RecordsPerPage;
            }
            $this->sqlQuery = 
                'SELECT ' . ($SelectFields ? $SelectFields : '*') . 
                ' FROM ' . $this->tableName . ((strlen($Where)) ? ' Where '. $Where : '') . 
                (is_array($OrderBy) && count($OrderBy) == 2 ? ' Order By ' . $OrderBy[0] . ' ' . $OrderBy[1] : '') . 
                ($Page ? ' limit :limitStart, :limitEnd' : '');
            
            if (DebugMode) {
                error_log('db::Read():SQL Query: ' . $this->sqlQuery);
            }
            $pdoStatement = parent::$link->prepare($this->sqlQuery);
            $pdoStatement->execute($this->opts);
            $AllRows = array();
            while ($row = $pdoStatement->fetch(PDO::FETCH_OBJ)) {
                array_push($AllRows, $row);
            }
            return $AllRows;
        } catch (Exception $e) {
            if (DebugMode) {
                trigger_error($this->printStackTrace($e));
            } else {
                trigger_error('Read() failed: ' . $e->getMessage());
            }
        }
    }

    public final function Update($Values = array(), $Where = array()) {
        try {
            $this->opts = array();
            if (is_array($Where) && count($Where)) {
                foreach ($Where as $field => $value) {
                    $sql[] = $field . '=:' . $field;
                    $this->opts[':' . $field] = $value;
                }
            }
            $fieldCount = count($Values);
            $this->sqlQuery = 'UPDATE ' . $this->tableName . ' SET ';
            $f = 0;
            foreach ($Values as $field => $value) {
                if (!$this->isReservedWord($value)) {
                    $this->sqlQuery .= $field . '=:' . $field;
                    $this->opts[':' . $field] = $value;
                } else {
                    $this->sqlQuery .= "$field =$value";
                }
                if ($f < $fieldCount - 1) {
                    $this->sqlQuery .=',';
                }
                $f++;
            }
            $this->sqlQuery .= (is_array($Where) && count($Where) ? ' Where ' . join(' AND ', $sql) : '');
            if (DebugMode) {
                error_log('db::Update():SQL Query: ' . $this->sqlQuery);
            }
            $pdoStatement = parent::$link->prepare($this->sqlQuery);
            return $pdoStatement->execute($this->opts);
        } catch (Exception $e) {
            if (DebugMode) {
                trigger_error($this->printStackTrace($e));
            } else {
                trigger_error('Update() failed: ' . $e->getMessage());
            }
        }
    }

    /*     * * If $PrimaryKeyValue eq all Then Delete all ** */

    public final function Delete($PrimaryKeyValue) {
        try {
            $this->sqlQuery = 'DELETE FROM ' . $this->tableName;
            if (strtolower($PrimaryKeyValue) == 'all') {
                //Return Affected Rows
                return parent::$link->exec($this->sqlQuery);
            } else {
                $this->sqlQuery .= ' WHERE '.$this->tableFields[0].'=:primary_key_value';
                $this->opts[':primary_key_value'] = $PrimaryKeyValue;
            }
            
            if (DebugMode) {
                error_log('db::Delete():SQL Query: ' . $this->sqlQuery);
            }
            $pdoStatement = parent::$link->prepare($this->sqlQuery);
            return $pdoStatement->execute($this->opts);
        } catch (Exception $e) {
            if (DebugMode) {
                trigger_error($this->printStackTrace($e));
            } else {
                trigger_error('Delete() failed: ' . $e->getMessage());
            }
        }
    }

}

?>
