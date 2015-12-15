function calcularTaxa() {
	if(document.getElementById("valor").value == "" || document.getElementById("tipo").value == ""){
		return;
	}
	$.ajax({
		type : 'POST',
		dataType : 'json',
		headers : {
			'Accept' : 'application/json',
		},
		url : "/calcularTaxa",
		data : $("#cadastroForm").serialize(),
		success : function(response) {
			$("#feedback").removeClass("error");
			$("#feedback").removeClass("success");
			$("#feedback").html("");
			$("#taxa").attr('value', response);
			$("#taxa").maskMoney('mask', response);
		},
		error : function(response) {
			$("#feedback").removeClass("success");
			$("#feedback").addClass("error");
			$("#feedback").html("Houve um erro ao calcular a taxa, favor tentar novamente.");
		}
	});
}

$(document).ready(function() {
	$("#valor").blur(function(){
		calcularTaxa();
	});
	$("#tipo").change(function(){
		calcularTaxa();
	});
	$('#taxa').off('keyup keydown keypress');
});