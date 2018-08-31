<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function () {
	
    var colorArray = ["gray","pink","yellow","blue","red","green","#fff"];
       var mychart = new Highcharts.Chart({ 
    	   chart: {
               renderTo: 'container',
               type: 'column',
               options3d: {
                   enabled: true,
                   alpha: 15,
                   beta: 15,
                   depth: 50,
                   viewDistance: 15
               }
           },
           title: {
               text: 'World\'s largest cities per 2014'
           },
           subtitle: {
               text: 'Source: <a href="http://en.wikipedia.org/wiki/List_of_cities_proper_by_population">Wikipedia</a>'
           },
           xAxis: {
               type: 'category',
               labels: {
                   rotation: 10,
                   style: {
                       fontSize: '13px',
                       fontFamily: 'Verdana, sans-serif'
                   }
               }
           },
           yAxis: {
               min: 0,
               title: {
                   text: '部门人数统计 (人)'
               }
           },
           legend: {
               enabled: false
           },
           tooltip: {
               pointFormat: '总人数: <b>{point.y} 人</b>'
           },
           series: [{
               // name: 'Population',
                data: [],
                dataLabels: {
                    enabled: true,
                    rotation: 0,
                    color: '#FFFFFF',
                    align: 'center',
                   // format: '{point.y:.1f}', // one decimal
                    y: 10, // 10 pixels down from the top
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            }]
     
	  });
    
       $.getJSON("chartAction_getChartDataColumn.action", function (e) { 
       	    for(key in e)
       	    	{
       	    	    e[key].color=colorArray[key];
       	    	}
          
      	mychart.series[0].setData(e);
      }); 

});
</script>
</head>
<body>

  <div id="container" style="height: 400px"></div>

</body>
</html>