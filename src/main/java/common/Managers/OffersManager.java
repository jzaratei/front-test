package common.Managers;

import common.entities.Offer;
import common.exceptions.ExceptionController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OffersManager {

    private static final Logger logger = LogManager.getLogger(OffersManager.class.getName());

    public static boolean checkListOrdered(List<Offer> offerList, String orderType) {
        logger.info("Checking ordered list by " + orderType + "...");
        try {
            if (offerList.isEmpty()) ExceptionController.hookFail("Actual list is empty. Nothing to check.");
            switch (orderType) {
                case "price":
                    return offerList.stream().sorted(Comparator.comparingDouble(Offer::getPrice))
                            .collect(Collectors.toList()).equals(offerList);
                case "stars":
                    return offerList.stream().sorted(Comparator.comparingDouble(Offer::getStars))
                            .collect(Collectors.toList()).equals(offerList);
                case "rating":
                    return offerList.stream().sorted(Comparator.comparingDouble(Offer::getRating).reversed())
                            .collect(Collectors.toList()).equals(offerList);
            }
        }catch (Exception e){
            ExceptionController.hookFail("A problem occurred while checking ordered list. ", e);
        }
        return false;
    }

}
