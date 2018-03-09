function checkLogin(){
    if(confirm('登录信息失效，请重新登录')){
        window.location.href="login.html";
    }
}

function checkApply(){
    var userApplyStatus = $("#userApplyStatus").val();
    if(confirm('你确定申请加入此团队吗')){
        if(userApplyStatus === "1"){
            alert("你已经申请过加入团队了，请勿重复申请！");
        } else {
            applyToGroup();
        }
    }
}

function applyToGroup(){
    var groupId = $("#groupId").val();
    var data ={
        groupId:groupId
    }
    var groupCountStatus = $("#groupCountStatus").val();
    if(groupCountStatus === "1"){
        alert("你已经加入或创建三个团队了，无法再执行此操作");
    } else {
        $.ajax({
            url: '/group/applyEntryGroup.json',
            type: 'GET',
            contentType: 'application/text;charset=utf-8',
            data: data,
            dataType: 'text',
            success: function (data) {
                if (data === "申请成功") {
                    alert("申请成功！");

                }
                else {
                    alert("申请失败");
                }
            }
        })
    }
}