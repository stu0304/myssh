<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	

<script type="text/javascript">

   function myLogin()
   {
	   document.getElementById("myform").submit();
   }

</script>
</head>
<body>
   
   <div style="margin:150px 400px;">
  <form action="loginAction_login.action" id="myform">
    <div class="easyui-panel" title="Login to system" style="width:400px;padding:30px 70px 20px 70px;">
		<div style="margin-bottom:10px">
			<input class="easyui-textbox" name="user.TUserName" style="width:100%;height:40px;padding:12px" data-options="prompt:'清输入用户名:',iconCls:'icon-man',iconWidth:38">
		</div>
		<div style="margin-bottom:20px">
			<input class="easyui-textbox" name="user.TUserPwd" type="password" style="width:100%;height:40px;padding:12px" data-options="prompt:'请输入密码:',iconCls:'icon-lock',iconWidth:38">
		</div>
		<div style="margin-bottom:20px">
			<input type="checkbox" checked="checked">
			<span>记住我</span>
			<a href="JumpAction_goToRegisterPage.action">注册</a>
		</div>
		<div>
			<a href="javascript:myLogin();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:100%;">
				<span style="font-size:14px;">登陆</span>
			</a>
		</div>
	</div>
       </form>
</div>
    
</body>
</html>