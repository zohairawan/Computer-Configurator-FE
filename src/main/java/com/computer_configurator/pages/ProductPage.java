package com.computer_configurator.pages;

import com.computer_configurator.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By productNameLoc = By.xpath("//h1[@class='product_title entry-title']");
    private final By skuLoc = By.xpath("//span[@class='sku']");
    private final By productIDLoc = By.xpath("//span[@class='product_id']/span");
    private final By componentsLoc = By.xpath("//select");

    // Actions
    public int getNumOfComponents() {
        return findAll(componentsLoc).size();
    }
}