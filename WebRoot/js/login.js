$(function() {
	
	$("#loginBtn").click(login);
	$("#loginClear").click(reset);
	$("input[name=username]").focus();
	$(document).keydown(function(e) {
		if (e.keyCode == 13) {
			login();
		}
	});

	function reset() {
		$('#loginForm').clearForm();
		$("#tip").text("");
	}

	function login() {
		$("[name=loginUsername]").blur();
		$("[name=loginPassword]").blur();
		if($(".error-field").size()){
		}else{
		$('#loginForm').ajaxSubmit(function(resp) {
			if (resp.s > 0) {
				location.href = "/welcome.action";
			} else {
				$("#tip").text(resp.d);
			}
		});
		}
	}
});
