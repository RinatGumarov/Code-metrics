<?php
//phpMyFireWall
//author davestj@gmail.com
error_reporting(E_ERROR);
// system port generator for phpMyFirewall
// this nifty little script will get your current open
// ports, tcp and udp and create a db files with them,
// making it easier for you to keep track of the ports
// you need open or dont need open.
// you can add or delete ports from the ports/*.db files
// be sure you know what you are doing, you could lock yourself
// out if you remove the wrong port, i.e. 22 80 etc....
include('./etc/config.php');
//get ready for command line arguments
$args = trim(next($HTTP_SERVER_VARS["argv"]));
if($args == ''){
echo "--------------------------------------------------
	 $argv[0] usage:\n
	 				$argv[0] --tcp   (generate list of active system tcp ports)\n
					$argv[0] --udp   (generate list of active udp ports)\n\n";
}
if($args == '--tcp'){
$system_tcp_ports = shell_exec("netstat -nl | grep tcp | cut -d: -f2 | awk '{print $1}'");
$tcp_ports = trim($system_tcp_ports);
$tcp_ports2 = str_replace("\n"," ",$tcp_ports);
echo "$tcp_ports2";
//write out system tcp in port db file
$tcpindb = fopen("$TCPINGRESSDB", 'w');
fwrite($tcpindb,$tcp_ports2);
fclose($tcpindb);
//write out system tcp out port db file
$tcpoutdb = fopen("$TCPEGRESSDB", 'w');
fwrite($tcpoutdb,$tcp_ports2);
fclose($tcpoutdb);
}
if($args == '--udp'){
$system_udp_ports = shell_exec("netstat -nl | grep udp | cut -d: -f2 | awk '{print $1}'");
$udp_ports = trim($system_udp_ports);
$udp_ports2 = str_replace("\n"," ",$udp_ports);
echo "$udp_ports2";
//write out system udp in port db file
$udpindb = fopen("$UDPINGRESSDB", 'w');
fwrite($udpindb,$udp_ports2);
fclose($udpindb);
//write out system udp out port db file
$udpoutdb = fopen("$UDPEGRESSDB", 'w');
fwrite($udpoutdb,$udp_ports2);
fclose($udpoutdb);
}
class MyClass
{
  public $prop1 = "I'm a class property!";

  public function __construct()
  {
      echo 'The class "', __CLASS__, '" was initiated!<br />';
  }

  public function __destruct()
  {
      echo 'The class "', __CLASS__, '" was destroyed.<br />';
  }

  public function setProperty($newval)
  {
      $this->prop1 = $newval;
  }

  public function getProperty()
  {
      return $this->prop1 . "<br />";
  }
}

// Create a new object
$obj = new MyClass;

// Get the value of $prop1
echo $obj->getProperty();

// Destroy the object
unset($obj);

// Output a message at the end of the file
echo "End of file.<br />";
?>
