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