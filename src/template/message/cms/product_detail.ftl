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
<div class="w1682">
  <div class="w_960"><img src="http://www.loiot.com/images/1680_2.jpg" /></div>
</div>
<div class="w_960">
  <!-- 左侧 开始 -->
  <#include '/product_left.ftl'>
  <!-- 左侧 结束 -->
  <!-- 右侧 开始 -->
  <div class="w_724">
    <div class="location">
      <div class="text">您现在的位置：<a href="/">首页</a><span>&gt;</span><strong>${po.name!''}</strong></div>
    </div>
    <div class="wrap detail">
      <div class="img"><img height="200" width="200" src="${po.bigPic!''}" /></div>
      <div class="intro">
        <h1>${po.name!''}</h1>
        <p>
            ${po.recommend!''}
        </p>
        <div class="left">产品价格：<strong class="red">
      		  <#if po.price??>
                     	￥<b>${po.price}</b>
                    <#else>
                    <b>暂无定价</b>
               </#if>      
         </strong>
          <p class="red">预购从速，立即拨打010-82933988-8813</p>
        </div>
        <div class="right">
        	<#if po.docUrl??>
        		<a href="${po.docUrl!''}"><img src="http://www.loiot.com/images/1680_20.png" /></a>
        	 </#if>   	
        </div>
      </div>
      <div class="tab">
        <ul>
          <li id="Tab_b1" onmousedown="BLi(1)" class="show"><span>产品描述</span></li>
          <li id="Tab_b2" onmousedown="BLi(2)"><span>性能指标</span></li>
          <li id="Tab_b3" onmousedown="BLi(3)"><span>安装说明</span></li>
          <li style='display:none;' id="Tab_b4" onmousedown="BLi(4)"><span>安装说明</span></li>
          
        </ul>
      </div>
      <div class="c" id="Tab_bc01">
        ${po.PDesc!''}
      </div>
      <div class="c undis" id="Tab_bc02">
      	${po.techPara!''}
      </div>
      <div class="c undis" id="Tab_bc03">
      	${po.install!''}
      </div>
      <div class="c undis" id="Tab_bc04">
        
 	 </div>
  <!-- 右侧 结束 -->
  </div></div>
  <div class="clear442"></div>
  <#include '/footer.ftl' >
</div>
<!-- 内容 结束 -->
</body>
</html>
