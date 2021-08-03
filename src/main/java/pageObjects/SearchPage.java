package pageObjects;

import common.exceptions.ExceptionController;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static common.selenium.extensions.WebElementExtensions.*;

public class SearchPage extends CommonPage {

    @FindBy(css = "div.app-wc h3.app-heading.app-heading--h3")
    protected WebElement lblSearchTitle;

    @FindBy(css = "input[data-qa='destination_search_input_input']")
    protected WebElement txtSearchInput;

    @FindBy(css = "button[data-qa='date_selection_button']")
    protected WebElement btnDateSelection;

    @FindBy(css = "button[data-qa='guest_selection_button']")
    protected WebElement btnGuestSelection;

    @FindBy(css = "a[data-qa='search_submit_button']")
    protected WebElement btnSearchSubmit;

    /*calendar*/

    @FindBy(css = "div[data-qa='date_selection_tab_checkin']")
    protected WebElement lblCheckin;

    @FindBy(css = "time.calendar-month__title")
    protected List<WebElement> lblCalendarMonthTitle;

    @FindBy(css = "#month_2021-08 time.calendar-month__day-label")
    protected List<WebElement> lblCalendarAugustDayList;

    @FindBy(css = "button[data-qa='date_selection_button_apply']")
    protected WebElement btnDateSelectionApply;

    /*guest selection*/

    @FindBy(css = "button[data-qa='guest_selection_menu_adults_spin_button_increment_action']")
    protected WebElement btnIncrementGuest;

    @FindBy(css = "input[data-qa='guest_selection_menu_adults_spin_button_value']")
    protected WebElement lblGuestValue;

    @FindBy(css = "button[data-qa='guest_selection_menu_button_apply']")
    protected WebElement btnGuestApply;


    public void doSearch(String destination, String dateInit, String dateFin, String guest) {
        try {
            weElementIsVisible(lblSearchTitle);
            weSendKeys(txtSearchInput, destination, true);
            weClick(lblSearchTitle);
            weElementIsVisible(btnDateSelection);
            weClick(btnDateSelection);
            selectDay(dateInit, dateFin);
            weClick(btnDateSelectionApply);
            weClick(btnGuestSelection);
            selectGuest(guest);
            weClick(btnSearchSubmit);
        } catch (Exception e) {
            ExceptionController.hookFail("A problem occurred while searching for a destination", e, true);
        }
    }

        public void selectDay(String dateInit, String dateFin){
        try {
            String dayInit = dateInit.split("-")[0];
            String monthInit = dateInit.split("-")[1];
            String dayFin = dateFin.split("-")[0];
            weElementIsVisible(lblCheckin);
            for (WebElement month : lblCalendarMonthTitle) {
                if (month.getText().contains(monthInit)){
                    logger.info("selecting initial day");
                    for (WebElement day : lblCalendarAugustDayList) {
                        if (day.getText().equals(dayInit)){
                            weClick(day);
                            break;
                        }
                    }
                    logger.info("selecting end day");
                    for (WebElement day : lblCalendarAugustDayList) {
                        if (day.getText().equals(dayFin)){
                            weClick(day);
                            break;
                        }
                    }
                    return;
                }
            }
        }catch (Exception e){
            ExceptionController.hookFail("A problem occurred while selecting days", e, true);
        }

        }

        public void selectGuest(String number){
        try {
            String initialValue = lblGuestValue.getAttribute("data-value");
            while (!number.equals(initialValue)){
                weClick(btnIncrementGuest);
                initialValue = waitAndGetAttributeChanged(lblGuestValue, initialValue);
            }
            weClick(btnGuestApply);
        }catch (Exception e){
            ExceptionController.hookFail("A problem occurred while selecting guest number", e, true);
        }

        }

}
