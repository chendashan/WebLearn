## 核心概念

**IOC ( Inversion of Control ) 控制反转**  
使用对象时，由主动new产生对象转换为由外部提供对象，此过程中对象创建控制权由程序转移到外部，此思想称为控制反转。

**Spring技术对IoC思想进行了实现**

- Spring提供了一个容器，成为IoC容器，用来充当IoC思想中的“外部”
- IoC容器负责对象的创建、初始化等一系列工作，被创建或被管理的对象在IoC容器中统称为Bean

**DI ( Dependency Injection ) 依赖注入**  
在容器中建立bean与bean之间的依赖关系的整个过程，称为依赖注入

## Maven

**坐标：** Maven中的坐标用于描述仓库中资源的位置。  
例如 `https://repo1.maven.org/maven2/com/google/code/gson/gson/2.9.0/gson-2.9.0.jar`