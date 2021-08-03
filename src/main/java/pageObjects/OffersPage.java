package pageObjects;

import common.entities.Offer;
import common.exceptions.ExceptionController;
import common.selenium.Driver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static common.selenium.Driver.browser;
import static common.selenium.extensions.JavascriptExtensions.weScrollIntoViewElement;
import static common.selenium.extensions.WebElementExtensions.*;
import static common.selenium.extensions.WebElementExtensions.weClick;

public class OffersPage extends CommonPage {

    @FindBy(css = "section.srp__search-info h3[data-qa='typography']")
    protected WebElement lblOffersPageTitle;

    @FindBy(css = "button[data-qa-label='Categoría']")
    protected WebElement tabOrderByCategory;

    @FindBy(css = "button[data-qa-label='Precio (ascendente)']")
    protected WebElement tabOrderByPrice;

    @FindBy(css = "button[data-qa-label='Mejor puntuados']")
    protected WebElement tabOrderByRating;

    @FindBy(css = "#select-result-wrapper-1 h2[data-qa='typography']")
    protected WebElement lblNoHotelFound;

    @FindBy(css = "article[data-qa='offer_tile']")
    protected List<WebElement> artOfferList;

    @FindBy(css = "div[data-qa='offer_tile_category']")
    protected List<WebElement> imgStarRatingList;

    @FindBy(css = "h2[data-qa='offer_tile_title']")
    protected List<WebElement> lblOfferTitleList;

    @FindBy(css = "div[data-qa='offer_tile_location']")
    protected List<WebElement> lblOfferLocationList;

    @FindBy(css = "div[data-qa='offer_tile_trustyou_seal'] div.trustyou-seal__value.trustyou-seal__value--size-xs")
    protected List<WebElement> lblOfferRatingList;

    @FindBy(css = "div.offer-tile__price-info span[data-qa='price_display']")
    protected List<WebElement> lblOfferPriceList;

    @FindBy(css = "div.offer-tile__price-info button[data-qa='button']")
    protected List<WebElement> lblBookButton;


    public void checkOffersPage() {
        logger.info("Checking offers page");
        try {
            isElementVisible(lblOffersPageTitle, 10);
            Assertions.assertTrue(lblOffersPageTitle.getText().contains("HOTELES"), "Hotels not found!");
            Assertions.assertTrue(lblOffersPageTitle.getText().contains("encontrados"));
            weElementIsVisible(tabOrderByCategory);
            weElementIsVisible(tabOrderByPrice);
            weElementIsVisible(tabOrderByRating);
        } catch (Exception e) {
            ExceptionController.hookFail("A problem occurred while checking search results", e, true);
        }
    }

    public List<Offer> getOfferList(){
        logger.info("Getting offers list...");
        logger.info("There's "+ artOfferList.size() + " offers.");
        List<Offer> actualOfferList = new ArrayList<>();
        for (int index = 0; index < artOfferList.size(); index++) {
            if(isElementVisible(lblOfferTitleList.get(index), 1)){
                Offer actualOffer = new Offer(lblOfferTitleList.get(index).getText(),
                        Double.parseDouble(imgStarRatingList.get(index).getText().split("\\n")[0]), "",
                        lblOfferLocationList.get(index).getText(), getRating(index),
                        Double.parseDouble(lblOfferPriceList.get(index).getText().replaceAll("[^0-9,]+", "")));
                actualOfferList.add(actualOffer);
                logger.info("Offer '" + actualOffer.getName() + "' added to offers list.");
            }
        }
        if (actualOfferList.isEmpty()) logger.warn("List page is empty or a problem occurred while adding products to list.");
        return actualOfferList;
    }

    private double getRating(int index){
        if (lblOfferRatingList.size()<artOfferList.size()) return 0.0;
        else return  Double.parseDouble(lblOfferRatingList.get(index).getText());
    }

    public void orderBy(String orderType) {
        logger.info("Order by " + orderType);
        try {
            if (orderType.equals("category")) weClick(tabOrderByCategory);
            else if (orderType.equals("price")) weClick(tabOrderByPrice);
            else weClick(tabOrderByRating);
            Thread.sleep(15000);
        }catch (Exception e){
            ExceptionController.hookFail("A problem occurred while ordering the offers", e, true);
        }
    }

    public boolean isHotelFound(){
        try {
            if (isElementVisible(lblNoHotelFound, 5)) {
                browser().navigate().refresh();
                Thread.sleep(5000);
            }
        }catch (Exception e){
            logger.warn("No hotels found!");
        }
        return !isElementVisible(lblNoHotelFound, 5);
    }

    public Offer selectHotelByStars(String stars) {
        Offer actualOffer = null;
        try {
            for (int index = 0; index < artOfferList.size(); index++) {
                if(isElementVisible(lblOfferTitleList.get(index), 1)){
                    if (imgStarRatingList.get(index).getText().split("\\n")[0].equals(stars)){
                        actualOffer = new Offer(lblOfferTitleList.get(index).getText(),
                                Double.parseDouble(imgStarRatingList.get(index).getText().split("\\n")[0]), "",
                                lblOfferLocationList.get(index).getText(), getRating(index),
                                Double.parseDouble(lblOfferPriceList.get(index).getText().replaceAll("[^0-9,]+", "")));
                        weScrollIntoViewElement(lblBookButton.get(index));
                        weClick(lblBookButton.get(index));
                        logger.info("Offer '" + actualOffer.getName() + "' added to offers list.");
                        Thread.sleep(5000);
                        break;
                    }
                }
            }
        }catch (Exception e){
            ExceptionController.hookFail("A problem occurred while selecting a hotel", e, true);
        }
        return actualOffer;
    }
}
