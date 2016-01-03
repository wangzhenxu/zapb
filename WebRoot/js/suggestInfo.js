//建议
var suggestInfo = {
	//请求url
	listUrl:"/tgSuggestBugInfo/list.action", //列表地址
	toAddUrl:"/tgSuggestBugInfo/toAdd.action", //去添加页面地址
	addUrl:"/tgSuggestBugInfo/add.action", //添加地址
	toEditUrl:"/tgSuggestBugInfo/toEdit.action?id=", //去修改页面地址
	editUrl:"/tgSuggestBugInfo/edit.action", //去修改页面地址
	deleteUrl :"/tgSuggestBugInfo/delete.action?id=", //删除页面地址
	toViewUrl:"/tgSuggestBugInfo/toView.action?id=", //详细页面地址
	getByIdUrl : "/tgSuggestBugInfo/getById.action", //根据id查询对象
	modifyDeleteStatusUrl:"/tgSuggestBugInfo/modifyDeleteStatus.action", //停用 或启用
	checkNameExitsUrl : "/tgSuggestBugInfo/checkNameExits.action", //检验唯一性,

	onlyName :  $("#onlyName"), //修改，唯一验证时需要添加此属性
	m_title : $(".m_title"),//页面标题
	_title_val : "建议",
	
	addform : jQuery("#addform"), //添加form
	queryfrom :jQuery("#queryForm"), //查询form
	addBtn : $("#addBtn"),//添加按钮
	queryBtn : $("#queryBtn"),//查询按钮
	left_menu_selected_id : "tgSuggestBugInfo_list",   //左侧菜单选择id  


	//属性
	
	suggestId : $("#suggestId"), //主键
	accountType : $("#accountType"), //用户类型
	projectType : $("#projectType"), //项目类型
	currentUrl : $("#currentUrl"), //当前访问的url
	title : $("#title"), //标题
	content : $("#content"), //内容
	address : $("#address"), //用户所在地
	agent : $("#agent"), //浏览器类型和版本
	userOs : $("#userOs"), //用户使用的操作系统
	operationType : $("#operationType"), //操作类型
	inPerson : $("#inPerson"), //录入人
	inTime : $("#inTime"), //录入时间
	sugType : $("#sugType"), //1 bug 2建议
	inPersonName : $("#inPersonName"),
	//页面初始化
	initPage : function (){
		var self = this;
		self.currPage = common.getCurrPageFlag();
		common.initLeftMenuSelected(self.left_menu_selected_id);

		//初始化标题
		common.initPageTitle(self._title_val,self.m_title);
		//列表页面
		if(self.currPage!="list"){
			self.addform.validationEngine({scroll:false});
			self.addBtn.unbind("click").click(function(){
				self.add();
			});
		}
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
		self.queryfrom.validationEngine({scroll:false});
		self.queryBtn.unbind("click").unbind("click").click(function(){
			self.query();
		});
    },
	//列表查询
	query : function(){
		var self = this;
		var b = self.queryfrom.validationEngine('validate');
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
			self.getById(self.suggestId.val(),function (result){
				if (result.s > 0) {
					self.setForm(result.data);
					$("input").attr("disabled",true);
					self.addBtn.hide();
					$("._detail").show();
				}//不存在
				else if (result.s==-1000) {
					location.href = common.notFindUrl;
				}  
				else {
					common.alert(result.d);
				}
			});
	   },
	
	//建议 添加 
	add : function (){
			var self = this;
			var b = self.addform.validationEngine('validate');
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
		self.getById(this.suggestId.val(),function (result){
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
	   var self = this;
	    //唯一验证时需要记录原来的名称
	   self.onlyName.val(obj.name);
	   //赋值
		self.suggestId.val(obj.suggestId); //主键
		
		//用户详情显示信息
		self.accountType.val(obj.accountType); //用户类型
		self.projectType.val(obj.projectType); //项目类型
		self.currentUrl.val(obj.currentUrl); //当前访问的url
		self.title.val(obj.title); //标题
		self.content.val(obj.content); //内容
		self.address.val(obj.address); //用户所在地
		self.agent.val(obj.agent); //浏览器类型和版本
		self.userOs.val(obj.userOs); //用户使用的操作系统
		self.operationType.val(obj.operationType); //操作类型
		self.inPerson.html(obj.inPerson);
			
		//其它属性
		if(obj.inTime && obj.inTime>0){
		  var new1 = new Date(obj.inTime).format("yyyy-MM-dd HH:mm");
		  self.inTime.html(new1); //录入时间
		}
			
		//其它属性
		self.sugType.val(obj.sugType); //1 bug 2建议
		self.inPersonName.html(obj.inPersonName);
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
