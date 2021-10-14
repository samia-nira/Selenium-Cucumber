import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class StepDefinitions {
    public WebDriver driver;
    WebDriverWait wait;
    int age = 0;
    String age1 = String.valueOf(age);
    @Given("^User visits a visual discovery engine site$")
        public void user_visits_a_visual_discovery_engine_site() throws Exception {
            System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
            FirefoxOptions ops = new FirefoxOptions();
            ops.addArguments("--headed"); //uncomment if you want to run in headless mode
            driver = new FirefoxDriver(ops);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get("https://www.pinterest.com/");
        }

    @When("^User enters invalid email \\\"([^\\\"]*)\\\",valid password  \\\"([^\\\"]*)\\\" and age (.*) credentials$")
        public void user_enters_invalid_email_and_invalid_password_credentials(String email, String password, String age1) throws Exception {
            wait = new WebDriverWait(driver, 40);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/div[@id='__PWS_ROOT__']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/button[1]")));
            WebElement btnSignup = driver.findElement(By.xpath("//body/div[@id='__PWS_ROOT__']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/button[1]"));
            btnSignup.click();
            Thread.sleep(1000);
            WebElement txtEmail = driver.findElement(By.id("email"));
            txtEmail.sendKeys(email);
            WebElement txtPassword = driver.findElement(By.id("password"));
            txtPassword.sendKeys(password);
            WebElement age  = driver.findElement(By.xpath("//input[@id='age']"));
            age.sendKeys(age1);
            Thread.sleep(1000);
            WebElement btnSubmitLogin = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/form[1]/div[5]"));
            btnSubmitLogin.click();
        }
    @Then("^User cannot signed up$")
        public void user_cannot_logged_in_successfully() throws Exception {
                wait = new WebDriverWait(driver, 40);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/form[1]/div[5]/button[1]")));
                String text = driver.findElement(By.xpath("//div[@class='tBJ dyH iFc _yT lZJ zDA IZT swG']")).getText();
                Assert.assertTrue(text.contains("Your password is too short! You need 6+ characters."));

            }
            @After
            public void closeBrowser () {
            driver.quit();
            }
}

