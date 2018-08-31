<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	myurl="modulesAction_addModules.action";
$(document).ready(function(){

	
	  $('#addTree').tree({
			onClick: function(node){
/* 				JQuery 
   $("#pidname").attr("value",node.text); 
	$("#mpid").attr("value",node.id); 
 */ 
				  $("#pidname").textbox('setValue',node.text);
				  $("#mpid").textbox('setValue',node.id);
				
			}
			});
});

function submitForm()
{
     $('#myform').form({    
       url:myurl,    
       onSubmit: function(){    
          
            },   
             // 提交成功返回的数据     
        success:function(data){
      	
              if(data=="okla")
              {
            	  // js高级编程   
            	  var $parent = self.parent.$;    
            	  $parent('#mydg').datagrid('reload');
              	  showMyMsg('添加模块信息','添加模块信息成功');
                 //$('#mydg').datagrid('reload'); // reload the data
                 $parent('#addWin').window('close');
        
              }else if(data=="editOk")
              	{
              	showMyMsg('修改模块信息','修改模块信息成功');
                  $('#mydg').datagrid('reload'); // reload the data
                  $('#addWin').window('close');
              	}
              
              else{
              	showMyMsg('模块信息管理','操作失败');
              }
          
         }});
         
    $('#myform').submit();
}
</script>
</head>
<body>
		<div class="easyui-panel" style="padding:5px;width:300px; height:100px;">
		<ul id="addTree" class="easyui-tree" data-options="
		url:'treeAction_loadTreeJSON.action',
		method:'get',
		animate:true,
		lines:true"></ul>
	    
	</div>
		<div>
		<form id="myform" method="post">
	    	<table cellpadding="5">
	    	    <tr style="display:none;">
	    			<td>modulesid:</td>
	    			<td><input class="easyui-textbox" type="text" name="modules.TModuleId" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>模块名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="modules.TModuleName" data-options="required:true"></input></td>
	    		</tr>
	    		   		<tr>
	    			<td>父模块名称:</td>
	    			<td>
	    			 <input id="pidname"  class="easyui-textbox" type="text" data-options="required:true"></input>
	    		</tr>
	    		<tr>
	    			<td>模块父ID:</td>
	    			<td><input id="mpid" class="easyui-textbox" type="text" name="modules.TModulePid" data-options="required:true"></input></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>模块URL:</td>
	    			<td><input class="easyui-textbox" type="text" name="modules.TModuleUrl" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	        <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	    </div>
</body>
</html>