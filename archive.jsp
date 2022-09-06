<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Good Job!</title>
<style>
body{
background-color : #B8B9B9;
}

#res{
width : 70%;
background-color : white;
margin : 0 auto;

}

.title{
font-size:1.5em;
font-style:"bold";
}

.list{
text-align : center;
margin : 2px;
padding  : 10px;
background-color :#B2D1D8;
width : parent;
}
#back{
width :16%;
height :50px;
background-color : #B6B6F0;
color : white;
font-size : 1.2em;
margin : 2%;
}
#container{
text-align : center;

}
</style>

</head>
<body>
<div id = container>
<input type="button" value="Go Back"  id="back" onclick = "window.location='main.jsp'" >

<p id = res>
</p>
</div>

</body>

<script>
window.addEventListener('load',init);
function init(){
	show();
}
function show(a){
	var oReq = new XMLHttpRequest();
	var arc = "";
	oReq.addEventListener("load",function(){
	    var myArr = JSON.parse(this.responseText); 
		for(let i =0;i<myArr.length;i++){
			arc +="<div class= 'list'><div class='title'>"+myArr[i]["title"]+"</div>";
			arc +="Name: "+myArr[i]["name"];
			arc +=" Priority: "+myArr[i]["sequence"];
			arc +=" Date: "+ myArr[i]["regdate"].slice(0, 10);
			arc +="<button class='delete' onclick='buttonClick(this)' data-id = "+myArr[i]["bigint"]+">Delete</button></div>";
			arc +="<br>";
		}
		document.getElementById("res").innerHTML = arc;	

	});
	if(a!=undefined){
		//if delete is clicked
		oReq.open("DELETE","arc"+"/"+a,true);
		oReq.setRequestHeader('Content-type','application/json; charset=utf-8');
		oReq.send();
	}else{
		oReq.open("POST","arc");
		oReq.send();
	} }
function buttonClick(e){
	var a = e.getAttribute("data-id");
	if (confirm("Will you really delete this item?")) {
			show(a);
		} else {
			show();
		}
	
	
}



</script>
</html>