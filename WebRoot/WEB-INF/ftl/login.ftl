<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网站后台管理系统-登录</title>
<#include "include/common.ftl"/>
<link href="/css/style.css" rel="stylesheet" type="text/css" />
<script src="js/login.js"></script>
<script src="/js/jquery.validationEngine.pack.js"></script>
<script src="/js/jquery.validationEngine-cn.js"></script>
<link href="/css/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
<script  language="javascript">	
$(document).ready(function(){
   $("#loginForm").validationEngine({ ajaxSubmit: true, validateAttribute: "validate",validationEventTriggers:"keyup blur"}); 
});
</script>
</head>
<!--[if IE 6]>
<script type="text/javascript" src="http://axiu.me/wp-content/themes/Summ/js/DD_belatedPNG.js" ></script>
 
<script type="text/javascript">
DD_belatedPNG.fix('.bj');
</script>
<![endif]-->
<body>
 <div class="contant">
  <div class="bj">
   <div class="bj1">
    <div class="login">
	 <div class="login1" style="position:relative;">
	 	<div id="tip" style="color: red; position: absolute; top: -15px; left: 300px;"></div>
		<form id="loginForm" name="loginForm" action="/login.action" method="post">
		   <table width="100%" border="0">
	         <tr>
	           <td align="right" valign="middle" class="hui">用户名：</td>
	           <td align="left" valign="middle"><input name="loginUsername" id="loginUsername" type="text" class="input" validate="validate[required]"/></td>
	         </tr>
	         <tr>
	           <td align="right" valign="middle" class="hui">密码：</td>
	           <td align="left" valign="middle"><input name="loginPassword" id="loginPassword" type="password" class="input" validate="validate[required]"/></td>
	         </tr>
	         <tr>
	           <td align="right" valign="middle" class="hui">&nbsp;</td>
	           <td height="60" align="left" valign="middle"><a id="loginBtn" href="javascript:;"><img src="images/login.jpg" width="85" height="28" border="0" /></a>&nbsp; &nbsp;<a id="loginClear" href="javascript:;"><img src="images/index_05.jpg" width="85" height="28" border="0" /></a></td>
	         </tr>
	       </table>
       </form>
	 </div>
	</div>
   </div>
  </div>
 </div>
</body>
</html>