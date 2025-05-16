package com.computer_configurator.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvLogger implements AutoCloseable{

    private final PrintWriter writer;

    public CsvLogger(String filePath) throws IOException {
        this.writer = new PrintWriter(new FileWriter(filePath));
    }

    public void writeEntry(String entry) {
        writer.println(entry);
        writer.flush();
    }

    public void writeFormattedEntry(int index, String productTitle, String productSku, String productID, String productUrl, List<String> incorrectItems) {
        String formattedIncorrectItems = String.join(" /// ", incorrectItems);
        writeEntry(String.format("%d,%s,%s,%s,%s,%s",
                index, productTitle, productSku, productID, productUrl, formattedIncorrectItems));
    }

    public void writeFormattedEntryForMissingComponents(int index, int actualNumOfComponents, int expectedNumOfComponents, String productTitle, String productSku, String productID, String productUrl, List<String> incorrectItems) {
        String formattedIncorrectComponents = String.join(" /// ", incorrectItems);
        writeEntry(String.format(
                "%d,%d/%d,%s,%s,%s,%s,%s",
                index, actualNumOfComponents, expectedNumOfComponents,
                productTitle, productSku, productID,
                productUrl, formattedIncorrectComponents));
    }

    @Override
    public void close() throws Exception {
        writer.close();
    }
}