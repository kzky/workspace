$(function(){
	var is_show = -1;
	$("button").click(function(){
	if (is_show == -1) {
			$("div").show("slow");
			is_show = 1;
		} else {
			$("div").hide("slow");
			is_show = -1;			
		}
//		$("div").toggle(); // style of appearing differs
	});
});