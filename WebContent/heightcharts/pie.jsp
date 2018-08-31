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
    
     <script type="text/javascript" src="<%=basePath%>js/char/jquery-1.8.2.min.js"></script>
   	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/char/demo.css">
   	
   	<script src="<%=basePath%>js/char/highcharts.js"></script>
	<script src="<%=basePath%>js/char/highcharts-3d.js"></script>
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
    
   //  $.ajaxSettings.async = false;
            var data1 = [];
            
            $.getJSON("ChartServlet?para=pie", function (dict) {                
                for (var key in dict) {
                    if (dict.hasOwnProperty(key)) {
                        data1.push([key, dict[key]]);
                    }
                };
                chart1.series[0].setData(data1);
            }); 
    
});
   
    


		</script>

  </head>
  
  <body>
  <div id="container" style="height: 400px"></div>
  </body>
</html>
