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
					$.post('<%=basePath%>workRulesAction_deleteWorkRules.action',
							{ruleid: row.TRuleId,  }, 
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
    	myurl="workRulesAction_addWorkRules.action";
    	$('#addWin').window('open');
    }

    function editWindow()
    {
    	myurl="workRulesAction_editWorkRules.action";
      	var row = $('#mydg').datagrid('getSelected');
      	if(row)
      		{
      		
      		   $('#myform').form('load',  {
      			   "workRules.TRuleId":row.TRuleId,
       			   "workRules.TStartTime":row.TStartTime,
       			   "workRules.TEndTime":row.TEndTime,
       			   "workRules.TAfterMinute":row.TAfterMinute
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

    <div id="addWin" class="easyui-window" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:300px;height:230px;padding:10px;">
		<form id="myform" method="post">
		
			
	    	<table cellpadding="5">     
	    	
	    	    <tr style="display:none;" > <!-- style="display:none;" -->
	    			<td>ruleid:</td>
	    			<td><input class="easyui-textbox" type="text" name="workRules.TRuleId" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>上班时间:</td>
	    			<td><input class="easyui-timespinner" type="text" name="workRules.TStartTime" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>下班时间:</td>
	    			<td><input class="easyui-timespinner" type="text" name="workRules.TEndTime" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>迟到旷工:</td>
	    			<td><input class="easyui-timespinner" type="text" name="workRules.TAfterMinute" data-options="required:true"></input></td>
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
        					  url:'workRulesAction_loadAllWorkRulesJSON.action',
        					  method:'get',
        					  toolbar:'#tb',
        					  pagination:true,
							  pageSize:10,
							  pageList:[10,15,20,25,30] ">
			<thead>
				<tr>
					<th data-options="field:'TRuleId',width:80" align="center" hidden="true">ruleid</th>
					<th data-options="field:'TStartTime',width:100" align="center">上班时间</th>
					<th data-options="field:'TEndTime',width:80" align="center">下班时间</th>
					<th data-options="field:'TAfterMinute',width:100" align="center">迟到/旷工时间</th>
				</tr>
			</thead>
		</table>

		<div id="tb" style="padding:2px 5px;">
			<div id="ft" style="padding:2px 5px;">
				<a href="javascript:addWindow();" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
				<a href="javascript:editWindow();" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
				<a href="javascript:deleteObject();" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a></br>			  		
				
				<input class="easyui-textbox" id="myname" data-options="label:'width: 50%',labelPosition:'top',prompt:'请输入用户名:'" style="width:30%;height:25px">
				<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			</div>
		</div>   
        </div>
    </div>
    
    
</body>
</html>