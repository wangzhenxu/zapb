<script src="/js/jquery/jquery.js"></script>
<script src="/js/jquery/jquery.form.js"></script>
<script src="/js/main.js"></script>
<script src="/js/jquery.date_input.js"></script> 
<script src="/js/common.js"></script> 
<script src="/js/hiAlert/jquery.alert.js"></script>
<script src="/js/jquery/jquery.placeholder.js"></script>

<!-- 文本编辑器 -->
<link rel="stylesheet" href="/kindeditor-4.1.10/themes/default/default.css" />
<script charset="utf-8" src="/kindeditor-4.1.10/kindeditor-min.js"></script>
<script charset="utf-8" src="/kindeditor-4.1.10/lang/zh_CN.js"></script>


<link href="/css/alert.css" rel="stylesheet" type="text/css" />

<#macro gmc_common_js  from="">
	<#if from="add">
 		<script type="text/javascript" src="/js/source/c_jquery.validationEngine.js"></script>
		<script type="text/javascript" src="/js/source/c_jquery.validationEngine.min.js"></script>
		<link href="/css/c_validationEngine.jquery.css" rel="stylesheet" type="text/css" />
		<script src="/js/my97/WdatePicker.js" type="text/javascript" > </script>
 	</#if>
 	<#if from="selete">
 		
 	</#if>
</#macro>


<#macro gmc_common_body head_title='' body_class=''>
	
	
</#macro>