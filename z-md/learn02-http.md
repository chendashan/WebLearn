HTTP : Hyper Text Transfer Protocol 超文本传输协议。HTTP最大的作用就是确定了请求和响应的数据格式。

浏览器发送给服务器的数据：发送报文；服务器返回给浏览器的数据：响应报文。

## Http协议

- Http称之为超文本传输协议
- Http是无状态的
- Http请求响应包含两个部分：请求和响应

**请求**  
请求包含三个部分：1.请求行 ； 2.请求消息头 ； 3.请求主体

1. 请求行包含三个信息：请求的方式、请求的URL、请求的协议（一般都是HTTP1.1）
2. 请求消息头包含了很多客户端需要告诉服务器的信息，比如浏览器型号、版本...
3. 请求体，三种情况
   1) get方式，没有请求体，但是有一个queryString
   2) post方式，有请求体，form data
   3) json格式，有请求体，request payload

**响应**  
响应也包含三部分：1.响应行 ； 2.响应头 ； 3.响应头

1. 响应行包含三个信息：协议、响应状态码、响应状态
2. 响应头包含了服务器的信息；服务器发送给浏览器的信息（内容的媒体类型、编码……）
3. 响应体：响应的实际内容（比如html文件）

### 会话

HTTP是无状态的，服务器无法判断是同一个客户端发过来的，还是不同的客户端发送过来的。  
通过会话跟踪技术来解决无状态的问题。

会话跟踪技术：  
客户端第一次发请求给服务器，服务器获取session，获取不到则创建新的，然后响应给客户端。  
下次客户端给服务器发请求时，会吧sessionID带给服务器，那么服务器就能判断了。

常用的方法：

```
//获取当前的会话，没有则创建一个新的会话
request.getSession();

//效果同上
request.getSession(true);

//获取当前会话，没有则返回null，不会创建新的
request.getSession(false);

session.getId();

//判断当前session是否是新的
session.isNew();

//session的非激活事件间隔时长，默认1800秒（半小时）
sesion.getMaxInactiveInterval();

//强制性让会话立即失效
session.invalidate();
```

**session保存作用域**

```
   session.setAttribute("username", "Michael Jordan");
   
   Object object = session.getAttribute("username");
   
   session.removeAttribute("username");
```

### 服务器内部转发和客户端重定向

**服务器内部转发：** 一次请求响应的过程，对于客户端而言，内部经过了多少次转发，客户端是不知道的。

```
   request.getRequestDispatcher("demo06").forward(req, resp);
```

**客户端重定向：** 两次请求响应的过程。客户端肯定知道请求URL有变化

```
   resp.sendRedirect("demo06");
```

### 保存作用域

原始情况下，保存作用域有四种：

- page：页面级别，现在几乎不用
- request：一次请求响应范围，服务器内部转发可以客户端重定向不可以
- session：一次会话范围
- application：整个应用程序范围

**application保存作用域：**

```
        ServletContext application = req.getServletContext();
        application.setAttribute("username", "Michael Jordan");
        
        Object object = application.getAttribute("username");
```
