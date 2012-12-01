<?php 
//edit it with your "hosting address as loccalhost", "username as root", password as "".
$host="localhost";
$username="root";
$password="";


//connect mysql & don't edit it.

$db_name="smsMeBack";

mysql_connect("$host","$username","$password") or die ("Connection Error, there might be some error occoured, Call for help: +8801714369600");
mysql_select_db("$db_name") or die ("Database Not Found");



function URL_forward( $Page, $Time )
{
print("<script language=\"JavaScript\">");
print("setTimeout(\"startover()\", $Time);");
print("function startover()");
print("{ window.location = '$Page';}");
print("</script>");
}





function Navigation($page,$max_results,$table,$where,$order_by,$asc="DESC",$add_url="")
{
$max_results    = intval($max_results);
$from           = (($page * $max_results) - $max_results);
$total_results  = mysql_result(query("SELECT COUNT(*) as Num FROM $table where $where order by  $order_by $asc"),0);
$total_pages    = ceil($total_results / $max_results);
$page_url       = $_SEVER["PHP_SELF"];



if($page > 1)
         {
         $prev = ($page - 1);
         print("<a href=\"$page_url?page=$prev&$add_url\"> &lt;&lt;Prev </a> ");
         }
         //end Preview
echo "$page of $total_pages &nbsp;";

if($page < $total_pages)
         {
         $next = ($page + 1);
         echo("<a href=\"$page_url?page=$next&$add_url\"> Next>>  </a>");
         }



}





function query($query = "")
{
	global	$query_debug;

	if (empty($query)) { return FALSE; }

	if (!empty($query_debug)) { print "<pre>$query</pre>\n"; }

	$result = mysql_query($query)
		or die(" query failed: "
			."<li>errorno=".mysql_errno()
			."<li>error=".mysql_error()
			."<li>query=".$query
		);
	return $result;
}

//This is mainly used in payment page to check fake account;


  function get_reseller_username($user_id,$us_id="")
          {

          $user_query=query("select *from user where id='$user_id' and created_by='$us_id' ");
          $user_row=mysql_fetch_object($user_query);
          $usersname=$user_row->user;
          if($usersname=="")
          {
           $_SESSION=array();
           session_destroy();
           URL_forward("../index.php",0);
           exit;
          }
          return $usersname;
          }


          function get_username($user_id,$us_id="")
          {

          $user_query=query("select *from user where id='$user_id'");
          $user_row=mysql_fetch_object($user_query);
          $usersname=$user_row->user;
          return $usersname;
          }





//Fuction used in admin panel

     function get_country($user_id)
          {
          $user_query=query("select *from country where id='$user_id'");
          $user_row=mysql_fetch_object($user_query);
          $usersname=$user_row->name;
          return $usersname;
          }

     function get_currency($user_id)
          {
          $user_query=query("select *from country where id='$user_id'");
          $user_row=mysql_fetch_object($user_query);
          $usersname=$user_row->currency;
          return $usersname;
          }



function get_boss_id($user_id)
{
$query=query("select * from user where id='$user_id'");
$row=mysql_fetch_object($query);
$balance=$row->created_by;
return $balance;
}


function my_boss_balance($user_id)
{
$query=query("select * from user where id='$user_id'");
$row=mysql_fetch_object($query);
$boss_balance= get_balance($row->created_by);
return $boss_balance;
}


/*
Function: Check balance of user,reseller, cut balance,check is sufficient balance is there or not
          is everything is ok then $text will return 1

Funciton Used: get_balance  it return user level upon provides user id and
               its also verified level also.uf provided level is not matched then there may be problem balance will return 0


*/

function upper_balance_checker($amount,$mybalance,$level,$user_id,$type)
{
 $amount=abs(floatval($amount));
 $mybalance=abs(floatval($mybalance));
 $my_nb=$mybalance-$amount;
 $q=query("select *from user where id='$user_id'");
 $r=mysql_fetch_object($q);
 $r5=$r->r5;  //admin
 $r4=$r->r4;  //r4
 $r4_b=get_balance($r4,3);
 $r4_nb=$r4_b-$amount;
 $r3=$r->r3;
 $r3_b=get_balance($r3,2);
 $r3_nb=$r3_b-$amount;

 $r2=$r->r2;
 $r2_b=get_balance($r2,1);
 $r2_nb=$r2_b-$amount;





 //For LEvel 1


 if($type=="unlimited")
 {

 if($level==0)
 {

     if($mybalance<$amount)
       {
       $text="Insufficient Balance in Your Account";
       }
     elseif($r4_b<$amount)
      {
      $text="Insufficient Balance in your Boss Account(R4)";
      }

   else
     {
     query("update user set balance='$r4_nb' where id='$r4'");
     query("update user set balance='$my_nb' where id='$user_id'");
     $text=1;

     }

 }

      //  Level 2


else if($level==1)
 {
    if($mybalance<$amount)
      {
      $text="Insufficient Balance in Your Account";
      }
    elseif($r4_b<$amount)
      {
      $text="Insufficient Balance in your R4";
      }

   else
      {
     query("update user set balance='$r4_nb' where id='$r4'");
     query("update user set balance='$my_nb' where id='$user_id'");

      $text=1;
      }

 }

 //Level 3

elseif($level==2)
 {
     if($mybalance<$amount)
       {
       $text="Insufficient Balance in Your Account";
       }
    elseif($r4_b<$amount)
       {
       $text="Insufficient Balance in your R4";
       }
   else
       {

     query("update user set balance='$r4_nb' where id='$r4'");
     query("update user set balance='$my_nb' where id='$user_id'");
     $text=1;
      }

 }

 }
 else if($type="limited")
 {

     if($mybalance<$amount)
       {
       $text="Insufficient Balance in Your Account";
       }
       else
       {
       $text=1;
       query("update user set balance='$my_nb' where id='$user_id'");
       }


 }
 else
 {

      echo"<h1>Invalid User </h1>
      </div>
      </section>
      </div></body></html>"  ;



      exit;
}


 if($text!=1)
 {
    echo"<h1>$text </h1>
      </div>
      </section>
      </div></body></html>"  ;
   exit;
 }

 return $text;




}




function get_balance($user_id,$level)
{
 $q=query("select *from user where id='$user_id' and level='$level'");
 $row=mysql_fetch_object($q);
 return $row->balance;

}








/*function online_user($user_id="")
{


$t=time();
$ipQuery = mysql_query("SELECT * FROM user_online WHERE user_id= '" . $user_id . "' LIMIT 1");

if(mysql_num_rows($ipQuery) == 1)
{
query("UPDATE user_online SET lastactive = " . time() . " WHERE user_id = '" . $user_id . "' LIMIT 1");
}
else
{
  echo mysql_num_rows($ipQuery);

/*
  session_unregister(myadmin);
  session_unregister(us_id);
  session_unregister(level);
  ob_start();
  flush();
  session_destroy();
  URL_forward("index.php",2);
  exit;

}   /*
$time_old=time() - 500;
query("DELETE FROM user_online WHERE lastactive <$time_old");
}

if(basename($_SERVER[PHP_SELF])!="index.php")
{
 online_user($us_id);
}
                */



/*
  This function proivdes values.
*/
function get_db_value($table,$where,$field)
{
  $q=query("select *from $table where $where ");
  $r=mysql_fetch_object($q);
  return $r->$field;

}

date_default_timezone_set('Asia/Dhaka');
$trdate=date("Y-m-d H:i:s",time());
$d=date("d",strtotime($trdate));
$m=date("m",strtotime($trdate));
$Y=date("Y",strtotime($trdate));
$H=date("H",strtotime($trdate));
$i=date("i",strtotime($trdate));
$s=date("s",strtotime($trdate));

$trdate=date("Y-m-d H:i:s",mktime($H,$i,$s,$m,$d,$Y));
$curdate=date("Y-m-d",mktime($m,$d,$Y));

$curyesterday = date('Y-m-d', mktime(0, 0, 0, date("m") , date("d") - 1, date("Y")));
$curweek = date('Y-m-d', mktime(0, 0, 0, date("m") , date("d") - 7, date("Y")));
$curmonth = date('Y-m-d', mktime(0, 0, 0, date("m") , date("d") - 30, date("Y")));







/*High Fy Security*/
$time         = time();
$_GET = array_map('trim', $_GET);
$_POST = array_map('trim', $_POST);
$_REQUEST = array_map('trim', $_REQUEST);
if(get_magic_quotes_gpc()):    $_GET = array_map('stripslashes', $_GET);
    $_POST = array_map('stripslashes', $_POST);
    $_COOKIE = array_map('stripslashes', $_COOKIE);
    $_REQUEST = array_map('stripslashes', $_REQUEST);
endif;
$_GET = array_map('mysql_real_escape_string', $_GET);
$_POST = array_map('mysql_real_escape_string', $_POST);
$_REQUEST = array_map('mysql_real_escape_string', $_REQUEST);
/*High Fy Security*/

if(!isset($_GET[page]) ||$_GET[page]==0){$page=1;}else{$page=$_GET[page];}


/**Coding for balance*/
$query              = query("select *from user where id='$us_id' ");
$reseller_data      = mysql_fetch_object($query);
$balance            = $reseller_data->balance;
$balance2           = $reseller_data->balance2 ;
$actual_balance  = $balance2;
$pending_balance = $balance;    //Available balance
$get_email        =$reseller_data->email;
$us_email=$get_email ;
/***/

$server_admin_id=1;

$s_date=date("Y-m-d");









?>
