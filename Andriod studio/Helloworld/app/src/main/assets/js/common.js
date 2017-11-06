/**
 * Created by Baron on 2016/12/18.
 */
 
var serveUrl="http://monitor.pro.youzewang.com/index.php?";
var domainUrl="http://monitor.pro.youzewang.com/";

$("head").append('<link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_464581_l114qgeu2fv18aor.css"/>');


var browser = {
    versions: function() {
        var u = navigator.userAgent,
            app = navigator.appVersion;
        return {
            android: u.indexOf("Android") > -1 || u.indexOf("Linux") >-1,
            iPhone: u.indexOf("iPhone") > -1 ,
            iPad: u.indexOf("iPad") >-1,
            iPod: u.indexOf("iPod") > -1,
        };
    } (),
    language: (navigator.browserLanguage || navigator.language).toLowerCase()
}

function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}


Date.prototype.format = function(fmt)
{ //author: meizz
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}


$(function()
{
    
    //$("body").append("<div style='height:160px;width:100px;background: #000;position: relative;z-index:100000;' onclick='window.location.reload();'>刷新</div>");

    $("html").fadeIn();

});


