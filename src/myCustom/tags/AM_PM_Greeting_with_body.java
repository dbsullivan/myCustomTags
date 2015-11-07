package myCustom.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;

/**
 * Created by Dave on 11/7/2015.
 */
public class AM_PM_Greeting_with_body extends SimpleTagSupport {
    private String message;

    private String userName;

    public void setUsername(String username) {
        this.userName = username;
    }

    StringWriter sw = new StringWriter();

    public void doTag() throws JspException, IOException {

        // send AM or PM message depending on time of day
        //get(Calendar.AM_PM): returns 0 (Calendar.AM) or 1 (Calendar.PM).

        Calendar cal = Calendar.getInstance();
        int isCurrentTime_AM_or_PM = cal.get(Calendar.AM_PM);

        switch (isCurrentTime_AM_or_PM) {
            case 0: message = "Good Morning! " + userName ;
                break;
            case 1: message = "Good Afternoon! " + userName;
                break;
            default: message = "Greetings! " + userName;
                break;
        }

        //  In this case, the output resulting from the invocation is first captured into a StringWriter
        //  before being written to the JspWriter associated with the tag.

        getJspBody().invoke(sw);

        JspWriter out = getJspContext().getOut();
        out.println(message + " " + sw.toString());
    }
}
