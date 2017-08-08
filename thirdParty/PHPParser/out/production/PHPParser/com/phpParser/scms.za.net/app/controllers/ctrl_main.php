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

class mainController extends Controller {
    public $RequireSSL = array();

    public function menu() {
      if($this->Application->Session->GetSessionStatus(session_id())!='Active') {
        $this->Application->redirectTo('/login?errno=7');
      }
      $this->Application->Role->IsRootRole();
      /*** set Template variables ***/
      $this->Application->Template->PAGE_TITLE = 'SCMS MVC Framework';
      /*** load the index template ***/
      $this->Application->Template->show('menu');
    }

    public function logout($token=null,$value=null) {
        if(!$this->Application->checkCSRFAccessToken($token,$value)) {
            return;
        }

        if(!$this->Application->Session->destroy(session_id())) {
            trigger_error('Error Destroying Session!');
            return false;
        }
        
        if(!$this->Application->Session->Regen(true)) {
            trigger_error('Error Regenerating Session!');
            return false;
        }
        
        /*** Reset Login Attempts Counter ***/
        $User = $this->Application->User->GetUserBySID(session_id());
        $this->Application->User->ResetLockOutCounter($User->scms_UID);

        $this->Application->redirectTo('/login');
    }

    public function login($unlockCode=false) {
        if($this->Application->Session->GetSessionStatus(session_id())=='Active') {
          $this->Application->redirectTo('/menu');
          exit;
        }

        $this->Application->Template->PAGE_TITLE = 'SCMS Framework - Login';
        $this->Application->Template->url = 'http://'.$_SERVER['SERVER_NAME'].'/authenticate';

        $errno = 0;

        /*** Unlock Account ***/
        if($unlockCode) {
            if(!$this->Application->UnlockAccountViaCode($unlockCode)) {
              $this->Application->Template->errormsg = 'Incorrect Unlock Code!';
            } else {
              $this->Application->Template->errormsg = 'Your Account has been unlocked.<br />Please try to login again!';
            }
        } elseif(isset($_GET['errno'])) {
          $errno = intval($_GET['errno']);

          switch($errno){
              case 1:
              $this->Application->Template->errormsg = 'Please enter a username!';
              break;
              case 2:
              $this->Application->Template->errormsg = 'Please enter a password!';
              break;
              case 3:
              $this->Application->Template->errormsg = 'Authentication failed!<br />Incorrect username/password!';
              break;
              case 4:
              $this->Application->Template->errormsg = 'Expired Session!<br />Please login again!';
              break;
              case 5:
              $this->Application->Template->errormsg = 'Incorrect CAPTCHA letters!';
              break;
              case 6:
              $this->Application->Template->errormsg = 'Cross Site Request Forgery Detected!';
              break;
              case 7:
              $this->Application->Template->errormsg = 'Invalid Session!';
              break;
              case 8:
              $this->Application->Template->errormsg = 'Insufficient Permissions!';
              break;
              case 9:
              $this->Application->Template->errormsg = 'Account Locked';
              break;
              default:
              $this->Application->Template->errormsg = '&nbsp;';
          }/*** End Switch ***/
        }
        $this->Application->Template->show('login');
    }

    public function authenticate() {
        $Username = trim($_POST['user']);
        $Password = trim($_POST['passwd']);

        if(!$this->Application->checkCSRFAccessToken()) {
            $this->Application->redirectTo('/login?errno=6');
        }

        $AUTH_CODE = $this->Application->User->Authenticate($Username,$Password,$REMINDER_KEY);

        switch($AUTH_CODE) {
            case 'AUTH_BLANK_USERNAME':
                $this->Application->redirectTo('/login?errno=1');
            exit;
            case 'AUTH_BLANK_PASSWORD':
                $this->Application->redirectTo('/login?errno=2');
            exit;
            case 'AUTH_INVALID_LOGIN':
                $this->Application->redirectTo('/login?errno=3');
            exit;
            case 'AUTH_CAPTCHA_ERROR':
                $this->Application->redirectTo('/login?errno=5');
            exit;
            case 'AUTH_LOGIN_EXPIRED':
                $this->Application->redirectTo('/users/remind/'.$REMINDER_KEY.'/');
            exit;
            case 'AUTH_AUTHENTICATED':
                $this->Application->redirectTo('/menu');
            exit;
            case 'AUTH_LOCKED_ACCOUNT':
                $this->Application->redirectTo('/login?errno=9');
            exit;
        }

    }

    public function error($errNo=false) {
        switch($errNo) {
            case 404:
            header('HTTP/1.0 404 Not Found');
            $error_text = 'The file you are looking for was not found';
            $error_title= 'SCMS 404 Error';
            break;
            case 400:
            header('HTTP/1.0 400 Authorization Required');
            $error_text = 'Authorization Required';
            $error_title= 'SCMS Access Denied';
            break;
            default:
            $error_text = 'A SCMS Web Application Error has occurred.';
            $error_title = 'SCMS Error';
        }
        
        $this->Application->Template->PAGE_TITLE = $error_title;
        $this->Application->Template->ERROR = $error_text;
	/*** load the index template ***/
        $this->Application->Template->show('error');
    }

    public function index() {
	/*** set Template variables ***/
        $this->Application->Template->PAGE_TITLE = 'SCMS MVC Framework';
	/*** load the index template ***/
        $this->Application->Template->show('intro');
    } 

    private function make_seed() {
        list($usec, $sec) = explode(' ', microtime());
        return (float) $sec + ((float) $usec * 100000);
    }    
    
    public function captcha() {
        $bgcolor_r=rand(0,255);
        $bgcolor_g=rand(0,255);
        $bgcolor_b=rand(0,255);
        /*** Number Of Chars To Generate ***/
        $NoChars = 5;
        $AllFonts = array();

        $width=150;
        $height=50;

        // Save all available fonts
        foreach (glob(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'docroot'.DIRECTORY_SEPARATOR.'fonts'.DIRECTORY_SEPARATOR.'*.ttf') as $font) {
            array_push($AllFonts, $font);
        }        
        
        // Seed the PRNG!
        srand($this->make_seed());
        
        //Random True Type Font File
        $font = $AllFonts[rand(0,count($AllFonts)-1)];                    
        
        //Create Image
        $Image = imagecreatetruecolor($width,$height);
        //Create Background Colour
        $bgcolor = imagecolorallocate($Image, $bgcolor_r, $bgcolor_g, $bgcolor_b);
        imagefill($Image, 0, 0, $bgcolor);

        //Random Text
        $text = strtoupper($this->Application->User->RegenPassword($NoChars));
        $_SESSION['SCMS_CAPTCHA'] = $text;

        $size=20;
        $x=10;
        $y_min = ($height / 2) + ($size / 2) - 2;
        $y_max = ($height / 2) + ($size / 2) + 2;

        $letter_colors = array();
        $line_colors = array();

        $min_color_interval = 100;

        for($i=0;$i<strlen($text);$i++) {
            $letter_colors['r']=rand(0,255);
            $letter_colors['g']=rand(0,255);
            $letter_colors['b']=rand(0,255);
            $line_colors['r']=rand(0,255);
            $line_colors['g']=rand(0,255);
            $line_colors['b']=rand(0,255);

            //Check RGB For Letters
            if(abs($letter_colors['r']-$bgcolor_r)<$min_color_interval) {
                    $letter_colors['r']=$letter_colors['r'] + $min_color_interval;
            }
            if(abs($letter_colors['g']-$bgcolor_g)<$min_color_interval) {
                    $letter_colors['g']=$letter_colors['g'] + $min_color_interval;
            }
            if(abs($letter_colors['b']-$bgcolor_b)<$min_color_interval) {
                    $letter_colors['b']=$letter_colors['b'] + $min_color_interval;
            }
            //Check RGB For Lines
            if(abs($line_colors['r']-$bgcolor_r)<$min_color_interval) {
                    $line_colors['r']=$line_colors['r'] + $min_color_interval;
            }
            if(abs($line_colors['g']-$bgcolor_g)<$min_color_interval) {
                    $line_colors['g']=$line_colors['g'] + $min_color_interval;
            }
            if(abs($line_colors['b']-$bgcolor_b)<$min_color_interval) {
                    $line_colors['b']=$line_colors['b'] + $min_color_interval;
            }

            $color = imagecolorallocate($Image, $letter_colors['r'], $letter_colors['g'], $letter_colors['b']);
            $linecolor = imagecolorallocate($Image, $line_colors['r'], $line_colors['g'], $line_colors['b']);

            if($bgcolor==$color || $linecolor==$bgcolor) {
                    $i--;
                    continue;
            }
            $xpos = $x+($i*rand($size+10,($size*2.5)/2));
            imagettftext($Image, $size, rand(-20,20), $xpos, rand($y_min,$y_max), $color, $font, substr($text,$i,1));

            $start=rand(45, 111);
            $end=$start+rand(75, 100);
            $ypos=($height/2)-rand(10,20);
            $xpos=$xpos+$size+($i * rand(1,3));
            imagesetthickness($Image, rand(1,3));
            //Draw Arc
            $w  = $width / 2.66 + rand(3, 10);
            $h = $size * 2.14 - rand(3, 10);
            imagearc($Image, $xpos, $ypos, $w, $h, $start, $end, $color);

            //Draw Line
            imagesetthickness($Image,1);
            imageline($Image,rand($width*-1,$width),rand($height*-1,$height),rand(0,$width),rand(0,$height),$linecolor);
        }


        header('Content-Type: image/png');
        imagepng($Image);
        imagedestroy($Image);
    }
    
}
?>
