package weekthree;

public class Person {

	private String name;
	private int age;
	public static int ID = 0;

	public Person() {
		this("no name", 0);
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
		ID++;
	}

	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		if (age > 0) {
			this.age = age;
		}
	}
}
