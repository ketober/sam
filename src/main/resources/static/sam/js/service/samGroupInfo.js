/**********工作组维护***************/
/**
 * 初始化资源树
 */
function initGroupTree(){
    var token = AjaxUtilsTemp.getToken();
    var setting = {
        async : {
            enable : true, // 设置 zTree是否开启异步加载模式
            url: "/sam/tsamgroupinfo/selectGroupTree?sync=false&access_token="+token,
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
                initGroupGrid(treeNode.id);
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

    $.fn.zTree.init( $("#menuTree"), setting);

}


function loadGroupTypeSelect(){
        AjaxUtilsTemp.commonAjax('GET','/sam/tsamgrouptype/queryGroupType',null,false,'text/plain',function (data) {
        if(data.result == '0000'){
            $('.groupTypeId').combobox({
                data : data.modules,//获取要显示的json数据
                valueField: 'GROUPTYPEID',
                textField: 'GROUPTYPENAME',
            });
            $(".groupTypeId").combobox('select',data.modules[0].GROUPTYPEID);
        }else{
            $.messager.alert('提示', '获取菜单下拉框接口异常。');
        }

    },function () {
            $.messager.alert("提示","获取菜单异常!");
        },'json');

}


/**
 * 初始化Grid
 */
function initGroupGrid(id){
    $('#dg').datagrid({
        rownumbers: true, //行号
        pagination: true, //分页控件
        pageSize: 10,
        pageList: [10, 20, 50],
        striped:true,
        loadMsg:"正在努力加载数据,表格渲染中...",
        columns:[[
            {field :'ck',checkbox : true,width : '10%' },
            {field:'groupId',title:'工作组编号',width:'10%'},
            {field:'groupName',title:'工作组名称',width:'20%'},
            {field:'groupTypeName',title:'工作组类型',width:'15%'},
            {field:'groupDesc',title:'工作组描述',width:'40%'},
            {field: 'opera', title: '操作', width: '10%',formatter: rowformater}

        ]],
        onLoadSuccess: function (data) {
            console.log(data);
            if (data == null){
                //自定义页面信息加载框

            }
        },
        loader: function (param, success) {
            var start = (param.page - 1) * param.rows + 1;
            var pageNum = param.rows;
            var params={groupName:$("#searchGroupName").val(),"page":pageNum,"start":start};

                  // $.ajax({
            //     url: "/tsamgroupinfo/selectGroupGrid?nodeId="+id, //获取数据后台接口
            //     method: "GET",
            //     data: params,
            //     dataType: "json",
            //     success: function (data) {
            //         success(data);
            //     }
            // });

            AjaxUtilsTemp.commonAjax('GET','/sam/tsamgroupinfo/selectGroupGrid?nodeId='+id,params,true,'text/plain',function (data) {
                success(jQuery.parseJSON(data));
            },function () {
                $.messager.alert("提示","获取数据异常!");
            },'json');

        },


    });


}


function openAddView() {
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
    $(".form").form('clear');
    $('#pop-window').window('open');

}


function selectGroup(){
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();
    if(nodes.length == 0){
        var fristnodes = zTree.getNodes();
               initGroupGrid(fristnodes[0].id);
    }else{
        initGroupGrid(nodes[0].id);

    }
}

function resultSearch() {
    $("#searchGroupName").textbox('setValue',"");
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();
    if(nodes.length == 0){
        var fristnodes = zTree.getNodes();
        initGroupGrid(fristnodes[0].id);
    }else{
        initGroupGrid(nodes[0].id);
    }

}

function rowformater(value, row, index) {
    var groupId = row.groupId;
    return  "<a onclick='openGroupEditView(\""+groupId+"\");'>编辑</a>";
}



function  openGroupEditView(groupId) {
    layer.open({
        type: 2,
        title:"工作组修改",
        area: ['950px', '550px'],
        content: "/sam/sam/html/SamGroupEdit.html?groupId="+groupId
    });


    // var title="工作组修改";
    // var url="/sam/sam/html/SamGroupEdit.html?groupId="+groupId;
    // var jq = top.jQuery;
    // jq('#tabs').tabs('add',{
    //     title:title,
    //     content:'<iframe src="'+url+'" frameBorder="0" border="0" scrolling="auto"  style="width: 100%; height: 100%;"/>',
    //     closable:true
    //
    // });
}



//新增修改入口,根据主键id 判断是新增还是修改
function dynameicGroupOperation(){
    var groupId = $(".groupId").val();
    if(groupId == "" || groupId == null){
        saveGroupInfo();
    }else{
        updateGroupInfo();
    }
}


function ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("#pop-window");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("#pop-window").css({display:"block",left:($(document.body).outerWidth(true) - 1100) / 2,top:($(window).height() - 240) / 2});
}
function ajaxLoadEnd(){
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}




//删除节点
function deleteGroup(){
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var row = $('#dg').datagrid('getSelections');
    if(row == null){
        $.messager.alert('提示', '请选择一条数据删除。');
        return;
    }
    var groupIds=new Array();
    var nodes=new Array();
    for(var i = 0;i<row.length;i++){
        var node=zTree.getNodeByParam('id',row[i].groupId);
        var count = selectSamGroupCountBySuperCode(row[i].groupId);
        if(count ==0 ){
            nodes.push(node);
        }
        groupIds.push(row[i].groupId);

    }
    $.messager.confirm("提示","确认删除？",function(sure) {
        if(sure){
            AjaxUtilsTemp.commonAjax('GET','/sam/tsamgroupinfo/deleteByPrimaryKey',{"groupIds":groupIds.join(",")},false,'text/plain',function (data) {
                if(data.resultVal == "1"){
                    if(data.resultMsg == ""){
                        $.messager.alert('提示', '工作组删除成功。');
                        if(nodes.length>0){
                            for(var i = 0;i<nodes.length;i++){
                                zTree.removeNode(nodes[i]);
                            }
                        }
                        $('#dg').datagrid('reload'); //刷新grid
                    }else{
                        $.messager.alert('提示', data.resultMsg);
                    }
                    return;
                }else{
                    $.messager.alert('提示', '工作组删除失败。');
                    return;
                }

             },'json');
            //     $.ajax({
            //     type: "GET",
            //     url: "/tsamgroupinfo/deleteByPrimaryKey",
            //     data:{"groupIds":groupIds.join(",")},
            //     dataType: "json",
            //     async:false,
            //     success: function(data){
            //         if(data.resultVal == "1"){
            //             if(data.resultMsg == ""){
            //                 $.messager.alert('提示', '工作组删除成功。');
            //             }else{
            //                 $.messager.alert('提示', data.resultMsg);
            //             }
            //             if(nodes.length>0){
            //                 for(var i = 0;i<nodes.length;i++){
            //                     zTree.removeNode(nodes[i]);
            //                 }
            //             }
            //             $('#dg').datagrid('reload'); //刷新grid
            //             return;
            //         }else{
            //             $.messager.alert('提示', '工作组删除失败。');
            //             return;
            //         }
            //     }
            // });
        }else{
            return false;
        }
    });


}


//查询是否有子节点
function selectSamGroupCountBySuperCode(groupId){
    var count="";
    AjaxUtilsTemp.commonAjax('GET','/sam/tsamgroupinfo/selectSamGroupCountBySuperCode/'+groupId,null,false,'text/plain',function (data) {
        count = data.resultMsg;
     },function () {
        $.messager.alert("提示","获取子节点数据异常!");
    },'json');
    //     $.ajax({
    //     type: "GET",
    //     url: "/tsamgroupinfo/selectSamGroupCountBySuperCode/"+groupId,
    //     dataType: "json",
    //     async:false,
    //     success: function(data){
    //         count = data.resultMsg;
    //     }
    // });
    return count;
}



//新增
function saveGroupInfo(){
    //选中那个节点就绑定在哪个节点下，没有选就默认是最顶级。
    var SUPERGROUPCODE;
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();

    if($(".orgaName").val() == "" || $(".orgaName").val() == null){
        $.messager.alert('提示', '工作组名称必填。');
        return;
    }

    if($(".orgaName").val().length>=10){
        $.messager.alert('提示', '工作组名称长度过长,最长不超过10个字符。');
        return;
    }

    if($(".groupTypeId").combobox('getValue') == "" ||  $(".groupTypeId").combobox('getValue') == null ||  $(".groupTypeId").combobox('getValue') == "-1"){
        $.messager.alert('提示', '请选择工作组类型。');
        return;
    }

    ajaxLoading();
    if(nodes[0] != null){
        SUPERORGACODE = nodes[0].id;
    }else{tsamgroupinfo
        SUPERORGACODE = '1';
    }
    var parame = {SUPERGROUPCODE:""+SUPERORGACODE+"",GROUPNAME:$(".orgaName").val(), GROUPTYPEID:$(".groupTypeId").combobox('getValue'),GROUPDESC:$(".groupDesc").val()};
    AjaxUtilsTemp.commonAjax('POST','/sam/tsamgroupinfo/insterGroupInfo',JSON.stringify(parame),true,'application/json',function (data) {
        ajaxLoadEnd();
        if(data.flag == 1){
            $('#dg').datagrid('reload'); //刷新grid
            var parentZNode = zTree.getNodeByParam("id", SUPERORGACODE, null); //获取父节点
            zTree.addNodes(parentZNode, new ZtreeNode(data.groupId,SUPERORGACODE,$(".orgaName").val()), true);
            zTree.expandNode(parentZNode,true,true);
            $.messager.alert('提示',"新增成功");
            $('#pop-window').window('close');

        }else{
            $.messager.alert('提示',"新增失败");
        }
    },function () {
        $.messager.alert("提示","新增数据失败!");
    },'json');


    // $.ajax({
    //     type: "POST",
    //     url: "/tsamgroupinfo/insterGroupInfo",
    //     data: JSON.stringify(data),
    //     dataType: "json",
    //     contentType:"application/json",
    //     success: function(data){
    //         ajaxLoadEnd();
    //         if(data.flag == 1){
    //             $('#dg').datagrid('reload'); //刷新grid
    //             var parentZNode = zTree.getNodeByParam("id", SUPERGROUPCODE, null); //获取父节点
    //             zTree.addNodes(parentZNode, new ZtreeNode(data.groupId,SUPERGROUPCODE,$(".orgaName").val()), true);
    //             zTree.expandNode(parentZNode,true,true);
    //             $.messager.alert('提示',"新增成功");
    //             $('#pop-window').window('close');
    //
    //         }else{
    //             $.messager.alert('提示',"新增失败");
    //         }
    //
    //     }
    // });

}


function ZtreeNode(id, pId, name) {//定义ztree的节点类
    this.id = id;
    this.pId = pId;
    this.name = name;
}

//修改
function updateGroupInfo(){
    //选中那个节点就绑定在哪个节点下，没有选就默认是最顶级。

    var SUPERGROUPCODE;
    var treeObj =  $('.easyui-tree');
    var selData = treeObj.tree('getChecked');
    if(selData != null){
        SUPERGROUPCODE = selData.id;
    }else{
        SUPERGROUPCODE = '0';
    }
    if($(".orgaName").val() == "" || $(".orgaName").val() == null){
        $.messager.alert('提示', '工作组名称必填。');
        return;
    }
    if($(".groupTypeId").val() == "" || $(".groupTypeId").val() == null){
        $.messager.alert('提示', '工作组类型编号必选。');
        return;
    }
    ajaxLoading();
    var data = {SUPERGROUPCODE:""+SUPERGROUPCODE+"",GROUPCODE:$(".groupCode").val(),GROUPID:$(".groupId").val(),GROUPNAME:$(".orgaName").val(), GROUPTYPEID:$(".groupTypeId").val(),GROUPDESC:$(".groupDesc").val()};
    $.ajax({
        type: "POST",
        url: "/sam/tsamgroupinfo/updateGroupInfo",
        data: JSON.stringify(data),
        dataType: "json",
        contentType:"application/json",
        success: function(data){
            //关闭弹出层
            if(data == 1){
                $.messager.alert("修改成功");
                $('#pop-window').window('close');
                //新增成功 往tree节点上append数据。
                // var selected =treeObj.tree('getSelected');
                // treeObj.tree('update', {
                //     target: selected.target,
                //     text: $(".orgaName").val()
                // });

            }else{
                alert("修改失败");
            }

        }
    });

}


