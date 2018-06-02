package com.syshlang;

import static org.junit.Assert.assertTrue;

import com.syshlang.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    private ApplicationContext ctx = null;
    private PersonRepsotory personRepsotory = null;
    {
        ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testJpa(){

    }

    @Test
    public void testHelloWorldSpringData() {
        System.out.println(personRepsotory.getClass().getName());

        User user = personRepsotory.getByLastName("AA");
        System.out.println(user);
    }

}
