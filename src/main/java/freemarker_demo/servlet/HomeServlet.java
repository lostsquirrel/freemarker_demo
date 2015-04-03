package freemarker_demo.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker_demo.directive.UpperDirective;
import freemarker_demo.wrapper.MyAppObjectWrapper;

public class HomeServlet extends HttpServlet {

	private static final String FREEMARKER_CFG = "FREEMARKER_CFG";

	private static final long serialVersionUID = 1L;

	private final Log log = LogFactory.getLog(HttpServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		// Create the root hash
		Map<String, Object> root = new HashMap<String, Object>();
		// Put string ``user'' into the root
		root.put("user", "Big Joe");
		// Create the hash for ``latestProduct''
		Map<String, Object> latest = new HashMap<String, Object>();
		// and put it into the root
		root.put("latestProduct", latest);
		// put ``url'' and ``name'' into latest
		latest.put("url", "products/greenmouse.html");
		latest.put("name", "green mouse");  
		Configuration cfg = (Configuration) super.getServletContext().getAttribute(FREEMARKER_CFG);
		cfg.setObjectWrapper(new MyAppObjectWrapper(cfg.getIncompatibleImprovements()));  
		//		Template temp = cfg.getTemplate("test.ftl");  
		Template temp = cfg.getTemplate("upper.ftl");  
		root.put("upper", new UpperDirective());  
		try {
			temp.process(root, pw);
		} catch (TemplateException e) {
			pw.print("ERROR");
			log.debug("error", e);
		} finally {
			pw.close();
		}
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
		
		context.setAttribute(FREEMARKER_CFG, cfg);
	}

	
}
