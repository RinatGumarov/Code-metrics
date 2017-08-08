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

if(!defined('SCMS_INSTALL_PATH')) {die('Direct Access Forbbiden!');}

if(!function_exists('simplexml_load_file')) {
  trigger_error('SimpleXml support not present! Please re-compile PHP with Xml Support!');
}
//Load Configuration File
$xmlConfig = SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'scms'.DIRECTORY_SEPARATOR.'config'.DIRECTORY_SEPARATOR.'configuration.xml';
$xml = simplexml_load_file($xmlConfig);
//GENERAL
define('ScmsVersion', (string)$xml->general->version);
define('DebugMode', (string)$xml->general->debug_mode);
define('EnableCAPTCHA', (string)$xml->general->enable_captcha);
define('EnablePasswordExpiration',(string)$xml->general->enable_password_expiration);
define('RecordsPerPage', (string)$xml->general->records_per_page);
define('AdminEmail',(string)$xml->general->admin_email);
//ROUTES
$routes = $xml->xpath('/configuration/routes/route');
for($r=0;$r<count($routes);$r++) {
    $route = $routes[$r];
    $Application->Router->addCustomRoute((string)$route->page,array((string)$route->controller,(string)$route->action));
}
//CRYPTO
define('scmsEncryption_Type', (string)$xml->encryption->passwd_algo);
define('scmsEncryption_Key', (string)$xml->encryption->algo_key);
define('scmsEncryption_Salt', (string)$xml->encryption->crypt_salt);
//ACCOUNTS
define('scmsAccount_LoginAge', (string)$xml->accounts->default_login_age);
define('scmsAccount_MinimumPasswordLength', (string)$xml->accounts->min_passwd_len);
define('scmsAccount_LockingHashAlgo', (string)$xml->accounts->hash_algo);
define('scmsAccount_LockingThreshold', (string)$xml->accounts->lockout_threshold);
define('scmsAccount_LockDuration', (string)$xml->accounts->lockout_duration);
define('scmsAccount_ReminderDuration', (string)$xml->accounts->reminder_lifetime);
//SESSIONS
define('scmsSession_Name', (string)$xml->sessions->name);
define('scmsSession_DataEncrypt', (string)$xml->sessions->crypt_data);
define('scmsSession_IdleTimeout', (string)$xml->sessions->idle_timeout);
define('scmsSession_IPCheck', (string)$xml->sessions->ip_check_on_request);
//DATABASE
define('scms_dbEngine', (string)$xml->database->engine);
define('scms_dbname', (string)$xml->database->name);
define('scms_dbhost', (string)$xml->database->host);
define('scms_dbport', (string)$xml->database->port);
define('scms_dbuser', (string)$xml->database->username);
define('scms_dbpasswd', (string)$xml->database->password);
define('scms_dbCNF', (string)$xml->database->cnf_path);
define('scms_dbSocket', (string)$xml->database->sock_path);
define('scms_dbDSN',(string)$xml->database->engine.':dbname='.(string)$xml->database->name.';host='.(string)$xml->database->host.';');
define('scms_MAX_UPLOAD_SIZE','8M');
//PHP.INI
ini_set('default_charset',(string)$xml->php_ini->default_charset);
ini_set('mysql.connect_timeout',(string)$xml->php_ini->mysql_connect_timeout);
ini_set('zlib.output_compression','on');
ini_set('zlib.output_compression_level','9');
ini_set('max_execution_time',(string)$xml->php_ini->max_execution_time);
ini_set('memory_limit',(string)$xml->php_ini->memory_limit);
ini_set('session.gc_probability',(string)$xml->php_ini->session_gc_probability);
ini_set('session.gc_divisor',(string)$xml->php_ini->session_gc_divisor);
ini_set('date.timezone',(string)$xml->php_ini->date_timezone);
//SCMS Hardcoded Values
ini_set('session.name',scmsSession_Name);
ini_set('session.referer_check',$_SERVER['SERVER_NAME']);
ini_set('session.cookie_lifetime',0);
ini_set('session.gc_maxlifetime',scmsSession_IdleTimeout);
ini_set('allow_url_include',false);
ini_set('default_mimetype','text/html');
set_magic_quotes_runtime(false);
ini_set('session.auto_start',false);
ini_set('session.use_cookies',true);
ini_set('session.use_only_cookies',true);
ini_set('session.use_trans_sid',false);
ini_set('session.cookie_domain',$_SERVER['SERVER_NAME']);
ini_set('session.cookie_path','/');
ini_set('session.cookie_httponly',true);
ini_set('session.cache_limiter','nocache');
ini_set('session.hash_function','1');
ini_set('session.hash_bits_per_character',6);
ini_set('session.entropy_file','/dev/urandom');
ini_set('session.entropy_length',32);
ini_set('sendmail_from','scms@'.$_SERVER['SERVER_NAME']);
ini_set('y2k_compliance',true);
ini_set('serialize_precision',100);
ini_set('precision',14);
ini_set('allow_url_fopen',true);
ini_set('detect_unicode',true);
ini_set('auto_detect_line_endings',true);
ini_set('default_socket_timeout',60);
ini_set('html_errors',false);
ini_set('open_basedir',SCMS_INSTALL_PATH);
ini_set('last_modified',true);
ini_set('user_agent','SCMSv'.ScmsVersion);
ini_set('upload_max_filesize',scms_MAX_UPLOAD_SIZE);
ini_set('post_max_size',scms_MAX_UPLOAD_SIZE);
?>