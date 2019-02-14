<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=VT323" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css"
	integrity="sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<style> 
		
			#gameView {
				text-align: center;
				visibility: hidden;
			}
			
			.card-img-top {
				width: 100%;
				height: 5vw;
				object-fit: cover;	
			}
			
			#cardSection button {
				cursor: pointer;
				font-size: 14px;
				font-family: 'Josefin Sans', sans-serif;
				font-weight: 0;
			}
			
			#cardSection {
				visibility: visible;
				font-family: VT323;
				font-size: 5px;	
			}

			.card-header {
				font-weight: 400;
				font-size: 15px;
			}

			.card-subtitle{
				font-size: 15px;
				padding: 10px;
			}
			
		</style> 
		
	</head>

    <body style="background: url(https://www.elegantthemes.com/blog/wp-content/uploads/2013/09/bg-11-full.jpg?fbclid=IwAR2NCFjf2oswomit651mKMp7LQjN-O84vfUzPQ6HhKl_f97FTpFoueTZoPY)">

    			<div class="row">
    				<div class="col-md-12"></div>
    					<div class="row">
    						<div class="col-md-12"></div>
    						<div class="container">
    							<div class="row"> 
    								<div class="col-lg-8">
    								<div id="setupGame">
    									<h1>Top Trumps</h1>    					
    										<div id="setNumberOfPlayers">
    											<p>Set number of opponents</p>
												<select id="playerSelection">
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												</select>
												<button type="button" class="btn btn-primary" onclick="setNumberOfPlayers()">Play</button>
											</div>
				    				</div>
    					
    							<div id="gameView">
    								<h1>Round number:</h1>
	    							<h2>Active Player:</h2>
	    							<h3>Previous Round Winner:</h3>
	    							
	    							<button class="btn btn-default" onclick="drawCardFunction()" id='drawCard'>Draw Cards</button><br>
    							</div>
    							
    			<div class="row text-center" id='cardSection'>

					<div class="col-sm-1"></div>

					<div class="col-lg-2">
						<div class="card" id="card1">
							<div class="card-header">Human</div>
							<h5 class="card-subtitle text-muted" id="card-title"></h5>
							<img class="card-img-top"
								src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Idris.jpg"
								alt="Card image cap">
							<div class="card-body">
							

								<button onclick="selectCategory(1)"
									class="btn btn-default btn-block" id="humanCat1">
									<span class="badge"></span>
								</button>
								<button onclick="selectCategory(2)"
									class="btn2 btn-default btn-block" id="humanCat2">
									<span class="badge"></span>
								</button>
								<button onclick="selectCategory(3)"
									class="btn3 btn-default btn-block" id="humanCat3">
									<span class="badge"></span>
								</button>
								<button onclick="selectCategory(4)"
									class="btn4 btn-default btn-block" id="humanCat4">
									<span class="badge"></span>
								</button>
								<button onclick="selectCategory(5)"
									class="btn5 btn-default btn-block" id="humanCat5">
									<span class="badge"></span>
								</button>
							</div>
							<div class="card-footer text-muted"></div>
						</div>
					</div>

					<div class="col-lg-2">
						<div class="card" id="card2">
							<div class="card-header">Computer 1</div>
							<h5 class="card-subtitle text-muted" id="card-title"></h5>
							<img class="card-img-top" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Avenger.jpg" alt="Card image cap">
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
							<div class="card-footer text-muted"></div>
						</div>
					</div>

					<div class="col-lg-2">
						<div class="card" id="card3">
							<div class="card-header">Computer 2</div>
							<h5 class="card-subtitle text-muted" id="card-title"></h5>
							<img class="card-img-top" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Hurricane.jpg" alt="Card image cap">
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
							<div class="card-footer text-muted"></div>
						</div>
					</div>

					<div class="col-lg-2">
						<div class="card" id="card4">
							<div class="card-header">Computer 3</div>
							<h5 class="card-subtitle text-muted" id="card-title"></h5>
							<img class="card-img-top" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Idris.jpg" alt="Card image cap">
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
							<div class="card-footer text-muted"></div>
						</div>
					</div>

					<div class="col-lg-2">
						<div class="card" id="card5">
							<div class="card-header">Computer 4</div>
							<h5 class="card-subtitle text-muted" id="card-title"></h5>
							<img class="card-img-top" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Hawk.jpg" alt="Card image cap">
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
							<div class="card-footer text-muted"></div>
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
			var selection = document.getElementById("setNumberOfPlayers");
			if (selection.style.display === "none") {} 
			else {selection.style.display = "none";}
		}
		
		function gameViewVisible(){
			document.getElementById("gameView").style.visibility = "visible";
		}
		
	  	function cardSectionVisible() {
	  		document.getElementById("cardSection").style.visibility = "visible";
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
			
		function setNumberOfPlayers(){
		
			var number = document.getElementById('playerSelection').value;
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/setNumberOfPlayer?number="); // Request type and URL
			
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
					/* cardSectionVisible(); */
				} 
			}
			
			xhr.send();	
			
		}
		
 		function drawCardFunction()	{
			
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
		        	if (list[i] == null) { continue; }
		          	$(cardTitle).find("#card-title").text(list[i].description);
			        $(cardTitle).find(".btn").each(function(j) { 
			        	$(this).html("<span class=\"badge\">" + list[j].stat1 + "</span>");
				          });
			        $(cardTitle).find(".btn2").each(function(k) { 
			        	$(this).html("<span class=\"badge\">" + list[k].stat2 + "</span>");
				          });
			        $(cardTitle).find(".btn3").each(function(l) { 
			        	$(this).html("<span class=\"badge\">" + list[l].stat3 + "</span>");
				          });
			        $(cardTitle).find(".btn4").each(function(m) { 
			        	$(this).html("<span class=\"badge\">" + list[m].stat4 + "</span>");
				          });
			        $(cardTitle).find(".btn5").each(function(n) { 
			        	$(this).html("<span class=\"badge\">" + list[n].stat5 + "</span>");
				          });
		        }
		        
		      }
			
		 	xhr.send();
		} 

		</script>
	</body>
</html>