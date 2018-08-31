<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>   <!--     tree_data1.json-->
<body>
	<div class="easyui-panel" style="padding:5px">
		<ul id="mytree" class="easyui-tree" data-options="
		url:'treeAction_loadTreeJSON.action',
		method:'get',
		animate:true,
		lines:true"></ul>
	    
	</div>


<script type="text/javascript"> 
     $('#mytree').tree({
	onClick: function(node){
		
		addTab(node.text,"<%=basePath%>"+node.url);
	}
	});
     </script>
</body>
</html>