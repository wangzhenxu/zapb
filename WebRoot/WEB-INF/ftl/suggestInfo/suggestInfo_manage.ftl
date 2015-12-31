
<!-- 
	列表地址：${Const.CTX}/tgSuggestBugInfo/list.action
	去添加页面地址 ：${Const.CTX}/tgSuggestBugInfo/toAdd.action
	去修改页面地址 ：${Const.CTX}/tgSuggestBugInfo/toEdit.action?id=
	删除页面地址 ：${Const.CTX}/tgSuggestBugInfo/delete.action?id=
	详细页面地址 ：${Const.CTX}/tgSuggestBugInfo/toView.action?id=
	
-->
<script>
	 left_menu_class_num=1;
	 leftMenuNum=1;
</script>


<script>
	//删除  建议
	function del(id) {
	if(window.confirm("确定要删除吗？")){
				location='${Const.CTX}/tgSuggestBugInfo/delete.action?id'= +id ;
	}
}
</script>
