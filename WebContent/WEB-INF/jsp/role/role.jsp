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
    function grantModule()
    {
		var row = $('#mydg').datagrid('getSelected');
	    var selModuleValue= $('#selModule').combotree('getValues');
	    $('#myAddModuleform').form({    
            url:myurl,    
            onSubmit: function(param){    
           	 param.modulesid = selModuleValue;
	   				param.roleid = row.TRoleId
                 },   
                  // 提交成功返回的数据     
             success:function(data){
           	
                   if(data=="addOk")
                   {
                      showMyMsg('分配模块','模块分配成功');
                      $('#mydg').datagrid('reload'); // reload the data
                      $('#addModuleWin').window('close');
             
                   }
                   else{
                   	showMyMsg('分配模块','模块分配失败');
                   }
               
              }});
              
         $('#myAddModuleform').submit();
	}

    function assignModule(){
    	var row = $('#mydg').datagrid('getSelected');
    	myurl = "modulesAction_grantModule.action";
    	if(row){
    		if(row.TModuleName=="未分配"){
    			$("#myAddModuleform").form("clear");
    			$('#addModuleWin').window('open');
    		}else{
    			showMyMsg('模块分配','角色已分配模块!');
    		}
    	}else{
    		showMyMsg('模块分配','请选择一个角色!');
    	}

    }
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
					$.post('<%=basePath%>roleAction_deletRole.action', {
				       roleid: row.TRoleId
					}, function(result) {
						if (result.success) {
							$('#mydg').datagrid('reload');
						} else {
							showMyMsg('删除用户','删除用户失败!');
						}
					}, 'json');
				}
			});
		}else{
			showMyMsg('删除用户','请选择一条数据');
		}
    }
    
    function addWindow()
    {
    	  $('#myform').form('clear');
    	myurl="roleAction_addRole.action";
    	$('#addWin').window('open');
    }
    
    function editWindow()
    {
    	myurl="roleAction_editRole.action";
      	var row = $('#mydg').datagrid('getSelected');
    	
      	if(row)
      		{
      		   $('#myform').form('load', {
      			   "role.TRoleId":row.TRoleId,
      			  "role.TRoleName":row.TRoleName
      		   });
      		 	$('#addWin').window('open');
      		}else{
      			showMyMsg('修改用户','请选择一条数据');
      		}
      	
   
    	
    }
    
	  function submitForm()
      {
           $("#myform").form({    
             url:myurl,    
             onSubmit: function(){    
                
                  },   
                   // 提交成功返回的数据     
              success:function(data){
            	
                    if(data=="okla")
                    {
                    	showMyMsg('添加用户','添加用户成功');
                       $('#mydg').datagrid('reload'); // reload the data
                       $('#addWin').window('close');
              
                    }else if(data=="editOk")
                    	{
                    	showMyMsg('修改用户','修改用户成功');
                        $('#mydg').datagrid('reload'); // reload the data
                        $('#addWin').window('close');
                    	}
                    
                    else{
                    	//showMyMsg('用户管理','操作失败');
                    	showMyMsg('修改用户','操作成功');
                        $('#mydg').datagrid('reload'); // reload the data
                        $('#addWin').window('close');
                    }
                
               }});
               
          $("#myform").submit();
      }
	  
	function clearForm(){
		$('#myform').form('clear');
	}
</script>
</head>
<body>

    <div id="addWin" class="easyui-window" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:300px;height:200px;padding:10px;">
		<form id="myform" method="post">
	    	<table cellpadding="5">
	    	    <tr style="display:none;">
	    			<td>roleid:</td>
	    			<td><input class="easyui-textbox" type="text" name="role.TRoleId" data-options="required:true"></input></td>
	    		</tr>
	    		  <tr>
	    			<td>角色名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="role.TRoleName" data-options="required:true"></input></td>
	    		</tr>	    		
	    	</table>
	    </form>
	    
	        <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	    </div>
	</div>
<!-- 为用户分配模块窗口 -->
  <div id="addModuleWin" class="easyui-window" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:300px;height:200px;padding:10px;">
		<form id="myAddModuleform" method="post" style="text-align:center;margin-top: 15px">			
	    	<!--  请选择模块：<select id="selModule" ></select> </br>-->
	    	请选择模块：<input id="selModule" class="easyui-combotree"  name="modules.TModuleId" data-options="
									url:'treeAction_loadTreeJSON.action',
									method:'get',
									animate:true,
									lines:true,
									multiple:true,
									cascadeCheck:false,
									width:'60%'"></input>
	    </form>
	    
	        <div style="text-align:center;padding:5px">
	    	<a href="javascript:grantModule();" class="easyui-linkbutton" data-options="iconCls:'icon-save',width:90"  >确定</a>
	    	<a id="btnCancle" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-no',width:90">取消</a>
	    </div>
	</div>
  <table id="mydg" class="easyui-datagrid" title="DataGrid Complex Toolbar" style="width:100%;height:500px"
			data-options="
			rownumbers:true,
			singleSelect:true,
			url:'roleAction_loadAllRoleJSON.action',
			method:'get',
			toolbar:'#tb' ,
			pagination:true,
			pageSize:10,
			pageList:[10,15,20,25]
			">
		<thead>
			<tr>
				<th data-options="field:'TRoleId',width:80" hidden="true">role ID</th>
				<th data-options="field:'TRoleName',width:100">角色名</th>
				<th data-options="field:'TModuleName',width:100" align="center">模块</th>
			</tr>
		</thead>
	</table>
	
		<div id="tb" style="padding:2px 5px;">
	<div id="ft" style="padding:2px 5px;">
		<a href="javascript:addWindow();" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
		<a href="javascript:editWindow();" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
		<a href="javascript:deleteObject();" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
		<a href="javascript:assignModule();" class="easyui-linkbutton" iconCls="icon-tip" plain="true">分配模块</a>
	</div>
		  <input id="myname" class="easyui-textbox" data-options="label:'width: 50%',labelPosition:'top',prompt:'请输入用户名:'" style="width:30%;height:25px">
		<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">Search</a>
	</div>
    
    
</body>
</html>