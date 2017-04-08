import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@WebServlet("/helloled/")
public class helloled extends javax.servlet.http.HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                        throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalOutput led0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);

        String on = request.getParameter("on");
        String off = request.getParameter("off");
        
        if(on != null) {

	      System.out.println("Led on");
	      led0.high();

            try{
                Thread.sleep(3000);
            }
            catch (Exception e)
            {}
            gpio.shutdown();
        }
        else
        if (off != null){
		  led0.low();
		}
        out.print("<html><head><title>Raspberry Pi Java app </title></head><body>");
        out.print("<h1>Led On via (GET)</h1>");
        out.print("  <form action='../helloled/' method='post'>");  
        out.print("      <input type='submit' name='on' value='ON'><br>");
        out.print("     <input type='submit' name='off' value='OFF'>");
        out.print("   </form>");
        out.print("</body></html>");


	    

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
                                        throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalOutput led0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);

        String on = request.getParameter("on");
        String off = request.getParameter("off");
        
        if(on != null) {

	      System.out.println("Led on");
	      led0.high();

            try{
                Thread.sleep(3000);
            }
            catch (Exception e)
            {}
            gpio.shutdown();
        }
        else
        if (off != null){
		  led0.low();
		}
        out.print("<html><head><title>Raspberry Pi Java app </title></head><body>");
        out.print("<h1>Led On via (POST)</h1>");
        out.print("  <form action='../helloled/' method='post'>");  
        out.print("      <input type='submit' name='on' value='ON'><br>");
        out.print("     <input type='submit' name='off' value='OFF'>");
        out.print("   </form>");
        out.print("</body></html>");


	    


    }
}
//sudo javac -cp ~/Programs/tomcat/lib/'*':/opt/pi4j/lib/'*'  -d ../classes/ helloled.java
