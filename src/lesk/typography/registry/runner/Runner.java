package lesk.typography.registry.runner;

import java.io.File;
import java.io.IOException;

import lesk.typography.registry.utils.Editor;
import lesk.typography.registry.utils.Marker;
import lesk.typography.registry.utils.Test;

public class Runner {

	public static void main(String[] args) throws IOException {

		
//		Editor edit = new Editor();
//		//File file = new File(args[0]);
//		File file = new File("D:/Reetrs");
//		edit.edit(file);
		
		Marker marker = new Marker();
		File f = new File("D:/Reestrs/МКД ГЭСК/готово");
		marker.fileReader("D:/Reestrs/МКД ГЭСК/Участки.xlsx");
		marker.fileMarker(f);
		
//		Test test = new Test();
//		File folder = new File("D:/Reestrs/МКД ГЭСК/готово/Ab_mkd_GESK_20_1479015833058/reestr_10.2016_20_2.xlsx");
//		test.methodTest(folder);
//		
		
		
		
	}

}
