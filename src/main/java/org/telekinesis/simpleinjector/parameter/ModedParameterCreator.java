package org.telekinesis.simpleinjector.parameter;

import java.lang.annotation.Annotation;
import java.util.Map;


public abstract class ModedParameterCreator<T extends Annotation> extends ParameterCreator<T> {

	public ModedParameterCreator(T annotation) {
		super(annotation);
	}

	@Override
	protected Object create(T annotation, Map<String, String> properties) {
		String id = getID(annotation);
		String valueString = properties.get(id);
		if(valueString == null){
			return getDefaultValue(annotation);
		}else{
			return createParameterFromString(valueString);
		}
	}
	
	protected abstract String getID(T annotation);
	protected abstract Object createParameterFromString(String valueString);
	protected abstract Object getDefaultValue(T annotation);

}
