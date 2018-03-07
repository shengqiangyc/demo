function register(info){
    $.ajax({
        url:'/register/register.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(info),
        dataType:'text',
        success:function(data){
            if(data === "注册成功") {
                alert("注册成功！");
                window.location.href = "login.html"
            }
        },
        error:function(err){
            console.log(err);
            alert("注册失败");
        }
    })

}

function uploadImage (){
    var files = $("#userImage").get(0).files[0];
    var file = $("#userImage").val();
    var userName = $("#userName");
    var userNameTip = $("#userTip");
    var password = $("#password");
    var passwordTip = $("#rePasswordTip");
    var realName = $("#realName");
    var realNameTip = $("#realNameTip");
    var sex = $(".sex");
    var identity = $("#identity");
    var identityTip = $("#identityTip");
    var address = $("#address");
    var introduction = $("#introduction");
    var mobile = $("#mobile");
    var mobileTip = $("#mobileTip");
    if(userName.val() === null || userName.val() ===""){
        alert("用户名不能为空");
    } else if(password.val() === null || password.val() === ""){
        alert("请填写密码");
    } else if(mobile.val() === null || mobile.val() === ""){
        alert("请填写手机号");
    } else if(file === null || file === ""){
        alert("请选择头像");
    } else if(realName.val() === null || realName.val() === ""){
        alert("请输入真实姓名");
    } else if(sex.val() === null || sex.val() === ""){
        alert("请选择性别");
    } else if(identity.val() === null || identity.val() === ""){
        alert("请填写身份证号");
    } else if(address.val() === null || address.val() === ""){
        alert("请输入地址");
    } else if(introduction.val() === null || introduction.val() === ""){
        alert("请输入简介");
    } else if(userNameTip.html() === "×用户名已经被注册"
        || userNameTip.html() === "×用户名不能包含空格"
        || userNameTip.html() === "×用户名不能超过15个字符"){
        alert(userNameTip.html());
    } else if(passwordTip.html() === "×两次密码不一致"){
        alert(passwordTip.html());
    } else if(mobileTip.html() === "×手机号格式不正确"){
        alert(mobileTip.html());
    } else if(realNameTip.html() === "×名字中不能含有空格"){
        alert(realNameTip.html());
    } else if(identityTip.html() === "×身份证格式不正确"){
        alert(identityTip.html());
    } else {
        var form = new FormData();
        form.append("file", files);
        $.ajax({
            url: "/register/uploadImage.json",
            type: "POST",
            contentType: false,
            data: form,
            dataType: "text",
            processData: false,  // 告诉jQuery不要去处理发送的数据
            success: function (data) {
                var info = {
                    userName: userName.val(),
                    password: password.val(),
                    realName: realName.val(),
                    sex: sex.val(),
                    identity: identity.val(),
                    address: address.val(),
                    introduction: introduction.val(),
                    mobile: mobile.val(),
                    url: data
                };
                register(info);
            },
            error: function (err) {
                console.log(err);
                alert("上传图片失败");
            }
        });
    }
}

function checkUser(){
    var tip = $("#userTip");
    var userName = $("#userName").val();
    if(userName.indexOf(" ") >= 0){
        tip.html("×用户名不能包含空格");
        tip.css("color","Red");
    }
    if(userName.length > 15){
        tip.html("×用户名不能超过15个字符");
        tip.css("color","Red");
    }
    $.ajax({
        url:'/register/checkUser.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: userName,
        dataType:'text',
        success:function(data){
            if(data === "用户名已经被注册") {
                tip.html("×用户名已经被注册");
                tip.css("color","Red");
            } else if(data === "用户名可用") {
                tip.html("√");
                tip.css("color","Green");
            }
        },
        error:function(err){
            console.log(err);
            alert("注册失败");
        }
    })
}

function checkRePassword(){
    var password = $("#password").val();
    var rePassword = $("#rePassword").val();
    var tip = $("#rePasswordTip");
    var tip2 = $("#passwordTip");
    if(password !== null && password !== "") {
        if (password !== rePassword) {
            tip.html("×两次密码不一致");
            tip.css("color", "Red");
        } else {
            tip.html("√");
            tip.css("color", "Green");
            tip2.html("√");
            tip2.css("color", "Green");
        }
    }
}

function checkMobile() {
    var p = /^1[3|4|5|8][0-9]\d{4,8}$/;
    var mobile = $("#mobile").val();
    var tip = $("#mobileTip");
    if(mobile !== null && mobile !== "") {
        if (!p.test(mobile)) {
            tip.html("×手机号格式不正确");
            tip.css("color", "Red");
        }
        else {
            tip.html("√");
            tip.css("color", "Green");
        }
    }
}

function checkRealName(){
    var realName = $("#realName").val();
    var tip = $("#realNameTip");
    if(realName !== null && realName !== "") {
        if (realName.indexOf(' ') >= 0) {
            tip.html("×名字中不能含有空格");
            tip.css("color", "Red");
        } else {
            tip.html("√");
            tip.css("color", "Green");
        }
    }
}

function checkIdentity(){
    var identity = $("#identity").val();
    var tip = $("#identityTip");
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if(identity !== null && identity !== "") {
        if (!reg.test(identity)) {
            tip.html("×身份证格式不正确");
            tip.css("color", "Red");
        } else {
            tip.html("√");
            tip.css("color", "Green");
        }
    }
}

function checkFile() {
    var name = $("#userImage").val();
    var tip = $("#imageTip");
    if (name !== null && name !== "") {
        tip.html("√");
        tip.css("color", "Green");
    }
}

function checkAddress() {
    var name = $("#address").val();
    var tip = $("#addressTip");
    if (name !== null && name !== "") {
        tip.html("√");
        tip.css("color", "Green");
    }
}

function checkIntro() {
    var name = $("#introduction").val();
    var tip = $("#introductionTip");
    if (name !== null && name !== "") {
        tip.html("√");
        tip.css("color", "Green");
    }
}





