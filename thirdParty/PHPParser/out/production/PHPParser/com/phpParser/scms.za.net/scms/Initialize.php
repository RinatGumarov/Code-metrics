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

/*** include application class ***/
require_once(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'scms'.DIRECTORY_SEPARATOR.'core'.DIRECTORY_SEPARATOR.'Application.class.php');
/*** include router class ***/
require_once(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'scms'.DIRECTORY_SEPARATOR.'core'.DIRECTORY_SEPARATOR.'Router.class.php');

/*** a new Application object ***/
$Application = Application::getInstance();
/*** load the router class ***/
$Application->Router = Router::getInstance($Application);

/*** include base controller class ***/
require_once(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'scms'.DIRECTORY_SEPARATOR.'core'.DIRECTORY_SEPARATOR.'Controller.php');

/*** include scms configuration ***/
require_once(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'scms'.DIRECTORY_SEPARATOR.'core'.DIRECTORY_SEPARATOR.'Configuration.php');
/*** include scms autoloader ***/
require_once(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'scms'.DIRECTORY_SEPARATOR.'core'.DIRECTORY_SEPARATOR.'AutoLoader.php');
/*** include scms error handling ***/
require_once(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'scms'.DIRECTORY_SEPARATOR.'core'.DIRECTORY_SEPARATOR.'ErrorHandling.php');

/*** include template class ***/
require_once(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'scms'.DIRECTORY_SEPARATOR.'core'.DIRECTORY_SEPARATOR.'Template.class.php');

/*** include mysql driver ***/
require_once(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'scms'.DIRECTORY_SEPARATOR.'db'.DIRECTORY_SEPARATOR.'engine'.DIRECTORY_SEPARATOR.'dbMysql.php');
/*** include Crud ***/
require_once(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'scms'.DIRECTORY_SEPARATOR.'db'.DIRECTORY_SEPARATOR.'Model.php');
/*** include login model ***/
require_once(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'scms'.DIRECTORY_SEPARATOR.'core'.DIRECTORY_SEPARATOR.'Login.model.php');
/*** include session model ***/
require_once(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'scms'.DIRECTORY_SEPARATOR.'core'.DIRECTORY_SEPARATOR.'Session.model.php');
/*** include user model ***/
require_once(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'scms'.DIRECTORY_SEPARATOR.'core'.DIRECTORY_SEPARATOR.'User.model.php');
/*** include role model ***/
require_once(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'scms'.DIRECTORY_SEPARATOR.'core'.DIRECTORY_SEPARATOR.'Role.model.php');

/*** Get Route From URL ***/
$Application->Router->getURLRoute();

/*** create the session Model object ***/
$Application->Session = Session::getInstance($Application);

/*** create the user Model object ***/
$Application->User = User::getInstance($Application);

/*** create the role Model object ***/
$Application->Role = Role::getInstance($Application);

/*** create the login Model object ***/
$Application->Login = Login::getInstance($Application);

/*** set up the Template class ***/
$Application->Template = Template::getInstance($Application);

/*** load the controller ***/
$Application->Router->loader();

session_write_close();
?>