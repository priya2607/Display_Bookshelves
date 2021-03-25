package com.Hackathon.output;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
public class ShowOutputInExcel 
{
		public void showStudyChairDetailsInExcelFile(List<WebElement> name, List<WebElement> color, List<WebElement> price) throws Exception
		{
			
			XSSFWorkbook workbook = new XSSFWorkbook(); 
		    // Create a blank sheet 
		    XSSFSheet sheet = workbook.createSheet("Bookshelves"); 
		    int size = name.size();
		    // This data needs to be written (Object[]) 
		   
		    TreeMap<String, Object[]> data = new TreeMap<String, Object[]>(); 
		    data.put("1", new Object[]{ "Name",   "Color" , "Price (Rs)"}); 
		    for(int i=0; i<size; i++)
		    	data.put(Integer.toString(i+2), new Object[]{name.get(i).getText(), color.get(i).getText(), price.get(i).getText()}); 

		    // Iterate over data and write to sheet 
		    Set<String> keyset = data.keySet(); 
		    int rownum = 0; 
		    for (String key : keyset)
		    { 
		        // this creates a new row in the sheet 
		        XSSFRow row = sheet.createRow(rownum++); 
		        Object[] objArr = data.get(key); 
		        int cellnum = 0; 
		        for (Object obj : objArr) 
		        { 
		            // this line creates a cell in the next column of that row 
		            XSSFCell cell = row.createCell(cellnum++); 
		            if (obj instanceof String) 
		                cell.setCellValue((String)obj); 
		            else if (obj instanceof Integer) 
		                cell.setCellValue((Integer)obj); 
		        } 
		    }   
		    try 
		    { 
		        // this Writes the workbook
		        FileOutputStream out = new FileOutputStream(new File("StudyChairsDetails.xlsx")); 
		        workbook.write(out); 
		        out.close(); 
		        System.out.println("StudyChairsDetails.xlsx written successfully on disk."); 
		    } 
		    catch (Exception e) { 
		        e.printStackTrace(); 
		    } 
		   
		}
		public void showBookshelvesInExcelFile(List<WebElement> name, List<WebElement> detail, List<WebElement> price) throws Exception
		{
			XSSFWorkbook workbook = new XSSFWorkbook(); 
		    // Create a blank sheet 
		    XSSFSheet sheet = workbook.createSheet("Bookshelves"); 
		    int size = name.size();
		    // This data needs to be written (Object[]) 
		   
		    TreeMap<String, Object[]> data = new TreeMap<String, Object[]>(); 
		    data.put("1", new Object[]{ "Name",    "Specification" ,  "Price (Rs)"}); 
		    for(int i=0; i<size; i++)
		    	data.put(Integer.toString(i+2), new Object[]{name.get(i).getText(), detail.get(i).getText(), price.get(i).getText()}); 

		    // Iterate over data and write to sheet 
		    Set<String> keyset = data.keySet(); 
		    int rownum = 0; 
		    for (String key : keyset)
		    { 
		        // this creates a new row in the sheet 
		        XSSFRow row = sheet.createRow(rownum++); 
		        Object[] objArr = data.get(key); 
		        int cellnum = 0; 
		        for (Object obj : objArr) 
		        { 
		            // this line creates a cell in the next column of that row 
		            XSSFCell cell = row.createCell(cellnum++); 
		            if (obj instanceof String) 
		                cell.setCellValue((String)obj); 
		            else if (obj instanceof Integer) 
		                cell.setCellValue((Integer)obj); 
		        } 
		    }   
		    try 
		    { 
		        // this Writes the workbook
		        FileOutputStream out = new FileOutputStream(new File("BookshelvesDetails.xlsx")); 
		        workbook.write(out); 
		        out.close(); 
		        System.out.println("BookshelvesDetails.xlsx written successfully on disk."); 
		    } 
		    catch (Exception e) { 
		        e.printStackTrace(); 
		    } 
		 
		}
		
		public void showMenuInExcel(List<String> listitem) throws IOException
		{
			XSSFWorkbook workbook = new XSSFWorkbook(); 
		    // Create a blank sheet 
		    XSSFSheet sheet = workbook.createSheet("MenuList"); 
		    int size = listitem.size();
		    // This data needs to be written (Object[]) 
		   
		    TreeMap<String, Object[]> data = new TreeMap<String, Object[]>(); 
		    data.put("1", new Object[]{ "ListItems"}); 
		    for(int i=0; i<size; i++)
		    	data.put(Integer.toString(i+2), new Object[]{listitem.get(i)}); 

		    // Iterate over data and write to sheet 
		    Set<String> keyset = data.keySet(); 
		    int rownum = 0; 
		    for (String key : keyset)
		    { 
		        // this creates a new row in the sheet 
		        XSSFRow row = sheet.createRow(rownum++); 
		        Object[] objArr = data.get(key); 
		        int cellnum = 0; 
		        for (Object obj : objArr) 
		        { 
		            // this line creates a cell in the next column of that row 
		            XSSFCell cell = row.createCell(cellnum++); 
		            if (obj instanceof String) 
		                cell.setCellValue((String)obj); 
		            else if (obj instanceof Integer) 
		                cell.setCellValue((Integer)obj); 
		        } 
		    }   
		    try 
		    { 
		        // this Writes the workbook
		        FileOutputStream out = new FileOutputStream(new File("MenuList.xlsx")); 
		        workbook.write(out); 
		        out.close(); 
		        System.out.println("MenuList.xlsx written successfully on disk."); 
		    } 
		    catch (Exception e) { 
		        e.printStackTrace(); 
		    } 
		    
		}
	
}
