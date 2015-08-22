function multiply() {
        
    var operand1 = $("form input[name='operand1']").val()
    var operand2 = $("form input[name='operand2']").val()
    /*
    var xhr = $.get("http://localhost:8080/jqury_sample/multiply", {"operand1": operand1, "operand2": operand2}, function(){
        alert("transmision success" + xhr.responseText)
    });
    */
   // xhr = // $.ajax自体の戻り値は，XMLHttpRequestなので，responseTextを参照してデータを取得  
   $.ajax({ 
       // dataにデータが入っている, dataTypeがtextだとそのまま表示できる
       // dataTypeがtext以外(json, xml, etc.)だと， そのobjectとして返される

       type: "GET",
       url: "http://localhost:8080/jqury_sample/multiply",
       data: {
        operand1: operand1,
        operand2: operand2
        },
       dataType: "text"
    }).done(function(data) { 
      // dataType: "json"にすると dataがobjectで返ってきて、jQuery.parseJSONが使えない
       // 代わりに、data.propertyで直接アクセス可能
       // （多分）xml 形式だと、上のようなことがパースしなくても可能
      
       
      // $().valはinputタグのvalue属性に使用する
       alert(data);
       var parseJSON = jQuery.parseJSON(data);
       var dat = parseJSON.operand1 + ", " + parseJSON.operand2 + ", " + parseJSON.result;
       $("input[name='multiply_result']").val(dat);
    }).fail(function(data) {
        alert("fail!");
    }).always(function(data) {
        alert("always");
     });
   
    //alert(xhr.responseText)// undefined
}

