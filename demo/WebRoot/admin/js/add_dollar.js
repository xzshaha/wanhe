
function checkName(){
	var names = $("#names").val();
	if(names=="" || names == null || names == undefined){
		layer.tips('请输入优惠劵名称', '#names');
		$("#names").val("");
		return false;
	}else{
		return true;
	}
}
function checkPoint(){
	var maxNum = $("#point").val();
	var reg = /^[1-9]\d*|0$/;
	if(maxNum=="" || maxNum == null || maxNum == undefined || !reg.test(maxNum)){
		layer.tips('请输入积分值', '#point');
		$("#point").val("");
		return false;
	}else{
		return true;
	}
}

function checkPrice(){
	var maxNum = $("#price").val();
	var reg = /^[1-9]\d*|0$/;
	if(maxNum=="" || maxNum == null || maxNum == undefined || !reg.test(maxNum)){
		layer.tips('请输入优惠劵单价', '#price');
		$("#price").val("");
		return false;
	}else{
		return true;
	}
}
