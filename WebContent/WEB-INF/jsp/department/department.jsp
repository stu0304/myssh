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
        	$('#mydg').datagrid('load', {
				searchName : $('#myname').val()
			});
    }
    function deleteObject()
    {
		var row = $('#mydg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', '亲,您真的要删除吗?', function(r) {
				if (r) {
					//ajax
					$.post('<%=basePath%>departAction_deleteDepart.action',
							{Depratid: row.TDeptId,  }, 
						    function(result) {
								if (result.success) {
									$('#mydg').datagrid('reload');
									showMyMsg('删除用户','删除用户成功!');
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
    	myurl="departAction_addDepart.action";
    	$('#addWin').window('open');
    }
    function editWindow()
    {
    	myurl="departAction_editDepart.action";
      	var row = $('#mydg').datagrid('getSelected');
      	if(row)
      		{
      		   $('#myform').form('load',  {
      			   "department.TDeptId":row.TDeptId,
       			   "department.TDeptName":row.TDeptName,
       			   "department.TStatus":row.TStatus,
       			   "department.TBudget":row.TBudget,
    			   "department.TDeptDesc":row.TDeptDesc,
    			   "department.TAdminId":row.TAdminId,
    			   "department.TBianzhi":row.TBianzhi,
       			  
       		   });
      		   $('#addWin').window('open');
      		}else{
      			showMyMsg('修改部门','请选择一条数据');
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
                    	showMyMsg('添加部门','添加部门成功');
                       $('#mydg').datagrid('reload'); // reload the data
                       $('#addWin').window('close');
              
                    }else if(data=="editOk")
                    	{
                    	showMyMsg('修改部门','修改部门成功');
                        $('#mydg').datagrid('reload'); // reload the data
                        $('#addWin').window('close');
                    	}
                    
                    else{
                    	showMyMsg('部门管理','操作失败');
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
    <div id="addWin" class="easyui-window" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:300px;height:490px;padding:10px;">
		<form id="myform" method="post">
	    	<table cellpadding="5">     
	    	    <tr style="display:none;">
	    			<td>Depratid:</td>
	    			<td><input class="easyui-textbox" type="text" name="department.TDeptId" data-options="required:true"></input></td>
	    		</tr>
	    		  <tr>
	    			<td>部门名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="department.TDeptName" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>部门状态:</td>
	    			<td><input class="easyui-numberbox" type="text" name="department.TStatus" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>部门预算:</td>
	    			<td><input class="easyui-textbox" type="text" name="department.TBudget" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>部门职能:</td>
	    			<td><input class="easyui-textbox" type="text" name="department.TDeptDesc" data-options="required:true"></input></td>
	    		</tr>
	    			<tr>
	    			<td>部门总监:</td>
	    			<td><input class="easyui-textbox" type="text" name="department.TAdminId" data-options="required:true"></input></td>
	    		</tr>	<tr>
	    			<td>人员编制:</td>
	    			<td><input class="easyui-textbox" type="text" name="department.TBianzhi" data-options="required:true"></input></td>
	    		</tr>	
	    		
	    	</table>
	    </form>    
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	    </div>
	</div>


  		  <table id="mydg" class="easyui-datagrid" style="width:100%;height:500px" 
        		data-options="rownumbers:true,
        					  singleSelect:true,
        					  url:'departAction_loadAllDepartJSON.action',
        					  method:'get',
        					  toolbar:'#tb',
        					  pagination:true,
							pageSize:10,
							pageList:[10,15,20,25]
							  ">
			<thead>
				<tr>
					<th data-options="field:'TDeptId',width:80" align="center" hidden="true">部门 ID</th>
					<th data-options="field:'TDeptName',width:100" align="center">部门名称</th>
					<th data-options="field:'TStatus',width:80" align="center">部门状态</th>
					<th data-options="field:'TBudget',width:80" align="center">部门预算</th>
					<th data-options="field:'TDeptDesc',width:80" align="center">部门职能</th>
					<th data-options="field:'TAdminId',width:80" align="center">部门总监</th>
					<th data-options="field:'TBianzhi',width:80" align="center">人员编制</th>
					</tr>
			</thead>
		</table>

		<div id="tb" style="padding:2px 5px;">
			<div id="ft" style="padding:2px 5px;">
				<a href="javascript:addWindow();" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
				<a href="javascript:editWindow();" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
				<a href="javascript:deleteObject();" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>				  		
				
				<input class="easyui-textbox" id="myname" data-options="label:'width: 50%',labelPosition:'top',prompt:'请输入用户名:'" style="width:10%;height:25px">
				<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			</div>
		</div>   
    
    
</body>
</html>