<?php
if(!defined('SCMS_INSTALL_PATH')) {die('Direct Access Forbbiden!');}

if(isset($response)) {
?>
  <div style="text-align:center;font-size:larger;color:red;background-color:inherit;"><?php echo $response;?></div><br />
<?php
}
?>
<br />
<form action="/configs/save/" method="post">
<table>
  <tr>
    <th colspan="2" style="font-weight: bolder;">GENERAL<hr /></th>
  </tr>
  <tr>
    <td align="right">SCMS Version</td>
    <td><?php echo ScmsVersion;?></td>
  </tr>
  <tr>
    <td align="right">Debug Mode</td>
    <td>
      <select name="DebugMode">
        <option <?php if(DebugMode) {echo 'selected="selected"';}?> value="1">true</option>
        <option <?php if(!DebugMode) {echo 'selected="selected"';}?> value="0">false</option>
      </select><img style="cursor:help;" src="/images/info.png" alt="When in Debug Mode, errors are displayed along with stack-traces" title="When in Debug Mode, errors are displayed along with stack-traces"/>
    </td>
  </tr>
  <tr>
    <td align="right">Enable CAPTCHA</td>
    <td>
      <select name="EnableCAPTCHA" id="EnableCAPTCHA">
        <option <?php if(EnableCAPTCHA) {echo 'selected="selected"';}?> value="1">Yes</option>
        <option <?php if(!EnableCAPTCHA) {echo 'selected="selected"';}?> value="0">No</option>
      </select><img style="cursor:help" src="/images/info.png" alt="Increases protection against CSRF as well as any scripted attacks" title="Increases protection against CSRF as well as any scripted attacks"/>
    </td>
  </tr>
  <tr>
    <td align="right">Records Per Page</td>
    <td><input style="width:10%;" name="RecordsPerPage" id="RecordsPerPage" type="text" maxlength="3" value="<?php echo intval(RecordsPerPage);?>"/><img style="cursor:help" src="/images/info.png" alt="How many records to show per page" title="How many records to show per page"/></td>
  </tr>
  <tr>
    <td align="right">Administrator Email</td>
    <td><input style="width:50%;" type="text" name="AdminEmail" id="AdminEmail" value="<?php echo htmlentities(AdminEmail,ENT_QUOTES); ?>" /><img style="cursor:help" src="/images/info.png" alt="Where to send errors when not in Debug Mode" title="Where to send errors when not in Debug Mode"/></td>
  </tr>  
  <tr>
    <th colspan="2" style="font-weight: bolder;">ENCRYPTION<hr /></th>
  </tr>
  <tr>
    <td align="right">Password Hashing/Encryption Algo</td>
<?php
$InsecureAlgos = array('md2','md4','md5','ripemd128','ripemd160','adler32','crc32','crc32b','salsa10','salsa20','tiger128,3','tiger160,3','tiger128,4','tiger160,4','snefru','haval128,3','haval160,3','haval192,3','haval224,3','haval128,4','haval160,4','haval192,4','haval224,4','haval128,5','haval160,5','haval192,5','haval224,5','sha1','sha224');
?>
    <td>
      <select name="scmsEncryption_Type" id="scmsEncryption_Type">
        <option value="aes">MySql AES</option>
        <option value="des">MySql DES</option>
<?php
        foreach(hash_algos() as $algo) {
?>
        <option <?php if(in_array($algo,$InsecureAlgos)){ echo 'disabled="disabled"'; } else if($algo == scmsEncryption_Type) { echo 'selected="selected"'; }?> value="<?php echo $algo; ?>"><?php echo $algo; ?></option>
<?php
        }
?>
      </select><img style="cursor:help" src="/images/info.png" alt="Encryption/Hashing Algorythm used for secure storage of user passwords" title="Encryption/Hashing Algorythm used for secure storage of user passwords"/>
    </td>
  </tr>
  <tr>
    <td align="right" style="vertical-align: top;">Algo Key</td>
    <td><textarea cols="50" rows="2" name="scmsEncryption_Key" id="scmsEncryption_Key"><?php echo htmlentities(scmsEncryption_Key, ENT_QUOTES); ?></textarea><img style="cursor:help" src="/images/info.png" alt="Symmetric Encryption Key" title="Symmetric Encryption Key"/></td>
  </tr>
  <tr>
    <td align="right" style="vertical-align: top;">Salt</td>
    <td><textarea cols="50" rows="2" name="scmsEncryption_Salt" id="scmsEncryption_Salt"><?php echo htmlentities(scmsEncryption_Salt, ENT_QUOTES); ?></textarea><img style="cursor:help" src="/images/info.png" alt="Symmetric Encryption Salt" title="Symmetric Encryption Salt"/></td>
  </tr>
  <tr>
    <th colspan="2" style="font-weight: bolder;text-decoration: underline;">ACCOUNTS<hr /></th>
  </tr>
  <tr>
    <td align="right">Default Login Age</td>
    <td><input style="width:10%;" type="text" name="scmsAccount_LoginAge" id="scmsAccount_LoginAge" value="<?php echo intval(scmsAccount_LoginAge); ?>" /><img style="cursor:help" src="/images/info.png" alt="Maximum number of successful logins before user's password is considered 'expired'" title="Maximum number of successful logins before user's password is considered 'expired'"/></td>
  </tr>
  <tr>
    <td align="right">Minimum Password Length</td>
    <td><input style="width:10%;" type="text" name="scmsAccount_MinimumPasswordLength" id="scmsAccount_MinimumPasswordLength" value="<?php echo intval(scmsAccount_MinimumPasswordLength); ?>" /><img style="cursor:help" src="/images/info.png" alt="Default Minimum Password Length: Recommended are strong passwords of 8 characters minimum" title="Default Minimum Password Length: Recommended are strong passwords of 8 characters minimum"/></td>
  </tr>
  <tr>
    <td align="right">Unlock Key Hashing Algo</td>
    <td>
      <select name="scmsAccount_LockingHashAlgo" id="scmsAccount_LockingHashAlgo">
<?php
        foreach(hash_algos() as $algo) {
?>
        <option <?php if(in_array($algo,$InsecureAlgos)){ echo 'disabled="disabled"'; } else if(scmsAccount_LockingHashAlgo == $algo) { echo 'selected="selected"'; }?> value="<?php echo $algo; ?>"><?php echo $algo; ?></option>
<?php
        }
?>
      </select><img style="cursor:help" src="/images/info.png" alt="Default Hashing Algorythm for generating the account unlock key" title="Default Hashing Algorythm for generating the account unlock key"/>
    </td>
  </tr>
  <tr>
    <td align="right">Failed Login Threshold</td>
    <td><input style="width:10%;" type="text" name="scmsAccount_LockingThreshold" id="scmsAccount_LockingThreshold" value="<?php echo intval(scmsAccount_LockingThreshold); ?>" /><img style="cursor:help" src="/images/info.png" alt="Maximum number of unsuccessful login attempts allowed" title="Maximum number of unsuccessful login attempts allowed"/></td>
  </tr>
  <tr>
    <td align="right">Lockout Duration</td>
    <td><input style="width:10%;" type="text" name="scmsAccount_LockDuration" id="scmsAccount_LockDuration" value="<?php echo intval(scmsAccount_LockDuration); ?>" /><img style="cursor:help" src="/images/info.png" alt="Maximum time for locking a user's account in seconds" title="Maximum time for locking a user's account in seconds"/></td>
  </tr>
  <tr>
    <td align="right">Unlock key lifetime</td>
    <td><input style="width:10%;" type="text" name="scmsAccount_ReminderDuration" id="scmsAccount_ReminderDuration" value="<?php echo intval(scmsAccount_ReminderDuration); ?>" /><img style="cursor:help" src="/images/info.png" alt="" title="Maximum time allowed between a request for a password change and the actual change {in seconds}"/></td>
  </tr>
  <tr>
    <th colspan="2" style="font-weight:bolder;">SESSIONS<hr /></th>
  </tr>
  <tr>
    <td align="right">Session Cookie Name</td>
    <td><input type="text" name="scmsSession_Name" id="scmsSession_Name" value="<?php echo htmlentities(strtoupper(scmsSession_Name),ENT_QUOTES); ?>" /><img style="cursor:help" src="/images/info.png" alt="Name of PHP Session Cookie" title="Name of PHP Session Cookie"/></td>
  </tr>
  <tr>
    <td align="right">Encrypt Session DATA</td>
    <td>
      <select name="scmsSession_DataEncrypt" id="scmsSession_DataEncrypt">
        <option <?php if(scmsSession_DataEncrypt) {echo 'selected="selected"';}?> value="1">Yes</option>
        <option <?php if(!scmsSession_DataEncrypt) {echo 'selected="selected"';}?> value="0">No</option>
      </select><img style="cursor:help" src="/images/info.png" alt="Enable Session Data Encryption: Encryption on write &amp; Decryption on read" title="Enable Session Data Encryption: Encryption on write &amp; Decryption on read"/>
    </td>
  </tr>
  <tr>
    <td align="right">Session Idle Timeout</td>
    <td><input style="width:10%;" type="text" name="scmsSession_IdleTimeout" id="scmsSession_IdleTimeout" value="<?php echo intval(scmsSession_IdleTimeout); ?>" /><img style="cursor:help" src="/images/info.png" alt="Default user session idle timeout: maximum time allowed between session requests {in seconds}" title="Default user session idle timeout: maximum time allowed between session requests {in seconds}"/></td>
  </tr>
  <tr>
    <td align="right">Session IP Check</td>
    <td>
      <select name="scmsSession_IPCheck" id="scmsSession_IPCheck">
        <option <?php if(scmsSession_IPCheck) {echo 'selected="selected"';}?> value="1">Yes</option>
        <option <?php if(!scmsSession_IPCheck) {echo 'selected="selected"';}?> value="0">No</option>
      </select><img style="cursor:help" src="/images/info.png" alt="Tie a user's session to his IP address: Prevents Session Hijacking but will not work if user's request are going through multiple proxies, so default is false" title="Tie a user's session to his IP address: Prevents Session Hijacking but will not work if user's request are going through multiple proxies, so default is false"/>
    </td>
  </tr>
  <tr>
    <th colspan="2" style="font-weight: bolder;">DATABASE<hr /></th>
  </tr>
  <tr>
    <td align="right">Engine</td>
    <td>
      <select name="scms_dbEngine" id="scms_dbEngine">
        <option value="mysql">MySql</option>
      </select><img style="cursor:help" src="/images/info.png" alt="Database Engine beign used" title="Database Engine beign used"/>
    </td>
  </tr>
  <tr>
    <td align="right">Name</td>
    <td><input type="text" name="scms_dbname" id="scms_dbname" value="<?php echo htmlentities(scms_dbname,ENT_QUOTES); ?>" /><img style="cursor:help" src="/images/info.png" alt="Database Name" title="Database Name"/></td>
  </tr>
  <tr>
    <td align="right">Host</td>
    <td><input type="text" name="scms_dbhost" id="scms_dbhost" value="<?php echo htmlentities(scms_dbhost,ENT_QUOTES); ?>" /><img style="cursor:help" src="/images/info.png" alt="Database Host" title="Database Host"/></td>
  </tr>
  <tr>
    <td align="right">Port</td>
    <td><input style="width:10%;" type="text"  id="scms_dbport" name="scms_dbport" value="<?php echo htmlentities(scms_dbport,ENT_QUOTES); ?>" /><img style="cursor:help" src="/images/info.png" alt="Database TCP Port" title="Database TCP Port"/></td>
  </tr>
  <tr>
    <td align="right">Username</td>
    <td><input type="text" name="scms_dbuser" id="scms_dbuser" value="<?php echo htmlentities(scms_dbuser,ENT_QUOTES); ?>" /><img style="cursor:help" src="/images/info.png" alt="Database Username" title="Database Username"/></td>
  </tr>
  <tr>
    <td align="right">Password</td>
    <td><input type="text" name="scms_dbpasswd" id="scms_dbpasswd" value="<?php echo htmlentities(scms_dbpasswd,ENT_QUOTES); ?>" /><img style="cursor:help" src="/images/info.png" alt="Database Password" title="Database Password"/></td>
  </tr>
  <tr>
    <th colspan="2" style="font-weight:bolder;">PHP SETTINGS&nbsp;&nbsp;&nbsp;<span style="font-size:x-small;text-align:right;">Please refer to the <a href="http://php.net/manual/en/ini.list.php">documentation</a></span><hr /></th>
  </tr>
  <tr>
    <td align="right">default_charset</td>
    <td>
      <select name="default_charset" id="default_charset">
        <option <?php if(ini_get('default_charset')=='utf-8'){?> selected="selected" <?php } ?> value="utf-8">utf-8</option>
        <option <?php if(ini_get('default_charset')=='iso-8859-1'){?> selected="selected" <?php } ?> value="iso-8859-1">iso-8859-1</option>
      </select>
    </td>
  </tr>
  <tr>
    <td align="right">max_execution_time</td>
    <td><input style="width:10%;" type="text" name="max_execution_time" id="max_execution_time" value="<?php echo ini_get('max_execution_time'); ?>" /></td>
  </tr>
  <tr>
    <td align="right">memory_limit</td>
    <td>
      <input style="width:10%;" type="text" name="memory_limit" id="memory_limit" value="<?php echo ini_get('memory_limit'); ?>" />
      <?php if(function_exists('memory_get_usage') && function_exists('memory_get_peak_usage')) {?>
        <span>Current: <?php echo ((memory_get_usage(true)/1024)/1024).'mb'; ?></span>
        <span>Peak: <?php echo ((memory_get_peak_usage(true)/1024)/1024).'mb'; ?></span>
      <?php }?>
    </td>
  </tr>
  <tr>
    <td align="right">date.timezone</td>
    <td><input style="width:30%;" type="text" name="date_timezone" id="date_timezone" value="<?php echo ini_get('date.timezone'); ?>" /></td>
  </tr>  
  <tr>
    <td align="right">session.gc_probability</td>
    <td><input style="width:10%;" type="text" name="session_gc_probability" id="session_gc_probability" value="<?php echo ini_get('session.gc_probability'); ?>" /></td>
  </tr>
  <tr>
    <td align="right">session.gc_divisor</td>
    <td><input style="width:10%;" type="text" name="session_gc_divisor" id="session_gc_divisor" value="<?php echo ini_get('session.gc_divisor'); ?>" /></td>
  </tr>  
  <tr>
    <td align="right">mysql_connect_timeout</td>
    <td><input style="width:10%;" type="text" id="mysql_connect_timeout" name="mysql_connect_timeout" value="<?php echo intval(ini_get('mysql.connect_timeout')); ?>" /></td>
  </tr>
  <tr>
    <td align="right">zlib.output_compression</td>
    <td>
      <select name="zlib.output_compression" id="zlib.output_compression">
        <option selected="selected" value="1">On</option>
      </select>
    </td>
  </tr>
  <tr>
    <td align="right">zlib.output_compression_level</td>
    <td>
      <select name="zlib.output_compression_level" id="zlib.output_compression_level">
        <option <?php if(ini_get('zlib.output_compression_level')==-1) {?> selected="selected" <?php } ?> value="-1">-1</option>
        <option <?php if(ini_get('zlib.output_compression_level')==1) {?> selected="selected" <?php } ?> value="1">1</option>
        <option <?php if(ini_get('zlib.output_compression_level')==2) {?> selected="selected" <?php } ?> value="2">2</option>
        <option <?php if(ini_get('zlib.output_compression_level')==3) {?> selected="selected" <?php } ?> value="3">3</option>
        <option <?php if(ini_get('zlib.output_compression_level')==4) {?> selected="selected" <?php } ?> value="4">4</option>
        <option <?php if(ini_get('zlib.output_compression_level')==5) {?> selected="selected" <?php } ?> value="5">5</option>
        <option <?php if(ini_get('zlib.output_compression_level')==6) {?> selected="selected" <?php } ?> value="6">6</option>
        <option <?php if(ini_get('zlib.output_compression_level')==7) {?> selected="selected" <?php } ?> value="7">7</option>
        <option <?php if(ini_get('zlib.output_compression_level')==8) {?> selected="selected" <?php } ?> value="8">8</option>
        <option <?php if(ini_get('zlib.output_compression_level')==9) {?> selected="selected" <?php } ?> value="9">9</option>
      </select>
    </td>
  </tr>
  <tr>
      <th colspan="2"><hr /></th>
  </tr>
</table>
<div>
  <input style="width:75%;" type="submit" name="save" value="Save SCMS Configuration" />
</div>
</form>