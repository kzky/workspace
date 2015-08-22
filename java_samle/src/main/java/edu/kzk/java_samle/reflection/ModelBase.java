package edu.kzk.java_samle.reflection;

public class ModelBase implements Model {
    protected static ModelBase instance; 
    protected ModelBase(){};
    
    public void execute() {System.out.println("Execute!");};
}
