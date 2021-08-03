package pageObjects;

import common.exceptions.ExceptionController;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Countries;

import static common.selenium.extensions.WebElementExtensions.weClick;

public class ReceptionPage extends CommonPage {

    @FindBy(css = "button[data-locale='es']")
    protected WebElement btnSpain;

    @FindBy(css = "button[data-locale='pt']")
    protected WebElement btnPortugal;

    @FindBy(css = "button[data-locale='br']")
    protected WebElement btnBrazil;

    @FindBy(css = "button[data-locale='it']")
    protected WebElement btnItaly;

    public void selectCountry(Countries country) {
        try {
            logger.info("Selecting country: " + country );
            switch (country){
                case SPAIN:
                    weClick(btnSpain);
                    break;
                case PORTUGAL:
                    weClick(btnPortugal);
                    break;
                case BRAZIL:
                    weClick(btnBrazil);
                    break;
                case ITALY:
                    weClick(btnItaly);
                    break;
                default:
                    ExceptionController.hookFail("Country not available.");
            }
            Thread.sleep(5000);
        }catch (Exception e){
            ExceptionController.hookFail("A problem occurred while doing login.", e, true);
        }
    }
}
