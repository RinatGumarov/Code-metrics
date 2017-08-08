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

class rolesController extends Controller {
    public $RequireSSL = array();

    public function index() {
      $this->view();
    }

    public function edit($Id=false) {
      if($this->Application->Session->GetSessionStatus(session_id())!='Active') {
        $this->Application->redirectTo('/login?errno=7');
      }
      $this->Application->Role->IsRootRole();
        
      $Id = intval($Id);

      if($Id) {
          $this->Application->Template->url = 'http://'.$_SERVER['SERVER_NAME'].'/roles/update';
          $this->Application->Template->PAGE_TITLE = 'SCMS Role';
          /*** Get All Records ***/
          $RoleModel = Role::getInstance($this->Application);
          $this->Application->Template->AllRows = $RoleModel->Read('scms_RID,scms_RoleName,scms_RoleDesc',"scms_RID='$Id'",array('scms_RID','ASC'));
          $this->Application->Template->show('role');
      } else {
          $this->view();
      }
    }

    public function update() {
      if($this->Application->Session->GetSessionStatus(session_id())!='Active') {
        $this->Application->redirectTo('/login?errno=7');
      }
      $this->Application->Role->IsRootRole();

      /*** POST CODE ***/
      if(isset($_POST['id']) && isset($_POST['exec'])) {
          $RID = intval($_POST['id']);

          if(!$this->Application->checkCSRFAccessToken()) {
              $this->Application->Template->errormsg = 'Cross Site Request Forgery Detected!';
          } elseif(!$this->Application->checkCAPTCHA()) {
              $this->Application->Template->errormsg = 'Incorrect CAPTCHA letters!';
          } elseif(!$this->Validate($Msg)) {
              $this->Application->Template->errormsg = $Msg;
          } else {
              $RoleModel = Role::getInstance($this->Application);
              switch($_POST['exec']) {
                  case 'update':
                      if(!$this->Validate($Msg)) {
                          $this->Application->Template->errormsg = $Msg;
                      } else {
                          $Values = array('scms_RoleName'=>strtoupper($_POST['role']),'scms_RoleDesc'=>$_POST['role_desc']);
                          $WhereClause = array('scms_RID'=>$RID);

                          if($RoleModel->Update($Values,$WhereClause)) {
                              $this->Application->Template->errormsg = 'Role Updated Successfully!';
                          } else {
                               $this->Application->Template->errormsg = 'Failed To Update Role!';
                          }
                      }
                  break;
                  case 'delete':
                      $PrimaryKeyValue = intval($_POST['id']);

                      if($PrimaryKeyValue>1002) {
                          if($RoleModel->Delete($PrimaryKeyValue)) {
                              $this->Application->Template->errormsg = 'Role Deleted Successfully!';
                          } else {
                               $this->Application->Template->errormsg = 'Failed To Delete Role!';
                          }
                      } else {
                           $this->Application->Template->errormsg = 'Please do not delete the SCMS demo roles!';
                      }
                  break;
              }/*** End Switch ***/
          }
      }/*** POST CODE ***/

      $this->view();
    }

    public function view($page=1) {
      if($this->Application->Session->GetSessionStatus(session_id())!='Active') {
        $this->Application->redirectTo('/login?errno=7');
      }
      $this->Application->Role->IsRootRole();
        
      $page = (intval($page))?$page:1;
      $this->Application->Template->url = 'http://'.$_SERVER['SERVER_NAME'].'/roles/edit/';
      $this->Application->Template->paging_url = 'http://'.$_SERVER['SERVER_NAME'].'/roles/view/';
      $this->Application->Template->current_page = $page;
      $this->Application->Template->PAGE_TITLE = 'SCMS All Roles';
      /*** Get All Records ***/
      $RoleModel = Role::getInstance($this->Application);
      $RecCount = $RoleModel->Read('count(scms_RID) as RecCount');
      $this->Application->Template->TotalRecordCount = $RecCount[0]->RecCount;
      $this->Application->Template->AllRows = $RoleModel->Read('scms_RID, scms_RoleName','',array('scms_RID','ASC'),$page);
      $this->Application->Template->show('role');
    }

    private function Validate(&$msg) {
        if(strlen($_POST['role'])<4 || strlen($_POST['role'])>30) {
            $msg='Role Name must be between 4 and 30 characters!';
            return false;
        }

        //Only Allow A-z and 0-9 in role name
        if(!preg_match('/^[A-z0-9]*$/', $_POST['role']))  {
            $msg='Role Name can only consist of Alphanumeric (A-z and 0-9) characters!';
            return false;
        }

        if(strlen($_POST['role_desc'])>0) {
        //Role Description Is Defined
            if(strlen($_POST['role_desc'])<10 || strlen($_POST['role_desc'])>100) {
                $msg='Role Description must be between 10 and 100 characters!';
                return false;
            }

            //Only Allow Space, A-z and 0-9 in role description
            if(preg_match("/^[A-z 0-9\n\r\.\',]*$/",$_POST['role_desc'])===false)  {
                $msg='Role Description can only consist of Alphanumeric (A-z and 0-9) and (comma, full-stop, single-quote and space) characters!';
                return false;
            }
        }

        /*** Valid Role ***/
        return true;
    }

    public function create() {
      if($this->Application->Session->GetSessionStatus(session_id())!='Active') {
        $this->Application->redirectTo('/login?errno=7');
      }
      $this->Application->Role->IsRootRole();

      /*** POST CODE ***/
      if(isset($_POST['exec'])) {
          if(!$this->Application->checkCSRFAccessToken()) {
              $this->Application->Template->errormsg = 'Cross Site Request Forgery Detected!';
          } elseif(!$this->Application->checkCAPTCHA()) {
              $this->Application->Template->errormsg = 'Incorrect CAPTCHA letters!';
          } elseif(!$this->Validate($Msg)) {
                  $this->Application->Template->errormsg = $Msg;
          } else {
              $RoleModel = Role::getInstance($this->Application);
              $Values = array('scms_RoleName'=>strtoupper($_POST['role']),'scms_RoleDesc'=>$_POST['role_desc']);
              if($RoleModel->Create($Values)) {
                  $this->Application->Template->errormsg = 'New Role Created Successfully!';
              } elseif(1==1062) {
                  $this->Application->Template->errormsg = 'A Role with that name already exists!<br />Please choose a <u>Case Insensitive Unique</u> Role Name.';
              }else {
                   $this->Application->Template->errormsg = 'Failed To Create New Role!';
              }
          }
      }/*** POST CODE ***/

      $this->displayform();
    }

    private function displayform() {
        $this->Application->Template->url = 'http://'.$_SERVER['SERVER_NAME'].'/roles/create';
        $this->Application->Template->PAGE_TITLE = 'SCMS New Role';
        $this->Application->Template->show('newrole');        
    }

    public function add() {
      if($this->Application->Session->GetSessionStatus(session_id())!='Active') {
        $this->Application->redirectTo('/login?errno=7');
      }
      $this->Application->Role->IsRootRole();

      $this->displayform();
    }


}

?>
