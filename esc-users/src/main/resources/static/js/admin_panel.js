$(document).ready(function(){

	
	
	$.getJSON("http://localhost:8070/getAll", function(data){
		
		var Role = function(name, code)
		{
			this.name = name;
			this.code = code;
		}
		
		var Department = function(name, code)
		{
			this.name = name;
			this.code = code;
		}
			
		function AdminViewModel()
		{
			this.users = ko.observableArray(data);			
			
			this.firstName = ko.observable("");
			this.lastName = ko.observable("");
			this.username = ko.observable("");
			this.email = ko.observable("");
			this.escID = ko.observable("");
			this.phoneNumber = ko.observable("");
			
			this.roles = ko.observableArray([
				new Role('Član vijeća', 'SuperUser'),
				new Role('Član', 'User')
				
			]);
			
			this.departments = ko.observableArray([
				new Department ('Odsjek za računarstvo i informatiku', 'RI'),
				new Department ('Odsjek za automatiku i elektroniku', 'AE'),
				new Department ('Odsjek za elektroenergetiku', 'EE'),
				new Department ('Odsjek za telekomunikacije', 'TK')
			]);
			
			
			
			
			this.createUsername = function()
			{
				
				var firstname ="";
				if(typeof ko.unwrap(this.firstName) !== "undefined" && ko.unwrap(this.firstName).length > 0)
				{
					
					firstname = ko.unwrap(this.firstName);
					firstname= firstname[0];
				}
				
				var lastname ="";
				if(typeof ko.unwrap(this.lastName) !== "undefined" && ko.unwrap(this.lastName).length > 0)
				{
					lastname= ko.unwrap( this.lastName);
					var formatted = lastname[0].toUpperCase();
					formatted += lastname.substring(1, lastname.length);
					this.lastName(formatted);
				}				
				
				var username = firstname + lastname + '1';
				this.username(username.toLowerCase());
			}
			
			this.makeFirstUppercaseName = function()
			{
				if(typeof ko.unwrap(this.firstName) !== "undefined" && ko.unwrap(this.firstName).length > 0)
				{
					var content = ko.unwrap(this.firstName);
					
					var formatted = content[0].toUpperCase();
					formatted += content.substring(1, content.length);
					
					this.firstName(formatted);
					
					
				}
			}
			
			this.createESCID = function()
			{
				var d = new Date();
				if((typeof ko.unwrap(this.firstName) !== "undefined" && ko.unwrap(this.firstName).length > 0) && (typeof ko.unwrap(this.lastName) !== "undefined" && ko.unwrap(this.lastName).length > 0))
				{
					var i1 = ko.unwrap(this.firstName);
					var i2 = ko.unwrap(this.lastName);
					i1 = i1[0].toUpperCase();
					i2 = i2[0].toUpperCase();
					var initials = i1 + i2;
					var usernameContent = initials + d.getHours() + d.getMinutes() + '1';
					this.escID(usernameContent);
				}
				
				
			}
			
			
			var self = this;
			this.addUser = function()
			{
				$.ajax({
					url: 'http://localhost:8070/addUser',
					type: 'POST',
					data: {
						firstName: ko.unwrap(this.firstName),
						lastName: ko.unwrap(this.lastName),
						escID: ko.unwrap(this.escID),
						username: ko.unwrap(this.username),
						password: md5(ko.unwrap(this.username)),
						email: ko.unwrap(this.email),
						phoneNumber: ko.unwrap(this.phoneNumber),
						role: $('#roleID').val(),
						department: $('#departmentID').val()							
					},
					success: function()
					{
						$.getJSON("http://localhost:8070/getAll", function(data){							
							self.users(data);
						});
						
					}
				});
				
				
				
			}
			
			
			
		}
		
		
		ko.applyBindings(new AdminViewModel());
		
		
		
		
		jQuery.validator.addMethod("validateSelectRole", function(value, element){
			return $('#roleID option:selected').index() > 0;
		}, "Odaberite korisničku rolu u sistemu.");

		jQuery.validator.addMethod("validateSelectDepartment", function(value, element){
			return  $('#departmentID option:selected').index() > 0;
		}, "Odaberite odsjek na kom budući član studira na Elektrotehničkom fakultetu u Sarajevu.");
		
		
		$('#userAddFormID').validate({
			
			errorElement: 'div',
			errorClass: "invalidPass",
			
			rules: {
				
			
				firstName: 	{
					required : true
				},
		
				lastName: {
					required: true
				},
				
				email: {
					required: true,
					email: true
				},
				
				username: {
					required: true,
					minlength: 3
				},
				
				escID: {
					required: true,
					minlength: 6
				},
				
				phoneNumber: {
					required: true,
					digits: true,
					minlength: 9
				},
				
				role: {
					validateSelectRole: true,
				},
				
				department: {
					validateSelectDepartment: true,
				}
				
			},

			messages: {

				firstName: {
					required: "Unesite ime"
				},
				
				lastName: {
					required: "Unesite prezime"
				},
				email: {
					required: "Unesite email adresu",
					email: "Unesite validnu email adresu"
				},
				
				username: {
					required: "Unesite korisničko ime (ili ostavite predloženo)",
					minlength: "Korisničko ime mora biti dužine barem tri karaktera"
				},
				
				escID: {
					required: "Unesite članski broj (ili ostavite predloženi)",
					minlength: "Članski broj mora sadržavati barem 6 karaktera"
				},
			
				phoneNumber: {
					required: "Unesite broj telefona",
					digits: "Broj telefona može sadržavati samo brojeve",
					minlength: "Najmanje 9 brojeva je potrebno za validan telefonski broj BH operatera (06x xxx xxx)"
				}
			}
		});
		
		
		
		$("#userAddFormID input").on('blur', function() {

			if($("#userAddFormID").valid()){
				$("#addUserBtnID").prop('disabled', false);
			}
			else {
				$("#addUserBtnID").prop('disabled', 'disabled');
			}
		});

		
	});
	
	
	
	$('#verified').tooltip1();
	$('#noverified').tooltip();
	
	
	
	
	
	
})
