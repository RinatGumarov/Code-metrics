<?php if(!defined('SCMS_INSTALL_PATH')) {die('Direct Access Forbbiden!');} ?>
<form onsubmit="return validate(this,'remind');" action="<?php echo $url;?>" method="post" id="frmRemind">
<table>
<tr>	
    <td align="right">Username:</td>
    <td><input type="text" name="user" maxlength="20" size="30" /></td>
</tr>
<tr>
    <td align="right">Email:</td>
    <td><input type="text" name="email" maxlength="50" size="30" /></td>
</tr>
<?php
  require_once('include/captcha.php');
?>
<tr>
    <td colspan="2" align="right">
      <input type="hidden" name="<?php echo $_SESSION['CSRF_Token'];?>" value="<?php echo $_SESSION['CSRF_Value'];?>" />
      <input style="width:100%;" type="submit" name="exec" size="50" value="Remind Me" />
    </td>
</tr>
<tr>
    <td align="right" colspan="2"><a href="/login" >Back To Login Page</a></td>
</tr>
</table>
</form>
<br />
<?php if(isset($errormsg)) {echo '<span class="errmsg">'.$errormsg.'</span>';} ?>

