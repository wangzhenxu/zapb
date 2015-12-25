<#--
分页器
pageIndex 当前页
pageCount 总页数
url url地址
join url地址与分页参数的连接字符,一般是?或是&
-->
<#macro ajaxPageBar pager url join elementId>
	
	<#-- 获取参数 -->	
	<#assign pageIndex=pager.pageIndex pageCount=pager.pageCount/>

	<div class="page">
		<ul>
<!--            <li class="c000">共10条</li> -->
			<#-- 总页数<=0,直接生成不可点击的页码 -->
		 	<#if pageCount<=1>
		 		<li>上一页</li>
	            <li class="show0">1</li>
	            <li>下一页</li>
		 	<#else>
		 	
		 		<#-- 判断是否是第一页 -->
		 		<#if pageIndex = 0>
	           		<li>上一页</li>
			 	<#else>
	            	<li><a class="ajaxPagerBtn" href="${url}${join}pi=${pageIndex-1}">上一页</a></li>
			 	</#if>
			 	
		 		<#-- 页码 -->
		 		<@pageGeneration start=1 end=pageCount pageIndex=pageIndex url=url join=join></@pageGeneration>
			 	
		 		<#-- 判断是否是最后一页 -->
		 		<#if pageIndex = pageCount-1>
	           		<li>下一页</li>
			 	<#else>
	            	<li><a class="ajaxPagerBtn" href="${url}${join}pi=${pageIndex+1}">下一页</a></li>
			 	</#if>
			 	
		 	</#if>
            <li class="c000">跳至
              <input type="text" class="pager_num">
              页</li>
            <li class="total"><a class="ajaxPagerBtn go" href="${url}${join}pi=">go</a></li>
	 	</ul>
	 </div>
	 
	 <script>
	 	$(function(){
	 		$(".ajaxPagerBtn").click(function(){
	 			var url=$(this).attr("href");
	 			if($(this).hasClass("go")){
	 				url+=($(".page .pager_num").val()-1);
	 			}
	 			$.get(url,function(data){
	 				$("#${elementId}").html(data);		
	 			});
	 			return false;
	 		});
	 	});
	 </script>
</#macro>

<#--
这个宏用于生成分页码.
start 起始页码
end 结束页码
pageIndex 当前页
url url地址
join url地址与分页参数的连接符,一般是?或是&
-->
<#macro pageGeneration start end pageIndex url join>
 	<#list start..end as i>
 		<#if pageIndex = i-1>
            <li class="show0">${i}</li>
 		<#else>
       		<li><a class="ajaxPagerBtn" href="${url}${join}pi=${i-1}">${i}</a></li>
 		</#if>
 	</#list>
</#macro>