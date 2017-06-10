$(document).ready(function(){
	
	getInfo();
	var id = localStorage.id;
	
	$.ajax({
		url: "http://localhost:8080/getProjectByID?id="+id,
		type: 'GET',
		dataType: 'json',
		success: function(data)
		{
			
			$.getJSON("http://localhost:8080/commentsOnProject?projID=" + id, function(data2){
					
				
				function Status(code, text)
				{
					this.code = code;
					this.text = text;
				}
				
				function POViewModel()
				{
					
					this.title = ko.observable(data.title);
					this.shortDescription = ko.observable(data.shortDescription);
					this.purpose = ko.observable(data.purpose);
					this.creationDate = ko.observable(formatDate(data.creationDate));
					this.decisionDate = ko.observable(formatDateDecision(data.decisionDate));
					this.decisionComment = ko.observable(data.decisionComment);
					this.status = ko.observable(data.status);
					this.teamSize = ko.observable(data.teamSize);
					this.teamLead = ko.observable(data.teamLead);					
					
					this.commentContent = ko.observable();
					this.commentsOnProject = ko.observableArray(data2);				
					
					
					this.teamMembers = ko.observableArray();
					
					this.role = ko.observable(localStorage.role);
					
					this.statuses = ko.observableArray([
						
						new Status(1, 'Odobren'),
						new Status(2, 'Odbijen'),
						new Status(3, 'Na čekanju')
					])
					
					
					
					this.requiredResources = ko.observableArray();
					
					this.quantity = ko.observable();
					this.titleRR = ko.observable();
					this.unitPrice = ko.observable();
					this.noteRR = ko.observable();
					
					
					
					
					
					var self = this;
					
					this.grandTotal = ko.computed(function(){
						
						var price =0;
						for(var i = 0; i < self.requiredResources().length; i++)
						{
							price += (self.requiredResources()[i].quantity * self.requiredResources()[i].unitPrice);
							
						}
						return price;
					});
					
					
					
					$.getJSON("http://localhost:8080/membersOnProject?projID="+localStorage.id, function(data){
						
						
						for(var i  = 0; i < data.length; i++)
						{
							self.teamMembers.push({'firstName': data[i].user.firstName, 'lastName': data[i].user.lastName});
							
						}	
						
						
					});				
					
					
					$.getJSON("http://localhost:8080/getAllRRs?projID="+localStorage.id, function(data){
						self.requiredResources(data);						
						
					});
					
					
					
					this.addNewRRFunction = function(){
						var qtity = this.quantity(); 
						var ttl = this.titleRR();
						var up = this.unitPrice();
						var not = this.noteRR();
						
						
						
						$.ajax({
							url: 'http://localhost:8080/saveRR',
							type: 'POST',
							data: {
								projID: localStorage.id,
								title: ttl,
								quantity: qtity,
								price: up,
								note: not
							},
							success: function()
							{
								$.getJSON("http://localhost:8080/getAllRRs?projID="+localStorage.id, function(data){
									self.requiredResources(data);
								});
							}
						});
					}					
					
					this.populatePopover = function(){
						
						$('#infoID').popover({
							html: true,
							trigger: 'manual',
							content: function() {
								var msg = "<style>.popover-content > p { text-align: center  }</style><label class='label label-success'> Početak projekta: " + ko.unwrap(self.creationDate) + "</label>"
								msg += "<br><label class='label label-success'> Datum odluke: " + ko.unwrap(self.decisionDate) + "</label><br><br>"
								+"<p>Komentar: " + ko.unwrap(self.decisionComment) + "</p>";
								return msg;
							}
						});
						$('#infoID').popover("show");
						
					}
					
					this.hidePopover = function(){
						$('#infoID').popover("hide");
					}
					
					this.openSection = function(){
						$('#commentSectionID').toggle("slow");
					}
					
					this.formatCommentDate = function(timestamp){
						var d = new Date(timestamp);
						 var day = d.getDate();
						 var month = d.getMonth();
						 var year = d.getFullYear();
						 var hours = d.getHours();
						 var mins = d.getMinutes();
						 
						 month++;
						 
						 return day + "." + month + "." + year + ". " + hours+":"+ mins;
					}
					
					var selftwo = this;
					
					
					
					
					this.postComment = function(){
						var commentContent = ko.unwrap(this.commentContent);
						var userID = localStorage.userID;
						var projectID = localStorage.id;
						
						$.ajax({
							url: "http://localhost:8080/addNewComment",
							type: "POST",							
							data: {
								'projID' : projectID,
								'userID' : userID,
								'content' : commentContent
							},							
							success: function(data)
							{
								
								$.ajax({
									url: "http://localhost:8080/commentsOnProject?projID="+ localStorage.id,
									type: "GET",									
									success: function(data2)
									{										
										selftwo.commentsOnProject(data2);
									}
								});
								
							}
						})
					}
					
					this.checkIfAssigned = function()
					{
						for(var i = 0; i < self.teamMembers().length; i++)
						{
							if(self.teamMembers()[i].firstName + ' ' + self.teamMembers()[i].lastName == localStorage.fullname)
								return true;
						}
						return false;
					}
					
									
					
					this.assignMemberToProject = function()
					{
						var member = localStorage.fullname;
						var splitted = member.split(" ");
						var name = splitted[0];
						var lastname = splitted[1];
						
						self.teamMembers.push({'firstName': name, 'lastName': lastname});
						
						$.ajax({
							url: "http://localhost:8080/assignMemberToProject",
							type: "POST",
							data: {
								'userID': localStorage.userID,
								'projID': localStorage.id
							},
							success: function(data)
							{
								
								
							}
						});
					}
					
					this.changeProjectStatus = function()
					{
						
						if($('#statusChooserID option:selected').index() > 0)
						{
							var text = $("#statusChooserID option:selected").text();
							var code = $('#statusChooserID').val();
							
							$.ajax({
								url: "http://localhost:8080/assignStatusToProject",
								type: "POST",
								data: {
									projID: localStorage.id,
									statID: code
								},
								success: function()
								{
									self.status({'text': text, 'code': code});
								}
							
							});
						}
					}
					
					this.setDecisionComment = function()
					{
						if($('#decisionCommentID').val().length > 0)
						{
							var message = $('#decisionCommentID').val();
							$.ajax({
								url: "http://localhost:8080/setDecisionComment",
								type: "POST",
								data: {
									projID: localStorage.id,
									comment: message
								},
								success: function(data)
								{
									self.decisionComment(message);
									$.ajax({
										url: "http://localhost:8080/setDecisionDate",
										type: 'POST',
										data: {
											projID: localStorage.id
										},
										success: function(data2){
											self.decisionDate(formatDateDecision(data2));
										}
									});
									
								}
							});
						}
					}
					
					
				}
				
				ko.applyBindings(new POViewModel());
				
				
			});
			
			
		
		}
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
		 
		 return day + "." + month + "." + year + ".";
		 
	 }
	
	function formatDateDecision(timestamp)
	 {
		 var d = new Date(timestamp);
		 var day = d.getDate();
		 var month = d.getMonth();
		 var year = d.getFullYear();
		 var hours = d.getHours();
		 var mins = d.getMinutes();
		 
		 month++;
		 
		 return day + "." + month + "." + year + ". " + hours + ":" + mins;
		 
	 }
	
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
	
	
	
	//$('#infoID').popover();
	
});
