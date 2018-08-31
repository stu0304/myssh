<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'chart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    
   <script type="text/javascript" src="<%=basePath%>heightcharts/jquery-1.8.3.min.js"></script>
   	<link rel="stylesheet" type="text/css" href="<%=basePath%>heightcharts/demo.css">
   	
   	<script src="<%=basePath%>heightcharts/highcharts.js"></script>
	<script src="<%=basePath%>heightcharts/highcharts-3d.js"></script>
    <script type="text/javascript">
$(function () {
	var xdata = [];
    var color = ["gray","pink","yellow","blue","red","green","#fff"];
    var chart = new Highcharts.Chart({
        chart: {
            renderTo: 'container',
            type: 'column',
            margin: 75,
            options3d: {
                enabled: true,
                alpha: 15,
                beta: 15,
                depth: 50,
                viewDistance: 15
            }
        },
        title: {
            text: '人员出勤率统计'
        },
        xAxis: {
            categories: xdata, crosshair: true
        },
        plotOptions: {
            column: {
                depth: 50,
                pointPadding: 0.2,
                borderWidth: 0
            }
        },  
        yAxis: {
            min: 0,
            title: {
                text: '人员出勤率统计 (%)'
            }
        },  
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: '出勤率: <b>{point.y} %</b>'
        },
        series:[{
            data: []

        }]
    });

    function showValues() {
        $('#R0-value').html(chart.options.chart.options3d.alpha);
        $('#R1-value').html(chart.options.chart.options3d.beta);
    }

    // Activate the sliders
    $('#R0').on('change', function () {
        chart.options.chart.options3d.alpha = this.value;
        showValues();
        chart.redraw(false);
    });
    $('#R1').on('change', function () {
        chart.options.chart.options3d.beta = this.value;
        showValues();
        chart.redraw(false);
    });

    showValues();
    
            $.getJSON("chartAction_getLeaveColumn.action", function (e) { 
            	
            	 for(var key in e){
            	//	alert(e[key].deptName);
            		 xdata.push(e[key].deptName);
            		 e[key].color=color[key];
            	 }
            	chart.series[0].setData(e);
            }); 
});
		</script>

  </head>
  
  <body>
  <div id="container" style="height: 400px"></div>
  </body>
</html>
