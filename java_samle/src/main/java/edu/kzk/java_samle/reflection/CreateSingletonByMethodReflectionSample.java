package edu.kzk.java_samle.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CreateSingletonByMethodReflectionSample {

    public static void main(String[] args) {
        
        // class
        String modelName = "edu.kzk.java_samle.reflection.ModelImpl";

        try {
            Class<? extends ModelBase> clazz = 
                    (Class<? extends ModelBase>) Class.forName(modelName);
            
            // show methods
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                System.out.println(method.toString());
            }
            
            System.out.println("");
            
            // invoke method
            Method method = clazz.getMethod("getInstance");
            ModelBase model = (ModelBase) method.invoke(null);
            
            model.execute();
            
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            
            e.printStackTrace();
        }
        
        
    }

}
