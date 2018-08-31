<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

    var myurl;
    function doSearch()
    {
            // alert($('#searchNameId').val());
        	$('#mydg').datagrid('load', {
			searchName : $('#myname').val()
			//uname : $('#uname').val()
		}

		);
    }
    
    function deleteObject()
    {
		var row = $('#mydg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', '亲,您真的要删除吗?', function(r) {
				if (r) {
					$.post('<%=basePath%>modulesAction_deleteModules.action', {
						modulesid: row.TModuleId
					}, function(result) {
						if (result.success) {
							$('#mydg').datagrid('reload');
						} else {
							showMyMsg('删除模块信息','删除用户失败!');
						}
					}, 'json');
				}
			});
		}else{
			showMyMsg('删除模块信息','请选择一条数据');
		}
    }
    
    function addWindow()
    {
    	  $('#myform').form('clear');
    	myurl="modulesAction_addModules.action";
    	$('#addWin').window('open');
    }
    
    function editWindow()
    {
    	myurl="modulesAction_editModules.action";
      	var row = $('#mydg').datagrid('getSelected');
    	
      	if(row)
      		{
      		   $('#myform').form('load', {
      			  "modules.TModuleId":row.TModuleId,
      			  "modules.TModuleName":row.TModuleName,
      			  "modules.TModulePid":row.TModulePid,
       			  "modules.TModuleUrl":row.TModuleUrl,
      		   });
      		 	$('#addWin').window('open');
      		}else{
      			showMyMsg('修改模块信息','请选择一条数据');
      		}
      	
   
    	
    }
    
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
                    	showMyMsg('添加模块信息','添加模块信息成功');
                       $('#mydg').datagrid('reload'); // reload the data
                       $('#addWin').window('close');
              
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
	  
	function clearForm(){
		$('#myform').form('clear');
	}
	
	
	
	
</script>
</head>
<body>

    <div id="addWin" class="easyui-window" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:600px;height:300px;padding:10px;">
          <iframe scrolling="auto" frameborder="0"  src="jumpAction_addModule.action" style="width:100%;height:100%;"></iframe>
	</div>

  
            <table id="mydg" class="easyui-datagrid" title="DataGrid Complex Toolbar" style="width:100%;height:500px"
			data-options="
			rownumbers:true,
			singleSelect:true,
			url:'modulesAction_loadAllModulesJSON.action',
			method:'get',
			toolbar:'#tb' ,
			pagination:true,
			pageSize:10,
			pageList:[10,15,20,25]
			">
		<thead>
			<tr>
				<th data-options="field:'TModuleId',width:80" hidden="true">模块ID</th>
				<th data-options="field:'TModuleName',width:100">模块名称</th>
				<th data-options="field:'TModulePid',width:80,align:'right'">父模块名称</th>
				<th data-options="field:'TModuleUrl',width:80,align:'right'">模块URL</th>
			</tr>
		</thead>
	</table>
	
		<div id="tb" style="padding:2px 5px;">
	    <div id="ft" style="padding:2px 5px;">
		<a href="javascript:addWindow();" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
		<a href="javascript:editWindow();" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
		<a href="javascript:deleteObject();" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
	</div>
		  <input class="easyui-textbox" id="myname" data-options="label:'width: 50%',labelPosition:'top',prompt:'请输入模块名称:'" style="width:30%;height:25px">
		<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">Search</a>
	</div>
	
        </div>
   
    
    
</body>
</html>