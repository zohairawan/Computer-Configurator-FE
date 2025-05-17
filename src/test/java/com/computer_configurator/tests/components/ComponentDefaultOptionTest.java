package com.computer_configurator.tests.components;

import com.computer_configurator.pages.AllComputersPage;
import com.computer_configurator.pages.ProductPage;
import com.computer_configurator.tests.base.BaseTest;
import com.computer_configurator.utils.CsvLogger;
import org.testng.annotations.Test;

import java.util.List;

public class ComponentDefaultOptionTest extends BaseTest {

    @Test
    public void testComponentDefaultOption() {
        try (CsvLogger csvLogger = new CsvLogger("ComponentDefaultOption")) {
            csvLogger.writeEntry("Index,Product Title,SKU,Product ID,URL,Component with incorrect default option");

            AllComputersPage allComputersPage = new AllComputersPage(driver);
            int index = 1;

//            while (true) {
            for (int z = 0; z < 1; z++) {
                allComputersPage.clickAcceptCookiesBtn();
                int totalProductsOnPage = allComputersPage.getTotalProductsOnPage();
                for (int i = 0; i < totalProductsOnPage; i++) {
                    if (!allComputersPage.isCustomBuiltComputer(i + 1)) {
                        ProductPage productPage = allComputersPage.scrollToAndClickProduct(i + 1);
                        try {
                            List<String> componentNamesWithNoOptionSelected = productPage.getComponentNamesWithNoOptionSelected();
                            for (String s : componentNamesWithNoOptionSelected) { // todo delete
                                System.out.println(s);
                            }
                            if (!componentNamesWithNoOptionSelected.isEmpty()) {
                                csvLogger.writeFormattedEntry(
                                        index++, productPage.getProductTitle(), productPage.getSku(),
                                        productPage.getProductID(), productPage.getCurrentURL(),
                                        componentNamesWithNoOptionSelected
                                );
                            }
                        } catch (Exception e) {
                            System.out.println("All default component options selected");
                        }
                        driver.navigate().back();
                    }
                }
            }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}