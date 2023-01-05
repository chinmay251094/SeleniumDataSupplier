package com.digicorp.listeners;

import com.digicorp.constants.FrameworkConstants;
import com.digicorp.utils.ExcelReaderUtils;
import lombok.SneakyThrows;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MethodInterceptor implements IMethodInterceptor {
    @SneakyThrows
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        List<Map<String, String>> list = ExcelReaderUtils.getTestCaseDetails(FrameworkConstants.getRunManagerSheetName());
        List<IMethodInstance> result = new ArrayList<>();

        for (int i = 0; i < methods.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (methods.get(i).getMethod().getMethodName().equalsIgnoreCase(list.get(j).get("TestCase")) &&
                        list.get(j).get("Execute").equalsIgnoreCase("yes")) {
                        methods.get(i).getMethod().setDescription((list.get(j).get("TestCaseDescription")));
                        methods.get(i).getMethod().setInvocationCount(Integer.parseInt(list.get(j).get("Count")));
                        result.add(methods.get(i));
                    }
                }
            }
        return result;
    }
}
