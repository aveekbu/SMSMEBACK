
<?php 
			  
			  
			  if($_GET['prf']=='send')
			  {
				  include 'include/dbconnector.php';
				  
				  $qc = mysql_query("Select * from bulk_sms_csvmsg where status='1' and user='$name' ");
				  while($qq = mysql_fetch_array($qc))
				  {
				 $phone = ''.$qq['number'].'';
				 $message = urlencode($qq['message']);
				 $sender = $qq['user'];
				 $time = time();
				 $api_status=file_get_contents("http://fahimit.com/smsapi?user=smsmeb&pass=123321&phone=$phone&senderid=$sender&message=$message");
				
				 
				 $result = substr($api_status, 0, 4);
					 if($result=='1701')
					{
						 mysql_query("Update bulk_sms_csvmsg SET status='0'");
						 mysql_query("Insert INTO sms (time, number, sender, message) values ('$time', '$phone', '$sender', '$message') ");
					}
					else
					{
						echo '';
					}
					 
				
				 }
				
			  }
			  ?>
              


<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td align="left" class="ct-title"> 
    
    Pending Bulk SMS
    </td>
  </tr>
  <tr>
    <td align="right"><form id="form1" name="form1" method="post" action="?rf=pendingbulksmslist&amp;prf=send">
      <input type="submit" name="Send" id="Send" value="Send Now" class="btn btn-inverse" />
    </form></td>
  </tr>
  <tr>
    <td align="left">
<table class="table table-striped table-bordered mediaTable">
								<thead>
									<tr>
										<th class="optional"> ID</th>
										<th class="essential persist">Number </th>
										<th class="optional">User </th>
										<th class="optional">Status</th>
										<th class="essential">Message</th>
									</tr>
								</thead>
								<tbody>
                                <?php 
								$sender = $name; 
								include 'include/dbconnector.php';
								$qct = mysql_query("Select * from bulk_sms_csvmsg where status='1' and user='$sender' order by id ASC");
								while($dd = mysql_fetch_array($qct))
								{
									echo '<tr>
										<td>'.$dd['id'].'</td>
										<td>';
										
										echo $dd['number'];
										
										echo '</td>
										<td>'.$dd['user'].'</td>
										<td>'.$dd['status'].'</td>
										<td>'.$dd['message'].'</td>
									</tr>';
									
								}
								
								?>
								</tbody>
							</table></td>
  </tr>
</table>
