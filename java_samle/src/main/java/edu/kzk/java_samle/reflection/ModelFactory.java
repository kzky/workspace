package edu.kzk.java_samle.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ModelFactory {
    private ModelFactory(){};

    public static ModelBase createModel(String className) {
        try {
            Class<? extends ModelBase> clazz = 
                    (Class<? extends ModelBase>) Class.forName(className);

 
            // invoke method
            Method method = clazz.getMethod("getInstance");
            ModelBase model = (ModelBase) method.invoke(null);

            return model;

        } catch (ClassNotFoundException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.err.println("You have to implement singleton class with getInstance method");
            e.printStackTrace();
        }

        return null;
    }
}
