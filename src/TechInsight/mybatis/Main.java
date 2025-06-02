package TechInsight.mybatis;


/**
 * @author gongxuanzhangmelt@gmail.com
 **/
public class Main {

    public static void main(String[] args) throws Exception {
        // 创建一个自定义的mysql会话工厂
        MySqlSessionFactory mySqlSessionFactory = new MySqlSessionFactory();
        // 通过工厂创建一个指定的Mapper代理对象
        UserMapper mapper = mySqlSessionFactory.getMapper(UserMapper.class);
        // 调用代理对象的方法，本质上是调用UserMapper中的方法
        User user = mapper.selectById(2);
        System.out.println(user);
        System.out.println(mapper.selectByName("jerry"));
        System.out.println(mapper.selectByNameAndAge("jerry",10));
    }

}
