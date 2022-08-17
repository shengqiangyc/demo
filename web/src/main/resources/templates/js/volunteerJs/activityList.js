$(document).ready(function() {
    getActivityList(1);
    $("#pagination1").pagination({
        currentPage: 1,
        totalPage: $("#countActivity").val(),
        callback: function (current) {
            getActivityList(current);
        }
    });
});

function getGroupListByName(){
    getActivityList(1);
    var pageSize;
    var searchParam = $("#select").val();
    getCountByName(searchParam);
    pageSize = $("#countActivityByName").val();
    $("#pagination1").pagination({
        currentPage: 1,
        totalPage: pageSize,
        callback: function (current) {
            getActivityList(current);
        }
    });
}

function getActivityList(current){
    var s = "<h3>全部项目</h3>";
    s += "<span>未找到结果</span>"
    $("#activityList").html(s);

    var data ={
        activityName : $("#select").val(),
        type : $("#activityType").val(),
        pageNo : current
    }
    $.ajax({
        url: '/activity/getActivityList.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (data) {
            var s="";
            s+="<h3>全部项目</h3>";
            for(var i in data) {
                s += "<li  class='main' >";
                s += "<a href='activityDetails.html?activityId=" + data[i].activityId + "'>";
                s += "<img src='" + data[i].url + "' width='212' height='129'/>";
                s += data[i].activityName;
                s += "<span style='color: green'>" + data[i].typeName + "</span></li></a>";
                $("#activityList").html(s);
            }
        }
    })
}

function getCountByName(){
    getActivityList(1);
    var pageSize;
    var searchParam = $("#select").val();
    var type = $("#activityType").val();
    var data = {
        activityName:searchParam,
        type:type
    }
    $.ajax({
        url: '/activity/countActivityByParam.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function(data){
            $("#countActivityByName").val(data);
            pageSize = $("#countActivityByName").val();
            $("#pagination1").pagination({
                currentPage: 1,
                totalPage: pageSize,
                callback: function (current) {
                    getActivityList(current);
                }
            });
        }
    })
}