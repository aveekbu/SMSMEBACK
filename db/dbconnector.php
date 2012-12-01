<?php 
//edit it with your "hosting address as loccalhost", "username as root", password as "".
$host="localhost";
$username="root";
$password="";


//connect mysql & don't edit it.

$db_name="smsMeBack";

mysql_connect("$host","$username","$password") or die ("Connection Error, there might be some error occoured, Call for help: +8801714369600");
mysql_select_db("$db_name") or die ("Database Not Found");


?>