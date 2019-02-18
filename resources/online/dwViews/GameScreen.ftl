<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

	<!-- Optional Styling of the web page, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="https://unpkg.com/nes.css/css/nes.min.css" rel="stylesheet" />
	<link href='http://fonts.googleapis.com/css?family=Press+Start+2P' rel='stylesheet' type='text/css'>
	
		<style> 
		
			body{
				background-image: url(http://localhost:7777/assets/background.gif);
			}
		
			#gameInfo {
				text-align: center;
				visibility: hidden;
				padding-top: 30px;
			}
			
			.nes-btn {
			}
			
			h1 {
				text-align: center;
				font-weight: 200;
				font-size: 50px;
				color: white;
			}
			
			h2	{
				text-align: left;
				font-weight: 200;
				font-size: 30px;
				color: white;
			}
			
			h3 {
				text-align: left;
				font-weight: 200;
				font-size: 30px;
				color: white;
			} 
			
 			h4 {
				text-align: left;
				font-weight: 200;
				font-size: 30px;
				color: white;
			}
			
			h5 {
				text-align: left;
				font-weight: 200;
				font-size: 30px;
				color: white;
			} 
			
			.card-picture {
				width: 100%;
				height: 5vw;
				object-fit: cover;	
				border: 2px solid white;
  				border-radius: 3px;
			}
			
			#cardSection button {
				font-size: 6px;
				font-weight: 0;
			}
			
			#cardSection {
				visibility: hidden;
				font-size: 5px;
	
 			}

			.card-header {
				font-weight: 200;
				font-size: 12px;
				color: white;
			}

			.card-subtitle {
				font-size: 8px;
				padding: 8px;
				color: white;
			}
			
			.nes-container.is-rounded.is-dark {
				width: 200px;
				height: 360px;
				background-color: #00000000;
			}
			
			.card-body {
 				padding: 10px;
			}
			
			#numberOfOpponents {
 			position: relative;
			left: 450px;
			width: 60px;
			text-indent: 10px;
			}
			
		</style>
	</head>

    <body>
    <div class="container">
    	<div class="row">
    	<div class="col-lg-12"></div>
    		<div class="row">
    		<div class="col-lg-12"></div>
    			<div class="container">
    				<div class="row"> 
    					<div class="col-lg-12">
    						<div id="setNumberOfOpponents">				
    							<h1> Choose number of opponents</h1>
								<select id="numberOfOpponents">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
								</select>
 								<button class="nes-btn" style="position:relative;left:470px;" onclick="setNumberOfOpponents();">Play</button>
				    		</div>
				    				
				    		<div id="gameInfo">
				    			<h3>Round: <label id='setRoundCounter'></label></h3>
	    						<h4>Active Player: <label id='activePlayer'></label></h4>
	    						<h5>Round Winner: <label id='roundWinnerRevealed'></label></h5>
	    						<button class="nes-btn" style="position:relative;right:479px;" onclick="displayCards();">Draw Cards</button>
	    					</div>
							
    						<div class="row text-center" id='cardSection'>
    							<div class="col-lg-2">
    								<div class="nes-container is-rounded is-dark" id="card1">
									<div class="card-header">Human</div>
										<h6 class="card-subtitle" id="card-title"></h6>
										<img class="card-picture" src="" alt="No_Image_Found">
										<div class="card-body">
											<button class="nes-btn" style="font-size:8px;width:130px;height:30px;" onclick="humanSelectsCategory(1)" id="stat1">
												<p class="stat"></p>
											</button>
											<button class="nes-btn" style="font-size:8px;width:130px;height:30px;" onclick="humanSelectsCategory(2)" id="stat2">
												<p class="stat"></p>
											</button>
											<button class="nes-btn" style="font-size:8px;width:130px;height:30px;" onclick="humanSelectsCategory(3)" id="stat3">
												<p class="stat"></p>
											</button>
											<button class="nes-btn" style="font-size:8px;width:130px;height:30px;" onclick="humanSelectsCategory(4)" id="stat4">
												<p class="stat"></p>
											</button>
											<button class="nes-btn" style="font-size:8px;width:130px;height:30px;" onclick="humanSelectsCategory(5)" id="stat5">
												<p class="stat"></p>
											</button>
										</div>
									</div>
								</div>
								<div class="col-lg-2">
									<div class="nes-container is-rounded is-dark" id="card2">
										<div class="card-header">Opponent 1</div>
											<h6 class="card-subtitle" id="card-title"></h6>
											<img class="card-picture" src="" alt="No_Image_Found">
										<div class="card-body">
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat1"></p>
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat2"></p>
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat3"></p>
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat4"></p>
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat5"></p>
										</div>
									</div>
								</div>
								<div class="col-lg-2">
									<div class="nes-container is-rounded is-dark" id="card3">
											<div class="card-header">Opponent 2</div>
											<h6 class="card-subtitle" id="card-title"></h6>
											<img class="card-picture" src="" alt="No_Image_Found">
										<div class="card-body">
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat1"></p>
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat2"></p>
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat3"></p>
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat4"></p>
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat5"></p>
										</div>
									</div>
								</div>
								<div class="col-lg-2">
									<div class="nes-container is-rounded is-dark" id="card4">
											<div class="card-header">Opponent 3</div>
											<h6 class="card-subtitle" id="card-title"></h6>
											<img class="card-picture" src="" alt="No_Image_Found">
										<div class="card-body">
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat1"></p>
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat2"></p>
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat3"></p>
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat4"></p>
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat5"></p>
										</div>
									</div>
								</div>
								<div class="col-lg-2">
									<div class="nes-container is-rounded is-dark" id="card5">
										<div class="card-header">Opponent 4</div>
											<h6 class="card-subtitle" id="card-title"></h6>
											<img class="card-picture" src="" alt="No_Image_Found">
										<div class="card-body">
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat1"></p>
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat2"></p>
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat3"></p>
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat4"></p>
											<p class="stat" style="font-size:8px;color:white;padding-top:14px;" id="stat5"></p>
										</div>
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
			document.getElementById("gameInfo").style.visibility = "visible";
		}
		
	  	function cardSectionVisible() {
	  		document.getElementById("cardSection").style.visibility = "visible";
	  		
	  	}
	  	
		function displayAppropriateAmountOfCards() {
			
			var selectedNumberOfPlayers = $('#numberOfOpponents').val();

	    	if (selectedNumberOfPlayers == 1) {
	      		$("#card3").remove();
	      		$("#card4").remove();
	      		$("#card5").remove();
	    	} 
	    	else if (selectedNumberOfPlayers == 2) {
	     		$("#card4").remove();
	      		$("#card5").remove();
	    	} 
	    	else if (selectedNumberOfPlayers == 3) {
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
				activePlayer();
		        for (i = 0; i < 5; i++) {
		        	var cardTitle = "#card" + (i + 1);
		        	$(cardTitle).find(".card-picture").attr("src", "http://localhost:7777/assets/" + list[i].description + ".jpg");
		          	$(cardTitle).find("#card-title").text(list[i].description);
			        $(cardTitle).find("#stat1").each(function(j) { 
			        	$(this).html("<p class=\"stat\">" + "Size: " + list[i].stat1 + "</p>");
				          });
			        $(cardTitle).find("#stat2").each(function(k) { 
			        	$(this).html("<p class=\"stat\">" + "Speed: " + list[i].stat2 + "</p>");
				          });
			        $(cardTitle).find("#stat3").each(function(l) { 
			        	$(this).html("<p class=\"stat\">" + "Range: " + list[i].stat3 + "</p>");
				          });
			        $(cardTitle).find("#stat4").each(function(m) { 
			        	$(this).html("<p class=\"stat\">" + "Firepower: " + list[i].stat4 + "</p>");
				          });
			        $(cardTitle).find("#stat5").each(function(n) { 
			        	$(this).html("<p class=\"stat\">" + "Cargo: " + list[i].stat5 + "</p>");
				          });
		        }
		      }
			setRoundCounter();
			displayAppropriateAmountOfCards();
			cardSectionVisible();
			
		 	xhr.send();
		}
 		
 		
 		function humanSelectsCategory(x) {
 			
 			var number = x
 			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/humanCompareStats?Number=" + number);
 			
 			if(!xhr){
 				alert("CORS NOT AVAILABLE");
 			} 
 			
 		    xhr.onload = function(e) {
 		         
 		    	var responseText = xhr.response; 
/*  		        responseText = responseText.replace(/^"(.*)"$/, '$1');
 */ 		        document.getElementById('roundWinnerRevealed').innerHTML = responseText;
 		    	  
 		    	  
 		      }
 			
 			xhr.send();
 		}
 		
 		function AiSelectsCategory(){
 			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/AICompareStat");
 			
 			if(!xhr){
 				
 				alert("CORS NOT AVAILABLE");
 				
 			}
 			
 			xhr.onload = function(e){
 				
 				var responseText = xhr.response;
 				document.getElementById('roundWinnerRevealed').innerHTML = responseText;
 				
 				
 			}
 			
 			xhr.send();
 		}
 		
 		function activePlayer(){
 			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/processRound")
 			if(!xhr){
 				alert("Cors NOT Available")
 			}
 			xhr.onload = function(e){
 			
 				var responseText = xhr.response;
 				document.getElementById('activePlayer').innerHTML = responseText;
 				processRound();
 				}
 			xhr.send();
 		}
 		
 		function processRound(){
 			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/processRound")
 			
 			if(!xhr){
 				alert("Cors NOT Available")
 			}
 			xhr.onload = function(e){
 			
 				var responseText = xhr.response;
 				document.getElementById('roundWinnerRevealed').innerHTML = responseText;
 			}
 			xhr.send();
 		}
 		
 		function setRoundCounter() {
 	   		var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/roundNumber");
 	   		
 			if (!xhr) {
 				alert("error!");
 			}
 			
 			xhr.onload = function(e) {
 			var responseText = xhr.response; // the text of the response
 				document.getElementById('setRoundCounter').innerHTML = responseText;
 			}
 		xhr.send();
 	   }
 		
 	   function roundNumber() {
 	   		var xhr = createCORSRequest('GET',
 					"http://localhost:7777/toptrumps/roundNumber");
 			if (!xhr) {
 				alert("No Round Number");
 				}
 			xhr.onload = function(e) {
 			var responseText = xhr.response; // the text of the response
 				document.getElementById('roundNumber').innerHTML = responseText;
 			}
 		xhr.send();
 	   }

		</script>
	</body>
</html>