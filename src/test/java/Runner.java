import org.testng.annotations.BeforeClass;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "Features"
        ,tags = "@data"
        ,glue = {"StepDefination"}
        ,plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class Runner extends AbstractTestNGCucumberTests {


    @BeforeClass
    public void quite() {
        System.out.println("Checking!!");
    }
}