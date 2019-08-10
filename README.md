# IBDS
IBDS(IntelligenceBusDispatchSystem)智能公交调度系统

*****************************
1. 形如ajax_login_POST的字段是通过搜索用来在相应js文件定位的；
2. 所有头像请保存在/IBDS/head/文件夹下
3. 所有文件如需保存请保存在/IBDS/asstes/文件夹下
-----------------------------

login_register.js
1. 登录ajax_login_POST
2. 获取验证码ajax_getVerifyCode_POST
3. 找回密码ajax_forgotPWD_POST
4. 注册ajax_register_POST

manager_upload.js
1. 上传文件ajax_uploadFile_POST

manager_drivers.js
1. 初始化司机信息ajax_initDrivers_POST
2. 删除信息ajax_deleteDriver_POST
3. 修改数据ajax_modifyData_POST
4. 添加司机ajax_addDriver_POST
5. 搜索司机ajax_search_POST

manager_mine.js
1. 修改头像ajax_changeAvatar_POST
2. 修改信息ajax_modifyManagerInfo_POST
3. 查看留言ajax_initNotes_POST
4. 修改密码ajax_changePWD_POST

driver_query.js
1. 初始化界面信息ajax_initDriverDays_POST
2. 筛选发车表ajax_filterTimeTable_POST
3. 发车表保存到本地ajax_saveTimeTable_POST
4. 签到ajax_signUp_POST
5. 留言ajax_postNotes_POST