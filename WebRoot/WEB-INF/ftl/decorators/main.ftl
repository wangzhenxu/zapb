<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
<#include "../include/common.ftl"/>
${head}
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script  language="javascript">
$(function(){
     $(".left").highLight("left_menu","list");
});
</script>
</head>
<body>
 <div id="contant">
  <div class="head"><img src="/images/erji_01.jpg" /></div>
  <div class="position">
   <div class="position01">欢迎您,<span class="org">${Session[Const.SESSION_USER_KEY].username}</span>,今天是2012年11月9日</div>
   <div class="position02"><img src="/images/hy_06.jpg" style="vertical-align:middle"/>&nbsp;<a href="/logout.action" id="logoutBtn">退出登录</a></div>
  </div>
  
  <div class="main">    
   <#--菜单内容-->
   <div class="sinbar">
    <div class="sinbar01">系统功能</div>
    <div class="left_menu">
     <ul class="c">
      <#if subject.isPermitted("productClass:list")>
      	<li><a href="/product/class/list.action" class="list">产品分类</a></li>
      </#if>
      <#if subject.isPermitted("product:list")>
      	<li><a href="/product/management/list.action" onclick="changeList()">产品管理</a></li>
      </#if>
      <#if subject.isPermitted("sn:list")>
      	<li><a href="/product/serialnumber/list.action">序列号管理</a></li>
      </#if>
      <#if subject.isPermitted("user:list")>
      	<li><a href="/user/list.action">网站用户</a></li>
      </#if>
      <#if subject.isPermitted("role:list")>
      	<li><a href="/account/role/list.action" class="two">后台角色</a></li>
      </#if>
      <#if subject.isPermitted("account:list")>
      	<li><a href="/account/list.action">后台帐号</a></li>
      </#if>
      <li><a href="/password.action">修改密码</a></li>
      <#if subject.isPermitted("app:appList")>
	      <li><a href="/app/getAppList.action">应用查看</a></li>
      </#if>
      <#if subject.isPermitted("app:auditAppList")>
	      <li><a href="/app/auditapplist.action">应用审核</a></li>
      </#if>
      <li><a href="/app/getDisplayList.action">应用上线</a></li>
      <li><a href="/scene/list.action">场景管理</a></li>
     	<#---- <li><a href="statis.html">统计分析</a></li>------------>
     
      	<li><a href="/productApp/class/list.action">产品分类2</a></li>
      	<li><a href="/cmsSolution/class/list.action">系统分类</a></li>
      	<li><a href="/cmsSolution/sub/list.action">系统发布</a></li>
      	
     
     </ul>
    </div>
   </div>
   
   <#--主体内容-->
   ${body}
   
  </div>
 </div>
 
</body>
</html>
