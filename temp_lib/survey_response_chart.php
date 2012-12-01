<?php
	
	include "../libchart/classes/libchart.php";


	$chart = new VerticalBarChart();
	$surveyid = $_GET['bc'];
	include '../include/dbconnector.php';
	$year = date('Y');
	//ROw Counting 
	
	$janck = mysql_query("Select * from survey_ans where surveyid='$surveyid' ");
	
		
	
	// Total Price Calculation for Bar
	
	
	$year = date('Y');
	
	$yes = mysql_query("Select * from survey_ans where surveyid='$surveyid' and ans='Yes' ");
	$no = mysql_query("Select * from survey_ans where surveyid='$surveyid' and ans='no' ");
	
	
	// total  
	
	$yesvalue = mysql_num_rows($yes);
	$novalue = mysql_num_rows($no);
	
	
	// End
	
	
	$serie1 = new XYDataSet();
	
	$serie1->addPoint(new Point("Question ID : ".$surveyid." ,".mysql_num_rows($janck), "".$yesvalue.""));
		
	
	$serie2 = new XYDataSet();
	$serie2->addPoint(new Point("Question ID : ".$surveyid." ,".mysql_num_rows($janck), "".$novalue.""));	
	
	$dataSet = new XYSeriesDataSet();
	$dataSet->addSerie("Yes", $serie1);
	$dataSet->addSerie("No", $serie2);
	$chart->setDataSet($dataSet);
	$chart->getPlot()->setGraphCaptionRatio(0.65);

	$chart->setTitle("Survey Result");
	$chart->render("../libchart/generated/demo7.png");
	
?>

	<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td align="center" style="padding:20px;">
    
    
    <img alt="Vertical bars chart" src="libchart/generated/demo7.png" style="border: 1px solid gray;"/>
    
    <div style="color:#666; font-size:10px;"><em><strong>Note: Bar has been set with price</strong></em> </div>
    
    </td>
  </tr>
</table>


