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
    function checkNotice()
    {
		var row = $('#noticeDg').datagrid('getSelected');
		
		if(row)
			{
			  
	      	 $.ajax({	   			
		   			url:'noticeAction_checkNotice.action',
		   			method:"post",
		   			dataType:"html",
		   			data:{
		   				noticeID:row.TNoticeId
		   			
		   			},
		   			success:function(data){
		   				if(data)
		   					{
		   					showMyMsg('消息管理','下发消息成功!');
		   					$('#noticeDg').datagrid('reload');
		   		
		   					}
		   				else{
		   					showMyMsg('消息管理','下发消息失败!');
		   				}
		   			}
		   		});
	      	 
			}
		else{
			showMyMsg('消息管理','请选择一条数据审核!');
		}
    }
    
    function doSearch()
    {
        	$('#noticeDg').datagrid('load', {
				searchName : $('#myname').val()
			});
    }
    function deleteObject()
    {
		var row = $('#noticeDg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', '亲,您真的要删除吗?', function(r) {
				if (r) {
					//ajax
					$.post('<%=basePath%>noticeAction_deleteNotice.action',
							{noticeid:row.TNoticeId,  }, 
						    function(result) {
								if (result.success) {
									$('#noticeDg').datagrid('reload');
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
    	myurl="noticeAction_addNotice.action";
    	$('#addWin').window('open');
    }
    function editWindow()
    {
    	myurl="noticeAction_editNotice.action";
      	var row = $('#noticeDg').datagrid('getSelected');
      	if(row)
      		{
      		   $('#myform').form('load',  {
      			 "notice.TNoticeId":row.TNoticeId,
    			 "notice.TTitle":row.TTitle,
     			"notice.TContent":row.TContent,
     			"notice.TAdminId":row.TAdminId,
     			"notice.TAddDate":row.TAddDate,
     			"notice.TStatus":row.TStatus,
     			"notice.TVisitNum":row.TVisitNum
     			
        		   
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
    <div id="addWin" class="easyui-window" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:50%;height:490px;padding:10px;">
		<form id="myform" method="post">
	    	<table cellpadding="5">
	    	    <tr style="display:none;">
	    			<td>noticeid:</td>
	    			<td><input class="easyui-textbox" type="text" name="notice.TNoticeId" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>公告题目:</td>
	    			<td><input class="easyui-textbox" type="text" name="notice.TTitle" data-options="required:true"></input></td>
	    		</tr>
	    
	    		<tr>
	    			<td>公告内容:</td>
	    			<td>
	    			<input id="mpid" name="notice.TContent" class="easyui-textbox" data-options="multiline:true,required:true" style="width:300px;height:100px">
	    		</tr>
	    		
	    		</table>
	    </form>
	      <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	   
     
      </div>       
	    
	    </div>
	      
     


  		        <table id="noticeDg" class="easyui-datagrid" title="DataGrid Complex Toolbar" style="width:100%;height:500px" 
        		data-options="rownumbers:true,
        					  singleSelect:true,
        					  url:'noticeAction_loadAllNoticeJSON.action',
        					  method:'get',
        					  toolbar:'#tb',
        					  pagination:true,
								pageSize:10,
								pageList:[10,15,20,25]
							  ">
			<thead>
				<tr>
				<th data-options="field:'TNoticeId',width:150" hidden="true">noticeid</th>
				<th data-options="field:'TTitle',width:100,align:'center'">公告题目</th>
				<th data-options="field:'TContent',width:80,align:'center'">公告内容</th>
				<th data-options="field:'TAdminId',width:80,align:'center'">发布人ID </th>
				<th data-options="field:'TAddDate',width:80,align:'center'">发布时间 </th>
				<th data-options="field:'TStatus',width:80,align:'center'">发布状态</th>
				<th data-options="field:'TVisitNum',width:80,align:'center'">访问数量</th>
				
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
			<a href="javascript:checkNotice();" class="easyui-linkbutton" iconCls="icon-search">审核</a>
			</div>
		</div>   
    
    
</body>
</html>