package edu.kzk.java_samle.basic.enumerate;

public enum MyEnum {
    HOGE,
    FUGA,
    FOO,
    ;
    
    private int id;

    private MyEnum(int id) {
        this.id = id;
    }
    
    private MyEnum() {
        
    }


    public int getId() {
        return id;
    }
}
