document.writeln("<div class=\"default_header\">\n" +
    "        <div class=\"default_header_body\">\n" +
    "            <div class=\"default_header_left\">\n" +
    "                <img class=\"logo\" src=\"images/logo_line.png \" onclick=\"\"/>\n" +
    "                <span class=\"default_title\">智能公交调度系统</span>\n" +
    "            </div>\n" +
    "            <div class=\"default_func_line\">\n" +
    "                <ul class=\"default_func\">\n" +
    "                    <li id=\"driver_query\" onclick=\"location.href='/IBDS/driver_query.html'\">发车查询</li>\n" +
    "                    <li id=\"driver_mine\" onclick=\"location.href='/IBDS/driver_mine.html'\">个人中心</li>\n" +
    "                </ul>\n" +
    "            </div>\n" +
    "            <div class=\"default_user_body\">\n" +
    "                <img class=\"manager_head\" src=\"images/head_default_manager.png \" onclick=\"\"/>\n" +
    "                <span class=\"manager_name\">Driver</span>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "    </div>");

var user_info;
function initDriverHeader() {
    user_info = JSON.parse(sessionStorage.getItem("user_info"));
    console.log(user_info);
    if(user_info == null || user_info.ifsuccess != "1"){
        location.href = "index.html";
    }else if(user_info.user_type != '1'){
        location.href = "index.html";
    }else{
        $(".manager_head").attr("src", '/IBDS/head/'+user_info.avatar);
        $('.manager_name').text(user_info.username);
    }
}