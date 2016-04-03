package test;

public class Examiner extends Thread {

	Incrementer a;

	public Examiner(Incrementer a) {
		this.a = a;
	}

	@Override
	public void run() {
		try {
			while (true) {
				System.out.println(a.getValue());
				sleep(100);
			}
		} catch (InterruptedException ie) {
		}
	}
}
