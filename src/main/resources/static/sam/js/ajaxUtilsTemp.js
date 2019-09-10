    /**
     * 工具对象
     */
    var AjaxUtilsTemp = window.ajaxUtilsTemp = {
        commonAjax: function myAjax(method, url, data, async, postType, successCallback,errorCallback) {
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
                contentType:postType,
                error: function (request) {
                    errorCallback();
                },
                success: function (result) {
                    console.log("result =====" + JSON.stringify(result));
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