package org.telekinesis.simpleinjector.parameter;

import org.telekinesis.simpleinjector.annotation.LongInject;

public class LongParameterCreator extends ModedParameterCreator<LongInject> {

	public LongParameterCreator(LongInject annotation) {
		super(annotation);
	}

	@Override
	protected String getID(LongInject annotation) {
		return annotation.id();
	}

	@Override
	protected Object createParameterFromString(String valueString) {
		return Long.parseLong(valueString);
	}

	@Override
	protected Object getDefaultValue(LongInject annotation) {
		return annotation.defaultValue();
	}

}
