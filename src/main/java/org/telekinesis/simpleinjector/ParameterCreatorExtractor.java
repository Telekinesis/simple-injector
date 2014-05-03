package org.telekinesis.simpleinjector;

import java.lang.annotation.Annotation;
import java.util.Map;

import org.telekinesis.simpleinjector.annotation.ClassInject;
import org.telekinesis.simpleinjector.annotation.DoubleInject;
import org.telekinesis.simpleinjector.annotation.IntInject;
import org.telekinesis.simpleinjector.annotation.LongInject;
import org.telekinesis.simpleinjector.annotation.StringInject;
import org.telekinesis.simpleinjector.parameter.ClassNameBasedCreator;
import org.telekinesis.simpleinjector.parameter.DoubleParameterCreator;
import org.telekinesis.simpleinjector.parameter.IntParameterCreator;
import org.telekinesis.simpleinjector.parameter.LongParameterCreator;
import org.telekinesis.simpleinjector.parameter.ParameterCreator;
import org.telekinesis.simpleinjector.parameter.StringParameterCreator;

public class ParameterCreatorExtractor {
	private ParameterCreatorExtractor(){}
	
	public static void checkParameterAnnotations(Annotation[][] annotationsOfParameters){

	}
	
	public static ParameterCreator<?> extract(Annotation[] annotationsOfOneParameter, Map<String, String> properties){
		for (Annotation annotation : annotationsOfOneParameter) {
			if(annotation.annotationType() == ClassInject.class){
				return new ClassNameBasedCreator((ClassInject)annotation);
			}else if(annotation.annotationType() == StringInject.class){
				return new StringParameterCreator((StringInject)annotation);
			}else if(annotation.annotationType() == IntInject.class){
				return new IntParameterCreator((IntInject)annotation);
			}else if(annotation.annotationType() == LongInject.class){
				return new LongParameterCreator((LongInject)annotation);
			}else if(annotation.annotationType() == DoubleInject.class){
				return new DoubleParameterCreator((DoubleInject)annotation);
			}
		}
		return null;
	}
}
