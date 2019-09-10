var register_email;
var register_verify;
var register_cp_name;
var register_cp_address;
var register_cp_phone;

var register_username;
var register_pwd;
var register_repwd;
var register_gender;

var forgot_email;
var forgot_verify;
var forgot_pwd;
var forgot_repwd;

var verifyCode = '2591';
var isVerifyCorrect = false;

function checkEmail(email) {
    var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
    if(reg.test(email)){
        return true;
    }else{
        return false;
    }
    return false;
}

function checkVerifyCode(jqNode) {
    isVerifyCorrect = false;
    if($.trim(jqNode.val()) == verifyCode){
        jqNode.css({'border-color': 'rgba(203,54,56,0)'});
        isVerifyCorrect = true;
        return true;
    }else {
        jqNode.css({'border-color': '#cb3638'});
        jqNode.shake(2, 10, 400);
        isVerifyCorrect = false;
        return false;
    }
    isVerifyCorrect = false;
    return false;
}

function onStepOneBlur() {
    register_email = $.trim($("#register_email").val());
    register_verify = $.trim($("#register_verify").val());
    register_cp_name = $.trim($("#register_cp_name").val());
    register_cp_address = $.trim($("#register_cp_address").val());
    register_cp_phone = $.trim($("#register_cp_phone").val());
    if(checkEmail(register_email)){
        if((register_verify != "" || register_verify != null) && (register_cp_name != "" || register_cp_name != null) && (register_cp_address != "" || register_cp_address != null) && (register_cp_phone != "" || register_cp_phone != null)){
            $(".register_next_btn").removeClass("main_btn_style_none");
            $(".register_next_btn").addClass("main_btn_style");
        }else {
            $(".register_next_btn").removeClass("main_btn_style");
            $(".register_next_btn").addClass("main_btn_style_none");
        }
    }else {
        $(".register_next_btn").removeClass("main_btn_style");
        $(".register_next_btn").addClass("main_btn_style_none");
    }
}

function onStepTwoBlur() {
    register_username = $.trim($("#register_username").val());
    register_pwd = $.trim($("#register_pwd").val());
    register_repwd = $.trim($("#register_repwd").val());
    register_gender = $.trim($(".gender_line input[name=\"input_gender\"]:checked").val());
    console.log(register_gender);
    if((register_username == "" || register_username == null) || (register_pwd == "" || register_pwd == null) || (register_repwd == "" || register_repwd == null) || (register_gender == "" || register_gender == null)){
        $(".register_complete_btn").removeClass("main_btn_style");
        $(".register_complete_btn").addClass("main_btn_style_none");
    }else {
        $(".register_complete_btn").removeClass("main_btn_style_none");
        $(".register_complete_btn").addClass("main_btn_style");
    }
}

function onNextBtn() {
    register_email = $.trim($("#register_email").val());
    register_verify = $.trim($("#register_verify").val());
    register_cp_name = $.trim($("#register_cp_name").val());
    register_cp_address = $.trim($("#register_cp_address").val());
    register_cp_phone = $.trim($("#register_cp_phone").val());
    if(!checkEmail(register_email) || !checkVerifyCode($("#register_verify")) || (register_cp_name == "" || register_cp_name == null) || (register_cp_address == "" || register_cp_address == null) || (register_cp_phone == "" || register_cp_phone == null)){
        $(".register_next_btn").removeClass("main_btn_style");
        $(".register_next_btn").addClass("main_btn_style_none");
    }else {
        $(".register_step_1").hide();
        $(".register_step_2").fadeIn(100);
    }
}

function onCompleteBtn() {
    register_username = $.trim($("#register_username").val());
    register_pwd = $.trim($("#register_pwd").val());
    register_repwd = $.trim($("#register_repwd").val());
    register_gender = $.trim($(".gender_line input[name=\"input_gender\"]:checked").val());
    console.log(register_gender);
    if(register_username != "" || register_username != null){
        $('#register_username').css({'border-color': 'rgba(203,54,56,0)'});
        if(register_pwd.length >= 6 && register_pwd.length <= 16){
            $('#register_pwd').css({'border-color': 'rgba(203,54,56,0)'});
            if(register_pwd == register_repwd){
                $('#register_repwd').css({'border-color': 'rgba(203,54,56,0)'});
                //注册ajax_register_POST
                //发出(data)：常用邮箱email, 公司名称cp_name, 公司地址cp_address, 公司电话cp_phone, 用户名username, 密码password, 性别gender
                //接收(json)：ifsuccess:0(失败),1(成功)
                var data= {email:register_email,cp_name:register_cp_name,cp_address:register_cp_address,cp_phone:register_cp_phone,username:register_username,password:register_pwd, cp_gender:register_gender};
                console.log(data);
                console.log("RegisterAjax");
                $.ajax({
                    url: "/IBDS/register", //后台请求数据
                    dataType: "json",
                    data:JSON.stringify(data),
                    type: "post",
                    success: function (msg) {
                        console.log("RegisterAjax:Success!");
                        console.log(msg);
                        if(msg.ifsuccess == '1'){
                            $(".register_step_2").hide();
                            $(".register_step_complete").fadeIn(100);
                        }else{
                            alert("内部错误，请联系管理员！！！");
                        }
                    },
                    error: function (msg) {
                        console.log("RegisterAjax:Error!");
                        console.log(msg);
                        alert("请求失败，请重试");
                    }
                });
            }else {
                $('#register_repwd').css({'border-color': '#cb3638'});
                $('#register_repwd').shake(2, 10, 400);
            }
        }else {
            $('#register_pwd').css({'border-color': '#cb3638'});
            $('#register_pwd').shake(2, 10, 400);
        }
    }else {
        $('#register_username').css({'border-color': '#cb3638'});
        $('#register_username').shake(2, 10, 400);
    }
    $(".register_complete_btn").removeClass("main_btn_style");
    $(".register_complete_btn").addClass("main_btn_style_none");
}

function onForgotPWDBlur() {
    forgot_email = $.trim($("#forgot_email").val());
    forgot_verify = $.trim($("#forgot_verify").val());
    forgot_pwd = $.trim($("#forgot_pwd").val());
    forgot_repwd = $.trim($("#forgot_repwd").val());
    if((forgot_email == "" || forgot_email == null) || (forgot_verify == "" || forgot_verify == null) || (forgot_pwd == "" || forgot_pwd == null) || (forgot_repwd == "" || forgot_repwd == null)){
        $(".login_next_btn").removeClass("main_btn_style");
        $(".login_next_btn").addClass("main_btn_style_none");
    }else {
        $(".login_next_btn").removeClass("main_btn_style_none");
        $(".login_next_btn").addClass("main_btn_style");
    }
}

function onChangePWDBtn() {
    forgot_email = $.trim($("#forgot_email").val());
    forgot_verify = $.trim($("#forgot_verify").val());
    forgot_pwd = $.trim($("#forgot_pwd").val());
    forgot_repwd = $.trim($("#forgot_repwd").val());
    if(checkVerifyCode($('#forgot_verify'))){
        if(forgot_pwd.length >= 6 && forgot_pwd.length <= 16){
            $('#forgot_repwd').css({'border-color': 'rgba(203,54,56,0)'});
            if(forgot_pwd == forgot_repwd){
                $('#forgot_pwd').css({'border-color': 'rgba(203,54,56,0)'});
                //找回密码ajax_forgotPWD_POST
                //发出(data)：常用邮箱email, 密码password
                //接收(json)：ifsuccess:0(失败),1(成功)
                var data= {email:forgot_email,password:forgot_repwd};
                console.log(data);
                console.log("ForgotPWDAjax");
                $.ajax({
                    url: "/IBDS/forgotPWD", //后台请求数据
                    dataType: "json",
                    data:JSON.stringify(data),
                    type: "post",
                    success: function (msg) {
                        console.log("ForgotPWDAjax:Success!");
                        console.log(msg);
                        if(msg.ifsuccess == '1'){
                            $(".login_body_forgot").hide();
                            $(".login_body_complete").fadeIn(100);
                        }else{
                            alert("内部错误，请联系管理员！！！");
                        }
                    },
                    error: function (msg) {
                        console.log("ForgotPWDAjax:Error!");
                        console.log(msg);
                        alert("请求失败，请重试");
                    }
                });
            }else{
                $('#forgot_repwd').css({'border-color': '#cb3638'});
                $('#forgot_repwd').shake(2, 10, 400);
                $(".login_next_btn").removeClass("main_btn_style");
                $(".login_next_btn").addClass("main_btn_style_none");
            }
        }else{
            $('#forgot_pwd').css({'border-color': '#cb3638'});
            $('#forgot_pwd').shake(2, 10, 400);
            $(".login_next_btn").removeClass("main_btn_style");
            $(".login_next_btn").addClass("main_btn_style_none");
        }
    }else {
        $('#forgot_verify').css({'border-color': '#cb3638'});
        $('#forgot_verify').shake(2, 10, 400);
        $(".login_next_btn").removeClass("main_btn_style");
        $(".login_next_btn").addClass("main_btn_style_none");
    }
}

/*re-item*********************************/
function turnToRegister() {
    $(".login_body").css({'transform': 'rotateY(-180deg)','-webkit-transform':'rotateY(-180deg)'});
    $(".register_body").css({'transform': 'rotateY(-360deg)','-webkit-transform':'rotateY(-360deg)'});
}

function turnToLogin() {
    $(".login_body").css({'transform': 'rotateY(0deg)','-webkit-transform':'rotateY(0deg)'});
    $(".register_body").css({'transform': 'rotateY(-180deg)','-webkit-transform':'rotateY(-180deg)'});
}
/*****************************************/

//***ajax***//
$('.login_btn').click(function (e) {
    var login_email = $.trim($('#login_email').val());
    var login_pwd = $.trim($('#login_pwd').val());
    if(checkEmail(login_email) && (login_email != '' && login_email != null)){
        if(login_pwd != '' && login_pwd != null){
            if(drag_validate){
                //可以登录
                //登录ajax_login_POST
                //发出(data)：常用邮箱email, 密码password
                //接收(json)：ifsuccess:0(密码错误),1(成功),2(未注册); 用户类型user_type(管理员0, 司机1), 头像avatar, 用户名username, 性别gender, 手机号phone, 邮箱email, 公交公司company, 地址address, 工号work_number
                var data= {email:login_email,password:login_pwd};
                console.log(data);
                console.log("LoginAjax");
                $.ajax({
                    url: "/IBDS/login", //后台请求数据
                    dataType: "json",
                    data:JSON.stringify(data),
                    type: "post",
                    success: function (msg) {
                        console.log("LoginAjax:Success!");
                        console.log(msg);
                        sessionStorage.setItem("user_info", JSON.stringify(msg));
                        if(msg.ifsuccess == "1"){
                            if(msg.user_type == '0'){
                                location.href = "manager_upload.html";
                            }else if(msg.user_type == '1'){
                                location.href = "driver_query.html";
                            }
                        }else if(msg.ifsuccess == "0"){
                            //密码错误
                            $('#drag').css({'border-color': 'rgba(203,54,56,0)'});
                            $('#login_email').css({'border-color': 'rgba(203,54,56,0)'});
                            $('#login_pwd').css({'border-color': '#cb3638'});
                            $('#login_pwd').shake(2, 10, 400);
                        }else if(msg.ifsuccess == "2"){
                            //未注册
                            $('#drag').css({'border-color': 'rgba(203,54,56,0)'});
                            $('#login_pwd').css({'border-color': 'rgba(203,54,56,0)'});
                            $('#login_eamil').css({'border-color': '#cb3638'});
                            $('#login_eamil').shake(2, 10, 400);
                        }else {
                            alert("内部错误，请联系管理员！！！");
                        }
                    },
                    error: function (msg) {
                        console.log("LoginAjax:Error!");
                        console.log(msg);
                        var parsedJson = JSON.stringify(msg);
                        console.log(parsedJson);
                        var jsonData = JSON.parse(parsedJson);
                        console.log(jsonData);
                        alert("请求失败，请重试");
                    }
                });
            }else {
                $('#login_email').css({'border-color': 'rgba(203,54,56,0)'});
                $('#login_pwd').css({'border-color': 'rgba(203,54,56,0)'});
                $('#drag').css({'border-color': '#cb3638'});
                $('#drag').shake(2, 10, 400);
            }
        }else {
            $('#drag').css({'border-color': 'rgba(203,54,56,0)'});
            $('#login_email').css({'border-color': 'rgba(203,54,56,0)'});
            $('#login_pwd').css({'border-color': '#cb3638'});
            $('#login_pwd').shake(2, 10, 400);
        }
    }else{
        $('#drag').css({'border-color': 'rgba(203,54,56,0)'});
        $('#login_pwd').css({'border-color': 'rgba(203,54,56,0)'});
        $('#login_email').css({'border-color': '#cb3638'});
        $('#login_email').shake(2, 10, 400);
    }
})

function getVerifyCode(jqnode) {
    email = $.trim($('#'+jqnode).val());
    if(checkEmail(email) && (email != '' && email != null)){
        //获取验证码ajax_getVerifyCode_POST
        //发出(data)：邮箱email
        //接收(json)：验证码verifyCode;
        var data= {email:email};
        console.log(data);
        console.log("GetVerifyCodeAjax");
        $.ajax({
            url: "/IBDS/getVerifyCode", //后台请求数据
            dataType: "json",
            data:JSON.stringify(data),
            type: "post",

            success: function (msg) {
                console.log("GetVerifyCodeAjax:Success!");
                verifyCode = msg.verifyCode;
            },
            error: function (msg) {
                console.log("GetVerifyCodeAjax:Error!");
                console.log(msg);
                alert("请求失败，请重试");
            }
        });
    }else {
        $("#forgot_email").css({'border-color': '#cb3638'});
        $("#forgot_email").shake(2, 10, 400);
    }
}


