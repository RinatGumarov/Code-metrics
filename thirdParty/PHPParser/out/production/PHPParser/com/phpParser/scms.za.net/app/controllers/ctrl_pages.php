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

class pagesController extends Controller {
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
          $this->Application->Template->AllRows = $this->Application->Role->Read('scms_RID,scms_RoleName,scms_RoleDesc',"scms_RID='$Id'",array('scms_RID','ASC'));
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
              switch($_POST['exec']) {
                  case 'update':
                      if(!$this->Validate($Msg)) {
                          $this->Application->Template->errormsg = $Msg;
                      } else {
                          $Values = array('scms_RoleName'=>strtoupper($_POST['role']),'scms_RoleDesc'=>$_POST['role_desc']);
                          $WhereClause = array('scms_RID'=>$RID);

                          if($this->Application->Role->Update($Values,$WhereClause)) {
                              $this->Application->Template->errormsg = 'Role Updated Successfully!';
                          } else {
                               $this->Application->Template->errormsg = 'Failed To Update Role!';
                          }
                      }
                  break;
                  case 'delete':
                      $PrimaryKeyValue = intval($_POST['id']);

                      if($PrimaryKeyValue>1002) {
                          if($this->Application->Role->Delete($PrimaryKeyValue)) {
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
      $this->Application->Template->url = 'http://'.$_SERVER['SERVER_NAME'].'/pages/edit/';
      $this->Application->Template->paging_url = 'http://'.$_SERVER['SERVER_NAME'].'/pages/view/';
      $this->Application->Template->current_page = $page;
      $this->Application->Template->PAGE_TITLE = 'All Static Pages';
      /*** Get All Records ***/
      $RecCount = $this->Application->Role->Read('count(id) as RecCount');
      $this->Application->Template->TotalRecordCount = $RecCount[0]->RecCount;
      $this->Application->Template->AllRows = $this->Application->Role->Read('*','',array('id','ASC'),$page);
      $this->Application->Template->show('newpage');
    }

    private function Validate(&$msg) {
        
        /*** Valid Role ***/
        return true;
    }

    public function create() {
      

      $this->displayform();
    }

    private function displayform() {
        $this->Application->Template->url = 'http://'.$_SERVER['SERVER_NAME'].'/roles/create';
        $this->Application->Template->PAGE_TITLE = ' New Static Page';
        $this->Application->Template->show('newpage');        
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
