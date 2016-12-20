package lesk.typography.registry.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Marker {
	
	ArrayList<String> areaList =  new ArrayList<String>(); //Районы
	ArrayList<String> streetList =  new ArrayList<String>(); //Улицы
	
	
	public void fileReader(String file){
		
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
				areaList.add(cell.getStringCellValue());
				}else{
				streetList.add(cell.getStringCellValue());
				}
			}
		}
		
		
	}
	
	//Класс для проставление названия района в реестре согласно улиц, указанных в реестре 
	public void fileMarker(File folder) throws IOException {
		
		String street = null;
		String str = null;
		String fileName = null;
				
		File[] folderEntries = folder.listFiles();
		for (File entry : folderEntries) //Перебор всех файлов в указанной папке
		 {
			 if (entry.isDirectory())
			 {
				 fileMarker(entry);
				 continue;
			 }
			 fileName = entry.getName();
			 if (fileName.endsWith(".xlsx")){
				 
				 InputStream file = null;
				 XSSFWorkbook wbook = null;
				 try{
				 file = new FileInputStream(entry);
				 wbook = new XSSFWorkbook(file);
				 } catch (NotOfficeXmlFileException e) {
						continue;
				 }
				 
				 Sheet sheet = wbook.getSheetAt(0);
				 
				 for (int b = 0; b<streetList.size(); b++){ //Цикл по массиву улиц
					 for (int i = 12; i<sheet.getLastRowNum(); i++){ //Цикл по реестру
					 	
						Row row = sheet.getRow(i);
					 	Cell cell = row.getCell(1);
					 	str = cell.getStringCellValue().toLowerCase().trim();
					 	street=streetList.get(b).toLowerCase().trim();
					 	if (str.endsWith(street)){  //Сравнение название улицы в реестре с таблицей
					 		row = sheet.createRow(3);
							cell = row.createCell(1);
							cell.setCellValue(areaList.get(b).toUpperCase());
							
							b=streetList.size();
							break;
							
					 	}
					 
				 	 }
					
				 }
				 
				FileOutputStream fileOut = new FileOutputStream(entry);
				wbook.write(fileOut);
				fileOut.close();
				wbook.close();
				 
			 }else{continue;}
			 
		 }
		
	}
	
	public Marker (){
		System.out.println("Done");
	}
	
}
