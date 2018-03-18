$(document).ready(function() {
    getGroupMembers(1);
    $("#pagination1").pagination({
        currentPage: 1,
        totalPage: $("#count").val(),
        callback: function (current) {
            getGroupMembers(current);
        }
    });
});

function getGroupMembers(current){
   var s = "<tr>\n" +
        " <th>头像</th>\n" +
        "<th style=\"width: 50px\">用户名</th>\n" +
        "<th style=\"width: 30px\">性别</th>\n" +
        "<th style=\"width: 50px\">真实姓名</th>\n" +
        "<th style=\"width: 90px\">所在地</th>\n" +
        "<th style=\"width: 60px\">角色</th>\n" +
        "<th>入队时间</th>\n" +
        "<th>操作</th>\n" +
        "</tr>";
    $("#groupMembers").html(s);
    var role = $("#userRole").val();
    var data ={
        groupId : $("#groupId").val(),
        pageNo : current
    }
    $.ajax({
        url: '/group/getAllGroupMembers.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (data) {
            for(var i in data) {
                var userId = data[i].userId;
                s += "<tr>";
                s += "<td><img src='"+data[i].userLogo+"' style='width: 70px;height: 50px'></td>";
                s += "<td>"+data[i].userName+"</td>";
                s += "<td>"+data[i].sex+"</td>";
                s += "<td>"+data[i].realName+"</td>";
                s += "<td>"+data[i].address+"</td>";
                s += "<td>"+data[i].role+"</td>";
                s += "<td>"+data[i].entryDate+"</td>";
                if(data[i].role === "创建者"){
                    s += "<td></td></tr>";
                } else if(role === "3" && data[i].role === "管理员"){
                    s += "<td><input type='button' onclick='removeMember("+userId+",3)' value='开除成员'>" +
                        "<input type='button' value='取消管理' onclick='removeMember("+data[i].userId+",2)' style='margin-left: 10px;'></td>";
                    s += "</tr>";
                } else if(role === "3" && data[i].role === "普通成员"){
                    s += "<td><input type='button' onclick='removeMember("+userId+",3)' value='开除成员'>" +
                        "<input type='button' value='设为管理' onclick='removeMember("+userId+",1)' style='margin-left: 10px;'></td>";
                    s += "</tr>";
                } else if(role ==="2"){
                    s += "<td><input type='button' onclick='removeMember("+userId+",3)' value='开除成员'></td>";
                    s += "</tr>";
                } else if(role === "1"){
                    s += "<td></td></tr>";
                }
                $("#groupMembers").html(s);
            }
        }
    })
}

function removeMember(userId,type){
    var groupId = $("#groupId").val();
    var data = {
        groupId : groupId,
        userId : userId,
        performType : type
    };
    var tips;
    if(type === 1){
        tips = '确定要将其设为管理员吗';
    } else if(type === 2){
        tips = '确定要取消其管理员吗';
    } else if(type === 3){
        tips = '确定要踢出该成员吗';
    }
    if(confirm(tips)){
        $.ajax({
            url: '/group/updateUserRole.json',
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (data) {
            }
        });
        alert("操作成功");
        window.location.reload();
    }
}

function disband(){
    var groupId = $("#groupId").val();
    var data = {
        groupId : groupId
    };
    if(confirm('你确定要解散团队吗')) {
        $.ajax({
            url: '/group/disbandGroup.json',
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (data) {
            }
        });
        alert("已解散该团队");
        window.location.href="index.html";
    }

}