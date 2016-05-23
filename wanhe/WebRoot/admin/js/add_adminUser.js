/*$(function(){
	$("#roleSel").change(function(){
		var role = $("#roleSel").val();
		if(role=="物管"){
			$("#property").show();
		}else{
			$("#property").hide();
		}
	})
})*/
function checkUsername(){
	var usernames = $("#usernames").val();
	var reg =  /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	if(usernames=="" || usernames == null || usernames == undefined || !reg.test(usernames)){
		layer.tips('请填写手机号码', '#usernames');
		$("#usernames").val("");
		return false;
	}else{
		return true;
	}
}
function checkPwd(){
	var pwd = $("#password").val();
	if(pwd=="" || pwd == null || pwd == undefined || pwd.length<6){
		layer.tips('请输入6位以上密码', '#password');
		$("#password").val("");
		return false;
	}else{
		return true;
	}
}
function checkEmail(){
	var email = $("#email").val();
	var reg = /[a-zA-Z0-9]{1,10}@[a-zA-Z0-9]{1,5}\.[a-zA-Z0-9]{1,5}/;
	if(email=="" || email == null || email == undefined || !reg.test(email)){
		layer.tips('请输入正确的邮箱格式', '#email');
		$("#email").val("");
		return false;
	}else{
		return true;
	}
}
function checkName(){
	var usernames = $("#names").val();
	var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
	if(usernames=="" || usernames == null || usernames == undefined || !reg.test(usernames)){
		layer.tips('请填写有效姓名', '#names');
		$("#names").val("");
		return false;
	}else{
		return true;
	}
}
function checkAge(){
	var age = $("#age").val();
	var reg = /^[1-9]\d*$/;
	if(age=="" || age == null || age == undefined || !reg.test(age)){
		layer.tips('请输入年龄', '#age');
		$("#age").val("");
		return false;
	}else{
		return true;
	}
}


function checkMobile(){
	var mobile = $("#mobiles").val();
	var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	if(mobile=="" || mobile == null || mobile == undefined || !reg.test(mobile)){
		layer.tips('请填写正确电话号码', '#mobiles');
		$("#mobiles").val("");
		return false;
	}else{
		return true;
	}
}



function checkWorkTime(){
	var workTime = $("#workTime").val();
	var reg = /^[1-9]\d*|0$/;
	if(workTime=="" || workTime == null || workTime == undefined || !reg.test(workTime)){
		layer.tips('请填写工作年限', '#workTime');
		$("#workTime").val("");
		return false;
	}else{
		return true;
	}
}

