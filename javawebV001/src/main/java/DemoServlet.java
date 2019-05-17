import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "DemoServlet",urlPatterns = {"/demo"})
public class DemoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取ServletContext对象
        ServletContext context = getServletContext();
        //获取给定的文件在服务器上面的绝对路径
        String path = context.getRealPath("");
        System.out.println("path="+path);

        //创建属性对象
//        Properties properties = new Properties();
//        FileInputStream is = new FileInputStream(path);
//
//        properties.load(is);
//
//        //获取name属性的值
//        String name = properties.getProperty("name");
//        System.out.println(name);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
