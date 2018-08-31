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
<title>Insert title here</title>

   <script type="text/javascript" src="<%=basePath%>heightcharts/jquery-1.8.3.min.js"></script>
   	<link rel="stylesheet" type="text/css" href="<%=basePath%>heightcharts/demo.css">
   	
   	<script src="<%=basePath%>heightcharts/highcharts.js"></script>

<script type="text/javascript">  
$(function () {
	
    chart1 = new Highcharts.Chart({
    chart: {
        renderTo: 'container', // 渲染
        type: 'pie',   // 饼图
        options3d: {
            enabled: true,
            alpha: 45,
            beta: 0
        }
    },
    title: {
        text: '浏览器市场 shares at a specific website, 2014'
    },
    tooltip: {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    plotOptions: {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            depth: 35,
            dataLabels: {
                enabled: true,
                format: '{point.name}'
            }
        }
    },
    series: [{
        "type": "pie",
        "name": " 浏览器 share",
        data: []   // 数组
    }]
});

        
        $.getJSON("chartAction_getChartData.action", function (dict) {                
            chart1.series[0].setData(dict);
        }); 

});



</script>
</head>
<body>
<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
</body>
</html>