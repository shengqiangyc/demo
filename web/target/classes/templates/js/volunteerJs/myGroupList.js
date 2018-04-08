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
        "            <a href=\"\" class=\"sf-with-ul\"><span>帐号管理 ▾</span></a>\n" +
        "              <ul class=\"sub-menu\" id=\"myGroupList\">\n" +
        "                <li>\n" +
        "                  <a href=\"loginOut.json\"><span>登录/退出登录</span></a>\n" +
        "                </li>\n" +
        "                <li>\n" +
        "                  <a href=\"updatePassword.html\"><span>修改密码</span></a>\n" +
        "                </li>\n" +
        "              </ul></li>"
    $("#primary-nav").html(s);
}

function getMyGroupList(){
    $.ajax({
        url: '/user/getMyGroupList.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        success: function (data) {
            $("#myGroupList").html("<li><a href=''><span>暂无团队</span></a></li>");
            if(data.length !== 0) {
                var s = "";
                for (var i in data) {
                    s += "<li><a href='groupVolunteers.html?groupId=" + data[i].groupId + "'><span>" + data[i].groupName + "</span></a></li>"
                }
                $("#myGroupList").html(s);
            }
        }
    })
}