package org.telekinesis.simpleinjector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.telekinesis.simpleinjector.annotation.InjectConstructor;
import org.telekinesis.simpleinjector.parameter.ParameterCreator;

public class DependencyInjector {
	private static DependencyInjector instance = new DependencyInjector();
	private DependencyInjector(){}
	
	public static DependencyInjector getInstance(){
		return instance;
	}

	public <T> T inject(Map<String, String> properties, Class<T> type){
		Constructor<?> constructor = findInjectableConstructor(type);
		if(constructor != null){
			return injectByConstructor(properties, type, constructor);
		}else{
			try{
				T object = (T)type.newInstance();
				return injectByMethods(properties, object);
			}catch(Exception e){
				e.printStackTrace();
				throw new InjectionException("Unable to inject type: " + type, e);
			}
		}
	}
	
	private Constructor<?> findInjectableConstructor(Class<?> type){
		Constructor<?>[] constructors = type.getConstructors();
		for (Constructor<?> constructor : constructors) {
			if(constructor.isAnnotationPresent(InjectConstructor.class))
				return constructor;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private <T> T injectByConstructor(Map<String, String> properties, Class<T> type, Constructor<?> constructor){
		try {
			List<ParameterCreator<?>> injects = extractParameterCreators(constructor.getParameterAnnotations(), properties, constructor.toString());
			Object[] parameters = createInjectionParameters(properties, injects);
			T object = (T) constructor.newInstance(parameters);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InjectionException("Unable to inject by Constructor " + constructor, e);
		}
	}
	
	public <T> T injectByMethods(Map<String, String> properties, T object){
		Class<?> type = object.getClass();
		try {
			List<MethodInjectionContext> methodsForInjection = findInjectionMethods(type, properties);
			for (MethodInjectionContext context : methodsForInjection) {
				Object[] parameters = createInjectionParameters(properties, context.getParameterCreators());
				context.getMethod().invoke(object, parameters);
			}
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InjectionException("Unable to inject by methods in class: " + type, e);
		}
	}
	
	private List<MethodInjectionContext> findInjectionMethods(Class<?> type, Map<String, String> properties){
		List<MethodInjectionContext> setters = new ArrayList<MethodInjectionContext>();
		for (Method method : type.getMethods()) {
			Annotation[][] annotations = method.getParameterAnnotations();
			List<ParameterCreator<?>> parameterCreators = extractParameterCreators(annotations, properties, method.toString());
			if(allParametersAnnotated(parameterCreators)){
				setters.add(new MethodInjectionContext(method, parameterCreators));
			}
		}
		return setters;
	}
	
	private List<ParameterCreator<?>> extractParameterCreators(Annotation[][] annotations, Map<String, String> properties, String methodName){
		List<ParameterCreator<?>> creators = new ArrayList<ParameterCreator<?>>();
		for (Annotation[] annotationsOfOneParameter : annotations) {
			ParameterCreator<?> creator = ParameterCreatorExtractor.extract(annotationsOfOneParameter, properties);
			if(creator == null)
				break;
			creators.add(creator);
		}
		if((false == creators.isEmpty()) && (creators.size() != annotations.length))
			throw new InjectionException("Some of the arguments of method: " + methodName + " are not annotated by inject annotations");
		return creators;
	}
	
	private boolean allParametersAnnotated(List<ParameterCreator<?>> parameterCreators){
		return false == parameterCreators.isEmpty();
	}
	
	private Object[] createInjectionParameters(Map<String, String> properties, List<ParameterCreator<?>> parameterCreators){
		List<Object> objects = new ArrayList<Object>();
		for (ParameterCreator<?> creator : parameterCreators) {
			objects.add(creator.createParameter(properties));
		}
		return objects.toArray();
	}
	
}
