package stepDefinitions;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.Messages;
import io.github.bonigarcia.wdm.WebDriverManager;

import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.apache.commons.io.FileUtils;
import org.bson.BsonDocument;
import org.bson.Document;
import org.eclipse.jetty.util.DateCache;
import org.jsoup.Jsoup;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
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
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FilenameFilter;
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
            options.addArguments("user-data-dir=C:/Users/praka/Documents/ChromeAuto/User Data Dev");
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


    @Given("^Celeb twitter page is open$")
    public void celeb_twitter_page_is_open() throws Throwable {
        int blogPostCount=0;
        int instaPostCount=0;

        Boolean BloggerNotWorking= false;
        Boolean StopInstaImage =false;
        File CelebImageFolder = new File(".//target//tweetImages//General//Image");
        File CelebVideoFolder = new File(".//target//tweetImages//General//Video");
        ConnectionString connectionString4 = new ConnectionString("mongodb+srv://Tracker2:Ddd7856@cluster0.3jatg.mongodb.net/CelebTracker?retryWrites=true&w=majority");
        MongoClientSettings settings4 = MongoClientSettings.builder()
                .applyConnectionString(connectionString4)
                .build();
        MongoClient mongoClient4 = MongoClients.create(settings4);
        MongoDatabase database4 = mongoClient4.getDatabase("CelebTracker");
        MongoCollection<Document> collection4 = database4.getCollection("Triggers");
        BsonDocument filter4 = new BsonDocument();

        collection4.countDocuments(filter4);
//        System.out.println("Connected");

        Iterator<Document> itr4 = collection4.find().iterator();
        mongoClient4.close();

        while (itr4.hasNext()) {
//            stopper=false;
            Document dcur4 = itr4.next();
//
            if((dcur4.get("trigger").toString()).equals("BloggerNotWorking")){
                BloggerNotWorking=(Boolean)dcur4.get("value");

            }else if((dcur4.get("trigger").toString()).equals("StopInstaImage")) {
                StopInstaImage = (Boolean) dcur4.get("value");
            }
            }



        ConnectionString connectionString = new ConnectionString("mongodb+srv://Tracker2:Ddd7856@cluster0.3jatg.mongodb.net/CelebTracker?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("CelebTracker");
        MongoCollection<Document> collection = database.getCollection("CelebData");
        BsonDocument filter = new BsonDocument();
        
        collection.countDocuments(filter);
//        System.out.println("Connected");

        Iterator<Document> itr = collection.find().iterator();
        mongoClient.close();

        String bloggerTab="";
        String TwitterTab="";
        String InstagramTab="";

        driver.get("https://twitter.com/");
        TwitterTab = driver.getWindowHandle();

        ((JavascriptExecutor) driver).executeScript("window.open()");

//            Thread.sleep(4000);
        ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs1.get(1));
        driver.get("https://www.instagram.com/");
        InstagramTab = driver.getWindowHandle();


        ((JavascriptExecutor) driver).executeScript("window.open()");

//            Thread.sleep(4000);
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(2));
        driver.get("https://www.blogger.com/");
        bloggerTab = driver.getWindowHandle();


        Boolean stopper=true;
        while (itr.hasNext() && stopper) {
//            stopper=false;
            Document dcur = itr.next();
//            String strArg1 = dcur.get("TwitterUserId").toString();
//            String dbDate = dcur.get("LastTweet").toString();

            String strArg1 = dcur.get("TwitterUserId").toString();
            String dbDate = dcur.get("LastTweet").toString();
            Boolean Notification = (Boolean) dcur.get("Notification");

            if(!Notification){
                break;
            }


            //db connect will be made and loop will execute

            driver.switchTo().window(TwitterTab);
            driver.get("https://twitter.com/" + strArg1+"/with_replies");
            TwitterTab = driver.getWindowHandle();
//            ((JavascriptExecutor) driver).executeScript("window.open()");

//
//            Thread.sleep(4000);
//            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//            driver.switchTo().window(tabs.get(1));
//            driver.get("https://www.blogger.com/go/signin");
//            bloggerTab = driver.getWindowHandle();

            driver.switchTo().window(bloggerTab);
            implicitWaitOff();
            //login into blogger
            List<WebElement> LoginProfile = driver.findElements(By.xpath("//div[@data-authuser='0']"));
            if (!(LoginProfile.isEmpty())) {
                for (WebElement p : LoginProfile) {
                    p.click();
                }
            }
            implicitWaitOn();

//        String dbDate = "2022-02-05T00:00:00.00";


            DateTimeFormatter myFormatDateObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            LocalDateTime Dbdateobj = LocalDateTime.parse(dbDate);
            String DbdateStrval = Dbdateobj.format(myFormatDateObj);


            Set<String> Labels = new LinkedHashSet<>();
            String CelebFname = "";
            String TweetTypeVal = "Tweeted";



            Labels.add("tweet");
            Labels.add("Sports");




            Labels.add(strArg1);

            String LabelsString;


//        String BlogHtmlContent = "<h3 style=\"text-align: left;\">&nbsp;Sachin Tendulkar Tweeted:</h3><p>Happy birthday Kamblya!</p><p>The innumerable memories we have had both on &amp; off the field are something I shall cherish forever.</p><p>Looking forward to hear from you on how 50 feels…\uD83D\uDE1C\uD83D\uDE0B</p><p>God bless you!</p><div class=\"separator\" style=\"clear: both; text-align: center;\"><a href=\"https://blogger.googleusercontent.com/img/a/AVvXsEiET3B5aP24bhpZqzTLTIe76VZ8SXHs1oJ05bFlmF0Uq2k8uiFYm4Gx7IvGw5fnleaqpRB6aiNBZP-9-ZkD6G9KmB4GMYC3MfPxqWc96Vavj58AJ2bso0qGed1w7rrJIBKigkm2w-P2g9pmOmnFLa7fmyp_R99gDRLejo4gYId6SmKpxKPu0Bh5sJs7CA=s1280\" imageanchor=\"1\" style=\"margin-left: 1em; margin-right: 1em;\"><img border=\"0\" data-original-height=\"720\" data-original-width=\"1280\" height=\"225\" src=\"https://blogger.googleusercontent.com/img/a/AVvXsEiET3B5aP24bhpZqzTLTIe76VZ8SXHs1oJ05bFlmF0Uq2k8uiFYm4Gx7IvGw5fnleaqpRB6aiNBZP-9-ZkD6G9KmB4GMYC3MfPxqWc96Vavj58AJ2bso0qGed1w7rrJIBKigkm2w-P2g9pmOmnFLa7fmyp_R99gDRLejo4gYId6SmKpxKPu0Bh5sJs7CA=w400-h225\" width=\"400\" /></a></div><br /><h4 style=\"text-align: left;\">Read more on twitter:</h4><p><br /></p> <blockquote class=\"twitter-tweet\"><p lang=\"en\" dir=\"ltr\">Happy birthday Kamblya!<br>The innumerable memories we have had both on &amp; off the field are something I shall cherish forever.<br><br>Looking forward to hear from you on how 50 feels…\uD83D\uDE1C\uD83D\uDE0B<br><br>God bless you! <a href=\"https://t.co/Tnx2rwJARa\">pic.twitter.com/Tnx2rwJARa</a></p>&mdash; Sachin Tendulkar (@sachin_rt) <a href=\"https://twitter.com/sachin_rt/status/1483331983262744578?ref_src=twsrc%5Etfw\">January 18, 2022</a></blockquote> <script async src=\"https://platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>";

//
//
//        Thread.sleep(4000);


            Thread.sleep(1000);

            driver.switchTo().window(TwitterTab);
            implicitWaitOff();

            Boolean LoginButtonPresent = driver.findElements(By.xpath("(//a[@href=\"/login\"])[1]")).size() > 0;

            implicitWaitOn();
            WebElement WithRepliesBtn = driver.findElement(By.xpath("//span[text()='Tweets & replies']"));
            if (LoginButtonPresent) {
                driver.findElement(By.xpath("(//a[@href=\"/login\"])[1]")).click();
//        Thread.sleep(3000);
                driver.findElement(By.xpath("//input[@autocomplete=\"username\"]")).sendKeys("prakashranjansingh04@gmail.com");
                driver.findElement(By.xpath("//span[text()='Next']")).click();
                Thread.sleep(3000);
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
//            driver.findElement(By.xpath("//span[text()='Tweets & replies']")).click();


            WebElement FourthTweet= driver.findElement(By.xpath("(//div[contains(@aria-label,'Tweets') and contains(@aria-label,'Timeline')]/div/div)[4]"));
            Thread.sleep(3000);
            List<WebElement> Tweets = driver.findElements(By.xpath("//div[contains(@aria-label,'Tweets') and contains(@aria-label,'Timeline')]/div/div"));

            int TweetXpathCount=0;
            for (WebElement x : Tweets) {
                TweetXpathCount+=1;
                String FullTweetDivText="";
                driver.switchTo().window(TwitterTab);
                implicitWaitOff();
                Boolean PinnedTweet = x.findElements(By.xpath(".//span[text()='Pinned Tweet']")).size() > 0;
                if (PinnedTweet) {
                    continue;
                }

                Boolean ReTweet = x.findElements(By.xpath(".//span[text()=\" Retweeted\"]")).size() > 0;
                if (ReTweet) {
                    CelebFname = x.findElement(By.xpath(".//span[@data-testId='socialContext']/span/span")).getText();

                } else {
                    CelebFname = x.findElement(By.xpath(".//a[@href='/" + strArg1 + "']/div/div/span/span")).getText();

                }
                Labels.add(CelebFname);
                implicitWaitOn();

                String TweetDate = "";
                WebElement TweetDatetime = x.findElement(By.xpath(".//time[@datetime]"));
                String TweetDatetimeValue = TweetDatetime.getAttribute("datetime");
                TweetDate = TweetDatetimeValue.substring(0, TweetDatetimeValue.length() - 1);
                LocalDateTime TweetDateCurVal = LocalDateTime.parse(TweetDate);
                TweetDateCurVal = TweetDateCurVal.plusHours(5);
                TweetDateCurVal = TweetDateCurVal.plusMinutes(30);
                String tweetDateCurValueStr = TweetDateCurVal.format(myFormatDateObj);

//                System.out.println("After formatting: " + tweetDateCurValueStr);
                System.out.println(DbdateStrval + " and " + tweetDateCurValueStr);
                if (DbdateStrval.compareToIgnoreCase(tweetDateCurValueStr) > 0) {
                    System.out.println("loop will break");
                    break;

                } else {
                    System.out.println(tweetDateCurValueStr + " date is good");
                }


                TweetDatetimeValue = TweetDatetimeValue.replace(':', '$');
                TweetDatetimeValue = TweetDatetimeValue.replace('.', '#');
                TweetDatetimeValue = TweetDatetimeValue.replace('/', '@');


                //div[@aria-roledescription='carousel']
                //img[@alt='Image' and contains(@src,"=large")]
                Boolean TweetImageYes = false;
                Boolean TweetVideoYes = false;
                StringSelection selection;
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                implicitWaitOff();

                if ((x.findElements(By.xpath(".//img[@alt='Image']")).size() > 0) && (!StopInstaImage)){
                    implicitWaitOn();
                    x.findElement(By.xpath(".//img[@alt='Image']")).click();

                    File UserTargetDir = new File(".//target//tweetImages//General//Image");

                    if (!(UserTargetDir.exists())) {
                        File targetFolderImage = new File(".//target//tweetImages//General//Image");
                        targetFolderImage.mkdirs();
                        File targetFolderVideo = new File(".//target//tweetImages//General//Video");
                        targetFolderVideo.mkdirs();
                    }


                    File OutputFolder = new File(".//target//tweetImages//General//Image");
                    Boolean noImageLeft = false;
                    WebElement ImgModal = driver.findElement(By.xpath("//div[@aria-modal='true']"));
                    WebElement ModalCloseButton = null;
                    while (!noImageLeft) {
//                Thread.sleep(10000);

//                driver.findElement(By.xpath("//img[@alt='Image' and contains(@src,\"=large\")]")).click();
                        ModalCloseButton = driver.findElement(By.xpath("//div[@aria-label='Close']"));
                        Thread.sleep(2000);
                        implicitWaitOff();
//                        Boolean ImgModalRightArrow = driver.findElements(By.xpath("//div[@data-testid='Carousel-NavRight']")).size() > 0;
                        Boolean ImgModalRightArrow= false;
                        try {
                            ImgModalRightArrow = driver.findElement(By.xpath("//div[@data-testid='Carousel-NavRight']")).isDisplayed();
                        }
                        catch(Exception e){
                            System.out.println("no right arrow");
                        }
                            if (ImgModalRightArrow) {
                            Thread.sleep(2000);
                            WebDriverWait waitTime = new WebDriverWait(driver, 5);
                            WebElement rightArrowSymbol = waitTime.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-testid='Carousel-NavRight']")));
                            rightArrowSymbol.click();
                            Thread.sleep(2000);
                        } else {
                            noImageLeft = true;
                        }
                    }
                    implicitWaitOn();
                    deleteFolder(CelebImageFolder);
                    List<WebElement> TweetFullImage = driver.findElements(By.xpath("//div[@data-testid='swipe-to-dismiss']/div/div/div/img[@alt='Image']"));
                    for (WebElement carouselImg : TweetFullImage) {
//                        System.out.println("----->" + carouselImg.getAttribute("src"));
                        String logoSRC = carouselImg.getAttribute("src");
                        URL imageURL = new URL(logoSRC);
                        BufferedImage saveImage = ImageIO.read(imageURL);
                        LocalDateTime CurTimeimg = LocalDateTime.now();
                        String CurTimeImgVal = CurTimeimg.format(myFormatDateObj);


                        CurTimeImgVal = CurTimeImgVal.replace(" ", "");
                        CurTimeImgVal = CurTimeImgVal.replace(':', '$');
                        CurTimeImgVal = CurTimeImgVal.replace('.', '#');
                        CurTimeImgVal = CurTimeImgVal.replace('/', '@');


                        File outputFile = new File(".//target//tweetImages//General//Image//" + CurTimeImgVal + ".jpg");


                        ImageIO.write(saveImage, "jpg", outputFile);

                    }


                    ModalCloseButton.click();
                    TweetImageYes = true;
                }
                else if(driver.findElements(By.xpath("(//div[contains(@aria-label,'Tweets') and contains(@aria-label,'Timeline')]/div/div)["+TweetXpathCount+"]/*//a[text()='Download']")).size() > 0){
                    String DownloadVideoTwitterTab="";
                    try {
                           deleteFolder(CelebVideoFolder);
//                        driver.findElement(By.xpath(".//a[text()='Download']")).click();
                        driver.findElement(By.xpath("(//div[contains(@aria-label,'Tweets') and contains(@aria-label,'Timeline')]/div/div)["+TweetXpathCount+"]/*//a[text()='Download']")).click();
                        Thread.sleep(3000);
                        Set<String> AllTabsNow = driver.getWindowHandles();

                        for (String tabName : AllTabsNow) {
                            if (!(tabName.equals(TwitterTab)) && !(tabName.equals(bloggerTab)) && !(tabName.equals(InstagramTab))) {
                                driver.switchTo().window(tabName);
                                DownloadVideoTwitterTab = driver.getWindowHandle();


                            }

                        }

                        WebElement VideoDownloadButton = driver.findElement(By.xpath("(//a[contains(text(),'Download') and contains(text(),'[2]')])[1]"));

                        String vidSRC = VideoDownloadButton.getAttribute("href");
                        URL videoURL = new URL(vidSRC);


                        File VideoOutputFile = new File(".//target//tweetImages//General//Video//videoContent.mp4");


                            FileUtils.copyURLToFile(videoURL, VideoOutputFile);




                        if(driver.findElements(By.xpath("//div[@aria-label='Close ad']")).size() >0){
                          driver.findElement(By.xpath("//div[@aria-label='Close ad']")).click();

                        }

                        Thread.sleep(2000);
                        driver.close();

                        TweetVideoYes = true;
                        System.out.println("Video Download passed");
                    }
                    catch (Exception e){
                        Boolean sssVideoTabBool=false;

                        if (DownloadVideoTwitterTab.equals(driver.getWindowHandle())){
                         sssVideoTabBool=true;
                        }
                        if(sssVideoTabBool){
                            driver.close();
                        }

                        TweetVideoYes=false;
                        System.out.println("Video Download failed");

                    }

                }
                else {

                    System.out.println("--------------No image found");
                    continue;

                }

                implicitWaitOn();
                driver.switchTo().window(TwitterTab);
                List<WebElement> TweetText = x.findElements(By.xpath(".//div[contains(@id,'id__')]/span"));

                StringBuilder TweetHtmlContent = new StringBuilder();
                StringBuilder TweetRawContent = new StringBuilder();
                String HeadingLine = "<h3 style=\"text-align: left;\">&nbsp;" + CelebFname + ":</h3>";
                TweetHtmlContent.append(HeadingLine);
                StringBuilder content = new StringBuilder();
                for (WebElement y : TweetText) {

                    TweetRawContent.append(y.getText());
                    content.append("<p>" + y.getText() + "</p>");
//                String TweetFormatTextPart="<p>Happy birthday Kamblya!</p><p>The innumerable memories we have had both on &amp; off the field are something I shall cherish forever.</p><p>Looking forward to hear from you on how 50 feels…\uD83D\uDE1C\uD83D\uDE0B</p><p>God bless you!</p><br /><h4 style=\"text-align: left;\">Read more on twitter:</h4><p><br /></p>";
//

                }



                try {
                    implicitWaitOff();
                    WebElement FullTweetDiv = x.findElement(By.xpath(".//div/div[@lang]"));
                    FullTweetDivText = html2text((String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;", FullTweetDiv));
                    System.out.println(FullTweetDivText);
                }
                catch(Exception e){
                    System.out.println("Full tweet text not working");
                }

                TweetHtmlContent.append(content);
//            System.out.println("0---------"+TweetHtmlContent);
                Pattern replace = Pattern.compile("\\n");
                Matcher matcher = replace.matcher(TweetRawContent);
                String TweetRawContentValue = matcher.replaceAll("");

                byte[] bytes = TweetRawContentValue.getBytes(StandardCharsets.UTF_8);

                String utf8EncodedTweetRawContentvalue = new String(bytes, StandardCharsets.UTF_8);

                int TweetLength = utf8EncodedTweetRawContentvalue.length();
                if (TweetLength > 50) {

                    utf8EncodedTweetRawContentvalue = utf8EncodedTweetRawContentvalue.substring(0, 50);
                }

//                System.out.println(utf8EncodedTweetRawContentvalue);


                driver.switchTo().window(bloggerTab);
                driver.navigate().refresh();
                implicitWaitOn();
                if(!BloggerNotWorking) {
                    driver.findElement(By.xpath("//span[text()='New Post']")).click();
                    try {
                        WebElement BlogTitleInput = driver.findElement(By.xpath("//input[@aria-label='Title']"));
                        BlogTitleInput.click();
                        actions.moveToElement(BlogTitleInput).click().build().perform();
                    } catch (NoSuchElementException e) {
                        BloggerNotWorking = true;
                    }
                }
                utf8EncodedTweetRawContentvalue = utf8EncodedTweetRawContentvalue.replace("'", "\'");

                String TweetHtmlContentValue = TweetHtmlContent.toString();

//            ((JavascriptExecutor) driver).executeScript("arguments[0].value='"+utf8EncodedTweetRawContentvalue+"...-"+CelebFname+" "+TweetTypeVal+"';",BlogTitleInput );
                if (!BloggerNotWorking) {
                    try {
                        driver.findElement(By.xpath("(//input[@aria-label='Title'])[1]")).sendKeys(utf8EncodedTweetRawContentvalue + "...-" + CelebFname + " " + TweetTypeVal);
                        Thread.sleep(3000);
                    } catch (WebDriverException e) {
                        System.out.println("BMP error");

                    }


                    selection = new StringSelection(TweetHtmlContentValue);

                    clipboard.setContents(selection, selection);


                    WebElement BloggerPostFrame = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/c-wiz/div/div[2]/div/div/div[3]/span/div/div[2]/div[2]"));

                    // code to click in center of screen

                    Dimension i = driver.manage().window().getSize();
//                System.out.println("Dimension x and y :" + i.getWidth() + " " + i.getHeight());
                    //3. Get the height and width of the screen
                    int xwidth = (i.getWidth() / 2);
                    int yheight = (i.getHeight() / 2);

                    robot.mouseMove(xwidth, yheight);

                    //Clicks Left mouse button

                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


                    actions.moveToElement(BloggerPostFrame).click().build().perform();

                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_CONTROL);

                    Thread.sleep(2000);
                }

                String CelebFnameHashtag = "#" + CelebFname.replaceAll("\\s", "");
                String DefaultTags = " #likes #like #follow #Cricket #CricketNews #RedNews " + CelebFnameHashtag;
                String InstaPostTextAreaText="";
                if(FullTweetDivText.isEmpty()) {
                    InstaPostTextAreaText = html2text(TweetHtmlContentValue + DefaultTags);
                }else{

                    InstaPostTextAreaText = CelebFname +": " +FullTweetDivText+DefaultTags;
                }

                if (TweetImageYes && (!StopInstaImage)) {


                    File[] files = CelebImageFolder.listFiles();
                    if (!BloggerNotWorking) {
                        driver.findElement(By.xpath("(//div[@aria-label='Insert image'])[3]")).click();
                        //image upload code
                        Thread.sleep(1000);
                        driver.findElement(By.xpath("//div[contains(@data-command,'imageUploadPicker')]/div[contains(text(),\"Upload from computer\")]")).click();
                        Thread.sleep(1000);
                        WebElement FrameUploadImage = driver.findElement(By.xpath("//iframe[@allow='camera']"));
                        driver.switchTo().frame(FrameUploadImage);

                        if (files != null) { //some JVMs r
                            // return null for empty dirs
                            for (File f : files) {
                                WebElement ChooseFileBtn = driver.findElement(By.xpath("//div[contains(text(),\'Choose files\')]"));

                                ChooseFileBtn.click();


                                Thread.sleep(1000);
                                driver.findElement(By.xpath("//div/input[@type='file']")).sendKeys(f.getAbsolutePath());

                                Thread.sleep(3000);

                                robot.keyPress(KeyEvent.VK_ESCAPE);
                                robot.keyRelease(KeyEvent.VK_ESCAPE);

                            }
                        }

                        WebDriverWait wait = new WebDriverWait(driver, 60);
                        WebElement SelectBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button' and text()='Select']")));
                        Thread.sleep(10000);
                        SelectBtn.click();

                        Thread.sleep(5000);
                        driver.switchTo().parentFrame();
                        driver.findElement(By.xpath("//label[text()='Large']")).click();
                        driver.findElement(By.xpath("//label[contains(text(),'Center')]")).click();

                        driver.findElement(By.xpath("(//span[text()='OK'])[2]")).click();
                        Thread.sleep(3000);
                    }
                    //code for instagram post
                    driver.switchTo().window(InstagramTab);
                    Boolean CGpopup = driver.findElements(By.xpath("//h2[text()='Your Post Goes Against Our Community Guidelines']")).size()>0;
                    if(CGpopup){
                        driver.findElement(By.xpath("//button[text()='OK']")).click();
                        StopInstaImage=true;
                    }
                    driver.findElement(By.xpath("//*[local-name()='svg' and @aria-label='New Post']")).click();
                    driver.findElement(By.xpath("//div/button[text()='Select from computer']")).click();

                    if (files != null) { //some JVMs r
                        // return null for empty dirs
                        int t = 1;
                        for (File f : files) {
                            if (t == 2) {
                                driver.findElement(By.xpath("//*[local-name()='svg' and @aria-label='Open Media Gallery']")).click();
                                driver.findElement(By.xpath("//*[local-name()='svg' and @aria-label='Plus icon']")).click();
                            } else if (t != 1) {
                                driver.findElement(By.xpath("//*[local-name()='svg' and @aria-label='Plus icon']")).click();
                            }
                            Thread.sleep(2000);
//                            driver.findElement(By.xpath("//input[@type='file' and @multiple]")).sendKeys(f.getAbsolutePath());


                            StringBuilder ImagePath = new StringBuilder(f.getAbsolutePath());

                            String ImagePathValue = ImagePath.toString();
                            selection = new StringSelection(ImagePathValue);
                            clipboard.setContents(selection, selection);


                            robot.keyPress(KeyEvent.VK_CONTROL);
                            robot.keyPress(KeyEvent.VK_V);
                            robot.keyRelease(KeyEvent.VK_V);
                            robot.keyRelease(KeyEvent.VK_CONTROL);


                            robot.keyPress(KeyEvent.VK_ENTER);
                            robot.keyRelease(KeyEvent.VK_ENTER);

                            Thread.sleep(2000);


//                            robot.keyPress(KeyEvent.VK_ESCAPE);
//                            robot.keyRelease(KeyEvent.VK_ESCAPE);
                            t += 1;
                        }
                        driver.findElement(By.xpath("//*[local-name()='svg' and @aria-label='Select Crop']")).click();
                        Thread.sleep(2000);
                        driver.findElement(By.xpath("//div[text()='Original']")).click();
                    }

                    driver.findElement(By.xpath("//button[text()='Next']")).click();
                    Thread.sleep(2000);
                    driver.findElement(By.xpath("//button[text()='Next']")).click();



                    try {
                        driver.findElement(By.xpath("//textarea[contains(@aria-label,'caption')]")).sendKeys(InstaPostTextAreaText);
                    } catch (Exception e) {
//                        System.out.println("BMP error Instagram caption");
                        driver.findElement(By.xpath("//textarea[contains(@aria-label,'caption')]")).sendKeys(CelebFname + " Update" + DefaultTags);
                    }


//                    for ( String LabelVal : Labels){
//                        if(LabelVal.equalsIgnoreCase("tweet") || LabelVal.equalsIgnoreCase("Twitter")){
//                            continue;
//                        }
//                        Random rand= new Random();
//                        int xCo=rand.nextInt(30);
//                        int yCo= rand.nextInt(30);
//
//
//                        System.out.println("------------------"+xCo+"---"+yCo);
//                        WebElement InstaPostDiv=driver.findElement(By.xpath("//img[@alt='Photo for tag placement']/following-sibling::div[@role='button']"));
//                        actions.moveToElement(InstaPostDiv).build().perform();
//                        actions.moveByOffset(xCo, yCo).click().build().perform();
////                        actions.moveToElement(InstaPostDiv,rand.nextInt(),rand.nextInt()).click().perform();
//
//                            Boolean searchTag=driver.findElements(By.xpath("//input[@type='search']")).size()>0;
//                            if (searchTag) {
//                                driver.findElement(By.xpath("//input[@type='search']")).sendKeys(LabelVal);
//                                Thread.sleep(2000);
//                                driver.findElement(By.xpath("(//button/div/img[contains(@alt,'profile picture')])[1]")).click();
//                            }
//
//                            Thread.sleep(1000);
//
//
//                    }

                    driver.findElement(By.xpath("//button[text()='Share']")).click();
                    implicitWaitOn();
                    try {
                        String SuccessInstaPost=driver.findElement(By.xpath("//h2[text()='Your post has been shared.']")).getText();
                        System.out.println("Instagram post Successfully posted");
                        instaPostCount+=1;
                    }
                    catch(NoSuchElementException e){

                        System.out.println("Instagram post failed with issues");
                    }





                    Thread.sleep(2000);
                    driver.findElement(By.xpath("//*[local-name()='svg' and @aria-label='Close']")).click();


                    deleteFolder(CelebImageFolder);
                }
                if(TweetVideoYes){
                    driver.switchTo().window(InstagramTab);

                    Boolean CGpopup = driver.findElements(By.xpath("//h2[text()='Your Post Goes Against Our Community Guidelines']")).size()>0;
                    if(CGpopup){
                        driver.findElement(By.xpath("//button[text()='OK']")).click();
                        StopInstaImage=true;
                    }

                    driver.findElement(By.xpath("//*[local-name()='svg' and @aria-label='New Post']")).click();
                    driver.findElement(By.xpath("//div/button[text()='Select from computer']")).click();

                    Thread.sleep(2000);
//                    String VidPath= "C:\\Users\\praka\\Downloads\\ssstwitter_1648758550.mp4";

                    File VideoOutputFileVal = new File(".//target//tweetImages//General//Video//videoContent.mp4");

//                    File DownloadsFolder = new File("C://Users//praka//Downloads");
//
//                    File[] Videofiles = DownloadsFolder.listFiles(new FilenameFilter() {
//                        public boolean accept(File dir, String name) {
//                            return name.endsWith(".mp4");
//                        }
//                    });
//
//                    File CurVid=Videofiles[0];
//                    StringBuilder VideoPath = new StringBuilder(Videofiles[0].getAbsolutePath());
                    StringBuilder VideoPath = new StringBuilder(VideoOutputFileVal.getAbsolutePath());
                    String VideoPathValue = VideoPath.toString();
                    selection = new StringSelection(VideoPathValue);
                    clipboard.setContents(selection, selection);


                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_CONTROL);



                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);

                    Thread.sleep(2000);


                    driver.findElement(By.xpath("//*[local-name()='svg' and @aria-label='Select Crop']")).click();
                    Thread.sleep(2000);
                    driver.findElement(By.xpath("//div[text()='Original']")).click();


                driver.findElement(By.xpath("//button[text()='Next']")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//button[text()='Next']")).click();

                    try {
                        driver.findElement(By.xpath("//textarea[contains(@aria-label,'caption')]")).sendKeys(InstaPostTextAreaText);
                    } catch (Exception e) {
//                        System.out.println("BMP error Instagram caption");
                        driver.findElement(By.xpath("//textarea[contains(@aria-label,'caption')]")).sendKeys(CelebFname + " Update" + DefaultTags);
                    }


//                    for ( String LabelVal : Labels){
//                        if(LabelVal.equalsIgnoreCase("tweet") || LabelVal.equalsIgnoreCase("Twitter")){
//                            continue;
//                        }
//                        Random rand= new Random();
//                        int xCo=rand.nextInt(30);
//                        int yCo= rand.nextInt(30);
//
//
//                        System.out.println("------------------"+xCo+"---"+yCo);
//                        WebElement InstaPostDiv=driver.findElement(By.xpath("//img[@alt='Photo for tag placement']/following-sibling::div[@role='button']"));
//                        actions.moveToElement(InstaPostDiv).build().perform();
//                        actions.moveByOffset(xCo, yCo).click().build().perform();
////                        actions.moveToElement(InstaPostDiv,rand.nextInt(),rand.nextInt()).click().perform();
//
//                            Boolean searchTag=driver.findElements(By.xpath("//input[@type='search']")).size()>0;
//                            if (searchTag) {
//                                driver.findElement(By.xpath("//input[@type='search']")).sendKeys(LabelVal);
//                                Thread.sleep(2000);
//                                driver.findElement(By.xpath("(//button/div/img[contains(@alt,'profile picture')])[1]")).click();
//                            }
//
//                            Thread.sleep(1000);
//
//
//                    }

                    driver.findElement(By.xpath("//button[text()='Share']")).click();
                    implicitWaitOn(80);
                    try {
                        String SuccessInstaPostVid=driver.findElement(By.xpath("//h2[text()='Your post has been shared.']")).getText();
                        System.out.println("Instagram video post Successfully posted");
                        instaPostCount+=1;
                    }
                    catch(NoSuchElementException e){

                        System.out.println("Instagram video post failed with issues");
                    }
                    Thread.sleep(5000);
                    driver.findElement(By.xpath("//*[local-name()='svg' and @aria-label='Close']")).click();

                    deleteFolder(CelebVideoFolder);

//                    if (CurVid.delete()) {
//                        System.out.println("Video deleted successfully");
//                    }
//                    else {
//                        System.out.println("Failed to delete the video file");
//                    }
                }
                implicitWaitOn();
                if (!BloggerNotWorking) {
                    driver.switchTo().window(bloggerTab);
                    WebElement BloggerPostFrame = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/c-wiz/div/div[2]/div/div/div[3]/span/div/div[2]/div[2]"));
                    StringBuilder EmbeddedTweetContent = new StringBuilder("<h4 style=\"text-align: left;\">Read more on twitter:</h4><p><br /></p>");

                    String EmbeddedTweetContentValue = EmbeddedTweetContent.toString();
                    selection = new StringSelection(EmbeddedTweetContentValue);
                    clipboard.setContents(selection, selection);

                    actions.moveToElement(BloggerPostFrame).click().build().perform();

                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_CONTROL);

                    Thread.sleep(2000);


                    driver.switchTo().window(TwitterTab);
                    x.findElement(By.xpath(".//div[@aria-label='More']")).click();
                    driver.findElement(By.xpath("//span[text()='Embed Tweet']")).click();
                    Thread.sleep(3000);
                    Set<String> AllTabs = driver.getWindowHandles();
                    String EmbedTwitterTab;
                    for (String tabName : AllTabs) {
                        if (!(tabName.equals(TwitterTab)) && !(tabName.equals(bloggerTab))) {
                            driver.switchTo().window(tabName);
                            EmbedTwitterTab = driver.getWindowHandle();


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

                    LabelsString = String.join(",", Labels);
                    driver.findElement(By.xpath("(//textarea[contains(@aria-label,'labels')])[1]")).click();
                    driver.findElement(By.xpath("(//textarea[contains(@aria-label,'labels')])[1]")).sendKeys(LabelsString);

                    driver.findElement(By.xpath("(//div[text()='Publish'])[1]")).click();
                    driver.findElement(By.xpath("(//span[text()='Confirm'])[2]")).click();
                    Boolean BlogHomePageManageButtonPresence = driver.findElements(By.xpath("(//span[text()='Manage'])[2]")).size() > 0;
                    if (BlogHomePageManageButtonPresence) {
                        blogPostCount += 1;
                        //do add db stuff

                    } else {
                        System.exit(0);
                    }


                }
            }
//            driver.switchTo().window(TwitterTab);
//            driver.findElement(By.xpath("//div[@aria-label=\"Account menu\"]")).click();
////        Thread.sleep(3000);
//            driver.findElement(By.xpath("//div[text()='Log out ']")).click();

//            driver.close();
//            driver.switchTo().window(bloggerTab);
//            driver.close();


            ConnectionString connectionString2 = new ConnectionString("mongodb+srv://Tracker2:Ddd7856@cluster0.3jatg.mongodb.net/CelebTracker?retryWrites=true&w=majority");
            MongoClientSettings settings2 = MongoClientSettings.builder()
                    .applyConnectionString(connectionString2)
                    .build();
            MongoClient mongoClient2 = MongoClients.create(settings2);
            MongoDatabase database2 = mongoClient2.getDatabase("CelebTracker");
            MongoCollection<Document> collection2 = database2.getCollection("CelebData");
            BsonDocument filter2 = new BsonDocument();


//            System.out.println("Connected");
            LocalDateTime CurrentDatetime = LocalDateTime.now();
            String NowDateTme=CurrentDatetime.toString();
            collection2.updateOne(Filters.eq("TwitterUserId",strArg1), Updates.set("LastTweet",NowDateTme));

            mongoClient2.close();


//

            //throw new PendingException();

        }
//
        driver.switchTo().window(bloggerTab);
            driver.close();
        driver.switchTo().window(TwitterTab);

        if(blogPostCount>0 || instaPostCount>0) {
            final String username = "prakash.rsingh04@gmail.com";
            final String password = "Ddd@7856";

            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.socketFactory.port", "465");
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("prakash.rsingh04@gmail.com"));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse("prakash.rsingh04@gmail.com")
                );
                message.setSubject("RedNEWS Status");
                message.setText("Total Blog Post Published: " + blogPostCount
                        + "\n" + "Total Insta Post Published: " + instaPostCount +
                        "\n");

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Total Blog Post Published: "+blogPostCount);
        System.out.println("Total Insta Post Published: "+instaPostCount);
        driver.close();
        driver.switchTo().window(InstagramTab);


    }





    @Then("^Close browser$")
    public void homepage_is_populated() throws Throwable {
       //throw new PendingException();
        driver.close();
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



    public void implicitWaitOn(int tsec){
        driver.manage().timeouts().implicitlyWait(tsec, TimeUnit.SECONDS);
    }
    public void implicitWaitOn(){
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void implicitWaitOff(){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }
    public void implicitWaitLow(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public String html2text(String html) {
        return Jsoup.parse(html).text();
    }





}

