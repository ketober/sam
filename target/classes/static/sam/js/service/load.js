/**
 * Created by jiang on 2016/12/16.
 */
require.config({
    urlArgs:"date="+_Config_.date.getDate(),
    paths:_PATH_
});
define(["jquery"],function($){
    var Load={
        loading:function (){
            // $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
            $("<div id='loading' style='position:absolute; left:50%; top:50%; width:200px; height:20px; margin:-10px 0 0 -100px;z-index:1'></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block"});
        },
        loadEnd: function (){
            $("#loading").remove();
    }
};
    Load.loading();
    return Load;
});
