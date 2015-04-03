package freemarker_demo.wrapper;

import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.Version;
import freemarker_demo.adapter.Tupple;
import freemarker_demo.adapter.TuppleAdapter;

public class MyAppObjectWrapper extends DefaultObjectWrapper {

	public MyAppObjectWrapper(Version incompatibleImprovements) {
		super(incompatibleImprovements);
	}

	@Override
	protected TemplateModel handleUnknownType(final Object obj)
			throws TemplateModelException {
		if (obj instanceof Tupple) {
			return new TuppleAdapter((Tupple<?, ?>) obj, this);
		}

		return super.handleUnknownType(obj);
	}
}
