$(document).ready(function () {
    var data = {
        groupId : $("#groupId").val()
    }
    $.ajax({
        url: '/group/getGroupDetail.json',
        type: 'GET',
        contentType: 'application/text;charset=utf-8',
        data: data,
        dataType: 'json',
        success: function (data) {
            $("#groupIntroduction").val(data.description);
            $("#require").val(data.groupRequirement);
            $("#status").val(data.status);
        }
    })
});

function updateGroup(){
    var groupId = $("#groupId").val();
    var description = $("#groupIntroduction").val();
    var require = $("#require").val();
    var status = $("#status").val();
    var role = $("#role").val();
    var data = {
        groupId : groupId,
        description : description,
        require : require,
        status : status
    };
    if (role === "1"){
        alert("无权限更改团队信息");
    } else {
        if (confirm("确定修改团队信息吗")) {
            $.ajax({
                url: '/group/updateGroupInfo.json',
                type: 'POST',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (data) {
                }
            });
            alert("修改成功");
            window.location.reload();
        }
    }
}