package edu.kzk.java_samle.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CreateSingletonByDeclaredConstructorSample {

    public static void main(String[] args) {
        // class
        String className = "edu.kzk.java_samle.reflection.ModelImpl";

        try {
            Class<? extends ModelBase> clazz = 
                    (Class<? extends ModelBase>) Class.forName(className);

            // constructor
            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            ModelBase model = (ModelBase) constructor.newInstance();
            
            model.execute();

        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {

            e.printStackTrace();
        }

    }

}
