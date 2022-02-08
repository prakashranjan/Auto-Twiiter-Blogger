package stepDefinitions;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.mongodb.ConnectionString;
import com.mongodb.DBCursor;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.mozilla.javascript.ObjToIntMap;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.joda.LocalDateTimeParser;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bson.BsonDocument;
import static com.mongodb.client.model.Filters.*;


import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;

public class test12 {

    public static void main(String[] args) {
//        String rawString = "Sharing something close to my heart - The “\uD835\uDE19\uD835\uDE2A\uD835\uDE28\uD835\uDE29\uD835\uDE35 \uD835\uDE35\uD835\uDE30 \uD835\uDE17\uD835\uDE2D\uD835\uDE22\uD835\uDE3A”.\n" +
//                "\n" +
//                "Happy #RepublicDay to all my fellow Indians! Flag of India\n" +
//                "\n" +
//                "#SportPlayingNation";
//        byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);
//
//        String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);
//
//        assertEquals(rawString, utf8EncodedString);
//        System.out.println(utf8EncodedString);
//
//
//
//        Date d= new Date();
//        Date p;
//        System.out.println(d);
//
//        LocalDateTime myDateObj = LocalDateTime.now();
//        System.out.println("Before formatting: " + myDateObj);
//
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//
//        String formattedDate = myDateObj.format(myFormatObj);
//        System.out.println("After formatting: " + formattedDate);
//        //Wed Feb 02 00:32:41 IST 2022
//        String Tdate ="2022-02-02T01:13:00.000Z";
//        Tdate= Tdate.substring(0,Tdate.length()-1);
//
//        LocalDateTime myDateObj2 = LocalDateTime.parse(Tdate);
//        System.out.println("Before formatting: " + myDateObj2);
//        DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        String formattedDate2 = myDateObj2.format(myFormatObj2);
//        System.out.println("After formatting: " + formattedDate2);
//
//        if(formattedDate2.compareToIgnoreCase(formattedDate)>0){
//            System.out.println("--> formatting: " + formattedDate2);
//        }
//        else{
//            System.out.println("---# formatting: " + formattedDate);
//        }

//         Set system properties via commandline or programmatically
//        System.setProperty("javax.net.ssl.keyStore", ".//X509-cert-8729440830276165484.pem");
//        System.setProperty("javax.net.ssl.keyStorePassword", "<keystore_password>");
//        String uri = "mongodb+srv://cluster0.3jatg.mongodb.net/CelebTracker?authSource=%24external&authMechanism=MONGODB-X509&retryWrites=true&w=majority";
//        ConnectionString connectionString = new ConnectionString(uri);
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .build();
//        MongoClient mongoClient = MongoClients.create(settings);
//        MongoDatabase database = mongoClient.getDatabase("CelebTracker");
//        MongoCollection<Document> collection = database.getCollection("CelebData");
//        BsonDocument filter = new BsonDocument();
//        collection.countDocuments(filter);
//        mongoClient.close();

//        ConnectionString connectionString = new ConnectionString("mongodb+srv://<username>:<password>@cluster0.3jatg.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .build();
//        MongoClient mongoClient = MongoClients.create(settings);
//        MongoDatabase database = mongoClient.getDatabase("test");
//
//
//        ConnectionString connectionString = new ConnectionString("mongodb+srv://Tracker2:Ddd7856@cluster0.3jatg.mongodb.net/CelebTracker?retryWrites=true&w=majority");
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .build();
//        MongoClient mongoClient = MongoClients.create(settings);
//        MongoDatabase database = mongoClient.getDatabase("CelebTracker");
//        MongoCollection<Document> collection = database.getCollection("CelebData");
//        BsonDocument filter = new BsonDocument();
//        collection.countDocuments(filter);
//        System.out.println("Connected");
//
//        Iterator<Document> itr = collection.find().iterator();
//
//
//        while(itr.hasNext()){
//
//            Document dcur = itr.next();
//            String strarg4=dcur.get("TwitterUserId").toString();
//            String strargDate=dcur.get("LastTweet").toString();
//            System.out.println(strarg4);
//            System.out.println(strargDate);
//        }
//
//        mongoClient.close();



//        collection.updateOne(Filters.eq("TwitterUserId","sachin_rt"), Updates.set("LastTweet","2022-02-09T06:00:00.00"));
//
//        Iterator<Document> itr2 = collection.find().iterator();
//        while(itr2.hasNext()){
//
//            Document dcur = itr2.next();
//            String strarg4=dcur.get("TwitterUserId").toString();
//            String strargDate=dcur.get("LastTweet").toString();
//            System.out.println(strarg4);
//            System.out.println(strargDate);
//        }
//
//
//        mongoClient.close();



    }

//    mongosh "mongodb+srv://cluster0.3jatg.mongodb.net/CelebTracker?authSource=%24external&authMechanism=MONGODB-X509" --tls --tlsCertificateKeyFile C:\Users\praka\IdeaProjects\CucumberAuto\CucumberAuto\X509-cert-8729440830276165484.pem


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