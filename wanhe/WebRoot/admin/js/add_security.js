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
function checkDeviceName(){
	var deviceName = $("#deviceName").val();
	var reg = /^[A-Za-z0-9_\-\u4e00-\u9fa5]+$/;
	if(deviceName=="" || deviceName == null || deviceName == undefined || !reg.test(deviceName)){
		layer.tips('请填写设备名', '#deviceName');
		$("#deviceName").val("");
		return false;
	}else{
		return true;
	}
}
function checkDeviceCode(){
	var deviceCode = $("#deviceCode").val();
	if(deviceCode=="" || deviceCode == null || deviceCode == undefined){
		layer.tips('请填写设备编码', '#deviceCode');
		$("#deviceCode").val("");
		return false;
	}else{
		return true;
	}
}
function checkIp(){
	var ip = $("#ip").val();
	/*var reg="((?:(?:25[0-5]|2[0-4]\d|[01]?\d?\d)\.){3}(?:25[0-5]|2[0-4]\d|[01]?\d?\d))";*/
	if(ip=="" || ip == null || ip == undefined){
		layer.tips('请输入正确的ip', '#ip');
		$("#ip").val("");
		return false;
	}else{
		return true;
	}
}
function checkPort(){
	var port = $("#port").val();
	/*var reg= "^([1-9]|[1-9]\\d{1,3}|[1-6][0-5][0-5][0-3][0-5])$";*/
	if(port=="" || port == null || port == undefined){
		layer.tips('请填写端口', '#port');
		$("#port").val("");
		return false;
	}else{
		return true;
	}
}
function checkAccount(){
	var account = $("#account").val();
	if(account=="" || account == null || account == undefined){
		layer.tips('请填写账号', '#account');
		$("#account").val("");
		return false;
	}else{
		return true;
	}
}


function checkPassword(){
	var password = $("#password").val();
	if(password=="" || password == null || password == undefined){
		layer.tips('请输入密码', '#password');
		$("#password").val("");
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

