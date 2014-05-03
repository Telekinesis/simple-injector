package org.telekinesis.simpleinjector.parameter;

import org.telekinesis.simpleinjector.annotation.DoubleInject;

public class DoubleParameterCreator extends ModedParameterCreator<DoubleInject> {

	public DoubleParameterCreator(DoubleInject annotation) {
		super(annotation);
	}

	@Override
	protected String getID(DoubleInject annotation) {
		return annotation.id();
	}

	@Override
	protected Object createParameterFromString(String valueString) {
		return Double.parseDouble(valueString);
	}

	@Override
	protected Object getDefaultValue(DoubleInject annotation) {
		return annotation.defaultValue();
	}

}
