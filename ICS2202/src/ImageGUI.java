import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ImageGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private String userName = "wehazanengineer@gmail.com";
	private String password = "wehazanengineer1";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageGUI frame = new ImageGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ImageGUI() {
		contentPane = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 800);

		JButton callButton = new JButton("Call");
		contentPane.add(callButton);
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\juan\\Documents\\ICS2202\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		// And now use this to visit Google
		driver.get("http://hangouts.google.com");

		// Find the text input element by its name
		WebElement phoneCall = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[4]/div[3]/div/div/ul/li[2]/div[1]"));
		phoneCall.click();
		driver.findElement(By.id("Email")).sendKeys(userName);
		driver.findElement(By.id("next")).click();
		try {
			Thread.sleep(500);
		} catch (Exception e){
			e.printStackTrace();
		}
		driver.findElement(By.id("Passwd")).sendKeys(password);
		driver.findElement(By.id("signIn")).click();

		if(!driver.findElements(By.xpath("//*[@id=\"yDmH0d\"]/div[6]/div[2]/div/div[2]/div[1]")).isEmpty()) {
			for(int i = 0; i < 4; i++) {
				driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[6]/div[2]/div/div[3]")).click();
			}
		}
		try {
			Thread.sleep(2000);
		} catch (Exception e){
			e.printStackTrace();
		}
		phoneCall = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[4]/div[4]/div/div/ul/li[2]/div[1]"));
		phoneCall.click();

		// Check the title of the page
		System.out.println("Page title is: " + driver.getTitle());

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		switch( event.getActionCommand()) {
			case "Call":

		}
	}
}
