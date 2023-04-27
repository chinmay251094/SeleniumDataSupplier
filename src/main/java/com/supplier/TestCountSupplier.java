package com.supplier;

import com.creditdatamw.zerocell.annotation.Column;
import com.creditdatamw.zerocell.converter.BooleanConverter;
import com.creditdatamw.zerocell.converter.IntegerConverter;
import io.github.sskorol.data.Sheet;

/**
 * This class represents a Test Count Supplier which is used to retrieve the number of times a specific test case
 * should be executed. It contains private fields representing the details of the test case, including the name,
 * a flag indicating whether it should be executed, a description, and the number of times it should be executed.
 * <p>
 * The class is annotated with "@Sheet(name = "Count")" which indicates that it is associated with an Excel sheet named "Count".
 * <p>
 * The class provides getters for each private field which can be used to access their respective values.
 * Additionally, the fields are annotated with "@Column" which maps the fields to a column in the Excel sheet.
 * The annotations also include the index of the column and the converter class to be used for converting the value to the
 * appropriate data type.
 */
@Sheet(name = "Count")
public class TestCountSupplier {
    @Column(name = "TestCase", index = 0)
    private String testCase;
    @Column(name = "Execute", index = 1, converterClass = BooleanConverter.class)
    private boolean execute;
    @Column(name = "TestDescription", index = 2)
    private String testDescription;
    @Column(name = "Count", index = 3, converterClass = IntegerConverter.class)
    private int testCount;

    public boolean isExecute() {
        return execute;
    }

    public String getTestCase() {
        return testCase;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public int getTestCount() {
        return testCount;
    }
}
