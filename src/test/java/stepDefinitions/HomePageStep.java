package stepDefinitions;

import com.microsoft.playwright.ElementHandle;
import com.resumelibrary.pages.HomePage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePageStep extends Utility {
    HomePage home = new HomePage(page);


    @Given("I am on home Page")
    public void i_am_on_home_page() {
        page.navigate("https://www-master.ci.resume-library.com");
        //page.navigate("https://www.resume-library.com");

    }

    @When("I see title")
    public void i_see_title() {
        String pageTitle = "Job Search USA - Browse jobs in your area | Resume-Library.com";
        Assert.assertEquals(pageTitle, home.getTitle());
    }

    @When("I click on find jobs")
    public void i_click_on_find_jobs() {
        home.clickOnFindJobs();

    }

    @When("I should be on page")
    public void i_should_be_on_page() {
        home.verifyUrl();

    }

    @Given("I am on page {string}")
    public void iAmOnPage(String url) {
        page.navigate(getWebUrl() + url);
    }

    @Then("I should be on page {string}")
    public void iShouldBeOnPage(String url) {
        waitFor(2);
        Assert.assertEquals(getWebUrl() + getURL(url), page.url());
    }

    @Then("I should not see search jobs navigation bar button")
    public void iShouldNotSeeSearchJobsNavigationBarButton() {
        home.verifyThatSearchJobsNavigationBarButtonIsNotDisplayed();
    }

    @And("I should see text H one tag {string}")
    public void iShouldSeeTextHOneTag(String text) {
        Assert.assertEquals(home.veryfyH1Text(text), text);

    }

    @And("I should see text H two tag {string}")
    public void iShouldSeeTextHTwoTag(String text) {
        Assert.assertEquals(home.veryfyH2Text(text), text);

    }

    @And("I should see text H three tag {string}")
    public void iShouldSeeTextHThreeTag(String text) {
        Assert.assertEquals(home.veryfyH3Text(text), text);
    }

    @And("I fill in search title field with {string}")
    public void iFillInSearchTitleFieldWith(String jobTitle) {
        home.enterJobTitle(jobTitle);
    }

    @And("I fill in search location with {string}")
    public void iFillInSearchLocationWith(String location) {
        home.enterSearchLocation(location);
    }

    @And("I select {string} from search distance")
    public void iSelectFromSearchDistance(String distance) {
        home.selectDistance(distance);
    }

    @And("I click on find jobs button")
    public void iClickOnFindJobsButton() {
        home.clickOnFindJobs();
    }

    @Then("I should be able to see in browser URL {string}")
    public void iShouldBeAbleToSeeInBrowserURL(String url) {
        home.verifyURLText(url);
    }

    @And("the title field should contain {string}")
    public void theTitleFieldShouldContain(String text) {
        Assert.assertEquals(home.getJobTitleAttributeValue(), text);
    }

    @And("the location field should contain {string}")
    public void theLocationFieldShouldContain(String text) {
        Assert.assertEquals(home.getLocationAttributeValue(), text);
    }

    @And("the distance field should contain {string}")
    public void theDistanceFieldShouldContain(String text) {
        Assert.assertEquals(home.getDistanceAttributeValue(), text);
    }

    public void waitFor(int timeout) {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("I wait for {string} seconds")
    public void iWaitForSeconds(String seconds) {
        waitFor(Integer.parseInt(seconds));
    }

    @Given("I navigate to page {string}")
    public void iNavigateToPage(String url) {
        page.navigate(getWebUrl() + getURL(url));
    }

    @And("I should see text {string}")
    public void iShouldSeeText(String textMessage) {
        String xpath = "//*[contains(text(),\"" + textMessage + "\")]";
        assertThat(page.locator(xpath).first()).hasText(textMessage);
    }

    @And("I click on recent searches link")
    public void iClickOnRecentSearchesLink() {
        home.clickOnRecentSearchesLink();
    }

    @And("I click on {string}")
    public void iClickOn(String text) {
        String xpath = "//*[contains(text(),\"" + text + "\")]";
        page.locator(xpath).first().click();
    }

    @And("I click on clear recent searches link")
    public void iClickOnClearRecentSearchesLink() {
        //page.locator(".clear-recent-searches").click();
        iClickOn("Clear recent searches");
    }

    @And("I confirm browser popup")
    public void iConfirmBrowserPopup() {

    }

    @When("I should not see {string}")
    public void iShouldNotSee(String text) {
        Assert.assertFalse(page.locator("//*[contains(text(),\"" + text + "\")]").isVisible());
    }

    @And("I move backward one page")
    public void iMoveBackwardOnePage() {
        page.goBack();
    }

    @Then("I should see resume library logo")
    public void iShouldSeeResumeLibraryLogo() {
        String xpath = "//a[@class='rl-logo-svg  USA']";
        Assert.assertTrue(page.locator(xpath).isVisible());
    }

    @Then("I should see linkText {string} under Jobs by Industry tab")
    public void iShouldSeeLinkTextUnderJobsByIndustryTab(String jobsByIndustry) {
        Assert.assertTrue(home.verifyJobsByIndustry(jobsByIndustry));
    }

    @Then("I should see linkText {string} under Jobs by State tab")
    public void iShouldSeeLinkTextUnderJobsByStateTab(String jobsByState) {
        Assert.assertTrue(home.verifyJobsByState(jobsByState));
    }

    @Then("I should see linkText {string} under Jobs by City tab")
    public void iShouldSeeLinkTextUnderJobsByCityTab(String jobsByCity) {
        Assert.assertTrue(home.verifyJobsByCityTab(jobsByCity));
    }

    @Then("I should see linkText {string} under Popular Jobs tab")
    public void iShouldSeeLinkTextUnderPopularJobsTab(String PopularJobsTab) {
        Assert.assertTrue(home.verifyJobsByPopularJobsTab(PopularJobsTab));
    }

    @Then("I login as a candidate")
    public void iLoginAsACandidate() {
        home.loginAsCandidate();
    }

    @When("I click on {string} Header Link")
    public void iClickOnHeaderLink(String headerLink) {
        ClickOnHeaderLink(headerLink);
    }

    public void ClickOnHeaderLink(String headerLink) {

        switch (headerLink) {
            case "My Dashboard":
                page.locator("#nav_my_account").click();
                break;
            case "My Profile":
                page.locator("#nav_modify_resume").click();
                break;
            case "Job Alerts":
                page.locator("#nav_jobalerts").click();
                break;
            case "Saved Jobs":
                page.locator("//nav[@label='Site Navigation']/ul/li/a[text()='Saved Jobs']").click();
                break;
            case "My Email Preferences":
                page.locator("//a[@class='account-link']").hover();
                page.locator("//li/a[text()='My Email Preferences']").click();
                break;
            case "My Applications":
                page.locator("//a[@class='account-link']").hover();
                page.locator("//li/a[text()='My Applications']").click();
                break;
            case "My Settings":
                page.locator("//a[@class='account-link']").hover();
                page.locator("//li[@id='user-nav']/ul/li/a[text()='My Settings']").click();
                break;
            case "Logout":
                page.locator("//a[@class='account-link']").hover();
                page.locator("//li[@class='logout']/a[text()='Logout']").click();
                break;
            case "Login":
                page.locator("//li[@class='logout']/a[text()='Login']").click();

                break;
            default:
                break;
        }
    }

    @And("I should see text p tag {string} and verify message {string}")
    public void iShouldSeeTextPTagAndVerifyMessage(String ptagText, String textTobeVerified) {
        Assert.assertEquals(home.verifyTextMessagePTags(ptagText), textTobeVerified);
    }

    @And("I should see text h one tags {string} and verify message {string}")
    public void iShouldSeeTextHOneTagsAndVerifyMessage(String honeTag, String textToBeVerified) {
        Assert.assertEquals(home.verifyTextMessageH1Tags(honeTag), textToBeVerified);
    }
}
