function showEditRoleInfo(id){
    AjaxUtilsTemp.commonAjax('GET','/sam/tsamrole/'+id,null,false,'application/json',function (data) {
        $(".roleId").val(data.ROLEID);
        $(".roleCode").val(data.ROLECODE);
        $(".superCode").val(data.SUPERCODE);
        $(".roleName").textbox('setValue', data.ROLENAME);
        $(".description").textbox('setValue', data.DESCRIPTION);
        var freeRoleDomObj = $('#freeRole');
        var IncludeRoleDomObj = $('#IncludeRole');
        //待分配人员列表
        loadFreedistributeStaff(freeRoleDomObj,id);
        //已分配人员列表
        loaddistributeStaff(IncludeRoleDomObj);
        $(".showroleName").html(data.ROLENAME);
        $(".showroleNamePX").html(data.ROLENAME);
    },function () {
        $.messager.alert("提示","获取数据失败!");
    },'json');

    // $.ajax({
    //     type: "GET",
    //     url: "/tsamrole/"+id,
    //     dataType: "json",
    //     success: function(data){
    //         $(".roleId").val(data.ROLEID);
    //         $(".roleCode").val(data.ROLECODE);
    //         $(".superCode").val(data.SUPERCODE);
    //         $(".roleName").textbox('setValue', data.ROLENAME);
    //         $(".description").textbox('setValue', data.DESCRIPTION);
    //         var freeRoleDomObj = $('#freeRole');
    //         var IncludeRoleDomObj = $('#IncludeRole');
    //         //待分配人员列表
    //         loadFreedistributeStaff(freeRoleDomObj,id);
    //         //已分配人员列表
    //         loaddistributeStaff(IncludeRoleDomObj);
    //         $(".showroleName").html(data.ROLENAME);
    //         $(".showroleNamePX").html(data.ROLENAME);
    //     }
    // });

}




function initCombotree(){
    var token = AjaxUtilsTemp.getToken();
    $("#orgaId").combotree({
        multiple: true,
        checkbox : true,
        onlyLeafCheck : true,//只能叶子节点才能勾选
        url : "/sam/tsamorgainfo/selectSamOrgaTreeForCombotree?access_token="+token+"&opStaffId="+AjaxUtilsTemp.getOpStaffId(),
        onBeforeSelect : function(node){
            $(this).tree("check", node.target);//控制点击文字时也能勾选
            return false;
        },
        onBeforeCheck : function(node, checked){
            if(checked){//如果是勾选操作，则把之前选中的节点清除（不勾选）
                var nodes = $(this).tree("getChecked");
                if(nodes.length > 0){
                    for(var i=0; i<nodes.length; i++){
                        $(this).tree("uncheck", nodes[i].target);
                    }
                }
            }
        },
        onLoadSuccess : function(node, data){
            var valueOrgi = "请选择";
            if(valueOrgi != null && $.trim(valueOrgi) != ""){
                var comboObj = $("#orgaId");
                comboObj.combotree("setValue", valueOrgi);
            }
        }
    });
}



function loadModelSelect(){
    $.ajax({
        type: "GET",
        url: "/sam/Module/qryModule",
        dataType: "json",
        async:false,
        contentType:"application/json",
        success: function(data){
            if(data.result == '0000'){
                $('.modelId').combobox({
                    data : data.modules,//获取要显示的json数据
                    valueField: 'MODULEID',
                    textField: 'MODULENAME',
                });
                $(".modelId").combobox('select',data.modules[0].MODULEID);
                initMenuTree(data.modules[0].MODULEID);

            }else{
                $.messager.alert('提示', '获取菜单下拉框接口异常。');

            }
        }
    });
}


function searchStaffNameGrid(){
    var freeRoleDomObj = $('#freeRole');
    var roleId =  $(".roleId").val();
    //待分配人员列表
    loadFreedistributeStaff(freeRoleDomObj,roleId);
}


function resultSearch(){
    $(".staffId2").textbox('setValue',"");
    $(".staffIdMin2").textbox('setValue',"");
    $(".staffIdMax2").textbox('setValue',"");
    $(".staffName2").textbox('setValue',"");
    $(".staffIdStatus2").combobox('select',"00");
    $("#orgaId").combotree("setValue", "请选择");
    //待分配人员列表
    var freeRoleDomObj = $('#freeRole');
    loadFreedistributeStaff(freeRoleDomObj,$(".roleId").val());

    // var tab = window.parent.$("#tabs").tabs("getSelected");
    // var index = window.parent.$("#tabs").tabs("getTabIndex", tab);
    // window.parent.$("#tabs").tabs("close", index);
    // var index = parent.layer.getFrameIndex(window.name);
    // parent.layer.close(index);

}



//已分配人员列表
function loaddistributeStaff(domObj){
    var id = $(".roleId").val();
    var token = AjaxUtilsTemp.getToken();
    domObj.datagrid({
        url: "/sam/StaffInfo/selectStaffForRoleId?roleId="+id+"&access_token="+token,
        method:"GET",
        contentType: "application/json",
        pagination: false, //分页控件
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100, 150, 200, 300, 500],
        striped:true,
        fit : true,
        fixRowHeight:600,
        loadMsg:"正在努力加载数据,表格渲染中...",
        columns:[[
            {field :'ck',checkbox : true,width : '10%' },
            {field:'staffId',title:'账号ID',width:'50%'},
            {field:'staffName',title:'人员名称',width:'47%'},
        ]],
        onLoadSuccess: function (data) {
            console.log(data);
        },
    });
}


//待分配人员列表
function loadFreedistributeStaff(freeRoleDomObj,roleId){
    var token = AjaxUtilsTemp.getToken();

    // var params={"roleId":roleId,"staffId":$(".staffId2").val(),"staffIdMin":$(".staffIdMin2").val(),
    //     "staffIdMax":$(".staffIdMax2").val(),"staffName":$(".staffName2").val(),"orgaId":orgaId,
    //     "staffIdStatus":$(".staffIdStatus2").combobox('getValue')
    // };
    var orgaId = $("#orgaId").combotree("getValue");
    if(orgaId == undefined || orgaId == '请选择'){
        orgaId ="";
    }
    freeRoleDomObj.datagrid({
        url: "/sam/StaffInfo/selectStaffFreeRole?roleId="+roleId+"&access_token="+token+"&staffName="+$(".staffName2").val()+"&staffId="+$(".staffId2").val()+"&staffIdMin="+$(".staffIdMin2").val()+"&staffIdMax="+$(".staffIdMax2").val()+"&staffIdStatus="+$(".staffIdStatus2").combobox('getValue')+"&orgaId="+orgaId,
        method:"GET",
        contentType: "application/json",
        pagination: false, //分页控件
        pageSize: 20,
        pageList: [10, 20, 30, 50, 100, 150, 200, 300, 500],
        striped:true,
        fit : true,
        fixRowHeight:600,
        loadMsg:"正在努力加载数据,表格渲染中...",
        columns:[[
            {field :'ck',checkbox : true,width : '10%' },
            {field:'staffId',title:'账号ID',width:'50%'},
            {field:'staffName',title:'人员名称',width:'47%'},
        ]]
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


function  ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 280) / 2,top:($(window).height() - 245) / 2});
}
function ajaxLoadEnd(){
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}



//修改
function updateRoleInfo(){

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
    var data = {TENANTID:1,SUPERCODE:$(".superCode").val(),ROLECODE:$(".roleCode").val(),ROLEID:$(".roleId").val(),ROLENAME:roleName,DESCRIPTION:$(".description").val()};
    AjaxUtilsTemp.commonAjax('POST','/sam/tsamrole/updateTSamRoleInfo',JSON.stringify(data),true,'application/json',function (data) {
        ajaxLoadEnd();
        //关闭弹出层
        if(data.resultVal == "1"){
            //刷新grid
            //$('#dg').datagrid('reload');
            parent.$("#dg").datagrid('reload');
            $.messager.alert("提示","修改成功.");

        }else{
            $.messager.alert("提示","修改失败");
        }

    },function () {
        $.messager.alert("提示","修改数据失败!");
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
    //         if(data.resultVal == "1"){
    //             //刷新grid
    //             $('#dg').datagrid('reload');
    //             // window.top.reloadRoleGird.call(); //刷新
    //             // var treeObj  = top.window.frames[0].geTreObj();
    //             // var nownode = treeObj.getNodesByParam("id", $(".superCode").val(), null);
    //             // var parent=nownode[0].getParentNode();
    //             // treeObj.reAsyncChildNodes(parent, "refresh");
    //             //treeObj.expandNode(nownode[0], true);
    //
    //             $.messager.alert("提示","修改成功.");
    //
    //         }else{
    //             $.messager.alert("提示","修改失败");
    //         }
    //
    //     }
    // });

}

/**
 * 往右移动
 */
function gridDataRight() {
    var pageNumber=1;
    var selRows = $('#freeRole').datagrid('getChecked');
    if(selRows.length == 0){
        $.messager.alert("提示","请选择要分配的人员");
        return;
    }
    if(selRows.length>0){
        for(var i = 0;i<selRows.length;i++){
            if(selRows[i] !=undefined){
                $('#IncludeRole').datagrid('insertRow',{
                    index:i,
                    row:selRows[i]
                });
                var index = $('#freeRole').datagrid('getRowIndex',selRows[i]);
                $('#freeRole').datagrid('deleteRow', index);
            }else{
                return;
            }

        }
    }

    var pageData=$('#freeRole').datagrid("getData");
    if(pageData.rows.length == 0){
        var queryParams = $('#freeRole').datagrid('options').queryParams;
        if(queryParams.pageNumber !=undefined){
            queryParams.pageNumber=parseInt(queryParams.pageNumber)+parseInt(pageNumber);
        }else{
            queryParams.pageNumber=parseInt(pageNumber);
        }
        $('#freeRole').datagrid('options').queryParams=queryParams;
        $('#freeRole').datagrid('reload');
    }


}

/**
 * 往左移动
 */
function gridDataLeft() {
    var selRows = $('#IncludeRole').datagrid('getChecked');
    if(selRows.length == 0){
        $.messager.alert("提示","请选择要取消分配的人员");
        return;
    }
    if(selRows.length>0){
        for(var i = 0;i<selRows.length;i++){
            if(selRows[i] !=undefined){
                $('#freeRole').datagrid('insertRow', {
                    index: i,
                    row: selRows[i]
                });
                var index = $('#IncludeRole').datagrid('getRowIndex', selRows[i]);
                $('#IncludeRole').datagrid('deleteRow', index);
            }else{
                return;
            }

        }

    }
}


/**
 * 保存选中分配的人员
 */
function saveRoleStaff(){
    var data = $("#IncludeRole").datagrid("getRows");
    var arr = new Array();
    ajaxLoading();
    if(data.length>0){
        for(var i =0;i<data.length;i++){
            arr.push(new role($(".roleId").val(),data[i].staffId));
        }
    }else{
        arr.push(new role($(".roleId").val(),"null"))
    }
    //判断选择的用户是否存在互斥
    var result = mutuallyDoesexistRole(JSON.stringify(arr));
    if(result !=""){
       $.messager.alert("提示",result);
        ajaxLoadEnd();
        return;
    }

    AjaxUtilsTemp.commonAjax('POST','/sam/tsamstaffrole/addStaffRoleBatch',JSON.stringify(arr),true,'application/json',function (data) {
        ajaxLoadEnd();
        if(data.message.flag == "1"){
            $.messager.alert("提示","分配人员成功");
        }else{
            $.messager.alert("提示","分配人员失败"+data.message.message);
        }
    },function () {
        $.messager.alert("提示","分配人员失败!");
    },'json');


    // $.ajax({
    //     type: "POST",
    //     url: "/tsamstaffrole/addStaffRoleBatch",
    //     data: JSON.stringify(arr),
    //     dataType: "json",
    //     contentType:"application/json",
    //     success: function(data){
    //         ajaxLoadEnd();
    //         if(data.message.flag == "1"){
    //             $.messager.alert("提示","分配人员成功");
    //         }else{
    //             $.messager.alert("提示","分配人员失败"+data.message.message);
    //
    //         }
    //     }
    // });
}

function role(ROLEID,STAFFID){
    this.ROLEID=ROLEID;
    this.STAFFID=STAFFID;
}


function ajaxTreeLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在初始化数据，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 280) / 2,top:($(window).height() - 240) / 2});
}
function ajaxTreeLoadingEnd(){
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}


// $('.modelId').combobox({
//     onChange: function (newValue, oldValue) {
//         var modelId = $(".modelId").combobox('getValue');
//         initMenuTree(modelId);
//     }
//  });



/**
 * 赋权
 */
function initMenuTree(){
    var token = AjaxUtilsTemp.getToken();
    var parme="000";
    ajaxTreeLoading();
    var zTreeObj;
    var setting = {
        view: {
            dblClickExpand: false,
            selectedMulti: false
        },
        check: {
            enable: true,
            autoCheckTrigger: true
        },
        async: {
            dataType:"json",
            enable: true,
            url:"/sam/Auth/qryAuthTree?moduleId="+parme+"&access_token="+token,
            // autoParam:["moduleId", "001"],
            dataFilter: filter
        },

        data: {
            simpleData: {
                enable: true,
                idKey : "id",
                pIdKey : "pid",
                rootPId: 0
            }
        },
        callback : {
            onAsyncSuccess: zTreeOnAsyncSuccess,//异步加载完成调用
        }

    };
    $.fn.zTree.init($("#menuTree"), setting, null);
    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) {
            return null;
        }
        childNodes = childNodes['auths'];

        if(childNodes){
            for (var i=0, l=childNodes.length; i<l; i++) {
                childNodes[i].id = childNodes[i].ELEMENTID;
                childNodes[i].name = childNodes[i].ELEMENTNAME;
                childNodes[i].pid = childNodes[i].SUPERELEMENTCODE;
            }
        }
        return childNodes;
    };
    
    
    function  zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        var nodes = treeObj.getNodes();
        if (nodes.length>0) {
            for(var i=0;i<nodes.length;i++){
                if(nodes[i].pid == "0"){
                    treeObj.expandNode(nodes[i], true, false, false);//默认展开第一级节点
                }
            }
        }
        //勾选分配的数据
        queryAuthByEntityId();
        ajaxTreeLoadingEnd();
    }
}


// 递归，获取所有子节点
function getAllChildrenNodes(treeObj,array){
    for(var j=0;j<array.length;j++){
        f(treeObj, treeObj.getNodes()[0],array[j]);
    }

}


function f(treeObj,treeNode,id) {
    if (treeNode !=undefined && treeNode.isParent) {
        if(treeNode.id == id){
            treeNode.checked =true;
        }
        var childrenNodes = treeNode.children;
        if (childrenNodes) {
            for (var i = 0; i < childrenNodes.length; i++) {
                if(childrenNodes[i].id == id){
                    childrenNodes[i].checked = true;
                    treeObj.updateNode(childrenNodes[i]);
                    treeObj.expandNode(childrenNodes[i], true, false, false);//默认展开第一级节点
                }
                f(treeObj,childrenNodes[i],id);
            }
        }
    }
}



//查询角色对应的菜单
function queryAuthByEntityId() {
    var roleId = $(".roleId").val();
    var querydata={"entityId":roleId,"permitType":"2"};

    AjaxUtilsTemp.commonAjax('GET','/sam/tsampermit/queryAuthByEntityId',querydata,true,'text/plain',function (data) {
        var treeObj = $.fn.zTree.getZTreeObj("menuTree");
        var resultData = data.resultMsg;
        var arrayId = new Array();
        if (resultData.length > 0) {
            for (var i = 0; i < resultData.length; i++) {
                arrayId.push(resultData[i].authObjId);
            }
            getAllChildrenNodes(treeObj,arrayId);
        }
    },function () {
        $.messager.alert("提示","获取角色菜单失败!");
    },'json');

    // $.ajax({
    //     type: "POST",
    //     url: "/tsampermit/queryAuthByEntityId",
    //     data: querydata,
    //     dataType: "json",
    //     success: function(data) {
    //         var treeObj = $.fn.zTree.getZTreeObj("menuTree");
    //         var resultData = data.resultMsg;
    //         var arrayId = new Array();
    //         if (resultData.length > 0) {
    //             for (var i = 0; i < resultData.length; i++) {
    //                 arrayId.push(resultData[i].authObjId);
    //             }
    //             getAllChildrenNodes(treeObj,arrayId);
    //
    //         }
    //     }
    // });
}



//保存角色 模块菜单的关联关系
function saveroleMenuRelated(){
    //updateModelId()
    saveTsampermit();
}

//更新角色的modelId
function updateModelId(){
    var modelId = $(".modelId").combobox('getValue');
    if(modelId ==""){
        $.messager.alert("提示","请选择模块。");
        return;
    }
    var data = {ROLEID:$(".roleId").val(),MODULEID:modelId};
    AjaxUtilsTemp.commonAjax('POST','/sam/tsamrole/updateTSamRoleInfo',JSON.stringify(data),true,'text/plain',function (data) {
        if(data.resultVal == "2"){
            $.messager.alert("提示","保存模块失败");
        }
    },function () {
        $.messager.alert("提示","保存模块失败!");
    },'json');



    // $.ajax({
    //     type: "POST",
    //     url: "/tsamrole/updateTSamRoleInfo",
    //     data: JSON.stringify(data),
    //     dataType: "json",
    //     contentType:"application/json",
    //     success: function(data){
    //         if(data.resultVal == "2"){
    //             $.messager.alert("提示","保存模块失败");
    //         }
    //     }
    // });
}





// 角色分配菜单模块权限
function saveTsampermit() {
    ajaxLoading();
    var roleId = $(".roleId").val();
    var permitType="2";
    var arr = new Array();
    var treeObj=$.fn.zTree.getZTreeObj("menuTree");
    var nodes=treeObj.getCheckedNodes(true);
    for(var i=0;i<nodes.length;i++){
        if(nodes[i].ELEMENTCODE !=null && nodes[i].MODULEID !=null){
            arr.push(new tsamPermit(permitType,roleId,nodes[i].ELEMENTCODE,nodes[i].MODULEID));
        }
    }

    AjaxUtilsTemp.commonAjax('POST','/sam/tsampermit/updateEntityPermitAuth',JSON.stringify(arr),true,'application/json',function (data) {
        ajaxLoadEnd();
        if(data.resultVal == "1"){
            $.messager.alert("提示","角色分配权限成功");
        }else{
            $.messager.alert("提示",data.resultMsg);
        }
    },function () {
        $.messager.alert("提示","角色分配权限失败!");
    },'json');




    // $.ajax({
    //     type: "POST",
    //     method:'POST',
    //     url: "/tsampermit/updateEntityPermitAuth",
    //     data: JSON.stringify(arr),
    //     dataType: "json",
    //     contentType:"application/json",
    //     success: function(data){
    //         ajaxLoadEnd();
    //         if(data.resultVal == "1"){
    //             $.messager.alert("提示","角色分配权限成功");
    //         }else{
    //             $.messager.alert("提示",data.resultMsg);
    //         }
    //     }
    // });
}

/**
 *
 * @param PERMIT_ID, 主键id
 * PERMIT_TYPE, 2 ENTITY_ID, roleId AUTH_OBJ_ID, 菜单id MODULE_ID, 模块
 */

function tsamPermit(PERMITTYPE,ENTITYID,AUTHOBJID,MODULEID){
    this.PERMITTYPE=PERMITTYPE;
    this.ENTITYID=ENTITYID;
    this.AUTHOBJID=AUTHOBJID;
    this.MODULEID=MODULEID;
}










function getRoleTreeonCheckNodeId(){
    var treeObj=$.fn.zTree.getZTreeObj("roleTree"),
        nodes=treeObj.getCheckedNodes(true),
        v=$(".roleId").val()+",";
    for(var i=0;i<nodes.length;i++){
        if(nodes[i].pId !=0 ){
            if(nodes[i].id == $(".roleId").val()){
                $.messager.alert("提示","不能互斥当前角色。");
                return;
            }else{
                v+=nodes[i].id + ",";

            }
        }
    }
    return v
}



//保存互斥角色
function saveMutuallyExclusiveRole(){
    var roleIds = getRoleTreeonCheckNodeId();
    if(roleIds !=undefined){
        AjaxUtilsTemp.commonAjax('GET','/sam/tsamrole/mutuallyExclusiveRole?roleId='+roleIds,null,true,'application/json',function (data) {
            if(data.resultVal == "1"){
                $.messager.alert("提示",data.resultMsg);
            }else{
                $.messager.alert("提示",data.resultMsg);

            }
        },function () {
            $.messager.alert("提示","保存互斥角色失败!");
        },'json');
        // $.ajax({
        //     type: "GET",
        //     method:'GET',
        //     url: "/tsamrole/mutuallyExclusiveRole?roleId="+roleIds,
        //     dataType: "json",
        //     success: function(data){
        //         if(data.resultVal == "1"){
        //             $.messager.alert("提示",data.resultMsg);
        //         }else{
        //             $.messager.alert("提示",data.resultMsg);
        //
        //         }
        //     }
        // });
    }

}


/**
 * 查询勾选的互斥的角色
 */
function selectMutuallyExclusiveRole(roleId){
    AjaxUtilsTemp.commonAjax('GET','/sam/tsamrolemutex/selecMutexByRoleId?roleId='+roleId,null,false,'application/json',function (data) {
        var treeObj = $.fn.zTree.getZTreeObj("roleTree");
        var resultData = data.resultMsg;
        var arrayId = new Array();
        if (resultData.length > 0) {
            for (var i = 0; i < resultData.length; i++) {
                arrayId.push(resultData[i]);
            }
            getAllChildrenNodes(treeObj,arrayId);

        }
    },function () {
        $.messager.alert("提示","获取互斥角色失败!");
    },'json');


    // $.ajax({
    //     type: "GET",
    //     url: "/tsamrolemutex/selecMutexByRoleId?roleId="+roleId,
    //     dataType: "json",
    //     async:false,
    //     success: function(data) {
    //         var treeObj = $.fn.zTree.getZTreeObj("roleTree");
    //         var resultData = data.resultMsg;
    //         var arrayId = new Array();
    //         if (resultData.length > 0) {
    //             debugger;
    //             for (var i = 0; i < resultData.length; i++) {
    //                 arrayId.push(resultData[i]);
    //             }
    //             getAllChildrenNodes(treeObj,arrayId);
    //
    //         }
    //     }
    // });
}


function roleMutexTree(roleId){
    var token = AjaxUtilsTemp.getToken();
    var setting = {
        view: {
            dblClickExpand: false,
            selectedMulti: false
        },
        check: {
            enable: true,
            chkboxType:{ "Y" : "s", "N" : "ps" },
            autoCheckTrigger: true
        },
        async : {
            enable : true,
            url: "/sam/tsamrole/selectTSamRoleTree?sync=true&access_token="+token,
            autoParam : [ "id" ]
        },
        data:{ // 必须使用data
            simpleData : {
                enable : true,
                idKey : "id",
                pIdKey : "pId",
                rootPId : 0
            }
        },
        // 回调函数
        callback : {
            onClick : function(event, treeId, treeNode, clickFlag) {
            },
            //捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
            onAsyncError : zTreeOnAsyncError,
            onAsyncSuccess : function(event, treeId, treeNode, msg){
                selectMutuallyExclusiveRole(roleId);
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
        }
    };

    // 加载错误提示
    function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
        alert("加载错误：" + XMLHttpRequest);
    };
    $.fn.zTree.init( $("#roleTree"), setting);

}

/**
 * 判断是否有互斥关系
 * @param rebateMap
 * @returns {string}
 */
function mutuallyDoesexistRole(rebateMap){
    var message="";
    AjaxUtilsTemp.commonAjax('POST','/sam/tsamrole/mutuallyDoesexistRole',rebateMap,false,'application/json',function (data) {
        message = data.resultMsg;
    },function () {
        $.messager.alert("提示","获取互斥角色失败!");
    },'json');
    // $.ajax({
    //     type: "POST",
    //     url: "/tsamrole/mutuallyDoesexistRole",
    //     dataType: "json",
    //     contentType:"application/json",
    //     data:rebateMap,
    //     async:false,
    //     success: function(data) {
    //         message = data.resultMsg;
    //     }
    // });
    return message;
}



function resultForm(){
    // var tab = window.parent.$("#tabs").tabs("getSelected");
    // var index = window.parent.$("#tabs").tabs("getTabIndex", tab);
    // window.parent.$("#tabs").tabs("close", index);
    //showEditRoleInfo($(".roleId").val());

    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);

}

//重置人员分配
function resultGrid() {
    // var freeRoleDomObj = $('#freeRole');
    // var IncludeRoleDomObj = $('#IncludeRole');
    // //待分配人员列表
    // loadFreedistributeStaff(freeRoleDomObj,$(".roleId").val());
    // //已分配人员列表
    // loaddistributeStaff(IncludeRoleDomObj);
    var tab = window.parent.$("#tabs").tabs("getSelected");
    var index = window.parent.$("#tabs").tabs("getTabIndex", tab);
    window.parent.$("#tabs").tabs("close", index);
}
//重置互斥角色
function resultRoleMenuRelated() {
    // var modelId = $(".modelId").combobox('getValue');
    //     // initMenuTree(modelId);

    // var tab = window.parent.$("#tabs").tabs("getSelected");
    // var index = window.parent.$("#tabs").tabs("getTabIndex", tab);
    // window.parent.$("#tabs").tabs("close", index);
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);

}

function resultMutuallyExclusiveRole(){
    // roleMutexTree($(".roleId").val());
    // var tab = window.parent.$("#tabs").tabs("getSelected");
    // var index = window.parent.$("#tabs").tabs("getTabIndex", tab);
    // window.parent.$("#tabs").tabs("close", index);
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}