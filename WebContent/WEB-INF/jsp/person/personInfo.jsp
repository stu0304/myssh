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
					$.post('<%=basePath%>personAction_deletePerson.action',
							{personid: row.TPersonId,  }, 
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
    	myurl="personAction_addPerson.action";
    	$('#addWin').window('open');
    }
    function editWindow()
    {
    	myurl="personAction_editPerson.action";
      	var row = $('#mydg').datagrid('getSelected');
      	if(row)
      		{
      		   $('#myform').form('load',  {
      			   "person.TPersonId":row.TPersonId,
       			   "person.TPersonName":row.TPersonName,
       			   "person.TMobile":row.TMobile,
       			   "person.TWorkNum":row.TWorkNum,
    			   "person.TEducation":row.TEducation,
    			   "person.TIdNum":row.TIdNum,
    			   "person.TAddress":row.TAddress,
       			   "person.TGender":row.TGender,
       			   "person.TBirthDate":row.TBirthDate,
       			   "person.TAddDate":row.TAddDate,
    			   "person.TWorkPhone":row.TWorkPhone,
    			   "person.TType":row.TType
        		   
       		   });
      		   $('#addWin').window('open');
      		}else{
      			showMyMsg('修改用户','请选择一条数据');
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
                    	showMyMsg('用户管理','操作失败');
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
	    			<td>personid:</td>
	    			<td><input class="easyui-textbox" type="text" name="person.TPersonId" data-options="required:true"></input></td>
	    		</tr>
	    		  <tr>
	    			<td>员工姓名:</td>
	    			<td><input class="easyui-textbox" type="text" name="person.TPersonName" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>手机号:</td>
	    			<td><input class="easyui-numberbox" type="text" name="person.TMobile" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>员工工号:</td>
	    			<td><input class="easyui-textbox" type="text" name="person.TWorkNum" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>学历:</td>
	    			<td><input class="easyui-textbox" type="text" name="person.TEducation" data-options="required:true"></input></td>
	    		</tr>
	    			<tr>
	    			<td>身份证号:</td>
	    			<td><input class="easyui-textbox" type="text" name="person.TIdNum" data-options="required:true"></input></td>
	    		</tr>	<tr>
	    			<td>现住址:</td>
	    			<td><input class="easyui-textbox" type="text" name="person.TAddress" data-options="required:true"></input></td>
	    		</tr>	
	    		<tr>
	    			<td>性别:</td>
	    			<td>
	    			   <!-- input class="easyui-textbox" type="text" name="person.TGender" data-options="required:true"></input-->
	    			   <select id="pgender" class="easyui-combobox" name="person.TGender" style="width:100%;">   
    						<option value="男">男</option>   
    						<option value="女">女</option>   
						</select> 
	    			</td>
	    		</tr>	
	    		<tr>
	    			<td>生日:</td>
	    			<td><input class="easyui-datebox" type="text" name="person.TBirthDate" data-options="required:true"></input></td>
	    		</tr>	
	    		<tr>
	    			<td>入职日期：</td>
	    			<td><input class="easyui-datebox" type="text" name="person.TAddDate" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>办公电话:</td>
	    			<td><input class="easyui-numberbox" type="text" name="person.TWorkPhone" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>员工类型:</td>     <!-- 合同工、临时工、试用期  -->
	    			<td>
	    			<!--input class="easyui-textbox" type="text" name="person.TType" data-options="required:true"></input-->
	    				<select id="ptype" class="easyui-combobox" name="person.TType" style="width:100%;">   
    						<option value="合同工">合同工</option>   
    						<option value="临时工">临时工</option>   
   							<option value="试用期">试用期</option>   
						</select> 
	    			</td>
	    		</tr>
	    		<tr style="display:none;">
	    			<td>职位id:</td>
	    			<td><input class="easyui-textbox" type="text" name="person.TJobId" data-options="required:true"></input></td>
	    		</tr>
	    		<tr style="display:none;">
	    			<td>部门id:</td>
	    			<td><input class="easyui-textbox" type="text" name="person.TDepartmentId" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    </form>    
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	    </div>
	</div>


  		        <table id="mydg" class="easyui-datagrid" title="DataGrid Complex Toolbar" style="width:100%;height:500px" 
        		data-options="rownumbers:true,
        					  singleSelect:true,
        					  url:'personAction_loadAllPersonJSON.action',
        					  method:'get',
        					  toolbar:'#tb',
        					  pagination:true,
								pageSize:10,
								pageList:[10,15,20,25]
							  ">
			<thead>
				<tr>
					<th data-options="field:'TPersonId',width:80" align="center" hidden="true">Item ID</th>
					<th data-options="field:'TPersonName',width:100" align="center">员工姓名</th>
					<th data-options="field:'TMobile',width:80" align="center">手机号</th>
					<th data-options="field:'TWorkNum',width:80" align="center">员工工号</th>
					<th data-options="field:'TEducation',width:80" align="center">学历</th>
					<th data-options="field:'TIdNum',width:80" align="center">身份证号</th>
					<th data-options="field:'TAddress',width:80" align="center">现住址</th>
					<th data-options="field:'TGender',width:80" align="center">性别</th>
					<th data-options="field:'TBirthDate',width:80" align="center">生日</th>
					<th data-options="field:'TAddDate',width:80" align="center">入职日期</th>
					<th data-options="field:'TWorkPhone',width:80" align="center">办公电话</th>
					<th data-options="field:'TType',width:80" align="center">员工类型 </th><!--合同工、临时工、试用期 -->
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