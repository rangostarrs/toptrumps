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
		<style type="text/css"> <#include "styles.css"> </style> 

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    
    	<header>
    		<div class="view" style="background-image: url('https://i.imgur.com/J46cBUQ.jpg'); background-repeat: no-repeat; background-size: cover; background-position: center center;"></div>
    	</header>
    	
    	<div class="container"></div>

			

	</head>

<!-- Call the initalize method when the page loads -->
<!-- 	<body onload="initalize()"> -->

	<body style=" opacity:0.5; color:white; background: url(https://www.elegantthemes.com/blog/wp-content/uploads/2013/09/bg-5-full.jpg)">
        		<div class="header">
					<button type="button" onclick="window.location.href ='http://localhost:7777/toptrumps';"class="btn btn-primary btn-lg">Back</button>
        			<font size="5"></font>
        			<h1 align="center">Game Statistics</h1>
        				<div class="row">
        					<table style="margin-top:100px; margin-left:400px;">
        					
							  <tr>
					    <th width="500">Statistic</th>
					    <th width="100">Value</th>
					  </tr>
					  <tr>
					  
					    <td height="50">>Number of Games Played</td>
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
        			</div>
        	
        	</body>
        	

	</div>
	
			

	<body />





		
		
		
		<script type="text/javascript">
		
			
		function initalize() { 
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				insertStatsOnline();
				
				
		} 
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
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
		
		
		
		
		
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
/* 			function helloJSONList() {
 */			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
/* 				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				}; */
				
				// We have done everything we need to prepare the CORS request, so send it
/* 				xhr.send();		
			} */
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
/* 			function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			} */

		</script>
		
		</body>
</html>
