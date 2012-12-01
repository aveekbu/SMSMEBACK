<table width="500" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td class="registration-form"><form name="form1" method="post" action="?rf=add">
      <table width="100%" border="0" cellspacing="2" cellpadding="5">
        <tr>
          <td align="left" class="ct-title"><strong>:: Sign up Here</strong>
          
          <hr>
          </td>
        </tr>
        <tr>
          <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
            <tr>
              <td align="right">&nbsp;</td>
              <td align="left">
              
              <?php 
			  if($_GET['rf']=='add')
			  {
				  include 'db/dbconnector.php';
				$national_id = $_POST['nationalid'];
				$password = $_POST['password'];
				$conpassword = $_POST['conpassword'];
				$name = $_POST['name'];
				$deg = $_POST['deg'];
				$phone = $_POST['phonenumber'];
				$email = $_POST['email'];
				$user_type = $_POST['user_type'];
				$pass = md5($password);
				   
				if(strlen($password)<4)
				{
					echo 'Password must be more then 4';
				}
				else if (!$password == $conpassword)
				{
					echo 'Confirm password doesn&rsquo;t match';
				}
				else if (!intval($national_id))
				{
				  echo 'National ID must be numeric value';
				}
				else
				{
					$add = mysql_query("Insert into user (nationalid, name, phonenumber, password, user_type, deg, email) values ('$national_id', '$name', '$phone', '$password', '$user_type', '$deg', '$email') ");
				
				if($add)
				{
					echo '<font color="#006600"><strong>You are successfully registered !</strong></font>';
				}
				else
				{
					echo '<font color="#FF0000"><strong>You are already registered with this national id</strong></font>';
				}
				
				}
			  }
			  
			  ?>
              
              
              
              
              </td>
            </tr>
            <tr>
              <td align="right"><strong>National ID</strong></td>
              <td align="left"><label for="nationalid"></label>
                <input type="text" name="nationalid" id="nationalid" required></td>
              </tr>
            <tr>
              <td align="right"><strong>Password</strong></td>
              <td align="left"><label for="password"></label>
                <input type="password" name="password" id="password" required>
                <span style="color:#F00; font-size:10px;"> More then 4</span></td>
              </tr>
            <tr>
              <td align="right"><strong>Confirm Password</strong></td>
              <td align="left"><input type="password" name="conpassword" id="conpassword" required>
                </td>
            </tr>
            <tr>
              <td align="right"><strong>User Type</strong></td>
              <td align="left"><select name="user_type" id="user_type">
                <option value="">Select Desired Group</option>
                <?php
				include 'include/dbconnector.php';
				  $q=mysql_query("select * from user_type");
				  while($r=mysql_fetch_array($q))
				  {
					  echo '<option value="'.$r['id'].'">'.$r['type'].'</option>';
					 
				  }
				  ?>
              </select></td>
            </tr>
            <tr>
              <td align="right"><strong>DEG</strong></td>
              <td align="left"><label for="user_type"></label>
                <label for="deg"></label>
                <input type="text" name="deg" id="deg"></td>
            </tr>
            <tr>
              <td align="right"><strong>Name</strong></td>
              <td align="left"><label for="name"></label>
                <input type="text" name="name" id="name" required></td>
              </tr>
            <tr>
              <td align="right"><strong>Phone No</strong></td>
              <td align="left"><label for="phonenumber"></label>
                <input type="text" name="phonenumber" id="phonenumber" required></td>
              </tr>
            <tr>
              <td align="right"><strong>Email</strong></td>
              <td align="left"><label for="email"></label>
                <input type="email" name="email" id="email" ></td>
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
