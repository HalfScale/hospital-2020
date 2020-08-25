$(function() {
	//We get the id of the requested doctor
	let path = window.location.pathname;
	let lastIndex = path.lastIndexOf("/");
	let id = path.substring(lastIndex + 1);
	
	$('.createBtn').on('click', function() {
		window.location.href= '../../appointments/add?doctor=' + id;
	});
});