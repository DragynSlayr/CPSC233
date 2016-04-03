package test;

public class Main {

	public static void main(String[] args) {
		Incrementer i = new Incrementer();
		Examiner e = new Examiner(i);

		i.start();
		e.start();
	}
}
