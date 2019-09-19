/**********组织机构维护***************/


/**
 * 初始化资源树
 */
function initOrgainfoTree(){
    $('#tenantId').combobox({
        // url: '../../data/skill-priority.json',
        valueField: 'codeValue',
        textField: 'codeName',
        panelHeight: 'auto',
        editable: true,
        missingMessage: '请选择',
        loader: function (param, success, error) {
            param = {

            };
            $.ajax({
                url: "/sam/tsamtenantinfo/qrytenantinfo",
                dataType: 'json',
                type: "POST",
                data: param,
                success: function (data) {
                    var items = $.map(data.list, function (item, index) {
                        return {
                            codeValue: item["tenantId"],
                            codeName: item["tenantName"]
                        };
                    });
                    success(items);
                },
                error: function () {
                    error.apply(this, arguments);
                }
            });
        },
        onLoadSuccess: function (data) {
        }
    });
    var token = AjaxUtilsTemp.getToken();
    var setting = {
        async : {
            enable : true, // 设置 zTree是否开启异步加载模式
            url: "/sam/tsamorgainfo/selectSamOrgaTree?access_token="+token,
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
                initOrgainfoGrid(treeNode.id);
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

function selectOrga(){
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();
    if(nodes.length == 0){
        var fristnodes = zTree.getNodes();
        initOrgainfoGrid(fristnodes[0].id);
    }else{
        initOrgainfoGrid(nodes[0].id);

    }

}


/**
 * 初始化Grid
 */
function initOrgainfoGrid(id){
    var params={orgaName:$("#searchOrgaName").val()};
    $('#dg').datagrid({
        rownumbers: false, //行号
        pagination: true, //分页控件
        pageSize: 10,
        pageList: [10, 20, 50],
        striped:true,
        fixRowHeight:600,
        loadMsg:"正在努力加载数据,表格渲染中...",
        columns:[[
            {field :'ck',checkbox : true,width : '10%' },
            {field:'orgaId',title:'组织机构编号',width:'23%'},
            {field:'orgaName',title:'组织机构名称',width:'25%'},
            {field:'orgaTypeName',title:'组织机构类型',width:'10%'},
            {field:'orgaStateName',title:'组织机构状态',width:'10%'},
            {field:'tenantName',title:'所属租户',width:'20%'},
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
            var params={
                "orgaName":$("#searchOrgaName").val(),
                "tenantName":$("#searchTenantName").val(),
                "page":pageNum,
                "start":start};
            // $.ajax({
            //     url: "/tsamorgainfo/selectTSamOrgaGrid?nodeId="+id, //获取数据后台接口
            //     method: "GET",
            //     data: params,
            //     dataType: "json",
            //     success: function (data) {
            //         success(data);
            //     }
            // });

            AjaxUtilsTemp.commonAjax('GET','/sam/tsamorgainfo/selectTSamOrgaGrid?nodeId='+id,params,true,'text/plain',function (data) {
                success(jQuery.parseJSON(data));
            },function () {
                $.messager.alert("提示","获取数据异常!");
            },'json');


        },


    });
}


function rowformater(value, row, index) {
    var orgaId = row.orgaId;
    return  "<a onclick='openOrgaEditView(\""+orgaId+"\");'>编辑</a>";
}



function  openOrgaEditView(orgaId) {

    $('#pop-window').window('open');
    AjaxUtilsTemp.commonAjax('GET','/sam/tsamorgainfo/'+orgaId,null,true,'text/plain',function (data) {
        $(".orgaId").val(data.ORGAID);
        $(".orgaCode").val(data.ORGACODE);
        $(".superOrgaCode").val(data.SUPERORGACODE);
        $(".orgaName").textbox('setValue', data.ORGANAME);
        $(".orgaTypeId").combobox('setValue', data.ORGATYPEID);
        $(".orgaState").combobox('setValue', data.ORGASTATE);
        $(".orgaDesc").textbox('setValue', data.ORGANAME);
        $("#tenantId").combobox('setText', data.tenantName);
        $("#tenantId").combobox('setValue', data.TENANTID);
      },'json');


}



function resultSearch(){
    $("#searchOrgaName").textbox('setValue',"");
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();
    if(nodes.length == 0){
        var fristnodes = zTree.getNodes();
        initOrgainfoGrid(fristnodes[0].id);
    }else{
        initOrgainfoGrid(nodes[0].id);
    }

}


function openOrgaView() {
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();
    if(nodes.length == 0){
        $.messager.alert('提示', '请选择树某个节点进行新增。');
        return;
    }
    if(nodes[0].level == 5){
        $.messager.alert('提示', '树节点深度限制为5级!');
        return;
    }
    $('#pop-window').window('open');
    $(".form").form('clear');
    $(".orgaTypeId").combobox("setValue",-1);
    $(".orgaState").combobox("setValue",-1);
}



//新增修改入口,根据主键id 判断是新增还是修改
function dynameicOrgaOperation(){
    var orgaId = $(".orgaId").val();
    if(orgaId == "" || orgaId == null){
        saveOrgaInfo();
    }else{
        updateOrgaInfo();
    }
}

function ZtreeNode(id, pId, name) {//定义ztree的节点类
    this.id = id;
    this.pId = pId;
    this.name = name;
}




function ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("#pop-window");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在加载数据，请稍候。。。").appendTo("#pop-window").css({display:"block",left:($(document.body).outerWidth(true) - 1100) / 2,top:($(window).height() - 240) / 2});
}
function ajaxLoadEnd(){
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}


//新增
function saveOrgaInfo(){
    //选中那个节点就绑定在哪个节点下，没有选就默认是最顶级。
    var SUPERGROUPCODE;
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();

    var orgaName = $(".orgaName").textbox('getValue');
    if(orgaName == "" || orgaName == null){
        $.messager.alert('提示', '组织机构名称必填。');
        return;
    }

    if(orgaName.length>=10){
        $.messager.alert('提示', '组织机构名称长度过长,最长不超过10个字符。');
        return;
    }


    var result = selectRepeatOrgaName(orgaName, nodes[0].id);
    if(result !=0){
        $.messager.alert('提示', '组织机构名称重复,请重新填写。');
        return;
    }
    if($(".orgaState").combobox('getValue') == "" || $(".orgaState").combobox('getValue') == null || $(".orgaState").combobox('getValue') == "-1"){
        $.messager.alert('提示', '请选择组织机构状态。');
        return;
    }

    if($(".orgaTypeId").combobox('getValue') == "" || $(".orgaTypeId").combobox('getValue') == null || $(".orgaTypeId").combobox('getValue') == "-1"){
        $.messager.alert('提示', '请选择组织机构类型。');
        return;
    }
    var tenantId = $("#tenantId").combobox('getValue');

    if(tenantId == "" || tenantId == null){
        $.messager.alert('提示', '请选择组所属租户。');
        return;
    }
    ajaxLoading();
    if(nodes[0] != null){
        SUPERORGACODE = nodes[0].id;
    }else{
        SUPERORGACODE = '1';
    }
    var data = {SUPERORGACODE:""+SUPERORGACODE+"",TENANTID:tenantId,ORGANAME:$(".orgaName").val(), ORGASTATE:$(".orgaState").combobox('getValue'),ORGATYPEID:$('.orgaTypeId').combobox('getValue'),orgaDesc:$(".orgaDesc").val()};
    AjaxUtilsTemp.commonAjax('POST','/sam/tsamorgainfo/insterTSamOrgaInfo',JSON.stringify(data),true,'application/json',function (data) {
        ajaxLoadEnd();
        if(data.flag == 1){
            $('#dg').datagrid('reload'); //刷新grid
            //刷新父节点
            var parentZNode = zTree.getNodeByParam("id", SUPERORGACODE, null); //获取父节点
            zTree.addNodes(parentZNode, new ZtreeNode(data.orgaId,SUPERORGACODE,$(".orgaName").val()), true);
            zTree.expandNode(parentZNode,true,true);
            $.messager.alert('提示', '新增成功!');
            $('#pop-window').window('close');

        }else{
            $.messager.alert('提示', '新增失败!');
        }
    },function () {
        $.messager.alert("提示","新增失败!");
    },'json');



    // $.ajax({
    //     type: "POST",
    //     url: "/tsamorgainfo/insterTSamOrgaInfo",
    //     data: JSON.stringify(data),
    //     dataType: "json",
    //     contentType:"application/json",
    //     success: function(data){
    //         ajaxLoadEnd();
    //         if(data.flag == 1){
    //             $('#dg').datagrid('reload'); //刷新grid
    //             //刷新父节点
    //             var parentZNode = zTree.getNodeByParam("id", SUPERORGACODE, null); //获取父节点
    //             zTree.addNodes(parentZNode, new ZtreeNode(data.orgaId,SUPERORGACODE,$(".orgaName").val()), true);
    //             zTree.expandNode(parentZNode,true,true);
    //             $.messager.alert('提示', '新增成功!');
    //             $('#pop-window').window('close');
    //
    //         }else{
    //             $.messager.alert('提示', '新增失败!');
    //         }
    //
    //     }
    // });

}


function selectRepeatOrgaName(orgaName,nodeId){
    var result=0;
    AjaxUtilsTemp.commonAjax('GET','/sam/tsamorgainfo/selectRepeatOrgaName',{"orgaName":orgaName,"nodeId":nodeId},false,'application/json',function (data) {
        if(data.resultVal !='1'){
            $.messager.alert('提示', '获取角色重名异常.');
            return;
        }else{
            result = data.resultMsg;
        }
    },function () {
        $.messager.alert("提示","获取角色重名异常!");
    },'json');
    return result;

}


//修改
function updateOrgaInfo(){


    if($(".orgaName").val() == "" || $(".orgaName").val() == null){
        $.messager.alert('提示', '组织机构名称必填。');
        return;
    }

    if($(".orgaName").val().length>=10){
        $.messager.alert('提示', '组织机构名称长度过长,最长不超过10个字符。');
        return;
    }



    if($(".orgaState").combobox('getValue') == "" ||  $(".orgaState").combobox('getValue') == null){
        $.messager.alert('提示', '组织机构类型。');
        return;
    }

    if($(".orgaTypeId").combobox('getValue') == "" ||  $(".orgaTypeId").combobox('getValue') == null){
        $.messager.alert('提示', '组织机构状态。');
        return;
    }
    var tenantId = $("#tenantId").combobox('getValue');
    var tenantId1 = $("#tenantId").combobox('getText');
    if(tenantId == "" || tenantId == null){
        $.messager.alert('提示', '请选择组所属租户。');
        return;
    }
    ajaxLoading();
    //选中那个节点就绑定在哪个节点下，没有选就默认是最顶级。
    var data = {TENANTID:tenantId,SUPERORGACODE:$(".superOrgaCode").val(),ORGACODE:$(".orgaCode").val(),ORGAID:$(".orgaId").val(),ORGANAME:$(".orgaName").val(), ORGASTATE:$(".orgaState").combobox('getValue'),ORGATYPEID:$(".orgaTypeId").combobox('getValue'),orgaDesc:$(".orgaDesc").val()};


    AjaxUtilsTemp.commonAjax('POST','/sam/tsamorgainfo/updateTSamOrgaInfo',JSON.stringify(data),true,'application/json',function (data) {
        ajaxLoadEnd();
        //关闭弹出层
        if(data == 1){
            $('#pop-window').window('close');
            $('#dg').datagrid('reload'); //刷新grid
            //新增成功 往tree节点上append数据。
            var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            var node=treeObj.getNodeByParam('id',$(".orgaId").val());
            node.name=$(".orgaName").val();
            treeObj.updateNode(node);
        }else{
            $.messager.alert('提示', '修改失败!');
        }

    },function () {
        $.messager.alert("提示","修改数据失败!");
    },'json');


    // $.ajax({
    //     type: "POST",
    //     url: "/tsamorgainfo/updateTSamOrgaInfo",
    //     data: JSON.stringify(data),
    //     dataType: "json",
    //     contentType:"application/json",
    //     success: function(data){
    //         ajaxLoadEnd();
    //         //关闭弹出层
    //         if(data == 1){
    //             $('#pop-window').window('close');
    //             $('#dg').datagrid('reload'); //刷新grid
    //             //新增成功 往tree节点上append数据。
    //             var treeObj = $.fn.zTree.getZTreeObj("menuTree");
    //             var node=treeObj.getNodeByParam('id',$(".orgaId").val());
    //             node.name=$(".orgaName").val();
    //             treeObj.updateNode(node);
    //         }else{
    //             $.messager.alert('提示', '修改失败!');
    //         }
    //
    //     }
    // });

}


/**
 * 删除
 */
function deleteOrga(){
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var row = $('#dg').datagrid('getSelections');
    if(row == null || row.length == 0){
        $.messager.alert('提示', '请选择一条数据删除。');
        return;
    }

    var orgaIds=new Array();
    var nodes=new Array();
    for(var i = 0;i<row.length;i++){
        var node=zTree.getNodeByParam('id',row[i].orgaId);
        var count = selectSamOrgaCountBySuperCode(row[i].orgaId);
        if(count ==0 ){
            nodes.push(node);
        }
         orgaIds.push(row[i].orgaId);
    }

    $.messager.confirm("提示","确认删除？",function(sure) {
        if(sure) {
            AjaxUtilsTemp.commonAjax('GET', '/sam/tsamorgainfo/deleteOrga', {"orgaIds": orgaIds.join(",")}, true, 'application/json', function (data) {
                if (data.resultVal == "1") {
                    if (data.resultMsg == "") {
                        $.messager.alert('提示', '组织删除成功。');
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
                    $.messager.alert('提示', '组织删除失败。');
                    return;

                }
            },function () {
                $.messager.alert("提示","删除组织失败!");
            }, 'json');
        }else{
            return false;
        }



        //     var rowDatga = $('#dg').datagrid('getChecked');
        //     $.ajax({
        //         type: "GET",
        //         url: "/tsamorgainfo/deleteOrga",
        //         data:{"orgaIds":orgaIds.join(",")},
        //         dataType: "json",
        //         success: function(data){
        //             if(data.resultVal == "1"){
        //                 if(data.resultMsg == ""){
        //                     $.messager.alert('提示', '组织删除成功。');
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
        //                 $.messager.alert('提示', '组织删除失败。');
        //                 return;
        //
        //             }
        //         }
        //     });
        // }else{
        //     return false;
        // }
    });

}



//查询是否有子节点
function selectSamOrgaCountBySuperCode(orgaId){
    var count="";
    // $.ajax({
    //     type: "GET",
    //     url: "/tsamorgainfo/selectSamOrgaCountBySuperCode/"+orgaId,
    //     dataType: "json",
    //     async:false,
    //     success: function(data){
    //         count = data.resultMsg;
    //     }
    // });
    AjaxUtilsTemp.commonAjax('GET','/sam/tsamorgainfo/selectSamOrgaCountBySuperCode/'+orgaId,null,false,'text/plain',function (data) {
        count = data.resultMsg;
    },function () {
        $.messager.alert("提示","获取子节点数据失败!");
    },'json');
    return count;
}



/**
 * 刷新当前选择节点的父节点
 */
function refreshParentNode(parentTId) {
    var zTree = $.fn.zTree.getZTreeObj("menuTree"),
        type = "refresh",
        silent = false,
        nodes = zTree.getSelectedNodes();
    var parentNode = zTree.getNodeByTId(nodes.tId);
    /*选中指定节点*/
    zTree.selectNode(parentNode);
    zTree.reAsyncChildNodes(parentNode, type, silent);
    zTree.expandNode(parentNode,true,true,false,false);
}




