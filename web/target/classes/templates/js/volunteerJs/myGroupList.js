$(document).ready(function(){
    menum();
  getMyGroupList();
})

function menum(){
    var s = "<ul id=\"menu-primary-menu\" class=\"sf-menu\">\n" +
        "            <li>\n" +
        "              <a href=\"index.html\">\n" +
        "              <span>首页&nbsp&nbsp\n"+
        "              </span></a>\n" +
        "            </li>\n" +
        "            <li>\n" +
        "              <a href=\"\" class=\"sf-with-ul\"><span>志愿者之家 ▾</span></a>\n" +
        "              <ul class=\"sub-menu\">\n" +
        "                <li>\n" +
        "                  <a href=\"groupList.html\">\n" +
        "                <span>志愿者团队\n" +
        "                </span></a>\n" +
        "                </li>\n" +
        "                <li>\n" +
        "                  <a href=\"activityList.html\">\n" +
        "                <span>志愿者项目\n" +
        "                </span></a>\n" +
        "                </li>\n" +
        "                <li>\n" +
        "                  <a href=\"myApplyList.html\">\n" +
        "                <span>我的入队申请\n" +
        "                </span></a>\n" +
        "                </li>\n" +
        "                <li>\n" +
        "                  <a href=\"createGroup.html\">\n" +
        "                <span>申请创建团队\n" +
        "                </span></a>\n" +
        "                </li>\n" +
        "              </ul>\n" +
        "            <li>\n" +
        "            <a href=\"\" class=\"sf-with-ul\"><span>我的团队 ▾</span></a>\n" +
        "              <ul class=\"sub-menu\" id=\"myGroupList\">\n" +
        "                <li>\n" +
        "                  <a href=\"groupVolunteers.html\"><span>团队1</span></a>\n" +
        "                </li>\n" +
        "                <li>\n" +
        "                  <a href=\"groupVolunteers.html\"><span>团队2</span></a>\n" +
        "                </li>\n" +
        "              </ul>\n" +
        "            </li>\n" +
        "            <li>\n" +
        "              <a href=\"/loginOut.json\" >\n" +
        "            <span>退出登录\n" +
        "            </span>\n" +
        "              </a>\n" +
        "            </li>\n" +
        "          </ul>"
    $("#primary-nav").html(s);
}

function getMyGroupList(){
    $("#myGroupList").html("");
    $.ajax({
        url: '/group/getMyGroupList.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        success: function (data) {
            var s = "";
            for(var i in data){
                s += "<li><a href='groupVolunteers.html?groupId="+ data[i].groupId +"'><span>"+ data[i].groupName +"</span></a></li>"
            }
            $("#myGroupList").html(s);
        }
    })
}