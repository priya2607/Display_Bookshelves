package com.Hackatone.output;
import java.util.List;

import org.openqa.selenium.WebElement;

public class ShowOutputInConsole 
{
	public void printBookShelvesOutput(List<WebElement> name, List<WebElement> details, List<WebElement> price)
	{
		System.out.println("NAME ----------------- SPECIAFICATION -------------------------------------------- PRICE");
		
		System.out.println();
		for(int i=0; i<Math.min(price.size(), (int)3); i++)
		{
			System.out.println(name.get(i).getText()+"--------"+details.get(i).getText()+"----------------------"+price.get(i).getText());
		}
		System.out.println();

	}
	public void printChairOutput(List<WebElement> name, List<WebElement> color, List<WebElement> price)
	{
		System.out.println("NAME ----------------- COLOR---------------------------------------- PRICE");
		System.out.println();

		for(int i=0; i<Math.min(price.size(), (int)3); i++)
		{
			System.out.println(name.get(i).getText()+"--------"+color.get(i).getText()+"----------------------"+price.get(i).getText());
		}
		System.out.println();

	}
	public void printMenuList(List<String> menu)
	{
		System.out.println("Menu List ::");
		System.out.println();
		
		for(int i=0; i<menu.size(); i++)
			System.out.println(menu.get(i));
	}
}
