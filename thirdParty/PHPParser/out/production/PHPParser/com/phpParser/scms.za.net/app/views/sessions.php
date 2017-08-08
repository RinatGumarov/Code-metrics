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

if(isset($errormsg)) {echo '<span class="errmsg">'.$errormsg.'</span><br /><br />';} ?>
<?php 
$RowCount = count($AllRows);

if($RowCount>0) {
?>
<form onsubmit="return ConfirmDelete();" action="/sessions/clear" method="post">
<table>
<?php
      require_once('include/captcha.php');
?>
<tr>
    <td colspan="2">
      <input type="hidden" name="<?php echo $_SESSION['CSRF_Token'];?>" value="<?php echo $_SESSION['CSRF_Value'];?>" />
      <input name="clear" onclick="return confirmAction('clear');" style="width:100%;" id="clear" type="submit" value="Clear Logs" />
    </td>
</tr>
</table>
</form>
<br />
<div class="errmsg"><?php echo $RowCount.' records found.'?></div>
<table class="info" cellspacing="5" cellpadding="5" style="font-size: small;">
    <tr align="left">
        <th>Secure</th>
        <th>Created</th>
        <th>Status</th>
        <th>Remote IP</th>
        <th>Forwarded IP</th>
        <th>Agent</th>
    </tr>
<?php	

for($r=0;$r<$RowCount;$r++) { 
        $Row = $AllRows[$r];
?>	
<tr align="center" <?php
if($Row->scms_sStatus=='Active'){
        echo 'style="background-color:#00CC66;color:#000000;"';
} elseif($Row->scms_sStatus=='Expired') {
        echo 'style="background-color:#FF0033;color:#000000;"';
}?>>
    <td class="info">
<?php
    if($Row->scms_SecureSession) {
?>
    <img src="/images/secure.png" alt="secure" />
<?php
    } else {
        echo '&nbsp;';
    }
?>
    </td>
        <td class="info"><?php echo $Row->scms_sCreated;?></td>
        <td class="info"><?php echo $Row->scms_sStatus;?></td>
        <td class="info"><?php echo $Row->scms_sIP; ?></td>
        <td class="info"><?php echo $Row->scms_sX_FWD_IP;?></td>
        <td class="info"><?php echo $Row->scms_sUserAgent;?></td>

</tr>
<?php 
        }
?>
</table>
<?php
} else {
?>
    <span class="errmsg">No Sessions Found!</span>
<?php	
}
?>	