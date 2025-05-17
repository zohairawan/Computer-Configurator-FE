package com.computer_configurator.tests.components;

import com.computer_configurator.pages.AllComputersPage;
import com.computer_configurator.pages.ProductPage;
import com.computer_configurator.tests.base.BaseTest;
import com.computer_configurator.utils.CsvLogger;
import org.testng.annotations.Test;

public class ComponentCountTest extends BaseTest {

    @Test
    public void testComponentCount() {
        try (CsvLogger csvLogger = new CsvLogger("ComponentCount")) {
            csvLogger.writeEntry("Index,Component Count,Product Title,SKU,Product ID,URL,Missing Components");

            AllComputersPage allComputersPage = new AllComputersPage(driver);
            int index = 1;

//            while (true) {
            for (int j = 0; j < 1; j++) {
                allComputersPage.clickAcceptCookiesBtn();
                int totalProductsOnPage = allComputersPage.getTotalProductsOnPage();

                for (int i = 0; i < totalProductsOnPage; i++) {
                    ProductPage productPage = allComputersPage.scrollToAndClickProduct(i + 1);
                    int actualNumOfComponents = productPage.getNumOfComponents();

                    if (actualNumOfComponents != productPage.getExpectedNumOfComponents()) {
                        csvLogger.writeFormattedEntryForMissingComponents(
                                index++, actualNumOfComponents, productPage.getExpectedNumOfComponents(),
                                productPage.getProductTitle(), productPage.getSku(), productPage.getProductID(),
                                productPage.getCurrentURL(), productPage.getMissingComponents());
                    }
                    driver.navigate().back();
//                    allComputersPage.refreshPage(); // todo uncomment
                }
            }
//                if (!allComputersPage.goToNextPage()) {
//                    break;
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("File does not exist...ending test"); // todo replace with logging
        }
    }
}