/**
 * Configures OS, browser, and URL
 * Stores fields/methods that every test will require
 */

package com.computer_configurator.tests.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    @Parameters({"browser"})
    public void setUp(@Optional String browser) {
        if (browser != null) {
            switch (browser.toLowerCase()) {
                case "chrome" -> driver = new ChromeDriver();
                case "edge" -> driver = new EdgeDriver();
                case "firefox" -> driver = new FirefoxDriver();
                default -> {
                    return;
                }
            }
        } else {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
    }

    @BeforeMethod
    @Parameters({"url"})
    public void loadApplication(String url) {
        driver.get(url);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}