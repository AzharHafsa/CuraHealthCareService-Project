import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Login {
    //Global Variables
    String BaseURL= "https://katalon-demo-cura.herokuapp.com/profile.php#login";
    WebDriver driver;
    String expectedOutput;
    String actualOutput;

    @BeforeTest
    public void BeforeTestMethod() throws InterruptedException{
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
        driver.get(BaseURL);

        //wait for 3 secs to load the login page
        Thread.sleep(3000);
    }

    //Test for wrong username and password combination
    @Test(priority = 1)
    public void negativeLoginTest() throws InterruptedException{
        //Enter wrong un and password and click login button
        driver.findElement(By.id("txt-username")).sendKeys("WrongUnameHafsa");
        driver.findElement(By.id("txt-password")).sendKeys("WrongPassword123");
        driver.findElement(By.id("btn-login")).click();

        //Wait for 2 secs
        Thread.sleep(2000);

        //Check whether the correct error message is displayed
        actualOutput = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/p[2]")).getText();
        expectedOutput = "Login failed! Please ensure the username and password are valid.";
        if(actualOutput.equals(expectedOutput)){
            System.out.println("T03.Wrong Uname & pw Test : Pass");
        }else{
            System.out.println("T03.Wrong Uname & pw Test : Fail");
        }
    }

    //Test for null values login
    @Test(priority = 2)
    public void nullValuesTest() throws InterruptedException{
        //Refresh the current login page
        driver.navigate().refresh();

        //wait for 2 secs
        Thread.sleep(2000);

        //Without entering any values clicking the login btn
        driver.findElement(By.id("btn-login")).click();

        //Check whether the correct error message is displayed
        actualOutput = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/p[2]")).getText();
        expectedOutput = "Login failed! Please ensure the username and password are valid.";
        if(actualOutput.equals(expectedOutput)){
            System.out.println("T04.Null values Login Test : Pass");
        }else{
            System.out.println("T04.Null values Login Test : Fail");
        }
    }

    //Test for correct UN and PW combination
    @Test(priority = 3)
    public void positiveLoginTest() throws InterruptedException{
        //Refresh the current login page
        driver.navigate().refresh();

        //wait for 2 secs
        Thread.sleep(2000);

        //Enter correct un and password and click login button
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.id("btn-login")).click();

        //Check whether the make appointment page is loaded after successful login
        actualOutput = driver.findElement(By.xpath("/html/body/section/div/div/div/h2")).getText();
        expectedOutput = "Make Appointment";
        if(actualOutput.equals(expectedOutput)){
            System.out.println("T05.Positive Login Test : Pass");
        }else{
            System.out.println("T05.Positive Login Test : Fail");
        }
        System.out.println("--------------------------------------------------------------------");
    }

    @AfterTest
    public void AfterTestMethod() throws InterruptedException{
        System.out.println("Login page test completed!");
    }

}
