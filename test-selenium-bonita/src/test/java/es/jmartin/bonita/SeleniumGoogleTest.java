package es.jmartin.bonita;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.jmartin.selenium.support.SeleniumTest;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "server.port=9000", webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@SeleniumTest(driver = FirefoxDriver.class, baseUrl = "http://www.google.com")
public class SeleniumGoogleTest {

	
	   @Autowired
	    private WebDriver driver;

	    //private HomePage homePage;
       
	    /**
	    @Before
	    public void setUp() throws Exception {
//	        homePage = PageFactory.initElements(driver, HomePage.class);

            // Create a new instance of the Firefox driver
            // Notice that the remainder of the code relies on the interface, 
            // not the implementation.
            //driver = new FirefoxDriver();
            
            // And now use this to visit Google
            //driver.get("http://localhost:8080/bonita");

            // Alternatively the same thing can be done like this
            // driver.navigate().to("http://www.google.com");
	    	
	    	
	    	// Create a new instance of the Firefox driver
	        // Notice that the remainder of the code relies on the interface, 
	        // not the implementation.
	        driver = new FirefoxDriver();

	        // And now use this to visit Google
	        driver.get("http://www.google.com");
	        // Alternatively the same thing can be done like this
	        // driver.navigate().to("http://www.google.com");

	        //homePage = new HomePage(driver);
   	
	    }
*/
	    /**
	    @Test
	    public void containsActuatorLinks() {
	        homePage.assertThat()
	                .hasActuatorLink("Tasks", "Cases", "Processes")
	                .hasNoActuatorLink("shutdown");
	    }
	    */
	    
	    @Test
	    public void prueba() {
	        // Find the text input element by its name
	        WebElement element = driver.findElement(By.name("q"));

	        // Enter something to search for
	        element.sendKeys("Cheese!");

	        // Now submit the form. WebDriver will find the form for us from the element
	        element.submit();

	        // Check the title of the page
	        System.out.println("Page title is: " + driver.getTitle());
	        
	        
	        new WebDriverWait(driver, 10);
	        
	        /**
	        // Google's search is rendered dynamically with JavaScript.
	        // Wait for the page to load, timeout after 10 seconds
	        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	                return d.getTitle().toLowerCase().startsWith("cheese!");
	            }
	        });
**/
	        // Should see: "cheese! - Google Search"
	        System.out.println("Page title is: " + driver.getTitle());
	        
	        //Close the browser
	        //driver.quit();
	        assertThat(driver.getTitle()).contains("Cheese!");
	       
	    }

}
