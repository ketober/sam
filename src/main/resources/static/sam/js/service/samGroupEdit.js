

//新增修改入口,根据主键id 判断是新增还是修改
function dynameicGroupOperation(){
    var groupId = $(".groupId").val();
    if(groupId == "" || groupId == null){
        saveGroupInfo();
    }else{
        updateGroupInfo();
    }
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

    if($(".orgaName").val().length>=10){
        $.messager.alert('提示', '工作组名称长度过长,最长不超过10个字符。');
        return;
    }

    if($(".groupTypeId").combobox('getValue') == "" || $(".groupTypeId").combobox('getValue') == null || $(".groupTypeId").combobox('getValue') == "-1"){
        $.messager.alert('提示', '请选择工作组类型。');
        return;
    }
    ajaxLoading();
    var parame = {TENANTID:"1",SUPERGROUPCODE:$(".superGroupCode").val(),GROUPCODE:$(".groupCode").val(),GROUPID:$(".groupId").val(),GROUPNAME:$(".orgaName").val(), GROUPTYPEID:$(".groupTypeId").combobox('getValue'),GROUPDESC:$(".groupDesc").val()};



    AjaxUtilsTemp.commonAjax('POST','/sam/tsamgroupinfo/updateGroupInfo',JSON.stringify(parame),true,'application/json',function (data) {
        ajaxLoadEnd();
        if(data == 1){
            parent.$("#dg").datagrid('reload');
            $.messager.alert("提示","修改成功");
        }else{
            $.messager.alert("提示","修改失败");
        }

    },function () {
        $.messager.alert("提示","修改数据失败!");
    },'json');

    // $.ajax({
    //     type: "POST",
    //     url: "/tsamgroupinfo/updateGroupInfo",
    //     data: JSON.stringify(data),
    //     dataType: "json",
    //     contentType:"application/json",
    //     success: function(data){
    //
    //     }
    // });

}


function showEditGroupInfo(groupId){
    AjaxUtilsTemp.commonAjax('GET','/sam/tsamgroupinfo/'+groupId,null,true,'text/plain',function (data) {
        $(".groupId").val(data.GROUPID);
        $(".groupCode").val(data.GROUPCODE);
        $(".superGroupCode").val(data.SUPERGROUPCODE);
        $(".orgaName").textbox('setValue', data.GROUPNAME);
        $(".groupDesc").textbox('setValue', data.GROUPDESC);
        $(".groupTypeId").combobox('setValue', data.GROUPTYPEID);
        var freeRoleDomObj = $('#freeRole');
        var IncludeRoleDomObj = $('#IncludeRole');
        var freeRoleForPic = $('#freeRoleForPic');
        var IncludeRoleForPicObj = $('#IncludeRoleForPic');

        //待分配人员列表
        loadFreedistributeStaff(freeRoleDomObj,groupId,"");
        loadFreedistributeStaff(freeRoleForPic,groupId,"");

        //已分配人员列表
        loaddistributeStaff(IncludeRoleDomObj);

        IncludeRoleForPic(IncludeRoleForPicObj);

    },'json');


    //
    // $.ajax({
    //     type: "GET",
    //     url: "/tsamgroupinfo/"+groupId,
    //     dataType: "json",
    //     // contentType:"application/json",
    //     success: function(data){
    //         $(".groupId").val(data.GROUPID);
    //         $(".groupCode").val(data.GROUPCODE);
    //         $(".superGroupCode").val(data.SUPERGROUPCODE);
    //         $(".orgaName").textbox('setValue', data.GROUPNAME);
    //         $(".groupDesc").textbox('setValue', data.GROUPDESC);
    //         $(".groupTypeId").combobox('setValue', data.GROUPTYPEID);
    //         var freeRoleDomObj = $('#freeRole');
    //         var IncludeRoleDomObj = $('#IncludeRole');
    //         var freeRoleForPic = $('#freeRoleForPic');
    //         var IncludeRoleForPicObj = $('#IncludeRoleForPic');
    //
    //         //待分配人员列表
    //         loadFreedistributeStaff(freeRoleDomObj,groupId,"");
    //         loadFreedistributeStaff(freeRoleForPic,groupId,"");
    //
    //         //已分配人员列表
    //         loaddistributeStaff(IncludeRoleDomObj);
    //
    //         IncludeRoleForPic(IncludeRoleForPicObj);
    //
    //
    //
    //     }
    // });

}



function loadGroupTypeSelect(){
    AjaxUtilsTemp.commonAjax('GET','/sam/tsamgrouptype/queryGroupType',null,false,'application/json',function (data) {
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
        $.messager.alert("提示","获取菜单下拉框接口异常。");
    },'json');


    // $.ajax({
    //     type: "GET",
    //     url: "/tsamgrouptype/queryGroupType",
    //     dataType: "json",
    //     async:false,
    //     contentType:"application/json",
    //     success: function(data){
    //         if(data.result == '0000'){
    //             $('.groupTypeId').combobox({
    //                 data : data.modules,//获取要显示的json数据
    //                 valueField: 'GROUPTYPEID',
    //                 textField: 'GROUPTYPENAME',
    //             });
    //             $(".groupTypeId").combobox('select',data.modules[0].GROUPTYPEID);
    //         }else{
    //             $.messager.alert('提示', '获取菜单下拉框接口异常。');
    //
    //         }
    //     }
    // });
}


var editIndex = undefined;
//已分配人员列表
function loaddistributeStaff(domObj){
    var id = $(".groupId").val()
    var token = AjaxUtilsTemp.getToken();
    domObj.datagrid({
        url: "/sam/StaffInfo/selectStaffIncludeGroup?groupId="+id+"&access_token="+token,
        method:"GET",
        contentType: "application/json",
        pagination: false, //分页控件
        pageSize: 20,
        pageList: [10, 20, 50],
        striped:true,
        fit : true,
        fixRowHeight:600,
        loadMsg:"正在努力加载数据,表格渲染中...",
        columns:[[
            {field :'ck',checkbox : true,width : '10%' },
            {field:'staffId',title:'账号ID',width:'26%'},
            {field:'staffName',title:'人员名称',width:'50%'},
            {
                title: '是否责任人',
                field: 'isprincipal',
                halign: 'center',
                width:'20%',
                formatter: function (value) {
                    for (var i = 0; i < _KKXMItems.length; i++) {
                        if (_KKXMItems[i].isprincipal == value)
                            return _KKXMItems[i].isprincipalName;
                    }
                },
                editor: {
                    type: 'combobox',//下拉框
                    options: {
                        valueField: 'isprincipal',//对应为表格中的field
                        textField: 'isprincipalName',//显示值
                        editable: false,
                        data: _KKXMItems,
                        required: true
                    }
                }
            }
        ]],
        onLoadSuccess: function (data) {

        },

        onBeforeEdit:function () {
            console.log("edit");
        },
        onClickRow: function(index, rowData) {
            if(editIndex != index) {
                if(endEditing()) {//行点击事件，让其可编辑
                    domObj.datagrid('selectRow', index).datagrid('beginEdit', index);
                    editIndex = index;
                } else {
                    domObj.datagrid('selectRow', index).datagrid('endEdit', index);
                }
            }


        }

    });
}


function endEditing(){
    var dom = $('#IncludeRole');
    if (editIndex == undefined){return true}
    if (dom.datagrid('validateRow', editIndex)){
        var ed =dom.datagrid('getEditor', {index:editIndex,field:'isprincipal'});
        if(ed == null){
            return true;
        }
        var isprincipalName = $(ed.target).combobox('getText');
        dom.datagrid('getRows')[editIndex]['isprincipalName'] = isprincipalName;
        dom.datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}



//已分配责任人列表
function IncludeRoleForPic(domObj){
    var id = $(".groupId").val();
    var token = AjaxUtilsTemp.getToken();
    domObj.datagrid({
        url: "/sam/StaffInfo/selectStaffIncludeGroupForPic?groupId="+id+"&access_token"+token,
        method:"GET",
        contentType: "application/json",
        pagination: false, //分页控件
        pageSize: 20,
        pageList: [10, 20, 50],
        striped:true,
        fit : true,
        fixRowHeight:600,
        loadMsg:"正在努力加载数据,表格渲染中...",
        columns:[[
            {field :'ck',checkbox : true,width : '10%' },
            {field:'staffId',title:'账号ID',width:'32%'},
            {field:'staffName',title:'人员名称',width:'50%'}

        ]],
        onLoadSuccess: function (data) {

        },
        onBeforeEdit:function () {
            console.log("edit");
        }

    });
}





function searchFreedistribute(flag){
    if(flag == '0'){
        var freeRoleDomObj = $('#freeRole');
        var groupId = $(".groupId").val();
        loadFreedistributeStaff(freeRoleDomObj,groupId,"");
    }else{
        var freeRoleDomObj = $('#freeRoleForPic');
        var groupId = $(".groupId").val();
        loadFreedistributeStaffPcx(freeRoleDomObj,groupId);
    }

}


var _KKXMItems = [{ "isprincipal": 1, "isprincipalName": "是" }, { "isprincipal": 0, "isprincipalName": "否" }];
//待分配人员列表
function loadFreedistributeStaff(freeRoleDomObj,groupId,pageSize){

    var token = AjaxUtilsTemp.getToken();
    var orgaId = $("#orgaId").combotree("getValue");
    if(orgaId == undefined || orgaId == '请选择'){
        orgaId ="";
    }
    freeRoleDomObj.datagrid({
        url: "/sam/StaffInfo/selectStaffFreeGroup?groupId="+groupId+"&access_token="+token+"&staffName="+$(".staffName").val()+"&staffId="+$(".staffId").val()+"&staffIdMin="+$(".staffIdMin").val()+"&staffIdMax="+$(".staffIdMax").val()+"&staffIdStatus="+$(".staffIdStatus").combobox('getValue')+"&orgaId="+orgaId,
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

/**
 *  责任人
 * @param freeRoleDomObj
 * @param groupId
 */

function loadFreedistributeStaffPcx(freeRoleDomObj,groupId){
    freeRoleDomObj.datagrid({
        rownumbers: false, //行号
        pagination: true, //分页控件
        fitColumns:true,
        pageSize: 10,
        pageList: [10, 20, 50],
        striped:true,
        loadMsg:"正在努力加载数据,表格渲染中...",
        columns:[[
            {field :'ck',checkbox : true,width : '10%' },
            {field:'staffId',title:'账号ID',width:'30%'},
            {field:'staffName',title:'人员名称',width:'66%'}
        ]],
        loader: function (param, success) {
            var  start = (param.page- 1) * param.rows + 1;
            var pageNum = param.rows;
            var params={"page":pageNum,"start":start,"groupId":groupId,"staffId":$(".staffId2").val(),"staffIdMin":$(".staffIdMin2").val(),
                "staffIdMax":$(".staffIdMax2").val(),"staffName":$(".staffName2").val(),"mobilePhone":$(".mobilePhone2").val(),"channelId":$(".channelId2").combobox('getValue'),
                "postId":$(".postId2").combobox('getValue'),"staffState":$(".staffState2").combobox('getValue'),"staffIdStatus":$(".staffIdStatus2").combobox('getValue')};
            // $.ajax({
            //     url: "/sam/StaffInfo/selectStaffFreeGroup",
            //     method: "GET",
            //     data: params,
            //     dataType: "json",
            //     success: function (data) {
            //         // if(page !=""){
            //         //     data.total = data.total-10;
            //         // }
            //         success(data);
            //     }
            // });


            AjaxUtilsTemp.commonAjax('GET','/sam/StaffInfo/selectStaffFreeGroup',params,true,'text/plain',function (data) {
                success(jQuery.parseJSON(data));
            },function () {
                $.messager.alert("提示","获取数据失败!");
            },'json');

        },

    });

}





/**
 * 往右移动
 */

function gridDataRight(flag){
    if(flag == '0'){
        var pageNumber=1;
        var rightObj = $('#freeRole');
        var leftObj = $('#IncludeRole');
        gridMovingRight(rightObj,leftObj)
        var pageData=rightObj.datagrid("getData");
        if(pageData.rows.length == 0){
            var freeRoleDomObj = $('#freeRole');

            var queryParams = freeRoleDomObj.datagrid('options').queryParams;
            if(queryParams.pageNumber !=undefined){
                queryParams.pageNumber=parseInt(queryParams.pageNumber)+parseInt(pageNumber);
            }else{
                queryParams.pageNumber=parseInt(pageNumber);
            }
            freeRoleDomObj.datagrid('options').queryParams=queryParams;
            freeRoleDomObj.datagrid('reload');
        }
    }else{
        var rightObjPic = $('#freeRoleForPic');
        var IncludeRoleForPic = $('#IncludeRoleForPic');

        //已分配责任人列表 只能有一个责任人
        var rows = $("#IncludeRoleForPic").datagrid("getRows");
        if(rows.length >=1){
            $.messager.alert("提示","只能选择分配一个责任人。");
            return;
        }
        gridMovingRight(rightObjPic,IncludeRoleForPic);
    }
}


function gridDataLeft(flag){
    if(flag == '0'){
        var rightObj = $('#freeRole');
        var leftObj = $('#IncludeRole');
        gridMovingRight(leftObj,rightObj)
    }else{
        var rightObjPic = $('#freeRoleForPic');
        var IncludeRoleForPic = $('#IncludeRoleForPic');
        gridMovingLeft(IncludeRoleForPic,rightObjPic);
    }
}


function gridMovingRight(rightObj,leftObj) {
    var selRows =rightObj.datagrid('getChecked');
    if(selRows.length == 0){
        $.messager.alert("提示","请选择要取消分配的人员");
        return;
    }
    if(selRows.length>0){
        for(var i = 0;i<selRows.length;i++){
            if(selRows[i] !=undefined){
                selRows[i].isprincipal = '0';
                leftObj.datagrid('insertRow',{
                    index:i,
                    row:selRows[i]
                });
                var index = rightObj.datagrid('getRowIndex',selRows[i]);
                rightObj.datagrid('deleteRow', index);

            }else{
                return;
            }

        }


    }


}

/**
 * 往左移动
 */
function gridMovingLeft(rightObj,leftObj) {
    var selRows = rightObj.datagrid('getChecked');
    if(selRows.length == 0){
        $.messager.alert("提示","请选择要分配的人员");
        return;
    }
    if(selRows.length>0){
        for(var i = 0;i<selRows.length;i++){
            if(selRows[i] !=undefined){
                leftObj.datagrid('insertRow', {
                    index: i,
                    row: selRows[i]
                });
                var index =leftObj.datagrid('getRowIndex', selRows[i]);
                rightObj.datagrid('deleteRow', index);
            }else{
                return;
            }

        }

    }
}




/**
 * 保存选中分配的人员
 */
function saveRoleStaff(flag){
    if(flag == '0'){
        var domObj = $("#IncludeRole");
        saveRoleStaffDynamic(domObj,flag);
    }else{
        var domObj = $("#IncludeRoleForPic");
        saveRoleStaffDynamic(domObj,flag);
    }
}

function saveRoleStaffDynamic(obj,flag){
    obj.datagrid('endEdit', editIndex);
    var data = obj.datagrid("getRows");
    var arr = new Array();


    // ajaxLoading();
    if(data.length>0){
        var num=0;
        for(var i =0;i<data.length;i++){
                if(data[i].isprincipal == '1'){
                    num = parseInt(num)+1;
                }
                if(num<=1){
                    var isprincipal = data[i].isprincipal;
                    if(isprincipal == undefined){
                        isprincipal = '0';
                    }
                    arr.push(new group($(".groupId").val(),data[i].staffId,isprincipal));
                }else{
                    $.messager.alert("提示","只能选择一个责任人。");
                    arr=null;
                    editIndex = undefined;
                    return;
                }
            }
    }else{
        arr.push(new group($(".groupId").val(),"null","0"));
    }


    AjaxUtilsTemp.commonAjax('POST','/sam/tsamgroupmember/addStaffGroupBatch',JSON.stringify(arr),true,'application/json',function (data) {
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
    //     url: "/tsamgroupmember/addStaffGroupBatch",
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



function resultSearch(flag){
    if(flag == '0'){
        $(".staffId").textbox('setValue',"");
        $(".staffIdMin").textbox('setValue',"");
        $(".staffIdMax").textbox('setValue',"");
        $(".staffName").textbox('setValue',"");
        $(".staffIdStatus").combobox('select',"00");
        $("#orgaId").combotree("setValue", "请选择");
        //待分配人员列表
        var freeRoleDomObj = $('#freeRole');
        loadFreedistributeStaff(freeRoleDomObj,$(".groupId").val());


    }else{
        $(".staffId2").textbox('setValue',"");
        $(".staffIdMin2").textbox('setValue',"");
        $(".staffIdMax2").textbox('setValue',"");
        $(".staffName2").textbox('setValue',"");
        $(".mobilePhone2").textbox('setValue',"");
        $(".channelId2").combobox('select',"00");
        $(".postId2").combobox('select',"00");
        $(".staffState2").combobox('select',"00");
        $(".staffIdStatus2").combobox('select',"00");
        //待分配人员列表
        var freeRoleForPic = $('#freeRoleForPic');
        loadFreedistributeStaffPcx(freeRoleForPic,$(".groupId").val());
    }


}


function group(GROUPID,STAFFID,ISPRINCIPAL){
    this.GROUPID=GROUPID;
    this.STAFFID=STAFFID;
    this.ISPRINCIPAL = ISPRINCIPAL;
}

function  ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 280) / 2,top:($(window).height() - 245) / 2});
}
function ajaxLoadEnd(){
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}

function resultForm(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    //showEditGroupInfo($(".groupId").val());

}

function resultGird(flag){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    // var groupId = $(".groupId").val()
    // if(flag =='0'){
    //     var freeRoleDomObj = $('#freeRole');
    //     var IncludeRoleDomObj = $('#IncludeRole');
    //     //待分配人员列表
    //     loadFreedistributeStaff(freeRoleDomObj,groupId,"");
    //     loaddistributeStaff(IncludeRoleDomObj);
    // }else{
    //     var freeRoleForPic = $('#freeRoleForPic');
    //     var IncludeRoleForPicObj = $('#IncludeRoleForPic');
    //     //已分配人员列表
    //     loadFreedistributeStaff(freeRoleForPic,groupId,"");
    //     IncludeRoleForPic(IncludeRoleForPicObj);
    // }
}