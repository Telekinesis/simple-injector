package org.telekinesis.simpleinjector.parameter;

import org.telekinesis.simpleinjector.annotation.IntInject;

public class IntParameterCreator extends ModedParameterCreator<IntInject> {

	public IntParameterCreator(IntInject annotation) {
		super(annotation);
	}

	@Override
	protected String getID(IntInject annotation) {
		return annotation.id();
	}

	@Override
	protected Object createParameterFromString(String valueString) {
		return Integer.parseInt(valueString);
	}

	@Override
	protected Object getDefaultValue(IntInject annotation) {
		return annotation.defaultValue();
	}

}
