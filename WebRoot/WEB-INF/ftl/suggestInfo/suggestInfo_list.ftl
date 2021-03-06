

  	<#include "../include/comm_jlb_macro.ftl"/>
<html>
	<head>
	<title>网站后台管理系统-${pageTitle!''}管理</title>
	<@gmc_common_js "select" />
</head>
	<body>
	<div class="right">
    <div class="location">
     <div class="location01">您现在的位置是：首页 &gt;  用户建议管理 &gt;<strong class="m_title" > </strong></div>
    </div>
    <div class="sort">
     <div class="sort1 m_title" ></div>
     <div class="query">
  	
	<form id="queryForm" >
	<ul>
			 <li style="width:22%">
		       	<span class="classify">标题：</span>
		    	<input name="nameT" type="text"   class="input"  id="nameT" value="${nameT!''}"/>
		      </li>
			  
			  <li style="width:25%">
			       	<span class="classify">用户类型：</span>
			    	<select id="accountType" name="accountType">
			    		 <option value="" > 请选择 </option>
			    		      <#list PlatAccountType.values() as c>
          				 			<option value="${c.code}" <#if  accountType?? && accountType!=''> <#if accountType?number==c.code> selected </#if> </#if>  > ${c.title!''} </option>
			    	    	  </#list>
			    	</select>
			   </li>
			  
			  <li style="width:15%">
			       	<span class="classify">操作类型：</span>
			    	<select id="operationType" name="operationType">
			    		 <option value="" > 请选择 </option>
			    		    <#list DictionaryUtil.getTypes(DictionaryType.SUGGEST_OPERATOR_STATUS.getCode()) as c>
			    		 		 <option value="${c.dictionaryId}" <#if  operationType?? && operationType!="" > <#if operationType?number==c.dictionaryId> selected </#if> </#if>  > ${c.showName!''} </option>
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
      
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>建议人</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>用户类型</strong></td>
        
        <td style="display:none;" height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>产品</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>标题</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>建议时间</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>操作类型</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>更新人</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>更新时间</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>操 作</strong></td>
       </tr>
       <#list pager.data as c>
       <tr>
       
        <td align="center" class="hui">
	 	  	<#if c.inPersonName??>
	 	    	${c.inPersonName}
	 	  	</#if>
	 	  </td>
       
		  <td align="center" class="hui">
		   <#if c.accountType??>
        		 ${AccountType.get(c.accountType).getTitle()}
           </#if>
		  </td>
		  
		 
		  <td style="display:none;" align="center" class="hui">
		   <#if c.projectType??>
        		 ${ProjectType.get(c.projectType).getTitle()}
           </#if>
           
		  </td>
		  <td align="center" class="hui">${c.title!''}</td>
		  
		  
		  <td align="center" class="hui">
    			 <#if c.inTime??>
	        	 	 ${c.inTime?string("yyyy-MM-dd")}
	 		 	 </#if>
	 	 </td>
	 	 
		  <td align="center" class="hui">
			   <#if c.operationType??>
			      	${DictionaryUtil.getName(c.operationType)}
	           </#if>
		  </td>
	 	  
		 
        
        
        <td align="center" class="hui">
	 	  <#if c.updatePersonName??>
	 	    	${c.updatePersonName}
	 	  </#if>
	 	   </td>
		 <td align="center" class="hui">
    			 <#if c.updateTime??>
	        	 	 ${c.updateTime?string("yyyy-MM-dd")}
	 		 	 </#if>
	 	 </td>
	 	 
		 <td align="center" class="hui" style="width:300px;"  >
	       		<div class="btn-group">
      			<#if subject.isPermitted("accountExpandInfo:detail")>   <#--tgSuggestBugInfo:detail  -->
				  <button type="button" class="btn btn-default"  onclick="suggestInfo.toDetail('${c.suggestId}','${SuggestType.SUGGEST.getCode()}')">详情</button>
				 </#if>
				 
      			<#if subject.isPermitted("accountExpandInfo:detail")>  <#--tgSuggestBugInfo:edit  -->
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



