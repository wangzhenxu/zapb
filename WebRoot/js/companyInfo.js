var companyInfo = {
	//请求url
	listUrl:"/zpCompanyInfo/list.action", //列表地址
	toAddUrl:"/zpCompanyInfo/toAdd.action", //去添加页面地址
	addUrl:"/zpCompanyInfo/add.action", //添加地址
	toEditUrl:"/zpCompanyInfo/toEdit.action?id=", //去修改页面地址
	deleteUrl :"/zpCompanyInfo/delete.action?id=", //删除页面地址
	toViewUrl:"/zpCompanyInfo/toView.action?id=", //详细页面地址
	editUrl:"/zpCompanyInfo/edit.action", //详细页面地址
	modifyDeleteStatusUrl:"/zpCompanyInfo/modifyDeleteStatus.action", //停用 或启用
	toJobListUrl:"/zpCompanyJobInfo/list.action?name=", //停用 或启用
	checkNameExitsUrl : "/zpCompanyInfo/checkNameExits.action", //检验唯一性,
	
	onlyName :  $("#onlyName"), //修改，唯一验证时需要添加此属性
	m_title : $(".m_title"),//页面标题
	//对象
	companyId : $("#companyId"), //主建
	name : $("#name"), //公司名称
	address : $("#address"), //公司详细地址
	desc : $("#desc"), //desc
	moreDesc : $("#moreDesc"), //更多描述
	scaleId : $("#scaleId"), //公司规模
	regtime : $("#regtime2"), //注册时间
	financingLevelId : $("#financingLevelId"), //融资规模
	industryId : $("#industryId"), //所属行业
	companyNature : $("#companyNature"), //公司性质
	inPerson : $("#inPerson"), //录入人
	lastUpdateTime : $("#lastUpdateTime"), //更新时间
	isDelete : $("#isDelete"), //1 未删除  2 以删除
	coordX : $("#coordX"), //坐标x
	coordY : $("#coordY"), //坐标y
	areaId : $("#areaId"), //公司所在城区
	inPersonName : $("#inPersonName"), //录入人

	query : function(){
		var serializeObj = common.serializeJson("queryForm");
		var jsonStr = JSON.stringify(serializeObj)
		location.href="/zpCompanyInfo/list.action?jsonParam="+jsonStr;
	},
	toDetail : function (id){
		location.href=this.toViewUrl+id;
	},
	tolist : function (){
		location.href="/zpCompanyInfo/list.action";
	},
	initPage : function (){
		var self = this;
		self.currPage = this.getCurrPageFlag();
		CKEDITOR.replace('desc1', {
			height : 150,
			width : 720,
			filebrowserUploadUrl:'/upload.action'
		});
		CKEDITOR.replace('desc2', {
			height : 150,
			width : 720,
			filebrowserUploadUrl:'/upload.action'
		});
		jQuery("#addform").validationEngine({scroll:false});
		$("#addBtn").click(function(){
			self.add();
		});
		//初始化标题
		self.initPageTitle();
		//查询数据
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
	initPageTitle : function (){
		var self = this;
		var attrVal = this.m_title.attr("tempAttrValue");//模块名称
		if(self.currPage=="edit"){
			this.m_title.html("修改"+attrVal+"信息");
		} else 
		if(self.currPage=="add"){
			this.m_title.html("添加"+attrVal);
		}else 
		if(self.currPage=="detail"){
			this.m_title.html(attrVal+"详情");
		}
	},
	initAddPage : function (){
		var self = this;
		$('#addform').attr("action",self.addUrl);
		//检验名称唯一性
		self.name.blur(function(){
				if($.trim(this.value).length>0){
					var param = {flag : common.getCurrPageFlag(),name:this.value};
					self.checkNameExits(param,self.checkNameExitCallBack);
				} 
		});
	},
	toAdd : function (){
		location.href="/zpCompanyInfo/toAdd.action";
	},
	toAddJob : function (companyId){
		location.href="/zpCompanyJobInfo/toAdd.action?companyId="+companyId;
	},
	//公司信息 添加 
	add : function (){
		var self = this;
			var b = $('#addform').validationEngine('validate');
			if(!b){
				return false;
			}
			var desc = CKEDITOR.instances.desc1.getData();
			var desc2 =CKEDITOR.instances.desc2.getData();
			if(desc.length==0 || desc==""){
				common.alert("请填写公司介绍");
				return;
			}
			$("#desc").val(desc);
			$("#moreDesc").val(desc2);
			
			if(self.currPage=="edit"){
			   common.openModal("delete_sure","确定修改信息吗？");
			   $("#delete_sure_a").click(function(){
				   self.ajaxSubmitForm();
			   });
			} else {
				self.ajaxSubmitForm();
			}
	},
	ajaxSubmitForm  : function (){
		$('#addform').ajaxSubmit(function(resp) {
			if (resp.s > 0) {
				location="/zpCompanyInfo/list.action";
			} else {
				//唯一判断
				if(resp.s==-100) {
					 $("#name").validationEngine("showPrompt",resp.d,"error");
				}
			}
		});	
	},
   
   toEdit : function (id){
		location.href="/zpCompanyInfo/toEdit.action?id=" + id;
   },
   initDetailPage : function(){
	   var self = this;
		self.getById(this.companyId.val(),function (result){
			if (result.s > 0) {
				self.setForm(result.data);
				$("input").attr("disabled",true);
				$("#addBtn").hide();
				$("._detail").show();
			}  
			else {
				hiOverAlert(resp.d,1000);
			}
		});
   },
   initEditPage : function (){
	   var self = this;
		self.getById(this.companyId.val(),function (result){
			if (result.s > 0) {
				self.setForm(result.data);
			}  
			else {
				hiOverAlert(resp.d,1000);
			}
		});
		$('#addform').attr("action",self.editUrl);
		
		//检验名称唯一性
		self.name.blur(function(){
				if($.trim(this.value).length>0){
					var param = {flag : common.getCurrPageFlag(),name:this.value,oldname:self.onlyName.val()};
					self.checkNameExits(param,self.checkNameExitCallBack);
				} 
		});
   },
   getById : function (id,callBack){
	   var obj = null;
	   var rData={"id" : id};
		$.ajax({
			url : "/zpCompanyInfo/getById.action",
			data : rData,// 
			success :callBack
		});
   },
   toDelete : function(id){
	   var self = this;
	   common.openModal("delete_sure","确定删除吗？");
	   $("#delete_sure_a").click(function(){
		  location.href= self.deleteUrl+id; 
	   });
   },
   modifyDeleteStatus : function (id,status){
	   var self = this;
	   var delTitle = "";
	   if(status==1){
		   status=2;
		   delTitle="确定暂停吗？职位也会暂停哦";
	   } else 
	   if(status==2){
		   status=1;
		   delTitle="确定启用吗？职位也会开启哦";
	   }	   
	   common.openModal("delete_sure",delTitle);
	   $("#delete_sure_a").click(function(){
		  location.href= self.modifyDeleteStatusUrl+"?id="+id+"&deleteStatus="+status; 
	   });
   },
   setForm : function (obj){
	    //唯一验证时需要记录原来的名称
	    this.onlyName.val(obj.name);
	   	//赋值
		this.companyId.val(obj.companyId); //主建
		this.name.val(obj.name); //公司名称
		this.address.val(obj.address); //公司详细地址
		
		
		CKEDITOR.instances.desc1.setData(obj.desc);
		CKEDITOR.instances.desc2.setData(obj.moreDesc);
		
		$("input[name='scaleId'][value='"+obj.scaleId+"']").attr("checked",true); //公司规模
		if(obj.regtime && obj.regtime>0){
			var new1 = new Date(obj.regtime).format("yyyy-MM");
			this.regtime.val(new1); //注册时间
		}
		$("input[name='financingLevelId'][value='"+obj.financingLevelId+"']").attr("checked",true); //融资规模
		$("input[name='industryId'][value='"+obj.industryId+"']").attr("checked",true); //所属行业
		$("input[name='companyNature'][value='"+obj.companyNature+"']").attr("checked",true); //公司性质
		//this.coordX.val(obj.coordX); //坐标x
		//this.coordY.val(obj.coordY); //坐标y
		$("input[name='areaId'][value='"+obj.areaId+"']").attr("checked",true); //公司所在城区
		
		//用户详情显示信息
		//this.inPerson.html(obj.inPerson);
		this.inPersonName.html(obj.inPersonName); //录入人

		if(obj.lastUpdateTime && obj.lastUpdateTime>0){
		  var new1 = new Date(obj.lastUpdateTime).format("yyyy-MM-dd HH:mm");
		  this.lastUpdateTime.html(new1); //更新时间
		}
		
        //启用状态
		if(obj.isDelete && obj.isDelete==1){
			this.isDelete.html("启用");
        	}else
    	 //暂停状态
		if(obj.isDelete && obj.isDelete==2){
			this.isDelete.html("暂停");
        }
		
   },
   getCurrPageFlag: function(){
	   var page = "";
	   if(location.href.indexOf("/toEdit.action")!=-1){
		   page="edit"; 
	   }else
	   if(location.href.indexOf("/toAdd.action")!=-1){
		   page="add"; 
	   } else
	  if(location.href.indexOf("/toView.action")!=-1){
		  page="detail"; 
	  } else
	  if(location.href.indexOf("/list.action")!=-1){
		  page="list"; 
	  }
	  return page;
   },
   toJobList : function (name){
	   var self =this;
	   window.open(self.toJobListUrl+name);
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


