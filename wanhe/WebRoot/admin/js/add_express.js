function checkContent(){
	var contents = $("#company").val();
	var reg = /^[A-Za-z0-9_()（）\-\u4e00-\u9fa5]+$/;
	if(contents=="" || contents == null || contents == undefined || !reg.test(contents)){
		layer.tips('请填写公司名称', '#company');
		$('#company').val('');
		return false;
	}else{
		return true;
	}
}

function checkNo(){
	var orderNo = $("#no").val();
	var reg = /^[1-9]\d*|0$/;
	if(orderNo=="" || orderNo == null || orderNo == undefined || !reg.test(orderNo)){
		layer.tips('请填写正确的快递单号', '#no');
		$('#no').val('');
		return false;
	}else{
		return true;
	}
}

function checkDeliveryPeple(){
	var deliveryPepleName = $("#expressPeple").val();
	var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
	if(deliveryPepleName=="" || deliveryPepleName == null || deliveryPepleName == undefined || !reg.test(deliveryPepleName)){
		layer.tips('请填写正确的快递员名字', '#expressPeple');
		$('#expressPeple').val('');
		return false;
	}else{
		return true;
	}
}

function checkAdmin(){
	var adminName = $("#admin").val();
	var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
	if(adminName=="" || adminName == null || adminName == undefined || !reg.test(adminName)){
		layer.tips('请填写正确处理人姓名', '#admin');
		$('#admin').val('');
		return false;
	}else{
		return true;
	}
}


function checkDeliveryPhone(){
	var deliveryPhone = $("#expressPhone").val();
	var reg =  /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	if(deliveryPhone=="" || deliveryPhone == null || deliveryPhone == undefined || !reg.test(deliveryPhone)){
		layer.tips('请填写快递员联系方式', '#expressPhone');
		$('#expressPhone').val('');
		return false;
	}else{
		return true;
	}
}


function checkSignPePle(){
	var signPeple = $("#signPeple").val();
	var reg =  /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
	if(signPeple=="" || signPeple == null || signPeple == undefined || !reg.test(signPeple)){
		layer.tips('请填写正确签收人姓名', '#signPeple');
		$('#signPeple').val('');
		return false;
	}else{
		return true;
	}
}


function checkDistribution(){
	var distribution = $("#distribution").val();
	var reg =  /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
	if(distribution=="" || distribution == null || distribution == undefined || !reg.test(distribution)){
		layer.tips('请填写正确配送人名字', '#distribution');
		$('#distribution').val('');
		return false;
	}else{
		return true;
	}
}
