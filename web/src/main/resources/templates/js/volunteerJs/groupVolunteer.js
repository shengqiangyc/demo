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
        "<th style='width: 90px'>入队时间</th>\n" +
        "<th style='width: 160px'>操作</th>\n" +
        "</tr>";
    $("#groupMembers").html(s);
    var role = $("#userRole").val();
    var nowUserId = $("#userId").val();
    var data ={
        groupId : $("#groupId").val(),
        userName : $("#select").val(),
        role : $("#role").val(),
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
                if (data[i].role === "创建者") {
                    s += "<td style='color: indianred'>" + data[i].role + "</td>";
                } else if (data[i].role === "管理员") {
                    s += "<td style='color: #3c9ccd'>" + data[i].role + "</td>";
                } else {
                    s += "<td style='color: #3f7f5f'>" + data[i].role + "</td>";

                }
                s += "<td>"+data[i].entryDate+"</td>";
                if(data[i].role === "创建者"){
                    s += "<td>——</td></tr>";
                } else if(nowUserId === userId.toString()){
                    s += "<td>——</td></tr>";
                } else if(role === "3" && data[i].role === "管理员"){
                    s += "<td><input type='button' onclick='removeMember("+userId+",3)' value='开除'>" +
                        "<input type='button' value='取消管理' onclick='removeMember("+data[i].userId+",2)' style='margin-left: 10px;'></td>";
                    s += "</tr>";
                } else if(role === "3" && data[i].role === "普通成员"){
                    s += "<td><input type='button' onclick='removeMember("+userId+",3)' value='开除'>" +
                        "<input type='button' value='设为管理' onclick='removeMember("+userId+",1)' style='margin-left: 10px;'></td>";
                    s += "</tr>";
                } else if(role ==="2"){
                    if(data[i].role === "管理员"){
                        s += "<td>——</td></tr>";
                    } else {
                        s += "<td><input type='button' onclick='removeMember(" + userId + ",3)' value='开除'></td>";
                        s += "</tr>";
                    }
                } else if(role === "1"){
                    s += "<td>——</td></tr>";
                }
                $("#groupMembers").html(s);
            }
        }
    })
}

function getCountByParam() {
    getGroupMembers(1);
    var pageSize;
    var data = {
        groupId : $("#groupId").val(),
        userName : $("#select").val(),
        role : $("#role").val()
    }
    $.ajax({
        url: '/group/countGroupMembers.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (data) {
            $("#countSelected").val(data);
            pageSize = $("#countSelected").val();
            $("#pagination1").pagination({
                currentPage: 1,
                totalPage: pageSize,
                callback: function (current) {
                    getGroupMembers(current);
                }
            });
        }
    });
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
    var role = $("#userRole").val();
    var data = {
        groupId : groupId
    };
    if(role !== "3"){
        alert("您不是团队创建人，无法解散团队");
    } else {
        if (confirm('你确定要解散团队吗')) {
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
            window.location.href = "index.html";
        }
    }
}

function exitGroup(){
    var data = {
        userId : $("#userId").val(),
        groupId : $("#groupId").val(),
        performType : 3
    };
    if(confirm('确定退出该团队吗')){
        $.ajax({
            url: '/group/exitGroup.json',
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (data) {
            }
        });
        alert("已退出团队");
        window.location.href = "index.html";
    }

}