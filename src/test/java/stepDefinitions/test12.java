package stepDefinitions;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.joda.LocalDateTimeParser;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;

public class test12 {

    public static void main(String[] args) {
        String rawString = "Sharing something close to my heart - The “\uD835\uDE19\uD835\uDE2A\uD835\uDE28\uD835\uDE29\uD835\uDE35 \uD835\uDE35\uD835\uDE30 \uD835\uDE17\uD835\uDE2D\uD835\uDE22\uD835\uDE3A”.\n" +
                "\n" +
                "Happy #RepublicDay to all my fellow Indians! Flag of India\n" +
                "\n" +
                "#SportPlayingNation";
        byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);

        String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);

        assertEquals(rawString, utf8EncodedString);
        System.out.println(utf8EncodedString);



        Date d= new Date();
        Date p;
        System.out.println(d);

        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("After formatting: " + formattedDate);
        //Wed Feb 02 00:32:41 IST 2022
        String Tdate ="2022-02-02T01:13:00.000Z";
        Tdate= Tdate.substring(0,Tdate.length()-1);

        LocalDateTime myDateObj2 = LocalDateTime.parse(Tdate);
        System.out.println("Before formatting: " + myDateObj2);
        DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate2 = myDateObj2.format(myFormatObj2);
        System.out.println("After formatting: " + formattedDate2);

        if(formattedDate2.compareToIgnoreCase(formattedDate)>0){
            System.out.println("--> formatting: " + formattedDate2);
        }
        else{
            System.out.println("---# formatting: " + formattedDate);
        }




    }


//    protected boolean isElementHiddenNow(String id) {
//        turnOffImplicitWaits();
//        boolean result = ExpectedConditions.invisibilityOfElementLocated(By.id(id)).apply(driver);
//        turnOnImplicitWaits();
//        return result;
//    }
//
//    private void turnOffImplicitWaits() {
//        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//    }
//
//    private void turnOnImplicitWaits() {
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    }
}