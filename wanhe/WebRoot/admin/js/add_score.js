$(function(){
	$("#type").change(function(){
		var role = $("#type").val();
		if(role==1){
			$("#keyBox").show();
		}else{
			$("#keyBox").hide();
		}
	})
})
function checkName(){
	var names = $("#names").val();
	if(names=="" || names == null || names == undefined){
		layer.tips('请填写规则名称', '#names');
		$("#names").val("");
		return false;
	}else{
		return true;
	}
}
function checkMaxNum(){
	var maxNum = $("#maxNum").val();
	var reg = /^[1-9]\d*|0$/;
	if(maxNum=="" || maxNum == null || maxNum == undefined || !reg.test(maxNum)){
		layer.tips('请输入数字', '#maxNum');
		$("#maxNum").val("");
		return false;
	}else{
		return true;
	}
}
function checkAddNum(){
	var maxNum = $("#addNum").val();
	var reg = /^[1-9]\d*|0$/;
	if(maxNum=="" || maxNum == null || maxNum == undefined || !reg.test(maxNum)){
		layer.tips('请输入数字', '#addNum');
		$("#addNum").val("");
		return false;
	}else{
		return true;
	}
}
function checkTypeKey(){
	var maxNum = $("#typeKey").val();
	var reg = /^[1-9]\d*|0$/;
	if(maxNum=="" || maxNum == null || maxNum == undefined || !reg.test(maxNum)){
		layer.tips('请输入数字', '#typeKey');
		$("#typeKey").val("");
		return false;
	}else{
		return true;
	}
}
function checkTypeValue(){
	var maxNum = $("#typeValue").val();
	var reg =/^[1-9]\d*|0$/;
	if(maxNum=="" || maxNum == null || maxNum == undefined || !reg.test(maxNum)){
		layer.tips('请输入数字', '#typeValue');
		$("#typeValue").val("");
		return false;
	}else{
		return true;
	}typeValue
}


function checkTypeUnit(){
	var mobile = $("#typeUnit").val();
	if(mobile=="" || mobile == null || mobile == undefined){
		layer.tips('请输入单位', '#typeUnit');
		$("#typeUnit").val("");
		return false;
	}else{
		return true;
	}
}



