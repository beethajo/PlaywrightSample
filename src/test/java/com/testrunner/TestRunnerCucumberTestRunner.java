package com.testrunner;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/",
        glue = {"stepDefinitions"},
       // plugin = { "pretty", "html:src/test/reports/cucumber-reports.html" },
        plugin = {"pretty", "summary", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        tags = "@homeClearRecentSearches"
        )
public class TestRunnerCucumberTestRunner {

}

