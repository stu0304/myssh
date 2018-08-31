<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
          <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <script type="text/javascript" src="<%=basePath%>heightcharts/jquery-1.8.3.min.js"></script>
   	<link rel="stylesheet" type="text/css" href="<%=basePath%>heightcharts/demo.css">
   	<script src="<%=basePath%>heightcharts/highcharts.js"></script>
<title>Insert title here</title>
	<script type="text/javascript">
$(function () {
	
	var deptChart = new Highcharts.Chart({
        chart: {
        	renderTo: 'container', 
            type: 'pie'
        },
        title: {
            text: 'Browser market shares January, 2015 to May, 2015'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            "type": "pie",
            "name": " 部门人数统计",
            data: []   // 数组
        }]
    });
    
    
    $.getJSON("chartAction_getChartData.action", function (e) {                
    	deptChart.series[0].setData(e);
    }); 
});
		</script>
</head>
<body>
<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
</body>
</html>