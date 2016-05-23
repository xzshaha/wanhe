/**
 * Created by wc on 2015/5/17.
 */

//(function($){
//    $.fn.UI = function(options){
//        var defaults = {
//            iconClass    :    'darkorange',
//            ButtonClass    :    'yellow'
//        };
//
//        $.extend(defaults,options);
//
//        $("i").removeClass("darkorange");
//        $("i").addClass(options.iconClass);
//    }
//})(jQuery);

jQuery.bar = function(options) {
    var defaults = {
        iconClass    :    'darkorange',
        ButtonClass    :    'yellow'
    };

    $.extend(defaults,options);

    $("i").removeClass("darkorange");
    $("i").addClass(options.iconClass);

    $(".btn").removeClass("btn-success");
    $(".btn").addClass(options.ButtonClass);

};
//jQuery.bar = function(param) {
//    alert('This function takes a parameter, which is "' + param + '".');
//};
