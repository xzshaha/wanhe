/**
 * 后台通用JS
 */

// JS 时间格式工具
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	;
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
		;
	}
	return format;
};

// 添加Cookie
function addCookie(name, value, options) {
	if (arguments.length > 1 && name != null) {
		if (options == null) {
			options = {};
		}
		if (value == null) {
			options.expires = -1;
		}
		if (typeof options.expires == "number") {
			var time = options.expires;
			var expires = options.expires = new Date();
			expires.setTime(expires.getTime() + time * 1000);
		}
		document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires ? "; expires=" + options.expires.toUTCString() : "") + (options.path ? "; path=" + options.path : "") + (options.domain ? "; domain=" + options.domain : ""), (options.secure ? "; secure" : "");
	}
}

// 获取Cookie
function getCookie(name) {
	if (name != null) {
		var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
		return value ? decodeURIComponent(value[1]) : null;
	}
}

// 移除Cookie
function removeCookie(name, options) {
	addCookie(name, null, options);
}

//生成随机字符串
function randomString(len) {
	len = len || 32;
	var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
	var maxPos = $chars.length;
	var pwd = '';
	for (i = 0; i < len; i++) {
		pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
	}
	return pwd;
}

// 上传预览图片
function preview(options) {
	var setting = $.extend({
			'id' : null,
			'type': 0, // 文件类型
			'name': '', // 文件名称
			'source' : '', // 源文件 
			'thumbnail' : '', // 缩略图
			'delCallBack':null, //删除回调方法
			'thumbnail_doc' : '../../resources/uploadify/file.png',
			'uploadId' : 'uploadFile'
	}, options);
	
	// 未传递ID, 生成随机ID
	if (setting.id == null) {
		setting.id = randomString(8);
	}
	
	// 预览列表容器
	$list = $("#" + setting.priviewContent);
	$preview = $("#preview");
	if ($preview.length < 1) {
		$preview = $('<div id="preview" class="upload_preview"></div>').appendTo($list);
	}
	
	var showThumbnail = null;
	if (setting.type == "image") {
		showThumbnail = settings.base + setting.thumbnail;
	} else {
		showThumbnail = setting.thumbnail_doc;
	}
	
	var content = '<div id="' + setting.id + '" class="upload_append_list">'
					+ '<div class="file_bar">'
					+	'<div style="padding:5px;">'
					+		'<p class="file_name">' + setting.name + '</p>'
					+		'<span class="file_del" data-index="0" title="删除"></span>'		
					+	'</div>'
					+  '</div>'
					+ '<a style="height:100px;width:120px;" href="#" class="imgBox">'
					+ 	'<div class="uploadImg" style="width:105px">'
					+		'<img id="uploadImage_0" class="upload_image" src="' + showThumbnail + '" style="width:expression(this.width > 105 ? 105px : this.width)">'
					+ 	'</div>'
					+  '</a>'
					+'</div>';
	var $content = $(content);
	
	// 绑定显示操作栏事件
	$content.hover(
		function (e) {
			$(this).find(".file_bar").addClass("file_hover");
		},function (e) {
			$(this).find(".file_bar").removeClass("file_hover");
			//eval(setting.delCallBack + "(" + setting.id + ")");
		}
	);
	
	// 绑定删除事件
	$content.find(".file_del").click(function() {
		$content.fadeOut("fast", function() {$(this).remove()});
		$("#" + setting.uploadId).uploadify('settings', 'uploadLimit', swfuploadify.settings.uploadLimit + 1);
	});
	$preview.append($content);
	return setting.id;
}

(function($) {

	var zIndex = 100;

	// 消息框
	var $message;
	var messageTimer;
	$.message = function() {
		var message = {};
		if ($.isPlainObject(arguments[0])) {
			message = arguments[0];
		} else if (typeof arguments[0] === "string" && typeof arguments[1] === "string") {
			message.type = arguments[0];
			message.content = arguments[1];
		} else {
			return false;
		}

		if (message.type == null || message.content == null) {
			return false;
		}

		if ($message == null) {
			$message = $('<div class="xxMessage"><div class="messageContent message' + message.type + 'Icon"><\/div><\/div>');
			if (!window.XMLHttpRequest) {
				$message.append('<iframe class="messageIframe"><\/iframe>');
			}
			$message.appendTo("body");
		}

		$message.children("div").removeClass("messagewarnIcon messageerrorIcon messagesuccessIcon").addClass("message" + message.type + "Icon").html(message.content);
		$message.css({"margin-left": - parseInt($message.outerWidth() / 2), "z-index": zIndex ++}).show();

		clearTimeout(messageTimer);
		messageTimer = setTimeout(function() {
			$message.hide();
		}, 3000);
		return $message;
	}

	// 对话框
	$.dialog = function(options) {
		var settings = {
			width: 320,
			height: "auto",
			modal: true,
			ok: "确定",
			cancel: "取消",
			onShow: null,
			onClose: null,
			onOk: null,
			onCancel: null
		};
		$.extend(settings, options);

		if (settings.content == null) {
			return false;
		}

		var $dialog = $('<div class="modal-content"><\/div>');
		var $dialogTitle;
		//var $dialogClose = $('<div class="dialogClose"><\/div>').appendTo($dialog);
		var $dialogHeader;
		var $dialogClose;
		var $dialogContent;
		var $dialogBottom;
		var $dialogOk;
		var $dialogCancel;
		var $dialogOverlay;
		var $diglogMain;

		$dialogHeader = $('<div class="modal-header"></div>').appendTo($dialog);;

		$dialogClose = $('<button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>').appendTo($dialogHeader);
		if (settings.title != null) {
			$dialogTitle = $('<h4 id="myModalLabel" class="modal-title">Modal title</h4>').appendTo($dialogHeader);
		}
		//if (settings.type != null) {
		//	$dialogContent = $('<div class="dialogContent dialog' + settings.type + 'Icon"><\/div>').appendTo($dialog);
		//} else {
		//	$dialogContent = $('<div class="dialogContent"><\/div>').appendTo($dialog);
		//}
		$dialogContent = $('<div class="modal-body">' + settings.content + '<\/div>').appendTo($dialog);

		if (settings.ok != null || settings.cancel != null) {
			$dialogBottom = $('<div class="modal-footer"><\/div>').appendTo($dialog);
		}

		if (settings.cancel != null) {
			$dialogCancel = $('<button data-dismiss="modal" class="btn btn-default" type="button">' + settings.cancel + '</button>').appendTo($dialogBottom);
		}

		if (settings.ok != null) {
			$dialogOk = $('<button class="btn btn-primary" type="button">' + settings.ok + '</button>').appendTo($dialogBottom);
		}
		if (!window.XMLHttpRequest) {
			$dialog.append('<iframe class="dialogIframe"><\/iframe>');
		}
		//if (settings.modal) {

		$diglogMain = $('<div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade in" style="display: block;"><\/div>');
		$dialog.appendTo($('<div class="modal-dialog"></div>').appendTo($diglogMain));
		//}
		//$dialog.appendTo($dialogOverlay);

		if (settings.modal) {
			$dialogOverlay = $('<div class="dialogOverlay"><\/div>').appendTo("body");
		}

		$diglogMain.appendTo("body");

		//var dialogX;
		//var dialogY;
		//if (settings.title != null) {
		//	$dialogTitle.text(settings.title);
		//}
		$dialogContent.html(settings.content);
		//$dialog.css({"width": settings.width, "height": settings.height, "margin-left": - parseInt(settings.width / 2), "z-index": zIndex ++});
		dialogShow();

		if ($dialogClose != null) {
			$dialogClose.click(function() {
				dialogClose();
				return false;
			});
		}

		if ($dialogOk != null) {
			$dialogOk.click(function() {
				if (settings.onOk && typeof settings.onOk == "function") {
					if (settings.onOk($dialog) != false) {
						dialogClose();
					}
				} else {
					dialogClose();
				}
				return false;
			});
		}

		if ($dialogCancel != null) {
			$dialogCancel.click(function() {
				if (settings.onCancel && typeof settings.onCancel == "function") {
					if (settings.onCancel($dialog) != false) {
						dialogClose();
					}
				} else {
					dialogClose();
				}
				return false;
			});
		}

		function dialogShow() {
			if (settings.onShow && typeof settings.onShow == "function") {
				if (settings.onShow($dialog) != false) {
					$diglogMain.show();
					$dialogOverlay.show();
				}
			} else {
				$diglogMain.show();
				$dialogOverlay.show();
			}
		}
		function dialogClose() {
			if (settings.onClose && typeof settings.onClose == "function") {
				if (settings.onClose($dialog) != false) {
					$diglogMain.remove();
					$dialogOverlay.remove();
				}
			} else {
				$diglogMain.remove();
				$dialogOverlay.remove();
			}
		}
		return $dialog;
	}

	// 令牌
	$(document).ajaxSend(function(event, request, settings) {
		if (!settings.crossDomain && settings.type != null && settings.type.toLowerCase() == "post") {
			var token = getCookie("token");
			if (token != null) {
				request.setRequestHeader("token", token);
			}
		}
	});

	$(document).ajaxComplete(function(event, request, settings) {
		var loginStatus = request.getResponseHeader("loginStatus");
		var tokenStatus = request.getResponseHeader("tokenStatus");

		if (loginStatus == "accessDenied") {
			$.message("warn", "登录超时，请重新登录");
			setTimeout(function() {
				location.reload(true);
			}, 2000);
		} else if (loginStatus == "unauthorized") {
			$.message("warn", "对不起，您无此操作权限！");
		} else if (tokenStatus == "accessDenied") {
			var token = getCookie("token");
			if (token != null) {
				$.extend(settings, {
					global: false,
					headers: {token: token}
				});
				$.ajax(settings);
			}
		}
	});

})(jQuery);