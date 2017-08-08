<?php if(!defined('SCMS_INSTALL_PATH')) {die('Direct Access Forbbiden!');} ?>
<form onsubmit="return validate(this,'changer');" action="<?php echo $url;?>" method="post" id="frmChange">
<table>
<tr>	
    <td align="right">New Password:</td>
    <td><input type="password" name="password" size="30" /></td>
</tr>
<tr>
    <td align="right">Confirm Password:</td>
    <td><input type="password" name="confirm" size="30" /></td>
</tr>
<?php
  require_once('include/captcha.php');
?>
<tr>
    <td colspan="2" align="right">
      <input type="hidden" name="remindercode" value="<?php echo $remindercode;?>" />
      <input type="hidden" name="<?php echo $_SESSION['CSRF_Token'];?>" value="<?php echo $_SESSION['CSRF_Value'];?>" />
      <input style="width:100%;" type="submit" name="exec" size="50" value="Change Password" />
    </td>
</tr>
<tr>
    <td align="right" colspan="2"><a href="/login" >Back To Login Page</a></td>
</tr>
</table>
</form>

