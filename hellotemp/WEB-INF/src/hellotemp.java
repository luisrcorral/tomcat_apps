import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

@WebServlet("/temp/")
public class hellotemp extends javax.servlet.http.HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                        throws IOException, FileNotFoundException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("<html><head><title>Raspberry Pi temp sensor </title></head><body>");
        out.print("<h1>Hello folks, receiving a GET request </h1>");
        out.println("<h3>Start Reading Temperature...</h3>");

        BufferedReader br = new BufferedReader(new FileReader("/sys/bus/w1/devices/28-0000075aafc6/w1_slave"));
        String tempfile = "";

		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			tempfile = sb.toString();

			br.close();
		}
		catch (IOException e){
			System.out.println("Exception reading temp file");
		}

		Pattern p = Pattern.compile("t=(.*)");
		Matcher m = p.matcher(tempfile);
		String message  = "";
		String tempstring = "";
		Float temperature = 0.0f;
		String temp4service = "";

		if(m.find()){
			tempstring = m.group(1);
			temperature = Float.parseFloat(tempstring);
			temperature = temperature/1000;
			message = "A DS18B20 temp sensor says that the temperature in my place is <strong> " + temperature + " </strong> deg C. <i>(Message automatically sent by my RaspberryPi</i>)";
		}
        out.println("<h2>" + message + "</h2>");
        out.println("<h3>End reading temperature.</h3>");
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
