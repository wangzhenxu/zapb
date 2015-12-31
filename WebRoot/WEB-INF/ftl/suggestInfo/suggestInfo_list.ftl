

  	<#include "../include/comm_jlb_macro.ftl"/>
<html>
	<head>
	<title>网站后台管理系统-${pageTitle!''}管理</title>
	<@gmc_common_js "select" />
</head>
	<body>
	<div class="right">
    <div class="location">
     <div class="location01">您现在的位置是：首页 &gt; <strong class="m_title"></strong></div>
    </div>
    <div class="sort">
     <div class="sort1 m_title" ></div>
     <div class="query">
  	
	<form id="queryForm" >
	<ul>
			 <li style="width:22%">
		       	<span class="classify">公司名称：</span>
		    	<input name="name" type="text"   class="input"  id="name" value=""/>
		      </li>
			       <li style="width:15%">
			       	<span class="classify">录入人：</span>
			    	<select id="inPerson" name="inPerson">
			    		 <option value="" > 请选择 </option>
			    		    <#list DictionaryUtil.getTypes(DictionaryType.COMPANY_INDUSTRY.getCode()) as c>
			    		 		 <option value="${c.dictionaryId}" <#if  inPerson?? && inPerson!="" > <#if inPerson?number==c.dictionaryId> selected </#if> </#if>  > ${c.showName!''} </option>
			 			 	</#list>
			    	</select>
			       </li>
		    <li style="width:5%"><a href="javascript:void(0)">
  		 		<button type="button" class="btn btn-default" id="queryBtn" >查&nbsp;询</button>
       		</li>
      </ul>
      
  </form>
     </div>
    </div>
    <div class="form">
     
     <div class="form2">
     
<table width="100%"  border="1" align="left" cellpadding="0" cellspacing="0" bordercolor="#ffffff" style="border-collapse:collapse">
      <tr class="lan">
      
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>用户类型</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>产品</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>标题</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>操作类型</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>建议人</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>录入时间</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>操 作</strong></td>
       </tr>
       <#list pager.data as c>
       <tr>
       
		  <td align="center" class="hui">
		   <#if c.accountType??>
        		 ${AccountType.get(c.accountType).getTitle()}
           </#if>
		  </td>
		  <td align="center" class="hui">
		   <#if c.projectType??>
        		 ${ProjectType.get(c.projectType).getTitle()}
           </#if>
           
		  </td>
		  <td align="center" class="hui">${c.title!''}</td>
		  <td align="center" class="hui">${c.operationType!''}</td>
	 	  <td align="center" class="hui">
	 	  <#if c.inPersonName??>
	 	    	${c.inPersonName}
	 	  </#if>
	 	   </td>
		 <td align="center" class="hui">
    			 <#if c.inTime??>
	        	 	 ${c.inTime?string("yyyy-MM-dd")}
	 		 	 </#if>
	 	 </td>
        
		 <td align="center" class="hui" style="width:300px;"  >
	       		<div class="btn-group">
      			<#if subject.isPermitted("zpJlInfo:list")>   <#--tgSuggestBugInfo:detail  -->
				  <button type="button" class="btn btn-default"  onclick="suggestInfo.toDetail('${c.suggestId}')">详情</button>
				 </#if>
				 
      			<#if subject.isPermitted("zpJlInfo:list")>  <#--tgSuggestBugInfo:edit  -->
				  <button type="button" class="btn btn-default"  onclick="suggestInfo.toEdit('${c.suggestId}','${SuggestType.SUGGEST.getCode()}')">修改</button>
				 </#if>
				 
	      		</div>
        </td>
       </tr>
       </#list>
       <tr>
     	 <td colspan="10" valign="middle" class="d">
     	 
      			<#if subject.isPermitted("zpJlInfo:list")>  <#--tgSuggestBugInfo:delete  -->
     	 	 <div class="btn-group" style="display:none;">
			   <button type="button" class="btn btn-default"  onclick="javascipt:void(0);">删除</button>
      	 	 </div>
				</#if>
     	 	
     	 </td>
       </tr>
      </table>
     
     </div>
     <@pageBar   pager=pager url="/tgSuggestBugInfo/suggestList.action?jsonParam=${jsonParam!''}" join="&"> </@pageBar> 
    
    </div>
   </div>
  <!-- 弹窗 结束 -->
	<#include "../include/deleteConfirmModal.ftl">
    <script src="/js/suggestInfo.js"></script>
    <script>
		suggestInfo.initPage();
	</script>
</html>



