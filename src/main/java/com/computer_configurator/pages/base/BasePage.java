package com.computer_configurator.pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    public void set(By locator, String text) {
        find(locator).sendKeys(text);
    }

    public void clearAndSet(By locator, String text) {
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    public void click(By locator) {
        find(locator).click();
    }

    public void scrollTo(By locator) {
        new Actions(driver).scrollToElement(find(locator)).perform();
    }

    public void scrollToJS(By locator) {
        WebElement webElement = find(locator);
        String script = "arguments[0].scrollIntoView(true);";
        ((JavascriptExecutor) driver).executeScript(script, webElement);
    }

    public void scrollToAndClick(By locator) {
        scrollTo(locator);
        click(locator);
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
}