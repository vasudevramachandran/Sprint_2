/**
 * @Vasudev
 * Added for the Add Bill page.
 */
function collateDataAndForward(){
	
	var checked[];
	
	checked = documents.getElementsByName("friendChecked");
	
	for(i=0; i < checked.length ; i++){
		
		alert(checked[i].value);
	}
	
	return false;
}