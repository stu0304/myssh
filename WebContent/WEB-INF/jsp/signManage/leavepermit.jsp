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
					$.post('<%=basePath%>leavePermitAction_deleteLeavePermit.action',
							{ lpid: row.TLpId,  }, 
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
    	myurl="leavePermitAction_addLeavePermit.action";
    	$('#addWin').window('open');
    }
    
    //请假审核
    function checkPermit()
    { 	
      	var row = $('#mydg').datagrid('getSelected');	
		if(row){	
			if(row.TStatus=="未审核"){
	      	 $.ajax({	   			
		   			url:'leavePermitAction_checkLeavePermit.action',
		   			method:"post",
		   			dataType:"html",
		   			data:{	
						tLpId : row.TLpId,
						tLpType : row.TLpType,
						tStartTime : row.TStartTime,
						tEndTime : row.TEndTime,
						tStatus : row.TStatus,
						tPersonId : row.TPersonId,
						tAdminId : row.TAdminId
					},
					success : function(data) {
						if (data) {
							showMyMsg('请假管理', '通过审核!');
							$('#mydg').datagrid('reload');

						} else {
							showMyMsg('请假管理', '审核失败!');
						}
					}
				});
			} else {
				showMyMsg('请假管理', '公告已审核!');
			}
		} else {
			showMyMsg('请假管理', '请选择一条数据审核!');
		}

	}

	function editWindow() {
		myurl = "leavePermitAction_editLeavePermit.action";
		var row = $('#mydg').datagrid('getSelected');
		if (row) {

			$('#myform').form('load', {
				"leavePermit.TLpId" : row.TLpId,
				"leavePermit.TLpType" : row.TLpType,
				"leavePermit.TStartTime" : row.TStartTime,
				"leavePermit.TEndTime" : row.TEndTime,
				"leavePermit.TStatus" : row.TStatus,
				"leavePermit.TPersonId" : row.TPersonId,
				"leavePermit.TAdminId" : row.TAdminId
			});
			$('#addWin').window('open');
		} else {
			showMyMsg('修改用户', '请选择一条数据');
		}
	}

	function submitForm() {
		$('#myform').form({
			url : myurl, 
			onSubmit : function() {

			},

			success : function(data) {

				if (data == "okla") {
					showMyMsg('添加用户', '添加用户成功');
					$('#mydg').datagrid('reload'); // reload the data
					$('#addWin').window('close');

				} else if (data == "editOk") {
					showMyMsg('修改用户', '修改用户成功');
					$('#mydg').datagrid('reload'); // reload the data
					$('#addWin').window('close');
				} else {
					showMyMsg('用户管理', '操作失败');
				}

			}
		});

		$('#myform').submit();
	}

	function clearForm() {
		$('#myform').form('clear');

	}
</script>
</head>       
<body>

    <div id="addWin" class="easyui-window" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:300px;height:220px;padding:10px;">
		<form id="myform" method="post">
	    	<table cellpadding="5">     
	    	    <tr style="display:none;">
	    			<td>lpid:</td>
	    			<td><input class="easyui-textbox" type="text" name="leavePermit.TLpId" data-options="required:true"></input></td>
	    		</tr>
	    		  <tr>
	    			<td>请假类型:</td>
	    			<td>	    			 
	    			   <select id="cc" class="easyui-combobox" name="leavePermit.TLpType" style="width:100%;">   
    						<option value="事假">事假 </option>   
    						<option value="婚假">婚假</option>   
    						<option value="丧假">丧假 </option>  
    						<option value="年休假">年休假</option>  
						</select> 
	    			</td>
	    
	    		</tr>
	    		<tr>
	    			<td>假期开始时间:</td>
	    			<td><input class="easyui-datebox" type="text" name="leavePermit.TStartTime" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>假期结束时间:</td>
	    			<td><input class="easyui-datebox" type="text" name="leavePermit.TEndTime" data-options="required:true"></input></td>
	    		</tr>
	    		<tr style="display:none;">
	    			<td>审核状态:</td>
	    			<td><input class="easyui-textbox" type="text" name="leavePermit.TStatus" data-options="required:true"></input></td>
	    		</tr>
	    		<tr style="display:none;">
	    			<td>申请人:</td>
	    			<td><input class="easyui-textbox" type="text" name="leavePermit.TPersonId" data-options="required:true"></input></td>
	    		</tr>	
	    		<tr style="display:none;">
	    			<td>审核人:</td>
	    			<td><input class="easyui-textbox" type="text" name="leavePermit.TAdminId" data-options="required:true"></input></td>
	    		</tr>	
	    		
	    	</table>
	    </form>    
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	    </div>
	</div>
											<!-- title="DataGrid Complex Toolbar" -->
        <table id="mydg" class="easyui-datagrid" style="width:100%;height:500px" 
        		data-options="rownumbers:true,
        					  singleSelect:true,
        					  url:'leavePermitAction_loadAllLeavePermitJSON.action',
        					  method:'get',
        					  toolbar:'#tb',
        					  pagination:true,
							  pageSize:10,
							  pageList:[10,15,20,25,30] ">
			<thead>
				<tr><!-- hidden="true" -->
					<th data-options="field:'TLpId',width:80" align="center" >lpid</th>
					<th data-options="field:'TLpType',width:100" align="center">请假类型</th>
					<th data-options="field:'TStartTime',width:80" align="center">假期开始时间</th>
					<th data-options="field:'TEndTime',width:80" align="center">假期结束时间</th>
					<th data-options="field:'TStatus',width:80" align="center">审核状态</th>
					<th data-options="field:'TPersonId',width:80" align="center">申请人</th>
					<th data-options="field:'TAdminId',width:80" align="center">审核人</th>
				</tr>
			</thead>
		</table>
		<div id="tb" style="padding:2px 5px;">
			<div id="ft" style="padding:2px 5px;">
				<a href="javascript:addWindow();" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
				<a href="javascript:editWindow();" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
				<a href="javascript:deleteObject();" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>  		  		
				<a href="javascript:checkPermit();" class="easyui-linkbutton" iconCls="icon-man" plain="true">审核</a></br>	
				<input class="easyui-textbox" id="myname" data-options="label:'width: 50%',labelPosition:'top',prompt:'请输入用户名:'" style="width:30%;height:25px">
				<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			</div>
		</div>   
        </div>
    </div>
    
    
</body>
</html>