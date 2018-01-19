/***
 * Una forma fácil de comenzar es este ejemplo, 
 * que busca el término "Cheese" en Google 
 * y luego muestra el título de la página de resultados en la consola.
 */

package es.jmartin.bonita;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

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
public class BonitaTest {

	
	   @Autowired
	    private WebDriver driver;

	    
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
	               
	        
	        
	        
	        //ADVERTENCIA: No mezcle esperas implícitas y explícitas. 
	        //Hacerlo puede causar tiempos de espera impredecibles.
	        //Por ejemplo, configurar una espera implícita de 10 segundos 
	        //y una espera explícita de 15 segundos podría provocar un tiempo de espera después de 20 segundos.
	        // Espera explícita
	        //Una espera explícita es un código que define para esperar 
	        //a que se produzca una determinada condición antes de seguir adelante en el código. 
	        //Google's search is rendered dynamically with JavaScript.
	        // Wait for the page to load, timeout after 10 seconds
	        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	                return d.getTitle().toLowerCase().startsWith("cheese!");
	            }
	        });

	        
	        //Esperas implícitas 
	        //Una espera implícita es decirle a WebDriver que sondee el DOM durante un cierto período de tiempo 
	        //cuando intente encontrar un elemento o elementos si no están disponibles de inmediato. 
	        //La configuración predeterminada es 0. Una vez configurado, la espera implícita 
	        //se establece para la vida de la instancia del objeto WebDriver.

//	        WebDriver driver = new FirefoxDriver();
//	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	        driver.get("http://somedomain/url_that_delays_loading");
//	        WebElement myDynamicElement = driver.findElement(By.id("myDynamicElement"));
	        
	        
	        // Should see: "cheese! - Google Search"
	        System.out.println("Page title is: " + driver.getTitle());
	        
	        //Close the browser
	        //driver.quit();
	        assertThat(driver.getTitle()).contains("Cheese!");
	       
	    }

}
