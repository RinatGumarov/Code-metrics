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

?>
Welcome To SCMS, latest version is <?php echo ScmsVersion; ?><br /><br />
It has the following features:<br /><br />
<ul>
    <li>Licensed Under <a href="http://www.gnu.org/licenses/gpl.html" >GPLv3</a></li>
    <li>Full <a href="http://validator.w3.org/check?uri=referer" >XHTML 1.0 Strict Conformity</a></li>
    <li>Full <a href="http://jigsaw.w3.org/css-validator/validator?uri=<?php echo'http://'.$_SERVER['SERVER_NAME'].'/css/scms.css'; ?>" >CSS 2.1 Conformity</a></li>
    <li>OOP (<a href="http://en.wikipedia.org/wiki/Object-oriented_programming">Object-oriented programming</a>)</li>
    <li>RBAC (<a href="http://en.wikipedia.org/wiki/Role-based_access_control">Role Based Access Control</a>)</li>
    <li>MVC (<a href="http://en.wikipedia.org/wiki/Model%E2%80%93View%E2%80%93Controller">Model-View-Controller</a>) Design Pattern</li>
    <li><a href="http://en.wikipedia.org/wiki/Singleton_pattern">Singleton</a> Design Pattern</li>
    <li>Designed for PHP5+ (Uses PDO)</li>
    <li>Strict IO (Input/Output) Validation</li>
    <li>Supports Mysql or any PDO driver supported database (still untested)</li>
    <li>Custom Session Handling With Idle Session Expiration &amp; Session Identifiers Regeneration</li>
    <li>User Account Locking With Both Automatic &amp; Manual Unlocking Methods</li>
    <li>Login Attempts &amp; Session Event Logging</li>
    <li>Per Action Optional SSL/TLS (Transport Layer Security) Enforcement</li>
    <li>Password Aging/Expiration</li>
    <li>Support for &quot;secure&quot; (When run over SSL/TLS) AND &quot;httponly&quot; Cookies</li>
    <li>Improved CSRF (<a href="http://en.wikipedia.org/wiki/Cross-site_request_forgery">Cross Site Request Forgery</a>) Protection By Using Random Protection Tokens</li>
    <li>Support for all PHP5 Hashing Algorithms as well as MySQL's AES and DES Encryption</li>
    <li>Additional CSRF and Bot Protection using Optional CAPTCHA (<a href="http://en.wikipedia.org/wiki/CAPTCHA">Completely Automated Public Turing test to tell Computers and Humans Apart</a>) Images</li>
    <li>Optional Session Data Storage Encryption</li>
    <li>Optional Session IP Checking (Prevents <a href="http://en.wikipedia.org/wiki/Session_hijacking">Session Hijacking</a>)</li>
</ul>
<br />
<a href="README">README</a> | <a href="REQUIRES">REQUIRES</a><br />
<br />
<a href="login">Test Drive &gt;&gt;&gt;</a>