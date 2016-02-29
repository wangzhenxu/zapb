<#include "../include/comm_jlb_macro.ftl"/>
<@gmc_common_js "add" />
<form id="addform" name="form"  method="post">
<input type="hidden" name="suggestId" id="suggestId"  value="${pid!''}"  />
<input type="hidden" name="onlyName" id="onlyName"/>

<!-- 右侧 开始 -->
<div class="right">
    <div class="location">
     <div class="location01">您现在的位置是：首页 &gt; <a href="javascript:suggestInfo.tolist();">用户bug管理</a> &gt;<strong class="m_title" > 添加建议</strong></div>
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
           
            <#list PlatAccountType.values() as c>
              <input class="radio" name="accountType" type="radio" value="${c.code}" > ${c.title}
 			</#list>
           
            
           </td>
     </tr>
     
    <tr>
           <td  align="right" class="hui1"><span class="red">*</span>项目类型 ：</td>
           <td  align="left" valign="middle">
          	简历宝
           </td>
     </tr>
     
    <tr>
           <td  align="right" class="hui1"><span class="red">*</span>当前访问的url ：</td>
           <td  align="left" valign="middle">
          		<input type="text" class="input validate[required]" name="currentUrl"  style="width:570px;" id="currentUrl">
           </td>
     </tr>
     
    <tr>
           <td  align="right" class="hui1"><span class="red">*</span>标题 ：</td>
           <td  align="left" valign="middle">
          	 <input name="title" id="title" type="text" class="input  validate[required] " style="width:570px;">
           </td>
     </tr>
     
    <tr>
           <td  align="right" class="hui1"><span class="red">*</span>内容 ：</td>
           <td  align="left" valign="middle">
          	 <textarea  style="width:300px;height:400px;visibility:hidden;"  name="sug_content" id="sug_content"></textarea>
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
          	   <input name="userOs"  id="userOs"  type="text" class="input  validate[required] " style="width:570px;">
           </td>
     </tr>
     
    <tr>
           <td  align="right" class="hui1">操作类型 ：</td>
           <td  align="left" valign="middle">
          	  <#list DictionaryUtil.getTypes(DictionaryType.BUG_OPERATOR_STATUS.getCode()) as c>
             	 <input class="radio" name="operationType" type="radio" value="${c.dictionaryId}" > ${c.name!''}
 			 </#list>
           </td>
     </tr>
     
      <tr style="display:none;" class="_detail">
           <td  align="right" class="hui1">录入人：</td>
           <td  align="left" valign="middle"  colspan="3" id="inPersonName">
           </td>
     </tr>
     
    <tr style="display:none;" class="_detail">
           <td  align="right" class="hui1">录入时间：</td>
           <td  align="left" valign="middle" colspan="3" id="inTime">
           </td>
         </tr>    
	  
         
         <tr style="display:none;" class="_detail">
           <td  align="right" class="hui1">更新人：</td>
           <td  align="left" valign="middle"  colspan="3" id="updatePersonName">
           </td>
     </tr>
      <tr style="display:none;" class="_detail">
           <td  align="right" class="hui1">更新时间：</td>
           <td  align="left" valign="middle" colspan="3" id="updateTime">
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
<script src="/js/bugInfo.js"></script>
<script>
	suggestInfo.initPage();
	//$(".ke-container-default").css({"width":"475px"});
	editor = KindEditor.create('textarea[name="sug_content"]',{
	//allowPreviewEmoticons : false,
	 uploadJson : '/file/kindEditorUplad.action', //上传
	 fileManagerJson : 'file_manager.do', // 文件管理
	 allowFileManager : true,
	items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'image', 'link']
	});
	
	
</script>

