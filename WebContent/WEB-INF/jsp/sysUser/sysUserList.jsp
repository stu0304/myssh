<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
$(document).ready(function(){
	$("#selRole").combobox({
	url:'roleAction_findRole.action',			
	textField:'TRoleName',
	valueField:'TRoleId',
	width:'100px',
	editable:false
});
	$("#UlName").hide();
	$("#Ulspan").hide();
	
})
    var myurl;
    
    function grantRole()
    {
		var row = $('#mydg').datagrid('getSelected');
	       	var selRoleValue= $('#selRole').combobox('getValue');
	       	
	      	 $.ajax({	   			
	   			url:myurl,
	   			method:"post",
	   			dataType:"html",
	   			data:{
	   				roleid:selRoleValue,
	   				userid:row.TUserId
	   			
	   			},
	   			success:function(data){
	   				if(data)
	   					{
	   					showMyMsg('提示','操作成功!');
	   					$('#mydg').datagrid('reload');
	   					$('#addRoleWin').window('close');
	   					}
	   				else{
	   					showMyMsg('用户授权','授权失败!');
	   				}
	   			}
	   		});
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
					$.post('<%=basePath%>sysUserAction_deleteSysUser.action', {
						userid: row.TUserId
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
    	myurl="sysUserAction_addSysUser.action";
    	$('#addWin').window('open');
    }
    
    function editWindow()
    {
    	myurl="sysUserAction_editSysUser.action";
      	var row = $('#mydg').datagrid('getSelected');
    	
      	if(row)
      		{
      		   $('#myform').form('load', {
      			   "user.TUserId":row.TUserId,
      			  "user.TPersonId":row.TPersonId,
      			   "user.TUserState":row.TUserState,
       			  "user.TUserName":row.TUserName,
       			  "user.TUserPwd":row.TUserPwd
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

	function assignRole(){
		   $("#Ulspan").hide();
		   $("#UlName").hide();		
	    	var row = $('#mydg').datagrid('getSelected');
	    	if(row)
	    		{
	    		    if(row.roleName=="无角色")
	    		    	{
	    		    	    myurl="roleAction_grantRole.action";
		    		    	$("#myAddRoleform").form("clear");
		    	    		$('#addRoleWin').window('open');
	    		    	}else{
	    		    		          myurl="roleAction_fixRole.action";	    		    			    		    		  
		    			    		    $("#Ulspan").show();
		    			    		    $("#spanUsername").show();
		    			    			$("#spanUsername").html(row.TUserName);
		    			    			
		    				    		 $("#myAddRoleform").form("load",{
		    				    			 'role.TRoleName':row.roleName
		    				    			});	
		    			    		     $("#addRoleWin").window("open"); 		    			    	
	    				}			    		
	    		}
	    	else{
	    		showMyMsg('用户授权','请选择一条数据!');
	    	}
		
		}
</script>
<style>
ul{
	list-style:none;
}	
</style>
</head>
<body>

    <div id="addWin" class="easyui-window" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:300px;height:200px;padding:10px;">
		<form id="myform" method="post">
	    	<table cellpadding="5">
	    	    <tr style="display:none;">
	    			<td>userid:</td>
	    			<td><input class="easyui-textbox" type="text" name="user.TUserId" data-options="required:true"></input></td>
	    		</tr>
	    		  <tr style="display:none;">
	    			<td>personid:</td>
	    			<td><input class="easyui-textbox" type="text" name="user.TPersonId" data-options="required:true"></input></td>
	    		</tr>
	    		  <tr style="display:none;">
	    			<td>userstate:</td>
	    			<td><input class="easyui-textbox" type="text" name="user.TUserState" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>用户名:</td>
	    			<td><input class="easyui-textbox" type="text" name="user.TUserName" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>密码:</td>
	    			<td><input class="easyui-textbox" type="text" name="user.TUserPwd" data-options="required:true"></input></td>
	    		</tr>
	    		
	    	</table>
	    </form>
	    
	        <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	    </div>
	</div>

<!-- 为用户分配权限窗口 -->
  <div id="addRoleWin" class="easyui-window" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:300px;height:200px;padding:10px;">
		<form id="myAddRoleform" method="post" >			
	    	<ul>
	    	   <li  id="UlName"><input type="text" id="tbxUid" name="user.TUserName"></li>
	    	   <li id="Ulspan">用户名：<span id="spanUsername"></span><br/></li>	    	    
	    		<li>请选择角色：</li>
	    		<li>
	    		  <br/>
	    		   <select id="selRole"  name="role.TRoleName"></select>   		
	    		</li>
	    	</ul>
	    </form>
	    
	        <div style="text-align:center;padding:5px">
	    	<a href="javascript:grantRole();" class="easyui-linkbutton" data-options="iconCls:'icon-save',width:90"  >确定</a>
	    	<a id="btnCancle" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-no',width:90">取消</a>
	    </div>
	</div>


  <table id="mydg" class="easyui-datagrid" title="DataGrid Complex Toolbar" style="width:100%;height:500px"
			data-options="
			rownumbers:true,
			singleSelect:true,
			url:'sysUserAction_loadAllSysUserJSON.action',
			method:'get',
			toolbar:'#tb' ,
			pagination:true,
			pageSize:10,
			pageList:[10,15,20,25]
			">
		<thead>
			<tr>
				<th data-options="field:'TUserId',width:80" hidden="true">Item ID</th>
				<th data-options="field:'TUserName',width:100">用户名</th>
				<th data-options="field:'roleName',width:100">角色名称</th>
				<th data-options="field:'TUserState',width:80,align:'right'">用户状态</th>
			</tr>
		</thead>
	</table>
	
		<div id="tb" style="padding:2px 5px;">
	<div id="ft" style="padding:2px 5px;">
		<a href="javascript:addWindow();" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
		<a href="javascript:editWindow();" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
		<a href="javascript:deleteObject();" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
		
		<c:choose>
		    <c:when test="${myRole=='超级管理员'}">
		        角色授予<a href="javascript:assignRole();" class="easyui-linkbutton" iconCls="icon-tip" plain="true"></a>
		    </c:when>
				    <c:when test="${myRole=='普通管理员'}">
		        角色授予<a href="javascript:assignRole();" class="easyui-linkbutton" iconCls="icon-tip" plain="true"></a>
		    </c:when>
		    <c:otherwise>
		    
		    </c:otherwise>
		</c:choose>
		
	</div>
		  <input id="myname" class="easyui-textbox" data-options="label:'width: 50%',labelPosition:'top',prompt:'请输入用户名:'" style="width:30%;height:25px">
		<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">Search</a>
	</div>
    
    
</body>
</html>