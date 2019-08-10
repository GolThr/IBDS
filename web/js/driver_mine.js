var user_info;
function init() {
    user_info = JSON.parse(sessionStorage.getItem("user_info"));
    console.log(user_info);
    if(user_info == null || user_info.ifsuccess != "1"){
        location.href = "index.html";
    }else if(user_info.user_type != '1'){
        location.href = "index.html";
    }else{
        $(".manager_head").attr("src", user_info.avatar);
        $('.manager_name').text(user_info.username);
    }
    $("#driver_mine").css({'border-bottom':'rgba(255, 255, 255, 1) solid 5px'});
    $("#driver_menu_logs").css({'background':'rgba(125, 197, 193, 0.1)'});
    $(".driver_mine_logs_page").show();
    $(".driver_mine_changepwd_page").hide();
    $(".driver_mine_personal_page").hide();
    $(".driver_mine_notes_page").hide();
    $('.calendar').hide();

    initManagerInfo();
}

function initManagerInfo(){
    $('.mine_user_head').attr('src', user_info.avatar);
    $('.mine_user_name').text(user_info.username);
    $('#driver_username').val(user_info.username);
    if(user_info.gender == '男'){
        $('#input_gender_male').attr('checked','checked');
        $('#input_gender_female').removeAttr('checked');
    }else{
        $('#input_gender_male').removeAttr('checked');
        $('#input_gender_female').attr('checked','checked');
    }
    $('#driver_email').val(user_info.email);
    $('#driver_phone').val(user_info.phone);
    $('#driver_company').val(user_info.company);
    $('#driver_address').val(user_info.address);
}

$('.mine_logout').click(function (e) {
    sessionStorage.removeItem("user_info");
    location.href = '/IBDS/login.html';
});

$("#driver_menu_personal").click(function (e) {
    $('.calendar').hide();
    $("#driver_menu_personal").css({'background':'rgba(125, 197, 193, 0.1)'});
    $("#driver_menu_changepwd").css({'background':'rgba(125, 197, 193, 0)'});
    $("#driver_menu_logs").css({'background':'rgba(125, 197, 193, 0)'});
    $("#driver_menu_notes").css({'background':'rgba(125, 197, 193, 0)'});
    $(".driver_mine_personal_page").fadeIn(100);
    $(".driver_mine_changepwd_page").hide();
    $(".driver_mine_logs_page").hide();
    $(".driver_mine_notes_page").hide();
});

$("#driver_menu_changepwd").click(function (e) {
    $('.calendar').hide();
    $("#driver_menu_personal").css({'background':'rgba(125, 197, 193, 0)'});
    $("#driver_menu_changepwd").css({'background':'rgba(125, 197, 193, 0.1)'});
    $("#driver_menu_logs").css({'background':'rgba(125, 197, 193, 0)'});
    $("#driver_menu_notes").css({'background':'rgba(125, 197, 193, 0)'});
    $(".driver_mine_personal_page").hide();
    $(".driver_mine_changepwd_page").fadeIn(100);
    $(".driver_mine_logs_page").hide();
    $(".driver_mine_notes_page").hide();
});

$("#driver_menu_logs").click(function (e) {
    $('.calendar').show();
    $("#driver_menu_personal").css({'background':'rgba(125, 197, 193, 0)'});
    $("#driver_menu_changepwd").css({'background':'rgba(125, 197, 193, 0)'});
    $("#driver_menu_logs").css({'background':'rgba(125, 197, 193, 0.1)'});
    $("#driver_menu_notes").css({'background':'rgba(125, 197, 193, 0)'});
    $(".driver_mine_personal_page").hide();
    $(".driver_mine_changepwd_page").hide();
    $(".driver_mine_logs_page").fadeIn(100);
    $(".driver_mine_notes_page").hide();
});

$("#driver_menu_notes").click(function (e) {
    $('.calendar').hide();
    $("#driver_menu_personal").css({'background':'rgba(125, 197, 193, 0)'});
    $("#driver_menu_changepwd").css({'background':'rgba(125, 197, 193, 0)'});
    $("#driver_menu_logs").css({'background':'rgba(125, 197, 193, 0)'});
    $("#driver_menu_notes").css({'background':'rgba(125, 197, 193, 0.1)'});
    $(".driver_mine_personal_page").hide();
    $(".driver_mine_changepwd_page").hide();
    $(".driver_mine_logs_page").hide();
    $(".driver_mine_notes_page").fadeIn(100);
});