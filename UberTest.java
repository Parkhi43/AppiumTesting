

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import static java.time.Duration.ofMillis;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.Dimension;

public class UberTest  {

    static AndroidDriver driver;

    public static void main(String[] args) {
        try {
            
        	openUber();
        	 enableLocation() ;
        	 enterLocation();
        	 setDateAndTime();
        	 changeRideAndPayment();
        	 scrollDownAndUp();
        	 sendPackage();
        	 accountVerification();
        	 login();
            driver.quit();
        } 
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    public static void openUber() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:platformVersion", "14"); 
        capabilities.setCapability("appium:deviceName", "ParkhiAccwhreja");
        capabilities.setCapability("appium:udid", "26160f03"); 
        capabilities.setCapability("appium:appPackage", "com.ubercab");
        capabilities.setCapability("appium:appActivity", "com.ubercab.presidio.app.core.root.RootActivity");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:ignoreHiddenApiPolicyError", true);
        capabilities.setCapability("appium:noReset", true);

        URL url = new URL("http://127.0.0.1:4723");

        driver = new AndroidDriver(url, capabilities);
        Thread.sleep(2000);
        System.out.println("Application launched");
    }

    
    
    public static void login() throws InterruptedException  {
    	Thread.sleep(3000);
     driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"forward-button\"]")).click();
     
     Thread.sleep(10000);
        

    }


    public static void enableLocation() throws InterruptedException  {
        WebElement enableLocationButton=driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ubercab:id/ub__system_banner_trailing_button\"]"));
        enableLocationButton.click();
        Thread.sleep(2000);
        System.out.println("Location Button clicked!");

        WebElement turnOn=driver.findElement(By.id("android:id/button1"));
        turnOn.click();
        Thread.sleep(2000);
        System.out.println("Location enabled!");


    }


    public static void enterLocation() throws InterruptedException {

        WebElement findLocationButton=driver.findElement(By.id("com.ubercab:id/ub__internal_text"));
        findLocationButton.click();
        Thread.sleep(2000);

        WebElement enterLocationButton=driver.findElement(By.id("com.ubercab:id/ub__location_edit_search_destination_edit"));
        enterLocationButton.sendKeys("ISBT-17");
        Thread.sleep(2000);
        System.out.println("Location found!");

        driver.findElement(By.xpath("(//android.widget.LinearLayout)[1]")).click();

    }



    public static void accountVerification() throws InterruptedException {
        WebElement accountIcon=driver.findElement(By.xpath("//android.view.View[@index='1' and @bounds='[810,2082][1080,2268]']"));

        accountIcon.click();
        Thread.sleep(2000);
        System.out.println("Account Icon clicked!");
      //android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout[6]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout

        WebElement settings=driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout[6]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout"));

        settings.click();
        Thread.sleep(2000);
        
        Dimension size = driver.manage().window().getSize();
        int screenWidth = size.width;
        int screenHeight = size.height;

        // Calculate start and end points for the scroll
        int startX = screenWidth / 2;  // horizontal center
        int startY = (int) (screenHeight * 0.9);  // near the bottom of the screen
        int endY = (int) (screenHeight * 0.2);    // near the top of the screen

        // Define the input source (finger)
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        boolean found = false;
        while (!found) {
            // Scroll down action
            Sequence scrollDown = new Sequence(finger, 0);
            scrollDown.addAction(finger.createPointerMove(ofMillis(0), PointerInput.Origin.viewport(), startX, startY)); // start position
            scrollDown.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            scrollDown.addAction(finger.createPointerMove(ofMillis(1000), PointerInput.Origin.viewport(), startX, endY)); // move up
            scrollDown.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Arrays.asList(scrollDown));
            Thread.sleep(1000);  // wait for the scroll to finish

            // Attempt to find the logout element
            try {
                WebElement logOut = driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id=\"com.ubercab:id/logout_settings_section_item\"]"));
                if (logOut.isDisplayed()) {
                    found = true;  // Element is found
                }
            } catch (NoSuchElementException e) {
                // Element not found, continue scrolling
                System.out.println("Logout element not found, scrolling down...");
            }
        

        WebElement logOut=driver.findElement(By.xpath(" //android.view.ViewGroup[@resource-id=\"com.ubercab:id/logout_settings_section_item\"]"));
        logOut.click();
        Thread.sleep(1000); 
      
        
        WebElement confirm=driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ubercab:id/confirmation_modal_button_primary_base\"]"));
        confirm.click();
        Thread.sleep(4000); 
        }
    }


    public static void setDateAndTime() throws InterruptedException {
        WebElement timeIcon=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Schedule a trip\"]"));

        timeIcon.click();
        Thread.sleep(2000);
        System.out.println("Time Icon clicked!");
        
      
        WebElement date=driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ubercab:id/scheduled_rides_select_date_textview\"]"));
        date.click();
        Thread.sleep(2000);
        
        
        WebElement dateSelect=driver.findElement(By.xpath("//android.view.View[@content-desc=\"25 October 2024\"]"));
        dateSelect.click();
        Thread.sleep(2000);
        
      
        WebElement ok=driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]"));
        ok.click();
        Thread.sleep(2000);
        
        WebElement time=driver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"3\"]"));
        time.click();
        Thread.sleep(2000);
   
        driver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"30\"]")).click();
        
        Thread.sleep(2000);

        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();
        Thread.sleep(2000);

        WebElement next= driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.ubercab:id/ub__picker_confirm_button\"]"));
        next.click();
        Thread.sleep(2000);
        System.out.println("Reserved the time slot!");
        
    }
    

    
    public static void scrollDownAndUp() throws InterruptedException {
        // Get the dimensions of the screen
        Dimension size = driver.manage().window().getSize();
        int screenWidth = size.width;
        int screenHeight = size.height;

        // Calculate start and end points for the scroll
        int startX = screenWidth / 2;  // horizontal center
        int startY = (int) (screenHeight * 0.8);  // near the bottom of the screen
        int endY = (int) (screenHeight * 0.2);    // near the top of the screen

        // Define the input source (finger)
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // Scroll down action (from bottom to top)
        Sequence scrollDown = new Sequence(finger, 0);
        scrollDown.addAction(finger.createPointerMove(ofMillis(0), PointerInput.Origin.viewport(), startX, startY)); // start position
        scrollDown.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scrollDown.addAction(finger.createPointerMove(ofMillis(1000), PointerInput.Origin.viewport(), startX, endY)); // move up
        scrollDown.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        System.out.println("Scrolled Down!");
        
        driver.perform(Arrays.asList(scrollDown));
        Thread.sleep(4000);

        
        Sequence scrollUp = new Sequence(finger, 1);
        scrollUp.addAction(finger.createPointerMove(ofMillis(1), PointerInput.Origin.viewport(), startX, endY)); // Start near the top
        scrollUp.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scrollUp.addAction(finger.createPointerMove(ofMillis(1000), PointerInput.Origin.viewport(), startX, startY)); // Move to bottom (corrected)
        scrollUp.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        System.out.println("Scrolled Up!");
        driver.perform(Arrays.asList(scrollUp));
        Thread.sleep(3000);
        }

    
    public static void changeRideAndPayment() throws InterruptedException {

        WebElement changeLoc=driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.ubercab:id/flex_product_selection_recycler_view_id\"]/android.view.ViewGroup[2]"));
        changeLoc.click();
        Thread.sleep(2000);

        WebElement movePayment=driver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.ubercab:id/ub__intent_caret\"]"));
        movePayment.click();
        Thread.sleep(2000);
        
      
       
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Back\"]")).click();
        Thread.sleep(2000);
    	
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Menu\"]")).click();
        Thread.sleep(2000);
 
        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]")).click();
        Thread.sleep(2000);
    	
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Menu\"]")).click();
        Thread.sleep(2000);
 
    
    }

   public static void sendPackage() throws InterruptedException{
	 
	    
	   WebElement servicesButton=driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id=\"com.ubercab:id/content_frame\"]/android.widget.FrameLayout[2]/android.widget.FrameLayout/com.uber.rib.core.compose.root.UberComposeView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View"));
       servicesButton.click();
       Thread.sleep(2000);

       WebElement movePayment=driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Package\"]"));
       movePayment.click();
       Thread.sleep(2000);
       
       driver.findElement(By.xpath(" //android.widget.Button[@content-desc=\"Menu\"]")).click();
    }


	
}




