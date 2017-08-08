<?php
if(EnableCAPTCHA) {
?>
<tr>
    <td>&nbsp;</td>
    <td><img id="scmsCaptcha" src="/captcha" alt="CAPTCHA" width="150" height="50" /></td>
</tr>
<tr>
    <td align="right">CAPTCHA:</td>
    <td>
      <input style="text-align:center;" type="text" name="captcha" size="10" maxlength="5" />&nbsp;
      <a id="reset" href="<?php echo htmlentities($_SERVER['REQUEST_URI'],ENT_QUOTES); ?>">get new</a>&nbsp;
      <script type="text/javascript">
      var reset = document.getElementById("reset");
      reset.href = "javascript:resetCaptcha();";
      function resetCaptcha() {
        var captcha = document.getElementById("scmsCaptcha");
        captcha.src = "/captcha?id=0&timestamp=" + new Date().getTime();
      }
      </script>
    </td>
</tr>
<?php
}
?>