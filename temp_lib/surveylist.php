
<span class="ct-title" > Survey Question </span>
<br />


<table class="table table-striped table-bordered mediaTable">
								<thead>
									<tr>
										<th class="optional">Question ID</th>
										<th class="essential persist">Destination </th>
										
										<th class="optional">Time</th>
										<th class="essential">Question</th>
                                        <th class="essential">Total Sent</th>
									</tr>
								</thead>
								<tbody>
                                <?php 
								include 'include/dbconnector.php';
								$qct = mysql_query("Select * from survey order by id DESC");
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
										
										<td>'.date('F d, Y', $tt).'</td>
										<td>'.$dd['question'].'</td>
										<td>'.$dd['totalsent'].'</td>
									</tr>';
									
								}
								
								?>
								</tbody>
							</table>