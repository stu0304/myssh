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

    function monthManage()
    {
        var logidValue=123;
 		 $.ajax({	   			
	   			url:'workLogAction_monthManage.action',
	   			method:"post",
	   			dataType:"html",
	   			data:{
	   				logid:logidValue

	   			},
	   			success:function(data){
	   				if(data)
	   					{	   					
	   					showMyMsg('签到管理','下发消息成功!');
	   					$('#mydg').datagrid('reload');
	   		            
	   					}
	   				else{
	   					showMyMsg('签到管理','下发消息失败!');
	   				}
	   			}
	   		});	
    }
    function signManage()
    {
        var logidValue=123;
 		 $.ajax({	   			
	   			url:'workLogAction_signManage.action',
	   			method:"post",
	   			dataType:"html",
	   			data:{
	   				logid:logidValue

	   			},
	   			success:function(data){
	   				if(data)
	   					{	   					
	   					//alert(row.TContent);
	   					showMyMsg('签到管理','签到成功!');
	   					$('#mydg').datagrid('reload');
	   		            
	   					}
	   				else{
	   					showMyMsg('签到管理','签到失败!');
	   				}
	   			}
	   		});	
    }
   
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
				
					$.post('<%=basePath%>workLogAction_deleteWorkLog.action',
							{logid: row.TLogId,  }, 
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
    	myurl="workLogAction_addWorkLog.action";
    	$('#addWin').window('open');
    }

    function editWindow()
    {
    	myurl="workLogAction_editWorkLog.action";
      	var row = $('#mydg').datagrid('getSelected');
      	if(row)
      		{
      			
      		   $('#myform').form('load',  {
      			   "workLog.TLogId":row.TLogId,

       			   "workLog.TStartTime":row.TStartTime,
       			   "workLog.TEndTime":row.TEndTime,
       			   "workLog.TWorkLate":row.TWorkLate,
    			   "workLog.TWorkLess":row.TWorkLess,
    			   "workLog.TWorkNone":row.TWorkNone
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
	//	$('#myform').form('clear');

	}
</script>
</head>       
<body>

    <div id="addWin" class="easyui-window" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:300px;height:150px;padding:10px;">
		<form id="myform" method="post">
	    	<table cellpadding="5">     
	    	    <tr style="display:none;" >
	    			<td>LogId:</td>
	    			<td><input id="logid" class="easyui-textbox" type="text" name="workLog.TLogId" data-options="required:true"></input></td>
	    		</tr>
	   
	    		<tr>
	    			<td>当前签到时间:</td>
	    			<td><input id="ss" class="easyui-timespinner" type="text" name="mysignTime" data-options="required:true"></input></td>
	    		</tr>
	    		<tr style="display:none;">
	    			<td>上班打卡时间:</td>
	    			<td><input class="easyui-timespinner" type="text" name="workLog.TStartTime" data-options="required:true"></input></td>
	    		</tr>
	    		<tr style="display:none;">
	    			<td>下班打卡时间:</td>
	    			<td><input class="easyui-timespinner" type="text" name="workLog.TEndTime" data-options="required:true"></input></td>
	    		</tr>
	    		<tr style="display:none;">
	    			<td>迟到:</td>
	    			<td><input class="easyui-textbox" type="text" name="workLog.TWorkLate" data-options="required:true"></input></td>
	    		</tr>
	    		<tr style="display:none;">
	    			<td>早退:</td>
	    			<td><input class="easyui-textbox" type="text" name="workLog.TWorkLess" data-options="required:true"></input></td>
	    		</tr>
	    		<tr style="display:none;">
	    			<td>旷工:</td>
	    			<td><input class="easyui-textbox" type="text" name="workLog.TWorkNone" data-options="required:true"></input></td>
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
        					  url:'workLogAction_loadAllWorkLogJSON.action',
        					  method:'get',
        					  toolbar:'#tb',
        					  pagination:true,
							  pageSize:10,
							  pageList:[10,15,20,25,30] ">
			<thead>
				<tr>                                                      
					<th data-options="field:'TLogId',width:80" align="center" hidden="true">TLogId</th>
					<th data-options="field:'TPersonId',width:100" align="center" hidden="true">PersonId</th>
					<th data-options="field:'TLogDate',width:100" align="center" >当前日期</th>
					<th data-options="field:'TStartTime',width:80" align="center">上班打卡时间</th>
					<th data-options="field:'TEndTime',width:80" align="center">下班打卡时间</th>
					<th data-options="field:'TWorkLate',width:100" align="center">是否迟到</th>
					<th data-options="field:'TWorkLess',width:80" align="center">是否早退</th>
					<th data-options="field:'TWorkNone',width:80" align="center">是否旷工</th>
				</tr>
			</thead>
		</table>

		<div id="tb" style="padding:2px 5px;">
			<div id="ft" style="padding:2px 5px;">
			<!--  
				<a href="javascript:addWindow();" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
				<a href="javascript:editWindow();" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
				<a href="javascript:deleteObject();" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>	-->					
				<a href="javascript:signManage();" class="easyui-linkbutton" iconCls="icon-tip" plain="true">签到</a>
				<a href="javascript:monthManage();" class="easyui-linkbutton" iconCls="icon-man" plain="true">选择当前月份</a>
				<a href="jumpAction_goTomyLeaveColumn.action" class="easyui-linkbutton" iconCls="icon-man" plain="true">柱状图</a>
				<a href="jumpAction_goToWorkLogSum.action" class="easyui-linkbutton" iconCls="icon-tip" plain="true">汇总</a></br>			  		
				
				<input class="easyui-textbox" id="myname" data-options="label:'width: 50%',labelPosition:'top',prompt:'请输入用户名:'" style="width:30%;height:25px">
				<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			</div>
		</div>   
        </div>
    </div>
    
    
</body>
</html>