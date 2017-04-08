import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

@WebServlet("/helloworld/")
public class helloworld extends javax.servlet.http.HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                        throws IOException, FileNotFoundException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("<html><head><title>Raspberry Pi Java app </title></head><body>");
        out.print("<h1>Hello folks, receiving a GET request </h1>");
        out.println("<h3>Sending response...</h3>");
        out.println("<h3>End sending response.</h3>");
        out.println("</body></html>");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
                                        throws IOException {

       response.setContentType("text/html");
       PrintWriter out = response.getWriter();
       out.print("Hello World from POST method ");

    }
}
//sudo javac -classpath ~/Programs/tomcat/lib/servlet-api.jar  -d ../classes/ helloworld.java
