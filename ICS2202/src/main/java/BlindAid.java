import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class BlindAid extends JFrame implements ActionListener, KeyListener {
	/**
	 * Variables
	 */
	private String[] callNumbers = new String[9];
	private String[] smsNumbers = new String[9];
	private boolean goProvider = false;
	private boolean vodafoneProvider = false;
	
	private boolean controlPressed = false;
	private boolean cPressed = false;
	private boolean mPressed = false;

	private Container container;
	private JMenu FILE_MENU;
	private String MESSAGING_PROVIDER = "Vodafone";
	private String hangoutUserName = "wehazanengineer@gmail.com";
	private String hangoutPassword = "wehazanengineer1";
	private String goUsername = "";
	private String goPassword = "";
	private String vodafoneUsername = "";
	private String vodafonePassword = "";
	private WebDriver driver;
	private WebDriverWait wait;

	VoiceManager freettsVM;
	static Voice freettsVoice;


	/**
	 * Create the frame.
	 */
	public BlindAid() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 400, 150);
		this.setResizable(false);
		this.addKeyListener(this);
        this.setFocusable(true);
		container = this.getContentPane();
		container.setLayout(new GridLayout(1,2));

		try {
			File inputFile = new File("data.dat");
			if(inputFile.exists()){
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputFile));
				this.goProvider = ois.readBoolean();
				this.vodafoneProvider = ois.readBoolean();
				this.callNumbers = (String[]) ois.readObject();
				this.smsNumbers = (String[]) ois.readObject();
				ois.close(); 
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		//Create an instance of a menu Bar
		JMenuBar menuBar = new JMenuBar();
		//Create the menu bar menus
		this.createFileMenu();		
		//Add File Menu to Menu Bar
		menuBar.add(FILE_MENU);
		//Add the new menu bar to window
		this.setJMenuBar(menuBar);


		//Create Call Button and add it to GUI
		JButton callButton = new JButton("Call");
		callButton.addActionListener(this);
		container.add(callButton);

		//Create SMS Button and add it to GUI
		JButton smsButton = new JButton("SMS");
		smsButton.addActionListener(this);
		container.add(smsButton);

		//get the .exe chrome driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

		System.setProperty("mbrola.base", "src/main/resources/mbrola");
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

		freettsVM = VoiceManager.getInstance();

		// Simply change to MBROLA voice
		freettsVoice = freettsVM.getVoice("kevin");

		// Allocate your chosen voice
		freettsVoice.allocate();
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

	//MenuBar Menus
	private void createFileMenu() {
		JMenuItem menuItem;

		FILE_MENU = new JMenu("File");

		menuItem = new JMenuItem("Options");
		menuItem.addActionListener(this);
		FILE_MENU.add(menuItem);
		FILE_MENU.addSeparator();
		menuItem = new JMenuItem("Quit");
		menuItem.addActionListener(this);
		FILE_MENU.add(menuItem);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
			this.controlPressed = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_C) {
			this.cPressed = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_M) {
			this.mPressed = true;
		}
		
		if(controlPressed) {
			if(e.getKeyCode() == KeyEvent.VK_1){
				if(mPressed){
					this.sms(smsNumbers[1]);
				}else if(cPressed){
					this.call(callNumbers[1]);
				}
			}else if(e.getKeyCode() == KeyEvent.VK_2){
				if(mPressed){
					this.sms(smsNumbers[2]);
				}else if(cPressed){
					this.call(callNumbers[2]);
				}
			}else if(e.getKeyCode() == KeyEvent.VK_3){
				if(mPressed){
					this.sms(smsNumbers[3]);
				}else if(cPressed){
					this.call(callNumbers[3]);
				}
			}else if(e.getKeyCode() == KeyEvent.VK_4){
				if(mPressed){
					this.sms(smsNumbers[4]);
				}else if(cPressed){
					this.call(callNumbers[4]);
				}
			}else if(e.getKeyCode() == KeyEvent.VK_5){
				if(mPressed){
					this.sms(smsNumbers[5]);
				}else if(cPressed){
					this.call(callNumbers[5]);
				}
			}else if(e.getKeyCode() == KeyEvent.VK_6){
				if(mPressed){
					this.sms(smsNumbers[6]);
				}else if(cPressed){
					this.call(callNumbers[6]);
				}
			}else if(e.getKeyCode() == KeyEvent.VK_7){
				if(mPressed){
					this.sms(smsNumbers[7]);
				}else if(cPressed){
					this.call(callNumbers[7]);
				}
			}else if(e.getKeyCode() == KeyEvent.VK_8){
				if(mPressed){
					this.sms(smsNumbers[8]);
				}else if(cPressed){
					this.call(callNumbers[8]);
				}
			}else if(e.getKeyCode() == KeyEvent.VK_9){
				if(mPressed){
					this.sms(smsNumbers[9]);
				}else if(cPressed){
					this.call(callNumbers[9]);
				}
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
			this.controlPressed = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_C) {
			this.cPressed = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_C) {
			this.mPressed = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent event) {		
		String phoneNumber;
		switch( event.getActionCommand()) {
		case "Call": System.out.println("Call Pressed");
		//Enter Phone Number to Call
		phoneNumber = JOptionPane.showInputDialog(this, "Who Would you like to call?");
		System.out.println("Number to Call: " + phoneNumber);
		if(phoneNumber != null) {
			this.call(phoneNumber);
		}
		break;

		case "SMS": System.out.println("SMS Pressed");
		phoneNumber = JOptionPane.showInputDialog(this, "Who Would you like to text?");
		if(phoneNumber != null) {
			this.sms(phoneNumber);
		}
		break;

		case "Options": System.out.println("Options Selected");	
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(0,1));

		JTabbedPane tabContainer = new JTabbedPane();

		//GeneralTab
		JPanel generalTab = new JPanel();
		JLabel providerLabel = new JLabel("Provider:");
		JRadioButton goButton = new JRadioButton("GO Mobile");
		goButton.setActionCommand("GO Mobile");
		goButton.setSelected(this.goProvider);

		JRadioButton vodafoneButton = new JRadioButton("Vodafone");
		vodafoneButton.setActionCommand("Vodafone");
		vodafoneButton.setSelected(this.vodafoneProvider);

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(goButton);
		radioGroup.add(vodafoneButton);

		generalTab.add(providerLabel);
		generalTab.add(goButton);
		generalTab.add(vodafoneButton);

		//CallTab
		JPanel callTab = new JPanel();
		JTextField[] callNumBoxes = new JTextField[9];
		callTab.setLayout(new GridLayout(9,2,5,5));

		for(int i = 0; i < 9; i++) {
			JLabel callShortcutsLabel = new JLabel("Call Shortcuts CTRL + C + "+i+" ");
			callTab.add(callShortcutsLabel);
			callNumBoxes[i] = new JTextField();
			callNumBoxes[i].setText(this.callNumbers[i]);
			callTab.add(callNumBoxes[i]);
		}

		//SMSTab
		JPanel smsTab = new JPanel();
		JTextField[] smsNumBoxes = new JTextField[9];
		smsTab.setLayout(new GridLayout(9,2,5,5));

		for(int i = 0; i < 9; i++) {
			JLabel callShortcutsLabel = new JLabel("SMS Shortcuts CTRL + M + "+i+" ");
			smsTab.add(callShortcutsLabel);
			smsNumBoxes[i] = new JTextField();
			smsNumBoxes[i].setText(this.smsNumbers[i]);
			smsTab.add(smsNumBoxes[i]);
		}
		tabContainer.add("General", generalTab);
		tabContainer.add("Call", callTab);
		tabContainer.add("SMS", smsTab);
		myPanel.add(tabContainer);


		int optionResult = JOptionPane.showConfirmDialog(this, myPanel, "Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if(optionResult == JOptionPane.OK_OPTION) {

			try {
				for(int i = 0; i < 9; i ++) {
					this.callNumbers[i] = callNumBoxes[i].getText();
					this.smsNumbers[i] = smsNumBoxes[i].getText();
				}

				this.goProvider = goButton.isSelected();
				this.vodafoneProvider = vodafoneButton.isSelected();

				FileOutputStream fos = new FileOutputStream("data.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);   
				oos.writeBoolean(goButton.isSelected());
				oos.writeBoolean(vodafoneButton.isSelected());
				oos.writeObject(this.callNumbers);
				oos.writeObject(this.smsNumbers);
				oos.close(); 
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		break;

		case "Quit": System.out.println("Quit Selected");
		//Quit
		System.exit(0);
		break;
		}
	}

	private void call(String num) {
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--use-fake-ui-for-media-stream=true");
		//Create a new driver to control chrome
		//WebDriver driver = new ChromeDriver(options);
		driver = new ChromeDriver();
		//Visit Google Hangouts
		driver.get("http://hangouts.google.com");

		//New wait object 10 seconds
		wait = new WebDriverWait(driver, 10);
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
		numberInputField.sendKeys(num);
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
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error while accepting microphone permission");
		}

		this.waitMS(5000);
		driver.close();
	}

	private void sms(String num) {
		String message = JOptionPane.showInputDialog(this, "Enter your message");
		System.out.println("Number to test: " + num + " Message: " + message);
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--use-fake-ui-for-media-stream=true");
		//Create a new driver to control chrome
		//WebDriver driver = new ChromeDriver(options);
		driver = new ChromeDriver();

		if(MESSAGING_PROVIDER.equalsIgnoreCase("GO")){
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

			driver.findElement(By.id("web-to-sms-form-recipients")).sendKeys(num);
			driver.findElement(By.id("web-to-sms-form-message")).sendKeys(message);
			driver.findElement(By.id("web-to-sms-form-submit")).click();
		}else if(MESSAGING_PROVIDER.equalsIgnoreCase("Vodafone")){
			//New wait object 10 seconds
			wait = new WebDriverWait(driver, 10);

			//Visit Vodafone
			driver.get("https://www.vodafone.com.mt/");
			wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_ctl00_cphParent_ResponsiveQuickLinks_ResponsiveLogin_txtUsername_txtInput"))).sendKeys(this.vodafoneUsername);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_ctl00_cphParent_ResponsiveQuickLinks_ResponsiveLogin_txtPassword_txtInput"))).sendKeys(this.vodafonePassword);
			try {
				//move mouse to corner to prevent mouseover menus from apearing
				Robot robot = new Robot();
				robot.mouseMove(0,0);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_ctl00_cphParent_ResponsiveQuickLinks_ResponsiveLogin_btnLogin"))).click();

			this.waitMS(5000);
			driver.get("https://www.vodafone.com.mt/web-to-sms");

			wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_ctl00_cphParent_cphMain_ctl00_tbvOtherVodafoneNumber_txtInput"))).sendKeys(num);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_ctl00_cphParent_cphMain_ctl00_txtContent_txtInput"))).sendKeys(message);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_ctl00_cphParent_cphMain_ctl00_btnSend"))).click();
		}

		this.waitMS(10000);
		driver.close();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BlindAid myGui = new BlindAid();
		freettsVoice.speak("Hello there!");
		freettsVoice.speak("Press ctrl and S to send sms. or ctrl and C to call!");
		freettsVoice.speak("Press ctrl and O for Options. or ctrl and E to exit!");

	}
}
