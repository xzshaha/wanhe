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
function checkEmail(){
	var email = $("#email").val();
	var reg = /[a-zA-Z0-9]{1,10}@[a-zA-Z0-9]{1,5}\.[a-zA-Z0-9]{1,5}/;
	if(email=="" || email == null || email == undefined || !reg.test(email)){
		layer.tips('请输入正确的邮箱格式', '#email');
		$("#email").val('');
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
		$("#names").val('');
		return false;
	}else{
		return true;
	}
}
function checkAge(){
	var age = $("#age").val();
	var reg = /^[1-9]\d*$/;
	if(age=="" || age == null || age == undefined || !reg.test(age)){
		layer.tips('请输入您的年龄', '#age');
		$("#age").val('');
		return false;
	}else{
		return true;
	}
}


function checkMobile(){
	var mobile = $("#mobile").val();
	var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	if(mobile=="" || mobile == null || mobile == undefined || !reg.test(mobile)){
		layer.tips('请填写正确手机号码', '#mobile');
		$("#mobile").val('');
		return false;
	}else{
		return true;
	}
}



function checkDoorNum(){
	var doorNum = $("#doorNum").val();
	var reg = /^[1-9]\d*|0$/;
	if(doorNum=="" || doorNum == null || doorNum == undefined || !reg.test(doorNum)){
		layer.tips('请填写门牌号', '#doorNum');
		$("#doorNum").val('');
		return false;
	}else{
		return true;
	}
}
function checkHeight(){
	var height = $("#height").val();
	var reg = /^[1-9]\d*|0$/;
	if(height=="" || height == null || height == undefined || !reg.test(height)){
		layer.tips('请填写业主身高', '#height');
		$("#height").val('');
		return false;
	}else{
		return true;
	}
}
function checkWeight(){
	var weight = $("#weight").val();
	var reg = /^[1-9]\d*|0$/;
	if(weight=="" || weight == null || weight == undefined || !reg.test(weight)){
		layer.tips('请填写业主体重', '#weight');
		$("#weight").val('');
		return false;
	}else{
		return true;
	}
}




function checkCarNum(){
	var carNum = $("#carNum").val();
	/*var reg = /^[1-9]\d*|0$/;*/
	if(carNum=="" || carNum == null || carNum == undefined || carNum.length<7){
		layer.tips('请填写车牌号', '#carNum');
		$("#carNum").val('');
		return false;
	}else{
		return true;
	}
}

function checkRunCard(){
	var runCard = $("#runCard").val();
	/*var reg = /^[1-9]\d*|0$/;*/
	if(runCard=="" || runCard == null || runCard == undefined || runCard.length<15){
		layer.tips('请填写行驶证号', '#runCard');
		$("#runCard").val('');
		return false;
	}else{
		return true;
	}
}

