<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

	<!-- Optional Styling of the web page, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" integrity="sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="https://unpkg.com/nes.css/css/nes.min.css" rel="stylesheet" />
	<link href='http://fonts.googleapis.com/css?family=Press+Start+2P' rel='stylesheet' type='text/css'>
	
		<style> 
		
			#gameView {
				text-align: center;
				visibility: hidden;
			}
			
			.btn .btn2 .btn3 .btn4 .btn5 {
				cursor: pointer;
				font-size: 10px;
				padding: 10px 10px;
			}
			
			.card-img-top {
				width: 100%;
				height: 5vw;
				object-fit: cover;	
			}
			
			#cardSection button {
				cursor: pointer;
				font-size: 10px;
				font-weight: 0;
			}
			
			#cardSection {
				visibility: hidden;
				font-size: 5px;	
			}

			.card-header {
				font-weight: 400;
				font-size: 10px;
			}

			.card-subtitle{
				font-size: 10px;
				padding: 10px;
			}
		</style>
	</head>

    <body style="background: url(https://i.stack.imgur.com/V3plu.gif)">
    
    	<div class="row">
    	<div class="col-md-12"></div>
    		<div class="row">
    		<div class="col-md-12"></div>
    			<div class="container">
    				<div class="row"> 
    					<div class="col-lg-8">
    						<div id="setNumberOfOpponents">
    							<h1>Top Trumps</h1>    					
    							<p>Set number of opponents</p>
								<select id="numberOfOpponents">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									</select>
 									<button type="button" class="nes-btn" onclick="setNumberOfOpponents();">Play</button>
				    		</div>
				    				
				    		<div id="gameView">
				    			<h1>Round: <label id="setRoundCounter"></label></h1>
	    						<h2>Active Player:</h2>
	    						<h3>Previous Round Winner:</h3>
	    						<button class="btn btn-default" onclick="displayCards();">Draw Cards</button>
	    					</div>
    							
    						<div class="row text-center" id='cardSection'>
    							<div class="col-lg-1"></div>
    							<div class="col-lg-2">
    								<div class="nes-container is-rounded is-dark" id="card1">
									<div class="card-header">Human</div>
										<h5 class="card-subtitle text-muted" id="card-title"></h5>
										<img class="card-img-top" src="" alt="No_Image_Found">
										<div class="card-body">
											<button onclick="selectCategory(1)" class="btn btn-default btn-block" id="humanCat1">
												<span class="badge"></span>
											</button>
											<button onclick="selectCategory(2)" class="btn2 btn-default btn-block" id="humanCat2">
												<span class="badge"></span>
											</button>
											<button onclick="selectCategory(3)" class="btn3 btn-default btn-block" id="humanCat3">
												<span class="badge"></span>
											</button>
											<button onclick="selectCategory(4)" class="btn4 btn-default btn-block" id="humanCat4">
												<span class="badge"></span>
											</button>
											<button onclick="selectCategory(5)" class="btn5 btn-default btn-block" id="humanCat5">
												<span class="badge"></span>
											</button>
										</div>
									</div>
								</div>

								<div class="col-lg-2">
									<div class="card" id="card2">
										<div class="card-header">Opponent 1</div>
											<h5 class="card-subtitle text-muted" id="card-title"></h5>
											<img class="card-img-top" src="" alt="No_Image_Found">
										<div class="card-body">
											<button class="btn btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
											<button class="btn2 btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
											<button class="btn3 btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
											<button class="btn4 btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
											<button class="btn5 btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
										</div>
									</div>
								</div>

								<div class="col-lg-2">
									<div class="card" id="card3">
											<div class="card-header">Opponent 2</div>
											<h5 class="card-subtitle text-muted" id="card-title"></h5>
											<img class="card-img-top" src="" alt="No_Image_Found">
										<div class="card-body">
											<button class="btn btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
											<button class="btn2 btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
											<button class="btn3 btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
											<button class="btn4 btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
											<button class="btn5 btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
										</div>
									</div>
								</div>

								<div class="col-lg-2">
									<div class="card" id="card4">
											<div class="card-header">Opponent 3</div>
											<h5 class="card-subtitle text-muted" id="card-title"></h5>
											<img class="card-img-top" src="" alt="No_Image_Found">
										<div class="card-body">
											<button class="btn btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
											<button class="btn2 btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
											<button class="btn3 btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
											<button class="btn4 btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
											<button class="btn5 btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
										</div>
									</div>
								</div>

								<div class="col-lg-2">
									<div class="card" id="card5">
										<div class="card-header">Opponent 4</div>
											<h5 class="card-subtitle text-muted" id="card-title"></h5>
											<img class="card-img-top" src="" alt="No_Image_Found">
										<div class="card-body">
											<button class="btn btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
											<button class="btn2 btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
											<button class="btn3 btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
											<button class="btn4 btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
											<button class="btn5 btn-default btn-block" disabled>
												<span class="badge"></span>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
					  
		
		<script type="text/javascript">
		
		function hideSelection(){
			var selection = document.getElementById("setNumberOfOpponents");
			if (selection.style.display === "none") {} 
			else {selection.style.display = "none";}
		}
		
		function gameViewVisible(){
			document.getElementById("gameView").style.visibility = "visible";
		}
		
	  	function cardSectionVisible() {
	  		document.getElementById("cardSection").style.visibility = "visible";
	  		
	  	}
	  	
		function displayAppropriateAmountOfCards() {
			
			var playerNum = $('#numberOfOpponents').val();

	    	if (playerNum == 1) {
	      		$("#card3").remove();
	      		$("#card4").remove();
	      		$("#card5").remove();
	    	} 
	    	else if (playerNum == 2) {
	     		$("#card4").remove();
	      		$("#card5").remove();
	    	} 
	    	else if (playerNum == 3) {
	      		$("#card5").remove();
	    	}
	  	}
		
		</script>
		
		<script type="text/javascript">
		
		
		var cardList = undefined;
		var cardListCurrentHands = undefined;
		
		function createCORSRequest(method, url) {
				var xhr = new XMLHttpRequest();
				if ("withCredentials" in xhr) {

				xhr.open(method, url, true);

				} else if (typeof XDomainRequest != "undefined") {

				xhr = new XDomainRequest();
				xhr.open(method, url);

				 } else {

				xhr = null;

				 }
				 return xhr;
		}
			
		function setNumberOfOpponents() {
		
			var number = document.getElementById('numberOfOpponents').value;
		    var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/setNumberOfOpponents?Number=" + number);
			
			if (!xhr) {
					alert("CORS not supported");
			}

			xhr.onload = function(e) {
				if (number < 1 || number > 4){
					alert("Max 4 players"); 
				}
				else {
					hideSelection();
					gameViewVisible();
				} 
			}
			
			xhr.send();	
			
		}
		
 		function displayCards() {
			
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/displayCards");
			
			if (!xhr) {
		        alert("No cards found"); 
			}
			
			xhr.onload = function(e) {
		        var responseText = xhr.response;
		        var list = JSON.parse(responseText);
		        
 				cardList = list[0];
 				cardListCurrentHands = list; 
				
		        for (i = 0; i < 5; i++) {
		        	var cardTitle = "#card" + (i + 1);
		        	$(cardTitle).find(".card-img-top").attr("src", "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + list[i].description + ".jpg");
		          	$(cardTitle).find("#card-title").text(list[i].description);
			        $(cardTitle).find(".btn").each(function(j) { 
			        	$(this).html("<span class=\"badge\">" + "Size: " + list[i].stat1 + "</span>");
				          });
			        $(cardTitle).find(".btn2").each(function(k) { 
			        	$(this).html("<span class=\"badge\">" + "Speed: " + list[i].stat2 + "</span>");
				          });
			        $(cardTitle).find(".btn3").each(function(l) { 
			        	$(this).html("<span class=\"badge\">" + "Range: " + list[i].stat3 + "</span>");
				          });
			        $(cardTitle).find(".btn4").each(function(m) { 
			        	$(this).html("<span class=\"badge\">" + "Firepower: " + list[i].stat4 + "</span>");
				          });
			        $(cardTitle).find(".btn5").each(function(n) { 
			        	$(this).html("<span class=\"badge\">" + "Cargo: " + list[i].stat5 + "</span>");
				          });
		        }
		        
		      }
			displayAppropriateAmountOfCards();
			cardSectionVisible();
		 	xhr.send();
		} 
 		
 	   function setRoundCounter() {
 	   		var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/roundNumber");
 	   		
 			if (!xhr) {
 				alert("error!");
 			}
 			
 			xhr.onload = function(e) {
 			var responseText = xhr.response; // the text of the response
 				document.getElementById("setRoundCounter").innerHTML = responseText;
 			}
 		xhr.send();
 	   }

		</script>
	</body>
</html>