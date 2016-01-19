
<!-- 
	列表地址：${Const.CTX}/tgProduct/list.action
	去添加页面地址 ：${Const.CTX}/tgProduct/toAdd.action
	去修改页面地址 ：${Const.CTX}/tgProduct/toEdit.action?id=
	删除页面地址 ：${Const.CTX}/tgProduct/delete.action?id=
	详细页面地址 ：${Const.CTX}/tgProduct/toView.action?id=
	
-->
<script>
	 left_menu_class_num=1;
	 leftMenuNum=1;
</script>


<script>
	//删除  商品
	function del(id) {
	if(window.confirm("确定要删除吗？")){
				location='${Const.CTX}/tgProduct/delete.action?id'= +id ;
	}
}
</script>
