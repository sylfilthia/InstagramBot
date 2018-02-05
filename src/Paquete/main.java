package Paquete;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.graph.ElementOrder;

import org.openqa.selenium.TimeoutException;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class main {
	static int Option;
	static String Username, Password;
	static ArrayList perfiles = new ArrayList();
		
	public static void setter(int opcion, String user, String pass){
		Option = opcion;
		Username = user;
		Password = pass;
	}
	
	public static void set_prof(ArrayList profiles){
		perfiles = profiles;
		
	}
	
	public static void main(String[] args) {
		
		for(int i = 0; i < perfiles.size(); i++)
			System.out.println(perfiles.get(i));
		
		Path currentRelativePath = Paths.get("");
		String path = currentRelativePath.toAbsolutePath().toString();
		Scanner Opcion = new Scanner(System.in);
		Scanner Datos = new Scanner(System.in);
		System.setProperty("webdriver.gecko.driver", path + "\\geckodriver.exe");		
		System.out.print("Let's put our hands to work.");		
		Bot argie = new Bot(Username,Password);
		
		switch(Option){
			case 1:
				argie.Rest(5, false);
				argie.Follow(perfiles);
				break;
			case 2:
				argie.Rest(5, true);
				argie.UnFollow();
				break;
			case 3:
				argie.Rest(5,true);
				argie.Comment();
				break;
			case 4:
				argie.Rest(5,true);
				argie.Like();
				break;
			default:		
		}
	}

}
