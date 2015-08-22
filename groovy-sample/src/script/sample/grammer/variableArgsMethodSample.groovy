import java.awt.TexturePaintContext.Int;

// 可変引数，デフォルト引数のサンプル

def m1(String s, int... xs) {
	println s
	for(i in xs) {
		println i
	}
}

m1("method with variable args", 10, -1, 1, 0)

def m2(String s, int x = 1, int y = 2, int z) {
	println s
	println x
	println y
	println z
}
m2("method with default value", 10)

def m3(String s, int y = 10, int z, int... xs) {
	println s
	for(i in xs) {
		println i
	}	
	println y
	println z	
}
m3("method with both", 1000, 10000, 10, 5, 6)
