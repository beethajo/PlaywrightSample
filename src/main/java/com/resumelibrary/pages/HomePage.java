package com.resumelibrary.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import java.util.List;

public class HomePage {
    Page page;
    // Locator — — — –
    String productName_1 = "id=item_4_title_link";

    String linksUnderJobsByIndustryTab = "//div[@id='home-jobs-by-industry']/ul/li";
    String linksUnderJobsByStateTab = "//div[@id='home-jobs-by-state']/ul/li";
    String linksUnderJobsByCityTab = "//div[@id='home-jobs-by-city']/ul/li";
    String linksUnderPopularJobsTab = "//div[@id='home-popular-jobs']/ul/li";

    //initialize Page using constructor
    public HomePage(Page page) {
        this.page = page;
    }


    //Method
    public String productName() {
        String productName = page.textContent(productName_1);
        return productName;
    }

    public String getTitle() {
        return page.title();
    }

    public void clickOnFindJobs() {
        page.click("#home-search-submit");
    }

    public void verifyUrl() {
        page.url().contains("/jobs");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void verifyThatSearchJobsNavigationBarButtonIsNotDisplayed() {
        page.isVisible("#top-search-jobs-btn");
    }

    public void enterJobTitle(String jobTitle) {
        page.locator("#q").type(jobTitle);
    }

    public void enterSearchLocation(String location) {
        page.locator("#search_loc").type(location);
    }

    public void selectDistance(String distance) {
        page.locator("#search_radius").selectOption(distance);
    }

    public void verifyURLText(String url) {
        page.url().contains(url);
    }

    public String getJobTitleAttributeValue() {
        return page.getAttribute("#q", "data-orig");
    }

    public String getLocationAttributeValue() {
        return page.getAttribute("#search_loc", "data-orig");
    }

    public String getDistanceAttributeValue() {
        return page.getAttribute("#search-results-distance", "data-orig");
    }

    public String veryfyH1Text(String text) {
        String xpath = "//h1[contains(text(),\"" + text + "\")]";
        return page.locator(xpath).textContent();

    }

    public String veryfyH2Text(String text) {
        String xpath = "//h2[contains(text(),\"" + text + "\")]";
        return page.locator(xpath).textContent();

    }

    public String veryfyH3Text(String text) {
        String xpath = "//h3[contains(text(),\"" + text + "\")]";
        return page.locator(xpath).textContent();

    }

    public void clickOnRecentSearchesLink() {
        page.locator(".recent-searches-desktop-btn").first().click();
    }

    public boolean verifyJobsByIndustry(String jobsByIndustry) {
        return compareTextFromElementUsingXpath(linksUnderJobsByIndustryTab, jobsByIndustry);
    }


    public boolean verifyJobsByState(String jobsByState) {
        return compareTextFromElementUsingXpath(linksUnderJobsByStateTab, jobsByState);
    }

    public boolean verifyJobsByCityTab(String jobsByCity) {
        return compareTextFromElementUsingXpath(linksUnderJobsByCityTab, jobsByCity);
    }

    public boolean verifyJobsByPopularJobsTab(String popularJobsTab) {
        return compareTextFromElementUsingXpath(linksUnderPopularJobsTab, popularJobsTab);
    }

    public boolean compareTextFromElementUsingXpath(String xpath, String text) {
        boolean textFromElement = false;
        List<ElementHandle> elements = page.querySelectorAll(xpath);
        for (ElementHandle element : elements) {
            String s = element.textContent();
            if (s.trim().equals(text.trim())) {
                textFromElement = true;
                break;
            }
        }
        return textFromElement;
    }

    public void loginAsCandidate() {
        page.navigate("https://www-master.ci.resume-library.com/login");
        page.locator("#email").fill("testers+candidate@resume-library.com");
        page.locator("#pass").fill("rltest01");
        page.locator("#signin-as-jobseeker").click();
    }

    public String verifyTextMessagePTags(String ptagText) {
        String xpath = "//p[contains(text(),\"" + ptagText + "\")]";
        String result = page.locator(xpath).textContent();
        return result;

    }
    public String verifyTextMessageH1Tags(String h1tagText) {
        String xpath = "//h1[contains(text(),\"" + h1tagText + "\")]";
        String result = page.locator(xpath).textContent();
        return result;

    }
}