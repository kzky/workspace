package edu.kzk.java_samle.reflection;

public class ModelImpl extends ModelBase {
    protected ModelImpl(){};
    
    public static ModelBase getInstance() {
        if (instance == null) {
            instance = new ModelImpl(); 
        }
        
        return instance;
    }
    
    @Override
    public void execute() {
        super.execute();
    }
    
}
