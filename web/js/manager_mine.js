var user_info;
function init() {
    user_info = JSON.parse(sessionStorage.getItem("user_info"));
    console.log(user_info);
    if(user_info == null || user_info.ifsuccess != "1"){
        location.href = "index.html";
    }else if(user_info.user_type != '0'){
        location.href = "index.html";
    }else{
        $(".manager_head").attr("src", user_info.avatar);
        $('.manager_name').text(user_info.username);
    }
    $("#manager_mine").css({'border-bottom':'rgba(255, 255, 255, 1) solid 5px'});
    $("#manager_menu_personal").css({'background':'rgba(125, 197, 193, 0.1)'});
    $(".manager_mine_personal_page").show();
    $(".manager_mine_message_page").hide();
    $(".manager_mine_changepwd_page").hide();
    initManagerInfo();
}

function initManagerInfo(){
    $('.mine_user_head').attr('src', user_info.avatar);
    $('.mine_user_name').text(user_info.username);
    $('#manager_username').val(user_info.username);
    if(user_info.gender == '男'){
        $('#input_gender_male').attr('checked','checked');
        $('#input_gender_female').removeAttr('checked');
    }else{
        $('#input_gender_male').removeAttr('checked');
        $('#input_gender_female').attr('checked','checked');
    }
    $('#manager_email').val(user_info.email);
    $('#manager_phone').val(user_info.phone);
    $('#manager_company').val(user_info.company);
    $('#manager_address').val(user_info.address);
}

function updateSession(data) {
    user_info.username = data.username;
    user_info.gender = data.gender;
    user_info.phone = data.phone;
    user_info.company = data.company;
    user_info.address = data.address;
    sessionStorage.setItem("user_info", JSON.stringify(user_info));
    location.reload();
    showTip("信息修改成功！");
}

function onModifyInfo(){
    var manager_username = $.trim($('#manager_username').val());
    var manager_gender = $.trim($(".gender_line input[name=\"input_gender\"]:checked").val());
    var manager_phone = $.trim($('#manager_phone').val());
    var manager_email = $.trim($('#manager_email').val());
    var manager_company = $.trim($('#manager_company').val());
    var manager_address = $.trim($('#manager_address').val());
    if(manager_username != '' || manager_username != null){
        $('#manager_username').css({'border-color': 'rgba(203,54,56,0)'});
        if(manager_gender != '' || manager_username != null){
            $('#manager_gender').css({'border-color': 'rgba(203,54,56,0)'});
            if(manager_phone != '' || manager_username != null){
                $('#manager_phone').css({'border-color': 'rgba(203,54,56,0)'});
                if(manager_company != '' || manager_username != null){
                    $('#manager_company').css({'border-color': 'rgba(203,54,56,0)'});
                    if(manager_address != '' || manager_username != null){
                        $('#manager_address').css({'border-color': 'rgba(203,54,56,0)'});
                        //修改信息ajax_modifyManagerInfo_POST
                        //发出(data)：用户名username, 性别gender, 手机号phone, 邮箱email, 公交公司company, 地址address
                        //接收(json)：ifsuccess:0(失败),1(成功)
                        var data= {username:manager_username,gender:manager_gender,phone:manager_phone,email:manager_email,company:manager_company,address:manager_address};
                        console.log(data);
                        console.log("ModifyManagerInfoAjax");
                        $.ajax({
                            url: "/IBDS/modifyManagerInfo", //后台请求数据
                            dataType: "json",
                            data:JSON.stringify(data),
                            type: "post",
                            success: function (msg) {
                                console.log("ModifyManagerInfoAjax:Success!");
                                console.log(msg);
                                if(msg.ifsuccess == '1'){
                                    updateSession(data);
                                }else {
                                    showTip("信息修改失败！");
                                }
                            },
                            error: function (msg) {
                                console.log("ModifyManagerInfoAjax:Error!");
                                console.log(msg);
                                alert("请求失败，请重试");
                                showTip("信息修改失败！");
                            }
                        });
                    }else {
                        $('#manager_address').css({'border-color': '#cb3638'});
                        $('#manager_address').shake(2, 10, 400);
                    }
                }else {
                    $('#manager_company').css({'border-color': '#cb3638'});
                    $('#manager_company').shake(2, 10, 400);
                }
            }else {
                $('#manager_phone').css({'border-color': '#cb3638'});
                $('#manager_phone').shake(2, 10, 400);
            }
        }else {
            $('#manager_gender').css({'border-color': '#cb3638'});
            $('#manager_gender').shake(2, 10, 400);
        }
    }else {
        $('#manager_username').css({'border-color': '#cb3638'});
        $('#manager_username').shake(2, 10, 400);
    }
}

function renderingNonePage() {
    $(".driver_mine_notes_page").append('<div class="item_none">\n' +
        '                    <img src="images/pic_none.png" onclick="" style="width: 200px;height: auto;"/>\n' +
        '                    <span style="width: 100%;text-align: center;margin-top: 30px;">未查询到信息</span>\n' +
        '                </div>');
}

$('.mine_logout').click(function (e) {
    sessionStorage.removeItem("user_info");
    location.href = '/IBDS/index.html';
});

$("#manager_menu_personal").click(function (e) {
    $("#manager_menu_personal").css({'background':'rgba(125, 197, 193, 0.1)'});
    $("#manager_menu_message").css({'background':'rgba(125, 197, 193, 0)'});
    $("#manager_menu_changepwd").css({'background':'rgba(125, 197, 193, 0)'});
    $(".manager_mine_personal_page").fadeIn(100);
    $(".manager_mine_message_page").hide();
    $(".manager_mine_changepwd_page").hide();
    initManagerInfo();
});

$("#manager_menu_message").click(function (e) {
    $("#manager_menu_personal").css({'background':'rgba(125, 197, 193, 0)'});
    $("#manager_menu_message").css({'background':'rgba(125, 197, 193, 0.1)'});
    $("#manager_menu_changepwd").css({'background':'rgba(125, 197, 193, 0)'});
    $(".manager_mine_personal_page").hide();
    $(".manager_mine_message_page").fadeIn(100);
    $(".manager_mine_changepwd_page").hide();

    //查看留言ajax_initNotes_POST
    //发出(data)：查看筛选init_type(全部: all, 只显示某一用户: email)
    //接收(jsonArray)：发布用户author, 发布用户头像author_avatar, 发布时间publish_time(时间格式示例：2019-08-05), 留言内容notes_content
    var data = {init_type:'all'};
    console.log(data);
    console.log("InitNotesAjax");
    $.ajax({
        url: "/IBDS/initNotes", //后台请求数据
        dataType: "json",
        data:JSON.stringify(data),
        type: "post",
        success: function (msg) {
            console.log("InitNotesAjax:Success!");
            console.log(msg);
            $(".manager_mine_message_page").html("");
            if(msg[0] != null){
                renderingNotes(msg);
            }else {
                renderingNonePage();
            }
        },
        error: function (msg) {
            console.log("InitNotesAjax:Error!");
            console.log(msg);
            alert("请求失败，请重试");
        }
    });
});

function renderingNotes(msg){
    for(var i in msg){
        $(".manager_mine_message_page").append('<div class="mine_card_item">\n' +
            '                    <div class="mine_card_item_top">\n' +
            '                        <div>\n' +
            '                            <img class="author_head" src="'+msg[i].author_avatar+'" onclick=""/>\n' +
            '                            <span class="author_name">'+msg[i].author+'</span>\n' +
            '                        </div>\n' +
            '                        <span class="publish_time">'+msg[i].publish_time+'</span>\n' +
            '                    </div>\n' +
            '                    <span class="mine_card_item_body">'+msg[i].notes_content+'</span>\n' +
            '                </div>');
    }
}

function onChangePWD(){
    var origin_pwd = $.trim($('#origin_pwd').val());
    var new_pwd = $.trim($('#new_pwd').val());
    var verify_pwd = $.trim($('#verify_pwd').val());
    if(origin_pwd.length >= 6 && origin_pwd.length <= 16){
        $('#origin_pwd').css({'border-color': 'rgba(203,54,56,0)'});
        if(new_pwd.length >= 6 && new_pwd.length <= 16){
            $('#new_pwd').css({'border-color': 'rgba(203,54,56,0)'});
            if(verify_pwd === new_pwd){
                $('#verify_pwd').css({'border-color': 'rgba(203,54,56,0)'});
                //修改密码ajax_changePWD_POST
                //发出(data)：邮箱email, 原密码origin_pwd, 新密码new_pwd
                //接收(json)：ifsuccess:0(失败),1(成功)
                var data= {email:user_info.email,origin_pwd:origin_pwd,new_pwd:verify_pwd};
                console.log(data);
                console.log("ChangePWDAjax");
                $.ajax({
                    url: "/IBDS/changePWD", //后台请求数据
                    dataType: "json",
                    data:JSON.stringify(data),
                    type: "post",
                    success: function (msg) {
                        console.log("ChangePWDAjax:Success!");
                        console.log(msg);
                        if(msg.ifsuccess == '1'){
                            showTip("密码修改成功！");
                        }else {
                            showTip("原密码错误，密码修改失败！");
                        }
                    },
                    error: function (msg) {
                        console.log("ChangePWDAjax:Error!");
                        console.log(msg);
                        alert("请求失败，请重试");
                        if(msg.ifsuccess == '1'){
                            showTip("密码修改成功！");
                        }else {
                            showTip("密码修改失败！");
                        }
                    }
                });
            }else {
                $('#verify_pwd').css({'border-color': '#cb3638'});
                $('#verify_pwd').shake(2, 10, 400);
            }
        }else {
            $('#new_pwd').css({'border-color': '#cb3638'});
            $('#new_pwd').shake(2, 10, 400);
        }
    }else {
        $('#origin_pwd').css({'border-color': '#cb3638'});
        $('#origin_pwd').shake(2, 10, 400);
    }
}

function showTip(msg){
    $('.dialog_content').fadeIn('fast');
    $('.dialog_pop_tips').fadeIn('fast');
    $('.dialog_title_tips').text(msg);
}

$("#manager_menu_changepwd").click(function (e) {
    $("#manager_menu_personal").css({'background':'rgba(125, 197, 193, 0)'});
    $("#manager_menu_message").css({'background':'rgba(125, 197, 193, 0)'});
    $("#manager_menu_changepwd").css({'background':'rgba(125, 197, 193, 0.1)'});
    $(".manager_mine_personal_page").hide();
    $(".manager_mine_message_page").hide();
    $(".manager_mine_changepwd_page").fadeIn(100);
});

//绑定所有type=file的元素的onchange事件的处理函数
$(':file').change(function () {
    var file = this.files[0]; //假设file标签没打开multiple属性，那么只取第一个文件就行了
    fileName = file.name;
    size = file.size;
    type = file.type;
    url = window.URL.createObjectURL(file); //获取本地文件的url，如果是图片文件，可用于预览图片

    totalSize += size;
    $("#file_name").text(fileName);
    if(type === "image/jpeg" || type === "image/png"){
        canUpload = true;
        onUploadFile();
    }else {
        $(".dialog_content").fadeIn("fast");
        $(".dialog_pop_tips").fadeIn("fast");
        $(".dialog_title_tips").text("请上传jpg或png格式图片！");
        canUpload = false;
    }
});

//UploadFile
var totalSize = 0;
var canUpload = false;
function onUploadFile() {
    $(".dialog_btn_upload_file").hide();
    $(".dialog_btn_upload_complete").show();
    //创建FormData对象，初始化为form表单中的数据。需要添加其他数据可使用formData.append("property", "value");
    var formData = new FormData();
    formData.append("file", $('#file_input')[0].files[0]);
    formData.append("email", user_info.email);
    console.log(formData);
    console.log("ChangeAvatarAjax");
    //修改头像ajax_changeAvatar_POST
    //发出(formData)：formDta:file(文件对象), 邮箱email
    //接收(json)：ifsuccess:0(失败),1(上传成功), 头像地址相对路径avatar
    $.ajax({
        url: "/IBDS/changeManagerAvatar",
        type: "POST",
        data: formData,
        success: function (msg) {
            console.log("ChangeAvatarAjax:Success");
            if(msg.ifsuccess == '1'){
                $(".mine_user_head").attr("src", "http://localhost:8080/IBDS"+msg.avatar);
                $(".manager_head").attr("src", "http://localhost:8080/IBDS"+msg.avatar);
                user_info.avatar = msg.avatar;
                sessionStorage.setItem("user_info", JSON.stringify(user_info));
                showTip("修改头像成功！");
            }else {
                alert('内部错误，请联系管理员！');
            }
        },
        error: function(msg){
            console.log("ChangeAvatarAjax:Error!");
            alert("请求失败，请重试");
        },
        contentType: false, //必须false才会自动加上正确的Content-Type
        processData: false  //必须false才会避开jQuery对 formdata 的默认处理
    });
}