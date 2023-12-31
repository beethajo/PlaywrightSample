package stepDefinitions;

import com.google.gson.JsonObject;
import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.nio.file.Paths;

public class Hooks extends Utility {
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    static String browserType = "LambdaChrome";

    @Before(order = 1)
    public static void initiateBrowser() {
        boolean flag = false;
        String user = System.getenv("LT_USERNAME");
        String accessKey = System.getenv("LT_ACCESS_KEY");

        if (browserType == "localChrome") {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            //context = browser.newContext();
            context = browser.newContext(new Browser.NewContextOptions()
                    .setRecordVideoDir(Paths.get("test-reports/Videos/")) // Set the video recording directory
                    .setRecordVideoSize(1280, 720));
            page = context.newPage();

        } else if (browserType == "LambdaChrome") {
            playwright = Playwright.create();
            JsonObject capabilities = new JsonObject();
            JsonObject ltOptions = new JsonObject();
            capabilities.addProperty("browsername", "Chrome"); // Browsers allowed: `Chrome`, `MicrosoftEdge`, `pw-chromium`, `pw-firefox` and `pw-webkit`
            capabilities.addProperty("browserVersion", "latest");
            ltOptions.addProperty("platform", "Windows 10");
            ltOptions.addProperty("name", "Playwright Test");
            ltOptions.addProperty("build", "Playwright Sample Build");
            ltOptions.addProperty("user", user);
            ltOptions.addProperty("accessKey", accessKey);
            ltOptions.addProperty("network", true);
            ltOptions.addProperty("console", true);
            ltOptions.addProperty("tunnel", true);
            capabilities.add("LT:Options", ltOptions);
            BrowserType chromium = playwright.chromium();
            String cdpUrl = "wss://cdp.lambdatest.com/playwright?capabilities=" + capabilities;
            browser = chromium.connect(cdpUrl);
            context = browser.newContext();
            page = browser.newPage();

        }
    }

    @After
    public void tearDown(Scenario scenario) {
        logger.info("[--->In Hooks, cucumber after tearDown method ---> " + "<---]");
        if (scenario.isFailed()) {
            scenario.log("[--->--------------------------------------------" + "<---]");
            scenario.log("[--->Scenario Name : " + scenario.getName() + "<---]");
            scenario.log("[--->CURRENT URL IS ----> " + page.url() + "<---]");
            scenario.log("[--->BROWSER NAME   ----> " + browser.browserType().name() + "<---]");
            scenario.log("[--->CURRENT TAG IS : " + scenario.getSourceTagNames() + "<---]");
            scenario.log("[--->--------------------------------------------" + "<---]");
            getScreenshot(scenario);
            setTestStatus("FAILED", "TEST FAILED", page);
        }
         else {
            setTestStatus("PASSED", "TEST PASSED", page);
        }
        context.close();
        browser.close();
        playwright.close();

    }

    public void getScreenshot(Scenario scenario) {
        try {
           scenario.attach(page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("src/test/reports/screenshot.png"))), "image/jpg", scenario.getName());
        } catch (Exception e) {

        }
    }

    public static void setTestStatus(String status, String remark, Page page) {
        Object result;
        result = page.evaluate("_ => {}", "lambdatest_action: { \"action\": \"setTestStatus\", \"arguments\": { \"status\": \"" + status + "\", \"remark\": \"" + remark + "\"}}");
    }
}

