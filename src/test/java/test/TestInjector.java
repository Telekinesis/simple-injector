package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.telekinesis.simpleinjector.DependencyInjector;

public class TestInjector {
	private Map<String, String> properties = new HashMap<String, String>();

	@Before
	public void before() {
		properties.put("testClass.value", "25");
		properties.put("testComponent", TestComponent.class.getName());
		properties.put("testComponent.str", "abc");
	}

	@Test
	public void test() {
		TestComponent referenceComponent = new TestComponent(20, "abc");
		TestClass referenceClass = new TestClass(25, referenceComponent);
		TestClass injectedClass = DependencyInjector.getInstance().inject(
				properties, TestClass.class);
		Assert.assertEquals(referenceClass, injectedClass);
		System.out.println(injectedClass);
	}
}
