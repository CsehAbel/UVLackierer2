function init(){
	var inputs=document.getElementsByTagName('input');
	
	for(var i=0;i<inputs.length;i++){
		inputs[i].value="";
	}
	
}

window.onload= init;