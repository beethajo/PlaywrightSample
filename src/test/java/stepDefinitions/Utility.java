package stepDefinitions;

import com.microsoft.playwright.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Utility {

    private static final Logger logger = LogManager.getLogger(Utility.class);
    static Playwright playwright;
    static Browser browser;
    static BrowserContext context;
    static Page page;
    public String getWebUrl() {
        return "https://www-master.ci.resume-library.com";
    }

    public synchronized String getURL(String textUrl) {
        URLConstants urlConstantsObj = new URLConstants();
        if (!textUrl.contains("/")) {
            String url = "";
            String[] splitUrlTexts = textUrl.split(" ");
            for (int i = 0; i < splitUrlTexts.length; i++) {
                if (i == (splitUrlTexts.length - 1)) {
                    url += splitUrlTexts[i].toUpperCase();
                } else {
                    url += splitUrlTexts[i].toUpperCase() + "_";
                }
            }
            logger.info("url is --> " + url);
            return urlConstantsObj.getConstantsURL(url);

        } else {
            return textUrl;
        }
    }

}
