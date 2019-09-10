
function showEditOrgaInfo(orgaId){
    AjaxUtilsTemp.commonAjax('GET','/sam/tsamorgainfo/'+orgaId,null,false,'text/plain',function (data) {
        $(".orgaId").val(data.ORGAID);
        $(".orgaCode").val(data.ORGACODE);
        $(".superOrgaCode").val(data.SUPERORGACODE);
        $(".orgaName").textbox('setValue', data.ORGANAME);
        $(".orgaTypeId").combobox('setValue', data.ORGATYPEID);
        $(".orgaState").combobox('setValue', data.ORGASTATE);
        $(".orgaDesc").textbox('setValue', data.ORGANAME);
    },function () {
        $.messager.alert("提示","获取数据失败!");
    },'json');
    // $.ajax({
    //     type: "GET",
    //     url: "/tsamorgainfo/"+orgaId,
    //     dataType: "json",
    //     success: function(data){
    //         $(".orgaId").val(data.ORGAID);
    //         $(".orgaCode").val(data.ORGACODE);
    //         $(".superOrgaCode").val(data.SUPERORGACODE);
    //         $(".orgaName").textbox('setValue', data.ORGANAME);
    //         $(".orgaTypeId").combobox('setValue', data.ORGATYPEID);
    //         $(".orgaState").combobox('setValue', data.ORGASTATE);
    //         $(".orgaDesc").textbox('setValue', data.ORGANAME);
    //     }
    // });

}





//待分配人员列表
function loadFreedOrgaStaff(freeRoleDomObj){
    var token = AjaxUtilsTemp.getToken();
    freeRoleDomObj.datagrid({
        url: "/sam/StaffInfo/selectStaffFreeRole?access_token="+token,
        method:"GET",
        contentType: "application/json",
        // queryParams:params,
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
            {field:'staffName',title:'人员名称',width:'46%'}

        ]],
        onLoadSuccess: function (data) {
            console.log(data);

        },
    });
}



//已分配人员列表
function inCloundOrgaStaff(freeRoleDomObj){
    var token = AjaxUtilsTemp.getToken();
    freeRoleDomObj.datagrid({
        url: "/sam/StaffInfo/selectStaffFreeRole?access_token="+token,
        method:"GET",
        contentType: "application/json",
        // queryParams:params,
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
            {field:'staffName',title:'人员名称',width:'46%'}

        ]],
        onLoadSuccess: function (data) {
            console.log(data);

        },
    });
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
function updateOrgaInfo(){
    var orgaName = $(".orgaName").textbox('getValue');
    if(orgaName == "" || orgaName == null){
        $.messager.alert('提示', '组织机构名称必填。');
        return;
    }
    if($(".orgaName").val().length>=10){
        $.messager.alert('提示', '工组织机构名称长度过长,最长不超过10个字符。');
        return;
    }

    var result = selectRepeatOrgaName(orgaName);
    if(result !=0){
        $.messager.alert('提示', '组织机构名称重复,请重新填写。');
        return;
    }
    if($(".orgaState").combobox('getValue') == "" ||  $(".orgaState").combobox('getValue') == null ||  $(".orgaState").combobox('getValue') == "-1"){
        $.messager.alert('提示', '请选择组织机构状态。');
        return;
    }

    if($(".orgaTypeId").combobox('getValue') == "" ||  $(".orgaTypeId").combobox('getValue') == null ||  $(".orgaTypeId").combobox('getValue') == "-1"){
        $.messager.alert('提示', '请选择组织机构类型。');
        return;
    }
    ajaxLoading();
    //选中那个节点就绑定在哪个节点下，没有选就默认是最顶级。
    var data = {TENANTID:1,SUPERORGACODE:$(".superOrgaCode").val(),ORGACODE:$(".orgaCode").val(),ORGAID:$(".orgaId").val(),ORGANAME:$(".orgaName").val(), ORGASTATE:$(".orgaState").combobox('getValue'),ORGATYPEID:$(".orgaTypeId").combobox('getValue'),orgaDesc:$(".orgaDesc").val()};

    AjaxUtilsTemp.commonAjax('POST','/sam/tsamgroupinfo/updateTSamOrgaInfo',JSON.stringify(data),true,'application/json',function (data) {
        ajaxLoadEnd();
        //关闭弹出层
        if(data == 1){
            $.messager.alert('提示', '修改成功!');
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
    //             $.messager.alert('提示', '修改成功!');
    //         }else{
    //             $.messager.alert('提示', '修改失败!');
    //         }
    //
    //     }
    // });

}



function selectRepeatOrgaName(orgaName){
    var result=0;

    AjaxUtilsTemp.commonAjax('GET','/sam/tsamgroupinfo/selectRepeatOrgaName',{"orgaName":orgaName},false,'application/json',function (data) {
        if(data.resultVal !='1'){
            $.messager.alert('提示', '获取角色重名异常.');
            return;
        }else{
            result = data.resultMsg;
        }
    },function () {
        $.messager.alert("提示","获取角色重名异常!");
    },'json');
    // $.ajax({
    //     type: "GET",
    //     url: "/tsamorgainfo/selectRepeatOrgaName",
    //     dataType: "json",
    //     data:{"orgaName":orgaName},
    //     async:false,
    //     contentType:"application/json",
    //     success: function(data){
    //         if(data.resultVal !='1'){
    //             $.messager.alert('提示', '获取角色重名异常.');
    //             return;
    //         }else{
    //             result = data.resultMsg;
    //         }
    //     }
    // });
    return result;
}



function resultForm(){
    var orgaId = $(".orgaId").val();
    showEditOrgaInfo(orgaId);
}
