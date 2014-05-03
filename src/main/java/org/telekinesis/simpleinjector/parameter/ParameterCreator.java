package org.telekinesis.simpleinjector.parameter;

import java.lang.annotation.Annotation;
import java.util.Map;

public abstract class ParameterCreator<T extends Annotation> {
	private final T annotation;
	
	public ParameterCreator(T annotation) {
		this.annotation = annotation;
	}

	public Object createParameter(Map<String, String> properties){
		return create(annotation, properties);
	}
	
	protected abstract Object create(T annotation, Map<String, String> properties);
}
