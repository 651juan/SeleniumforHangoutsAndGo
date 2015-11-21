import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class BlindAid extends JFrame implements ActionListener {
	/**
	 * Variables
	 */
	private Container container;

	private String userName = "wehazanengineer@gmail.com";
	private String password = "wehazanengineer1";

	/**
	 * Create the frame.
	 */
	public BlindAid() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 500);
		container = this.getContentPane();
		container.setLayout(new GridLayout(1,2));

		//Create Call Button and add it to GUI
		JButton callButton = new JButton("Call");
		callButton.addActionListener(this);
		container.add(callButton);

		//Create SMS Button and add it to GUI
		JButton smsButton = new JButton("SMS");
		smsButton.addActionListener(this);
		container.add(smsButton);

		//get the .exe chrome driver
		System.setProperty("webdriver.chrome.driver", "ICS2202/chromedriver.exe");

		//Show Gui
		this.setVisible(true);
	}
	
	private void waitMS(long ms) {
		try{
			Thread.sleep(ms);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		switch( event.getActionCommand()) {
		case "Call": System.out.println("Call Pressed");
		//Enter Phone Number to Call
	    String phoneNumber = JOptionPane.showInputDialog(this, "Who Would you like to call?");

		//Create a new driver to control chrome
		WebDriver driver = new ChromeDriver();
		//Visit Google Hangouts
		driver.get("http://hangouts.google.com");
		
		if(!driver.findElements(By.id("gb_71")).isEmpty()) { //Not Empty then signed in
			System.out.println("Already Signed In");
		}else{
			System.out.println("Not Signed In");
			//Try to find signin button
			List<WebElement> signInButton = driver.findElements(By.id("gb_70"));
			//If it exist
			if(!signInButton.isEmpty()){
				//click sign in button
				signInButton.get(0).click();
				//Type Username
				driver.findElement(By.id("Email")).sendKeys(userName);
				//Press Next
				driver.findElement(By.id("next")).click();
				//Wait to log in
				this.waitMS(2000);
				//Type Password
				driver.findElement(By.id("Passwd")).sendKeys(password);
				//Click Sign in
				driver.findElement(By.id("signIn")).click();
				//If first login click 4x next
				if(!driver.findElements(By.xpath("//*[@id=\"yDmH0d\"]/div[6]/div[2]/div/div[2]/div[1]")).isEmpty()) {
					for(int i = 0; i < 4; i++) {
						driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[6]/div[2]/div/div[3]")).click();
					}
				}
				//Get phone button (commented phone call is before sign in)
				//WebElement phoneCall = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[4]/div[3]/div/div/ul/li[2]/div[1]"));
				this.waitMS(4000);
				WebElement phoneCall = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[4]/div[4]/div/div/ul/li[2]/div[1]"));
				//Click the phone call button
				phoneCall.click();
				this.waitMS(4000);
				//Get PhoneCall Field
				WebElement phoneCallEntry = driver.findElement(By.cssSelector("placeholder=\"Name, phone number\""));
				phoneCallEntry.sendKeys(phoneNumber);
				//GetPhoneCall Image
				WebElement phoneCallImage = driver.findElement(By.xpath("//*[@id=\":hr\"]/div[1]/div[2]/img"));
				phoneCallImage.click();
				//If there is an accept button click it
				List<WebElement> acceptTermsCallButton = driver.findElements(By.xpath("//*[@id=\":o5.ct\"]/div/div/div/div[2]/div/button"));
				
				if(!acceptTermsCallButton.isEmpty()) {
					acceptTermsCallButton.get(0).click();
				}
				
				// Check the title of the page
				//System.out.println("Page title is: " + driver.getTitle());
				
				//Close Driver
				//driver.close();
			}
		}
		break;

		case "SMS": System.out.println("SMS Pressed");
		break;
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BlindAid myGui = new BlindAid();
	}
}
