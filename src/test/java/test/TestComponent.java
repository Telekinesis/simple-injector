package test;

import org.telekinesis.simpleinjector.annotation.LongInject;
import org.telekinesis.simpleinjector.annotation.StringInject;

public class TestComponent {
	private long value;
	private String str;
	
	public TestComponent(){}

	public TestComponent(long value, String str) {
		this.value = value;
		this.str = str;
	}

	public long getValue() {
		return value;
	}

	public void setValue(@LongInject(defaultValue = 20) long value) {
		this.value = value;
	}

	public String getStr() {
		return str;
	}

	public void setStr(
			@StringInject(id = "testComponent.str", defaultValue = "") String str) {
		this.str = str;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((str == null) ? 0 : str.hashCode());
		result = prime * result + (int) (value ^ (value >>> 32));
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
		TestComponent other = (TestComponent) obj;
		if (str == null) {
			if (other.str != null)
				return false;
		} else if (!str.equals(other.str))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TestComponent [value=" + value + ", str=" + str + "]";
	}

}
