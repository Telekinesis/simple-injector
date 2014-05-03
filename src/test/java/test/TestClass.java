package test;

import org.telekinesis.simpleinjector.annotation.ClassInject;
import org.telekinesis.simpleinjector.annotation.InjectConstructor;
import org.telekinesis.simpleinjector.annotation.IntInject;

public class TestClass {
	private int value;
	private TestComponent component;
	
	@InjectConstructor
	public TestClass(
			@IntInject(id = "testClass.value", defaultValue = 10)int value, 
			@ClassInject("testComponent")TestComponent component) {
		this.value = value;
		this.component = component;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((component == null) ? 0 : component.hashCode());
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestClass other = (TestClass) obj;
		if (component == null) {
			if (other.component != null)
				return false;
		} else if (!component.equals(other.component))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TestClass [value=" + value + ", component=" + component + "]";
	}
	
}
