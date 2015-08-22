package edu.kzk.scala_sample.basic.function


object FunctionSample {

    def main(args: Array[String]) {
        var a = 10;
        var b = 5;
        
        println("### 通常メソッド ###");
        println(add(a, b));
        println();
        
        println("### void相当関数 ###")
        print("void相当関数");
        println();

        println("### 関数を受け取る関数 ###")
        val func = funcOfFunc(add);
        println(func(a, b));
        println();

        println("### 関数リテラル ###");
        println(functionLiteral(a, b));
        println();
        
        println("### 関数を受け取る関数(関数リテラル) ###");
        val func1 = funcOfFunc(functionLiteral);
        println(func1(a, b));
        
    }
    /*
     * メソッド 
     * 基本構文
     * def function(arg: arg_type): ret_type => {processing}
     */
    // 通常メソッド
    def add(x: Int, y: Int): Int = {
            x + y;
    }
    // 戻り型省略
    def add2(x: Int, y: Int) = {
        x + y;
    }

    // {} 省略
    def add3(x: Int, y: Int) = x + y;

    // = 省略
    def add4(x: Int, y: Int) {x + y};

    // void相当
    def print(str: String) = println(str);

    // 関数を引数に取る関数
    // 基本構文 
    // def function(f: (arg_type) => ret_type) = {processing with f}
    def funcOfFunc(f: (Int, Int) => Int) = {f};
    // def funcOfFunc(f: (a: Int, b: Int) => {a + b}: Int) = {f(a, b)};はできない．

    // 関数を返す関数 
    // 関数の引数型=>関数の戻り型をメソッドの戻り値としている
    def retFnc1(): String => String = { (s: String) => {"return " + s} };

    /*
     * 関数リテラル
     * 基本構文 
     * (arg: type) => {}: ret_type           
     */
    val functionLiteral = (x: Int, y: Int) => {x + y}: Int;
}
