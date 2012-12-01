<script>

function showResult(str)
{
if (str.length==0)
  {
  document.getElementById("roomlist").innerHTML="";
  document.getElementById("roomlist").style.border="0px";
  return;
  }
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("roomlist").innerHTML=xmlhttp.responseText;
    document.getElementById("roomlist").style.border="0px solid #A5ACB2";
    }
  }
xmlhttp.open("GET","temp_lib/survey_response_chart.php?bc="+str,true);
xmlhttp.send();
}

</script>



<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td class="ct-title">Survey Answer</td>
  </tr>
  <tr>
    <td><form id="form1" name="form1" method="post" action="">
      <label for="bc"></label>
      <select name="bc" id="bc" onchange="showResult(this.value)">
        <option value="">Select Desired Question Id</option>
        
        <?php
		include 'include/dbconnector.php';
		$qck = mysql_query("Select * from survey ");
		while($qtc = mysql_fetch_array($qck))
		{
			echo ' <option value="'.$qtc['id'].'">'.$qtc['question'].'</option>';
		}
        
        ?>
      </select>
    </form></td>
  </tr>
  <tr>
    <td align="center">
    
    <div id="roomlist">
    
    </div>
    
    &nbsp;</td>
  </tr>
  <tr>
    <td align="left"><table class="table table-striped table-bordered mediaTable">
      <thead>
        <tr>
          <th class="optional"> ID</th>
          <th class="essential persist">Number </th>
          <th class="essential persist">Question ID </th>
          <th class="optional">Time</th>
          <th class="essential">Answer</th>
        </tr>
      </thead>
      <tbody>
        <?php 
								include 'include/dbconnector.php';
								$qct = mysql_query("Select * from survey_ans order by id DESC");
								while($dd = mysql_fetch_array($qct))
								{
									echo '<tr>
										<td>'.$dd['id'].'</td>
										<td>';
										
										$tt = $dd['time'];
											echo $cd['number'];
										echo '</td>
										
										<td>'.$dd['surveyid'].'</td>
										<td>'.date('F d, Y', $tt).'</td>
										<td>'.$dd['ans'].'</td>
									</tr>';
									
								}
								
								?>
      </tbody>
    </table></td>
  </tr>
</table>
