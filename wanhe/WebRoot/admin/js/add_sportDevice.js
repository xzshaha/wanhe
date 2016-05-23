function checkCode(){
	var code = $("#code").val();
	if(code=="" || code == null || code == undefined){
		layer.tips('请填写设备编码', '#code');
	}
}


function checkName(){
	var name = $("#name").val();
	var reg =/^[A-Za-z0-9_()（）\-\u4e00-\u9fa5]+$/;
	if(name=="" || name == null || name == undefined || !reg.test(name)){
		layer.tips('请填写设备名称', '#name');
	}
}

function checkModel(){
	var model = $("#model").val();
	var reg ="";
	if(model=="" || model == null || model == undefined || !reg.test(model)){
		layer.tips('请填写设备型号', '#model');
	}
}
