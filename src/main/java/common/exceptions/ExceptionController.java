package common.exceptions;

import common.Managers.ScenarioManager;
import common.interfaces.FailAssertion;
import common.selenium.extensions.WebDriverExtensions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ExceptionController {

    private static final Logger logger = LogManager.getLogger(ExceptionController.class.getName());

    private static FailAssertion mThrower = null;

    public static void regHookFail(FailAssertion thrower) {
        mThrower = thrower;
    }

    public static void hookFail(String message) {
        logger.error(message);
        mThrower.fail(message);
    }

    public static void hookFail(String message, boolean screenshot) {
        logger.error(message);
        if (screenshot) WebDriverExtensions.getScreenshot(ScenarioManager.getScenario());
        mThrower.fail(message);
    }

    public static void hookFail(String message, Exception error) {
        logger.error(message + " ---> " + error.getLocalizedMessage() + "\n"
                + Arrays.toString(error.getStackTrace()));
        mThrower.fail(message + " ---> " + error);
    }

    public static void hookFail(String message, Exception error, boolean screenshot) {
        logger.error(message + " ---> " + error.getLocalizedMessage());
        if (screenshot) WebDriverExtensions.getScreenshot(ScenarioManager.getScenario());
        mThrower.fail(message + " ---> " + error);
    }

}
