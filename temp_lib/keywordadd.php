<table width="500" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td class="registration-form"><form name="form1" method="post" action="?rf=add&amp;prf=send">
      <table width="100%" border="0" cellspacing="2" cellpadding="5">
        <tr>
          <td align="left" class="ct-title"><strong>:: Add Keyword </strong>
          
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
				  $keyword= $_POST['keyword'];
				  $description = $_POST['description'];
				  $sender = $name; 
				  
					$keyd = mysql_query("Insert INTO keyword (keyword, discrip) values ('$keyword', '$description') ");
				if($keyd)
				{
					echo 'Successfully Added';
				}
				else
				{
					echo 'keyword already exist';
					echo mysql_error();
				}
			  }
			  ?>
              
              
              
              
              </td>
            </tr>
            <tr>
              <td align="right"><strong>Keyword</strong></td>
              <td align="left"><label for="nationalid"></label>
                <label for="group"></label>
                <label for="keyword"></label>
                <input type="text" name="keyword" id="keyword" /></td>
              </tr>
            <tr>
              <td align="right" valign="top"><strong>Description </strong></td>
              <td align="left"><label for="password"></label>
                <textarea name="description" required id="password"></textarea></td>
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
