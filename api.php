
<?

include 'include/dbconnector.php' ; 
$q=$_GET[q];
$r1=mysql_result(mysql_query("select count(*) as num  from  survey_ans where surveyid='$q' and ans='YES' "),0);
$r2=mysql_result(mysql_query("select count(*) as num1 from survey_ans where surveyid='$q' and ans='NO' "),0);
$r3=mysql_result(mysql_query("select totalsent from survey where id='$q'  "),0);

//print_r($r3);die;
$return_arr = array();



	
    $row_array['ans1'] = $r1;
    $row_array['ans2'] = $r2;
    $row_array['total'] =$r3;
//print_r($row_array);die();
//    array_push($return_arr,$row_array);

echo json_encode($row_array);

