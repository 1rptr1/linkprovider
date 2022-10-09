package StepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utility.FileToHashmap;
import utility.WriteToText;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class code {
    WebDriver driver;
    int lastpagenumber=100;
    String currentURL;
    String name;
    String torrent_link;
    Map<String, String> nameAndLinks;
    String filepath;
    @Given("user navigates to 1337x website")
    public void user_navigates_to_1337x_website() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.images", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("enable-automation");
       // options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
       // options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        //options.addArguments("--headless", "--disable-gpu", "--blink-settings=imagesEnabled=false");
        System.setProperty("webdriver.chrome.driver","src/test/resources/Driver/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.manage().window().maximize();
    }
    @Then("user searches for all files with {string} as keyword")
    public void user_searches_for_all_files_with_as_keyword(String string) {
        driver.navigate().to("https://www.1377x.to/search/"+string+"/1");
        currentURL = driver.getCurrentUrl();
        currentURL = currentURL.substring(0,currentURL.length()-1);

    }
    @Then("user browses all the files along with torrent link to writes to file named {string}")
    public void userBrowsesAllTheFilesAlongWithTorrentLinkToWritesToFileNamed(String arg0) {
        try{
        for(int i = 1;i<lastpagenumber;++i)
        {
            int rows = driver.findElements(By.xpath("//table/tbody/tr")).size();
            for (int j = 1; j < rows; j++) {
                WebElement webelement = driver.findElement(By.xpath("//table/tbody/tr[" + j + "]/td/a[2]"));
                name = webelement.getText();

                JavascriptExecutor jse = (JavascriptExecutor)driver;
                jse.executeScript("arguments[0].click()", webelement);

                torrent_link = driver.findElement(By.xpath("//a[text()='Magnet Download']")).getAttribute("href");

                driver.navigate().back();
                Thread.sleep(10000);
                WriteToText.write(arg0,name +"|"+torrent_link);
            }

            driver.navigate().to(currentURL+i);
            Thread.sleep(1000);
            System.out.println(driver.getCurrentUrl());
        }}
        catch (Exception e)
        {
            e.printStackTrace();
            driver.quit();
        }
    }

    @Then("user closes the website")
    public void user_closes_the_website() {
        driver.quit();
    }

    @Then("user reads the previously stored data from file named as {string} and stores data in Hashmap")
    public void user_reads_the_previously_stored_data_from_file_named_as_and_stores_data_in_hashmap(String string) {
         nameAndLinks = FileToHashmap.getData(string);
         filepath=string;
        }

    @Then("user check change in data or update in data and adds them to the file")
    public void user_check_change_in_data_or_update_in_data_and_adds_them_to_the_file() {
        try{
            for(int i = 1;i<lastpagenumber;++i)
            {
                int rows = driver.findElements(By.xpath("//table/tbody/tr")).size();
                for (int j = 1; j < rows; j++) {
                    WebElement webelement = driver.findElement(By.xpath("//table/tbody/tr[" + j + "]/td/a[2]"));
                    name = webelement.getText();
                    if (nameAndLinks.containsKey(name)) {
                        i = lastpagenumber;
                        j = rows;
                    } else {
                        JavascriptExecutor jse = (JavascriptExecutor) driver;
                        jse.executeScript("arguments[0].click()", webelement);

                        torrent_link = driver.findElement(By.xpath("//a[text()='Magnet Download']")).getAttribute("href");

                        driver.navigate().back();
                        Thread.sleep(10000);
                    }
                }
                driver.navigate().to(currentURL+i);
                Thread.sleep(1000);
                nameAndLinks.put(name,torrent_link);
            }}
        catch (Exception e)
        {
            e.printStackTrace();
            driver.quit();
        }
        WriteToText.mapData(filepath,nameAndLinks);
    }


}
