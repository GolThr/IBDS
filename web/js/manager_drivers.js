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
    $("#manager_drivers").css({'border-bottom':'rgba(255, 255, 255, 1) solid 5px'});

    //初始化司机信息ajax_initDrivers_POST
    //发出(data)：
    //接收(jsonArray)：司机邮箱driver_email, 工号driver_id, 姓名driver_name, 性别driver_gender, 电话driver_phone, 线路driver_line
    console.log("InitDriversAjax");
    $.ajax({
        url: "/IBDS/initDrivers", //后台请求数据
        type: "post",
        success: function (msg) {
            console.log("InitDriversAjax:Success!");
            console.log(JSON.parse(msg));
            renderingLists(JSON.parse(msg));
        },
        error: function (msg) {
            console.log("InitDriversAjax:Error!");
            console.log(msg);
            alert("请求失败，请重试");
        }
    });
}

function checkEmail(email) {
    var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
    if(reg.test(email)){
        return true;
    }else{
        return false;
    }
    return false;
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

//渲染table
function renderingLists(msg) {
    for(var i in msg){
        $(".driver_list").append('<tr>' +
            '                    <td>'+msg[i].driver_id+'</td>' +
            '                    <td class="change_able"><input class="'+msg[i].driver_email+'" type="text" value="'+msg[i].driver_name+'" onblur="onInputNameBlur(this)" onfocus="onInputNameFocus(this)"/></td>' +
            '                    <td class="change_able"><input class="'+msg[i].driver_email+'" type="text" value="'+msg[i].driver_gender+'" onblur="onInputGenderBlur(this)" onfocus="onInputGenderFocus(this)"/></td>' +
            '                    <td class="change_able"><input class="'+msg[i].driver_email+'" type="text" value="'+msg[i].driver_phone+'" onblur="onInputPhoneBlur(this)" onfocus="onInputPhoneFocus(this)"/></td>' +
            '                    <td class="change_able"><input class="'+msg[i].driver_email+'" type="text" value="'+msg[i].driver_line+'" onblur="onInputLineBlur(this)" onfocus="onInputLineFocus(this)"/></td>' +
            '                    <td><img class="'+msg[i].driver_email+'" src="images/ic_delete_inline.png" onclick="onDeleteInline(this)"/></td>' +
            '                </tr>');
    }
}
//渲染表头
function renderingListHead() {
    $(".driver_list").append('<tr>\n' +
        '                    <td style="width: 100px;">工号</td>\n' +
        '                    <td>姓名</td>\n' +
        '                    <td>性别</td>\n' +
        '                    <td>电话</td>\n' +
        '                    <td>线路</td>\n' +
        '                    <td style="width: 50px;">删除</td>\n' +
        '                </tr>');
}

//实时更改密码
var driver_name_old, driver_gender_old, driver_phone_old, driver_line_old;
function onInputNameFocus(obj) {
    driver_name_old = $(obj).val();
}

function onInputNameBlur(obj) {
    console.log($(obj).val());
    console.log($(obj).attr("class"));
    var name_new = $(obj).val();
    var email_new = $(obj).attr("class");
    if(name_new != driver_name_old){
        console.log("Run...");
        onChangeData(email_new, "name", name_new);
    }
}

function onInputGenderFocus(obj) {
    driver_gender_old = $(obj).val();
}

function onInputGenderBlur(obj) {
    console.log($(obj).val());
    console.log($(obj).attr("class"));
    var gender_new = $(obj).val();
    var email_new = $(obj).attr("class");
    if(gender_new != driver_gender_old){
        console.log("Run...");
        onChangeData(email_new, "gender", gender_new);
    }
}

function onInputPhoneFocus(obj) {
    driver_phone_old = $(obj).val();
}

function onInputPhoneBlur(obj) {
    console.log($(obj).val());
    console.log($(obj).attr("class"));
    var phone_new = $(obj).val();
    var email_new = $(obj).attr("class");
    if(phone_new != driver_phone_old){
        console.log("Run...");
        onChangeData(email_new, "phone", phone_new);
    }
}

function onInputLineFocus(obj) {
    driver_line_old = $(obj).val();
}

function onInputLineBlur(obj) {
    console.log($(obj).val());
    console.log($(obj).attr("class"));
    var line_new = $(obj).val();
    var email_new = $(obj).attr("class");
    if(line_new != driver_line_old){
        console.log("Run...");
        onChangeData(email_new, "line", line_new);
    }
}
//////////////


var driver_delete;
function onDeleteInline(obj) {
    driver_delete = $(obj).attr("class");
    $(".dialog_content").fadeIn("fast");
    $(".dialog_pop_add_driver").hide();
    $(".dialog_ensure_delete").fadeIn("fast");
}

//确认删除
function onEnsureBtnClicked() {
    //删除信息ajax_deleteDriver_POST
    //发出(data)：邮箱email
    //接收(json)：ifsuccess:0(失败),1(成功)
    var data= {email:driver_delete};
    console.log(data);
    console.log("DeleteDriverAjax");
    $.ajax({
        url: "/IBDS/deleteBook",
        dataType: "json",
        data:JSON.stringify(data),
        type: "post",
        success: function (msg) {
            console.log("DeleteDriverAjax:Success!");
            console.log(msg);
            if(msg.ifsuccess == '1'){
                location.reload();
            }else{
                alert("内部错误，请联系管理员！！！");
            }
        },
        error: function (msg) {
            console.log("DeleteDriverAjax:Error!");
            console.log(msg);
            alert("请求失败，请重试");
        }
    });
};

//修改数据
function onChangeData(email, data_type, data_new) {
    //修改数据ajax_modifyData_POST
    //发出(data)：司机邮箱email,新数据data_new
    //接收(json)：ifsuccess:0(失败),1(成功)
    var data= {email:email,data_type:data_type,data_new:data_new};
    console.log(data);
    console.log("ModifyDataAjax");
    $.ajax({
        url: "/IBDS/modifyData", //后台请求数据,encodeing
        dataType: "json",
        data:JSON.stringify(data),
        type: "post",
        success: function (msg) {
            console.log("ModifyDataAjax:Success!");
            console.log(msg);
            if(msg.ifsuccess){
                location.reload();
            }else {
                alert("修改数据失败!!!")
            }
        },
        error: function (msg) {
            console.log("ModifyDataAjax:Error!");
            console.log(msg);
            alert("请求失败，请重试!");
        }
    });
}

//添加司机
function onAddDriverBtn() {
    var add_email = $.trim($('#add_email').val());
    var add_name = $.trim($('#add_name').val());
    var add_id = $.trim($('#add_id').val());
    var add_phone = $.trim($('#add_phone').val());
    var add_line = $.trim($('#add_line').val());
    var add_gender = $.trim($(".gender_line input[name=\"input_gender\"]:checked").val());
    if(checkEmail(add_email)){
        $('#add_email').css({'border-color': 'rgba(203,54,56,0)'});
        if(add_name != '' && add_name != null){
            $('#add_name').css({'border-color': 'rgba(203,54,56,0)'});
            if(add_id != '' && add_id != null){
                $('#add_id').css({'border-color': 'rgba(203,54,56,0)'});
                if(add_phone != '' && add_phone != null){
                    $('#add_phone').css({'border-color': 'rgba(203,54,56,0)'});
                    if(add_line != '' && add_line != null){
                        $('#add_line').css({'border-color': 'rgba(203,54,56,0)'});
                        if(add_gender != '' && add_gender != null){
                            $('.gender_line').css({'border-color': 'rgba(203,54,56,0)'});
                            //添加司机ajax_addDriver_POST
                            //发出(data)：邮箱email, 司机姓名name, 工号id, 电话phone, 线路line, 性别gender
                            //接收(json)：ifsuccess:0(失败),1(成功)
                            var data= {email:add_email,name:add_name,id:add_id,phone:add_phone,line:add_line,gender:add_gender};
                            console.log(data);
                            console.log("AddDriverAjax");
                            $.ajax({
                                url: "/IBDS/addDriver", //后台请求数据
                                dataType: "json",
                                data:JSON.stringify(data),
                                type: "post",
                                success: function (msg) {
                                    console.log("AddDriverAjax:Success!");
                                    console.log(msg);
                                    if(msg.ifsuccess == '1'){
                                        location.reload();
                                    }else {
                                        alert("添加司机失败");
                                    }
                                },
                                error: function (msg) {
                                    console.log("AddDriverAjax:Error!");
                                    console.log(msg);
                                    alert("请求失败，请重试");
                                }
                            });
                        }else {
                            $('.gender_line').css({'border-color': '#cb3638'});
                            $('.gender_line').shake(2, 10, 400);
                        }
                    }else {
                        $('#add_line').css({'border-color': '#cb3638'});
                        $('#add_line').shake(2, 10, 400);
                    }
                }else {
                    $('#add_phone').css({'border-color': '#cb3638'});
                    $('#add_phone').shake(2, 10, 400);
                }
            }else {
                $('#add_id').css({'border-color': '#cb3638'});
                $('#add_id').shake(2, 10, 400);
            }
        }else {
            $('#add_name').css({'border-color': '#cb3638'});
            $('#add_name').shake(2, 10, 400);
        }
    }else {
        $('#add_email').css({'border-color': '#cb3638'});
        $('#add_email').shake(2, 10, 400);
    }
}

//搜索
function onSearchBtn() {
    var key_words = $.trim($('.manager_drivers_search').val());
    if(key_words != '' && key_words != null){
        //搜索司机ajax_search_POST
        //发出(data)：关键词key_words
        //接收(jsonArray)：[0:{ifsuccess:0(失败),1(成功)}, 1:{工号id, 姓名name, 性别gender, 电话phone, 线路line}, ...]
        var data= {key_words:key_words};
        console.log(data);
        console.log("SearchAjax");
        $.ajax({
            url: "/IBDS/search", //后台请求数据
            dataType: "json",
            data:JSON.stringify(data),
            type: "post",
            success: function (msg) {
                console.log("SearchAjax:Success!");
                console.log(msg);
                $(".driver_list").html("");
                renderingListHead();
                renderingLists(msg);
            },
            error: function (msg) {
                console.log("SearchAjax:Error!");
                console.log(msg);
                alert("请求失败，请重试");
            }
        });
    }
}