var arrayOfRolloverClasses = new Array();
var arrayOfClickClasses = new Array();
var activeRow = false;
var activeRowClickArray = new Array();
var textValue = "";
var contextPath = '';
function init (context) {
	contextPath = context;
}
function highlightTableRow() {
	var tableObj = this.parentNode;
	if (tableObj.tagName != 'TABLE')
		tableObj = tableObj.parentNode;

	if (this != activeRow) {
		this.setAttribute('origCl', this.className);
		this.origCl = this.className;
	}
	this.className = arrayOfRolloverClasses[tableObj.id];

	activeRow = this;

}

function clickOnTableRow() {
	var tableObj = this.parentNode;
	if (tableObj.tagName != 'TABLE')
		tableObj = tableObj.parentNode;

	if (activeRowClickArray[tableObj.id]
			&& this != activeRowClickArray[tableObj.id]) {
		activeRowClickArray[tableObj.id].className = '';
	}
	this.className = arrayOfClickClasses[tableObj.id];

	activeRowClickArray[tableObj.id] = this;

}

function resetRowStyle() {
	var tableObj = this.parentNode;
	if (tableObj.tagName != 'TABLE')
		tableObj = tableObj.parentNode;

	if (activeRowClickArray[tableObj.id]
			&& this == activeRowClickArray[tableObj.id]) {
		this.className = arrayOfClickClasses[tableObj.id];
		return;
	}

	var origCl = this.getAttribute('origCl');
	if (!origCl)
		origCl = this.origCl;
	this.className = origCl;

}

function addTableRolloverEffect(tableId, whichClass, whichClassOnClick) {
	arrayOfRolloverClasses[tableId] = whichClass;
	arrayOfClickClasses[tableId] = whichClassOnClick;

	var tableObj = document.getElementById(tableId);
	var tBody = tableObj.getElementsByTagName('TBODY');
	if (tBody) {
		var rows = tBody[0].getElementsByTagName('TR');
	} else {
		var rows = tableObj.getElementsByTagName('TR');
	}
	for ( var no = 0; no < rows.length; no++) {
		rows[no].onmouseover = highlightTableRow;
		rows[no].onmouseout = resetRowStyle;

		if (whichClassOnClick) {
			rows[no].onclick = clickOnTableRow;
		}
	}
}
function popup (link, windowName){
	alert (link);
	if (!window.focus)
		return true;
	window.open(link, windowName, 'width=275,height=300,scrollbars=yes');
	return false;
}

function editCustomer (link, windowName){
	if (!window.focus)
		return true;
	var checkBoxes = document.getElementById("customerId");
	alert (checkBoxes);
	var id = "";
	for (var i = 0; i < checkBoxes.length; i++){
		var checkBox = checkBoxes[i];
		alert (checkBox);
		if (checkBox.checked){
			id = checkBox.value;
			break;
		}
	}
	popup (link+"/"+id, windowName);
	return false;
}

function deleteCustomers (){
	var frm = document.getElementById("customerForm");
	frm.action = "customerList/delete";
	frm.submit ();
}

/**
function addCustomer () {
	var ifrm = window.parent.document.getElementById("body");
	ifrm.src="<c:url value="addCustomer"/>";
}

function editCustomer (customerId){
	var ifrm = window.parent.document.getElementById("body");
	ifrm.src="<c:url value="addCustomer"/>/" + customerId;
}

function accountPreferences (customerId) {
	var ifrm = window.parent.document.getElementById("body");
	ifrm.src="<c:url value="customerAcountPreferences"/>/" + customerId;
}

function accountTransaction (customerId){
	var ifrm = window.parent.document.getElementById("body");
	ifrm.src="<c:url value="accountTransaction"/>/" + customerId;
}

*/
function commonCallback(data,ioArgs) {
	document.getElementById("ajaxCustomerList").innerHTML = data;
	addTableRolloverEffect('customerList','tableRollOverEffect1','tableRowClickEffect1');
	var txtField = document.getElementById("textSearch");
	txtField.value = textValue;
}

function helloError(data, ioArgs) {
	alert (data);
	alert (ioArgs);
    alert('Error when retrieving data from the server!');
}

function onLoad (context) {
	init (context);
	reloadCustomerList();
}

function reloadCustomerList () {
	axajCustomerList (contextPath + '/a/customerList', 'commonCallback');
}

function axajCustomerList (thisUrl) {
	dojo.xhrGet({
	      url: thisUrl,
	      load: commonCallback,
	      error: helloError
	});
}

function goToPage (pageNumber) {
	var url = contextPath+'/a/customerList/page/' + pageNumber;
	if (textValue != "")
		url = url + "/"+textValue;
	axajCustomerList (url);
}

function searchCustomer () {
	var txtField = document.getElementById("textSearch");
	textValue = txtField.value;
	var url = contextPath +'/a/customerList/search/' + textValue;
	axajCustomerList (url);
}

function addCustomer (){
	var thisUrl = contextPath+'/a/addCustomer';
	changeAccountTransaction (thisUrl);
}

function changeAccountTransaction (thisUrl){
	dojo.xhrGet({
	      url: thisUrl,
	      load: bodyCallBack,
	      error: helloError
	});
}

function bodyCallBack (data,ioArgs) {
	document.getElementById("ajaxBody").innerHTML = data;
}

function postCustomer () {
	var xhrArgs = {
        form: dojo.byId("customer"),
        handleAs: "text",
        load: function(data) {
            dojo.byId("ajaxBody").innerHTML = data;
            reloadCustomerList ();
        },
        error: function(error) {
            dojo.byId("ajaxBody").innerHTML = "Error occured while saving.";
        }
    };
    //Call the asynchronous xhrPost
	dojo.xhrPost(xhrArgs);
}