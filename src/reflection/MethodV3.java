package reflection;

import reflection.data.Calculator;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class MethodV3 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Calculator calculator = new Calculator();
        Class<? extends Calculator> calculatorInstance = calculator.getClass();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("add? sub? 입력: ");
            String methodName = sc.nextLine();
            System.out.println("인자 1 : ");
            int arg1 = sc.nextInt();
            System.out.println("인자 2 : ");
            int arg2 = sc.nextInt();

            sc.nextLine();

            Object resultValue = calculatorInstance.getDeclaredMethod(methodName, int.class, int.class).invoke(calculator, arg1, arg2);
            System.out.println("resultValue = " + resultValue);
            System.out.println();
        }

    }

}
