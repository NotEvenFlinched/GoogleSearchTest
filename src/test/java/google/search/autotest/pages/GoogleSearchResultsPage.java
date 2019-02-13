package google.search.autotest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GoogleSearchResultsPage {

    public GoogleSearchResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(className = "LC20lb")
    public List<WebElement> resultTitles;

    public int getSearchResultsCount() {
        return resultTitles.size();
    }

    public Set<String> getUniqueResultTitles() {
        Set<String> resultTitlesUnique = new LinkedHashSet<>();

        for (WebElement w : resultTitles) {
            resultTitlesUnique.add(w.getText());
        }

        return resultTitlesUnique;
    }
}
