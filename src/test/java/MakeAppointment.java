import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MakeAppointment {
    //Global variables
    WebDriver driver;
    String BaseURL = "https://katalon-demo-cura.herokuapp.com/";
    String expectedOutput;
    String actualOutput;


    @BeforeTest
    public void beforeTestMethod() throws InterruptedException{
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();

        //Load the home page and navigate to login page
        driver.get(BaseURL);
        driver.findElement(By.id("btn-make-appointment")).click();
        Thread.sleep(2000);

        //Login to the page
        login();
        //wait for 2 secs
        Thread.sleep(2000);
    }

    //Test for making an appointment
    @Test(priority = 1)
    public void MakeAppPositiveTest() throws InterruptedException{

        //Select 2nd dropdown value
        Select facility= new Select(driver.findElement(By.id("combo_facility")));
        facility.selectByIndex(1);
        Thread.sleep(2000);

        //Tick the readmission checkbox
        driver.findElement(By.id("chk_hospotal_readmission")).click();
        Thread.sleep(2000);

        //Select 2nd option from radio btn
        driver.findElement(By.id("radio_program_medicaid")).click();
        Thread.sleep(2000);

        //Select a date from the date picker
        //Can be done in 2 ways - using sendKeys or click - Here I used sendKeys method
        driver.findElement(By.id("txt_visit_date")).sendKeys("27/06/2024");

        Thread.sleep(2000);

        //Enter a comment
        driver.findElement(By.id("txt_comment")).sendKeys("Thank you!");
        Thread.sleep(1000);

        //Click book appointment btn
        driver.findElement(By.id("btn-book-appointment")).click();
        Thread.sleep(2000);

        //Check whether the appointment is correctly made
        actualOutput = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/p")).getText();
        expectedOutput = "Please be informed that your appointment has been booked as following:";

        if(actualOutput.equals(expectedOutput)){
            System.out.println("T06.Make appointment positive test : Pass");
        }else{
            System.out.println("T06.Make appointment positive test : Fail");
        }

    }

    //Test for Go to home page btn
    @Test(priority = 2)
    public void goToHomePageBtnTest() throws InterruptedException{
        //click go to home page btn
        driver.findElement(By.xpath("/html/body/section/div/div/div[7]/p/a")).click();

        //check whether it navigates to home page correctly
        expectedOutput ="Make Appointment";
        actualOutput =driver.findElement(By.xpath("/html/body/section/div/div/div/h2")).getText();

        if(actualOutput.equals(expectedOutput)){
            System.out.println("T07.Go to home page btn test : Pass");
        }else{
            System.out.println("T07.Go to home page btn test : Fail");
        }
    }

    //Test for History page navigation
    @Test(priority = 3)
    public void historyMenuToggleBtnTest() throws InterruptedException{
        driver.findElement(By.id("menu-toggle")).click();
        driver.findElement(By.xpath("/html/body/nav/ul/li[3]/a")).click();

        expectedOutput = "History";
        actualOutput = driver.findElement(By.xpath("/html/body/section/div/div[1]/div/h2")).getText();
        Thread.sleep(2000);

        if(actualOutput.equals(expectedOutput)){
            System.out.println("T08.History navigation Test : Pass");
        }else {
            System.out.println("T08.History navigation Test : Fail");
        }
    }

    //Test for Profile page navigation
    @Test(priority = 4)
    public void ProfileMenuToggleBtnTest() throws InterruptedException{
        driver.findElement(By.id("menu-toggle")).click();
        driver.findElement(By.xpath("/html/body/nav/ul/li[4]/a")).click();
        expectedOutput = "Profile";
        actualOutput = driver.findElement(By.xpath("/html/body/section/div/div/div/h2")).getText();
        Thread.sleep(2000);

        if(actualOutput.equals(expectedOutput)){
            System.out.println("T09.Profile page navigation Test : Pass");
        }else {
            System.out.println("T09.Profile Menu navigation Test : Fail");
        }
    }

    //Test for Logout
    @Test(priority = 5)
    public void LogoutTest() throws InterruptedException{
        driver.findElement(By.id("menu-toggle")).click();
        driver.findElement(By.xpath("/html/body/nav/ul/li[5]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("btn-make-appointment")).click();

        expectedOutput = "Login";
        actualOutput = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/h2")).getText();
        Thread.sleep(2000);

        if(actualOutput.equals(expectedOutput)){
            System.out.println("T10.Logout Test : Pass");
        }else {
            System.out.println("T10.Logout Test : Fail");
        }
        System.out.println("--------------------------------------------------------------------");
    }


    @AfterTest
    public void afterTestMethod() throws InterruptedException{
        driver.get(BaseURL);
        System.out.println("Make Appointment page test completed!");
    }

    //Supporting methods
    //Login to cura
    public void login() throws InterruptedException{
        //Enter correct un and password and click login button
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.id("btn-login")).click();
    }
}
