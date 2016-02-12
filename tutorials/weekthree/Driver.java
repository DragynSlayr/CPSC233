package weekthree;

public class Driver {

	public static void main(String[] args) {
		Person unknown;// Reference to an object
		unknown = new Person();
		Person me = new Person("Inderpreet", 19);

		System.out.println(unknown.getName());
		System.out.println(me.getName());
		System.out.println("Objects: " + Person.ID);
	}
}
