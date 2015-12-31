<#include "../include/comm_jlb_macro.ftl"/>
<@gmc_common_js "add" />
<form id="addform" name="form"  method="post">
<input type="hidden" name="suggestId" id="suggestId"  value="${pid!''}"  />
<input type="hidden" name="onlyName" id="onlyName"/>

<!-- 右侧 开始 -->
<div class="right">
    <div class="location">
     <div class="location01">您现在的位置是：首页 &gt; <a href="javascript:suggestInfo.tolist();">CMS发布管理</a> &gt; 系统发布 &gt;<strong class="m_title" > 添加建议</strong></div>
    </div>
    <div class="nav">
     <div class="basic">
	 <div class="basic01 m_title" >添加建议</div>
	</div>
     <div class="query1">
       <table width="100%" border="0" align="left">
         <tr>
           <td colspan="4" class="red">* 号为必填项</td>
         </tr>
         
     
    <tr>
           <td  align="right" class="hui1">用户类型 ：</td>
           <td  align="left" valign="middle">
          	
          	
           </td>
     </tr>
     
    <tr>
           <td  align="right" class="hui1"><span class="red">*</span>项目类型 ：</td>
           <td  align="left" valign="middle">
          	
          	
           </td>
     </tr>
     
    <tr>
           <td  align="right" class="hui1"><span class="red">*</span>当前访问的url ：</td>
           <td  align="left" valign="middle">
          			 <textarea  cols="45" rows="5" class="input validate[required,length[1000] text-input mokuainr ckeditor" name="currentUrl"  id="currentUrl" ></textarea>
          	
          	
           </td>
     </tr>
     
    <tr>
           <td  align="right" class="hui1"><span class="red">*</span>标题 ：</td>
           <td  align="left" valign="middle">
          	   			<input name="title" id="title" type="text" class="input  validate[required] ">
          	
          	
           </td>
     </tr>
     
    <tr>
           <td  align="right" class="hui1"><span class="red">*</span>内容 ：</td>
           <td  align="left" valign="middle">
          			 <textarea  cols="45" rows="5" class="input validate[required,length[1000] text-input mokuainr ckeditor" name="content"  id="content" ></textarea>
          	
          	
           </td>
     </tr>
     
    <tr>
           <td  align="right" class="hui1">用户所在地 ：</td>
           <td  align="left" valign="middle">
          	   			<input name="address" id="address" type="text" class="input ">
          	
          	
           </td>
     </tr>
     
    <tr>
           <td  align="right" class="hui1">浏览器类型和版本 ：</td>
           <td  align="left" valign="middle">
          	   			<input name="agent" id="agent" type="text" class="input ">
          	
          	
           </td>
     </tr>
     
    <tr>
           <td  align="right" class="hui1">用户使用的操作系统 ：</td>
           <td  align="left" valign="middle">
          			 <textarea  cols="45" rows="5" class="input validate[required,length[1000] text-input mokuainr ckeditor" name="userOs"  id="userOs" ></textarea>
          	
          	
           </td>
     </tr>
     
    <tr>
           <td  align="right" class="hui1">操作类型 ：</td>
           <td  align="left" valign="middle">
          	
          	
           </td>
     </tr>
     
    <tr>
           <td  align="right" class="hui1">录入人 ：</td>
           <td  align="left" valign="middle">
		 		 <#list DictionaryUtil.getTypes(DictionaryType.ENGLISH_LEVEL.getCode()) as c>
		          	 	<input class="radio" name="{column.columnNameLower}" type="radio" value="${c.dictionaryId}" > ${c.showName!''} 
	          	 </#list> 
          	
          	
           </td>
     </tr>
     
    <tr>
           <td  align="right" class="hui1">录入时间 ：</td>
           <td  align="left" valign="middle">
          	
          	   <input name="inTime" id="inTime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM'})" >
          	
           </td>
     </tr>
     
    <tr>
           <td  align="right" class="hui1">1 bug 2建议 ：</td>
           <td  align="left" valign="middle">
          	
          	
           </td>
     </tr>
         
         
         
        
         
         
         </table>

     </div>
    </div>
    <div class="anniu">
	  	<div class="btn-group">
	  			  <button type="button" class="btn btn-default" id="addBtn">保 &nbsp;存</button>
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		 <button type="button" class="btn btn-default" onclick="suggestInfo.tolist();">返&nbsp; 回</button>
      	</div>
    </div>
   </div>
<!-- 右侧 结束 -->
</form>
<#include "../include/deleteConfirmModal.ftl">
<script src="/js/suggestInfo.js"></script>
<script>
	suggestInfo.initPage();
</script>

