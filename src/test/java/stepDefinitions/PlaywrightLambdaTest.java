package stepDefinitions;

import com.google.gson.JsonObject;
import com.microsoft.playwright.*;
import stepDefinitions.Utility;

import java.net.URLEncoder;

public class PlaywrightLambdaTest extends Utility {
    public static void main(String[] args) {

    }

//    playwright = Playwright.create();
//    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
//    context = browser.newContext();
//    page = context.newPage();

    public static void initiateBrowser() {
        try (Playwright playwright = Playwright.create()) {
            JsonObject capabilities = new JsonObject();
            JsonObject ltOptions = new JsonObject();

            String user = System.getenv("LT_USERNAME");
            String accessKey = System.getenv("LT_ACCESS_KEY");

            capabilities.addProperty("browsername", "Chrome"); // Browsers allowed: `Chrome`, `MicrosoftEdge`, `pw-chromium`, `pw-firefox` and `pw-webkit`
            capabilities.addProperty("browserVersion", "latest");
            ltOptions.addProperty("platform", "Windows 10");
            ltOptions.addProperty("name", "Playwright Test");
            ltOptions.addProperty("build", "Playwright Java Build");
            ltOptions.addProperty("user", user);
            ltOptions.addProperty("accessKey", accessKey);
            capabilities.add("LT:Options", ltOptions);

            BrowserType chromium = playwright.chromium();
            String caps = URLEncoder.encode(capabilities.toString(), "utf-8");
            String cdpUrl = "wss://cdp.lambdatest.com/playwright?capabilities=" + capabilities;
            browser = chromium.connect(cdpUrl);
            //page = browser.newPage();
            context = browser.newContext();
            page = context.newPage();

        } catch (Exception err) {
            setTestStatus("failed", err.getMessage(), page);
            err.printStackTrace();
        }

    }

    public static void setTestStatus(String status, String remark, Page page) {
        Object result;
        result = page.evaluate("_ => {}", "lambdatest_action: { \"action\": \"setTestStatus\", \"arguments\": { \"status\": \"" + status + "\", \"remark\": \"" + remark + "\"}}");
    }
}
