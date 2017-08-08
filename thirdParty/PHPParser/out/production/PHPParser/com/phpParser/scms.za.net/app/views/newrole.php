<?php if(!defined('SCMS_INSTALL_PATH')) {die('Direct Access Forbbiden!');}
if(isset($errormsg)) {echo '<span class="errmsg">'.$errormsg.'</span><br /><br />';} ?>
<form onsubmit="return validate(this,'role');" action="<?php echo $url;?>" method="post" id="frmNewRole">
<table>
<tr>	
    <td align="right">Role Name:</td>
    <td><input type="text" size="30" maxlength="30" name="role"/> *</td>
</tr>
<tr>
    <td align="right" style="vertical-align: top;">Role Description:</td>
    <td><textarea cols="24" rows="5" name="role_desc" ></textarea></td>
</tr>
<?php
  require_once('include/captcha.php');
?>
<tr>
    <td><input type="hidden" name="<?php echo $_SESSION['CSRF_Token'];?>" value="<?php echo $_SESSION['CSRF_Value'];?>" /></td>
    <td align="right">
    <span style="font-size: small;">*Required Fields</span>
    <input type="submit" name="exec" size="50" value="Add New Role" />
    </td>
</tr>
</table>
</form>