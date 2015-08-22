println "Groovy version"
pattern = /..../

// ~ はPatternオブジェクトを生成する．
println ~pattern instanceof Pattern  

// =~はMactcherオブジェクトを生成する．
println (("hoge"=~pattern) instanceof java.util.regex.Matcher) 
println "hoge" ==~ pattern

// 定番の構文
myFairStringy = 'The rain in Spain stays mainly in the plain!';
rhyme = /\b\w*ain\b/;

//***** パターン検索 (1) *****
found = '';
(myFairStringy =~ rhyme).each{ match ->
    found += match + ' ';
	println match
}
//assert found == 'rain Spain plain ';


//***** パターン検索 (2) : グループを使う *****
('xy' =~ /(.)(.)/).each{ all, elm1, elm2 ->
  println all
  println elm1
  println elm2
}
 
