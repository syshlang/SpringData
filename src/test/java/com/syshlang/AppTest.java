package com.syshlang;

import com.syshlang.entity.Person;
import com.syshlang.repository.PersonRepsotory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    private ApplicationContext ctx = null;

    {
        ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    /**
     * 测试数据源
     * @throws SQLException
     */
    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    /**
     * 测试 配置 JPA 的 EntityManagerFactory
     */
    @Test
    public void testJpa(){

    }

    @Test
    public void testSpringData(){
        PersonRepsotory personRepsotory = ctx.getBean(PersonRepsotory.class);
        Person person = personRepsotory.getByLastName("AA");
        System.out.println(person);
    }

}
