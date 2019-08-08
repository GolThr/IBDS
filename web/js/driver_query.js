var user_info;
function init() {
    user_info = JSON.parse(sessionStorage.getItem("user_info"));
    console.log(user_info);
    if(user_info == null || user_info.ifsuccess != "true"){
        location.href = "index.html";
    }else if(user_info.user_type != '1'){
        location.href = "index.html";
    }else{
        $('.manager_name').text(user_info.username);
    }
    $("#driver_query").css({'border-bottom':'rgba(255, 255, 255, 1) solid 5px'});
}

function onFuncPhone() {
    $(".driver_func_phone_content").stop();
    $(".driver_func_phone_content").fadeIn(500);
}

function outFuncPhone() {
    $(".driver_func_phone_content").stop();
    $(".driver_func_phone_content").fadeOut(250);
}

function onFuncSignIn() {
    $(".driver_func_signin_content").stop();
    $(".driver_func_signin_content").fadeIn(500);
}

function outFuncsignIn() {
    $(".driver_func_signin_content").stop();
    $(".driver_func_signin_content").fadeOut(250);
}

function onFuncNote() {
    $(".driver_func_note_content").stop();
    $(".driver_func_note_content").fadeIn(500);
}

function outFuncNote() {
    $(".driver_func_note_content").stop();
    $(".driver_func_note_content").fadeOut(250);
}