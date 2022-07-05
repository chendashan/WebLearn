## XML

- HTML : 超文本标记语言
- XML : 可扩展的标记语言
- HTML是XML的一个子集

XML包含三个部分：

1. XML声明，而且声明这一行必须在XML文件的第一行
2. DTD 文件类型定义（可以没有）
3. XML正文

## thymeleaf

**thymeleaf:** 是用来帮我们做视图渲染的技术。

**使用**

1. 使用步骤： 添加jar ， 新建ViewBaseServlet(有两个方法） ， 配置两个<context-param> : view-prefix , view-suffix
2. 部分标签： `<th:if>` , `<th:unless>` , `<th:each>` , `<th:text>`

## Servlet

1. Servlet生命周期的初始化方法：init() , init(config)

```
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        this.init();
    }
```

因此，如果我们需要在初始化时执行一些自定义的操作，可以出些无参的init()方法  
可以通过`getConfig()` 获取ServletConfig 对象
可以通过 `config.getInitParameter()` 获取初始化参数

2. 通过ServletContext获取配置的上下文参数。

## 过滤器Filter

1. Filter也属于Servlet规范
2. 使用：新建类实现Filter接口，然后实现三个方法 init() 、doFilter() 、 destroy()
3. 过滤器链，依次执行，采取注解方式时按照全类名先后顺序 


