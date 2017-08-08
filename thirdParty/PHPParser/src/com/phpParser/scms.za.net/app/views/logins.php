<?php if(!defined('SCMS_INSTALL_PATH')) {die('Direct Access Forbbiden!');} ?>
<?php if(isset($errormsg)) {echo '<span class="errmsg">'.$errormsg.'</span><br /><br />';}

$RowCount = count($AllRows);

if($RowCount>0) {
?>
<form onsubmit="return ConfirmDelete();" action="/logins/clear" method="post">
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
<table class="info" style="font-size:small;" cellspacing="5" cellpadding="5">
    <tr align="left">
        <th>Time</th>
        <th>IP</th>
        <th>Agent</th>
    </tr>
<?php	

for($r=0;$r<$RowCount;$r++) { 
        $Row = $AllRows[$r];
?>	
    <tr align="center" <?php if(!$Row->scms_lSuccess) {echo 'style="background-color:red;color:#000000;"';}?>>
        <td class="info"><?php echo $Row->scms_lTried;?></td>
        <td class="info"><?php
        if(strlen($Row->scms_lX_FWD_IP)==0) {
                echo $Row->scms_lIP;
        } else {
                echo $Row->scms_lX_FWD_IP;
        }
                ?></td>
        <td class="info"><?php echo $Row->scms_lUserAgent; ?></td>
    </tr>
<?php 
        }
?>
</table>
<?php
} else {
?>
        <span class="errmsg">No Logins Found!</span>
<?php	
}
?>	