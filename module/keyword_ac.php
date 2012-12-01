<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left">
    
    <?php 
	if($_GET['rf']=='add')
	{
		include 'temp_lib/keywordadd.php';
	}
	
	if($_GET['rf']=='list')
	{
		include 'temp_lib/keywordlist.php';
	}
	
	
	if($_GET['rf']=='result')
	{
		include 'temp_lib/surveyanslist.php';
	}
	
	if($_GET['rf']=='pendingbulksmslist')
	{
		include 'temp_lib/pendingbulksmslist.php';
	}
	
	
	
	?>
    
    </td>
  </tr>
</table>
