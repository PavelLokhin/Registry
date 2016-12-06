package lesk.typography.registry.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Marker {
	
	ArrayList<String> areaList =  new ArrayList<String>();
	ArrayList<String> streetList =  new ArrayList<String>();
	
	
	public void fileReader(String file){
		
		
		//HashMap<String, String> comSviaz = new HashMap();
		
		InputStream in = null;
		XSSFWorkbook wb = null;
		
		//Чтение файла и запись данных в ArrayList
		try{
		in = new FileInputStream(file);
		wb = new XSSFWorkbook(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Sheet sheet = wb.getSheetAt(0) ;
		Iterator<Row> it = sheet.iterator();
		
		while (it.hasNext()) {
			Row row = it.next();
			Iterator<Cell> cells = row.iterator();
			while (cells.hasNext()){
				Cell cell = cells.next();
				if (cell.getColumnIndex() == 0){
				areaList.add(cell.getStringCellValue());// Наименование района
				//System.out.println(areaList);
				}else{
				streetList.add(cell.getStringCellValue());//Наименование улицы
				//System.out.println(streetList);
				}
			}
		}
		
		
	}
	
	//Класс для проставление названия района в реестре согласно улиц, указанных в реестре 
	public void fileMarker(File folder) throws IOException {
		
		//File folder = new File(path);
		//String area = null;
		String street = null;
		String str = null;
		
		File[] folderEntries = folder.listFiles();
		for (File entry : folderEntries) //Рекурсивный перебор всех файлов, указанных в папке
		 {
			 if (entry.isDirectory())
			 {
				 fileMarker(entry);
				 continue;
			 }
			 str = entry.getName();
			 if (str.endsWith(".xlsx")){
				 InputStream file = new FileInputStream(entry);
				 
				 XSSFWorkbook wb = new XSSFWorkbook(file);
				 Sheet sheet = wb.getSheetAt(0);
				 
				 for (int b =0; b<streetList.size(); b++){
					 for (int i = 13; i<sheet.getLastRowNum(); i++){
					 	
						Row row = sheet.getRow(i);
					 	Cell cell = row.getCell(1);
					 	str = cell.getStringCellValue().toLowerCase();
					 	street=streetList.get(b).toLowerCase();
					 	
					 	if (str.endsWith(street)){  //Сравнение название улицы в реестре с таблицей
					 		row = sheet.createRow(3);
							cell = row.createCell(0);
							cell.setCellValue(areaList.get(b));
							b=streetList.size();
							break;
							
					 	}
					 
				 	 }
				 }
		 
				 FileOutputStream fileOut = new FileOutputStream(entry);
				 wb.write(fileOut);
				 fileOut.close();
				 wb.close();
				 
			 }else{continue;}
			 
		 }
		
	}

	public Marker (){
		
		System.out.print("Hello world");
		
	}
	
	
}
