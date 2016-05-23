
function checkUsername(){
	var usernames = $("#username").val();
	var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	if(usernames=="" || usernames == null || usernames == undefined || !reg.test(usernames)){
		layer.tips('请输入正确手机号', '#username');
		$("#username").val('');
		return false;
	}else{
		return true;
	}
}

function checkMobile(){
	var usernames = $("#mobile").val();
	var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	if(usernames=="" || usernames == null || usernames == undefined || !reg.test(usernames)){
		layer.tips('请输入正确手机号', '#mobile');
		$("#mobile").val('');
		return false;
	}else{
		return true;
	}
}
function checkPwd(){
	var pwd = $("#password").val();
	if(pwd=="" || pwd == null || pwd == undefined || pwd.length<6){
		layer.tips('请输入6位以上密码', '#password');
		$("#password").val('');
		return false;
	}else{
		return true;
	}
}
function checkName(){
	var usernames = $("#names").val();
	var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
	if(usernames=="" || usernames == null || usernames == undefined || !reg.test(usernames)){
		layer.tips('请填写有效名称', '#names');
		$("#names").val('');
		return false;
	}else{
		return true;
	}
}


function checkMobile(){
	var mobile = $("#mobile").val();
	var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	if(mobile=="" || mobile == null || mobile == undefined || !reg.test(mobile)){
		layer.tips('请填写正确电话号码', '#mobile');
		$("#mobile").val('');
		return false;
	}else{
		return true;
	}
}

function checkAddress(){
	var address = $("#address").val();
	if(address=="" || address==null || address == undefined){
		layer.tips('请填写地址', '#address');
		$("#address").val('');
		return false;
	}else{
		return true;
	}
}

function checkDiscript(){
	var address = $("#discript").val();
	if(address=="" || address==null || address == undefined){
		layer.tips('请填写描述', '#discript');
		$("#discript").val('');
		return false;
	}else{
		return true;
	}
}



