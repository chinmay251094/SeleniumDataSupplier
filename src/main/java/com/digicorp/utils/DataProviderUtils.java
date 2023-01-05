package com.digicorp.utils;

import com.digicorp.constants.FrameworkConstants;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataProviderUtils {
    private static List<Map<String, String>> completeList = new ArrayList<>();

    @DataProvider(parallel = true)
    public Object[] getData(Method method) throws IOException {
        String testCase = method.getName();
        if (completeList.isEmpty()) {
            completeList = ExcelReaderUtils.getTestCaseDetails(FrameworkConstants.getDataManagerSheetName());
        }

        List<Map<String, String>> casesToBeExecuted = new ArrayList<>();

        for (int i = 0; i < completeList.size(); i++) {
            if (completeList.get(i).get("TestCase").equalsIgnoreCase(testCase) &&
                    completeList.get(i).get("Execute").equalsIgnoreCase("yes")) {
                casesToBeExecuted.add(completeList.get(i));
            }
        }
        return casesToBeExecuted.toArray();
    }
}
