<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>       
<body>

<!-- DataGrid -->   											<!-- title="DataGrid Complex Toolbar" -->
        <table id="mydg" class="easyui-datagrid" style="width:100%;height:500px" 
        		data-options="rownumbers:true,
        					  singleSelect:true,
        					  url:'workLogAction_loadAllWorkLogSumJSON.action',
        					  method:'get',
        					  toolbar:'#tb',
        					  pagination:true,
							  pageSize:10,
							  pageList:[10,15,20,25,30] ">
			<thead>
				<tr>                                                      <!-- hidden="true" -->
					<th data-options="field:'tpersonid',width:80" align="center" >姓名</th>
					<th data-options="field:'tworklate',width:100" align="center">迟到</th>
					<th data-options="field:'tworkless',width:100" align="center" >早退</th>
					<th data-options="field:'tworknone',width:80" align="center">旷工</th>
					<th data-options="field:'tworkleave',width:80" align="center">请假</th>
				</tr>
			</thead>
		</table> 
        </div>
    </div>
    
</body>
</html>