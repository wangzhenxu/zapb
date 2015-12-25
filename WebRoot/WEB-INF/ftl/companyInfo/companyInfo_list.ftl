<html>
	<head>
	<title>网站后台管理系统-客户管理</title>
	<#include "../include/bootstrap.ftl"/>
	<#include "../include/pager.ftl">
	<script src="/js/source/jquery.validationEngine.js"></script>
	<script src="/js/source/jquery.validationEngine.min.js"></script>
	<link href="/js/source/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
    <script src="/js/hiAlert/jquery.alert.js"></script>
    <script type="text/javascript" src="http://www.loiot.com/c/ckeditor/ckeditor.js"></script>			
	<link href="/css/alert.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		body {
			background-image: url(/images/erji_22.jpg);
			background-repeat: repeat-x;
			}
	</style>
</head>
	<body>
	<div class="right">
    <div class="location">
     <div class="location01">您现在的位置是：首页 &gt; <strong>客户管理</strong></div>
    </div>
    <div class="sort">
     <div class="sort1">客户管理</div>
     <div class="query">
 		<form id="queryForm" >
      <ul>
       <li style="width:22%">
       	<span class="classify">公司名称：</span>
    	<input name="name" type="text"  class="input"  id="name" value="${name!''}"/>
       </li>
       <li style="width:15%">
       	<span class="classify">所属行业：</span>
    	<select id="industryId" name="industryId">
    		 <option value="" > 请选择 </option>
    		 <#list DictionaryUtil.getTypes(DictionaryType.COMPANY_INDUSTRY.getCode()) as c>
    		 	<option value="${c.dictionaryId}" <#if industryId??> <#if industryId==c.dictionaryId?string> selected </#if> </#if>> ${c.showName!''} </option>
 			 </#list>
    	</select>
       </li>
       <li style="width:20%">
       	<span class="classify">公司性质：</span>
    	<select id="companyNature" name="companyNature">
    		 <option value="" > 请选择 </option>
    		 <#list DictionaryUtil.getTypes(DictionaryType.COMPANY_NATURE.getCode()) as c>
    		 	<option value="${c.dictionaryId}" <#if companyNature??> <#if companyNature==c.dictionaryId?string> selected </#if> </#if> > ${c.showName!''} </option>
 			 </#list>
    	</select>
       </li>
        <li style="width:20%">
       	<span class="classify">公司规模：</span>
    	<select id="scaleId" name="scaleId">
    		 <option value="" > 请选择 </option>
    		 <#list DictionaryUtil.getTypes(DictionaryType.COMPANY_SCALE.getCode()) as c>
    		 	<option value="${c.dictionaryId}" <#if scaleId??> <#if scaleId==c.dictionaryId?string> selected </#if> </#if> > ${c.showName!''} </option>
 			 </#list>
    	</select>
       </li>
        <li style="width:15%">
       	<span class="classify">融资阶段：</span>
    	<select id="financingLevelId" name="financingLevelId">
    		 <option value="" > 请选择 </option>
    		 <#list DictionaryUtil.getTypes(DictionaryType.COMPANY_FINANCING_LEVEL.getCode()) as c>
    		 	<option value="${c.dictionaryId}" <#if financingLevelId??> <#if financingLevelId==c.dictionaryId?string> selected </#if> </#if> > ${c.showName!''} </option>
 			 </#list>
    	</select>
       </li>
       
       
       <li style="width:5%">
   		 <button type="button" class="btn btn-default" onclick="companyInfo.query();">查&nbsp;询</button>
   	   </li>
      </ul>
      <ul>
      	<li style="display:none;">
	      	<span class="classify">所在城区：</span>
	    	<select id="areaId" name="areaId" style="width: 160px;">
	    		 <option value="" > 请选择 </option>
	    		 <#list DictionaryUtil.getTypes(DictionaryType.COMPANY_AREA.getCode()) as c>
	    		 	<option value="${c.dictionaryId}" <#if areaId??> <#if areaId==c.dictionaryId?string> selected </#if> </#if> > ${c.showName!''} </option>
	 			 </#list>
	    	</select>
      	</li>
      </ul>
      </form>
     </div>
    </div>
    <div class="form">
      <#if subject.isPermitted("zpCompanyInfo:add")>
	      <div class="btn-group">
			  <button type="button" class="btn btn-default"  onclick="companyInfo.toAdd();">增加客户</button>
	      </div>
     </#if>
     <div class="form2">
     <table width="100%"  border="1" align="left" cellpadding="0" cellspacing="0" bordercolor="#ffffff" style="border-collapse:collapse">
      <tr class="lan">
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>公司名称</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>公司地点</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>所属行业</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>公司规模</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>公司性质</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>融资规模</strong></td>
        <td style="display:none;" height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>所在城区</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>录入时间</strong></td>
        <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>操 作</strong></td>
       </tr>
       <#list pager.data as c>
       <tr>
       <td align="center" class="hui" title="${c.name!''}">${StringUtil.truncate(c.name,14,"...")}</td>
        <td align="center" class="hui" title="${c.address!''}">${StringUtil.truncate(c.address,10,"...")}</td>
        <td align="center" class="hui">
      	  <#if c.industryId??> 	
        	${DictionaryUtil.getName(c.industryId)}<#else>无
      	  </#if>
        </td>
        <td align="center" class="hui">
    	 <#if c.scaleId??> 	
    		${DictionaryUtil.getName(c.scaleId)}<#else>无
  	 	 </#if>
        </td>
        <td align="center" class="hui">
        <#if c.companyNature??> 	
    		${DictionaryUtil.getName(c.companyNature)}<#else>无
  	 	 </#if>
        </td>
        <td align="center" class="hui">
        <#if c.financingLevelId??> 	
    		${DictionaryUtil.getName(c.financingLevelId)}<#else>无
  	 	 </#if>
        </td>
        
        <td align="center" class="hui" style="display:none;">
	         <#if c.areaId??>
	         	${DictionaryUtil.getName(c.areaId)}<#else>无
	 		</#if>
        </td>
        
         <td align="center" class="hui">
	         <#if c.lastUpdateTime??>
	         	${c.lastUpdateTime?string("yyyy-MM-dd")}
	 		</#if>
        </td>
        
        <td align="left" class="hui" style="width:400px;"  >
	       	<div class="btn-group">
			    <#if subject.isPermitted("zpCompanyInfo:detail")>
				  <button type="button" style="margin-left:15px;" class="btn btn-default"  onclick="companyInfo.toDetail('${c.companyId}')">详情</button>
				 </#if>
			<#if subject.isPermitted("zpCompanyInfo:edit")>
				  <button type="button" class="btn btn-default"  onclick="companyInfo.toEdit('${c.companyId}')">修改</button>
		     </#if>
		      <#if subject.isPermitted("zpCompanyInfo:disableAndEnabled")>
				  <#if c.isDelete??>
				  	<button type="button" class="btn btn-default"  onclick=companyInfo.modifyDeleteStatus('${c.companyId}','${c.isDelete!""}');>
				  	 	<#if c.isDelete==PauseStartType.START.getCode()> 暂停 </#if>
				  	 	<#if c.isDelete==PauseStartType.PAUSE.getCode()> 启用 </#if>
				  </button>	
				  </#if>
			</#if>
				
				<#if subject.isPermitted("zpCompanyJobInfo:add")>
				   <#if c.isDelete??>
				  	 	<#if c.isDelete==PauseStartType.START.getCode()> 
				  	 		<button type="button" class="btn btn-default"  onclick="companyInfo.toAddJob('${c.companyId}')">发布职位</button>
				  	 	 </#if>
				  </#if>
				</#if>
				
				<button type="button" class="btn btn-default"  onclick="companyInfo.toJobList('${c.name}')">管理职位</button>
				
	      	 </div>
      	</td>
       </tr>
       </#list>
       <tr>
     	 <td colspan="10" valign="middle" class="d">
 	 		<div class="btn-group" style="display:none;">
			  <button type="button" class="btn btn-default"  onclick="javascipt:void(0);">删除</button>
      	 	</div>
     	 </td>
       </tr>
      </table>
     </div>
	 <#-- 分页栏 -->
     <@pageBar pager=pager url="/zpCompanyInfo/list.action?jsonParam=${jsonParam!''}" join="&"></@pageBar>
    </div>
   </div>
  <!-- 弹窗 结束 -->
	<#include "../include/deleteConfirmModal.ftl">
	
    <script src="/js/companyInfo.js"></script>
     <script>
    		common.initLeftMenuSelected("zpCompanyInfo_list");
    </script>
</html>

