$(document).ready(function(){
	
	$('#formID').on('submit', function(event){
		event.preventDefault();
		
		var username = $('#usernameID').val();
		var password = $('#passwordID').val();
		var hPassword = md5(password);
		
		$.ajax({
			url:'http://localhost:8070/login',
		    data: JSON.stringify({"username": username, password: hPassword}),
		    type: 'POST',		    
		    contentType: 'application/json', 
		    success: function(res, status, xhr)
		    {
		    	
		    	$('#loginErrorID').hide();
		    	if(typeof(Storage) !== "undefined")
		    	{
		    		localStorage.setItem("username", username);
		    	}
		    	window.location = '/profile';		    	
		    },
			error: function(res, status, xhr)
			{
				$('#loginErrorID').show('slow');
			}
		});
	
	}); 
});