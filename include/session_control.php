<?php
include "include/session.php";
include "include/dbconnector.php";
$data=mysql_query("select* from user where phonenumber='$_SESSION[userid]' or nationalid='$_SESSION[userid]' ");
			while ($call=mysql_fetch_array($data))
			{
				$uid="".$call['id'].""; 
				$user_id="".$call['user_id'].""; 
				$name = $call['name'];
				$phonenumber="".$call['phonenumber']."";
				$email="".$call['email']."";
				$natinalid="".$call['nationalid']."";
				$area="".$call['area']."";
				$deg="".$call['deg']."";
				
				
				}
				
		
// Company Data 

	//Session_Check 
		if($uid=='')
		{
			echo '<meta http-equiv="refresh" content="0; url=login.php"> ';
		}
		
?>