var user_info;
function init() {
    user_info = JSON.parse(sessionStorage.getItem("user_info"));
    console.log(user_info);
    if(user_info == null || user_info.ifsuccess != "true"){
        location.href = "index.html";
    }else if(user_info.user_type != '0'){
        location.href = "index.html";
    }else{
        $('.manager_name').text(user_info.username);
    }
    $("#manager_mine").css({'border-bottom':'rgba(255, 255, 255, 1) solid 5px'});
    $("#manager_menu_personal").css({'background':'rgba(125, 197, 193, 0.1)'});
    $(".manager_mine_personal_page").show();
    $(".manager_mine_message_page").hide();
    $(".manager_mine_changepwd_page").hide();
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