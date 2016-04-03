package test;

public class Incrementer extends Thread {

	private int value;

	public Incrementer() {
		this.value = 0;
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public void run() {
		try {
			while (true) {
				this.value++;
				sleep(500);
			}
		} catch (InterruptedException ie) {
		}
	}
}
