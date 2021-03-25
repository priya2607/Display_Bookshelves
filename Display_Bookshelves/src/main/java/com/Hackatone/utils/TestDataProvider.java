package com.Hackatone.utils;
import org.testng.annotations.DataProvider;

public class TestDataProvider 
{
	@DataProvider(name="customized_purposes")
	public Object[][] getData()
	{
		//Rows - Number of times your test has to be repeated.
		//Columns - Number of parameters in test data.
	 
		// 1st row
		Object[][] data = new Object[11][6];
		 
		// 1st row
		data[0][0]="Germany";
		data[0][1]="22";
		data[0][2]="21";
		data[0][3]="Aditya Sinha";	
		data[0][4]="9874563210";
		data[0][5]="adi@gmail.com";
		return data;
	
	}
}
