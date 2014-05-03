package org.telekinesis.simpleinjector.parameter;

import org.telekinesis.simpleinjector.annotation.StringInject;

public class StringParameterCreator extends ModedParameterCreator<StringInject> {

	public StringParameterCreator(StringInject annotation) {
		super(annotation);
	}

	@Override
	protected String getID(StringInject annotation) {
		return annotation.id();
	}

	@Override
	protected Object createParameterFromString(String valueString) {
		return valueString;
	}

	@Override
	protected Object getDefaultValue(StringInject annotation) {
		return annotation.defaultValue();
	}

}
