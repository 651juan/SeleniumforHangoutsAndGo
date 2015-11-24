import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class BlindAid extends JFrame implements ActionListener {
	/**
	 * Variables
	 */
	private Container container;
	private String hangoutUserName = "wehazanengineer@gmail.com";
	private String hangoutPassword = "wehazanengineer1";
	private String goUsername = "";
	private String goPassword = "";
	String phoneNumber;
	WebDriver driver;

	/**
	 * Create the frame.
	 */
	public BlindAid() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 400, 150);
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

		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--use-fake-ui-for-media-stream=true");
		//Create a new driver to control chrome
		//WebDriver driver = new ChromeDriver(options);
		driver = new ChromeDriver();

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
		phoneNumber = JOptionPane.showInputDialog(this, "Who Would you like to call?");
		System.out.println("Number to Call: " + phoneNumber);

		//Visit Google Hangouts
		driver.get("http://hangouts.google.com");

		//New wait object 10 seconds
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		//Sign out button not Empty then signed in
		if(driver.findElements(By.id("gb_71")).isEmpty()) { 
			System.out.println("Not Signed In");
			//Try to find signin button
			List<WebElement> signInButton = driver.findElements(By.id("gb_70"));
			//If it exist
			if(!signInButton.isEmpty()){
				//click sign in button
				signInButton.get(0).click();
				//Type Username
				driver.findElement(By.id("Email")).sendKeys(hangoutUserName);
				//Press Next
				driver.findElement(By.id("next")).click();

				//Wait for password field to appear
				WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("Passwd")));
				//Type Password
				passwordField.sendKeys(this.hangoutPassword);

				//Click Sign in
				driver.findElement(By.id("signIn")).click();

				//If first login click 4x next
				if(!driver.findElements(By.xpath("//*[@id=\"yDmH0d\"]/div[6]/div[2]/div/div[2]/div[1]")).isEmpty()) {
					for(int i = 0; i < 4; i++) {
						driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[6]/div[2]/div/div[3]")).click();
					}
				}
			}
		}

		this.waitMS(1000);
		//Get phone button (commented phone call xpath is before sign in)
		WebElement phoneCall = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"yDmH0d\"]/div[4]/div[4]/div/div/ul/li[2]/div[1]")));

		//Click the phone call button
		phoneCall.click();

		//Get PhoneCall Field Frame
		WebDriver numberEntryFrame = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("gtn-roster-iframe-id-b")));

		//Switch To phone call frame
		numberEntryFrame.switchTo();

		//Send phone number to input field
		WebElement numberInputField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\":4.vw\"]/div/div[1]/div/div[2]/div/div[2]/div/div/div[1]/table/tbody/tr/td/input")));
		numberInputField.sendKeys(phoneNumber);
		//GetPhoneCall Image
		WebElement callButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\":4.vw\"]/div/div[2]/div/div/div/div[3]/ul")));
		callButton.click();
		//Switch Back to main frame
		driver.switchTo().parentFrame();
		//Wait for chrome to ask permission to use microphone
		this.waitMS(2000);
		//Switch to permision prompt and accept
		try{
			Robot myRobot = new Robot();
			myRobot.keyPress(KeyEvent.VK_TAB);
			myRobot.delay(100);
			myRobot.keyPress(KeyEvent.VK_ENTER);
		}catch(org.openqa.selenium.NoAlertPresentException e){
			System.out.println("Permission already Given");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error while accepting microphone permission");
		}

			this.waitMS(5000);
			driver.close();
			break;

		case "SMS": System.out.println("SMS Pressed");
			phoneNumber = JOptionPane.showInputDialog(this, "Who Would you like to text?");
			String message = JOptionPane.showInputDialog(this, "Enter your message");
			System.out.println("Number to test: " + phoneNumber + "Message: " + message);

			//Visit Google Hangouts
			driver.get("https://www.go.com.mt/personal");

			//New wait object 10 seconds
			this.waitMS(15000);

			driver.findElement(By.className("mygo")).click();

			driver.findElement(By.id("_58_login")).sendKeys(goUsername);
			driver.findElement(By.id("_58_password")).sendKeys(goPassword);
			this.waitMS(1000);
			driver.findElement(By.xpath("//*[@id=\"_58_fm\"]/div/span/span/input")).click();

			driver.get("https://www.go.com.mt/my-go/my-mobile/messaging");
			this.waitMS(10000);

			if(driver.findElements(By.className("ui-dialog-titlebar-close")).size() > 0) {
				driver.findElement(By.className("ui-dialog-titlebar-close")).click();
			}

			driver.findElement(By.id("web-to-sms-form-recipients")).sendKeys(phoneNumber);
			driver.findElement(By.id("web-to-sms-form-message")).sendKeys(message);
			driver.findElement(By.id("web-to-sms-form-submit")).click();

			this.waitMS(10000);

			driver.close();
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
