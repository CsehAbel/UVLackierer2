window.onload = function (){
	var elem=document.getElementsByClassName("kivezet")[0];
	
	elem.focus()
	
	elem.setSelectionRange(elem.value.length,elem.value.length);
	
	elem.value='';
	
	//
	var elem2=document.getElementsByClassName("kivezet")[1];
	
	elem2.focus()
	
	elem2.setSelectionRange(elem2.value.length,elem2.value.length);
	
	elem2.value='';
	//
	
	var bejel=document.getElementsByClassName("bejelentkezes")[0];
	
	bejel.value='';
	
	/*elem.addEventListener("input", function(event) {
		if(event.keyCode === 13){
			var kiv=document.getElementsByClassName("gomb")[0]
			kiv.click();
			return false;
		} 
		
	})
	
	elem2.addEventListener("input", function(event) {
		if(event.keyCode === 13){
			var kiv=document.getElementsByClassName("vipgomb")[0]
			kiv.click();
			return false;
		} 
	})*/
}
		