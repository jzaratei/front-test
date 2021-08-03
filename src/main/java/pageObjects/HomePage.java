package pageObjects;

import common.exceptions.ExceptionController;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Countries;

import java.util.List;

import static common.selenium.extensions.JavascriptExtensions.weScrollIntoViewElement;
import static common.selenium.extensions.WebElementExtensions.*;

public class HomePage extends CommonPage {

    @FindBy(css = "a[title='Explorar']")
    protected WebElement btnExplore;

    @FindBy(css = "a[title='Hoteles']")
    protected WebElement btnHotelsEs;


    @FindBy(css = "a[title='Hotéis']")
    protected WebElement btnHotelsPt;

    @FindBy(css = "h1.app-hero__title.app-heading.app-heading--light.app-heading--display.app-heading--uc")
    protected WebElement lblWelcome;

    @FindBy(css = "div.app-wc h3.app-heading.app-heading--h3")
    protected WebElement lblSearchTitle;

    /*recommended destination*/

    @FindBy(css = "img[alt='Descubre las ofertas de TUI ']")
    protected WebElement imgRecommendedImages;

    @FindBy(css = "section.app-list h2.app-list__title.app-heading")
    protected WebElement lblRecommendedTitle;

    @FindBy(css = "div.app-list__items h3.app-heading.app-heading--h4")
    protected List<WebElement> lblRecommendedNameList;

    public void checkHomeLanguage(Countries country){
        try {
            logger.info("Checking home language...");
            switch (country){
                case SPAIN:
                    weElementIsVisible(btnExplore);
                    weElementIsVisible(btnHotelsEs);
                    weElementIsVisible(lblWelcome);
                    weElementIsVisible(lblSearchTitle);
                    Assertions.assertEquals("TU HABITACIÓN\nESTÁ PREPARADA", lblWelcome.getText());
                    Assertions.assertEquals("Encuentra tu estancia perfecta!", lblSearchTitle.getText());
                    break;
                case PORTUGAL:
                    weElementIsVisible(btnExplore);
                    weElementIsVisible(btnHotelsPt);
                    weElementIsVisible(lblWelcome);
                    weElementIsVisible(lblSearchTitle);
                    Assertions.assertEquals("O SEU QUARTO\nESTÁ PRONTO", lblWelcome.getText());
                    Assertions.assertEquals("Encontre a sua estadia perfeita!", lblSearchTitle.getText());
                    break;
            }
        }catch (Exception e){
            ExceptionController.hookFail("A problem occurred while checking " + country + " home page", e, true);
        }
    }

    public void selectRecommendedDestination(String destination) {
        try {
            logger.info("Selecting recommended destination...");
            weScrollIntoViewElement(lblRecommendedTitle);
            weElementIsVisible(imgRecommendedImages);
            for (WebElement destinationName: lblRecommendedNameList){
                if (destinationName.getText().equals(destination)){
                    weClick(destinationName);
                    break;
                }
            }
        }catch (Exception e){
            ExceptionController.hookFail("A problem occurred while selecting a recommended destination.", e, true);
        }
    }

    public void selectHeaderTab(String tab){
        logger.info("Going to " + tab +  " page");
        try {
            if (tab.equals("Explorar")) weClick(btnExplore);
            else if (tab.equals("Hoteles")) weClick(btnHotelsEs);
            Thread.sleep(5000);
        }catch (Exception e){
            ExceptionController.hookFail("A problem occurred while selecting " + tab, e, true);
        }
       ;
    }
}
