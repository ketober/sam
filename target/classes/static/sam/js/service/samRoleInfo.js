/**********角色维护***************/
/**
 * 初始化资源树
 */
var GrantTree = null;

 function initRoleTree(){
     var token = AjaxUtilsTemp.getToken();
     var setting = {
        async : {
            enable : true, // 设置 zTree是否开启异步加载模式
            url: "/sam/tsamrole/selectTSamRoleTree?sync=false&access_token="+token,
            autoParam : [ "id" ]	// 异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1
        },
        data:{ // 必须使用data
            simpleData : {
                enable : true,
                idKey : "id", // id编号命名 默认
                pIdKey : "pId", // 父id编号命名 默认
                rootPId : 0	// 用于修正根节点父节点数据，即 pIdKey 指定的属性值
            }
        },
        // 回调函数
        callback : {
            onClick : function(event, treeId, treeNode, clickFlag) {
                // 判断是否父节点
               // if(!treeNode.isParent){
                    initRoleGrid(treeNode.id);
                //}
            },
            //捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
            onAsyncError : zTreeOnAsyncError,
            onAsyncSuccess : zTreeOnAsyncSuccess
        }
    };

    // 加载错误提示
    function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
        alert("加载错误：" + XMLHttpRequest);
    };


     function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){
         var treeObj = $.fn.zTree.getZTreeObj(treeId);
         var nodes = treeObj.getNodes();
         if (nodes.length>0) {
             for(var i=0;i<nodes.length;i++){
                 if(nodes[i].pId == "0"){
                     treeObj.expandNode(nodes[i], true, false, false);//默认展开第一级节点
                 }
             }
         }
     }
     GrantTree = $.fn.zTree.init( $("#menuTree"), setting);
}


function selectRole() {
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();
    if(nodes.length == 0){
        var fristnodes = zTree.getNodes();
        initRoleGrid(fristnodes[0].id);
    }else{
        initRoleGrid(nodes[0].id);

    }
}

/**
 * 初始化Grid
 */
function initRoleGrid(id){
    $('#dg').datagrid({
        rownumbers: false, //行号
        pagination: true, //分页控件
        fitColumns:true,
        pageSize: 10,
        pageList: [10, 20, 50],
        striped:true,
        //height:450,
        loadMsg:"正在努力加载数据,表格渲染中...",
        columns:[[
            {field :'ck',checkbox : true,width : '10%' },
            {field:'roleName',title:'角色名称',width:'25%'},
            {field:'description',title:'描述信息',width:'38%'},
            // {field:'moduleName',title:'模块名称',width:'15%'},
            {field:'count',title:'拥有员工数',width:'20%'},
            {field: 'opera', title: '操作', width: '15%',formatter: rowformater}

        ]],

        loader: function (param, success) {
            var start = (param.page - 1) * param.rows + 1;
            var pageNum = param.rows;
            var params={roleName:$("#searchRoleName").val(),"page":pageNum,"start":start};

            AjaxUtilsTemp.commonAjax('GET','/sam/tsamrole/selectTSamRoleGrid?nodeId='+id,params,true,'text/plain',function (data) {
                success(jQuery.parseJSON(data));
            },function () {
                $.messager.alert("提示","获取角色数据失败!");
            },'json');
            // $.ajax({
            //     url: "/tsamrole/selectTSamRoleGrid?nodeId="+id,
            //     method: "GET",
            //     data: params,
            //     dataType: "json",
            //     success: function (data) {
            //         success(data);
            //     }
            // });
        },

    });


}



//回调刷新
window.top["reloadRoleGird"]=function(){
    $('#dg').datagrid('reload');
};


function rowformater(value, row, index) {
    var roleId = row.roleId;
    return  "<a onclick='openRoleEditView(\""+roleId+"\");'>编辑</a>";
}



function resultSearch(){
    $("#searchRoleName").textbox('setValue',"");
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();
    if(nodes.length == 0){
        var fristnodes = zTree.getNodes();
        initRoleGrid(fristnodes[0].id);
    }else{
        initRoleGrid(nodes[0].id);
    }


}

function  openRoleEditView(roleId) {
    layer.open({
        type: 2,
        title:"角色修改",
        area: ['900px', '550px'],
        content: "/sam/sam/html/SamRoleEdit.html?roleId="+roleId
    });


    // var title="角色修改";
    // var url="/sam/sam/html/SamRoleEdit.html?roleId="+roleId;
    // var str = $(window.parent.document).find(".iframe_"+roleId+"");
    // var jq = top.jQuery;
    // if(str.length >0){
    //     var selTab = jq('#tabs').tabs('getSelected');
    //     jq('#tabs').tabs('update', {
    //         tab: selTab,
    //         options: {
    //             content:'<iframe src="'+url+'"  frameBorder="0" border="0" scrolling="auto"  style="width: 100%; height: 100%;"/>',
    //         }
    //     });
    // }else{
    //     jq('#tabs').tabs('add',{
    //         title:title,
    //         content:'<iframe src="'+url+'" class="iframe_'+roleId+'" frameBorder="0" border="0" scrolling="auto"  style="width: 100%; height: 100%;"/>',
    //         closable:true
    //     });
    //}


}



function addSamRole(){
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();
    if(nodes.length == 0){
        $.messager.alert('提示', '请选择树某个节点进行新增。');
        return;
    }

    if(nodes[0].level == 3){
        $.messager.alert('提示', '树节点深度限制为3级!');
        return;
    }
    $('#pop-window').window('open');
    $(".form").form('clear');


}






function deleteRole(){
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var row = $('#dg').datagrid('getSelections');
    if(row == null){
        $.messager.alert('提示', '请选择一条数据删除。');
        return;
    }
    var roleIds=new Array();
    var nodes=new Array();
    for(var i = 0;i<row.length;i++){
        var node=zTree.getNodeByParam('id',row[i].roleId);
        var count = selectSamRoleCountBySuperCode(row[i].roleId);
        if(count ==0 ){
            nodes.push(node);
        }
        roleIds.push(row[i].roleId);

    }

    $.messager.confirm("提示","确认删除？",function(sure) {
        if (sure) {
            AjaxUtilsTemp.commonAjax('GET', '/sam/tsamrole/deleteSamRole', {"roleIds": roleIds.join(",")}, true, 'application/json', function (data) {
                if (data.resultVal == "1") {
                    if (data.resultMsg == "") {
                        $.messager.alert('提示', '角色删除成功。');
                    } else {
                        $.messager.alert('提示', data.resultMsg);
                    }
                    if (nodes.length > 0) {
                        for (var i = 0; i < nodes.length; i++) {
                            zTree.removeNode(nodes[i]);
                        }
                    }
                    $('#dg').datagrid('reload'); //刷新grid
                    return;
                } else {
                    $.messager.alert('提示', '角色删除失败。');
                    return;
                }
            },function () {
                $.messager.alert("提示","角色删除失败!");
            }, 'json');

        }else{
            return false;
        }

        //
        //     $.ajax({
        //         type: "GET",
        //         url: "/tsamrole/deleteSamRole",
        //         data:{"roleIds":roleIds.join(",")},
        //         dataType: "json",
        //         success: function(data){
        //             if(data.resultVal == "1"){
        //                 if(data.resultMsg == ""){
        //                     $.messager.alert('提示', '角色删除成功。');
        //                 }else{
        //                     $.messager.alert('提示', data.resultMsg);
        //                 }
        //                 if(nodes.length>0){
        //                     for(var i = 0;i<nodes.length;i++){
        //                         zTree.removeNode(nodes[i]);
        //                     }
        //                 }
        //                 $('#dg').datagrid('reload'); //刷新grid
        //                 return;
        //             }else{
        //                 $.messager.alert('提示', '角色删除失败。');
        //                 return;
        //             }
        //         }
        //     });
        //
        // }else{
        //     return false;
        // }
    });

}



//查询是否有子节点
function selectSamRoleCountBySuperCode(roleId){
    var count="";
    // $.ajax({
    //     type: "GET",
    //     url: "/tsamrole/selectSamRoleCountBySuperCode/"+roleId,
    //     dataType: "json",
    //     async:false,
    //     success: function(data){
    //         count = data.resultMsg;
    //     }
    // });

    AjaxUtilsTemp.commonAjax('GET','/sam/tsamrole/selectSamRoleCountBySuperCode/'+roleId,null,false,'text/plain',function (data) {
        count = data.resultMsg;
    },function () {
        $.messager.alert("提示","查询子节点数据失败!");
    },'json');

    return count;
}



/**
 * 保存选中分配的人员
 */
function saveRoleStaff(){
    if($(".roleId").val() =="" || $(".roleId").val() == null){
        $.messager.alert('提示', '请先保存角色信息');
        return;
    }
    var data = $("#IncludeRole").datagrid("getRows");
    var arr = new Array();
    if(data.length>0){
        for(var i =0;i<data.length;i++){
            arr.push(new role($(".roleId").val(),data[i].staffId));
        }
    }else{
        arr.push(new role($(".roleId").val(),"null"));
    }
    $.ajax({
        type: "POST",
        url: "/tsamstaffrole/addStaffRoleBatch",
        data: JSON.stringify(arr),
        dataType: "json",
        contentType:"application/json",
        success: function(data){
            if(data == 1){
                $('#pop-window').window('close'); //关闭弹出层
                $('#dg').datagrid('reload'); //刷新grid
                //新增成功 往tree节点上append数据。
            }else{
                $.messager.alert("分配人员失败");
            }

        }
    });
}


function role(ROLEID,STAFFID){
    this.ROLEID=ROLEID;
    this.STAFFID=STAFFID;
}





function showEditRoleInfo(){
    var rowDatga = $('#dg').datagrid('getChecked');
    $('#pop-window').window('open');
    var freeRoleDomObj = $('#freeRole');
    var IncludeRoleDomObj = $('#IncludeRole');

    $.ajax({
        type: "GET",
        url: "/tsamrole/"+rowDatga[0].roleId,
        dataType: "json",
        success: function(data){
            $(".roleId").val(data.ROLEID);
            $(".roleCode").val(data.ROLECODE);
            $(".superCode").val(data.SUPERCODE);
            $(".roleName").textbox('setValue', data.ROLENAME);
            $(".description").textbox('setValue', data.DESCRIPTION);
            //待分配人员列表
            loadFreedistributeStaff(freeRoleDomObj);

            //已分配人员列表
            loaddistributeStaff(IncludeRoleDomObj);
        }
    });

}


//新增修改入口,根据主键id 判断是新增还是修改
function dynameicRoleOperation(){
    var roleId = $(".roleId").val();
    if(roleId == "" || roleId == null){
        saveRoleInfo();
    }else{
        updateRoleInfo();
    }
}



function ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("#pop-window");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("#pop-window").css({display:"block",left:($(document.body).outerWidth(true) - 900) / 2,top:($(window).height() - 245) / 2});
}
function ajaxLoadEnd(){
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}
function ZtreeNode(id, pId, name) {//定义ztree的节点类
    this.id = id;
    this.pId = pId;
    this.name = name;
}



function geTreObj() {
    return GrantTree;
}

//新增
function saveRoleInfo(){
    // //选中那个节点就绑定在哪个节点下，没有选就默认是最顶级。
    var SUPERCODE;
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();

    if(nodes[0] != null){
        SUPERCODE = nodes[0].id;
    }else{
        SUPERCODE = '1';
    }


    var roleName = $(".roleName").val();
    if(roleName == "" || roleName == null){
        $.messager.alert('提示', '角色名称必填。');
        return;
    }

    if(roleName.length>=10){
        $.messager.alert('提示', '角色名称长度过长,最长不超过10个字符。');
        return;
    }
    ajaxLoading();
    var data = {TENANTID:1,SUPERCODE:SUPERCODE,ROLENAME:roleName,DESCRIPTION:$(".description").val()};
    AjaxUtilsTemp.commonAjax('POST','/sam/tsamrole/insterTSamRoleInfo',JSON.stringify(data),true,'application/json',function (data) {
        ajaxLoadEnd();
        if(data.flag == 1){
            $('#dg').datagrid('reload'); //刷新grid
            var parentZNode = zTree.getNodeByParam("id", SUPERCODE, null); //获取父节点
            zTree.addNodes(parentZNode, new ZtreeNode(data.roleId,SUPERCODE,$(".roleName").val()), true);
            zTree.expandNode(parentZNode,true,true);
            $.messager.alert("提示","新增成功");
            $('#pop-window').window('close'); //关闭弹出层

        }else{
            $.messager.alert("提示","新增失败");
        }
    },function () {
        $.messager.alert("提示","新增角色失败!");
    },'json');




    //
    // $.ajax({
    //     type: "POST",
    //     url: "/tsamrole/insterTSamRoleInfo",
    //     data: JSON.stringify(data),
    //     dataType: "json",
    //     contentType:"application/json",
    //     success: function(data){
    //         ajaxLoadEnd();
    //         if(data.flag == 1){
    //             $('#dg').datagrid('reload'); //刷新grid
    //             var parentZNode = zTree.getNodeByParam("id", SUPERCODE, null); //获取父节点
    //             zTree.addNodes(parentZNode, new ZtreeNode(data.roleId,SUPERCODE,$(".roleName").val()), true);
    //             zTree.expandNode(parentZNode,true,true);
    //             $.messager.alert("提示","新增成功");
    //             $('#pop-window').window('close'); //关闭弹出层
    //
    //         }else{
    //             $.messager.alert("提示","新增失败");
    //         }
    //
    //     }
    // });
}



//修改
function updateRoleInfo(){

    if($(".roleName").val() == "" || $(".roleName").val() == null){
        $.messager.alert('提示', '角色名称必填。');
        return;
    }
    ajaxLoading();
    var data = {TENANTID:1,SUPERCODE:$(".superCode").val(),ROLECODE:$(".roleCode").val(),ROLEID:$(".roleId").val(),ROLENAME:$(".roleName").val(),DESCRIPTION:$(".description").val()};

    AjaxUtilsTemp.commonAjax('POST','/sam/tsamrole/updateTSamRoleInfo',JSON.stringify(data),true,'application/json',function (data) {
        ajaxLoadEnd();
        //关闭弹出层
        if(data == 1){
            $('#pop-window').window('close');
            //刷新grid
            $('#dg').datagrid('reload');

            var parentZNode = zTree.getNodeByParam("id", $(".superCode").val(), null); //获取父节点
            zTree.addNodes(parentZNode, new ZtreeNode("11",$(".superCode").val(),$(".roleName").val()), true);


        }else{
            $.messager.alert("提示","修改失败");
        }

    },function () {
        $.messager.alert("提示","修改角色失败!");
    },'json');

    // $.ajax({
    //     type: "POST",
    //     url: "/tsamrole/updateTSamRoleInfo",
    //     data: JSON.stringify(data),
    //     dataType: "json",
    //     contentType:"application/json",
    //     success: function(data){
    //         ajaxLoadEnd();
    //         //关闭弹出层
    //         if(data == 1){
    //             $('#pop-window').window('close');
    //             //刷新grid
    //             $('#dg').datagrid('reload');
    //
    //             var parentZNode = zTree.getNodeByParam("id", $(".superCode").val(), null); //获取父节点
    //             zTree.addNodes(parentZNode, new ZtreeNode("11",$(".superCode").val(),$(".roleName").val()), true);
    //
    //
    //         }else{
    //             $.messager.alert("提示","修改失败");
    //         }
    //
    //     }
    // });

}


