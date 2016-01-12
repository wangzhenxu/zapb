<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>网站后台管理系统-产品分类</title>
	<script src="/js/jquery.validationEngine.pack.js"></script>
    <script src="/js/jquery.validationEngine-cn.js"></script>
    <script src="/js/hiAlert/jquery.alert.js"></script>	
    <script src="/js/password.js"></script>	
    <link href="/css/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
    <link href="/css/alert.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		.tip{color:red;padding-left:5px;}
	</style>
	<script  language="javascript">	
		$(function(){
			menuClick("d3");
		})
	</script>
</head>
<body>
   <div class="right">
    <div class="location">
     <div class="location01">您现在的位置是：首页 &gt; <strong>修改密码</strong></div>
    </div>
    <div class="sort">
     <div class="sort1">修改密码</div>
     <div class="query">
     	<form id="password" action="/account/changePassword.action" method="post" passed="true">
      <table width="600" border="0">
       <tr>
        <td align="right" class="hui1" width="30%">原密码：</td>
        <td  width="70%"><input id="oldPassword" name="oldPassword" type="password"  class="input" validate="validate[required]"/></td>
       </tr>
       <tr>
        <td align="right" class="hui1">新密码：</td>
        <td><input id="newPassword" name="newPassword" type="password"  class="input" validate="validate[required,length[4,20]]"/></td>
       </tr>
       <tr>
        <td align="right" class="hui1">确认新密码：</td>
        <td><input id="retryPassword" name="retryPassword" type="password" class="input" validate="validate[required,confirm[newPassword]]"/></td>
       </tr>
       <tr>
         <td align="right" class="hui1">&nbsp;</td>
         <td height="60" valign="middle"><a href="javascript:changePassword();" ><img src="images/bjwj03.jpg" width="62" height="23" /></a> </td>
       </tr>
      </table>
      </form>
     </div>
    </div>
   </div>
  </div>
 </div>
 <script>
	    common.initLeftMenuSelected("editPassword");
</script>
</body>

</html>