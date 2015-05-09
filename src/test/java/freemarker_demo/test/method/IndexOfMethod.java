package freemarker_demo.test.method;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker_demo.test.GettingStartedTest;

public class IndexOfMethod implements TemplateMethodModelEx {
	
	private static final Log log = LogFactory.getLog(GettingStartedTest.class);
	
	public TemplateModel exec(@SuppressWarnings("rawtypes") List args) throws TemplateModelException {
		if (args.size() != 2) {
			log.debug(args);
			throw new TemplateModelException("Wrong arguments");
		}
		String string = ((SimpleScalar) args.get(0)).toString();
//		log.debug(args.get(0).getClass().getName());
//		log.debug(args.get(0).toString());
		String string2 = args.get(1).toString();
//		log.debug(args.get(1).getClass().getName());
		
		return new SimpleNumber(string2.indexOf(string));
	}
}
