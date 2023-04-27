package com.supplier;

import com.creditdatamw.zerocell.converter.Converter;
import com.enums.RunModes;
/**
 * This class implements the Converter interface to convert a string value to a RunModes enum value.
 * <p>
 * It converts the input string to upper case and returns the corresponding RunModes enum value.
 */
public class StringToRunMode implements Converter<RunModes> {

    /**
     * Converts the input string to a RunModes enum value.
     *
     * @param value        the string value to be converted.
     * @param columnNumber the column number of the input value in the data source.
     * @param rowNumber    the row number of the input value in the data source.
     * @return the converted RunModes enum value.
     */
    @Override
    public RunModes convert(String value, String columnNumber, int rowNumber) {
        return RunModes.valueOf(value.toUpperCase());
    }
}
