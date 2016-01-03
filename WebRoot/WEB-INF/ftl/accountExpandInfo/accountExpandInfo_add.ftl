

<!-- 
		
	    expandId;  //id
	    accountId;  //账户id
	    auditPositionId;  //评审职位id
	    nickName;  //昵称
	    iphone;  //手机
	    email;  //邮箱
	    sexId;  //性别id
	    paymentTypeId;  //支付方式
	    paymentCode;  //支付内容
	    inTime;  //录入时间
	    inPerson;  //录入人
	    lastLoginTime;  //最后登录时间
	    idcard;  //身份证
	    isAcceptAudit;  //是否接受评审
	    currMoney;  //当前余额

	
-->
<#include "../include/comm_jlb_macro.ftl"/>
<@gmc_common_js "add" />
<form id="addform" name="form"  method="post">

<input type="hidden" name="expandId" id="expandId"  value="${pid!''}"  />
<input type="hidden" name="onlyName" id="onlyName"/>

<!-- 右侧 开始 -->
<div class="right">
    <div class="location">
     <div class="location01">您现在的位置是：首页 &gt;  账号管理 &gt;<strong class="m_title" > 补充个人信息</strong></div>
    </div>
    <div class="nav">
     <div class="basic">
	 <div class="basic01 m_title" >补充个人信息</div>
	</div>
     <div class="query1">
       <table width="100%" border="0" align="left">
         <tr>
           <td colspan="4" class="red">* 号为必填项</td>
         </tr>
         
         
         <tr>
           <td  align="right" class="hui1"><span class="red">*</span>真实姓名：</td>
           <td  align="left" valign="middle">
          	 <input name="realName" id="realName" type="text" maxlength="3" class="input validate[required]">
           	 <span class="hui1">&nbsp;不会对外展示</span>
           </td>
            <td  align="right" class="hui1"><span class="red">*</span>昵称：</td>
           	<td  align="left" valign="middle">
          	 <input name="nickName" id="nickName" type="text" class="input validate[required]" maxlength="6" class="input" >
          	 <span class="hui1">&nbsp;显示的名称</span>
           </td>
         </tr>
         
         
         
         
          <tr>
          
           <td  align="right" class="hui1"><span class="red">*</span>qq号：</td>
           <td  align="left" valign="middle">
          	 	<input name="qq" id="qq" type="text"  maxlength="11" class="input validate[required,custom[number],maxSize[11],minSize[6]]">
			</td>
			
           <td  align="right" class="hui1"><span class="red">*</span>邮箱：</td>
           <td  align="left" valign="middle">
          	 	<input name="email" id="email" type="text" class="input validate[required,custom[email]]">
          	 	 <span class="hui1">&nbsp;接收邮件(常用的哦)</span>
		   </td>
		   
		  
         </tr>
         
        
          <tr>
           <td  align="right" class="hui1"><span class="red">*</span>手机：</td>
           <td  align="left" valign="middle" colspan="3">
          	 <input name="iphone" id="iphone" type="text" class="input validate[required,custom[phone]]">
           </td>
         </tr>
         
           <tr>
           <td  align="right" class="hui1"><span class="red">*</span>支付方式：</td>
           <td  align="left" valign="middle">
          		 <#list DictionaryUtil.getTypes(DictionaryType.PAY_TYPE.getCode()) as c>
	          	 	<input  class="radio validate[required]" name="paymentTypeId" id="paymentTypeId"  type="radio" value="${c.dictionaryId}" > ${c.showName!''} 
	          	 </#list>
           </td>
            <td  align="right" class="hui1"><span class="red">*</span>支付号码：</td>
           	<td  align="left" valign="middle">
          	 <input name="paymentCode" id="paymentCode" type="text" class="input validate[required]">
           </td>
         </tr>
         <tr>
           <td  align="right" class="hui1"><span class="red">*</span>性别：</td>
           
           <td  align="left" valign="middle" colspan="3">
          	 	<#list DictionaryUtil.getTypes(DictionaryType.SEX.getCode()) as c>
	          	 	<input  class="radio validate[required]" name="sexId" id="sexId" type="radio" value="${c.dictionaryId}" > ${c.showName!''} 
	          	 </#list>
           </td>
         </tr>
         
          <#if Session[Const.SESSION_USER_KEY].type==AccountType.TECHICAL_AUDIT.getCode()>
         <tr>
           <td  align="right" class="hui1">状态：</td>
           <td  align="left" valign="middle" colspan="3">
          		<#list DictionaryUtil.getTypes(DictionaryType.ACCEPT_AUDIT.getCode()) as c>
	          	 	<input class="radio" name="isAcceptAudit" id="isAcceptAudit" type="radio" value="${c.dictionaryId}" > ${c.showName!''} 
	          	 </#list>
	          	 <span class="hui1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（忙的时候可以暂停哦）</span>
           </td>
         </tr>
         </#if>
         
          <tr>
           <td  align="right" class="hui1">当前余额：</td>
           <td  align="left" valign="middle" colspan="3">
          		0元
           </td>
         </tr>
         
        
          


	 	<tr style="display:none;" class="_detail">
           <td  align="right" class="hui1">录入时间：</td>
           <td  align="left" valign="middle" colspan="3" id="inTime">
           </td>
         </tr>    
	   <tr style="display:none;" class="_detail">
           <td  align="right" class="hui1">录入人：</td>
           <td  align="left" valign="middle"  colspan="3" id="inPersonName">
           </td>
         </tr>
         
         </table>

     </div>
    </div>
    <div class="anniu">
	  	<div class="btn-group">
	  	   <#if subject.isPermitted("accountExpandInfo:editAll")> 
	  			  <button type="button" class="btn btn-default" id="addBtn">保 &nbsp;存</button>
	  		 <#else>
	  		 	<#if subject.isPermitted("accountExpandInfo:edit")> 
	  			   <button type="button" class="btn btn-default" id="addBtn">保 &nbsp;存</button>
	  		   </#if>
	  	   </#if>
      	</div>
    </div>
   </div>
<!-- 右侧 结束 -->
</form>
<#include "../include/deleteConfirmModal.ftl">
<script src="/js/accountExpandInfo.js"></script>
<script>
	accountExpandInfo.initPage();
	var accountType="${Session[Const.SESSION_USER_KEY].type}";
	var accountIdT="${Session[Const.SESSION_USER_KEY].accountId}";
</script>

