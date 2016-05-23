/**
 * Created by wc on 2015/5/21.
 */


$(function(){


});

function getPage(url){
    if(url == "" || url == null){
        return;
    }
    $.get(url,{"_":Math.random()},function(result){
    	$(".page-body").html('');
        $(".page-body").append(result);
    });
}


function showDialog(url, title, width, height,infoMation,full, isShadeClose,successCallBack,closeCallBack) {
	/*window.parent.show();*/
	if (url == '' || url == null) {
		return;
	}
	if (title == '' || title == null) {
		title = "提示框";
	}
	if (width == '' || width == null) {
		width = '660';
	}
	if (height == '' || height == null) {
		height = '400';
	}
	if(!arguments[6]){
		isShadeClose = false;
	}
	 if(!arguments[4]){
		 full = false;
	 }

	$.get(url, {
		"_" : Math.random()
	}, function(result) {
		//scrollbar: false,		//禁止浏览器滚动条
		var index = layer.open({
			type : 1,
			title : title,
			shadeClose : isShadeClose,
			area : [ width + "px", height + "px" ],
			shade : 0.8,
			content : result,		//窗体展示内容
			shift:0,				//动画 1-6
			success:function(){		//弹出窗弹出会回调
				$(".layui-layer-shade").css({
					"z-index":"1900"
				});
				$(".layui-layer").css({
					"z-index":"1910"
				});
				
				if(successCallBack != null && typeof(eval(successCallBack)) == "function"){
					successCallBack();
				}
				
			},
			end : function(){	//弹出窗关闭后回调
				$(".select2-drop").remove();
				$(".select2-drop-mask").remove();
				if(closeCallBack != null && typeof(eavl(closeCallBack)) == "function"){
					closeCallBack();
				}
			}
		});

		if (infoMation != null && typeof (eval(infoMation)) == "function") {
			infoMation();
		}

		if(full){
			layer.full(index);
		}
		
	});
	
}

