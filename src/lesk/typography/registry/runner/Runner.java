package lesk.typography.registry.runner;

import java.io.File;
import java.io.IOException;

import lesk.typography.registry.utils.Editor;
import lesk.typography.registry.utils.Marker;

public class Runner {

	public static void main(String[] args) throws IOException {
		
								
		Editor edit = new Editor();
		File file = new File(args[0]);
		edit.edit(file);
		
		Marker marker = new Marker();
		String rList = new String (args[1]);
		File rDir = new File(args[2]);
		marker.fileReader(rList);
		marker.fileMarker(rDir);
		
		
	}

}
