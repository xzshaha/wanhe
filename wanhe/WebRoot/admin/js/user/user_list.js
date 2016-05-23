$(function(){
	$(".delete").each(function(){
		$(this).click(function(){
			//询问框
			layer.confirm('您确认要删除此数据？', {
			    btn: ['确认','取消'] //按钮
			}, function(){
			    layer.msg('数据已删除', {icon: 1});
			}, function(){
	//				    layer.msg('您取消', {
	//				        time: 20000, //20s后自动关闭
	//				        btn: ['明白了', '知道了']
	//				    });
			});
		})
	})
})