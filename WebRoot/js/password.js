	$(document).ready(function(){
	   $("#password").validationEngine({ ajaxSubmit: true, validateAttribute: "validate",validationEventTriggers:"keyup blur"}); 
	     });
	function changePassword() {
	$("#oldPassword").blur();
	$("#newPassword").blur();
	$("#retryPassword").blur();
	if($(".error-field").size()){
	}else{
			  $('#password').ajaxSubmit(function(resp) {
				if (resp.s > 0) {
					hiOverAlert("更改成功！",1000);
					setTimeout(function(){
					window.location.reload();},
					1500);
				} else {
						if(resp.s==-100102){
						   $("#tip").remove();
					       $("[name=oldPassword]").after("<span id='tip' style='color:#ff0000'>"+resp.d+"</span>");
						}else{
						   hiOverAlert(resp.d,1000);
						   setTimeout(function(){
						   window.location.reload();},
						   1000);
				    }
				}
		   });
		 }
	}