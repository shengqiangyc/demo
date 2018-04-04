function updatePassword(){
    var oldPassword = $("#oldPassword").val();
    var newPassword = $("#newPassword").val();
    var reviewPassword = $("#reviewPassword").val();
    var userId = $("#userId").val();
    var data = {
        oldPassword : oldPassword,
        newPassword : newPassword,
        userId : userId
    }
    if (newPassword !== reviewPassword){
        alert("两次密码不匹配！");
    } else {
        $.ajax({
            url: '/user/updatePassword.json',
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            dataType: 'text',
            success: function (data) {
                if (data === "密码错误") {
                    alert("原密码不正确");
                } else if (data === "成功"){
                    alert ("修改成功");
                    window.location.href = "login.html";
                } else {
                    alert("修改失败,请重试");
                    window.location.reload();
                }
            }
        })
    }
}