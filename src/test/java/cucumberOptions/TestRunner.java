package cucumberOptions;

import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepDefinitions",
        tags="@mobile",
        dryRun = false,
        stepNotifications = true,
        monochrome = true,
        plugin = {"pretty",
                "html:target/CucumberReport.html",
                "json:target/CucumberReport.json",
                "junit:target/cukes.xml"
        }



)

public class TestRunner {
}
