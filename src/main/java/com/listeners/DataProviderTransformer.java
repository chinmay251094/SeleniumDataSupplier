package com.listeners;

import com.supplier.SupplierReader;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IDataProvidable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static io.github.sskorol.utils.ReflectionUtils.getDataSupplierAnnotation;
import static io.github.sskorol.utils.ReflectionUtils.getDataSupplierClass;
import static java.util.Objects.nonNull;

public class DataProviderTransformer implements IAnnotationTransformer {

    @SuppressWarnings("unchecked")
    public void transform(final ITestAnnotation annotation, final Class testClass, final Constructor testConstructor, final Method testMethod) {
        assignCustomDataSupplier(annotation, testMethod, testClass);
    }

    public void transform(final IFactoryAnnotation annotation, final Method testMethod) {
        assignCustomDataSupplier(annotation, testMethod, null);
    }

    @SuppressWarnings("FinalLocalVariable")
    private <T> void assignCustomDataSupplier(final IDataProvidable annotation, final Method testMethod, final Class<T> testClass) {
        var dataSupplierClass = getDataSupplierClass(annotation, testClass, testMethod);
        var dataSupplierAnnotation = getDataSupplierAnnotation(dataSupplierClass, annotation.getDataProvider());
        if (!annotation.getDataProvider().isEmpty() && nonNull(dataSupplierAnnotation)) {
            annotation.setDataProviderClass(SupplierReader.class);
            var dataProviderName = "getDataFromExcel";
            annotation.setDataProvider(dataProviderName);
        }
    }
}

