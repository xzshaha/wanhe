
function checkName(){
	var names = $("#names").val();
	if(names=="" || names == null || names == undefined){
		layer.tips('请输入岗亭名称', '#names');
		$("#names").val("");
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

function checkClientNum(){
	var clientNum = $("#clientNum").val();
	var reg = /^[1-9]\d*|0$/;
	if(clientNum=="" || clientNum == null || clientNum == undefined){
		layer.tips('请输入客户号', '#clientNum');
		$("#clientNum").val("");
		return false;
	}else{
		return true;
	}
}
function checkChannelNum(){
	var channelNum = $("#channelNum").val();
	var reg = /^[1-9]\d*|0$/;
	if(channelNum=="" || channelNum == null || channelNum == undefined){
		layer.tips('请输入通道号', '#channelNum');
		$("#channelNum").val("");
		return false;
	}else{
		return true;
	}
}
