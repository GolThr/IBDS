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
    //初始化信息ajax_initManagerInfo_POST
    //发出(data)：邮箱email
    //接收(json)：ifsuccess:0(失败),1(成功), 用户名username, 性别gender, 手机号phone, 邮箱email, 公交公司company, 地址address
    var data = {email:user_info.email};
    console.log(data);
    console.log("InitManagerInfo");
    $.ajax({
        url: "/IBDS/initManagerInfo", //后台请求数据
        type: "post",
        success: function (msg) {
            console.log("InitManagerInfo:Success!");
            console.log(msg);
            if(msg.ifsuccess == '1'){
                setManagerInfo(msg);
            }
        },
        error: function (msg) {
            console.log("InitManagerInfo:Error!");
            console.log(msg);
            alert("请求失败，请重试");
            msga = {username:'沈长盈',gender:'男',phone:'17852651111',email:'12341651515@163.com',company:'啥公交有限责任公司',address:'山东省日照市'};
            setManagerInfo(msga);
        }
    });
}

function setManagerInfo(msg){
    $('#manager_username').val(msg.username);
    if(msg.gender == '男'){
        $('#input_gender_male').attr('checked','checked');
        $('#input_gender_female').removeAttr('checked');
    }else{
        $('#input_gender_male').removeAttr('checked');
        $('#input_gender_female').attr('checked','checked');
    }
    $('#manager_phone').val(msg.phone);
    $('#manager_company').val(msg.company);
    $('#manager_address').val(msg.address);
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
                                console.log("LoginAjax:Success!");
                                console.log(msg);
                            },
                            error: function (msg) {
                                console.log("LoginAjax:Error!");
                                console.log(msg);
                                alert("请求失败，请重试");
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

$("#manager_menu_personal").click(function (e) {
    $("#manager_menu_personal").css({'background':'rgba(125, 197, 193, 0.1)'});
    $("#manager_menu_message").css({'background':'rgba(125, 197, 193, 0)'});
    $("#manager_menu_changepwd").css({'background':'rgba(125, 197, 193, 0)'});
    $(".manager_mine_personal_page").fadeIn(100);
    $(".manager_mine_message_page").hide();
    $(".manager_mine_changepwd_page").hide();
});

$("#manager_menu_message").click(function (e) {
    $("#manager_menu_personal").css({'background':'rgba(125, 197, 193, 0)'});
    $("#manager_menu_message").css({'background':'rgba(125, 197, 193, 0.1)'});
    $("#manager_menu_changepwd").css({'background':'rgba(125, 197, 193, 0)'});
    $(".manager_mine_personal_page").hide();
    $(".manager_mine_message_page").fadeIn(100);
    $(".manager_mine_changepwd_page").hide();
});

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
    console.log(formData);
    console.log("ChangeAvatarAjax");
    //修改头像ajax_changeAvatar_POST
    //发出(formData)：formDta:file(文件对象)
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