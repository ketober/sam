// require.config({
//     urlArgs: "date=" + _Config_.date.getDate(),
//     paths: _PATH_,
//     shim: {
//         "layer": {
//             deps: ["jquery"],
//             exports: "layer"
//         }
//     }
// });
define(['jquery'], function ($) {
    /**
     * 工具对象
     */
    var AjaxUtils = window.ajaxUtils = {
        commonAjax: function myAjax(method, url, data, successCallback,errorCallback, type) {
            var tokenInfo;
            var arr,reg=new RegExp("(^| )"+"tokenInfo"+"=([^;]*)(;|$)"); //正则匹配
            if(arr=document.cookie.match(reg)){
                tokenInfo= unescape(arr[2]);
            }
            var token='';
            if(typeof tokenInfo == 'undefined'){
                token='';
            }else{
                token = JSON.parse(tokenInfo).access_token;
            }
            $.ajax({
                headers: {
                    Authorization: "Bearer " + token,
                },
                type: method,
                url: url,
                data: data,
                async: true,
                error: function (request) {
                    errorCallback();
                },
                success: function (result) {
                    successCallback(result);
                }
            });
        },
        commonAjaxAsync: function myAjax(method, url,async, data, successCallback,errorCallback, type) {
            var tokenInfo;
            var arr,reg=new RegExp("(^| )"+"tokenInfo"+"=([^;]*)(;|$)"); //正则匹配
            if(arr=document.cookie.match(reg)){
                tokenInfo= unescape(arr[2]);
            }
            var token='';
            if(typeof tokenInfo == 'undefined'){
                token='';
            }else{
                token = JSON.parse(tokenInfo).access_token;
            }
            $.ajax({
                headers: {
                    Authorization: "Bearer " + token,
                },
                type: method,
                url: url,
                data: data,
                async: async,
                error: function (request) {
                    errorCallback();
                },
                success: function (result) {
                    successCallback(result);
                }
            });
        },
        getToken: function myAjax(method, url, data, callback, type) {
            var tokenInfo;
            var arr,reg=new RegExp("(^| )"+"tokenInfo"+"=([^;]*)(;|$)"); //正则匹配
            if(arr=document.cookie.match(reg)){
                tokenInfo= unescape(arr[2]);
            }
            var token='';
            if (typeof tokenInfo == 'undefined'){
                // if (window != window.top) {
                //     window.top.location.href = window.location.href;
                // }
                // window.top.location.href=window.oauthLogin;
                // window.top.location.href="http://10.21.57.137:18081/sso/oauth/login";
                token='';
            }else{
                token = JSON.parse(tokenInfo).access_token;
            }
            return token;
        }

    };
    return AjaxUtils;
});