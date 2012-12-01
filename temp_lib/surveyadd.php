<table width="500" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td class="registration-form"><form name="form1" method="post" action="?rf=add&amp;prf=send">
      <table width="100%" border="0" cellspacing="2" cellpadding="5">
        <tr>
          <td align="left" class="ct-title"><strong>:: Add Survey </strong>
          
          <hr>
          </td>
        </tr>
        <tr>
          <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
            <tr>
              <td align="right">&nbsp;</td>
              <td align="left">
              
              <?php 
			  
			  
			  if($_GET['prf']=='send')
			  {
				  include 'db/dbconnector.php';
				  $group= $_POST['group'];
				  $message = urlencode($_POST['message']);
				  $sender = $name; 
				  $time = time();
				  $qc = mysql_query("Select * from user where user_type='$group' ");
				  
				  $totalsent = mysql_num_rows($qc);
				  
				  while($qq = mysql_fetch_array($qc))
				  {
				 $phone = '88'.$qq['phonenumber'].'';
				 $api_status=file_get_contents("http://fahimit.com/smsapi?user=smsmeb&pass=123321&phone=$phone&senderid=$sender&message=$message");
				 }
				$result = substr($api_status, 0, 4);
				if($result=='1701')
				{
					mysql_query("Insert INTO survey (question, time, group_id, totalsent) values ('$message', '$time', '$group', '$totalsent') ");
					echo 'Success';
				}
				else
				{
					echo 'Failed';
				}
			  }
			  ?>
              
              
              
              
              </td>
            </tr>
            <tr>
              <td align="right"><strong>Desired Group</strong></td>
              <td align="left"><label for="nationalid"></label>
                <label for="group"></label>
                <select name="group" id="group">
                  <option value="">Select Desired Group</option>
                  
                   <?php
				  $q=mysql_query("select * from user_type");
				  while($r=mysql_fetch_array($q))
				  {
					  echo '<option value="'.$r['id'].'">'.$r['type'].'</option>';
					 
				  }
				  ?>
                  
                </select></td>
              </tr>
            <tr>
              <td align="right" valign="top"><strong>Question</strong></td>
              <td align="left"><label for="password"></label>
                <textarea name="message" required id="password"></textarea>
                <span style="color:#F00; font-size:10px;"> Max 160</span></td>
              </tr>
            <tr>
              <td align="right">&nbsp;</td>
              <td align="left"><input type="submit" name="submit" id="submit" class="btn" value="Submit"></td>
              </tr>
            <tr>
              <td align="right">&nbsp;</td>
              <td align="left">&nbsp;</td>
            </tr>
          </table></td>
        </tr>
      </table>
    </form></td>
  </tr>
</table>
