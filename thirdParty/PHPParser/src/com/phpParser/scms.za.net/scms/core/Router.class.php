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

class Router {

    private static $instance = null;
    private $Application;
    private $path;
    private $params = array();
    
    public $file;
    public $controller;
    public $action;
    public $customRoutes = array();

    private function setControllerPath($path) {
            /*** check if path is a directory ***/
            if (!is_dir($path)) {
                trigger_error('Could not set controller path');
            }
            /*** set the path ***/
            $this->path = $path;
    }

    private function __construct($Application) {
        $this->Application = $Application;
        /*** set the controllers path ***/
        $this->setControllerPath(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'app'.DIRECTORY_SEPARATOR.'controllers');
    }

    public static function getInstance($Application) {
        if (!self::$instance) {
            self::$instance = new Router($Application);
        }
        return self::$instance;
    }

    public function addCustomRoute($route,$path=array()) {
        $this->customRoutes["$route"]=$path;
    }

    public function getURLRoute() {
        /*** get the route from the url ***/
        $route = (empty($_GET['route'])) ? '' : $_GET['route'];
        error_log('---------------------New-Request---------------------');
        if (!empty($route)) {
          /*** get the parts of the route ***/
          $parts = explode('/', $route);
          $paramsCount = count($parts);
          $start = 1;
          /*** custom routes ***/
          if(isset($this->customRoutes["$parts[0]"]) && count($this->customRoutes["$parts[0]"])) {
            $this->controller = $this->customRoutes["$parts[0]"][0];
            $this->action = $this->customRoutes["$parts[0]"][1];
          } else {
              $this->controller = $parts[0];
              if(isset($parts[1])) {
                  $this->action = $parts[1];
                  $start = 2;
              }
          }
          //Parse Parameters
          for($i=$start;$i<$paramsCount;$i++) {
              $this->params[$i-$start] = $parts[$i];
          }
        }

        /*** Default Controller ***/
        if (empty($this->controller)) {
            $this->controller = 'main';
        }
        /*** Default Action ***/
        if (empty($this->action)) {
            $this->action = 'intro';
        }
        /*** set the file path ***/
        $this->file = $this->path.DIRECTORY_SEPARATOR.'ctrl_'. strtolower($this->controller) . '.php';
        error_log('Router::getURLRoute(): /'.$route.' => ctrl_'. strtolower($this->controller) . '.php');
    }

    public function loader() {
        /*** Sanity Check ***/
        if (!file_exists($this->file) || !is_readable($this->file)) {
            error_log('ERROR: '.  htmlentities($this->file, ENT_QUOTES) . ' does not exist or is unreadable!');
            $this->Application->redirectTo('/error/404');
            // Bail out
            exit;
        }

        /*** include the controller ***/
        require_once($this->file);

        /*** a new controller class instance ***/
        $class = $this->controller . 'Controller';
        $controller = new $class($this->Application);
        $action = 'index';
        /*** check if the action is callable and that it exists ***/
        if (is_callable(array($controller, $this->action)) && method_exists($controller,$this->action)) {
            $action = $this->action;
        }

        /*** Check SSL Requirement ***/
        $SecureSession = (in_array($action, $controller->RequireSSL) || $controller->RequireSSL=='all')?1:0;
        if($SecureSession && !isset($_SERVER['HTTPS'])  || (!$SecureSession && isset($_SERVER['HTTPS']))) {
            $URL = ($SecureSession?'https://':'http://').$_SERVER['SERVER_NAME'].'/';
            if(isset($_GET['route'])) {
                $URL .= $_GET['route'];
            }
            error_log("Redirecting to $URL");
            /*** REDIRECT ***/
            header('location: '.$URL);
            exit;
        }
        
        /*** Start Session ***/
        $this->Application->Session->Start($SecureSession);
        /*** Use Reflection ***/
        $Reflector = new ReflectionClass($controller);
        $Method = $Reflector->getMethod($action);
        $parameters = $Method->getParameters();
        $noMissingParams = count($parameters) - count($this->params);
        for($i=0;$i<$noMissingParams;$i++) {
            $this->params[count($this->params)-1+$i] = false;
        }
        
         /*** Check Auth Requirement ***/
        $AuthSession = (in_array($action, $controller->RequireAuth) || (count($controller->RequireAuth) && $controller->RequireAuth[0]=='all'))?1:0;
        if($AuthSession && $this->Application->Session->GetSessionStatus(session_id())!='Active') {
            error_log('Router::loader(): ERROR, Authenticated Session Required For Page /'.$controller.'/'.$action.'/');
            /*** Regen Session ID ***/
            if (!$this->Application->Session->Regen(true)) {
                trigger_error('Error Regenerating Session!');
            }            
            $this->Application->redirectTo('/?errno=7');
        }  
        
        /*** Check ROOT Role Requirement ***/
        $ROOTRole = (in_array($action, $controller->RequireROOT) || (count($controller->RequireROOT) && $controller->RequireROOT[0]=='all'))?1:0;
        if($ROOTRole && $this->Application->GetRolenameBySID(session_id()) != 'ROOT') {
            /*** Log Out This Session! ***/
            error_log('Router::loader('.$this->Application->GetRolenameBySID(session_id()).'): ROOT Permissions Required For Page /'.$controller.'/'.$action.'/');
            if (!$this->Application->Session->destroy(session_id())) {
                trigger_error('Error Destroying Session!');
            }
            /*** Regen Session ID ***/
            if (!$this->Application->Session->Regen(true)) {
                trigger_error('Error Regenerating Session!');
            }
            /*** Redirect ***/
            $this->Application->redirectTo('/?errno=8');
        }          
        /*** Log this request ***/
        error_log('Router::loader(): Calling '.$controller.'->'.$action.' with '.count($this->params).' parameters');
        foreach($this->params as $param) { error_log('Router::loader():Parameters: '.$param); }
        /*** call the action ***/
        call_user_func_array(array($controller,$action),$this->params);
    }

}
?>