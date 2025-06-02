package TechInsight.mybatis;


import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gongxuanzhangmelt@gmail.com
 **/
public class MySqlSessionFactory {

    // 本该从配置文件中读取的数据库连接信息，这里作为示例写成本地变量
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mybatis_db";
    private static final String DB_USER = "root";
    private static final String PASSWORD = "123456";

    /**
     * 生成一个指定的Mapper的代理对象
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/1 14:57
     * @param: mapperClass指定的Mapper接口，通过解析这个Mapper接口中定义的方法，生成对应的SQL语句，
     * 然后通过JDBC执行SQL语句，获取查询结果，最后将结果封装成指定的Mapper接口的实现类对象返回。
     * 这样，我们就可以通过调用Mapper接口的方法来执行数据库操作，而不需要直接编写SQL语句。
     * 例如，我们可以通过UserMapper的selectById方法来查询指定ID的用户信息，而不需要编写SQL语句。
     * 这种方式可以提高开发效率，减少代码冗余，并且可以方便地进行数据库操作的扩展和维护。
     * @return: 生成的代理对象，准确说是进行了动态实现mapper接口的代理对象
     **/
    @SuppressWarnings("all")
    public <T> T getMapper(Class<T> mapperClass) {
        // 通过动态代理，生成一个指定的Mapper的代理对象
        return (T) Proxy.newProxyInstance(
                // 类加载器，用于加载代理类
                this.getClass().getClassLoader(),
                // 代理类需要实现的接口列表，这里只需要实现mapperClass接口即可
                new Class[]{mapperClass},
                // 调用处理器，用于处理代理对象的方法调用，实质上的方法逻辑都在这里实现
                new MapperInvocationHandler());
    }

    /**
     * 调用处理器
     *
     * @Author: Alan [initator@alandevise.com]
     * @Date: 2025/6/1 15:08
     **/
    static class MapperInvocationHandler implements InvocationHandler {

        /**
         * 处理代理对象的方法调用
         *
         * @Author: Alan [initator@alandevise.com]
         * @Date: 2025/6/2 13:32
         * @param: proxy 代理对象
         * @param: method 被调用的方法类型比如select、insert、update、delete等
         * @param: args sql查询中的参数，比如id、name、age等
         * @return: 解析sql查询结果之后的返回结果
         **/
        @Override
        public Object invoke(Object proxy,
                             Method method,
                             Object[] args) throws Throwable {
            // 作为示例这里仅处理select方法
            if (method.getName().startsWith("select")) {
                return invokeSelect(proxy, method, args);
            }
            // 更多的方法可以写比如insert、update、delete等
            // 如果不是增删改查中的任何一种直接返回null
            return null;
        }

        private Object invokeSelect(Object proxy, Method method, Object[] args) {
            String sql = createSelectSql(method);
            System.out.println(sql);
            try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, PASSWORD);
                 PreparedStatement statement = conn.prepareStatement(sql)) {
                for (int i = 0; i < args.length; i++) {
                    Object arg = args[i];
                    if (arg instanceof Integer) {
                        statement.setInt(i + 1, (int) arg);
                    } else if (arg instanceof String) {
                        statement.setString(i + 1, arg.toString());
                    }
                }
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    return parseResult(rs, method.getReturnType());
                }
            } catch (Exception e) {
            }
            return null;
        }

        private Object parseResult(ResultSet rs, Class<?> returnType) throws Exception {
            Constructor<?> constructor = returnType.getConstructor();
            Object result = constructor.newInstance();
            Field[] declaredFields = returnType.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                Object column = null;
                String name = declaredField.getName();
                if (declaredField.getType() == String.class) {
                    column = rs.getString(name);
                } else if (declaredField.getType() == Integer.class) {
                    column = rs.getInt(name);
                }
                declaredField.setAccessible(true);
                declaredField.set(result, column);
            }
            return result;
        }

        private String createSelectSql(Method method) {
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("select ");
            List<String> selectCols = getSelectCols(method.getReturnType());
            sqlBuilder.append(String.join(",", selectCols));
            sqlBuilder.append(" from ");
            String tableName = getSelectTableName(method.getReturnType());
            sqlBuilder.append(tableName);
            sqlBuilder.append(" where ");
            //  select * from user where id = ?
            String where = getSelectWhere(method);
            sqlBuilder.append(where);
            return sqlBuilder.toString();
        }

        private String getSelectWhere(Method method) {
            return Arrays.stream(method.getParameters())
                    .map((parameter) -> {
                        Param param = parameter.getAnnotation(Param.class);
                        String column = param.value();
                        return column + " = ?";
                    })
                    .collect(Collectors.joining(" and "));
        }

        private String getSelectTableName(Class<?> returnType) {
            Table table = returnType.getAnnotation(Table.class);
            if (table == null) {
                throw new RuntimeException("返回值无法确定查询表");
            }
            return table.tableName();
        }

        private List<String> getSelectCols(Class<?> returnType) {
            Field[] declaredFields = returnType.getDeclaredFields();
            return Arrays.stream(declaredFields).map(Field::getName).toList();
        }

    }

}
