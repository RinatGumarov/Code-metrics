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

class Template {
	private $Application;
	private $TemplateVars = array();
        private static $instance = null;
        private $defaultLayout = 'default-scms-layout.php';
        private $defaultPageTitle = 'SCMS MVC Framework';
	

	public static function getInstance($Application) {
            if (!self::$instance) {self::$instance = new Template($Application);}
            return self::$instance;
	}

	private function __construct($Application) {
            $this->Application = $Application;
	}
	
	public function __set($key,$value) {
            $this->TemplateVars[$key] = $value;
	}
	
	public function show($ViewFile, $useLayout=true,$layoutFile=null) {
            $viewsPath = SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'app'.DIRECTORY_SEPARATOR.'views'.DIRECTORY_SEPARATOR;
            if (!file_exists($viewsPath.$ViewFile.'.php')) {
                return trigger_error('View not found!');    
            } elseif(!isset($this->TemplateVars['PAGE_TITLE']) || !strlen($this->TemplateVars['PAGE_TITLE'])) { 
                $this->TemplateVars['PAGE_TITLE']=$this->defaultPageTitle;     
            }
            foreach ($this->TemplateVars as $key => $value) {
            /* Load variables, so they are accessible for views */
                $$key = $value;
            }
            $RENDER_VIEW = $viewsPath.$ViewFile.'.php';
            
            /*** CSRF Protect ***/
            $this->Application->GenerateAntiCSRFToken();
            /*** Send HTTP Headers **
            if(count($http_headers)) {
                $keys = array_keys($http_headers);
                foreach($keys as $header) {
                    header($header.': '.$http_headers[$header]);
                }
            }
            */
            /*** Log ***/
            error_log('Template::show(): '.$viewsPath.$ViewFile.'.php');
            $layoutsPath = SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'docroot'.DIRECTORY_SEPARATOR.'layouts'.DIRECTORY_SEPARATOR;
            if($useLayout) {
                if(!is_null($layoutFile) && file_exists($layoutsPath.$layoutFile)) {
                //Alternate Layout Template
                    include_once ($layoutsPath.$layoutFile);
            } else {
                //Default Layout Template
                    include_once ($layoutsPath.$this->defaultLayout);                    
                }                
            } else {
            //Without Main Layout Template
                include_once ($path);
            }
	}	
}
?>