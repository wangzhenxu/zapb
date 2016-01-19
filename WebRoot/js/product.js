var product = {
	//请求url
	listUrl:"/tgProduct/list.action", //列表地址
	toAddUrl:"/tgProduct/toAdd.action", //去添加页面地址
	addUrl:"/tgProduct/add.action", //添加地址
	toEditUrl:"/tgProduct/toEdit.action?id=", //去修改页面地址
	editUrl:"/tgProduct/edit.action", //去修改页面地址
	deleteUrl :"/tgProduct/delete.action?id=", //删除页面地址
	toViewUrl:"/tgProduct/toView.action?id=", //详细页面地址
	getByIdUrl : "/tgProduct/getById.action", //根据id查询对象
	modifyDeleteStatusUrl:"/tgProduct/modifyDeleteStatus.action", //停用 或启用
	checkNameExitsUrl : "/tgProduct/checkNameExits.action", //检验唯一性,

	onlyName :  $("#onlyName"), //修改，唯一验证时需要添加此属性
	m_title : $(".m_title"),//页面标题
	_title_val : "商品",
	
	addform : $("#addform"), //添加form
	queryfrom :$("#queryForm"), //查询form
	addBtn : $("#addBtn"),//添加按钮
	queryBtn : $("#queryBtn"),//查询按钮
	left_menu_selected_id: $(".right").attr("data-name"),   //左侧菜单选择id  

	//属性
	id : $("#id"), //主建
	category : $("#category"), //分类
	name : $("#name"), //名称
	price : $("#price"), //价格
	unit : $("#unit"), //单位
	origin : $("#origin"), //来源
	originUrl : $("#originUrl"), //来源网址
	remark : $("#remark"), //备注
	isDelete : $("#isDelete"), //上架或下架状态
	inPerson : $("#inPerson"), //录入人
	inTime : $("#inTime"), //录入时间
	updatePerson : $("#updatePerson"), //更新人
	udpateTime : $("#udpateTime"), //更新时间
	inPersonName : $("#inPersonName"),
	updatePersonName : $("#updatePersonName"),
	//页面初始化
	initPage : function (){
		var self = this;
		self.currPage = common.getCurrPageFlag();
		console.log(self.left_menu_selected_id);
		common.initLeftMenuSelected(self.left_menu_selected_id);

		//初始化标题
		common.initPageTitle(self._title_val,self.m_title);
		//列表页面
		if(self.currPage!="list"){
			self.addform.validationEngine({scroll:false});
			self.addBtn.unbind("click");
		}
		self.addform.validationEngine({scroll:false});
		self.addBtn.click(function(e){
			self.add();
		});
		if(self.currPage=="list"){
			self.initSeletePage();
		}else
		if(self.currPage=="edit"){
			self.initEditPage();
		} else 
		if(self.currPage=="add"){
			self.initAddPage();
		}else 
		if(self.currPage=="detail"){
			self.initDetailPage();
		}
	},
	initSeletePage : function (){
		var self =this;
		$("#queryForm").validationEngine({scroll:false});
		$("#queryBtn").unbind("click").click(function(){
			self.query();
		});
    },
	//列表查询
	query : function(){
		var self = this;
		var b = $("#queryForm").validationEngine();
		if(!b){
			return false;
		}
		var serializeObj = common.serializeJson("queryForm");
		var jsonStr = JSON.stringify(serializeObj)
		location.href=this.listUrl+"?jsonParam="+jsonStr;
	},
	 //跳转列表页面
	tolist : function (){
		location.href=this.listUrl;
	},
	//跳转到添加页面
	toAdd : function (){
		location.href=this.toAddUrl;
	},
	 //跳转修改页面
	toEdit : function (id){
		location.href=this.toEditUrl +id;
   },
   //跳转详情页面
   toDetail : function (id){
		location.href=this.toViewUrl+id;
	},
   //删除商品
   toDelete : function (id){
		location.href=this.deleteUrl+id;
	},
	//初始化添加页面
	initAddPage : function (){
		var self = this;
		self.addform.attr("action",self.addUrl);
		//检验名称唯一性
		/*self.name.blur(function(){
				if($.trim(this.value).length>0){
					var param = {flag : common.getCurrPageFlag(),name:this.value};
					self.checkNameExits(param,self.checkNameExitCallBack);
				} 
		});*/
		
	},
	//初始化详情页面数据
	initDetailPage : function(){
		   var self = this;
			self.getById(self.id.val(),function (result){
				console.log(result);
				if (result.s > 0) {
					self.setForm(result.data);
					$("input").attr("disabled",true);
					self.addBtn.hide();
					$("._detail").show();
				}//不存在
				else if (result.s==-1000) {
//					location.href = common.notFindUrl;
				}  
				else {
					common.alert(result.d);
				}
			});
	   },
	
	//商品 添加 
	add : function (){
			var self = this;
			var b = self.addform.validationEngine('validate');
			console.log(b);
			if(!b){
				return false;
			}
			//$("#desc").val(CKEDITOR.instances.desc1.getData());
			//$("#moreDesc").val(CKEDITOR.instances.desc2.getData());
			if(self.currPage=="edit"){
			   common.openModal("delete_sure","确定修改信息吗？");
			   $("#delete_sure_a").unbind("click").click(function(){
				   self.ajaxSubmitForm();
			   });
			} else {
				self.ajaxSubmitForm();
		    }
	},
	//提交表单
	ajaxSubmitForm  : function (){
		var self = this;
		self.addform.ajaxSubmit(function(resp) {
			if (resp.s > 0) {
				location.href=self.listUrl;
			} else {
				//唯一判断
				if(resp.s==-100) {
					 $("#name").validationEngine("showPrompt",resp.d,"error");
				}
			}
		});	
	},
	//初始化修改页面数据
	initEditPage : function (){
		var self = this;
		console.log(this.id.val());
		self.getById(this.id.val(),function (result){
			if (result.s > 0) {
				self.setForm(result.data);
			}//不存在
			else if (result.s==-1000) {
				location.href = common.notFindUrl;
			}  
			else {
				common.alert(resp.d);
			}
		});
		self.addform.attr("action",self.editUrl);
		//检验名称唯一性
		/*self.name.blur(function(){
				if($.trim(this.value).length>0){
					var param = {flag : common.getCurrPageFlag(),name:this.value,oldname:self.onlyName.val()};
					self.checkNameExits(param,self.checkNameExitCallBack);
				} 
		});*/
   },
   //根据id查询信息
   getById : function (id,callBack){
	   var self = this;
	   var obj = null;
	   var rData={"id" : id};
		$.ajax({
			url : self.getByIdUrl,
			data : rData,// 
			success :callBack
		});
   },
   //删除
   toDelete : function(id){
	   var self = this;
	   common.openModal("delete_sure","确定删除吗？");
	   $("#delete_sure_a").unbind("click").click(function(){
		  location.href= self.deleteUrl+id; 
	   });
   },
   //更新删除状态
   modifyDeleteStatus : function (id,status){
	   var self = this;
	   var delTitle = "";
	   if(status==1){
		   status=2;
		   delTitle="确定暂停吗?";
	   } else 
	   if(status==2){
		   status=1;
		   delTitle="确定启用吗？";
	   }	   
	   common.openModal("delete_sure",delTitle);
	   $("#delete_sure_a").unbind("click").click(function(){
		  location.href= self.modifyDeleteStatusUrl+"?id="+id+"&deleteStatus="+status; 
	   });
   },
   //填充表单数据
   setForm : function (obj){
	   console.log(obj);
		var self = this;
		//唯一验证时需要记录原来的名称
		self.onlyName.val(obj.name);
		//赋值
		self.id.val(obj.id); //主建
		var form = self.addform;
		//设置商品的相关属性
		form.find("[name='category'] option[value='"+obj.category+"']").attr("selected",true); //分类
		self.name.val(obj.name); //名称
		self.price.val(obj.price); //价格
		self.unit.val(obj.unit); //单位
		form.find("[name='origin'] option[value='"+obj.origin+"']").attr("selected",true); //来源
		self.originUrl.val(obj.originUrl); //来源网址
		self.remark.val(obj.remark); //备注
		form.find("[name='isDelete'] option[value='"+obj.isDelete+"']").attr("selected",true); //是否上架
		
		if(self.currPage != "detail"){
			return ;
		}
		//录入属性
		if(obj.inTime && obj.inTime>0){
		  var new1 = new Date(obj.inTime).format("yyyy-MM-dd HH:mm");
		  self.inTime.text(new1); //录入时间
		}
		self.inPersonName.text(obj.inPersonName);
			
		//更新属性
		if(obj.udpateTime && obj.udpateTime>0){
			var new1 = new Date(obj.udpateTime).format("yyyy-MM");
			self.udpateTime.text(new1); //更新时间
		}
		self.updatePersonName.text(obj.updatePersonName);
		$(".info").show();
   },
   //检查名称唯一性
   checkNameExits : function (param,callBack){
	   var self =this;
		$.ajax({
			url : self.checkNameExitsUrl,
			data : param,// 
			success :callBack
		});
   },
   //检查名称，回调
   checkNameExitCallBack : function (result){
	   if (result.s < 0) {
		   common.alert(result.d,2000);
	   }  
   }
}
