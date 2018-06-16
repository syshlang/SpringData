package com.syshlang;

import com.syshlang.common.AddressRepository;
import com.syshlang.entity.Person;
import com.syshlang.repository.PersonRepsotory;
import com.syshlang.service.PersonService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    private ApplicationContext ctx = null;
    private PersonRepsotory personRepsotory = null;
    private PersonService personService;

    {
        ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        personRepsotory = ctx.getBean(PersonRepsotory.class);
        personService = ctx.getBean(PersonService.class);
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


    /**
     *简单条件查询  使用关键字
     */
    @Test
    public void testKeyWords(){
        List<Person> persons = personRepsotory.getByLastNameStartingWithAndIdLessThan("X", 10);
        System.out.println(persons);
        persons = personRepsotory.getByLastNameEndingWithAndIdLessThan("X", 10);
        System.out.println(persons);
        persons = personRepsotory.getByEmailInAndBirthLessThan(Arrays.asList("AA@atguigu.com", "FF@atguigu.com",
                "SS@atguigu.com"), new Date());
        System.out.println(persons.size());
    }


    /**
     * 简单条件查询  使用关键字
     * 在属性之间加上 “_” 以显式表达意图
     */
    @Test
    public void testKeyWords2(){
        List<Person> persons = personRepsotory.getByAddress_IdGreaterThan(1);
        System.out.println(persons);
    }

    /**
     * 使用 @Query 注解可以自定义 JPQL 语句以实现更灵活的查询
     */
    @Test
    public void testQueryAnnotation(){
        Person person = personRepsotory.getMaxIdPerson();
        System.out.println(person);
    }

    /**
     * 为 @Query 注解传递参数的方式1: 使用占位符.
     */
    @Test
    public void testQueryAnnotationParams1(){
        List<Person> persons = personRepsotory.testQueryAnnotationParams1("AA", "aa@atguigu.com");
        System.out.println(persons);
    }


    /**
     * 为 @Query 注解传递参数的方式1: 命名参数的方式.
     */
    @Test
    public void testQueryAnnotationParams2(){
        List<Person> persons = personRepsotory.testQueryAnnotationParams2("aa@atguigu.com", "AA");
        System.out.println(persons);
    }

    /**
     * SpringData 允许在占位符上添加 %%.
     */
    @Test
    public void testQueryAnnotationLikeParam(){
		//List<Person> persons = personRepsotory.testQueryAnnotationLikeParam("%A%", "%bb%");
		//System.out.println(persons.size());

		//List<Person> persons = personRepsotory.testQueryAnnotationLikeParam("A", "bb");
		//System.out.println(persons.size());

        List<Person> persons = personRepsotory.testQueryAnnotationLikeParam2("bb", "A");
        System.out.println(persons.size());
    }


    /**
     * 设置 nativeQuery=true 即可以使用原生的 SQL 查询
     */
    @Test
    public void testNativeQuery(){
        long count = personRepsotory.getTotalCount();
        System.out.println(count);
    }


    /**
     * 自定义的 JPQL 完成 UPDATE 和 DELETE 操作
     *  @Transactional
     */
    @Test
    public void testModifying1(){
		personRepsotory.updatePersonEmail(1, "mmmm@atguigu.com");
    }

    /**
     * 改变 Spring Data 提供的事务默认方式，可以在方法上注解 @Transactional 声明
     */
    @Test
    public void testModifying2(){
        personService.updatePersonEmail("mmmm@atguigu.com", 1);
    }

    /**
     *  改变 Spring Data 提供的事务默认方式，可以在方法上注解 @Transactional 声明
     */
    @Test
    public void testCrudReposiory(){
        List<Person> persons = new ArrayList<>();

        for(int i = 'a'; i <= 'z'; i++){
            Person person = new Person();
            person.setAddressId(i + 1);
            person.setBirth(new Date());
            person.setEmail((char)i + "" + (char)i + "@atguigu.com");
            person.setLastName((char)i + "" + (char)i);

            persons.add(person);
        }
        personService.savePersons(persons);
    }

    /**
     * Pageable
     * Sort
     *
     */
    @Test
    public void testPagingAndSortingRespository(){
        //pageNo 从 0 开始.
        int pageNo = 6 - 1;
        int pageSize = 5;
        //Pageable 接口通常使用的其 PageRequest 实现类. 其中封装了需要分页的信息
        //排序相关的. Sort 封装了排序的信息
        //Order 是具体针对于某一个属性进行升序还是降序.
        Sort.Order order1 = new Sort.Order(Direction.DESC, "id");
        Sort.Order order2 = new Sort.Order(Direction.ASC, "email");
        Sort sort = new Sort(order1, order2);

        PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
        Page<Person> page = personRepsotory.findAll(pageable);

        System.out.println("总数记录: " + page.getTotalElements());
        System.out.println("当前第几页: " + (page.getNumber() + 1));
        System.out.println("总页数: " + page.getTotalPages());
        System.out.println("当前页面的 List: " + page.getContent());
        System.out.println("当前页面的记录数: " + page.getNumberOfElements());
    }


    /**
     * save方法具有延迟性
     *在高并发下建议用saveAndFlush
     */
    @Test
    public void testJpaRepository(){
        Person person = new Person();
        person.setBirth(new Date());
        person.setEmail("xy@atguigu.com");
        person.setLastName("xyz");
        person.setId(28);

        Person person2 = personRepsotory.saveAndFlush(person);

        System.out.println(person == person2);
    }

    /**
     * 目标: 实现带查询条件的分页. id > 5 的条件
     *
     * 调用 JpaSpecificationExecutor 的 Page<T> findAll(Specification<T> spec, Pageable pageable);
     * Specification: 封装了 JPA Criteria 查询的查询条件
     * Pageable: 封装了请求分页的信息: 例如 pageNo, pageSize, Sort
     */
    @Test
    public void testJpaSpecificationExecutor(){
        int pageNo = 3 - 1;
        int pageSize = 5;
        PageRequest pageable = new PageRequest(pageNo, pageSize);

        //通常使用 Specification 的匿名内部类
        Specification<Person> specification = new Specification<Person>() {
            /**
             * @param *root: 代表查询的实体类.
             * @param query: 可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
             * 来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象.
             * @param *cb: CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到 Predicate 对象
             * @return: *Predicate 类型, 代表一个查询条件.
             */
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path path = root.get("id");
                Predicate predicate = cb.gt(path, 5);
                return predicate;
            }
        };

        Page<Person> page = personRepsotory.findAll(specification, pageable);

        System.out.println("总记录数: " + page.getTotalElements());
        System.out.println("当前第几页: " + (page.getNumber() + 1));
        System.out.println("总页数: " + page.getTotalPages());
        System.out.println("当前页面的 List: " + page.getContent());
        System.out.println("当前页面的记录数: " + page.getNumberOfElements());
    }

    /**
     *
     * 测试为某一个 Repository 上添加自定义方法
     */
    @Test
    public void testCustomRepositoryMethod(){
        personRepsotory.test();
    }

    @Test
    public void testCommonCustomRepositoryMethod(){
        ApplicationContext ctx1 = new ClassPathXmlApplicationContext("classpath:applicationContext1.xml");
        AddressRepository addressRepository = ctx1.getBean(AddressRepository.class);
        addressRepository.method();
    }
}
