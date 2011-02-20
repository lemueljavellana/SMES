var arrayOfRolloverClasses = new Array();
var arrayOfClickClasses = new Array();
var activeRow = false;
var activeRowClickArray = new Array();
var textValue = "";
var contextPath = '';
var toBePaidAccounts = null;

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

function onClckAccountCheckbock () {
	var checkBoxes = document.getElementsByName("cb");
	var checkedBox = 0;
	for (var i = 0; i < checkBoxes.length; i++){
		var checkBox = checkBoxes[i];
		if (checkBox.checked) {
			checkedBox = checkedBox + 1;
			if (checkedBox >= 2){
				break;
			}
		}
	}
	//Disable edit button
	var dEb = true;
	//Disable delete button
	var dDb = true;
	if (checkedBox == 1){
		dEb = false;
		dDb = false;
	} else if (checkedBox > 1) {
		dEb = true;
		dDb = false;
	}
	document.getElementById("editButton").disabled = dEb;
	document.getElementById("deleteButton").disabled=dDb;
}


function deleteCustomers (){
	var frm = document.getElementById("customerForm");
	frm.action = "customerList/delete";
	frm.submit ();
}

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
function showCustomerAccount (customerId) {
	var thisUrl = contextPath+'/a/accountTransaction/' + customerId;
	changeAccountTransaction (thisUrl);
}

function addCustomer (){
	var thisUrl = contextPath+'/a/addCustomer';
	dojo.xhrGet({
	      url: thisUrl,
	      load: addCustomerCallBack,
	      error: helloError
	});
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
	addTableRolloverEffect('customerAccount','tableRollOverEffect2','tableRowClickEffect2');
}

function addCustomerCallBack (data, ioArgs) {
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

function saveAccount (customerId) {
	var xhrArgs = {
		form : dojo.byId("account"),
		handleAs : "text",
		load : function(data) {
			if(data == "success"){
				showCustomerAccount (customerId);
			} else {
				dojo.byId("AddEdittransaction").innerHTML = data;
			}
		},
		error : function(error) {
			dojo.byId("ajaxBody").innerHTML = "Error occured while saving.";
		}
	};
	// Call the asynchronous xhrPost
	dojo.xhrPost(xhrArgs);
}

function getCheckedAccountId () {
	var checkBoxes = document.getElementsByName("cb");
	var id = new Array ();
	var index = 0;
	for (var i = 0; i < checkBoxes.length; i++){
		var checkBox = checkBoxes[i];
		if (checkBox.checked) {
			id[index++] = checkBox.value;
		}
	}
	return id;
}

function deleteAccount (customerId) {
	var ids = getCheckedAccountId();
	var conCat = null;
	for (var i=0; i < ids.length; i++) {
		if (conCat == null)
			conCat = ids[i];
		else 
			conCat = conCat + "," +ids[i];
		
	}
	var thisUrl = contextPath + "/a/accountTransaction/"+customerId+"/deleteAccount/";
	thisUrl = thisUrl + conCat;
	reloadAfterProcess(thisUrl, customerId);
}

function editAcccount (customerId) {
	var ids = getCheckedAccountId();
	if (ids.length > 1)
		alert ("please check one account only");
	var id = ids[0];
	var thisUrl = contextPath + "/a/accountTransaction/"+customerId+"/editAccount/"+id;
	showTransactionForm(thisUrl);
}

function addAccount (customerId) {
	var thisUrl = contextPath+"/a/accountTransaction/"+customerId+"/addAccount";
	showTransactionForm(thisUrl);
}

function addPayment (customerId){
	var thisUrl = contextPath+"/a/accountTransaction/"+customerId+"/addPayment";
	showTransactionForm(thisUrl);
}

function savePayment (customerId){
	postTransaction(customerId, "payment");
}

function postTransaction (customerId, divId){
	var xhrArgs = {
		form : dojo.byId(divId),
		handleAs : "text",
		load : function(data) {
			dojo.byId("AddEdittransaction").innerHTML = data;
			showCustomerAccount (customerId);
		},
		error : function(error) {
			dojo.byId("ajaxBody").innerHTML = "Error occured while saving.";
		}
	};
	// Call the asynchronous xhrPost
	dojo.xhrPost(xhrArgs);
}

function reloadAfterProcess (thisUrl, customerId) {
	dojo.xhrGet({
	      url: thisUrl,
	      load: function (data) {
	    	  		if (data == "success"){
	    	  			showCustomerAccount (customerId);
	      			}
	      		},
	      error: function (data, ioArgs){
	    	  		dojo.byId("AddEdittransaction").innerHTML = "unknown error";
	      		}
	});
}


function showTransactionForm (thisUrl) {
	dojo.xhrGet({
	      url: thisUrl,
	      load: function (data) {
	    	  		dojo.byId("AddEdittransaction").innerHTML = data;
	      		},
	      error: function (data, ioArgs){
	    	  		dojo.byId("AddEdittransaction").innerHTML = "unknown error";
	      		}
	});
}

function showTab (tabUrl) {
	dojo.xhrGet({
      url: tabUrl,
      load: function (data) {
    	  dojo.byId("tabContent").innerHTML = data;
      },
      error: function (data, ioArgs){
    	  dojo.byId("tabContent").innerHTML = "unknown error";
      }
	});
}

function showPaymentTab (customerId) {
	toBePaidAccounts = new Array();
	var tabUrl = contextPath + "/a/"+customerId + "/payment";
	document.getElementById("arTabHeader").class = "";
	document.getElementById("paymentTabHeader").class = "current_page_item";
	showTab(tabUrl);
}

function addAccountsPayment (customerId){
	var checkedAccount = getCheckedAccountId();
	var lastIndex = 0;
	if (toBePaidAccounts.length != 0)
		lastIndex = toBePaidAccounts.length;
	for (var i=0; i < checkedAccount.length; i++ ){
		var id = checkedAccount[i];
		toBePaidAccounts[lastIndex++] = id;
	}
	var toBeExclude = null;
	for (var i=0; i < toBePaidAccounts.length; i++){
		var id = toBePaidAccounts[i];
		if (toBeExclude == null)
			toBeExclude = id;
		else 
			toBeExclude = toBeExclude + ","+ id;
	}
	var thisUrl = contextPath + "/a/"+ customerId + "/payment/excludeAccounts/" + toBeExclude;
	alert (thisUrl);
	updateAccountsTable(thisUrl);
}

function updateAccountsTable (thisUrl){
	dojo.xhrGet({
      url: thisUrl,
      load: function (data) {
    	  dojo.byId("paymentTop").innerHTML = data;
      },
      error: function (data, ioArgs){
    	  dojo.byId("paymentTop").innerHTML = "unknown error";
      }
	});
}