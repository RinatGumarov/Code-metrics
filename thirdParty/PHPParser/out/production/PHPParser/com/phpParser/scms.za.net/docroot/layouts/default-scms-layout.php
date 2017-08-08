<?php if(!defined('SCMS_INSTALL_PATH')) {die('Direct Access Forbbiden!');} ?>
<!DOCTYPE HTML>
<html>
<head>   
<title><?php print($PAGE_TITLE); ?></title>
<?php
$HEADER_EXTRA_ELEMENTS = array(
  '<link href="/css/scms.css" rel="stylesheet" type="text/css" />',
  '<link rel="shortcut icon" href="/images/om.gif" type="image/gif" />',
  '<link rel="icon" href="/images/om.gif" type="image/gif" />',    
  '<script src="/js/Validation.js" type="text/javascript" ></script>',
  '<script src="/js/Alerts.js" type="text/javascript" ></script>'
);

	include_once('include/header.php');
?>
</head>
<body>
<div id="container">
	<div id="header">
	<h1><?php echo $PAGE_TITLE; ?></h1>
	</div>
	<div id="left">
	<?php 
        if($this->Application->Session->GetSessionStatus(session_id())=='Active') {
            include_once('include/menu.php');
        } else {
        ?>
            Free Software<br><a href="http://www.gnu.org/licenses/gpl.html" target="_blank"><img alt="GPLv3" src="/images/gplv3-88x31.png"></a><br />
            <span style="font-size: xx-small;">Written And Maintained By <a href="http://dash.za.net/?ref=scms" target="_blank">Dash Shendy</a></span><br />
            <br />
            Download Scms Version <?php echo ScmsVersion; ?><br>
            <a href="/SCMSv<?php echo ScmsVersion; ?>.tar.gz">G-Zipped</a><small> (<?php echo round(filesize(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'docroot'.DIRECTORY_SEPARATOR.'SCMSv'.ScmsVersion.'.tar.gz')/1024); ?> KB)</small>
            <br />
            <a href="/SCMSv<?php echo ScmsVersion; ?>.zip">Zipped</a><small> (<?php echo round(filesize(SCMS_INSTALL_PATH.DIRECTORY_SEPARATOR.'docroot'.DIRECTORY_SEPARATOR.'SCMSv'.ScmsVersion.'.zip')/1024); ?> KB)</small><br />
            <br />
            <a href="mailto:neuromancer at dash dot za dot net?subject=I wish to contribute to your project" target="_blank">Get Involved In This Project</a><br />
            <a target="_blank" href="https://github.com/d4sh/scms"><img alt="Github" src="/images/github.png"></a><a target="_blank" href="http://freshmeat.net/projects/scms/"><img alt="Freshmeat" src="/images/fm.gif"></a><a target="_blank" href="http://sourceforge.net/projects/s-c-m-s/"><img alt="Sourceforge" src="/images/sf.png"></a>
        <?php
        }
        ?>
	</div>
	<div id="content">
        <?php include_once ($RENDER_VIEW); ?>
	</div>
	<div id="footer">
            Page Executed in <?php echo round(microtime(true)-PageExecutionTimer,6); ?>s
            <br /><a target="_blank" href="http://jigsaw.w3.org/css-validator/validator?uri=<?php echo 'http://'.$_SERVER['SERVER_NAME'].'/css/Style.css'; ?>"><img src="/images/valid_css.png" alt="Valid CSS!"/></a>
            <a target="_blank" href="http://validator.w3.org/check?uri=referer" ><img src="/images/valid_xhtml.gif" alt="Valid XHTML 1.0 Transitional" /></a>
            <br />Powered By <a target="_blank" href="http://scms.za.net/?ref=<?php echo $_SERVER['SERVER_NAME']; ?>" >SCMS</a> &copy; 2011
	</div>
</div>
</body>
</html>


