package freemarker_demo.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final Log log = LogFactory.getLog(HttpServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		pw.write("GET ");
		log.debug("hello");
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	@Override
	public void init() throws ServletException {
		super.init();
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		cfg.setDefaultEncoding("UTF-8");
		try {
			String path = super.getServletContext().getRealPath("/");
			String templates = String.format("%sWEB-INF/templates", path);
			File rootPaht = new File(templates);
			log.debug(rootPaht.getAbsoluteFile());
			
			cfg.setDirectoryForTemplateLoading(rootPaht);
			// Sets how errors will appear.
			// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
//			TemplateExceptionHandler.RETHROW_HANDLER
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);  
		} catch (IOException e) {
			e.printStackTrace();
		}
		ServletContext context = super.getServletContext();
		
		context.setAttribute("freemarkerCfg", cfg);
	}

	
}
