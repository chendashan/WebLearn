package com.example.myssm.myspringmvc;

import com.example.myssm.ioc.BeanFactory;
import com.example.myssm.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    public DispatcherServlet() {
    }

    public void init() throws ServletException {
        super.init();
        //beanFactory = new ClassPathXmlApplicationContext();
        //之前是在此处主动创建IOC容器，现在优化为从application作用域去获取
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        if (beanFactoryObj != null) {
            beanFactory = (BeanFactory) beanFactoryObj;
        } else {
            throw new RuntimeException("IOC容器获取失败！");
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        //request.setCharacterEncoding("UTF-8");

        //获取servicePath
        String servicePath = request.getServletPath();
        servicePath = servicePath.substring(1);
        int lastIndex = servicePath.lastIndexOf(".do");
        servicePath = servicePath.substring(0, lastIndex);

        Object controllerBeanObj = beanFactory.getBean(servicePath);

        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }


        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (operate.equals(method.getName())) {
                    //1. 统一获取请求参数

                    //获取当前方法的参数，返回参数数组
                    Parameter[] parameters = method.getParameters();
                    //parameterValues 用来承载参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        //如果参数名是request,response,session 那么就不是通过请求中获取参数的方式了
                        if ("request".equals(parameterName)) {
                            parameterValues[i] = request;
                        } else if ("response".equals(parameterName)) {
                            parameterValues[i] = response;
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i] = request.getSession();
                        } else {
                            //从请求中获取参数
                            String parameterValue = request.getParameter(parameterName);

                            String typeName = parameter.getType().getName();
                            if ("java.lang.Integer".equals(typeName) && parameterValue != null) {
                                parameterValues[i] = Integer.parseInt(parameterValue);
                            } else {
                                parameterValues[i] = parameterValue;
                            }

                        }
                    }

                    //2. controller组件中的方法调用
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);

                    //3. 视图处理
                    String strReturn = (String) returnObj;
                    if (strReturn.startsWith("redirect:")) {
                        String redirectStr = strReturn.substring("redirect:".length());
                        response.sendRedirect(redirectStr);
                    } else {
                        super.processTemplate(strReturn, request, response);
                    }
                }
            }

//            } else {
//                throw new RuntimeException("operate值非法！");
//            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new DispatcherServletException("DispatcherServlet 出错了");
        }
    }
}
