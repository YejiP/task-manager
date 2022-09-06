<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Goals</title>

<style>
body{
background-color : #B8B9B9;}


.name{
background-color : #104493;
color:white;
padding  : 10px;
margin : 5px;
}
.list{
margin : 2px;
padding  : 10px;
background-color :#B2D1D8;
width : parent;
}

.col{
float: left;
width: 30%;
background-color : white;
margin:1%;}

.title{
font-size:1.5em;
font-style:"bold";
}

#addNew{
width :16%;
height :50px;
background-color : #5D6399;
color : white;
font-size : 1.2em;
margin-left: 5%;
}
#archive{
width :16%;
height :50px;
background-color : #B6B6F0;
color : white;
font-size : 1.2em;
}

#menu{
margin-right : 5%;
text-align: right;
}

</style>

</head>


<body>
<div id = "menu">
<button id = "addNew"  onclick="window.location='write.jsp'";>Add New Todo</button>
<button id = "archive"  onclick="window.location='archive.jsp'";>Go to Archive</button>

</div>


<div class = "container">


<div class="col">
<div class="name">TODO</div>
<p id="todo" >

</p>
</div>



<div class="col">
<div class="name">DOING</div>
<p id="doing" >

</p>
</div>



<div class="col">
<div class="name" >DONE</div>
<p id="done" >

</p></div>
</div>

</body>

<script id="template-list-item" type="text/template">
<div class= 'list'><div class='title'>{title}</div>Name: {name} Priority: {sequence} Date: {date}<button class='{type} toNext'  data-class={type} data-id = {bigint}><img width='10px' src='https://image.flaticon.com/icons/png/512/57/57116.png'></button><br>
</div>
</script> 

<script>
window.addEventListener('DOMContentLoaded', (event) => {
	init()
});
function init(){
	show();
}
function show(a,b){
	var todos = "";
	    var doing = ""
	    var done = ""
		var oReq = new XMLHttpRequest();
	    
	    if(a==undefined){
			oReq.open("POST","task");
			oReq.send();
		}
		else{
			//todo doing
			oReq.open("PUT", "task"+"/"+a +"/"+b, true);
			oReq.setRequestHeader('Content-type','application/json; charset=utf-8');
			oReq.send();
			}

		oReq.addEventListener("load",function(){
			var todos ="";
			var doing ="";
			var done = "";

		    var myArr = JSON.parse(this.responseText);
			for(let i =0;i<myArr.length;i++){
				var tmp = "";

				tmp= document.querySelector("#template-list-item").innerHTML;
				//alert(tmp)
				if(myArr[i]["regdate"]!=null){
					var datenew=myArr[i]["regdate"].slice(0, 10);

				}
				//tmp.replace() 를 하면, 문자열을 반환한다. tmp가 바뀌는게 아니네..맞네..
				
				var res=tmp.replace("{title}",myArr[i]["title"])
				.replace("{name}",myArr[i]["name"])
				.replace("{sequence}",myArr[i]["sequence"])
				.replace("{date}",datenew)
				.replace("{bigint}",myArr[i]["bigint"])
				.replaceAll("{type}",myArr[i]["type"]);
				//alert(res)
								
				if(myArr[i]["type"]=="todo"){
					todos +=res;
				}else if(myArr[i]["type"]=="doing"){
					doing +=res;
				}else if(myArr[i]["type"]=="done"){			
					done +=res;
				}
			}		

			document.getElementById("todo").innerHTML = todos;
			document.getElementById("doing").innerHTML = doing;
			document.getElementById("done").innerHTML = done;


	});
		taskItemContainer = document.querySelectorAll(".col");
		//set the eventlistener on upper element.
		for(i=0;i<taskItemContainer.length;i++){
			taskItemContainer[i].addEventListener("click",function(evt){
				if(evt.target.type=="submit"){
					var clicked = evt.target.getAttribute("data-class");
					var id= evt.target.getAttribute("data-id");
					show(id,clicked); 
					}
				
			})
		}
	}
	

</script>


</html>