package by.bsuir.webproj.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class AuthCheckingTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        String locale = (String)pageContext.getSession().getAttribute("locale");
        char[] charArrayLoc = locale.toCharArray();
        charArrayLoc[2] = '-';
        locale = String.valueOf(charArrayLoc);
        Locale loc = Locale.forLanguageTag(locale);
        ResourceBundle resource = ResourceBundle.getBundle("resources.content", loc);
        try {
            JspWriter out = pageContext.getOut();
            if (pageContext.getSession().getAttribute("login") == null) {

                out.write("<div class=\"authentication\">\n" +
                        "        <button class=\"auth-link-login custom-button\" >"+ resource.getString("Button.login")+"</button>\n" +
                        "        <button class=\"auth-link-register custom-button\"  >"+resource.getString("Button.register")+"</button>\n" +
                        "    </div>"); /** Put a login and registration buttons, if there is no logged user yet **/
            }
            else{
                out.write("<div class=\"authentication\">\n" +
                        "        <a class=\"auth-link\" href=\"controller?command=logout\">"+resource.getString("Button.logout")+"</a>\n" +
                        "        <a class=\"auth-link\" href=\"controller?command=profile\">"+resource.getString("Button.profile")+"</a>\n"+
                        "    </div>");
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}