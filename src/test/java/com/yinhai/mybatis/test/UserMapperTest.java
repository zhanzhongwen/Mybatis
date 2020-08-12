package com.yinhai.mybatis.test;

import com.yinhai.mybatis.dao.UserMapper;
import com.yinhai.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class UserMapperTest {
    String resource = "mybatis-config.xml";
    public UserMapper userMapper;
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    @Before
    public void serUp() throws Exception{
        // 指定配置文件
        //String resource = "mybatis-config.xml";
        // 读取配置文件
        inputStream = Resources.getResourceAsStream(resource);
        // 构建sqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取sqlSession
        sqlSession = sqlSessionFactory.openSession(true);

        // 1. 映射文件的命名空间（namespace）必须是mapper接口的全路径
        // 2. 映射文件的statement的id必须和mapper接口的方法名保持一致
        // 3. Statement的resultType必须和mapper接口方法的返回类型一致
        // 4. statement的parameterType必须和mapper接口方法的参数类型一致（不一定）
        this.userMapper = sqlSession.getMapper(UserMapper.class);
        //问题为何这里是个接口对象？参数是一个接口，返回结果是一个接口？接口是无法实例化的
    }
    @Test
    public void testQueryUserByTableName() {
        List<User> userList = userMapper.queryUserByTableName("tb_user");
        for(User u : userList){
            System.out.println(u);
        }
    }
   @Test
    public void testQueryUserById() {

        System.out.println(this.userMapper.queryUserById("1"));
        //mybaits默认开启的一级缓存，再次执行同样SQL,Mybaits选择在缓存中命中返回
        //一级缓存满足条件：
        //1、同一个session中
        //2、相同的SQL和参数
        //如果要再次执行同样的SQL  ,可以关闭缓存sqlSession.clearCache();可以强制清除缓存
        //执行update、insert、delete的时候，会清空缓存
        sqlSession.clearCache();
        System.out.println(this.userMapper.queryUserById("1"));
    }

    @Test
    public void testInsertUser() throws ParseException {
        User user = new User();
        user.setAge(20);
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("2009-01-09"));
        user.setName("大神");
        user.setPassword("123456");
        user.setSex(2);
        user.setUserName("bigGod222");
        user.setId("4");
        this.userMapper.insertUser(user);
        System.out.println(user.getId());
    }
    @Test
    public void testLogin() {
        //this.userMapper.login("hj","123456");
        System.out.println(this.userMapper.login("hj","123456"));
    }
    @Test
    public void testQueryUserListByName1(){
        System.out.println(userMapper.queryUserListByName1("zpc"));

    }
    @Test
    public void testQueryUserListByName2(){
        System.out.println(userMapper.queryUserListByName2("zpc"));
    }

    @Test
    public void testqueryUserList() {
        List<User> users = this.userMapper.queryUserList("大鹏展翅");
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void queryUserListByNameOrAge() throws Exception {
        List<User> users = this.userMapper.queryUserListByNameOrAge(null, null);
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void queryUserListByNameAndAge() throws Exception {
        List<User> users = this.userMapper.queryUserListByNameAndAge("鹏程", null);
        for (User user : users) {
            System.out.println(user);
        }
    }
    //TODO 这里测试有问题
    @Test
    public void testUpdateUserById() throws ParseException {
        User user = new User();
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("2009-01-09"));
        user.setName("静静");
        user.setPassword("123456");
        user.setSex(0);
        user.setUserName("Jinjin");
        user.setId("1");
        this.userMapper.updateUser(user);
    }

    @Test
    public void queryUserListByIds() throws Exception {
        List<User> users = this.userMapper.queryUserListByIds(new String[]{"1","2"});
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * mybaits的二级缓存作用域是一个mapper的namespace ，同一个namespace中查询sql可以从缓存中命中
     */
    @Test
    public void testCache() {
        System.out.println(this.userMapper.queryUserById("1"));

        sqlSession.close();
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession = sqlSessionFactory.openSession(); //打开一个新的session
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(mapper.queryUserById("1"));//查看二级缓存是否生效，即不执行sql
    }

    /**
     * 逻辑分页
     */
    @Test
    public void testQueryOrderByRowBounds() throws IOException {
        inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int start = 0;//offset
        int pageSize = 2;//limit
        RowBounds rb = new RowBounds(start,pageSize);
        List<User> userList = userMapper.selectUserList(rb);//sql语句中没有任何参数
        for(User user : userList){
            System.out.println(user);
        }
        sqlSession.close();
    }


}
