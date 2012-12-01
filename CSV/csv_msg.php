<?php
$user=$_POST['user'];
include'dbHCMP.php';
function get_file_extension($file_name) {
	return end(explode('.',$file_name));
}

function errors($error){
	if (!empty($error))
	{
			$i = 0;
			while ($i < count($error)){
			$showError.= '<div class="msg-error">'.$error[$i].'</div>';
			$i ++;}
			return $showError;
	}// close if empty errors
} // close function

if (isset($_POST['upfile'])){
// check feilds are not empty

if(get_file_extension($_FILES["uploaded"]["name"])!= 'csv')
{
$error[] = 'Only CSV files accepted!';
}

if (!$error){

$tot = 0;
$handle = fopen($_FILES["uploaded"]["tmp_name"], "r");
while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
	for ($c=0; $c < 1; $c++) {  
		
		
				mysql_query("INSERT INTO bulk_sms_csvmsg(
				number,
				message,
				user,
				status	
				)VALUES(
					'".mysql_real_escape_string($data[0])."',
					'".mysql_real_escape_string($data[1])."',
					'$user',
					'1'		
				)")or die(mysql_error());
		
	$tot++;}
}
fclose($handle);
//echo "scess";
header('location: bulk_csvmsg_confarm.php');  
}// end no error
}//close if isset upfile

?>