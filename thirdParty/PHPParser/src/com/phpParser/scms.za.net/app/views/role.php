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

if(isset($errormsg)) {echo '<span class="errmsg">'.$errormsg.'</span><br /><br />';}
$RowCount = count($AllRows);

if(isset($paging_url) && isset($current_page) && isset($TotalRecordCount)) {
//Paging Navigation
?>
<p>Showing <?php echo (RecordsPerPage*$current_page)-RecordsPerPage.' - '.(RecordsPerPage*$current_page).' of '.$TotalRecordCount; ?> Total Records.</p><br />
<a href="<?php echo $paging_url.(1); ?>">&lt;&lt; First</a>&nbsp;|&nbsp;<a href="<?php echo ($current_page>1)?$paging_url.($current_page-1):'1'; ?>">&lt; Previous</a>&nbsp;|&nbsp;<a href="<?php echo ($current_page<round($TotalRecordCount/RecordsPerPage))?$paging_url.($current_page+1):round($TotalRecordCount/RecordsPerPage); ?>">Next &gt;</a>&nbsp;|&nbsp;<a href="<?php echo $paging_url.round($TotalRecordCount/RecordsPerPage); ?>">Last &gt;&gt;</a><br />
<br />
<?php
}
if($RowCount>0) {
    for($r=0;$r<$RowCount;$r++) {
        $Row = $AllRows[$r];
        if(isset($paging_url) && isset($current_page) && isset($TotalRecordCount)) {
?>
<form action="<?php echo $url.$Row->scms_RID;?>" method="post" id="frmUpdateRole_<?php echo $Row->scms_RID; ?>" >
  <div>
    <input type="submit" name="role" maxlength="30" style="width:30%;" value="<?php echo $Row->scms_RID.':'.htmlentities(stripslashes($Row->scms_RoleName),ENT_QUOTES);?>" />
    <input type="hidden" name="<?php echo $_SESSION['CSRF_Token']; ?>" value="<?php echo $_SESSION['CSRF_Value']; ?>" />
  </div>
</form>
<?php
        } else {
?>
        <form onsubmit="return validate(this,'role');" action="<?php echo $url;?>" method="post" id="frmUpdateRole_<?php echo $Row->scms_RID; ?>">
        <table>
        <tr>
            <td align="right" style="vertical-align: top;">Role ID:</td>
            <td>
            <?php echo $Row->scms_RID;?>
            <input type="hidden" name="id" value="<?php echo $Row->scms_RID;?>" />
            </td>
        </tr>
        <tr>
            <td align="right" style="vertical-align: top;">Role Name:</td>
            <td><input type="text" name="role" maxlength="30" size="30" value="<?php echo htmlentities(stripslashes($Row->scms_RoleName),ENT_QUOTES);?>" />*</td>
        </tr>
        <tr>
            <td align="right" style="vertical-align: top;">Role Description:</td>
            <td><textarea name="role_desc"  rows="5" cols="24"><?php echo htmlentities(stripslashes($Row->scms_RoleDesc),ENT_QUOTES);?></textarea></td>
        </tr>
<?php
        require_once('include/captcha.php');
?>
        <tr>
            <td><input type="hidden" name="<?php echo $_SESSION['CSRF_Token']; ?>" value="<?php echo $_SESSION['CSRF_Value']; ?>" /></td>
            <td align="right">
            <span style="font-size:small">*Required Fields</span>
            <input type="submit" name="exec" size="50" value="update" />
            <input type="submit" name="exec" size="50" value="delete" onclick="return confirmAction('delete');" />
            </td>
        </tr>
        </table>
        </form>
<?php
        }
    }/*** End For Loop ***/
} else { ?>
    <span class="errmsg">No Roles Found!</span>
<?php
}
?>