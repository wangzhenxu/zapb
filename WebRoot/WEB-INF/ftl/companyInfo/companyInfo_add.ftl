<#include "../include/comm_jlb_macro.ftl"/>
 <script type="text/javascript" src="/js/ckeditor/ckeditor.js"></script>			

<!-- 
	列表地址：/zpCompanyInfo/list.action
	去添加页面地址 ：/zpCompanyInfo/toAdd.action
	去修改页面地址 ：/zpCompanyInfo/toEdit.action?id=
	删除页面地址 ：/zpCompanyInfo/delete.action?id=
	详细页面地址 ：/zpCompanyInfo/toView.action?id=
	validate :<input type="text" name="name" id="name"  class="input_text w200 validate[required]" >
		
	    companyId;  //主建
	    name;  //公司名称
	    address;  //公司详细地址
	    desc;  //desc
	    moreDesc;  //更多描述
	    scaleId;  //公司规模
	    regtime;  //注册时间
	    financingLevelId;  //融资规模
	    industryId;  //所属行业
	    companyNature;  //公司性质
	    inPerson;  //录入人
	    lastUpdateTime;  //更新时间
	    isDelete;  //1 未删除  2 以删除
-->
<@gmc_common_js from="add" />
<form id="addform" name="form"  method="post">
<input type="hidden" name="desc" id="desc" />
<input type="hidden" name="moreDesc" id="moreDesc" /> 
<input type="hidden" name="companyId" id="companyId" value="${pid!''}" />
<input type="hidden" name="onlyName" id="onlyName"/>


<!-- 右侧 开始 -->
<div class="right">
    <div class="location">
     <div class="location01">您现在的位置是：首页 &gt; <a href="javascript:companyInfo.tolist();">客户管理</a> &gt;<strong class="m_title" tempAttrValue="客户"> 添加客户</strong></div>
    </div>
    <div class="nav">
     <div class="basic">
	 <div class="basic01 m_title" tempAttrValue="客户">添加客户</div>
	</div>
     <div class="query1">
       <table width="100%" border="0" align="left">
         <tr>
           <td colspan="4" class="red">* 号为必填项</td>
         </tr>
         <tr>
           <td  align="right" class="hui1 buhuan"><span class="red">*</span>公司名称：</td>
           <td  align="left" valign="middle">
           	 <input name="name" id="name" type="text"  style="width: 230px;" class="input validate[required]">
          </td>
           <td align="right" class="hui1 buhuan"><span class="red">*</span>公司地址：</td>
          <td  align="left" valign="middle">
          	 <input name="address" id="address"  style="width: 230px;" type="text" class="input validate[required]">
          </td>
         </tr>
         
         <tr style="display:none;">
           <td  align="right" class="hui1 buhuan"><span class="red">*</span>所在城区：</td>
           <td colspan="3" align="left" valign="middle">
            	<#list DictionaryUtil.getTypes(DictionaryType.COMPANY_AREA.getCode()) as c>
          	  	  <input   id="dsf" name="areaId"  type="radio" value="${c.dictionaryId}" > ${c.showName!''} 
          	 	</#list>
           </td>
         </tr>
         
         <tr>
           <td  align="right" class="hui1 buhuan"><span class="red">*</span>公司规模：</td>
           <td colspan="3" align="left" valign="middle">
	         <#list DictionaryUtil.getTypes(DictionaryType.COMPANY_SCALE.getCode()) as c>
          	  	 <input class="validate[required] radio" id="scaleId" name="scaleId" type="radio" value="${c.dictionaryId}" > ${c.showName!''} 
          	 </#list>
          </td>
         </tr>
         
          <tr>
           <td  align="right" class="hui1 buhuan">融资阶段：</td>
           <td colspan="3" align="left" valign="middle">
	         <#list DictionaryUtil.getTypes(DictionaryType.COMPANY_FINANCING_LEVEL.getCode()) as c>
          	  	 <input class="radio" name="financingLevelId" type="radio" value="${c.dictionaryId}" > ${c.showName!''} 
          	 </#list>
          </td>
         </tr>
         
        <tr>
           <td  align="right" class="hui1 buhuan">所属行业：</td>
           <td colspan="3" align="left" valign="middle">
	         <#list DictionaryUtil.getTypes(DictionaryType.COMPANY_INDUSTRY.getCode()) as c>
          	  	 <input class="radio" name="industryId" type="radio" value="${c.dictionaryId}" > ${c.showName!''} 
          	 </#list>
          </td>
         </tr>
         
         <tr>
           <td  align="right" class="hui1 buhuan">公司性质：</td>
           <td colspan="3" align="left" valign="middle">
	         <#list DictionaryUtil.getTypes(DictionaryType.COMPANY_NATURE.getCode()) as c>
          	  	 <input class="radio" name="companyNature" type="radio" value="${c.dictionaryId}" > ${c.showName!''} 
          	 </#list>
          </td>
         </tr>
         
         <tr>
           <td  align="right" class="hui1 buhuan">注册时间：</td>
           <td  align="left" valign="middle">
	 			<input name="regtime2" id="regtime2" onClick="WdatePicker({dateFmt:'yyyy-MM'})" type="text" readonly>  </td>
          <td align="right" class="hui1"></td>
          <td  align="left" valign="middle">
          </td>
         </tr>
         
          <tr>
           <td  align="right" class="hui1 buhuan"><span class="red">*</span>公司介绍：</td>
           <td colspan="3" align="left" valign="middle">
           		<textarea  cols="45" rows="5" class="input validate[required,length[1000] text-input mokuainr ckeditor" name="desc1"  id="desc1" ></textarea>
           </td>
         </tr>
         
         <tr>
           <td  align="right" class="hui1 buhuan">更多介绍：</td>
           <td colspan="3" align="left" valign="middle">
           		<textarea  cols="45" rows="5" class="input validate[required,length[1000] text-input mokuainr ckeditor" name="desc2"  id="desc2" ></textarea>
           </td>
         </tr>
         
          <tr style="display:none;" class="_detail">
           <td  align="right" class="hui1 buhuan">录入人：</td>
           <td colspan="3" align="left" valign="middle" id="inPersonName">
           </td>
         </tr>
	 	<tr style="display:none;" class="_detail">
           <td  align="right" class="hui1 buhuan">最后更新时间：</td>
           <td colspan="3" align="left" valign="middle" id="lastUpdateTime">
           </td>
         </tr>
      <tr style="display:none;" class="_detail">
             <td  align="right" class="hui1 buhuan">是否停用：</td>
             <td colspan="3" align="left" valign="middle" id="isDelete">
             
             </td>
         </tr>
         
         </table>

     </div>
    </div>
    <div class="anniu">
	   	
	   	<div class="btn-group">
				 <button type="button" class="btn btn-default" id="addBtn">保 &nbsp;存</button>
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		 <button type="button" class="btn btn-default" onclick="companyInfo.tolist();">返&nbsp; 回</button>
      	</div>
      
    </div>
   </div>



<!-- 右侧 结束 -->
</form>
<#include "../include/deleteConfirmModal.ftl">
<script src="/js/companyInfo.js"></script>

<script>
	companyInfo.initPage();
</script>
