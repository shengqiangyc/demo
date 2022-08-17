$(document).ready(function() {
    getMyApplys(1);
    $("#pagination1").pagination({
        currentPage: 1,
        totalPage: $("#count").val(),
        callback: function (current) {
            getMyApplys(current);
        }
    });
});

function getMyApplys(current){
    var data ={
        userId : $("#userId").val(),
        start : (current-1)*8
    }
    $.ajax({
        url: '/user/myApplyList.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (data) {
            $("#myApplyList").html("<span style='font-size: 16px;color: grey'>暂无申请</span>");
            if(data.length !== 0) {
                var s = " <tr>\n" +
                    "              <th style=\"width: 120px\">申请团队</th>\n" +
                    "              <th style=\"width: 150px\">申请时间</th>\n" +
                    "              <th style=\"width: 100px\">审核状态</th>\n" +
                    "              <th style=\"width: 100px\">操作</th>\n" +
                    "            </tr>";
                for (var i in data) {
                    s += "<tr>";
                    s += "<td>" + data[i].groupName + "</td>";
                    s += "<td>" + data[i].applyDate + "</td>";
                    s += "<td>" + data[i].applyStatus + "</td>";
                    if(data[i].applyStatus === "待审核"){
                        s += "<td><input type=\"button\" value=\"取消申请\" onclick='updateApply("+data[i].applyId+")'></td>";
                    } else {
                        s += "<td>——</td>";
                    }
                }
                $("#myApplyList").html(s);
            }
        }
    })
}

function updateApply(applyId) {
    var data = {
        applyId : applyId
    };
    if (confirm('确定取消该申请吗')) {
        $.ajax({
            url: '/user/cancelApply.json',
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (data) {
            }
        });
        alert("已取消该申请");
        window.location.href = "";
    }
}