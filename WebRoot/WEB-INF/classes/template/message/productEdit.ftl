<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网站后台管理系统-产品管理-增加产品</title>
<script LANGUAGE="javascript">
$(document).ready(function(){
   $("#product").validationEngine({validateAttribute: "validate",validationEventTriggers:"keyup blur"}); 
     });
var samllPicbool=true;
function update(){	
if(samllPicbool){
$("#product").submit();
}else{
	$("#product").find("input").trigger("blur");
	$("#product").find("textarea").trigger("blur");
	$.validationEngine.buildPrompt('#ui-upload-holder','请上传大小在1M以内的图片','error');
}
}
$(function(){
	$.each($(".showType input"),function(){
		if(this.value=="${(product.showType)!""}"){
			$(this).attr("checked",true);
		}
	})
})
</script>
</head>
<!--[if IE 6]>
<script type="text/javascript" src="http://axiu.me/wp-content/themes/Summ/js/DD_belatedPNG.js" ></script>
 
<script type="text/javascript">
DD_belatedPNG.fix('.jiben');
</script>
<![endif]-->
<script  language="javascript">	
<!--
var subMenu = new Array(3);
subMenu[0] = 'undefine';
subMenu[1] = 'undefine';
subMenu[2] = 'undefine';
subMenu[3] = 'undefine';
subMenu[4] = 'undefine';
function gotoURL(szURL){
 window.open(szURL,'Main','') ;
}
//-->
</script>
<body>
     <div class="query1">
      <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#dddddd" style=" border-collapse:collapse;">
       <tr>
         <td width="15%" align="right" class="hui1"><span class="red">*</span> 产品ID：<input name="product.productId" type="hidden"  class="input" value="${product.productId}"/>
         <input name="actionType" type="hidden"  class="input" value="2"/>
         <input name="product.hasKey" type="hidden"  class="input" value="${product.hasKey}"/>
         <input name="product.picUrl" type="hidden"  class="input" value="${(product.picUrl)!""}"/>
         </td>
         <td width="85%" align="left" valign="middle"><input name="product.snPrefix" id="product_snPrefix" type="text"  class="inputa" value="${product.snPrefix!0}"  validate="validate[required,custom[eightCha]]"/></td>
         </tr>
       <tr>
         <td align="right" class="hui1"><span class="red">*</span> 产品分类：</td>
         <td align="left" valign="middle"><select name="product.pcId" id="select2" class="inputa">
         <#list classes as class>
           <option  <#if class.pcId==product.productClazz.pcId>selected="selected"</#if>  value=${class.pcId}>${class.name}</option>  
           </#list>
         </select></td>
         </tr>
       <tr>
         <td align="right" valign="top" class="hui1"><span class="red">*</span> 产品名称：</td>
         <td align="left" valign="middle"><input name="product.name" id="product_name" type="text" value="${product.name!""}"  class="inputa" validate="validate[required,length[1,20]]"/></td>
         </tr>
       <tr>
         <td align="right" valign="top" class="hui1"><span class="red">*</span> 产品类型：</td>
         <td align="left" valign="middle">
          <#if product.hasKey==1>
           <input type="radio" name="product.productType" id="radio"  value="1"  <#if product.productType==1>checked="true"</#if>  style=" vertical-align:middle"/>
           <span class="hui"> 网关</span>&nbsp;&nbsp;
           <input type="radio" name="product.productType" id="radio"  value="2"  <#if product.productType==2>checked="true"</#if>  style=" vertical-align:middle"/>
           <span class="hui"> 中间件</span>
           <input type="radio" name="product.productType" id="radio"  value="4" <#if product.productType==4>checked="true"</#if>  style=" vertical-align:middle"/>
           <span class="hui">读写器</span>
           <#else>
           <input type="radio" name="product.productType" id="radio"  value="3"  <#if product.productType==3>checked="true"</#if>  style=" vertical-align:middle"/>
           <span class="hui"> 节点</span>&nbsp;&nbsp;
           <input type="radio" name="product.productType" id="radio"  value="5"  <#if product.productType==5>checked="true"</#if>  style=" vertical-align:middle"/>
           <span class="hui">RFID</span>
           </#if>
         </tr>
       <tr>
         <#if (product.showType)??>
       <tr class="showType">
         <th><span class="red songti em05">*</span>展示类型：</th>
         <td><input type="radio" name="product.showType" id="radio" checked="true"  value="1"  style=" vertical-align:middle"/>
           <span class="hui"> 曲线图</span>&nbsp;&nbsp;
           <input type="radio" name="product.showType" id="radio"  value="2"  style=" vertical-align:middle"/>
           <span class="hui"> 锯齿图</span><input type="radio" name="product.showType" id="radio"  value="3"  style=" vertical-align:middle"/>
           <span class="hui"> 图像</span>&nbsp;&nbsp;
           <input type="radio" name="product.showType" id="radio"  value="4"  style=" vertical-align:middle"/>
           <span class="hui"> highstock</span>&nbsp;&nbsp;
           <input type="radio" name="product.showType" id="radio"  value="5"  style=" vertical-align:middle"/>
           <span class="hui"> 视频</span></td>
         </tr>  
         </#if>        
         <td align="right" valign="top" class="hui1"><span class="red">*</span> 提供商：</td>
         <td align="left" valign="middle">${product.customerName!""}</td>
         </tr>
       <tr>
         <td align="right" valign="top" class="hui1">产品描述：</td>
         <td align="left" valign="middle"><textarea name="product.description" id="textarea" cols="45" rows="5" class="texta">${product.description!""}</textarea></td>
         </tr>
       <tr>
         <td align="right" valign="top" class="hui1"><span class="red">*</span> 标签TAGS：</td>
         <td align="left" valign="middle">
         <textarea name="tag.tagWord" id="textarea" cols="45" rows="5" class="texta" validate="validate[required,length[1,20]]"><#list product.tagList as tag>${tag.tagWord!""}<#if tag_has_next>,</#if></#list></textarea><span class="lan1">例如：温度，湿度，红外监控等可以逗号、空格、回车的形式分隔开。</span></td>
         </tr>
       <tr>
         <td align="right" valign="top" class="hui1"><span class="red">*</span>系统图片：</td>
         <td valign="top" class="hui"><div class="uploadlay">
          
           <div id="ui-upload-holder">
             <div id="ui-upload-txt">浏览</div>
            <input type="file" id="ui-upload-input" class="ui-upload-input" name="ui-upload-input" onchange="onUploadImgChange(this)"/>
             </div>
         </div></td>
         </tr>
       <tr>
         <td align="right" valign="top" class="hui1">&nbsp;</td>
         <td align="left" valign="middle" class="hui">图片类型：JPG、GIF、PNG，文件大小：1M以内。</td>
         </tr>
       <tr>
         <td align="right" valign="top" class="hui1">&nbsp;</td>
         <td align="left" class="hui1">
           <div class="kuang"  width="150px" height="150px"> 
           <div id="preview_wrapper" width="150px" height="150px"> 
	        	<div id="preview_fake" width="150px" height="150px"> 
	            	<img id="preview" src="${UPLOAD_PIC_URL}${product.picUrl!""}" width="150px" height="150px"  onload="onPreviewLoad(this)"/> 
	        	</div> 
    		</div> 
    		<img id="preview_size_fake" width="150px" height="150px"/></div>
           <div class="chicun">150*150</div>
         </td>
         </tr>
          <#if product.hasKey==1>
       <tr>
         <td align="right" valign="top" class="hui1">共享设置：</td>
          <td align="left" class="hui">对于网关默认接入时公开数据 
           <input type="radio" name="product.isPublic" id="radio6" value="1"  style=" vertical-align:middle" <#if product.isPublic==1>checked="checked"</#if>/>
                                        公开&nbsp;&nbsp;
          <input name="product.isPublic" type="radio" id="radio7"  style=" vertical-align:middle" value="0"  <#if product.isPublic==0>checked="checked"</#if>/>
                                       不公开</td>
       </tr>
       </#if>
       </table>
    </div>
      <div class="baocun"><a href="javascript:void(0);" onclick="update();"><img src="/images/bjwj03.jpg" width="62" height="23" /></a> &nbsp;<a href="/product/management/toupdate.action?productId=${product.productId}"><img src="/images/bjwj_05.jpg" width="62" height="23" /></a></div>
</body>
</html>
