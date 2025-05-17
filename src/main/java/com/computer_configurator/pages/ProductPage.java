package com.computer_configurator.pages;

import com.computer_configurator.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By productNameLoc = By.xpath("//h1[@class='product_title entry-title']");
    private final By skuLoc = By.xpath("//span[@class='sku']");
    private final By productIDLoc = By.xpath("//span[@class='product_id']/span");
    private final By componentsLoc = By.xpath("//select");
    private final By componentNamesLoc = By.xpath("//span[@class='component_title_text step_title_text']");
    private final List<String> componentMasterList = List.of("motherboard", "cpu (processor)", "cpu cooler", "ram", "graphics card", "professional sound card", "wi-fi & bluetooth card", "capture card", "operating system", "operating system drive", "storage drive 1", "storage drive 2", "power supply unit", "chassis fans", "case", "primary monitor", "secondary monitor", "color calibrator for monitor", "keyboard", "mouse", "mousepad");

    // Actions
    public String getProductTitle() {
        return find(productNameLoc).getText();
    }

    public String getSku() {
        return find(skuLoc).getText();
    }

    public String getProductID() {
        return find(productIDLoc).getText();
    }

    public int getNumOfComponents() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(componentsLoc));
        return findAll(componentsLoc).size();
    }

    public String getComponentName(int index) {
        return find(By.xpath("(//span[@class='component_title_text step_title_text'])[" + index + "]")).getText();
    }

    public List<String> getComponentNamesWithNoOptionSelected() {
        List<WebElement> all = findAll(By.xpath("//select/option[@selected and normalize-space()='Choose an option']/ancestor::div[@class='component_inner']/preceding-sibling::div//span[@class='component_title_text step_title_text']"));
        List<String> componentNames = new ArrayList<>();
        for (WebElement webElement : all) {
            componentNames.add(webElement.getText());
        }
        return componentNames;
    }

    // AI wrote this
//    public List<String> getComponentsName() {
//        return findAll(componentNamesLoc)
//                .stream()
//                .map(WebElement::getText)
//                .toList();
//    }

    public List<String> getAllComponentNamesPresentOnPage() {
        List<WebElement> componentsElem = findAll(componentNamesLoc);
        List<String> componentNames = new ArrayList<>();
        for (WebElement componentElem : componentsElem) {
            componentNames.add(componentElem.getText().toLowerCase());
        }
        return componentNames;
    }

    public List<String> getMissingComponents() {
        List<String> missingComponents = new ArrayList<>();
        List<String> componentNames = getAllComponentNamesPresentOnPage();
        for (String component : componentMasterList) {
            if (!componentNames.contains(component.toLowerCase())) {
                missingComponents.add(component);
            }
        }
        return missingComponents;
    }

    public int getExpectedNumOfComponents() {
        return 21;
    }
}