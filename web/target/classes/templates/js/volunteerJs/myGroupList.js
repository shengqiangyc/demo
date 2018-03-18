$(document).ready(function(){
  getMyGroupList();
})

function getMyGroupList(){
    $("#myGroupList").html("");
    $.ajax({
        url: '/group/getMyGroupList.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        success: function (data) {
            var s = "";
            for(var i in data){
                s += "<li><a href='groupVolunteers.html'><span>"+ data[i].groupName +"</span></a></li>"
            }
            $("#myGroupList").html(s);
        }
    })
}