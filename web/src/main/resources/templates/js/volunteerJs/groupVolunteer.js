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
                s += "<tr>";
                s += "<td><img src='"+data[i].userLogo+"' style='width: 70px;height: 50px'></td>";
                s += "<td>"+data[i].userName+"</td>";
                s += "<td>"+data[i].sex+"</td>";
                s += "<td>"+data[i].realName+"</td>";
                s += "<td>"+data[i].address+"</td>";
                s += "<td>"+data[i].role+"</td>";
                s += "<td>"+data[i].entryDate+"</td>";
                s += "<td><input type='button' value='开除成员'><input type='button' value='升为管理' style='margin-left: 10px;'></td>";
                s += "</tr>";
                $("#groupMembers").html(s);
            }
        }
    })
}