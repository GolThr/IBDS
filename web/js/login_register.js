var register_email;
var register_verify;
var register_cp_name;
var register_cp_address;
var register_cp_phone;

var register_username;
var register_pwd;
var register_repwd;
var register_gender;

function onGoRegisterClicked() {

}

function onGoLoginClicked() {

}

function onStepOneBlur() {
    register_email = $.trim($("#register_email").val());
    register_verify = $.trim($("#register_verify").val());
    register_cp_name = $.trim($("#register_cp_name").val());
    register_cp_address = $.trim($("#register_cp_address").val());
    register_cp_phone = $.trim($("#register_cp_phone").val());
    if((register_email == "" || register_email == null) || (register_verify == "" || register_verify == null) || (register_cp_name == "" || register_cp_name == null) || (register_cp_address == "" || register_cp_address == null) || (register_cp_phone == "" || register_cp_phone == null)){
        $(".register_next_btn").removeClass("main_btn_style");
        $(".register_next_btn").addClass("main_btn_style_none");
    }else {
        $(".register_next_btn").removeClass("main_btn_style_none");
        $(".register_next_btn").addClass("main_btn_style");
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
    if((register_email == "" || register_email == null) || (register_verify == "" || register_verify == null) || (register_cp_name == "" || register_cp_name == null) || (register_cp_address == "" || register_cp_address == null) || (register_cp_phone == "" || register_cp_phone == null)){
        $(".register_next_btn").removeClass("main_btn_style");
        $(".register_next_btn").addClass("main_btn_style_none");
    }else {
        $(".register_step_1").hide();
        $(".register_step_2").fadeIn(100);
    }
}

function onCompleteBtn() {register_username = $.trim($("#register_username").val());
    register_pwd = $.trim($("#register_pwd").val());
    register_repwd = $.trim($("#register_repwd").val());
    register_gender = $.trim($(".gender_line input[name=\"input_gender\"]:checked").val());
    console.log(register_gender);
    if((register_username == "" || register_username == null) || (register_pwd == "" || register_pwd == null) || (register_repwd == "" || register_repwd == null) || (register_gender == "" || register_gender == null)){
        $(".register_complete_btn").removeClass("main_btn_style");
        $(".register_complete_btn").addClass("main_btn_style_none");
    }else {
        $(".register_step_2").hide();
        $(".register_step_complete").fadeIn(100);
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