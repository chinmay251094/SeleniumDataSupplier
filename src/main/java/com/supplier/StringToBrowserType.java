package com.supplier;

import com.creditdatamw.zerocell.converter.Converter;
import com.enums.Browsers;

/**
 * A converter class that converts a string value to a {@link Browsers} enum.
 */
public class StringToBrowserType implements Converter<Browsers> {

    /**
     * Converts the given string value to a {@link Browsers} enum.
     *
     * @param value        the string value to convert.
     * @param columnNumber the column number of the value.
     * @param rowNumber    the row number of the value.
     * @return the converted {@link Browsers} enum value.
     */
    @Override
    public Browsers convert(String value, String columnNumber, int rowNumber) {
        return Browsers.valueOf(value.toUpperCase());
    }
}
