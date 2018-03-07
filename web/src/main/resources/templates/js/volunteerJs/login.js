//登录
function checkUser(){
    var userName = $('#userName').val();
    var password = $('#password').val();
    $("#notFound").text("");
    $("#pwError").text("");
    var data = {
        userName:userName,
        password:password
    }
    if(userName === null || userName === ""){
        alert("请填写用户名");
    } else if(password === null || password === ""){
        alert("请输入密码！")
    }else {
        $.ajax({
            url: '/login/check.json',
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            dataType: 'text',
            success: function (data) {
                if (data === "用户不存在")
                    $('#notFound').text("×用户不存在，请确认用户名是否正确");
                else if(data === "密码错误")
                    $('#pwError').text("×密码错误");
                else if(data === "登录成功"){
                    login(userName,password);
                }
            },
            error: function (err) {
                console.log(err);
                alert("错误");
            }

        });
    }
}

function login(userName,password){
    var data = {
        userName:userName,
        password:password
    }
    $.ajax({
        url:'/login/login.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(data),
        dataType:'text',
        success:function(data){
            if(data === "登录成功") {
                window.location.href = "/index.html"
            }

        }
    })
}