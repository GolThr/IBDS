document.writeln("<div class=\"default_header\">\n" +
    "        <div class=\"default_header_body\">\n" +
    "            <div class=\"default_header_left\">\n" +
    "                <img class=\"logo\" src=\"images/logo_line.png \" onclick=\"\"/>\n" +
    "                <span class=\"default_title\">智能公交调度系统</span>\n" +
    "            </div>\n" +
    "            <div class=\"btn_line\">\n" +
    "                <button class=\"go_login\">登录</button>\n" +
    "                <button class=\"go_register\">注册</button>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "    </div>");

$(".go_login").click(function (e) {
    location.href = '/IBDS/login.html';
});