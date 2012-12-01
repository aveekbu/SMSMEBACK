<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left">
    
    <?php 
	if($_GET['rf']=='add')
	{
		include 'temp_lib/noticeadd.php';
	}
	
	if($_GET['rf']=='list')
	{
		include 'temp_lib/noticelist.php';
	}
	
	
	if($_GET['rf']=='bulksms')
	{
		include 'temp_lib/bulksms.php';
	}
	
	if($_GET['rf']=='pendingbulksmslist')
	{
		include 'temp_lib/pendingbulksmslist.php';
	}
	
	
	
	?>
    
    </td>
  </tr>
</table>
