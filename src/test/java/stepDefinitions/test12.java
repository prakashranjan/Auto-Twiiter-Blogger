package stepDefinitions;

import java.nio.charset.StandardCharsets;

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
    }
}