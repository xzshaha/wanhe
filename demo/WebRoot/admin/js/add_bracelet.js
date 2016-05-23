
function checkName(){
	var deviceName = $("#deviceName").val();
	var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
	if(deviceName=="" || deviceName == null || deviceName == undefined || !reg.test(deviceName)){
		layer.tips('请填写设备名', '#deviceName');
		$("#deviceName").val("");
		return false;
	}else{
		return true;
	}
}
function checkCode(){
	var model = $("#deviceCode").val();
	if(model=="" || model == null || model == undefined){
		layer.tips('请填写设备编码', '#deviceCode');
		$("#deviceCode").val("");
		return false;
	}else{
		return true;
	}
}

function checkUserName(){
	var userName = $("#userName").val();
	var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
	if(userName=="" || userName == null || userName == undefined || !reg.test(userName)){
		layer.tips('请填写使用人名称', '#userName');
		$("#userName").val("");
		return false;
	}else{
		return true;
	}
}