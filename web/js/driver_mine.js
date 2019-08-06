function init() {
    $("#driver_mine").css({'border-bottom':'rgba(255, 255, 255, 1) solid 5px'});
    $("#driver_menu_personal").css({'background':'rgba(125, 197, 193, 0.1)'});
    $(".driver_mine_personal_page").show();
    $(".driver_mine_changepwd_page").hide();
    $(".driver_mine_logs_page").hide();
    $(".driver_mine_notes_page").hide();
}

$("#driver_menu_personal").click(function (e) {
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
    $("#driver_menu_personal").css({'background':'rgba(125, 197, 193, 0)'});
    $("#driver_menu_changepwd").css({'background':'rgba(125, 197, 193, 0)'});
    $("#driver_menu_logs").css({'background':'rgba(125, 197, 193, 0)'});
    $("#driver_menu_notes").css({'background':'rgba(125, 197, 193, 0.1)'});
    $(".driver_mine_personal_page").hide();
    $(".driver_mine_changepwd_page").hide();
    $(".driver_mine_logs_page").hide();
    $(".driver_mine_notes_page").fadeIn(100);
});