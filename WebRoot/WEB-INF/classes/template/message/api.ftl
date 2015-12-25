<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>loiot</title>
<link href="http://www.loiot.com/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function goTopEx(){
        var obj=document.getElementById("api_left");
        function getScrollTop(){
                return document.documentElement.scrollTop;
            }
        function setScrollTop(value){
                document.documentElement.scrollTop=value;
            }    
        window.onscroll=function(){getScrollTop()>133?obj.className='api_left api_leftfixed':obj.className='api_left';}
        obj.onclick=function(){
            var goTop=setInterval(scrollMove,10);
            function scrollMove(){
                    setScrollTop(getScrollTop()/1.1);
                    if(getScrollTop()<1)clearInterval(goTop);
                }
        }
    }
</script>
</head>
<body>
<div class="w_960 rel">

<!-- 页头 开始 -->
<div class="header">
  <div class="logo"><a href="http://www.loiot.com/"><img src="http://www.loiot.com/images/logo.gif" alt="wolot" title="返回首页" /></a></div>
  <div class="left"><img src="http://www.loiot.com/images/logo_text.gif" alt="物联网 开放平台" /></div>
  <div class="right">
    <ul class="menu">
	  <li><a href="http://www.loiot.com/">首页</a></li>
	  <li><a href="http://www.loiot.com/platform.html">平台介绍</a></li>
	  <li><a href="http://www.loiot.com/appcenter.html">应用中心</a></li>
      <li><a href="http://www.loiot.com/phone.html">手机下载</a></li>
      <li><a href="http://www.loiot.com/help.html">帮助中心</a></li>
    </ul>
    <div class="status">欢迎光临物联网开放平台！ [<a href="http://www.loiot.com/web/user/toLogin.do">登录</a>] [<a href="http://www.loiot.com/web/user/toReg.do">免费注册</a>]</div>
  </div>
</div>
<!-- 页头 结束 -->

  <!-- 左侧 开始 -->
  <div class="api_left" id="api_left">
    <h1>api文档</h1>
    <#list oneList as one>
    	<h2><i class="jian"></i><a href="#"  id="1${one.id}" class="goDown">${one.name}</a></h2>
		    	  	<div class="w w1">
		    	  		<#list one.apiMenuSecond as second>
						      <h3><i class="jian"></i><a href="#" id="2${second.id}" class="goDown">${second.name}</a></h3>
							      <div class="w w11">
							             <#list second.apiMenuThree as three>
							       			 <h4><a href="#" id="3${three.id}" class="goDown">${three.name}</a></h4>
							       		 </#list>	 
							      </div>
				      </#list>
				   </div>
    </#list>
  </div>
  <script type="text/javascript">goTopEx();</script>
  <!-- 左侧 结束 -->
  <!-- 右侧 开始 -->
  <div class="api_right">
  <#list oneList as one>
    <h2 class="1${one.id}">${one.name!''}</h2>
   <#list one.apiMenuSecond as second>
    <div class="h3" id="menu11">
      <h3 class="2${second.id}">${second.name!''}</h3>
       ${second.desc}
      <!-- 节点输入到中间件 开始 -->
      <div class="h4" id="menu111">
  			  <#if second.apiMenuThree??>
					     <#list second.apiMenuThree as three>
					       			 <h4 class="3${three.id}">${three.name!''}</h4>
        										<#if three.apiBody??>
        													 ${three.desc}
													         <table class="table">
													          <tbody>
													            <tr>
													              <td>URL</td>
													              <td>${three.apiBody.url!''}</td>
													            </tr>
													            <tr>
													              <td>Format</td>
													              <td>${three.apiBody.formart!''}</td>
													            </tr>
													            <tr>
													              <td>Method</td>
													              <td>${three.apiBody.method!''}</td>
													            </tr>
													             <tr>
													              <td>Header</td>
													              <td>${three.apiBody.header!''}</td>
													            </tr>
													          </tbody>
													        </table>
        
        			          				<#if three.apiBody.params?? && (three.apiBody.params?size>0) >  
													        <strong>Parameters</strong>
													        <table class="table">
													          <tbody>
													            <tr>
													              <th>属性名称</th>
													              <th>类型</th>
													              <th>是否必填</th>
													              <th>备注</th>
													            </tr>
													           		 <#if three.apiBody??>
																             <#list three.apiBody.params as param>
																		         <tr>
																		              <td>${param.propertyName!''}</td>
																		              <td>${param.propertyType!''}</td>
																		              <td>${param.allowNull!''}</td>
																		              <td>${param.remark!''}</td>
																		         </tr>
																             </#list>
																     </#if>        
													          </tbody>
													        </table>
       									 </#if>
        
      
														        <strong>返回参数</strong>
														         ${three.apiBody.resultsetContent!''}
														         <strong>调用示例</strong>
																 <p>${three.apiBody.callExample!''}</p>
			          													
			          													<#if three.apiBody.errCodes?? && (three.apiBody.errCodes?size>0) >  
			          															 <strong>错误码</strong>
																			        <table class="table">
																			          <tbody>
																			            <tr>
																			              <th>错误code</th>
																			              <th>错误描述</th>
																			            </tr>
																			            <#list three.apiBody.errCodes as code>
																           	               <tr>
																		                       <td>${code.code!''}</td>
																		                        <td>${code.codeDesc!''}</td>
																		                   </tr>
																           </#list> 
																			          </tbody>
																			        </table>  
			          													</#if>
			          											
         
      
      												</#if>
     
    						</#list>
     
     		 </#if>
      </div> 
      <!-- 节点输入到中间件 结束 -->
    </div>
    <!-- _____中间件数据上行 结束_____ -->
    </#list>
    
   </#list>
     
     
  </div>
  <!-- 右侧 结束 -->
  <!-- 页脚 开始 -->
<div class="footer u">
  <div> 北京时代凌宇科技有限公司京ICP备10042645号 Copyright-2007 Loit CO LTD. all rights reserved <br />
    <a href="/about_us.html">关于我们</a>|<a href="/app/appIndex.do">应用开发平台</a>|<a href="/about_address.html">联系我们</a>|<a href="/terms.html">服务条款</a>|<a href="/help.html">帮助中心</a></div>
</div>
<!-- 页脚 结束 -->
<script type="text/javascript" src="http://www.loiot.com/js/Component/jquery.js"></script>
<script type="text/javascript" src="http://www.loiot.com/js/index.js"></script>
</div>

		  <script>
					 function openMenu(obj){
							    var a =  $("#"+obj).parent().next().is("div");
							    if(a) {
							    			var iObj =  $("#"+obj).parent().find("i").get(0);
							    			var className = iObj.className;
							    			if(className=="jian"){
							    				  $("#"+obj).parent().find("i").removeClass('jian');
							    				 $("#"+obj).parent().next().hide();
							    			} else {
							    				   $("#"+obj).parent().find("i").addClass('jian');
							    				  $("#"+obj).parent().next().show();
							    			}
							    }
							  }
		  
		  </script>
  
<script type="text/javascript"> 
jQuery.fn.anchorGoWhere = function(options){
        
        var obj = jQuery(this);
        var defaults = {target:1, timer:1000};
        var o = jQuery.extend(defaults,options);
        obj.each(function(i){
            jQuery(obj[i]).click(function(){
                var _rel = jQuery(this).attr("id");
                switch(o.target){
                    case 1:
                        var targetTop = jQuery("."+_rel).offset().top;
                        jQuery("html,body").animate({scrollTop:targetTop}, o.timer);
                        break;
                    case 2:
                        var targetLeft = jQuery("."+_rel).offset().left;
                        jQuery("html,body").animate({scrollLeft:targetLeft}, o.timer);
                        break;
                }
                return false;
            });
        });
    };
	$(document).ready(function(){
        $(".goDown").anchorGoWhere({target:1});
         $(".goDown").click(function () {
         	openMenu(this.id);
         });
    });
</script>
</body>
</html>
