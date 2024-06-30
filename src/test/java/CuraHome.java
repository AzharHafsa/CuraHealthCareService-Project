import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CuraHome {

    //Global Variables
    String BaseURL = "https://katalon-demo-cura.herokuapp.com/";
    WebDriver driver;
    Boolean expectedOutput;
    Boolean actualOutput;

    @BeforeTest
    public void beforeTestMethod() throws InterruptedException{
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
        driver.get(BaseURL);

        //Wait for 3 secs to load the home page
        Thread.sleep(3000);
    }

    @Test(priority = 1)
    public void testMakeAppointmentBtn() throws InterruptedException{
         //Click make Appointment button
        driver.findElement(By.id("btn-make-appointment")).click();

         //Wait for 2 secs to load Login page
        Thread.sleep(2000);

         //Check whether correctly navigated to Login page
        actualOutput= driver.findElement(By.xpath("/html/body/section/div/div/div[1]/h2")).getAccessibleName().equals("Login");

        expectedOutput = true;

        if(actualOutput == expectedOutput){
            System.out.println("T01.Make Appointment Btn test : Pass");
        }else {
            System.out.println("T01.Make Appointment Btn test : Fail");
        }

    }

    @Test(priority = 2)
    public void testMenuToggleBtn() throws InterruptedException{
        //Back to home page
        driver.get(BaseURL);

        //Click menu toggle btn
        driver.findElement(By.id("menu-toggle")).click();

        //Check whether the navigation bar is correctly displayed
        String value= driver.findElement(By.id("sidebar-wrapper")).getAttribute("class");

        if(value.equals("active")){
            System.out.println("T02.Menu toggle Btn test : Pass");
        }else{
            System.out.println("T02.Menu toggle Btn test : Fail");
        }
        System.out.println("--------------------------------------------------------------------");
    }

    @AfterTest
    public void afterTestMethod() throws InterruptedException{
        System.out.println("Home Page Test Completed!");
    }

}
