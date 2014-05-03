package org.telekinesis.simpleinjector;

import java.lang.reflect.Method;
import java.util.List;

import org.telekinesis.simpleinjector.parameter.ParameterCreator;

public class MethodInjectionContext {
	private final Method method;
	private final List<ParameterCreator<?>> parameterCreators;

	public MethodInjectionContext(Method method,
			List<ParameterCreator<?>> parameterCreators) {
		this.method = method;
		this.parameterCreators = parameterCreators;
	}

	public Method getMethod() {
		return method;
	}

	public List<ParameterCreator<?>> getParameterCreators() {
		return parameterCreators;
	}

	@Override
	public String toString() {
		return "MethodInjectionContext [method=" + method
				+ ", parameterCreators=" + parameterCreators + "]";
	}
	
}
