package com.computer_configurator.tests.components;

import com.computer_configurator.pages.AllComputersPage;
import com.computer_configurator.pages.ProductPage;
import com.computer_configurator.tests.base.BaseTest;
import com.computer_configurator.utils.CsvLogger;
import org.testng.annotations.Test;

public class ComponentCountTest extends BaseTest {

    @Test
    public void testComponentCount() {
        String fileName = System.getProperty("user.dir") + "/src/test/java/com/computer_configurator/csv_logs/ComponentCount.csv";
        try (CsvLogger csvLogger = new CsvLogger(fileName)) {
            csvLogger.writeEntry("Index,Component Count,Product Title,SKU,Product ID,URL,Missing Components");

            AllComputersPage allComputersPage = new AllComputersPage(driver);

            while (true) {
                allComputersPage.clickAcceptCookiesBtn();
                int totalProductsOnPage = allComputersPage.getTotalProductsOnPage();
                int index = 1;

                for (int i = 1; i <= totalProductsOnPage; i++) {
                    ProductPage productPage = allComputersPage.scrollToAndClickProduct(i);
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
                if (!allComputersPage.goToNextPage()) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("File does not exist...ending test"); // todo replace with logging
        }
    }
}