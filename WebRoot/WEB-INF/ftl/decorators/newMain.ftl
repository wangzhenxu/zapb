<#setting number_format="#">

<#assign menuClass=menuClass!"editPassword">

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
<#include "../include/common.ftl"/>
${head}
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script LANGUAGE="javascript">
$(function(){
	$(".MenuLevel1").click(function(){
		//清空样式
		$(".MenuLevel1 span").empty().append('<img src="/images/2.gif" border="0"/>');
		$(".SubMenu").addClass("SubMenuLayerHidden");
		//选中菜单
		$(this).find("span").empty().append('<img src="/images/1.gif" border="0"/>');
		$(this).next().removeClass("SubMenuLayerHidden");
	});
	//location.href="/zpEmalTemplate/list.action"
});
function menuClick(){
}
</script>
</head>
<body>
 <div id="contant">
  <div class="head"><img src="/images/erji_01.jpg" /></div>
  <div class="position">
   <div class="position01">欢迎您,<span class="org">${Session[Const.SESSION_USER_KEY].username}</span>,今天是${.now}</div>
   <div class="position02"><img src="/images/hy_06.jpg" style="vertical-align:middle"/>&nbsp;<a href="/logout.action" id="logoutBtn">退出登录</a></div>
  </div>
  
  <div class="main">    
   <#--菜单内容-->
	<div class="sinbar">
	<div class="sinbar01">菜单栏</div>
    <div class="left_menu">
     <!-- 帐号管理 -->
     <#assign d=menuClass!="accountRole"&&menuClass!="accountManage"&&menuClass!="editPassword"&&menuClass!="customerManage"&&menuClass!="projectManage" &&menuClass!="customerAccountRole" >
     
     
      <#if subject.isPermitted("zpCompanyInfo:list") ||subject.isPermitted("zpCompanyJobInfo:list")>
     	<!-- 客户管理 -->
     	<div class="MenuLevel1"> 客户管理 <span><img src="/images/<#if d>2<#else>1</#if>.gif" border="0"/></span></div>
     		<div class="<#if d>SubMenuLayerHidden</#if> SubMenu">
     		 <#if subject.isPermitted("zpCompanyInfo:list")>
      			<a class="MenuLevel4 editPassword" href="/zpCompanyInfo/list.action"> <img src="/images/bullet_sarrow.gif"/> &nbsp;&nbsp;<span id="zpCompanyInfo_list">客户管理</span></a>
      		</#if>
     	</div>
     </#if>
     
     
     	 <!-- 用户建议管理 -->
     	<div class="MenuLevel1"> 用户建议管理 <span><img src="/images/<#if d>2<#else>1</#if>.gif" border="0"/></span></div>
     		<div class="<#if d>SubMenuLayerHidden</#if> SubMenu">
      			<a class="MenuLevel4 editPassword" href="/tgSuggestBugInfo/suggestList.action"> <img src="/images/bullet_sarrow.gif"/> &nbsp;&nbsp;<span id="tgSuggestBugInfo_suggestList">用户建议管理</span></a>
     	</div>
     	
     	 <!-- 用户bug管理 -->
     	<div class="MenuLevel1"> 用户bug管理 <span><img src="/images/<#if d>2<#else>1</#if>.gif" border="0"/></span></div>
     		<div class="<#if d>SubMenuLayerHidden</#if> SubMenu">
      			<a class="MenuLevel4 editPassword" href="/tgSuggestBugInfo/bugList.action"> <img src="/images/bullet_sarrow.gif"/> &nbsp;&nbsp;<span id="tgSuggestBugInfo_bugList">用户bug管理</span></a>
     	</div>
    
     
    </div>
	</div>
   <#--主体内容-->
   ${body}
   
  </div>
 </div>
 <div class="foot" style="display:none;" >
   <div class="footer"  >北京璟仪科技有限公司京ICP备10042645号 Copyright-2015 Loit CO LTD. all rights reserved</div>
  </div>
  
  <script>
  
   $('input, textarea').placeholder();
  
  //停止操作
  function disableOperator(){
     $(".left_menu").find("a").each(function(){
  	 	if($(this).find("span").attr("id")!="accountExpandInfo_list"){
  	  	 	$(this).attr("href","javascript:void(0)");
  	   		$(this).click(function (){
  	   		common.alert("请先填写个人信息");
  	   		return;
  	   		});
  		 }
  		});
  }
  <#if  Session[Const.SESSION_USER_KEY].iphone?? && Session[Const.SESSION_USER_KEY].iphone?length gt 0  >
  	<#elseif Session[Const.SESSION_USER_KEY].type!=AccountType.ADMIN.getCode()>
  		disableOperator();
  </#if>
  </script>
</body>
</html>

