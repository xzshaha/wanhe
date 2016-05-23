
function checkGroupName(){
	var name = $("#names").val();
	var reg = /^[A-Za-z0-9_\-\u4e00-\u9fa5]+$/;
	if(name=="" || name == null || name == undefined || !reg.test(name)){
		layer.tips('给圈起个名字吧', '#names');
		$("#names").val("");
		return false;
	}else{
		return true;
	}
}
