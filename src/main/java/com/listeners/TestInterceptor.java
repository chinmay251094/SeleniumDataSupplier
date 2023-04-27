package com.listeners;

import com.annotations.TestDescription;
import com.supplier.SupplierReader;
import com.supplier.TestCountSupplier;
import one.util.streamex.StreamEx;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestInterceptor implements IMethodInterceptor {

    /**
     * This method intercepts the list of test methods to be executed and filters out
     * the methods that are not marked for execution in the test data spreadsheet.
     * It also sets the invocation count for the test methods based on the number of times
     * they need to be executed as per the test data spreadsheet. If the test method has
     * a TestDescription annotation, it adds the description to the testNG context.
     *
     * @param methods - list of test methods to be executed
     * @param context - the testNG context
     * @return filtered list of test methods to be executed
     */
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        List<IMethodInstance> filteredMethods = new ArrayList<>();
        for (IMethodInstance methodInstance : methods) {
            Method method = methodInstance.getMethod().getConstructorOrMethod().getMethod();
            StreamEx<TestCountSupplier> supplierStream = SupplierReader.getCountFromExcel(method);
            TestCountSupplier testCountSupplier = supplierStream
                    .filter(t -> t.getTestCase().equalsIgnoreCase(method.getName()))
                    .findFirst().orElse(null);
            if (testCountSupplier != null && testCountSupplier.isExecute() && testCountSupplier.getTestCount() > 0) {
                if (method.isAnnotationPresent(TestDescription.class)) {
                    TestDescription testDescription = method.getAnnotation(TestDescription.class);
                    String description = testDescription.description();
                    context.getCurrentXmlTest().addParameter("testDescription", description);
                }
                methodInstance.getMethod().setInvocationCount(testCountSupplier.getTestCount());
                filteredMethods.add(methodInstance);
            }
        }
        return filteredMethods;
    }
}

