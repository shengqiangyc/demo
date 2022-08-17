function uploadImage(){
    var files = $("#image").get(0).files[0];
    var file = $("#image").val();
    var groupId = $("#groupId").val();
    var activityName = $("#activityName").val();
    var leader = $("#activityLeader").val();
    var city = $("#city").val();
    var description = $("#description").val();
    var activityType = $("#activityType").val();
    var nameTip = $("#nameTip")
    var cityTip = $("#cityTip")
    var form = new FormData();
    form.append("file", files);
    if(activityName === "" || activityName === null){
        alert("项目名称不能为空！");
    } else if(activityType === 0){
        alert("请选择项目类型！");
    } else if(city === "" || city === null){
        alert("城市不能为空");
    } else if(leader === 0){
        alert("请选择一个负责人！");
    } else if(file === "" || file === null){
        alert("请选择图片");
    } else if(description === "" || description === null){
        alert("简介不能为空");
    } else if(nameTip.html() === "×项目名称长度不能超过15个字符"){
        alert(nameTip.html());
    } else if(cityTip.html() === "×无效的城市"){
        alert(cityTip.html());
    } else {
        $.ajax({
            url: "/activity/uploadActivityImage.json",
            type: "POST",
            contentType: false,
            data: form,
            dataType: "text",
            processData: false,  // 告诉jQuery不要去处理发送的数据
            success: function (data) {
                var info = {
                    activityName: activityName,
                    groupId: groupId,
                    type: activityType,
                    city: city,
                    description: description,
                    leader: leader,
                    image: data
                };
                createActivity(info);
            },
            error: function (err) {
                console.log(err);
                alert("上传图片失败");
            }
        });
    }
}

function createActivity(info){
    $.ajax({
        url:'/activity/createActivity.json',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(info),
        dataType:'text',
        success:function(data){
            if(data === "成功") {
                alert("创建项目成功！");
                window.location.href = "myActivityList.html?groupId="+info.groupId;
            }  else {
                alert("创建失败");
            }
        },
        error:function(err){
            console.log(err);
            alert("创建失败");
        }
    })
}

function checkCity(){
    var city = $("#city").val();
    var tip = $("#cityTip");
    if(city !== null && city !== "") {
        if (city.indexOf(' ') >= 0 || city.length > 15) {
            tip.html("×无效的城市");
            tip.css("color", "Red");
        } else {
            tip.html("√");
            tip.css("color", "Green");
        }
    }
}

function checkName(){
    var name = $("#activityName").val();
    var tip = $("#nameTip");
    if(name !== null && name !== "") {
        if (name.length >= 15) {
            tip.html("×项目名称长度不能超过15个字符");
            tip.css("color", "Red");
        } else {
            tip.html("√");
            tip.css("color", "Green");
        }
    }
}

function checkImage(){
    var image = $("#image").val();
    var tip = $("#pictureTip")
    if(image !== null && image !== "") {
        tip.html("√");
        tip.css("color", "Green");
    }
}

function checkIntro(){
    var description = $("#description").val();
    var tip = $("#descriptionTip")
    if(description !== null && description !== "") {
        tip.html("√");
        tip.css("color", "Green");
    }
}