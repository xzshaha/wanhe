
function checkName(){
	var deviceName = $("#deviceName").val();
	if(deviceName=="" || deviceName == null || deviceName == undefined){
		layer.tips('请输入设备名称', '#deviceName');
		$("#deviceName").val("");
		return false;
	}else{
		return true;
	}
}
function checkCode(){
	var deviceCode = $("#deviceCode").val();
	if(deviceCode=="" || deviceCode == null || deviceCode == undefined){
		layer.tips('请输入设备编码', '#deviceCode');
		$("#deviceCode").val("");
		return false;
	}else{
		return true;
	}
}
function checkTime(){
	var lockInterval = $("#lockInterval").val();
	var reg = /^[1-9]\d*|0$/;
	if(lockInterval=="" || lockInterval == null || lockInterval == undefined || !reg.test(lockInterval)){
		layer.tips('请输入间隔时间', '#lockInterval');
		$("#lockInterval").val("");
		return false;
	}else{
		return true;
	}
}
