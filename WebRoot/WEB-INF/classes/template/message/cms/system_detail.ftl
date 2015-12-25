<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>loiot</title>
<link href="http://www.loiot.com/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="w_960">
<#include '/header.ftl'>
</div>
<!-- 内容 开始 -->
<div class="w1683">
  <div class="w_960"><img src="http://www.loiot.com/images/1680_3.jpg" /></div>
</div>
<div class="w_960">
  <!-- 左侧 开始 -->
  <#include 'sys_left.ftl'>
  <!-- 左侧 结束 -->
  <!-- 右侧 开始 -->
  <div class="w_724">
    <div class="location">
      <div class="text">您现在的位置：<a href="/">首页</a><span>&gt;</span><a href="${po.cscStaticUrl!'javascript:void(0)'}">${po.cscName!''}</a><span>&gt;</span><strong>${po.name!''}</strong></div>
    </div>
    <div class="wrap">
      <h2 class="left">${po.name!''}</h2>
      	<#if po.accessUrl?? && po.accessUrl!='' && po.accessUrl!=' '>
      		<a href="${po.accessUrl}" class="button">进入系统</a>
      	</#if>
      <p class="clear">
      	 ${po.desc!''}
      </p>
      
      
      <h3>系统示意图</h3>
      <div class="pic">
      	    ${po.arch!''}
      </div>
      <h3>系统功能/特点</h3>
      <p>
      		${po.func!''}
      </p>
      <h2 class="border">相关产品列表</h2>
        <if  po.relationList??>
	      <ul class="pro">
	  			<#list po.relationList as cat>
					 <li><a target="_blank" href="${cat.staticUrl!''}"><img src="${cat.smallPic!''}" /></a><br />
		         		 ${cat.paName!''}
		         	</li>
				 </#list>
	      </ul>
      </if>
    </div>
  </div>
  <!-- 右侧 结束 -->
  <div class="clear442"></div>
  <#include '/footer.ftl' >
</div>
<!-- 内容 结束 -->
</body>
</html>
