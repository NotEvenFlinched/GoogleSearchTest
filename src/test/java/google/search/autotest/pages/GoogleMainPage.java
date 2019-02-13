package google.search.autotest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleMainPage {

    public GoogleMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(xpath = "//input[contains(@class, 'gLFyf gsfi')]")
    private WebElement searchField;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    public void inputSearchTerm(String searchTerm) {
        searchField.sendKeys(searchTerm);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

}
