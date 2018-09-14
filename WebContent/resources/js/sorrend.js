window.onload = function (){
	var elem=document.getElementsByClassName("kivezet")[0];
	
	elem.focus()
	
	elem.setSelectionRange(elem.value.length,elem.value.length);
	
	elem.value=0;
	
	elem.addEventListener("input", function() {
		if(this.value.match("^[0-9]{9}$")){
			var kiv=document.getElementsByClassName("gomb")[0]
			kiv.click();
		}
	})
}
		