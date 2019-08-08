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
    $("#manager_drivers").css({'border-bottom':'rgba(255, 255, 255, 1) solid 5px'});
}

$(".manger_drivers_add").click(function () {
    $(".dialog_content").fadeIn("fast");
    $(".dialog_pop_delete_driver").hide();
    $(".dialog_pop_add_driver").fadeIn("fast");
});

//截止事件
$(".dialog_pop").click(function (event) {
    event.stopPropagation();
});