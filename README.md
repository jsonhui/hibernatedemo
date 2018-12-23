# hibernatedemo
Hibernate的配置详解

在Hibernate中，我们使用时主要有两种配置文件：

    核心配置文件——hibernate.cfg.xml(主要描述Hibernate的相关配置)
    映射配置文件——xxx.hbm.xml
核心配置文件
Hibernate的核心配置文件，即hibernate.cfg.xml，主要用来描述Hibernate的相关配置。对于Hibernate的核心配置文件它有两种方式：
    hibernate.cfg.xml
    hibernate.properties
我们在开发中使用比较多的是hibernate.cfg.xml这种方式，原因是它的配置能力更强，并且易于修改。所以我主要讲解的是hibernate.cfg.xml这种配置方式。
我以《Hibernate快速入门》一文案例中的hibernate.cfg.xml核心配置文件为例进行讲解。
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
    "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
    "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <!-- 配置关于数据库连接的四个项：driverClass  url username password -->
        <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql:///hibernateTest</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">yezi</property>
        <!-- 可以将向数据库发送的SQL语句显示出来 -->
        <property name="hibernate.show_sql">true</property>
        <!-- 格式化SQL语句 -->
        <property name="hibernate.format_sql">true</property>
        <!-- hibernate的方言 -->
        <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>

        <!-- 配置hibernate的映射文件所在的位置 -->
        <mapping resource="cn/itheima/domain/Customer.hbm.xml" />
    </session-factory>
</hibernate-configuration>

可将以上配置文件的内容分为3部分来看待：
    加载数据库相关信息
    <!-- 配置关于数据库连接的四个项：driverClass  url username password -->
    <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
    <property name="hibernate.connection.url">jdbc:mysql:///hibernateTest</property>
    <property name="hibernate.connection.username">root</property>
    <property name="hibernate.connection.password">yezi</property>
    Hibernate的相关配置
    <!-- 可以将向数据库发送的SQL语句显示出来 -->
    <property name="hibernate.show_sql">true</property>
    <!-- 格式化SQL语句 -->
    <property name="hibernate.format_sql">true</property>
    <!-- hibernate的方言 -->
    <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>

    加载映射配置文件

    <!-- 配置hibernate的映射文件所在的位置 -->
    <mapping resource="cn/itheima/domain/Customer.hbm.xml" />
 
提示：对于hibernate.cfg.xml配置文件中的内容可以参考project/etc/hibernate.properties文件中的配置。
查阅hibernate.properties文件，可发现有如下内容：

#hibernate.hbm2ddl.auto create-drop
#hibernate.hbm2ddl.auto create
#hibernate.hbm2ddl.auto update
#hibernate.hbm2ddl.auto validate
那么hibernate.hbm2ddl.auto这个属性到底是个什么东东昵？这儿我就来详解讲讲。先说结论：配置这个属性后，我们可以进行表的自动创建。该属性有4个取值：

    create-drop
    每次都会创建一个新的表，执行完成后就删除。一般在测试中使用。

    create
    每次都会创建一个新的表，但不删除。一般也是在测试中使用。下面我来举例说明该属性值，要知道我也是在《Hibernate快速入门》一文案例的基础上来讲解的。
    首先在hibernate.cfg.xml配置文件中加入如下内容：

    <!-- 自动创建表 -->
    <property name="hibernate.hbm2ddl.auto">create</property>
    然后执行单元测试类——HibernateTest1.java中的saveCustomerTest()方法：

    public class HibernateTest1 {

        // 保存一个Customer
        @Test
        public void saveCustomerTest() {
            // 创建一个Customer
            Customer c = new Customer();
            c.setName("叶子叶");
            c.setAddress("天门");

            // 使用Hibernate的API来完成将Customer信息保存到mysql数据库中的操作
            Configuration config = new Configuration().configure(); // Hibernate框架加载hibernate.cfg.xml文件
            SessionFactory sessionFactory = config.buildSessionFactory();
            Session session = sessionFactory.openSession(); // 相当于得到一个Connection
            // 开启事务
            Transaction transaction = session.beginTransaction();
            // 操作
            session.save(c);
            // 事务提交
            // session.getTransaction().commit();
            transaction.commit();
            session.close();
            sessionFactory.close();
        }

        ......
    }
    

    记住在测试以上方法之前，要确保数据库里面没有t_customer表。不出意外，我们就能在Eclipse的控制台上看到建表语句：

    create table hibernateTest.t_customer (
        id integer not null auto_increment,
        name varchar(20),
        address varchar(50),
        sex varchar(20),
        primary key (id)
    )


    和向t_customer表中插入的SQL语句：

    insert 
    into
        hibernateTest.t_customer
        (name, address, sex) 
    values
        (?, ?, ?)

    update
    如果数据库中有表，不创建，没有表则创建；如果映射不匹配，会自动更新表结构。
    首先在hibernate.cfg.xml配置文件中加入如下内容：

    <!-- 自动创建表 -->
    <property name="hibernate.hbm2ddl.auto">update</property>


    然后在实体类——Customer.java中增加一个属性private String sex;：

    public class Customer {

        private int id;
        private String name;
        private String address;
        private String sex;

        public Customer() {
            super();
        }

        public Customer(String name, String address) {
            super();
            this.name = name;
            this.address = address;
        }

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        public String getSex() {
            return sex;
        }
        public void setSex(String sex) {
            this.sex = sex;
        }
        @Override
        public String toString() {
            return "Customer [id=" + id + ", name=" + name + ", address=" + address + "]";
        }

    }
     

    接着修改实体类的映射配置文件(Customer.hbm.xml)的内容为：

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE hibernate-mapping PUBLIC 
        "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
    <hibernate-mapping package="cn.itheima.domain">
        <!-- 
            name：即实体类的全名
            table：映射到数据库里面的那个表的名称
            catalog：数据库的名称
         -->
        <class name="Customer" table="t_customer" catalog="hibernateTest">
            <!-- class下必须要有一个id的子元素 -->
            <!-- id是用于描述主键的 -->
            <id name="id" column="id">
                <!-- 主键生成策略 -->
                <generator class="native"></generator>
            </id>
            <!-- 
                使用property来描述属性与字段的对应关系
                如果length忽略不写，且你的表是自动创建这种方案，那么length的默认长度是255
            -->
            <property name="name" column="name" length="20"></property>
            <property name="address" column="address" length="50"></property>
            <property name="sex" column="sex" length="20"></property>
        </class>
    </hibernate-mapping>
       

    最后测试单元测试类——HibernateTest1.java中的saveCustomerTest()方法：
    public class HibernateTest1 {
        // 保存一个Customer
        @Test
        public void saveCustomerTest() {
            // 创建一个Customer
            Customer c = new Customer();
            c.setName("叶子");
            c.setAddress("天门");
            c.setSex("男");

            // 使用Hibernate的API来完成将Customer信息保存到mysql数据库中的操作
            Configuration config = new Configuration().configure(); // Hibernate框架加载hibernate.cfg.xml文件
            SessionFactory sessionFactory = config.buildSessionFactory();
            Session session = sessionFactory.openSession(); // 相当于得到一个Connection
            // 开启事务
            Transaction transaction = session.beginTransaction();

            // 操作
            session.save(c);

            // 事务提交
            // session.getTransaction().commit();
            transaction.commit();
            session.close();
            sessionFactory.close();
        }

        ......
    }
    查看t_customer表可发现：
    这里写图片描述
    这已说明了如果映射不匹配，会自动更新表结构。但是注意：只能添加，不能说我这个表里面有3个字段，我映射2个了，然后它就帮我删了，这是不行的！

    validate
    只会使用存在的表，并且会对映射关系进行校验。
    对于validate的测试，可参考update。只须在hibernate.cfg.xml配置文件中加入如下内容：

    <!-- 自动创建表 -->
    <property name="hibernate.hbm2ddl.auto">validate</property>

    这时运行单元测试类——HibernateTest1.java中的saveCustomerTest()方法，会发现如下异常：

    org.hibernate.tool.schema.spi.SchemaManagementException: Schema-validation: missing column [sex] in table [t_customer]
        at org.hibernate.tool.schema.internal.SchemaValidatorImpl.validateTable(SchemaValidatorImpl.java:85)
        at org.hibernate.tool.schema.internal.SchemaValidatorImpl.doValidation(SchemaValidatorImpl.java:50)
        at org.hibernate.tool.hbm2ddl.SchemaValidator.validate(SchemaValidator.java:91)
        at org.hibernate.internal.SessionFactoryImpl.<init>(SessionFactoryImpl.java:473)
        at org.hibernate.boot.internal.SessionFactoryBuilderImpl.build(SessionFactoryBuilderImpl.java:444)
        at org.hibernate.cfg.Configuration.buildSessionFactory(Configuration.java:708)
        at org.hibernate.cfg.Configuration.buildSessionFactory(Configuration.java:724)
        at cn.itheima.test.HibernateTest1.saveCustomerTest(HibernateTest1.java:27)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
        at java.lang.reflect.Method.invoke(Unknown Source)
        at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
        at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
        at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
        at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
        at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
        at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
        at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
        at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
        at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
        at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
        at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
        at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
        at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
        at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:459)
        at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:675)
        at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:382)
        at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:192)
       

    也即说明了如果表结构与映射文件不匹配，会报异常。

映射配置文件

映射配置文件的名称是类名.hbm.xml，它一般放置在实体类所在的包下。这个配置文件的主要作用是建立表与类之间的映射关系。下面我来粗略地介绍一下该映射配置文件，当然你可以在以后的Hibernate学习中逐渐地补全一些细枝末节。

    统一声明包名，这样在<class>中就不需要写类的全名。

    <hibernate-mapping package="cn.itheima.domain">
        <class name="Customer" table="t_customer" catalog="hibernateTest">
            ....
        </class>
    </hibernate-mapping>
       
    关于<class>标签配置的详细介绍：
        name属性：类的全名称。
        table属性：映射到数据库里面的那个表的名称，可以省略，这时表的名称就与类名一致。
        catalog属性：数据库名称，可以省略，如果省略，则参考核心配置文件中url路径中的库名称。

    关于<id>标签配置的详细介绍：

    <hibernate-mapping package="cn.itheima.domain">
        <class name="Customer" table="t_customer" catalog="hibernateTest">
            <!-- class下必须要有一个id的子元素 -->
            <!-- id是用于描述主键的 -->
            <id name="id" column="id" type="int"> <!-- java数据类型 -->
                <!-- 主键生成策略 -->
                <generator class="native"></generator>
            </id>
            ......
        </class>
    </hibernate-mapping>
    首先该标签必须存在。<id>是用于建立类中的属性与表中的主键映射。
        name：类中的属性名称
        column：表中的主键名称，column也可以省略，这时列名就与类中属性名称一致
        length：字段长度，如果length忽略不写，且你的表是自动创建这种方案，那么length的默认长度是255
        type属性：指定类型
        <generator>它主要是描述主键生成策略，这里就不做篇幅来介绍了，请看后面的文章。
    关于<property>标签
    它是描述类中属性与表中非主键字段的映射关系。

关于Hibernate映射配置文件中的类型问题

对于type属性它的取值可以有三种：

    Java中的数据类型
    Hibernate中的数据类型
    SQL的数据类型
可参考下表：
那么我实体类(Customer.java)的映射配置文件可以写为：

<!--<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-mapping PUBLIC 
    "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
    "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
<hibernate-mapping package="cn.itheima.domain">
    <!-- 
        name：即实体类的全名
        table：映射到数据库里面的那个表的名称
        catalog：数据库的名称
     -->
   <!-- <class name="Customer" table="t_customer" catalog="hibernateTest">-->
        <!-- class下必须要有一个id的子元素 -->
        <!-- id是用于描述主键的 -->
        <!--<id name="id" column="id" type="int"> <!-- java数据类型 -->
            <!-- 主键生成策略 -->
          <!--  <generator class="native"></generator>
        </id>-->
        <!-- 
            使用property来描述属性与字段的对应关系
            如果length忽略不写，且你的表是自动创建这种方案，那么length的默认长度是255
        -->
       <!-- <property name="name" column="name" length="20" type="string"></property> <!-- Hibernate数据类型 -->
       <!-- <property name="address">
           <!-- <column name="address" length="50" sql-type="varchar(50)"></column> <!-- SQL的数据类型 -->
       <!-- </property>
        <property name="sex" column="sex" length="20"></property>
    </class>
</hibernate-mapping>-->
