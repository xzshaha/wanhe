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
function checkTitle(){
	var title = $("#title").val();
	var reg = /^[A-Za-z0-9_\-\u4e00-\u9fa5]+$/;
	if(title=="" || title == null || title == undefined || !reg.test(title) || title.length>20){
		layer.tips('请填写标题', '#title');
		$("#title").val("");
		return false;
	}else{
		return true;
	}
}
function checkContent(){
	var content = $("#content").val();
	if(content=="" || content == null || content == undefined || content.length>64){
		layer.tips('请输入内容', '#content');
		$("#content").val("");
		return false;
	}else{
		return true;
	}
}
