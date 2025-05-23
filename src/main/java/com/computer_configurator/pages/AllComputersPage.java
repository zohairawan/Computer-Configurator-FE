package com.computer_configurator.pages;

import com.computer_configurator.pages.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AllComputersPage extends BasePage {

    // Constructor
    public AllComputersPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By acceptCookiesBtnLoc = By.xpath("//button[normalize-space()='Accept']");
    private final By nextPageBtnLoc = By.xpath("//a[@class='next page-numbers']");
    private final By productsLoc = By.xpath("//ul[@class='products products_style_centered columns-2']/li");

    // Actions
    public void clickAcceptCookiesBtn() {
        try {
            WebElement acceptCookiesBtn = find(acceptCookiesBtnLoc);
            if (acceptCookiesBtn.isDisplayed()) {
                acceptCookiesBtn.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Cookies pop up not present...skipping click"); // todo delete | logging
        }
    }

    public int getTotalProductsOnPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productsLoc));
        return findAll(productsLoc).size();
    }

    public void scrollToProduct(int index) {
        scrollTo(By.xpath("(//h2[@class='woocommerce-loop-product__title'])[" + index + "]"));
    }

    public ProductPage clickOnProduct(int index) {
        click(By.xpath("(//h2[@class='woocommerce-loop-product__title'])[" + index + "]"));
        return new ProductPage(driver);
    }

    public ProductPage scrollToAndClickProduct(int index) {
        scrollToProduct(index);
        return clickOnProduct(index);
    }

    public void scrollToProductJS(int index) {
        scrollToJS(By.xpath("(//h2[@class='woocommerce-loop-product__title'])[" + index + "]"));
    }

    public ProductPage clickOnProductJS(int index) {
        clickJS(By.xpath("(//h2[@class='woocommerce-loop-product__title'])[" + index + "]"));
        return new ProductPage(driver);
    }

    public ProductPage scrollToAndClickProductJS(int index) {
        scrollToProductJS(index);
        return clickOnProductJS(index);
    }

    public String getProductName(int index) {
        return find(By.xpath("(//h2[@class='woocommerce-loop-product__title']/a)[1]")).getText();
    }

    public boolean isCustomBuiltComputer(int index) {
        return getProductName(index).toLowerCase().contains("custom built");
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public boolean hasNextPageBtn() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(nextPageBtnLoc));
            return find(nextPageBtnLoc).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Next page button not found...ending program"); // todo replace with logging
            return false;
        }
    }

    public boolean goToNextPage() {
        if (hasNextPageBtn()) {
            scrollToAndClickNextPageBtn();
            return true;
        }
        return false;
    }

    public void scrollToAndClickNextPageBtn() {
        scrollToAndClick(nextPageBtnLoc);
    }
}