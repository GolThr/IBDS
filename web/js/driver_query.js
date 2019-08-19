var if_signup = false;
var driver_line;
function init() {
    initDriverHeader();

    $("#driver_query").css({'border-bottom':'rgba(255, 255, 255, 1) solid 5px'});

    //初始化界面信息ajax_initDriverDays_POST
    //发出(data)：司机邮箱email, 公司名称company
    //接收(json)：ifsuccess:0(失败),1(成功), 线路line, 当天时间today_date(日期示例：2019年8月9日), 公司管理员姓名admin, 公司电话phone, 是否签到if_signup(未签到0, 已签到1), 签到天数signup_days
    var data = {email:user_info.email,company:user_info.company};
    console.log(data);
    console.log("InitDriverDaysAjax");
    $.ajax({
        url: "/IBDS/initDriverDays", //后台请求数据
        dataType: "json",
        data:JSON.stringify(data),
        type: "post",
        success: function (msg) {
            console.log("InitDriverDaysAjax:Success!");
            console.log(msg);
            if(msg.ifsuccess == '1'){
                driver_line = msg.line;
                renderingListHeadInfo(msg);
                $('#company_phone').html(msg.admin+'<br>'+msg.phone);
                $('.driver_func_signin_days').text(msg.signup_days);
                if(msg.if_signup == '1'){
                    if_signup = true;
                    $('.driver_func_signin_link').text("今天已签到");
                }else {
                    if_signup = false;
                    $('.driver_func_signin_link').text("立即签到");
                }
            }else {
                alert('内部错误，请联系管理员！！！');
            }
        },
        error: function (msg) {
            console.log("InitDriverDaysAjax:Error!");
            console.log(msg);
            alert("请求失败，请重试");
            // msg = {ifsuccess:'1',line:'bfsjbghj',today_date:'2019年8月9日',admin:'me',phone:'123456',if_signup:'0',signup_days:'321'};
        }
    });

    onFilterTable('self');
}

function renderingNonePage() {
    $(".driver_list_body").append('<div class="item_none">\n' +
        '                    <img src="images/pic_none.png" onclick="" style="width: 200px;height: auto;"/>\n' +
        '                    <span style="width: 100%;text-align: center;margin-top: 30px;">未查询到信息</span>\n' +
        '                </div>');
}

function renderingListHeadInfo(msg) {
    var today = msg.today_date;
    $('#table_date').text(today.substr(0, 4) + '年' + today.substr(5, 2) + '月' + today.substr(8, 2) + '日');
    $('#table_line').text(msg.line);
}

function renderingListHead(msg) {
    $(".driver_list_body").append('<table class="data_list time_table_list" style="border-collapse:separate; border-spacing:0px 10px;">\n' +
        '                <tr>\n' +
        '                    <td>司机姓名</td>\n' +
        '                    <td>发车时间</td>\n' +
        '                </tr>\n' +
        '            </table>');
}

function renderingLists(msg) {
    for(var i in msg){
        $(".time_table_list").append('<tr>\n' +
            '                    <td>'+msg[i].driver_name+'</td>\n' +
            '                    <td>'+msg[i].start_time+'</td>\n' +
            '                </tr>');
    }
}

function doPrint() {
    bdhtml=window.document.body.innerHTML;
    sprnstr="<!--startprint-->"; //开始打印标识字符串有17个字符
    eprnstr="<!--endprint-->"; //结束打印标识字符串
    prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17); //从开始打印标识之后的内容
    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); //截取开始标识和结束标识之间的内容
    window.document.body.innerHTML=prnhtml; //把需要打印的指定内容赋给body.innerHTML
    window.print(); //调用浏览器的打印功能打印指定区域
    window.document.body.innerHTML=bdhtml; // 最后还原页面
}

function doSave() {
    //发车表保存到本地ajax_saveTimeTable_POST
    //发出(data)：公司名称company
    //接收(json)：ifsuccess:0(失败),1(成功), 文件路径url
    var data = {company:user_info.company};
    console.log(data);
    console.log("SaveTimeTableAjax");
    $.ajax({
        url: "/IBDS/saveTimeTable", //后台请求数据
        dataType: "json",
        data:JSON.stringify(data),
        type: "post",
        success: function (msg) {
            console.log("LoginAjax:Success!");
            console.log(msg);
            if(msg.ifsuccess){
                window.open(msg.url);
            }
        },
        error: function (msg) {
            console.log("LoginAjax:Error!");
            console.log(msg);
            alert("请求失败，请重试");
        }
    });
}

function doSignUp() {
    if(!if_signup){
        //签到ajax_signUp_POST
        //发出(data)：司机邮箱email
        //接收(json)：ifsuccess:0(失败),1(成功), 签到天数signup_days
        var data= {email:user_info.email};
        console.log(data);
        console.log("SignUpAjax");
        $.ajax({
            url: "/IBDS/signUp", //后台请求数据
            dataType: "json",
            data:JSON.stringify(data),
            type: "post",
            success: function (msg) {
                console.log("SignUpAjax:Success!");
                console.log(msg);
                if(msg.ifsuccess == '1'){
                    if_signup = true;
                    $('.driver_func_signin_days').text(msg.signup_days);
                    $('.driver_func_signin_link').text("今天已签到");
                }else {
                    alert('内部错误，请联系管理员！！！');
                }
            },
            error: function (msg) {
                console.log("SignUpAjax:Error!");
                console.log(msg);
                alert("请求失败，请重试");
                // msg={ifsuccess:'1',signup_days:'323'};
            }
        });
    }
}

function doPostNotes() {
    var note_content = $.trim($('.driver_func_note_info').val());
    if(note_content != '' && note_content != null){
        //留言ajax_postNotes_POST
        //发出(data)：邮箱email, 留言内容note_content
        //接收(json)：ifsuccess:0(失败),1(成功)
        var data= {email:user_info.email,note_content:note_content};
        console.log(data);
        console.log("PostNotesAjax");
        $.ajax({
            url: "/IBDS/postNotes", //后台请求数据
            dataType: "json",
            data:JSON.stringify(data),
            type: "post",
            success: function (msg) {
                console.log("PostNotesAjax:Success!");
                console.log(msg);
                if(msg.ifsuccess == '1'){
                    $('.driver_func_note_info').val('');
                    showTip('发表留言成功');
                }else {
                    showTip('内部错误，请联系管理员');
                }
            },
            error: function (msg) {
                console.log("PostNotesAjax:Error!");
                console.log(msg);
                alert("请求失败，请重试");
            }
        });
    }else {
        showTip('请输入留言内容');
    }
}

function showTip(msg){
    $('.dialog_content').fadeIn('fast');
    $('.dialog_pop_tips').fadeIn('fast');
    $('.dialog_title_tips').text(msg);
}

function onFilterTable(type) {
    //筛选发车表ajax_filterTimeTable_POST
    //发出(data)：公司名称company, 司机邮箱email, 筛选类别filter_type(查看全部all, 只看自己self), 线路line
    //接收(jsonArray)：司机姓名driver_name, 发车时间start_time
    var data= {company:user_info.company,email:user_info.email,filter_type:type,line:driver_line};
    console.log(data);
    console.log("FilterTimeTableAjax");
    $.ajax({
        url: "/IBDS/filterTimeTable", //后台请求数据
        dataType: "json",
        data:JSON.stringify(data),
        type: "post",
        success: function (msg) {
            console.log("FilterTimeTableAjax:Success!");
            console.log(msg);
            $(".driver_list_body").html("");
            renderingListHead(msg);
            if(msg[0] != null){
                renderingLists(msg);
            }else {
                renderingNonePage();
            }
        },
        error: function (msg) {
            console.log("FilterTimeTableAjax:Error!");
            console.log(msg);
            alert("请求失败，请重试");
            // msg=[{driver_name:'sjgnr',start_time:'12:21'}];
        }
    });
}

function onFuncPhone() {
    $(".driver_func_phone_content").stop();
    $(".driver_func_phone_content").fadeIn(500);
}

function outFuncPhone() {
    $(".driver_func_phone_content").stop();
    $(".driver_func_phone_content").fadeOut(250);
}

function onFuncSignIn() {
    $(".driver_func_signin_content").stop();
    $(".driver_func_signin_content").fadeIn(500);
}

function outFuncsignIn() {
    $(".driver_func_signin_content").stop();
    $(".driver_func_signin_content").fadeOut(250);
}

function onFuncNote() {
    $(".driver_func_note_content").stop();
    $(".driver_func_note_content").fadeIn(500);
}

function outFuncNote() {
    $(".driver_func_note_content").stop();
    $(".driver_func_note_content").fadeOut(250);
}