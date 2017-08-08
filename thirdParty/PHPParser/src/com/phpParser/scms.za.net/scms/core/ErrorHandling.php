<?php
/*
THE FOLLOWING FILE IS PART OF SCMS 1.0
http://scms.za.net./

Written By DASH (admin@scms.za.net)

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

/*** Set PHP.INI ***/
ini_set('ignore_repeated_errors',true);
ini_set('ignore_repeated_source',true);
ini_set('report_memleaks',true);
ini_set('error_prepend_string','<span><h5>SCMS_Error:</h5><br />');
ini_set('error_append_string','</span>');
ini_set('log_errors_max_len',1024);

/*** Determine DebugMode ***/
if(DebugMode) {
	/*** Show All Errors ***/
	error_reporting(E_ALL);
	ini_set('display_errors',true);
	ini_set('display_startup_errors',true);
	ini_set('log_errors',false);
} else {
	/*** Hide Errors But Send Emails ***/
	error_reporting(0);
	ini_set('display_errors',false);
	ini_set('display_startup_errors',false);
	ini_set('log_errors',true);
	set_exception_handler('scms_ExceptionHandler');
	set_error_handler('scms_ErrorHandler');	
}

/*** Handlers ***/
function scms_ExceptionHandler($exception) {
	$ERROR='Uncaught exception:'. $exception->getMessage()."\n";
	$headers="Content-type: text/html\r\n";
	$headers.="Subject: Fatal-Error in SCMS\r\n";
	$headers.="From: scms\r\n";
        if (error_log($ERROR, 1, AdminEmail, $headers)) {
            header('location: /error/');
	} else {
            header('location: /error/666/');
            /*
             * TODO: Try mail() instead
             */
	}
}

function scms_ErrorHandler($errno,$errstr,$errfile,$errline,$errcontext) {
	$ERROR='<html><body>';
	$ERROR.='Error No.'.$errno.'<br />';
	$ERROR.='Error:'.$errstr.'<br />';
	$ERROR.='Error File:'.$errfile.'<br />';
	$ERROR.='Error Line No.'.$errline.'<br />';
	$ERROR.='</body></html>';
	$headers="Content-type: text/html\r\n";
	$headers.="Subject: Fatal-Error in SCMS\r\n";
	$headers.="From: scms\r\n";
        if (error_log($ERROR, 1, AdminEmail, $headers)) {
            header('location: /error/');
	} else {
            header('location: /error/666/');
            /*
             * TODO: Try mail() instead
             */
	}
}
?>