package ua.nure.gnuchykh.web.custom.tag;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class InfoTag extends TagSupport {

    private static final Logger LOG = Logger.getLogger(InfoTag.class);

    @Override
    public int doStartTag() throws JspException {
        GregorianCalendar gc = new GregorianCalendar();
        String info = "<hr /> <p align=\"right\">&copy;Autobase (Gny4ix, summaryTask, EPAM-KhPI Java Training), 2017</p>" ;

        try {
            JspWriter out = pageContext.getOut();
            out.write(info);
        } catch (IOException e) {
            LOG.info(e.getMessage());
            throw new JspException(e.getMessage());

        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}