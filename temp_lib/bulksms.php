<table width="500" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td class="registration-form"><table width="100%" border="0" cellspacing="2" cellpadding="5">
        <tr>
          <td align="left" class="ct-title">:: Send Bulk SMS<hr>
          </td>
        </tr>
        <tr>
          <td>
      <form class="form_validation_ttip" action="?rf=bulksms&amp;prf=upd" form enctype="multipart/form-data" method="post">
          
          <table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td align="right">&nbsp;</td>
    <td align="left">
    
    
    
    <?php
	if($_GET['prf']=='upd')
	{
$user=$name;
include'include/dbconnector.php';
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
echo "Successfully Uploaded";
echo '<br>';
echo '<a href="?rf=pendingbulksmslist"> Would You like to send Now ? </a>';
}// end no error
}//close if isset upfile
	}
?>
    
    
    
    
    
    </td>
  </tr>
  <tr>
    <td width="38%" align="right">Select CSV File</td>
    <td width="62%" align="left">
    
    <input type="file" name="uploaded"  />
    <input type="hidden" name="user" value="<?php echo $name; ?>">
    
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="left"><button class="btn btn-inverse" name="upfile" type="submit">Upload File</button></td>
  </tr>
</table>

          
      </form>
    </td>
  </tr>
      </table>
    </td>
  </tr>
  
  </table>