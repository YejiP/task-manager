<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Goals!</title>
<Style>
body{
background-color : #B8B9B9;}

.container{
margin : 0 auto;
width : 60%;
text-align: center;
padding : 20px;

background-color : white;
}
</Style>
</head>
<body>
<div class="container">
<h1>Register Todo</h1>
<form id="go" action="register" method="post">
<p>What is it about?<br> <input type="text" id="title" name = "title" placeholder="Study Swift (upto 24 characters)" maxlength =24 autocomplete ="off" required></input></p>

<p>Whose task is it?<br><input type="text" id="who"  name = "who" autocomplete ="off" placeholder="Jane Doe" required></p> 

<p>Set priority
<label><input type="radio" name = "priority" value = "1" required>1</label>
<label><input type="radio" name = "priority" value = "2" required>2</label>
<label><input type="radio" name = "priority" value = "3" required>3</label>
</p>
<input type="button" value="Go Back"  id="back" >
<input type="submit">
<input type="button" value="Clear" id="clear">
</form>
</div>
</body>
<script>
window.addEventListener('load', init);	
function init(){
	clear();
	back();
}

function clear(){
	document.getElementById("clear").onclick = function(){
		document.getElementById("title").value="";
		document.getElementById("who").value="";
		var elements =document.getElementsByName("priority");
		for (i=0;i<elements.length;i++) {
			elements[i].checked=false;
		}
		
	}
}

function back(){
	document.getElementById("back").onclick = function(){
		window.location ='main.jsp';
	}
}
</script>


</html>