window.onload = function (){
	var elem=document.getElementsByClassName("lejszam")[0];
	
	elem.focus()
	
	elem.setSelectionRange(elem.value.length,elem.value.length);
	
	elem.value='';
	
	var bejel=document.getElementsByClassName("bejelentkezes")[0];
	
	bejel.value='';
	
	/*elem.addEventListener("input", function() {
		if(this.value.match("^[0-9]{9}$")){
			var kiv=document.getElementsByClassName("enter")[0]
			kiv.click();
		}
	})*/
}