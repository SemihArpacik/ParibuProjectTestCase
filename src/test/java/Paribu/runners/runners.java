package Paribu.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber_rapor.html",
                "pretty",
                "html:test-output/reports/default-cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml",
                "rerun:target/failedRerun.txt"},
        features = "src/test/resources/features/",
        glue = "Paribu/steps",
        tags = "@api"


)

public class runners {


}
