var json = '{\
    "title": "サンプル",\
    "color":[\
    {"name": "赤色","colorName": "red","colorCode": "#ff0000"},\
    {"name": "青色","colorName": "blue","colorCode": "#0000ff"},\
    {"name": "緑色","colorName": "green","colorCode": "#008000"}]\
    }';
    
var parseJSON = jQuery.parseJSON( json );

function parse_json_sample() {
    var title = parseJSON.title;
    alert(title)
    var color = parseJSON.color; 
    $.each(color, function(index) {
        var elm = "name: " + color[index].name + ", colorName: " +  color[index].colorName + ", colorCode: " + color[index].colorCode 
        alert(elm)                  
    });    
}

