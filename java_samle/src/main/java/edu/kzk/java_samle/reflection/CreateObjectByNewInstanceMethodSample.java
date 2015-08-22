package edu.kzk.java_samle.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CreateObjectByNewInstanceMethodSample {

    public static void main(String[] args) {
        // class
        String className = "edu.kzk.java_samle.reflection.ModelImpl";

        try {
            Class<? extends ModelBase> clazz = 
                    (Class<? extends ModelBase>) Class.forName(className);

            // new 
            ModelBase model = (ModelBase) clazz.newInstance();

            model.execute();

        } catch (ClassNotFoundException | SecurityException | IllegalAccessException | IllegalArgumentException | InstantiationException e) {

            e.printStackTrace();
        }

    }

}
