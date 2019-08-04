function init() {
    $("#manager_drivers").css({'border-bottom':'rgba(255, 255, 255, 1) solid 5px'});
}

$(".manger_drivers_add").click(function () {
    $(".dialog_content").fadeIn("fast");
    $(".dialog_pop_delete_driver").hide();
    $(".dialog_pop_add_driver").fadeIn("fast");
});