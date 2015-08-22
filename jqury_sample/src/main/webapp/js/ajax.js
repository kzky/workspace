function multiply() {
        
    var operand1 = $("form input[name='operand1']").val()
    var operand2 = $("form input[name='operand2']").val()
    /*
    var xhr = $.get("http://localhost:8080/jqury_sample/multiply", {"operand1": operand1, "operand2": operand2}, function(){
        alert("transmision success" + xhr.responseText)
    });
    */
   
   xhr = $.ajax({ // 受け取らないとダメ、dataにデータが入っているわけではない
       type: "get",
       url: "http://localhost:8080/jqury_sample/multiply",
       data: {
        operand1: operand1,
        operand2: operand2
        },
       dataTyep: "json"
    })
   .done(function(data) {
      //$("input[name='multiply_result']").attr("value", xhr.responseText);
       
       alert(xhr.resposeText)// XmlDocument obj
      // $("input[name='multiply_result']").val(xhr.responseText)
        //$("input[name='multiply_result']").text(xhr.responseText) //
        // $().text(val)は、input tagには使用しない -> inputタグは閉じるがないから.textではない
       //var parseJSON = jQuery.parseJSON(xhr.responseText);
       //var dat = parseJSON.operand1 + ", " + parseJSON.operand2 + ", " + parseJSON.result
    })
   .fail(function(data) {alert("fail!")}) 
   .always(function(data) {alert("always")})
   
    //alert(xhr.responseText)// undefined
}

