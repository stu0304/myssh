<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>



<script type="text/javascript">
function showMyMsg(mytitle,mymsg)
{
	$.messager.show({
		title:mytitle,
		msg:mymsg,
		timeout:1000,
		showType:'fade',
		style:{
			right:'',
			bottom:''
		}
	});
	
}
</script>    
  <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/easyui/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/easyui/demo.css">
	<script type="text/javascript" src="<%=basePath%>resource/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/easyui/easyui-lang-zh_CN.js"></script>
	
   	<link rel="stylesheet" type="text/css" href="<%=basePath%>heightcharts/demo.css">
   	
   	<script src="<%=basePath%>heightcharts/highcharts.js"></script>
	<script src="<%=basePath%>heightcharts/highcharts-3d.js"></script>
	
	<style type="text/css">
  body{
      padding:0px;
  }
</style>
