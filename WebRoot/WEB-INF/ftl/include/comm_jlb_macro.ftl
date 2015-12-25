<#macro gmc_common_js  from="">
	<#include "../include/bootstrap.ftl"/>

	<#if from="add">
 		<script type="text/javascript" src="/js/source/jquery.validationEngine.js"></script>
		<script type="text/javascript" src="/js/source/jquery.validationEngine.min.js"></script>
		<link href="/css/c_validationEngine.jquery.css" rel="stylesheet" type="text/css" />
		<script src="/js/my97/WdatePicker.js" type="text/javascript" > </script>
 	</#if>
 	<#if from="select">
		<#include "../include/pager.ftl">
		<script src="/js/source/jquery.validationEngine.js"></script>
		<script src="/js/source/jquery.validationEngine.min.js"></script>
		<link href="/js/source/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
		body {
			background-image: url(/images/erji_22.jpg);
			background-repeat: repeat-x;
			}
	</style>
 	</#if>
</#macro>

<#macro gmc_model_js  from="">
	<#if from="map">
		<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
	 	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=a788539c529d82e25bbc19023317f781"></script>		
		<script src="/js/gaodeMap.js"></script>
		
		<style type="text/css">
        #tip {
            height: 120px;
            background-color: #fff;
            padding-left: 10px;
            padding-right: 10px;
            position: absolute;
            bottom: 20px;
            font-size: 12px;
            right: 10px;
            border-radius: 3px;
            width: 200px;
            line-height: 20px;
        }

        #tip input[type="button"] {
            margin-top: 10px;
            background-color: #0D9BF2;
            height: 25px;
            text-align: center;
            line-height: 25px;
            color: #fff;
            border-radius: 3px;
            border: 0;
            cursor: pointer;
        }
    </style>
	</#if>
</#macro>


