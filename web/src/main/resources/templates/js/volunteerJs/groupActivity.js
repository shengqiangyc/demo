$(document).ready(function () {
    getGroupActivitys(1);
    $("#pagination1").pagination({
        currentPage: 1,
        totalPage: $("#count").val(),
        callback: function (current) {
            getGroupActivitys(current);
        }
    });
});

function getGroupActivitys(current) {
    var data = {
        groupId: $("#groupId").val(),
        leaderId: $("#leader").val(),
        status: $("#activityStatus").val(),
        pageNo: current
    }
    var userRole = $("#userRole").val();
    var userId = $("#userId").val();
    $.ajax({
        url: '/activity/getGroupActivity.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (data) {
            $("#groupActivityList").html("<span style='font-size: 16px;color: grey'>暂无项目</span>");
            if (data.length !== 0) {
                var s = " <tr>\n" +
                    "            <th>项目图片</th>\n" +
                    "            <th width=\"60px\">项目名称</th>\n" +
                    "            <th width=\"70px\">所在城市</th>\n" +
                    "            <th width=\"50px\">项目类型</th>\n" +
                    "            <th width=\"110px\">开始时间</th>\n" +
                    "            <th width=\"60px\">负责人</th>\n" +
                    "            <th width=\"50px\">项目状态</th>\n" +
                    "            <th width='\80px' >操作</th>\n" +
                    "          </tr>";
                for (var i in data) {
                    s += "<tr>";
                    s += "<td><a href='activityDetails.html?activityId="+ data[i].activityId +"'><img src='" + data[i].url + "' style='width: 70px;height: 50px'></a></td>";
                    s += "<td>" + data[i].activityName + "</td>";
                    s += "<td>" + data[i].city + "</td>";
                    s += "<td>" + data[i].type + "</td>";
                    s += "<td>" + data[i].createTime + "</td>";
                    s += "<td>" + data[i].leaderName + "</td>";
                    if (data[i].statusInt === 2) {
                        s += "<td style='color: indianred'>" + data[i].status+ "</td>";
                    } else if (data[i].statusInt === 0) {
                        s += "<td style='color: #3c9ccd'>" + data[i].status + "</td>";
                    } else {
                        s += "<td style='color: #3f7f5f'>" + data[i].status + "</td>";

                    }
                    if (userRole === "3" || userId === data[i].leaderId.toString()) {
                        if (data[i].statusInt === 0) {
                            s += "<td><input type=\"button\" value=\"开始项目\" onclick='updateStatus("+ data[i].activityId +",1)'></td></tr>";
                        } else if (data[i].statusInt === 1) {
                            s += "<td><input type=\"button\" value=\"结束项目\" onclick='updateStatus("+ data[i].activityId +",2)'></td></tr>";
                        } else {
                            s += "<td>——</td></tr>";
                        }
                    } else {
                        s += "<td>——</td></tr>";
                    }
                }
                $("#groupActivityList").html(s);
            }
        }
    });
}

function updateStatus(activityId,type){
    var data = {
        activityId : activityId,
        type : type
    };
    var tips;
    if(type === 1){
        tips = '确定要开始项目吗';
    } else if(type === 2) {
        tips = '确定要结束项目吗';
    }
    if(confirm(tips)){
        $.ajax({
            url: '/activity/updateActivityStatus.json',
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

    function getCountByName() {
        getGroupActivitys(1);
        var pageSize;
        var status = $("#activityStatus").val();
        var leader = $("#leader").val();
        var data = {
            status: status,
            leaderId: leader,
            groupId : $("#groupId").val()
        }
        $.ajax({
            url: '/activity/countGroupActivityByParam.json',
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
                        getGroupActivitys(current);
                    }
                });
            }
        });
    }