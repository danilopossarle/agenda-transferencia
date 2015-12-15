jQuery(function($){
	$(".data").mask("99/99/9999");
	$(".money").maskMoney({prefix:'R$ ', allowNegative: false, thousands:'.', decimal:','});
	$(".conta").mask("99999-9");
});