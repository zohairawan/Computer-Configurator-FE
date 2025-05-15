package com.computer_configurator.pages;

import com.computer_configurator.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AllComputersPage extends BasePage {

    // Constructor
    public AllComputersPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By acceptCookiesBtnLoc = By.xpath("//button[normalize-space()='Accept']");

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

    public ProductPage clickOnProduct(int index) {
        click(By.xpath("(//h2[@class='woocommerce-loop-product__title'])["+index+"]"));
        return new ProductPage(driver);
    }
}