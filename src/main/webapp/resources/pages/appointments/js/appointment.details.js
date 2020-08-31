$(function () {
	$('#cancelModal .submitBtn').on('click', function () {
		$('#cancelModal').find('.dummy-submit').click();
	});
	
	$('#approveModal .submitBtn').on('click', function() {
		$('#approveModal').find('.dummy-submit').click();
	});
});