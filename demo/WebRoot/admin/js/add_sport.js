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

function checkName(){
	var usernames = $("#names").val();
	var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
	if(usernames=="" || usernames == null || usernames == undefined || !reg.test(usernames)){
		layer.tips('请填写设备名', '#names');
		$("#names").val("");
		return false;
	}else{
		return true;
	}
}
function checkModel(){
	var model = $("#model").val();
	if(model=="" || model == null || model == undefined){
		layer.tips('请填写设备型号', '#model');
		$("#model").val("");
		return false;
	}else{
		return true;
	}
}

function checkCode(){
	var code = $("#code").val();
	if(code=="" || code == null || code == undefined){
		layer.tips('请填写设备编码', '#code');
		$("#code").val("");
		return false;
	}else{
		return true;
	}
}