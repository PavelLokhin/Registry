package lesk.typography.registry.runner;

import java.io.File;
import java.io.IOException;

import lesk.typography.registry.utils.Editor;

public class Runner {

	public static void main(String[] args) throws IOException {

		Editor edit = new Editor();
		File file = new File("D:/Reestrs");
		edit.edit(file);
		
		
	}

}
