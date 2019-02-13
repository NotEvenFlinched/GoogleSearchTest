package google.search.autotest.tests;

import google.search.autotest.pages.GoogleMainPage;
import google.search.autotest.pages.GoogleSearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class GoogleSearchTest {
    public static WebDriver driver;
    public static GoogleMainPage googleMainPage;
    public static GoogleSearchResultsPage googleSearchResultsPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        driver = new ChromeDriver();
        googleMainPage = new GoogleMainPage(driver);
        googleSearchResultsPage = new GoogleSearchResultsPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
    }

    @Test
    @Parameters("querryText")
    public void searchTest(String querryText) {
        googleMainPage.inputSearchTerm(querryText);
        googleMainPage.clickSearchButton();

        if (googleSearchResultsPage.getSearchResultsCount() > 0) {

            for (String title : googleSearchResultsPage.getUniqueResultTitles()) {
                if (title.toLowerCase().contains(querryText.toLowerCase())) {
                    System.out.println("Search result - " + title + " - literally contains search querry");
                } else {
                    String[] querryWords = querryText.split(" ");
                    if (querryWords.length > 1) {
                        for (String word : querryWords) {
                            if (title.toLowerCase().contains(word.toLowerCase())) {
                                System.out.println("Search result - " + title + " - partially contains search querry");
                                break;
                            }
                        }
                    } else {
                        System.out.println("Search result - " + title + " - did not contains search querry");
                    }
                }
            }
        } else {
            System.out.println("Your search - " + querryText + " - did not match any documents.");
        }
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
