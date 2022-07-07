## 使用

1. 日期和字符串之间的格式化  
   `SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");`
2. thymeleaf中使用#dates这个公共的内置对象
   `${#dates.format(topic.topicDate ,'yyyy-MM-dd HH:mm:ss')}`

3. 系统启动时，我们访问的页面是： http://localhost:8080/pro23/page.do?operate=page&page=login  
   为什么不是： http://localhost:8080/pro23/login.html  ?  
   答： 如果是后者，那么属于直接访问静态页面。那么页面上的thymeleaf表达式（标签）浏览器是不能识别的
   我们访问前者的目的其实就是要执行 ViewBaseServlet中的processTemplete()

## 方位URL的流程

http://localhost:8080/pro23/page.do?operate=page&page=login 访问这个URL，执行的过程是什么样的？  
答:

| http://  | localhost   | :8080 | /pro23       | /page.do                 | ?operate=page&page=login |
|----------|-------------|-------|--------------|--------------------------|--------------------------|
| protocol | ServerIP    | port  | context root | request.getServletPath() | query string             |

1) DispatcherServlet -> urlPattern :  *.do 拦截/page.do
2) request.getServletPath() ->  /page.do
3) 解析处理字符串，将/page.do -> page
4) 拿到page这个字符串，然后去IOC容器（BeanFactory）中寻找id=page的那个bean对象 -> PageController.java
5) 获取operate的值 -> page 因此得知，应该执行 PageController中的page()方法
6) PageController中的page方法定义如下：
   public String page(String page){
   return page ;
   }
7) 在queryString:   ?operate=page&page=login 中 获取请求参数，参数名是page，参数值是login
   因此page方法的参数page值会被赋上"login"
   然后return "login" , return 给 谁？？
8) 因为PageController的page方法是DispatcherServlet通过反射调用的
   method.invoke(....) ;
   因此，字符串"login"返回给DispatcherServlet
9) DispatcherServlet接收到返回值，然后处理视图
   目前处理视图的方式有两种： 1.带前缀redirect:    2.不带前缀
   当前，返回"login"，不带前缀
   那么执行 super.processTemplete("login",request,response);
10) 此时ViewBaseServlet中的processTemplete方法会执行，效果是：
    在"login"这个字符串前面拼接 "/"  (其实就是配置文件中view-prefixe配置的值)
    在"login"这个字符串后面拼接 ".html" (其实就是配置文件中view-suffix配置的值)
    最后进行服务器转发

## javaWeb开发流程

目前我们进行javaWeb项目开发的“套路”是这样的：

1. 拷贝 myssm包
2. 新建配置文件applicationContext.xml或者可以不叫这个名字，在web.xml中指定文件名
3. 在web.xml文件中配置：  
   配置前缀和后缀，这样thymeleaf引擎就可以根据我们返回的字符串进行拼接，再跳转

```
       <context-param>
       <param-name>view-prefix</param-name>
       <param-value>/</param-value>
       </context-param>
       <context-param>
       <param-name>view-suffix</param-name>
       <param-value>.html</param-value>
       </context-param>
```

配置监听器要读取的参数，目的是加载IOC容器的配置文件（也就是applicationContext.xml）

```
       <context-param>
       <param-name>contextConfigLocation</param-name>
       <param-value>applicationContext.xml</param-value>
       </context-param>
```

4. 开发具体的业务模块：
    1) 一个具体的业务模块纵向上由几个部分组成：
        - html页面
        - POJO类
        - DAO接口和实现类
        - Service接口和实现类
        - Controller 控制器组件
    2) 如果html页面有thymeleaf表达式，一定不能够直接访问，必须要经过PageController
    3) 在applicationContext.xml中配置 DAO、Service、Controller，以及三者之间的依赖关系
    4) DAO实现类中 ， 继承BaseDAO，然后实现具体的接口, 需要注意，BaseDAO后面的泛型不能写错。  
       例如：
       public class UserDAOImpl extends BaseDAO<User> implements UserDAO{}
    5) Service是业务控制类，这一层我们只需要记住一点：
        - 业务逻辑我们都封装在service这一层，不要分散在Controller层。也不要出现在DAO层（我们需要保证DAO方法的单精度特性）
        - 当某一个业务功能需要使用其他模块的业务功能时，尽量的调用别人的service，而不是深入到其他模块的DAO细节
    6) Controller类的编写规则

```
1. 在applicationContext.xml中配置Controller
    <bean id="user" class="com.atguigu.qqzone.controllers.UserController>
    那么，用户在前端发请求时，对应的servletpath就是   /user.do   , 其中的“user”就是对应此处的bean的id值
2. 在Controller中设计的方法名需要和operate的值一致
    public String login(String loginId , String pwd , HttpSession session){
        return "index";
    }
    因此，我们的登录验证的表单如下：
    <form th:action="@{/user.do}" method="post">
        <inut type="hidden" name="operate" value="login"/>
    </form>
3. 在表单中，组件的name属性和Controller中方法的参数名一致
    <input type="text" name="loginId" /> 
    public String login(String loginId , String pwd , HttpSession session){}
4. 另外，需要注意的是： Controller中的方法中的参数不一定都是通过请求参数获取的
    if("request".equals...) else if("response".equals....) else if("session".equals....){
        //直接赋值
    }else{
        //此处才是从request的请求参数中获取
        request.getParameter("loginId") .....
    }
```

7) DispatcherServlet中步骤大致分为：
    1. 从application作用域获取IOC容器
    2. 解析servletPath ， 在IOC容器中寻找对应的Controller组件
    3. 准备operate指定的方法所要求的参数
    4. 调用operate指定的方法
    5. 接收到执行operate指定的方法的返回值，对返回值进行处理 - 视图处理
8) 为什么DispatcherServlet能够从application作用域获取到IOC容器？  
   ContextLoaderListener在容器启动时会执行初始化任务，而它的操作就是：
    1. 解析IOC的配置文件，创建一个一个的组件，并完成组件之间依赖关系的注入
    2. 将IOC容器保存到application作用域
