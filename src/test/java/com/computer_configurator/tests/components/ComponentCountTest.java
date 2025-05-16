package com.computer_configurator.tests.components;

import com.computer_configurator.pages.AllComputersPage;
import com.computer_configurator.pages.ProductPage;
import com.computer_configurator.tests.base.BaseTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class ComponentCountTest extends BaseTest {

    @Test
    public void testComponentCount() {
        // Create a log file
        String fileName = System.getProperty("user.dir") + "/src/test/java/com/computer_configurator/csv_logs/ComponentCount.csv";
        try (PrintWriter csvFile = new PrintWriter(fileName)) {
            csvFile.println("Index,Component Count,Product Title,SKU,Product ID,URL,Missing Components");

            AllComputersPage allComputersPage = new AllComputersPage(driver);
            while (true) {
                allComputersPage.clickAcceptCookiesBtn();
                int totalProductsOnPage = allComputersPage.getTotalProductsOnPage();
                int index = 1;
                for (int i = 1; i <= totalProductsOnPage; i++) {
                    ProductPage productPage = allComputersPage.scrollToAndClickProduct(i);
                    int actualNumOfComponents = productPage.getNumOfComponents();
                    if (actualNumOfComponents != productPage.getExpectedNumOfComponents()) {
                        StringBuilder missingComponents = new StringBuilder();
                        List<String> componentNames = productPage.getComponentsName();
                        List<String> componenetNamesMasterList = productPage.getComponentMasterList();
                        for (String componentNameML : componenetNamesMasterList) {
                            if (componentNames.stream().noneMatch(name -> name.equalsIgnoreCase(componentNameML))) {
                                if (!missingComponents.isEmpty()) {
                                    missingComponents.append(" /// ");
                                }
                                missingComponents.append(componentNameML);
                            }
                        }
                        csvFile.print(index++);
                        csvFile.print(",");
                        csvFile.print(actualNumOfComponents + "/" + productPage.getExpectedNumOfComponents());
                        csvFile.print(",");
                        csvFile.print(productPage.getProductTitle());
                        csvFile.print(",");
                        csvFile.print(productPage.getSku());
                        csvFile.print(",");
                        csvFile.print(productPage.getProductID());
                        csvFile.print(",");
                        csvFile.print(productPage.getCurrentURL());
                        csvFile.print(",");
                        csvFile.print(missingComponents);
                        csvFile.println();
                    }

                    driver.navigate().back();
//                    allComputersPage.refreshPage(); // todo uncomment
                }

                // Click on next page - Pagination
                if (!allComputersPage.goToNextPage()) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist...ending test"); // todo replace with logging
        }
    }
}