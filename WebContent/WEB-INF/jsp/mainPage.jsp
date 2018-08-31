<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function addTab(title, url){
	if ($('#tt').tabs('exists', title)){
		$('#tt').tabs('select', title);
	} else {
		var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
		$('#tt').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
}
</script>
</head>
<body>

  <div class="easyui-layout" style="width:100%;height:100%;">
        <div data-options="region:'north'" style="height:50px"></div>
        <div data-options="region:'south',split:true" style="height:50px;">@天津市大学软件学院</div>
        <div data-options="region:'west',split:true,href:'jumpAction_goToTreePage.action'" title="West" style="width:200px;"></div>
       <div data-options="region:'center',title:'center title'" style="padding:5px;">
       
 
       
       
       <div id="tt" class="easyui-tabs" style="width:100%;height:100%;">
				
	   </div>

       </div> 
    </div>
    
    
    
    
</body>
</html>