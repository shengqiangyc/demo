$(document).ready(function() {
    getGroupList(1);
    $("#pagination1").pagination({
        currentPage: 1,
        totalPage: $("#groupCount").val(),
        callback: function (current) {
            getGroupList(current);
        }
    });
});

function getGroupListByName(){
    getGroupList(1);
    var pageSize;
    var searchParam = $("#select").val();
    getCountByName(searchParam);
    pageSize = $("#groupCountByName").val();
    $("#pagination1").pagination({
        currentPage: 1,
        totalPage: pageSize,
        callback: function (current) {
            getGroupList(current);
        }
    });
}

function getGroupList(current){
    var s = "<h3>全部团队</h3>";
    s += "<span>未找到结果</span>"
    $("#groupList").html(s);

    var data ={
        groupName : $("#select").val(),
        pageNo : current
    }
    $.ajax({
        url: '/group/getGroupList.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (data) {
           var s="";
            s+="<h3>全部团队</h3>";
           for(var i in data) {
               s += "<li  class='main' >";
               s += "<a href='groupDetail.json?groupId=" + data[i].groupId + "'>";
               s += "<img src='" + data[i].groupImage + "' width='212' height='129'/>";
               s += data[i].groupName;
               s += "<span style='color: green'>" + data[i].groupStatus + "</span></li></a>";
               $("#groupList").html(s);
           }
           }
    })
}

function getCountByName(name){
    $.ajax({
        url: '/group/countGroupByName.json',
        type: 'GET',
        contentType: 'application/text;charset=utf-8',
        data: name,
        dataType: 'json',
        success: function(data){
            $("#groupCountByName").val(data);
        }
    })
}