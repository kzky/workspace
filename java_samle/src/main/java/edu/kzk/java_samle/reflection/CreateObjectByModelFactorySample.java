package edu.kzk.java_samle.reflection;

public class CreateObjectByModelFactorySample {

    public static void main(String[] args) {

        // classname
        String className = "edu.kzk.java_samle.reflection.ModelImpl";

        ModelBase model = ModelFactory.createModel(className);
        model.execute();
    }

}
