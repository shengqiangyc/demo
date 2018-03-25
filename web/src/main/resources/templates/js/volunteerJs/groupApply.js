$(document).ready(function() {
    var role = $("#userRole").val();
    if(role === "1"){
        $("#groupApplys").html("<span style='font-size: 16px;color: grey'>No Access!</span>");
    } else {
        getGroupApplys(1);
        $("#pagination1").pagination({
            currentPage: 1,
            totalPage: $("#countPage").val(),
            callback: function (current) {
                getGroupApplys(current);
            }
        });
    }
});



function getGroupApplys(current){
    var data ={
        groupId : $("#groupId").val(),
        pageNo : current
    }
    $.ajax({
        url: '/group/applyList.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (data) {
            $("#groupApplys").html("<span style='font-size: 16px;color: grey'>暂无申请</span>");
            if(data.length !== 0) {
                var s = " <tr>\n" +
                    "            <th>头像</th>\n" +
                    "            <th style=\"width: 50px\">姓名</th>\n" +
                    "            <th style=\"width: 50px\">性别</th>\n" +
                    "            <th style=\"width: 50px\">年龄</th>\n" +
                    "            <th style=\"width: 80px\">所在地</th>\n" +
                    "            <th style=\"width: 130px\">申请时间</th>\n" +
                    "            <th style=\"width: 110px\">操作</th>\n" +
                    "          </tr>";
                for (var i in data) {
                    s += "<tr>";
                    s += "<td><img src='" + data[i].logo + "' style='width: 70px;height: 50px'></td>";
                    s += "<td>" + data[i].userName + "</td>";
                    s += "<td>" + data[i].sex + "</td>";
                    s += "<td>" + data[i].realName + "</td>";
                    s += "<td>" + data[i].address + "</td>";
                    s += "<td>" + data[i].applyDate + "</td>";
                    s += " <td><input type=\"button\" value=\"同意\" onclick='updateApply("+data[i].userId+",2)'>\n" +
                        "                <input type=\"button\" value=\"拒绝\" onclick='updateApply("+data[i].userId+",3)' style=\"margin-left: 10px;\"></td>\n" +
                        "          </tr>";
                }
                $("#groupApplys").html(s);
            }
        }
    })
}

function updateApply(userId,type){
    var groupId = $("#groupId").val();
    var tip = "";
    if(type === 2){
        tip = "同意";
    } else {
        tip = "拒绝";
    }
    var data = {
        groupId : groupId,
        userId :userId,
        status : type
    };
    if (confirm("你确定要"+tip+"该申请吗")) {
        $.ajax({
            url: '/group/updateApply.json',
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (data) {
            }
        });
        alert("已"+tip+"该申请");
        window.location.href = "";
    }
}