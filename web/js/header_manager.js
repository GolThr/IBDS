document.writeln('<div class="default_header">\n' +
    '        <div class="default_header_body">\n' +
    '            <div class="default_header_left">\n' +
    '                <img class="logo" src="images/logo_line.png " onclick=""/>\n' +
    '                <span class="default_title">智能公交调度系统</span>\n' +
    '            </div>\n' +
    '            <div class="default_func_line">\n' +
    '                <ul class="default_func">\n' +
    '                    <li id="manager_upload" onmouseover="onManagerFunc()" onmouseleave="outManagerFunc()" onclick="location.href=\'/IBDS/manager_upload.html\'">数据上传</li>\n' +
    '                    <li id="manager_drivers" onclick="location.href=\'/IBDS/manager_drivers.html\'">管理公交人员</li>\n' +
    '                    <li id="manager_result" onclick="location.href=\'/IBDS/manager_result.html\'">查看发车情况</li>\n' +
    '                    <li id="manager_mine" onclick="location.href=\'/IBDS/manager_mine.html\'">个人中心</li>\n' +
    '                </ul>\n' +
    '                <div class="data_uploade_tab_list" style="display: none;" onmouseover="onManagerFunc()" onmouseleave="outManagerFunc()">\n' +
    '                    <ul>\n' +
    '                        <li>上传公交数据</li>\n' +
    '                        <li>上传司机信息</li>\n' +
    '                    </ul>\n' +
    '                </div>\n' +
    '            </div>\n' +
    '            <div class="default_user_body">\n' +
    '                <img class="manager_head" src="images/head_default_manager.png " onclick=""/>\n' +
    '                <span class="manager_name">Manager</span>\n' +
    '            </div>\n' +
    '        </div>\n' +
    '    </div>');

function onManagerFunc() {
    $(".data_uploade_tab_list").stop();
    $(".data_uploade_tab_list").slideDown(260);
}

function outManagerFunc() {
    $(".data_uploade_tab_list").stop();
    $(".data_uploade_tab_list").slideUp(100);
}

var user_info;
function initManagerHeader() {
    user_info = JSON.parse(sessionStorage.getItem("user_info"));
    console.log(user_info);
    if(user_info == null || user_info.ifsuccess != "1"){
        location.href = "index.html";
    }else if(user_info.user_type != '0'){
        location.href = "index.html";
    }else{
        $(".manager_head").attr("src", '/IBDS/head/'+user_info.avatar);
        $('.manager_name').text(user_info.username);
    }
}