package lab5;

import java.io.File;
import java.io.IOException;

public class FileHandler {

	private View view;
	private File saveFile;

	public FileHandler(View view, String filename) {
		this.view = view;
		this.saveFile = new File(filename);

		if (!this.saveFile.exists()) {
			try {
				this.saveFile.createNewFile();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public void saveSimulation() {

	}

	public void loadSimulation() {

	}
}
