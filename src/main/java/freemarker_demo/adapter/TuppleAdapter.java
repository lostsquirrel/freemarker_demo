package freemarker_demo.adapter;

import freemarker.template.AdapterTemplateModel;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.WrappingTemplateModel;

public class TuppleAdapter extends WrappingTemplateModel implements
		TemplateSequenceModel, AdapterTemplateModel {

	private final Tupple<?, ?> tupple;

	public TuppleAdapter(Tupple<?, ?> tupple, ObjectWrapper ow) {
		super(ow); // coming from WrappingTemplateModel
		this.tupple = tupple;
	}

	@Override
	// coming from TemplateSequenceModel
	public int size() throws TemplateModelException {
		return 2;
	}

	@Override
	// coming from TemplateSequenceModel
	public TemplateModel get(int index) throws TemplateModelException {
		switch (index) {
		case 0:
			return wrap(tupple.getE1());
		case 1:
			return wrap(tupple.getE2());
		default:
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	// coming from AdapterTemplateModel
	public Object getAdaptedObject(Class hint) {
		return tupple;
	}

}
