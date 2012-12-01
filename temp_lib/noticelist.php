
<span class="ct-title" > Success Group SMS List </span>
<br />


<table class="table table-striped table-bordered mediaTable">
								<thead>
									<tr>
										<th class="optional">SMS ID</th>
										<th class="essential persist">Destination </th>
										<th class="optional">Sender </th>
										<th class="optional">Time</th>
										<th class="essential">Message</th>
									</tr>
								</thead>
								<tbody>
                                <?php 
								include 'include/dbconnector.php';
								$qct = mysql_query("Select * from group_sms order by id DESC");
								while($dd = mysql_fetch_array($qct))
								{
									echo '<tr>
										<td>'.$dd['id'].'</td>
										<td>';
										
										$groupid = $dd['group_id'];
										$ck = mysql_query("Select * from user_type where id='$groupid' ");
										while($cd = mysql_fetch_array($ck))
										{
											echo $cd['type'];
										}
										$tt = $dd['time'];
										
										echo '</td>
										<td>'.$dd['sender'].'</td>
										<td>'.date('F d, Y', $tt).'</td>
										<td>'.$dd['message'].'</td>
									</tr>';
									
								}
								
								?>
								</tbody>
							</table>