package org.telekinesis.simpleinjector.parameter;

import java.util.Map;

import org.telekinesis.simpleinjector.DependencyInjector;
import org.telekinesis.simpleinjector.InjectionException;
import org.telekinesis.simpleinjector.annotation.ClassInject;

public class ClassNameBasedCreator extends ParameterCreator<ClassInject> {

	public ClassNameBasedCreator(ClassInject annotation) {
		super(annotation);
	}

	@Override
	public Object create(ClassInject inject, Map<String, String> properties) {
		String id = inject.value();
		String className = properties.get(id);
		try {
			Class<?> type = Class.forName(className);
			return DependencyInjector.getInstance().inject(properties, type);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new InjectionException("Unable to inject class id = " + id + ", className = " + className, e);
		}
		
	}

}
