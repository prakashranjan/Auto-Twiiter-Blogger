package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.Messages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;


import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.awt.Robot;



public class stepDefinition {

//
    WebDriver driver;
    Actions actions;
    Robot robot;


    @Given("^\"([^\"]*)\" browser is opened$")
    public void something_browser_is_opened(String strArg1) throws Throwable {
        if(strArg1.equals("Chrome")) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("resolution", "1920x1080");

            DesiredCapabilities capabilities = DesiredCapabilities.chrome();

            ChromeDriverService service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File("C://Users//praka//Downloads//chromedriver_win32//chromedriver.exe"))
                    .usingAnyFreePort()
                    .build();
            ChromeOptions options = new ChromeOptions();
            options.merge(capabilities);
            options.addExtensions (new File("C://Users//praka//Downloads//nbkknbagklenkcienihfapbfpjemnfoi.crx"));
            driver = new ChromeDriver(service, options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            actions = new Actions(driver);
            robot=new Robot();



//        System.setProperty("webdriver.chrome.driver", "C://Users//praka//Downloads//chromedriver_win32//chromedriver.exe");
////        driver = new ChromeDriver(caps);}
        }
        else if(strArg1.equals("Firefox")){
            System.setProperty("webdriver.gecko.driver", "C://Users//praka//Downloads//chromedriver_win32//geckodriver.exe");
            driver = new FirefoxDriver();
        }
        //throw new PendingException();
    }


    @Given("^Celeb twitter page is of \"([^\"]*)\"$")
    public void celeb_twitter_page_is_of_something(String strArg1) throws Throwable {

        driver.get("https://twitter.com/sachin_rt");
        String TwitterTab=driver.getWindowHandle();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);

        // CTRL+T is now pressed

        robot.keyRelease(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(2));
        driver.get("https://www.blogger.com/go/signin");
        String bloggerTab=driver.getWindowHandle();

        //login into blogger


        driver.switchTo().window(TwitterTab);




        driver.findElement(By.xpath("(//a[@href=\"/login\"])[1]")).click();
//        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@autocomplete=\"username\"]")).sendKeys("prakashranjansingh04@gmail.com");
        driver.findElement(By.xpath("//span[text()='Next']")).click();
        Thread.sleep(5000);
        List<WebElement> NumberCheckTab= driver.findElements(By.xpath("//input[@data-testId='ocfEnterTextTextInput']"));


            if (!(NumberCheckTab.isEmpty())) {
                for (WebElement m: NumberCheckTab) {
                m.sendKeys("9717065383");
                driver.findElement(By.xpath("//span[text()='Next']")).click();
            }
        }
//        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Ddd@7856");
        driver.findElement(By.xpath("//span[text()='Log in']")).click();
//        Thread.sleep(3000);
        WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder='Search Twitter']"));
        searchBox.sendKeys(strArg1);
        searchBox.sendKeys(Keys.RETURN);
//        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()='People']")).click();
//        Thread.sleep(3000);
        driver.findElement(By.xpath("(//a[@href='/"+strArg1+"'])[1]")).click();
//        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()='Tweets & replies']")).click();


        Thread.sleep(10000);
        List<WebElement> Tweets= driver.findElements(By.xpath("//div[contains(@aria-label,'Tweets') and contains(@aria-label,'Timeline')]/div/div"));


        for (WebElement x: Tweets) {
            WebElement TweetDatetime = x.findElement(By.xpath(".//time[@datetime]"));
            String TweetDatetimeValue = TweetDatetime.getAttribute("datetime");
            TweetDatetimeValue = TweetDatetimeValue.replace(':', '$');
            TweetDatetimeValue = TweetDatetimeValue.replace('.', '#');
            TweetDatetimeValue = TweetDatetimeValue.replace('/', '@');


            List<WebElement> TweetImageThumbnails = x.findElements(By.xpath(".//img[@alt='Image']"));

            if(!(TweetImageThumbnails.isEmpty())){
            for (WebElement l :TweetImageThumbnails) {

                l.click();

                WebElement ImgModal = driver.findElement(By.xpath("//div[@aria-modal='true']"));
                WebElement TweetFullImage = ImgModal.findElement(By.xpath(".//img[@alt='Image']"));
                String logoSRC = TweetFullImage.getAttribute("src");

                URL imageURL = new URL(logoSRC);
                BufferedImage saveImage = ImageIO.read(imageURL);
                File outputFile = new File(".//target//tweetImages//" + strArg1 + "//" + TweetDatetimeValue + ".jpg");


                ImageIO.write(saveImage, "jpg", outputFile);

                WebElement ModalCloseButton = driver.findElement(By.xpath("//div[@aria-label='Close']"));
                ModalCloseButton.click();

                Thread.sleep(3000);
            }}

            List<WebElement> TweetText = x.findElements(By.xpath(".//div[contains(@id,'id__')]/span"));



            for (WebElement y: TweetText){
                String content=y.getText();
                System.out.println(content);

            }

            System.out.println("-------------------------------------------------------------------------------------------------------------------");
        }

        driver.findElement(By.xpath("//div[@aria-label=\"Account menu\"]")).click();
//        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[text()='Log out ']")).click();


        //throw new PendingException();
    }

    @Given("^User is on netbanking login page$")
    public void user_is_on_netbanking_login_page() throws Throwable {
        System.out.println("User is on netbanking login page success");

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement table= driver.findElement(By.name("courses"));

        int colSize=table.findElements(By.tagName("th")).size();
        System.out.println(colSize);
        int rowSize =table.findElements(By.xpath(".//tr")).size();

        System.out.println(rowSize);
        String rowPrice;
        int max=0;
        int j=0;
        for(int i=2;i<rowSize;i++){

            rowPrice= table.findElement(By.xpath("//tbody/tr["+i+"]/td[3]")).getText();

            j=Integer.parseInt(rowPrice);
            if (j>max){
//            System.out.println(max);
                max=j;}







        }
        System.out.println(max);

        //throw new PendingException();
    }

    @When("^User log in into application using \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_log_in_into_application_using_something_and_something(String strArg1, String strArg2) throws Throwable {
        System.out.println(strArg1);
        System.out.println(strArg2);
        //throw new PendingException();
    }

    @Then("^Homepage is populated$")
    public void homepage_is_populated() throws Throwable {
       //throw new PendingException();
    }

    @And("^Cards displayed \"([^\"]*)\"$")
    public void cards_displayed_something(String strArg1) throws Throwable {
        //throw new PendingException();
        System.out.println(strArg1);
        driver.close();
    }
    @Then("^User sign up with following details$")
    public void user_sign_up_with_following_details(DataTable data) throws Throwable {
        List <List<String>> obj = data.asLists();
        System.out.println(obj.get(0).get(0));
        System.out.println(obj.get(0).get(1));
        System.out.println(obj.get(0).get(2));
        System.out.println(obj.get(0).get(3));
        System.out.println(obj.get(0).get(4));
        System.out.println(obj.get(0).get(5));

        //throw new PendingException();
    }

    @When("^User log in into application with (.+) and (.+)$")
    public void user_log_in_into_application_with_and(String username, String password) throws Throwable {
        System.out.println(username);
        System.out.println(password);

        //throw new PendingException();
    }
    @Given("^browser is opened$")
    public void browser_is_opened() throws Throwable {
        System.out.println("browser is opened");
        //throw new PendingException();
    }

    @When("^browser cache clean triggered$")
    public void browser_cache_clean_triggered() throws Throwable {
        System.out.println("browser cache clean triggered");
        //throw new PendingException();
    }

    @Then("^browser started as fresh$")
    public void browser_started_as_fresh() throws Throwable {
        System.out.println("browser started as fresh");
        //throw new PendingException();
    }

}
