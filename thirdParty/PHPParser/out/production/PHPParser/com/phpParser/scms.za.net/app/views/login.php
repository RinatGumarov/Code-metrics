<?php
if(!defined('SCMS_INSTALL_PATH')) {die('Direct Access Forbbiden!');}
?>

Please use the form below to login into SCMS.<br /><br />
ROOT Access:&nbsp;&nbsp;root:HackScms2012<br />
User Access:&nbsp;&nbsp;demo:LetMeIns1d3<br /><br />
<form onsubmit="return validate(this,'login');" action="<?php echo $url;?>" method="post" id="frmLogin">
<table>
<tr>	
    <td align="right">Username:</td>
    <td><input type="text" name="user" id="user" maxlength="20" size="20" /></td>
</tr>
<tr>
    <td align="right">Password:</td>
    <td><input type="password" name="passwd" id="passwd" maxlength="20" size="20" /></td>
</tr>
<?php
  require_once('include/captcha.php');
?>
<tr>
  <td colspan="2">
    <input type="hidden" name="<?php echo $_SESSION['CSRF_Token'];?>" value="<?php echo $_SESSION['CSRF_Value'];?>" />
    <input style="width:100%;" type="submit" name="login" size="50" value="Login" />
  </td>
</tr>
<tr>
    <td align="right" colspan="2"><a href="/users/remind">Forgotten Credentials?</a></td>
</tr>
</table>
</form>
<br />
<?php if(isset($errormsg)) {echo '<span class="errmsg">'.$errormsg.'</span>';} ?>

