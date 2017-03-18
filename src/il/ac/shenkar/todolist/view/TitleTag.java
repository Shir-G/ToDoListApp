package il.ac.shenkar.todolist.view;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class TitleTag extends SimpleTagSupport{

	private String message;

   public void setMessage(String msg) {
      this.message = msg;
   }
	
	StringWriter sw = new StringWriter();
	
	public void doTag() throws JspException, IOException {
		
		if (message != null) {
          /* Use message from attribute */
          JspWriter out = getJspContext().getOut();
          out.println("<p style='font-size:450%'>" + message + "</p>");
       }
       else {
          /* use message from the body */
          getJspBody().invoke(sw);
          getJspContext().getOut().println("<p style='font-size:450%'>" + sw.toString() + "</p>");
       }
	}
}
