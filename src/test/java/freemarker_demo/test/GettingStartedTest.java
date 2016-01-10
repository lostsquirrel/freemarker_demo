package freemarker_demo.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateModelException;
import freemarker_demo.test.method.IndexOfMethod;
import freemarker_demo.test.tags.RepeatDirective;
import freemarker_demo.test.tags.UpperDirective;

public class GettingStartedTest {

	private Configuration cfg;
	
	public String template;
	
	private Map<String, Object> root = new HashMap<String, Object>();
	
	private static final Log log = LogFactory.getLog(GettingStartedTest.class);
	
	@Test
	public void testSharedVariable() throws Exception {
		this.template = "test_shared_variable";
	}
	
	@Test
	public void testTags2() throws Exception {
		this.template = "test_tags_2";
	}
	
	@Test
	public void testTags() throws Exception {
		root.put("upper", new UpperDirective());
		root.put("repeat", new RepeatDirective());
		this.template = "test_tags_1";
	}
	
	@Test
	public void testMethod() {
		root.put("indexOf", new IndexOfMethod()); 
		log.debug(template);
		this.template = "test_method";
		log.debug(template);
		log.debug(root);
		
	}
	
	@Test
	public void TestModelData(){
		// Create the root hash
		// Put string ``user'' into the root
		root.put("user", "Big Joe");
		// Create the hash for ``latestProduct''
		Map<String, String> latest = new HashMap<String, String>();
		// and put it into the root
		root.put("latestProduct", latest);
		// put ``url'' and ``name'' into latest
		latest.put("url", "products/greenmouse.html");
		latest.put("name", "green mouse"); 
		
		this.template = "test";
	}
	
	@After
	public void process() throws Exception {
		log.debug(template);
		Template temp = cfg.getTemplate(template + ".ftl");
		Writer out = new OutputStreamWriter(System.out);
		temp.process(root, out);
	}
	@Before
	public void initConfig() throws TemplateModelException {
		// Create your Configuration instance, and specify if up to what FreeMarker
		// version (here 2.3.22) do you want to apply the fixes that are not 100%
		// backward-compatible. See the Configuration JavaDoc for details.
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

		// Specify the source where the template files come from. Here I set a
		// plain directory for it, but non-file-system sources are possible too:
		try {
			String classPath = GettingStartedTest.class.getResource("/templates").getPath();
//			String templatePath = "/where/you/store/templates";
			cfg.setDirectoryForTemplateLoading(new File(classPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Set the preferred charset template files are stored in. UTF-8 is
		// a good choice in most applications:
		cfg.setDefaultEncoding("UTF-8");

		// Sets how errors will appear.
		// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER); 
		
		//Shared variables
		cfg.setSharedVariable("company", "Foo Inc.");
		
		this.cfg = cfg;
	}
}
