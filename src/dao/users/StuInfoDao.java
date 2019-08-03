package dao.users;

import bean.UserInfo;

public interface StuInfoDao {
    //添加一个用户
    public boolean ModifyStuInfo(String stu_id, String subSql) throws Exception;

    //用户已核对信息
    public boolean hasChecked(String stu_id) throws Exception;

    //通过user_id查询一个用户
    public UserInfo findInfoById(String stu_id) throws Exception;
}
