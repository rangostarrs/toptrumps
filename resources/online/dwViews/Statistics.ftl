<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
		<link href="https://unpkg.com/nes.css/css/nes.min.css" rel="stylesheet" />
		<link href='http://fonts.googleapis.com/css?family=Press+Start+2P' rel='stylesheet' type='text/css'>
		
		<style>
		
			body {
				background-image: url(https://i.stack.imgur.com/V3plu.gif);
			}
			
			h1 {
			font-weight: 400;
			font-size: 30px;
			color: white;
			}
			
			.nes-btn {
			font-size: 13px;
			padding: 10px 10px;
			}
			
			table {
				font-weight: 200;
				font-size: 15px;
				color: white;
			}
		
		</style>

	</head>

	<body onload="initalize()">
		<div class="header">
        		<font size="5"></font>
        		<h1 align="center" style="padding: 15px">Game Statistics</h1>
        	<div class="row">
        		<table style="margin-top:100px; margin-left:400px;">
        			<tr>
        				<th width="500">Statistic:</th>
        				<th width="100">Value</th>
					 </tr>
					 <tr>
					 	<td height="50">Number of Games Played</td>
					    <td id="num1"></td>
					 </tr>
					 <tr>
					    <td height="50">Number of Player Wins</td>
					    <td id="num2"></td>
					 </tr>
					 <tr>
					    <td height="50">Number of CPU Wins</td>
					    <td id="num3"></td>
					 </tr>
					 <tr>
					    <td height="50">Average Number of Draws</td>
					    <td id="num4"></td>
					 </tr>
					 <tr>
					    <td height="50">Highest Number of Rounds</td>
					    <td id="num5"></td>
					 </tr>
				</table>
        	</div>
        	
        	
        	<div class="row">
  				<div class="col-sm"></div>
  				<div class="col-sm-8">
  					<button type="button" class="nes-btn" onclick="window.location.href ='http://localhost:7777/toptrumps/game';">Play again</button>
  					<button type="button" class="nes-btn" onclick="window.location.href ='http://localhost:7777/toptrumps';">Back</button>
  				</div>
			</div>
    	</div>
		
		<script type="text/javascript">
		
			
		function initalize() { 
			
				insertStatsOnline();
				
				
		} 
		
			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		
		</script>
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
		function insertStatsOnline() {
			var xhr = createCORSRequest('GET',
					"http://localhost:7777/toptrumps/statisticsonline");
			if (!xhr) {
				alert("Bad bad bad");
			}
			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				var list = JSON.parse(responseText);

				for (i = 0; i < 5; i++) {
					var tuple = "#num" + (i + 1);
					$(tuple).text(list[i]);
				}
			}
			xhr.send();
		}
		</script>
	</body>
</html>
