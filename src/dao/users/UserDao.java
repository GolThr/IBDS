package dao.users;

import bean.User;

public interface UserDao {
    //添加一个用户
    public boolean addUser(User user) throws Exception;

    //修改密码
    public boolean changePassword(String stu_id, String password) throws Exception;

    //通过user_id查询一个用户
    public User findUserById(String stu_id) throws Exception;
}
