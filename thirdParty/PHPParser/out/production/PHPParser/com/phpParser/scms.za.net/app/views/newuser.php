<?php if(!defined('SCMS_INSTALL_PATH')) {die('Direct Access Forbbiden!');} 
if(isset($errormsg)) {echo '<span class="errmsg">'.$errormsg.'</span><br /><br />';} ?>
<form onsubmit="return validate(this,'user');" action="<?php echo $url;?>" method="post" id="frmNewUser">
<table>
<tr>
    <td align="right" style="vertical-align: top;">Role Name:</td>
    <td><select name="role">
    <option value="0">Please Select A Role</option>
<?php
for($i=0;$i<count($AllRoles);$i++) {
$role=$AllRoles[$i];
echo '<option value="'.$role->scms_RID.'">'.stripcslashes($role->scms_RoleName).'</option>';
}
?>
        </select>*</td>
</tr>
<tr>
        <td align="right" style="vertical-align: top;">User Name:</td>
        <td><input type="text" name="user" maxlength="20" size="30" />*</td>
</tr>
<tr>
        <td align="right" style="vertical-align: top;">Password:</td>
        <td><input type="password" name="password" maxlength="30" size="30" />* using <?php echo scmsEncryption_Type;?></td>
</tr>
<tr>
        <td align="right" style="vertical-align: top;">Email:</td>
        <td><input type="text" name="email" maxlength="40" size="40" />*</td>
</tr>
<?php
  require_once('include/captcha.php');
?>
<tr>
    <td><input type="hidden" name="<?php echo $_SESSION['CSRF_Token']; ?>" value="<?php echo $_SESSION['CSRF_Value']; ?>" /></td>
    <td align="right">
    <span style="font-size:small">*Required Fields</span>
    <input type="submit" name="exec" size="50" value="Add user" /></td>
</tr>
</table>
</form>

