package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Construct {

    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("reflection.data.BasicData");

        System.out.println("====== constructors() =====");
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("====== declaredConstructors() =====");
        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            System.out.println("constructor = " + constructor);
        }

        System.out.println("====== () =====");

        Constructor<?> constructor = aClass.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Object instance = constructor.newInstance("hello");
        System.out.println("instance = " + instance);

        Method method1 = aClass.getDeclaredMethod("call");
        method1.invoke(instance);

    }
}
