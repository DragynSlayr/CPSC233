package lab5;

import java.io.File;
import java.io.IOException;

public class FileHandler {

	private View view;
	private File saveFile;

	public FileHandler(View view, String filename) {
		this.view = view;

		saveFile = new File(filename);

		if (!saveFile.exists()) {
			try {
				saveFile.createNewFile();
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
