function createGroup (){
    var files = $("#GroupImage").get(0).files[0];
    var file = $("#GroupImage").val();
    var groupName = $("#groupName");
    var nameTip = $("#nameTip");
    var city = $("#city");
    var cityTip = $("#cityTip");
    var qq = $("#qqGroup");
    var qqTip = $("#qqTip");
    var introduction = $("#GroupIntroduction");
    var require = $("#require");
    var groupCount = $("#groupCountStatus");
    if(groupCount.val() === "1"){
        alert("您已经创建或加入三个团队了，无法再执行此操作！");
    } else if(groupName.val() === null || groupName.val() ===""){
        alert("团队名称不能为空");
    } else if(city.val() === null || city.val() === ""){
        alert("请填写城市");
    } else if(qq.val() === null || qq.val() === ""){
        alert("请填写团队QQ群");
    } else if(file === null || file === ""){
        alert("请选择图片");
    } else if(introduction.val() === null || introduction.val() === ""){
        alert("请输入团队简介");
    } else if(require.val() === null || require.val() === ""){
        alert("请输入招募要求");
    } else if(nameTip.html() === "×团队名已存在"
        || nameTip.html() === "×团队名不能超过15个字符"
        || nameTip.html() === "×团队名不能包含空格"){
        alert(nameTip.html());
    } else if(cityTip.html() === "×城市中不能含有空格"){
        alert(cityTip.html());
    } else if(qqTip.html() === "×无效的qq群号"){
        alert(qqTip.html());
    }else {
        var form = new FormData();
        form.append("file", files);
        $.ajax({
            url: "/group/uploadGroupImage.json",
            type: "POST",
            contentType: false,
            data: form,
            dataType: "text",
            processData: false,  // 告诉jQuery不要去处理发送的数据
            success: function (data) {
                var info = {
                    groupName: groupName.val(),
                    city: city.val(),
                    qqGroup: qq.val(),
                    introduction: introduction.val(),
                    require: require.val(),
                    url: data
                };
                createGroupInfo(info);
            },
            error: function (err) {
                console.log(err);
                alert("上传图片失败");
            }
        });
    }
}

function createGroupInfo(info){
    $.ajax({
        url:'/group/createGroup.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(info),
        dataType:'text',
        success:function(data){
            if(data === "创建成功") {
                alert("创建团队成功！");
                window.location.href = "index.html"
            } else if(data === "未检测到用户信息"){
                if(confirm('未检测到用户信息,请重新登录')){
                    window.location.href = "login.html"
                }
            } else {
                alert("创建失败");
            }
        },
        error:function(err){
            console.log(err);
            alert("创建失败");
        }
    })

}

function checkGroupName(){
    var groupName = $("#groupName").val();
    var tip = $("#nameTip");
    if(groupName.indexOf(" ") >= 0){
        tip.html("×团队名不能包含空格");
        tip.css("color","Red");
    }
    if(groupName.length > 15){
        tip.html("×团队名不能超过15个字符");
        tip.css("color","Red");
    }
    var data = {
        groupName : groupName
    }
    $.ajax({
        url:'/group/checkGroup.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(data),
        dataType:'text',
        success:function(data){
            if(data === "团队名已存在") {
                tip.html("×团队名已存在");
                tip.css("color","Red");
            } else if(data === "团队名可用") {
                tip.html("√");
                tip.css("color","Green");
            }
        },
        error:function(err){
            console.log(err);
            alert("创建团队失败");
        }
    })
}

function checkCity(){
    var city = $("#city").val();
    var tip = $("#cityTip");
    if(city !== null && city !== "") {
        if (city.indexOf(' ') >= 0) {
            tip.html("×城市中不能含有空格");
            tip.css("color", "Red");
        } else {
            tip.html("√");
            tip.css("color", "Green");
        }
    }
}

function checkQQ(){
    var qq = $("#qqGroup").val();
    var tip = $("#qqTip");
    var p = /^[0-9]*$/;
    if(qq !== null && qq !== "") {
        if (!p.test(qq)) {
            tip.html("×无效的qq群号");
            tip.css("color", "Red");
        } else {
            tip.html("√");
            tip.css("color", "Green");
        }
    }
}

function checkIntro(){
    var introduction = $("#GroupIntroduction").val();
    var tip = $("#introTip")
    if(introduction !== null && introduction !== "") {
        tip.html("√");
        tip.css("color", "Green");
    }
}

function checkRequire(){
    var require = $("#require").val();
    var tip = $("#requireTip")
    if(require !== null && require !== "") {
        tip.html("√");
        tip.css("color", "Green");
    }
}

function checkImage(){
    var name = $("#GroupImage").val();
    var tip = $("#imageTip");
    if (name !== null && name !== "") {
        tip.html("√");
        tip.css("color", "Green");
    }
}



