$(function(){
	$("dd:not(:first)").css("display", "none");
	$("dl dt").click(function(){
		if($("+dd", this).css("display") == "none"){
			$("dd").slideUp("slow");
			$("+dd", this).slideDown("slow");
		}
	});
});