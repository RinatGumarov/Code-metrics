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

abstract class dbMysql  {
    //Db Connection
    public static $link = NULL;

    protected function __construct() {
        if(is_null(self::$link)) {
            try {
                if(!in_array('mysql', PDO::getAvailableDrivers())) {
                    trigger_error('MySQL Database Engine is unsupported, please check your configuration!');
                    exit;
                }
                //Open Connection using DSN
                self::$link = new PDO(scms_dbDSN, scms_dbuser, scms_dbpasswd);
                /* Set attributes */
                self::$link->setAttribute(PDO::ATTR_CASE,PDO::CASE_NATURAL);
                self::$link->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
                self::$link->setAttribute(PDO::ATTR_ORACLE_NULLS,PDO::NULL_EMPTY_STRING);
                self::$link->setAttribute(PDO::MYSQL_ATTR_USE_BUFFERED_QUERY, true);
                self::$link->setAttribute(PDO::MYSQL_ATTR_LOCAL_INFILE, false);
                self::$link->setAttribute(PDO::MYSQL_ATTR_READ_DEFAULT_FILE, scms_dbCNF);
                self::$link->setAttribute(PDO::MYSQL_ATTR_MAX_BUFFER_SIZE, 3);
                self::$link->setAttribute(PDO::MYSQL_ATTR_DIRECT_QUERY, false);
                self::$link->setAttribute(PDO::MYSQL_ATTR_COMPRESS, true);
            } catch (PDOException $e) {
                trigger_error('Connection failed: ' . $e->getMessage());
                exit;
            }
        }
    }
    
    private function __clone(){ /*** no clones allowed! ***/ }
    
    public function getLink() { return self::$link; }
    
    protected function __destruct() { self::$link = null; }
}

?>
