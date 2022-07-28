package homework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;

public class Application {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //args = new String[]{"homework.HomeworkTest"};
        for (String testClassName: args) {
            Class<?> clazz = Class.forName(testClassName);
            Method[] methods = clazz.getMethods();

            List<Method> beforeMethods = Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(Before.class))
                    .toList();
            List<Method> testMethods = Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(Test.class))
                    .toList();
            List<Method> afterMethods = Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(After.class))
                    .toList();

            int totalCount = testMethods.size();
            int failCount = 0;
            for (Method testMethod : testMethods) {
                boolean isSuccessful = true;
                Object instance = clazz.getDeclaredConstructor().newInstance();
                for (Method beforeMethod: beforeMethods) {
                    beforeMethod.invoke(instance);
                }
                try {
                    testMethod.invoke(instance);
                } catch (Exception e) {
                    isSuccessful = false;
                    failCount++;
                }
                for (Method afterMethod: afterMethods) {
                    afterMethod.invoke(instance);
                }
                System.out.println(testMethod.getName() + " - " + isSuccessful);
            }
            System.out.println("total - " + totalCount);
            System.out.println("successful - " + (totalCount - failCount));
            System.out.println("fail - " + failCount);
        }
    }
}
