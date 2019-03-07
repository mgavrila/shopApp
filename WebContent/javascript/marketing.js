function myFunction(){
    	var e = document.getElementById("category");
    	var value = e.options[e.selectedIndex].value;
    	var text = e.options[e.selectedIndex].text;
    	alert("myFunction aler");
    	loadDoc(text);
    	
    	
 }

function loadDoc(text) {
		   var xhttp = new XMLHttpRequest();
		   xhttp.onreadystatechange = function() {
		     if (this.readyState == 4 && this.status == 200) {
		    	alert("status 4");
		       xmlFunction(this);
		     }
		   };
		   xhttp.open("POST", "../MarketingController", true);
		   xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		   xhttp.send("category="+text+"&action=getProductListAsXml");
		 }
	   
function xmlFunction(xml) {
		   var i;
		   var xmlDoc = xml.responseXML;
		   var table="<option disabled selected value > -- select an option -- </option> ";
		   
		   var x = xmlDoc.getElementsByTagName("produse");
		   for (i = 0; i <x.length; i++) { 
		     table += "	<option value="+x[i].getElementsByTagName("nume")[0].childNodes[0].nodeValue+">" +
		     x[i].getElementsByTagName("nume")[0].childNodes[0].nodeValue +"</option>";
		   }
		   document.getElementById("product").innerHTML = table;
}   
