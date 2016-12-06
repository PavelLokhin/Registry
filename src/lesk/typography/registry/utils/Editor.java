package lesk.typography.registry.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Editor {
	
	
	public void edit (File folder) throws IOException {
		
		String str = null;
		 
		 File[] folderEntries = folder.listFiles();
		 for (File entry : folderEntries)
		 {
			 if (entry.isDirectory())
			 {
				 edit(entry);
				 continue;
			 }
			 str = entry.getName();
			 if (str.endsWith(".xlsx")){
				 InputStream file = new FileInputStream(entry);
				 
				 XSSFWorkbook wb = new XSSFWorkbook(file);
				 Sheet sheet = wb.getSheetAt(0);
				 sheet.setFitToPage(true);
				 
				 FileOutputStream fileOut = new FileOutputStream(entry);
				 wb.write(fileOut);
				 fileOut.close();
				 wb.close();
				 
			 }else{continue;}
			 
		 }
		 
		
	}
	
}
