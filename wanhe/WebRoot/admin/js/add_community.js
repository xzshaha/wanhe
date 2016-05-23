var longitude = "";
var latitude = "";
	var ac = new BMap.Autocomplete({    //建立一个自动完成的对象
	    "input" : "address"
	});
	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
		var _value = e.item.value;
	    myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
	    var myGeo=new BMap.Geocoder();
	    myGeo.getPoint(myValue, function(point){
	    	if (point) {
				latitude=point.lat; //获取纬度
				longitude=point.lng; //获取经度
		  	}
	    })
});




function checkAddress(){
	var address = $("#address").val();
	var reg = /^[A-Za-z0-9_()（）\#\-\u4e00-\u9fa5]+$/;
	if(address=="" || address == null || address == undefined || !reg.test(address)){
		layer.tips('请填写小区地址', '#address');
		$("#address").val('');
		return false;
	}else{
		return true;
	}
}

function checkName(){
	var name = $("#names").val();
	var reg = /^[A-Za-z0-9_()（）\-\u4e00-\u9fa5]+$/;
	if(name=="" || name == null || name == undefined || !reg.test(name)){
		layer.tips('请填写小区名称', '#names');
		$("#names").val('');
		return false;
	}else{
		return true;
	}
}


function checkMobile(){
	var mobile = $("#mobile").val();
	var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	if(mobile=="" || mobile == null || mobile == undefined || !reg.test(mobile)){
		layer.tips('请填写正确电话号码', '#mobile');
		$("#mobile").val("");
		return false;
	}else{
		return true;
	}
}


