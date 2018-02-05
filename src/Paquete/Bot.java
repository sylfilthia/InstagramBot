package Paquete;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.graph.ElementOrder;

import org.openqa.selenium.TimeoutException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Bot {
	WebDriver driver;
	String usuario;
	String password;
	
	//COMMENTS☻
	String[] first_comment  = { "Che,", "wow...", "Buenisimo che!", "Holaa,", "Wow","Hey","Eu","ey","eyy","Euu","eyy"};
	String[] middle_comment = { "Muy buena foto!", "genial la foto, te dejo mi like.. Buena foto", "LIKE!!", "buen contenido!! pasate por mi perfil..","terrible foto, likee!","buena foto.","copada foto.. pasate por mi perfil"};
	String[] emoji_1        = { "😃", "😎", "👯", "😉", "😳", "😜", "😙", "😘", "😉", "🙊" };
	String[] emoji_2        = { "🐷", "👌", "💫", "😂", "😍", "🌌", "💫" ,"👈", "🐵", "🙈", "🙉" };
	
	public Bot(String usuario, String password){
		driver = new FirefoxDriver();
		this.usuario = usuario;
		this.password = password;
		Login();
	}
	
	public void Rest(int seconds, boolean msg){
		try {
			if(msg)
				System.out.println("It's my rest time... let me take like umh " + seconds + "s");
			Thread.sleep(seconds*1000);			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void waitElement(String elemento, int tiempo){
		WebDriverWait esperar = new WebDriverWait(driver, tiempo);
		esperar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elemento)));
	}
	
	public boolean Verify(String element){
		return driver.findElements(By.xpath(element)).size() > 0;
	}
	
	public void Login(){		
		driver.get("https://www.instagram.com/accounts/login/");
		waitElement("//*[@id='react-root']/section/main/div/article/div/div[1]/div/form/div[1]/div/div[1]/input",20); 	//MODIFY XPATH HERE IN CASE INSTAGRAM CHANGE HIS STRUCTURE
		waitElement("//*[@id='react-root']/section/main/div/article/div/div[1]/div/form/div[2]/div/div[1]/input",20);	//MODIFY XPATH HERE IN CASE INSTAGRAM CHANGE HIS STRUCTURE
		driver.findElement(By.xpath("//*[@id='react-root']/section/main/div/article/div/div[1]/div/form/div[1]/div/div[1]/input")).sendKeys(usuario);	//MODIFY XPATH HERE IN CASE INSTAGRAM CHANGE HIS STRUCTURE
		driver.findElement(By.xpath("//*[@id='react-root']/section/main/div/article/div/div[1]/div/form/div[2]/div/div[1]/input")).sendKeys(password);	//MODIFY XPATH HERE IN CASE INSTAGRAM CHANGE HIS STRUCTURE
		driver.findElement(By.xpath("//*[@id='react-root']/section/main/div/article/div/div[1]/div/form/div[1]/div/div[1]/input")).sendKeys(Keys.RETURN);	//MODIFY XPATH HERE IN CASE INSTAGRAM CHANGE HIS STRUCTURE
		Rest(3,true);		
		driver.get("https://www.instagram.com/" + usuario);
	}
	
	public void Like(){
		this.driver.get("https://www.instagram.com/");
		Rest(3,false);
		int count = 0,row = 0, empty = 0;			
			for(int i = 1; i < 500; i++){
				List<WebElement> Likes = driver.findElements(By.xpath("//span[text()='Me gusta']"));	//MODIFY 'Me Gusta' to Like or however it is in your language :)
				if(Likes.size() > 0){
					System.out.println("Dude i just found like " + Likes.size() + " photos we haven't liked yet");
						empty = 0;
					for(WebElement w : Likes) {							
								try{
									w.click();
									count++;
									row++;	
									Rest(5,true);
								} catch(Exception e){
									break;
								}
							System.out.println("A-anon... we are doing good... it's our " + count + "º like! (" + row +  "º in a row) [Loop: " + i + "]");
						}	
				}else{
					empty++;
				}
				
				if(empty > 8){
					System.out.println("There's nothing else to like so... it's over right?");
					break;
				}
				
				row = 0;
				if(Likes.size() == 0)
					((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
				Likes.clear();
				Rest(5,false);
			}
		//Create a WebElement LIST
		//Save every 'Like' button with the text 'Me gusta' ('Like' in english, you gotta change it) into the LIST
		//Iterate the List
		//Gib laik
		//If there's no more thing to like... go to the bottom and load more 'cards'.
	}
	
	public void Comment(){
		this.driver.get("https://www.instagram.com/");
		Rest(3,false);
		int count = 0,row = 0,avaible = 0;	
		boolean commented = false;
			for(int i = 1; i < 500; i++){
				
				List<WebElement> Comments = driver.findElements(By.xpath("//*[@id='react-root']/section/main/section/div[1]/div[1]/div/article"));	 //MODIFY XPATH HERE... YOU HAVE TO SELECT A NOTICE AND PUT IT HERE.
				avaible = Comments.size();
				System.out.println("Seleccione " + Comments.size() + " articulos");
				if(Comments.size() > 0){
					for(WebElement w : Comments) {
						commented = false;
						try{
							List<WebElement> last_comments = w.findElements(By.xpath(".//li"));						
							for(WebElement l : last_comments){
								try{
									List<WebElement> title_comments = l.findElements(By.xpath(".//a[@title = '" + usuario + "']"));
									if(title_comments.size() > 0){
										commented = true;
										avaible--;
									}
								}catch(Exception e){
									
								}							
							}
						}catch(Exception e){
							
						}
						System.out.println(avaible + " fueron disponibles para comentar");
						try{
							if(!commented){
								WebElement textarea = w.findElement(By.xpath(".//textarea"));
								String message = first_comment[Azar(0,first_comment.length - 1)] + " " 
										   + middle_comment[Azar(0,first_comment.length - 1)] + " " 
									       + emoji_1[Azar(0,first_comment.length - 1)]
									       + emoji_2[Azar(0,first_comment.length - 1)];
								textarea.click();
								textarea.clear();
								textarea.sendKeys(message);
								Rest(1,false);
								textarea.sendKeys(Keys.RETURN);
								textarea.sendKeys(Keys.ENTER);
								count++;
								row++;	
								Rest(20,true);								
								
							}
						} catch(Exception e){
							break;
						}
							System.out.println("A-anon... we are doing good... it's our " + count + "º comment! (" + row +  "º in a row) [Loop: " + i + "]");
							
						}					
				}
				
				if(avaible == 0 && i > 1){
					System.out.println("hey i found like " + Comments.size() + " photos but we already commented'em all.");
					((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
				}	
				Comments.clear();
				row = 0;	
				Rest(10,false);
			}
		//Create a WebElement LIST
		//Save every 'Like' button with the text 'Me gusta' ('Like' in english, you gotta change it) into the LIST
		//Iterate the List
		//Gib laik
		//If there's no more thing to like... go to the bottom and load more 'cards'.
	}
	
	public void Follow(ArrayList profiles){
		for(int i = 0; i < profiles.size(); i++){
			int valor = 100, tiempo = 0, porcentaje = 0, contador = 0;			
			boolean descanso = false,error = false;
			
			String link = "https://www.instagram.com/" + profiles.get(i);			
			driver.get(link);			
			
				System.out.println("Followers: "+valor);
				waitElement(".//*[@id='react-root']/section/main/article/header/section/ul/li[2]/a",5);
				driver.findElement(By.xpath(".//*[@id='react-root']/section/main/article/header/section/ul/li[2]/a")).click();
			
			
			for(int y = 1; y < 100; y++){
				descanso = false;
				if(contador == 100){
					Rest(600,true);
					contador = 0;
				}else{
					contador++;
				}
				
				porcentaje = Azar(1,105);
				
				if(porcentaje >= 0 && porcentaje <= 50){
					tiempo = 5;
				}
				else if(porcentaje >= 50 && porcentaje <= 70){
					tiempo = 10;
				}
				else if(porcentaje >= 70 && porcentaje <= 90){
					tiempo = 15;
				}
				else if(porcentaje >= 90 && porcentaje <= 100){
					tiempo = 20;
				}
				else if(porcentaje >= 102 && porcentaje <= 105){
					descanso = true;
				}
				
				System.out.println("Tiempo de espera: "+(tiempo/1000)+"s.");
				System.out.println("Loteria: "+porcentaje+"/"+105);
				System.out.println("¿Descanso?: "+descanso);
				System.out.println("Progreso: "+valor+"/"+y);
				
				try{		
					
				waitElement("/html/body/div[4]/div/div[2]/div/div[2]/ul/div/li[" + y + "]/div/div[2]/span/button",20); //HERE IS WHERE YOU HAVE TO MODIFY FOLLOW BUTTON... PUT ONE AS EXAMPLE.
				if(Verify("/html/body/div[4]/div/div[2]/div/div[2]/ul/div/li[" + y + "]/div/div[2]/span/button")){     // HERE
					try{
					System.out.println(y);
					String Elemento = "/html/body/div[4]/div/div[2]/div/div[2]/ul/div/li[" + y + "]/div/div[2]/span/button"; //HERE
					waitElement(Elemento,20);
					if(Verify("/html/body/div[4]/div/div[2]/div/div[2]/ul/div/li[" + y + "]/div/div[2]/span/button")){ //HERE
							   
						driver.findElement(By.xpath(Elemento)).click();
					}
					if(Verify("/html/body/div[4]/div/div[2]/div/div[2]/ul/div/li[" + y + "]/div/div[2]/span/button")){ //AAAAAAND.... HERE.
						driver.findElement(By.xpath(Elemento)).sendKeys(Keys.TAB);
					}								
						
					}catch(TimeoutException te){
						
					}
					Rest(tiempo,true);	
				}else{
					System.out.println("ERROR");
				}
			}catch(Exception e){
				
			}
				
				
			}
			
		}
	}
	
	public void UnFollow(){		
		int unfollowed = 0;
		int tiempo = 0;
		boolean descanso = false;
		int porcentaje = 0;
		int contador = 0;
		for(int x = 0; x < 5000; x++){
			if(contador == 100){
				Rest(3600,true);
				contador = 0;
			}
			driver.get("https://www.instagram.com/"+ usuario + "/following/");
			waitElement(".//*[@id='react-root']/section/main/article/header/section/ul/li[3]/a",20);
			driver.findElement(By.xpath(".//*[@id='react-root']/section/main/article/header/section/ul/li[3]/a")).click();
			Rest(1,false);
			for(int y = 1; y < 15; y++){
				descanso = false;
				contador++;
				porcentaje = Azar(0,105);
				
				if(porcentaje >= 0 && porcentaje <= 50){
					tiempo = 30;
				}
				else if(porcentaje >= 50 && porcentaje <= 70){
					tiempo = 40;
				}
				else if(porcentaje >= 70 && porcentaje <= 90){
					tiempo = 60;
				}
				else if(porcentaje >= 90 && porcentaje <= 100){
					tiempo = 120;
				}
				else if(porcentaje >= 103 && porcentaje <= 105){
					descanso = true;
					System.out.println("Psst... i hear the cops...");
				}
				System.out.println("And this is the faggot number " + unfollowed + "...  [" + porcentaje + "/105]");
				if(!descanso){
					try{
						String Elemento = "/html/body/div[4]/div/div[2]/div/div[2]/ul/div/li["+y+"]/div/div[2]/span/button"; //MODIFY XPATH HERE IN CASE INSTAGRAM CHANGE HIS STRUCTURE 	[UNFOLLOW BUTTON]
						waitElement("/html/body/div[4]/div/div[2]/div/div[2]/ul/div/li["+y+"]/div/div[2]/span/button",20); //MODIFY XPATH HERE IN CASE INSTAGRAM CHANGE HIS STRUCTURE		[UNFOLLOW BUTTON]
						driver.findElement(By.xpath(Elemento)).click();
						unfollowed++;
						Rest(tiempo,true);	
					}catch(TimeoutException te){
						
					}				
				}else{
					Rest(Azar(1200,3600),true);
				}
			}
		}
	}
		
	public static int Azar(int min,int max){
		int x = max;
		int y = min;
		int hash = (int) (Math.random()*x) + y;
		return hash;		
	}
}

