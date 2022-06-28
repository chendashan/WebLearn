1. JDBC : sun公司发布的一个 java 程序和数据库之间的统信规范（接口）。
2. 各大数据库厂商去实现 JDBC 规范（实现类），这些实现类打成压缩包，就是所谓的jar包。

JDBC作用：用于Java程序和数据库通信。

```
Statement - (存在注入式漏洞，一般不使用)
    PreparedStatement - 预处理命令对象
        CallableStatement - 执行存储过程的
```