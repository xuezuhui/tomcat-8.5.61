package zuhui.xue;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * @Desc: 启动tomcat插件
 * @Author: admin
 * @Date: 2021/2/7 13:58
 */
public class myTomcat {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        //1.设置容器上下文
        Context context = tomcat.addContext("/qq", null);

        //2.设置servlet并放入容器
        HttpServlet httpServlet = new HttpServlet(){
            @Override
            public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
                res.getWriter().write("this is my start tomcat");
                res.flushBuffer();
            }
        };

        tomcat.addServlet(context,"/servlet",httpServlet);
        context.addServletMappingDecoded("/servlet","/servlet");

        //3.todo 配置一些参数
        tomcat.setPort(9090);
        //4.启动tomcat
        tomcat.init();
        tomcat.start();
        tomcat.getServer().await();
    }
}
