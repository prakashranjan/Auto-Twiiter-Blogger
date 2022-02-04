package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.Messages;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;


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
            WebDriverManager.chromedriver().setup();
//            ChromeDriverService service = new ChromeDriverService.Builder()
//                    .usingDriverExecutable(new File("C://Users//praka//Downloads//chromedriver_win32//chromedriver.exe"))
//                    .usingAnyFreePort()
//                    .build();
            ChromeOptions options = new ChromeOptions();


            options.merge(capabilities);
            options.addArguments("user-data-dir=C:/Users/praka/Documents/ChromeAuto/User Data");
            options.addArguments("profile-directory=Profile 3");
//            options.addExtensions (new File("C://Users//praka//Downloads//nbkknbagklenkcienihfapbfpjemnfoi.crx"));
//            options.addArguments("--start-fullscreen");
            driver = new ChromeDriver( options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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

        driver.get("https://twitter.com/"+strArg1);
        String TwitterTab = driver.getWindowHandle();
        ((JavascriptExecutor) driver).executeScript("window.open()");

//
        Thread.sleep(4000);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://www.blogger.com/go/signin");
        String bloggerTab = driver.getWindowHandle();

        //login into blogger
        List<WebElement> LoginProfile = driver.findElements(By.xpath("//div[@data-authuser='0']"));
        if (!(LoginProfile.isEmpty())) {
            for (WebElement p : LoginProfile) {
                p.click();
            }
        }



        String dbDate ="2022-02-02T00:00:00.00";

        LocalDateTime Dbdateobj = LocalDateTime.parse(dbDate);
        DateTimeFormatter myFormatDateObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String DbdateStrval = Dbdateobj.format(myFormatDateObj);


        Set<String> Labels= new LinkedHashSet<>();
        String CelebFname = "";
        String TweetTypeVal="Tweeted";


        Labels.add("twitter");
        Labels.add("tweet");
        Labels.add(strArg1);

        String LabelsString;




//        String BlogHtmlContent = "<h3 style=\"text-align: left;\">&nbsp;Sachin Tendulkar Tweeted:</h3><p>Happy birthday Kamblya!</p><p>The innumerable memories we have had both on &amp; off the field are something I shall cherish forever.</p><p>Looking forward to hear from you on how 50 feels…\uD83D\uDE1C\uD83D\uDE0B</p><p>God bless you!</p><div class=\"separator\" style=\"clear: both; text-align: center;\"><a href=\"https://blogger.googleusercontent.com/img/a/AVvXsEiET3B5aP24bhpZqzTLTIe76VZ8SXHs1oJ05bFlmF0Uq2k8uiFYm4Gx7IvGw5fnleaqpRB6aiNBZP-9-ZkD6G9KmB4GMYC3MfPxqWc96Vavj58AJ2bso0qGed1w7rrJIBKigkm2w-P2g9pmOmnFLa7fmyp_R99gDRLejo4gYId6SmKpxKPu0Bh5sJs7CA=s1280\" imageanchor=\"1\" style=\"margin-left: 1em; margin-right: 1em;\"><img border=\"0\" data-original-height=\"720\" data-original-width=\"1280\" height=\"225\" src=\"https://blogger.googleusercontent.com/img/a/AVvXsEiET3B5aP24bhpZqzTLTIe76VZ8SXHs1oJ05bFlmF0Uq2k8uiFYm4Gx7IvGw5fnleaqpRB6aiNBZP-9-ZkD6G9KmB4GMYC3MfPxqWc96Vavj58AJ2bso0qGed1w7rrJIBKigkm2w-P2g9pmOmnFLa7fmyp_R99gDRLejo4gYId6SmKpxKPu0Bh5sJs7CA=w400-h225\" width=\"400\" /></a></div><br /><h4 style=\"text-align: left;\">Read more on twitter:</h4><p><br /></p> <blockquote class=\"twitter-tweet\"><p lang=\"en\" dir=\"ltr\">Happy birthday Kamblya!<br>The innumerable memories we have had both on &amp; off the field are something I shall cherish forever.<br><br>Looking forward to hear from you on how 50 feels…\uD83D\uDE1C\uD83D\uDE0B<br><br>God bless you! <a href=\"https://t.co/Tnx2rwJARa\">pic.twitter.com/Tnx2rwJARa</a></p>&mdash; Sachin Tendulkar (@sachin_rt) <a href=\"https://twitter.com/sachin_rt/status/1483331983262744578?ref_src=twsrc%5Etfw\">January 18, 2022</a></blockquote> <script async src=\"https://platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>";

//
//
//        Thread.sleep(4000);





        Thread.sleep(2000);

        driver.switchTo().window(TwitterTab);

        Boolean LoginButtonPresent= driver.findElements(By.xpath("(//a[@href=\"/login\"])[1]")).size()>0;


        if(LoginButtonPresent)
        {
        driver.findElement(By.xpath("(//a[@href=\"/login\"])[1]")).click();
//        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@autocomplete=\"username\"]")).sendKeys("prakashranjansingh04@gmail.com");
        driver.findElement(By.xpath("//span[text()='Next']")).click();
        Thread.sleep(5000);
        List<WebElement> NumberCheckTab = driver.findElements(By.xpath("//input[@data-testId='ocfEnterTextTextInput']"));


        if (!(NumberCheckTab.isEmpty())) {
            for (WebElement m : NumberCheckTab) {
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
        driver.findElement(By.xpath("(//a[@href='/" + strArg1 + "'])[1]")).click();
//        Thread.sleep(3000);


    }
        driver.findElement(By.xpath("//span[text()='Tweets & replies']")).click();


        Thread.sleep(5000);
        List<WebElement> Tweets= driver.findElements(By.xpath("//div[contains(@aria-label,'Tweets') and contains(@aria-label,'Timeline')]/div/div"));


        for (WebElement x: Tweets) {
            driver.switchTo().window(TwitterTab);

            Boolean PinnedTweet=x.findElements(By.xpath(".//span[text()='Pinned Tweet']")).size()>0;
            if(PinnedTweet){
                continue;
            }

            String TweetDate="";
            WebElement TweetDatetime = x.findElement(By.xpath(".//time[@datetime]"));
            String TweetDatetimeValue = TweetDatetime.getAttribute("datetime");
            TweetDate=TweetDatetimeValue.substring(0,TweetDatetimeValue.length()-1);
            LocalDateTime TweetDateCurVal = LocalDateTime.parse(TweetDate);

            String tweetDateCurValueStr = TweetDateCurVal.format(myFormatDateObj);
            System.out.println("After formatting: " + tweetDateCurValueStr);
            if(tweetDateCurValueStr.compareToIgnoreCase(DbdateStrval)<0) {
                System.out.println("loop will break");
                break;

            }else{
                System.out.println(tweetDateCurValueStr+" date is good");
            }


            TweetDatetimeValue = TweetDatetimeValue.replace(':', '$');
            TweetDatetimeValue = TweetDatetimeValue.replace('.', '#');
            TweetDatetimeValue = TweetDatetimeValue.replace('/', '@');

            CelebFname=x.findElement(By.xpath(".//a[@href='/"+strArg1+"']/div/div/div/span/span")).getText();
            Labels.add(CelebFname);



            //div[@aria-roledescription='carousel']
            //img[@alt='Image' and contains(@src,"=large")]
            Boolean TweetImageYes= false;
            Boolean TweetVideoYes=false;
            if( x.findElements(By.xpath(".//img[@alt='Image']")).size()>0){
                x.findElement(By.xpath(".//img[@alt='Image']")).click();

                File UserTargetDir= new File(".//target//tweetImages//"+ strArg1);

                if(!(UserTargetDir.exists())) {
                    File targetFolderImage = new File(".//target//tweetImages//" + strArg1 + "//Image");
                    targetFolderImage.mkdirs();
                    File targetFolderVideo = new File(".//target//tweetImages//" + strArg1 + "//Video");
                    targetFolderVideo.mkdirs();
                }


                File OutputFolder = new File(".//target//tweetImages//" + strArg1+"//Image");
                Boolean noImageLeft=false;
                WebElement ImgModal = driver.findElement(By.xpath("//div[@aria-modal='true']"));
                while(!noImageLeft) {
//                Thread.sleep(10000);

//                driver.findElement(By.xpath("//img[@alt='Image' and contains(@src,\"=large\")]")).click();
                    Boolean ImgModalRightArrow = driver.findElements(By.xpath("//div[@data-testid='Carousel-NavRight']")).size() > 0;
                    if (ImgModalRightArrow) {
                        driver.findElement(By.xpath("//div[@data-testid='Carousel-NavRight']")).click();
                        Thread.sleep(3000);
                    } else {
                        noImageLeft = true;
                    }
                }



                List<WebElement> TweetFullImage = driver.findElements(By.xpath("//div[@data-testid='swipe-to-dismiss']/div/div/div/img[@alt='Image']"));
                for (WebElement carouselImg: TweetFullImage){
                    System.out.println("----->"+carouselImg.getAttribute("src"));
                    String logoSRC=carouselImg.getAttribute("src");
                    URL imageURL = new URL(logoSRC);
                    BufferedImage saveImage = ImageIO.read(imageURL);
                    LocalDateTime CurTimeimg = LocalDateTime.now();
                    String CurTimeImgVal = CurTimeimg.format(myFormatDateObj);



                    CurTimeImgVal= CurTimeImgVal.replace(" ", "");
                    CurTimeImgVal = CurTimeImgVal.replace(':', '$');
                    CurTimeImgVal = CurTimeImgVal.replace('.', '#');
                    CurTimeImgVal = CurTimeImgVal.replace('/', '@');


                    File outputFile = new File(".//target//tweetImages//" + strArg1 + "//Image//" + CurTimeImgVal + ".jpg");


                    ImageIO.write(saveImage, "jpg", outputFile);

                }



                WebElement ModalCloseButton = driver.findElement(By.xpath("//div[@aria-label='Close']"));
                ModalCloseButton.click();
            TweetImageYes=true;
            }



            List<WebElement> TweetText = x.findElements(By.xpath(".//div[contains(@id,'id__')]/span"));

            StringBuilder TweetHtmlContent= new StringBuilder();
            StringBuilder TweetRawContent= new StringBuilder();

            TweetHtmlContent.append("<h3 style=\"text-align: left;\">&nbsp;"+CelebFname+" "+TweetTypeVal+":</h3>");
            StringBuilder content= new StringBuilder();
            for (WebElement y: TweetText){

                        TweetRawContent.append(y.getText());
                        content.append("<p>"+y.getText()+"</p>");
//                String TweetFormatTextPart="<p>Happy birthday Kamblya!</p><p>The innumerable memories we have had both on &amp; off the field are something I shall cherish forever.</p><p>Looking forward to hear from you on how 50 feels…\uD83D\uDE1C\uD83D\uDE0B</p><p>God bless you!</p><br /><h4 style=\"text-align: left;\">Read more on twitter:</h4><p><br /></p>";
//

            }

            TweetHtmlContent.append(content);
//            System.out.println("0---------"+TweetHtmlContent);
            Pattern replace = Pattern.compile("\\n");
            Matcher matcher = replace.matcher(TweetRawContent);
            String TweetRawContentValue = matcher.replaceAll("");

            byte[] bytes = TweetRawContentValue.getBytes(StandardCharsets.UTF_8);

            String utf8EncodedTweetRawContentvalue = new String(bytes, StandardCharsets.UTF_8);

            int TweetLength = utf8EncodedTweetRawContentvalue.length();
            if(TweetLength>50){

                utf8EncodedTweetRawContentvalue=utf8EncodedTweetRawContentvalue.substring(0,50);
            }

            System.out.println(utf8EncodedTweetRawContentvalue);





            driver.switchTo().window(bloggerTab);
            driver.navigate().refresh();
            driver.findElement(By.xpath("//span[text()='New Post']")).click();
            WebElement BlogTitleInput=driver.findElement(By.xpath("(//input[@aria-label='Title'])[1]"));
//            BlogTitleInput.click();
            actions.moveToElement(BlogTitleInput).click().build().perform();

            utf8EncodedTweetRawContentvalue= utf8EncodedTweetRawContentvalue.replace("\'","\\'");


            ((JavascriptExecutor) driver).executeScript("arguments[0].value='"+utf8EncodedTweetRawContentvalue+"...-"+CelebFname+" "+TweetTypeVal+"';",BlogTitleInput );
            Thread.sleep(3000);

//            driver.findElement(By.xpath("(//input[@aria-label='Title'])[1]")).sendKeys(utf8EncodedTweetRawContentvalue+"...-"+CelebFname+" "+TweetTypeVal);


            String TweetHtmlContentValue= TweetHtmlContent.toString();
            StringSelection selection = new StringSelection(TweetHtmlContentValue);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection,selection);


            WebElement BloggerPostFrame=driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/c-wiz/div/div[2]/div/div/div[3]/span/div/div[2]/div[2]"));

            // code to click in center of screen

            Dimension i = driver.manage().window().getSize();
            System.out.println("Dimension x and y :"+i.getWidth()+" "+i.getHeight());
            //3. Get the height and width of the screen
            int xwidth = (i.getWidth()/2);
            int yheight = (i.getHeight()/2);

            robot.mouseMove(xwidth,yheight);

            //Clicks Left mouse button

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);



            actions.moveToElement(BloggerPostFrame).click().build().perform();

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            Thread.sleep(2000);

            if(TweetImageYes) {
                driver.findElement(By.xpath("(//div[@aria-label='Insert image'])[3]")).click();
                //image upload code
                Thread.sleep(4000);
                driver.findElement(By.xpath("//div[contains(@data-command,'imageUploadPicker')]/div[contains(text(),\"Upload from computer\")]")).click();
                Thread.sleep(4000);
                WebElement FrameUploadImage = driver.findElement(By.xpath("//iframe[@allow='camera']"));
                driver.switchTo().frame(FrameUploadImage);
                File CelebImageFolder = new File(".//target//tweetImages//" + strArg1+"//Image");
                File[] files = CelebImageFolder.listFiles();
                if(files!=null) { //some JVMs r
                    // return null for empty dirs
                    for(File f: files) {
                        WebElement ChooseFileBtn= driver.findElement(By.xpath("//div[contains(text(),\'Choose files\')]"));

                        ChooseFileBtn.click();



                        Thread.sleep(4000);
                        driver.findElement(By.xpath("//div/input[@type='file']")).sendKeys(f.getAbsolutePath());

                        Thread.sleep(8000);

                        robot.keyPress(KeyEvent.VK_ESCAPE);
                        robot.keyRelease(KeyEvent.VK_ESCAPE);

                    }
                }

                WebDriverWait wait = new WebDriverWait(driver,60);
                WebElement SelectBtn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button' and text()='Select']")));
                SelectBtn.click();

                Thread.sleep(5000);
                driver.switchTo().parentFrame();
                driver.findElement(By.xpath("//label[text()='Large']")).click();
                driver.findElement(By.xpath("//label[contains(text(),'Center')]")).click();

                driver.findElement(By.xpath("(//span[text()='OK'])[2]")).click();
                Thread.sleep(4000);


                deleteFolder(CelebImageFolder);
            }

            StringBuilder EmbeddedTweetContent= new StringBuilder("<h4 style=\"text-align: left;\">Read more on twitter:</h4><p><br /></p>");

            String EmbeddedTweetContentValue= EmbeddedTweetContent.toString();
            selection = new StringSelection(EmbeddedTweetContentValue);
            clipboard.setContents(selection,selection);

            actions.moveToElement(BloggerPostFrame).click().build().perform();

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            Thread.sleep(2000);


            driver.switchTo().window(TwitterTab);
            x.findElement(By.xpath(".//div[@aria-label='More']")).click();
            driver.findElement(By.xpath("//span[text()='Embed Tweet']")).click();
            Thread.sleep(5000);
            Set<String> AllTabs=driver.getWindowHandles();
            String EmbedTwitterTab;
            for (String tabName:AllTabs){
                if(!(tabName.equals(TwitterTab)) && !(tabName.equals(bloggerTab)) ){
                    driver.switchTo().window(tabName);
                    EmbedTwitterTab=driver.getWindowHandle();


                }

            }

            driver.findElement(By.xpath("//button[text()='Copy Code']")).click();
            Thread.sleep(2000);
            driver.close();
            driver.switchTo().window(bloggerTab);
            actions.moveToElement(BloggerPostFrame).click().build().perform();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            LabelsString= String.join(",",Labels);
            driver.findElement(By.xpath("(//textarea[contains(@aria-label,'labels')])[1]")).click();
            driver.findElement(By.xpath("(//textarea[contains(@aria-label,'labels')])[1]")).sendKeys(LabelsString);

            driver.findElement(By.xpath("(//div[text()='Publish'])[1]")).click();
            driver.findElement(By.xpath("(//span[text()='Confirm'])[2]")).click();
            Boolean BlogHomePageManageButtonPresence=driver.findElements(By.xpath("(//span[text()='Manage'])[2]")).size()>0;
            if(BlogHomePageManageButtonPresence){

                //do add db stuff

            }else{
                System.exit(0);
            }



        }
        driver.switchTo().window(TwitterTab);
        driver.findElement(By.xpath("//div[@aria-label=\"Account menu\"]")).click();
//        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[text()='Log out ']")).click();

        driver.close();
        driver.switchTo().window(bloggerTab);
        driver.close();

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
//        driver.close();
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

    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) { //some JVMs return null for empty dirs
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
    }


}

