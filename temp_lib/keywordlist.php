
<span class="ct-title" > Success Group SMS List </span>
<br />


<table class="table table-striped table-bordered mediaTable">
								<thead>
									<tr>
										<th class="optional">Key ID</th>
										<th class="essential persist">Keyword </th>
										<th class="optional">Description </th>
										<th class="optional">Status</th>
		
									</tr>
								</thead>
								<tbody>
                                <?php 
								include 'include/dbconnector.php';
								$qct = mysql_query("Select * from keyword order by id ASC");
								while($dd = mysql_fetch_array($qct))
								{
									echo '<tr>
										<td>'.$dd['id'].'</td>
										<td>';
										
											echo $dd['keyword'];
										
										echo '</td>
										<td>'.$dd['discrip'].'</td>
										
										<td>';
										$status = $dd['status'];
										
										if($status=='1')
										{
											echo 'Active';
										}
										else
										{
											echo 'Inactive';
										}
										
										echo '</td>
									</tr>';
									
								}
								
								?>
								</tbody>
							</table>