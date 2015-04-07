package freemarker_demo.directive;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.core.Environment;
import freemarker.core.Environment.Namespace;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;

public class TemplateBlockDirective implements TemplateDirectiveModel  {

	private static final Log log = LogFactory.getLog(TemplateBlockDirective.class);
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		Writer writer = (Writer) env.getCustomAttribute("writer");
		if (writer == null) {
			writer = new StringWriter();
			env.setCustomAttribute("writer", writer);
		}
			
		Iterator paramIter = params.entrySet().iterator();
		String type = null;
		String name = null;
		while (paramIter.hasNext()) {
			Map.Entry ent = (Map.Entry) paramIter.next();

			String paramName = (String) ent.getKey();
			TemplateModel paramValue = (TemplateModel) ent.getValue();
			log.debug(String.format("name=%s,value=%s", paramName, paramValue));
			if ("type".equalsIgnoreCase(paramName)) {
				type = paramValue.toString();
			}
			if ("name".equalsIgnoreCase(paramName)) {
				name = paramValue.toString();
			}

			
		}
		log.debug("type="+type);
		log.debug("name="+name);
		if ("block".equalsIgnoreCase(type)) {
			body = (TemplateDirectiveBody) env.getCustomAttribute(name);
			if (body != null) {
				body.render(env.getOut());
			}
			
		} if ("override".equalsIgnoreCase(type)) {
			env.setCustomAttribute(name, body);
		}
		
	}
	
}
