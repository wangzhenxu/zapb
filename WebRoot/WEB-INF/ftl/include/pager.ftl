

<#--
分页器
pageIndex 当前页
pageCount 总页数
url url地址
join url地址与分页参数的连接字符,一般是?或是&
-->
<#macro pageBar pager url join >
	<#-- 获取参数 -->	
	<#assign pageIndex=pager.pageIndex pageCount=pager.pageCount totalResults=pager.totalResults/>
    
	<div class="manu">
	共为您找到相关结果${totalResults}个
		<#-- 总页数<=0,直接生成不可点击的页码 -->
	 	<#if pageCount<=1>
		 	<span class="disabled"> 上一页 </span>
		 	<span class="current">1</span>
		 	<span class="disabled"> 下一页 </span>
	 	<#else>
	 	
	 		<#-- 判断是否是第一页 -->
	 		<#if pageIndex = 0>
		 		<span class="disabled"> 上一页 </span>
		 	<#else>
		 		<a href='${url}${join}pi=${pageIndex-1}'> 上一页 </a>
		 	</#if>
		 	
	 		<#-- 页码 -->
	 		<@pageGeneration start=1 end=pageCount pageIndex=pageIndex url=url join=join></@pageGeneration>
		 	
	 		<#-- 判断是否是最后一页 -->
	 		<#if pageIndex = pageCount-1>
		 		<span class="disabled"> 下一页 </span>
		 	<#else>
		 		<a href='${url}${join}pi=${pageIndex+1}'>下一页 </a>
		 	</#if>
		 	
	 	</#if>
	 </div>
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
 			<span class="current">${i}</span>
 		<#else>
 			<a href='${url}${join}pi=${i-1}'>${i}</a>
 		</#if>
 	</#list>
</#macro>