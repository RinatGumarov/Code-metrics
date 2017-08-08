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

class loginsController extends Controller {
    public $RequireSSL = array();

    public function index() {
        $this->view();
    }

    public function view() {
      if($this->Application->Session->GetSessionStatus(session_id())!='Active') {
        $this->Application->redirectTo('/login?errno=7');
      }
      $this->Application->Role->IsRootRole();
        
      $this->Application->Template->PAGE_TITLE = 'SCMS All Logins';
      //Get All Records
      $this->Application->Template->AllRows = $this->Application->Login->Read('','',array('scms_lTried','DESC'),null);
      $this->Application->Template->show('logins');
    }

    public function clear() {
      if($this->Application->Session->GetSessionStatus(session_id())!='Active') {
        $this->Application->redirectTo('/login?errno=7');
      }
      $this->Application->Role->IsRootRole();

      if(!$this->Application->checkCSRFAccessToken()) {
          $this->Application->Template->errormsg = 'Cross Site Request Forgery Detected!';
          $this->index();
          exit;
      } elseif(!$this->Application->checkCAPTCHA()) {
          $this->Application->Template->errormsg = 'Incorrect CAPTCHA letters!';
          $this->index();
          exit;
      } else {
          if($this->Application->Login->Delete('All','All')) {
              $this->Application->Template->errormsg='All Login Logs Deleted Successfully!';
          } else {
              $this->Application->Template->errormsg='Failed To Delete All Login Logs!';
          }
          //Show Index
          $this->index();
      }
    }


}

?>
