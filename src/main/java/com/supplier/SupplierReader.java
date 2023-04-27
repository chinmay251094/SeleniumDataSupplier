package com.supplier;

import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.XlsxReader;
import one.util.streamex.StreamEx;

import java.lang.reflect.Method;

import static io.github.sskorol.data.TestDataReader.use;

/**
 * This class provides data suppliers that read data from an Excel file.
 */
public class SupplierReader {

    /**
     * Returns a stream of test data from an Excel file for the given test method.
     * Only data marked for execution and matching the test method name will be included.
     *
     * @param method The test method for which to get data.
     * @return A stream of {@link TestDataSupplier} objects.
     */
    @DataSupplier(runInParallel = true)
    public static StreamEx<TestDataSupplier> getDataFromExcel(Method method) {
        return use(XlsxReader.class)
                .withTarget(TestDataSupplier.class)
                .withSource("TestingFile.xlsx")
                .read()
                .filter(testDataSupplier -> testDataSupplier.isExecute() && testDataSupplier.getTestcase().equalsIgnoreCase(method.getName()));
    }

    /**
     * Returns a stream of test count data from an Excel file for the given test method.
     * Only data marked for execution and matching the test method name will be included.
     *
     * @param method The test method for which to get data.
     * @return A stream of {@link TestCountSupplier} objects.
     */
    @DataSupplier
    public static StreamEx<TestCountSupplier> getCountFromExcel(Method method) {
        return use(XlsxReader.class)
                .withTarget(TestCountSupplier.class)
                .withSource("TestingFile.xlsx")
                .read()
                .filter(testDataSupplier -> testDataSupplier.isExecute() && testDataSupplier.getTestCase().equalsIgnoreCase(method.getName()));
    }
}

