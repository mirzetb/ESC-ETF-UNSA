$(document).ready(function(){
	
	var username = localStorage.username;	
	
	 
	 $.getJSON("/getByUsername?username=" + username, function (data){
		 
		 
		 function UserViewModel() {
			 
			  this.firstName = data['firstName'];
			  this.lastName = data['lastName'];  
			  this.department = data['department']['name'];
			  this.escID = data['escID'];
			  this.username = data['username'];
			  this.email = data['email'];
			  this.phoneNumber = data['phoneNumber'];
			  this.role = data['role']['name'];
			  this.unique_id = data['unique_id'];
			  this.password = data['password'];
			  if(data['validated'] == 0)
				  this.validated = ko.observable(false);
			  else
				  this.validated = ko.observable(true);
			  this.registrationDate = formatDate(data['registrationDate']);
			  this.mailSent = ko.observable(false);
			  this.roleCode = data['role']['code'];
			  
			  
			  
			  
			  this.sendVerificationMail = function()
			  {
				  
				  $.getJSON("/sendValidationMail?mailTo="+this.email+"&uid="+this.unique_id, function(){
					  
				  });
				  
				  this.mailSent(true);
				 				  
			  }
			  
			  this.preformLogout = function()
			  {
				  
				  $.ajax({
					  url: "http://localhost:8070/logoutUser",
					  type: "GET",					  
					  success: function(data)
					  {		
						  localStorage.removeItem('username');						  
						  window.location = "http://localhost:8070/";
					  }
				  });
				  
			  }
			  
			  this.redirectToProjects = function()
			  {
				  window.location = "http://localhost:8080/";
			  }
			  
			  
			  
			  $("#pw_change").submit(function(e, pass){
			        
			        e.preventDefault(e);
			        
			        $.getJSON("/getByUsername?username=" + username, function (data){
			        	var pass = data['password'];
			        	
			        	var newPass = $('#new').val();
			        	newPass = md5(newPass);
			        	if(md5($('#curr').val()) != pass)
			        	{
			        		$('#errorMessage').html("Unesite ispravnu trenutnu lozinku");
			        		$('#errorMessage').show();
			        	}
			        	else if(pass == newPass)
			        	{
			        		$('#errorMessage').html("Nova lozinka se mora razlikovati od trenutne");
			        		$('#errorMessage').show();
			        	}
			        	else 
			        	{
			        		$('#errorMessage').html("");
			        		$('#errorMessage').hide();
			        		
			        		
			        		
			        		$.ajax({
			        			type: "POST",
			        			url: "http://localhost:8070/changePassword",
			        			data: {
			        				'username': localStorage.username,
			        				'newPass' : newPass
			        				
			        			},			        			
			        			success: function(data){
			        				
			        				$('#errorMessage').css("color", "green");
			        				$('#errorMessage').html(data);
					        		$('#errorMessage').show();
			        			}
			        			
			        		});
			        		
			        		
			        	}
			        });
			        
			   });

		
			      
		}
		 
				
		ko.applyBindings(new UserViewModel());
		
		
		
		$("#pw_change").validate({

			errorElement: 'div',
			errorClass: "invalidPass",

			rules: {
				newPW: {
					required: true,
					minlength: 8
					
				},
				newPWRepeat: {
					equalTo: "#new"
					
				}
				
			},

			messages: {
				newPW : {
					required: "Unesite lozinku",
					minlength: "Lozinka mora sadržavati barem 8 karaktera"
					
				},

				newPWRepeat: "Lozinke se moraju podudarati!"
			}
				
		});
		
		$("#pw_change input").on('keyup blur', function() {

			if($("#pw_change").valid()){
				$("#passwordSubmit").prop('disabled', false);
			}
			else {
				$("#passwordSubmit").prop('disabled', 'disabled');
			}
		});
		
		$('#PWFormToggle').click(function(){
			$('#pw_change').toggle('slow');
		});
		
		
		 				 
		 
	 });
	
			

	 function formatDate(timestamp)
	 {
		 var d = new Date(timestamp);
		 var day = d.getDate();
		 var month = d.getMonth();
		 var year = d.getFullYear();
		 var hours = d.getHours();
		 var mins = d.getMinutes();
		 
		 month++;
		 
		 return day + "." + month + "." + year + "., " + hours +":" + mins + "h";
		 
	 }
	 
	
	 
	
	
	
	
	
	
	
	
});

