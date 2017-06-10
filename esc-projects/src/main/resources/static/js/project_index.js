$(document).ready(function(){
	
	
	getInfo();
	$.getJSON("http://localhost:8080/getAllProjs", function(data){
		
		function ProjectViewModel(){
			
			this.projects = ko.observableArray(data);
			
			this.title = ko.observable("");
			this.purpose = ko.observable("");
			this.shortDescription = ko.observable("");
			this.teamSize = ko.observable("");
			
			var self = this;
			
			this.redirectToOverview = function()
			{
			
				var id = $(this).attr("id");
				localStorage.setItem('id', id);
				window.location= "http://localhost:8080/projectOverview";
				
			}
			
			
			
			this.suggestProj= function(){
				
				var username = localStorage.username;
				var title = self.title();
				var purpose = self.purpose();
				var shortDescription = self.shortDescription();
				var teamSize = self.teamSize();
				
				$.ajax({
					url: "http://localhost:8080/suggestProject",
					type: "POST",
					data: {
						'username' : username,
						'title': title,
						'purpose': purpose,
						'shortDescription' : shortDescription,
						'teamSize' : teamSize
					},
					success: function()
					{
						$.getJSON("http://localhost:8080/getAllProjs", function(data){
							self.projects(data);
							$('#projectSuggestion').modal('hide');
							$('#succesModal').modal('show');
						});
					}
				});
			}
			
			
		}
		
		
		
		
		
		ko.applyBindings(new ProjectViewModel());
		
		
$('#formSuggestID').validate({
			
	errorElement: 'div',
	errorClass: "invalidPass",
	
			rules: {
				
				title: {
					required: true
				},
				
				purpose: {
					required: true
				},
				
				description: {
					required: true
				},
				
				teamSize: {
					required: true
					
				}
				
			},
			
			messages: {
				
				title: {
					required: 'Unesite naslov projekta'
				},
			
				purpose: {
					required: 'Unesite svrhu projekta'
				},
				
				description: {
					required: 'Unesite opis projekta'
				},
				
				teamSize: {
					required: 'Odaberite broj članova tima'
				}
			
			}
			
		});
		
	});
	
	
	$("#formSuggestID input").on('blur', function() {

		if($("#formSuggestID").valid()){
			$("#suggestProjSubmitID").prop('disabled', false);
		}
		else {
			$("#suggestProjSubmitID").prop('disabled', 'disabled');
		}
	});
	
	
	function getInfo()
	{
		
		$.ajax({
			url: "http://localhost:8080/getLoggedUsername",
			type: 'GET',
			success: function(data)
			{
				$.ajax({
					url: "http://localhost:8080/getOneUser?username="+data,
					type: 'GET',
					success: function(data)
					{						
						localStorage.setItem("username", data.username);
						localStorage.setItem("fullname", data.firstName + ' ' + data.lastName);
						localStorage.setItem("role", data.role);
						localStorage.setItem("userID", data.userID);
					}
				})
			}
		})
	}
	
});