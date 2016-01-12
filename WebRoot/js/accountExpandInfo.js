
//个人信息
var accountExpandInfo = {
	//请求url
	listUrl:"/accountExpandInfo/list.action", //列表地址
	toAddUrl:"/accountExpandInfo/toAdd.action", //去添加页面地址
	addUrl:"/accountExpandInfo/add.action", //添加地址
	toEditUrl:"/accountExpandInfo/toEdit.action?id=", //去修改页面地址
	editUrl:"/accountExpandInfo/edit.action", //去修改页面地址
	deleteUrl :"/accountExpandInfo/delete.action?id=", //删除页面地址
	toViewUrl:"/accountExpandInfo/toView.action?id=", //详细页面地址
	getByIdUrl : "/accountExpandInfo/getById.action", //根据id查询对象
	modifyDeleteStatusUrl:"/accountExpandInfo/modifyDeleteStatus.action", //停用 或启用

	onlyName :  $("#onlyName"), //修改，唯一验证时需要添加此属性
	_title_val : "个人",
	m_title : $(".m_title"),//页面标题

	addform : jQuery("#addform"), //添加form
	queryfrom :jQuery("#queryForm"), //查询form
	addBtn : $("#addBtn"),//添加按钮
	queryBtn : $("#queryBtn"),//查询按钮
	left_menu_selected_id : "accountExpandInfo_list",   //左侧菜单选择id  


	//属性
	
	expandId : $("#expandId"), //id
	accountId : $("#accountId"), //账户id
	auditPositionId : $("#auditPositionId"), //评审职位id
	realName : $("#realName"), //真实姓名
	nickName : $("#nickName"), //昵称
	iphone : $("#iphone"), //手机
	email : $("#email"), //邮箱
	sexId : $("#sexId"), //性别id
	paymentTypeId : $("#paymentTypeId"), //支付方式
	paymentCode : $("#paymentCode"), //支付内容
	inTime : $("#inTime"), //录入时间
	inPerson : $("#inPerson"), //录入人
	lastLoginTime : $("#lastLoginTime"), //最后登录时间
	idcard : $("#idcard"), //身份证
	isAcceptAudit : $("#isAcceptAudit"), //是否接受评审
	currMoney : $("#currMoney"), //当前余额
	inPersonName : $("#inPersonName"),
	qq : $("#qq"),

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
			self.addBtn.click(function(){
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
		self.queryBtn.click(function(){
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
		self.qq.blur(function(){
		 	self.qqBlur();
	   });
		
	},
	//初始化详情页面数据
	initDetailPage : function(){
		   var self = this;
			self.getById(self.expandId.val(),function (result){
				if (result.s > 0) {
					self.setForm(result.data);
					$("input").attr("disabled",true);
					//薪资管理员、猎头顾问隐藏
					if(accountType==5 ||  accountType==6){
						//self.addBtn.hide();
					}else {
						
					}
					
					//如果只有看的权限
					if(result.data.accountId!=accountIdT){
						self.addBtn.hide();
					}
					
					self.addBtn.html("修&nbsp改");
					self.addBtn.unbind("click").click(function(){
						self.toEdit(self.expandId.val());
					});
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
	
	//个人信息 添加 
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
				   $("#delete_sure_a").click(function(){
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
				self.toDetail(self.expandId.val());
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
		self.m_title.html("补充个人信息");
		self.getById(this.expandId.val(),function (result){
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
		self.qq.blur(function(){
		 	self.qqBlur();
	   });
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
	   $("#delete_sure_a").click(function(){
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
	   $("#delete_sure_a").click(function(){
		  location.href= self.modifyDeleteStatusUrl+"?id="+id+"&deleteStatus="+status; 
	   });
   },
   //填充表单数据
   setForm : function (obj){
	   var self = this;
	    //唯一验证时需要记录原来的名称
	   self.onlyName.val(obj.name);
	   //赋值
		self.expandId.val(obj.expandId); //id
		
		//用户详情显示信息
		$("input[name='accountId'][value='"+obj.accountId+"']").attr("checked",true); //账户id
		$("input[name='auditPositionId'][value='"+obj.auditPositionId+"']").attr("checked",true); //评审职位id
		self.realName.val(obj.realName); //真实姓名
		self.nickName.val(obj.nickName); //昵称
		self.iphone.val(obj.iphone); //手机
		self.email.val(obj.email); //邮箱
		self.qq.val(obj.qq); //qq

		$("input[name='sexId'][value='"+obj.sexId+"']").attr("checked",true); //性别id
		$("input[name='paymentTypeId'][value='"+obj.paymentTypeId+"']").attr("checked",true); //支付方式
		self.paymentCode.val(obj.paymentCode); //支付内容
		if(obj.inTime && obj.inTime>0){
		  var new1 = new Date(obj.inTime).format("yyyy-MM-dd HH:mm");
		  self.inTime.html(new1); //录入时间
		}
			
		//其它属性
		self.inPerson.html(obj.inPerson);
			
		//其它属性
		if(obj.lastLoginTime && obj.lastLoginTime>0){
			var new1 = new Date(obj.lastLoginTime).format("yyyy-MM");
			self.lastLoginTime.val(new1); //最后登录时间
		}
		self.idcard.val(obj.idcard); //身份证
		$("input[name='isAcceptAudit'][value='"+obj.isAcceptAudit+"']").attr("checked",true); //是否接受评审
		self.currMoney.val(obj.currMoney); //当前余额
		self.inPersonName.html(obj.inPersonName);
   },
   
   qqBlur : function (){
	   var self =this;
	   var patrn=/^[0-9]{1,11}$/;    
	   if (!patrn.exec(self.qq.val())) return false
	   if(self.currPage=="edit" &&   self.email.val().length>0){
		  return;  
	   } 
	   self.email.val(self.qq.val()+"@qq.com");
	   /*if(self.email.val().length>0 && self.email.val().indexOf("@qq.com")!=-1){
		   self.qq.val(self.email.val().replace("@qq.com",""));
	   }*/
   }
}
