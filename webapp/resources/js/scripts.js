 
/**Validate fields fill */
function verificar(xhr, status, args, dlg, tbl) {//incluiu dlg,tbl
	if(args.validationFailed) {
		PF(dlg).jq.effect("shake", {times:5}, 100);
		PF(dlg).show();
	}
	else {
		PF(dlg).hide();
		PF(tbl).clearFilters();
	}
}
	
function start() {
	 PF('statusDialog').show();
}

function stop() {
    PF('statusDialog').hide();
}

/**Validate login fields.*/
function LoginRequest(xhr, status, args, dlg) { /* acrescentado dlg */
    if(args.validationFailed || !args.loggedIn) {
        PF(dlg).jq.effect("shake", {times:5}, 100); 
    }
    else {
        PF(dlg).hide();
        $('#menuPrincipal.xhtml').fadeOut();
    }
}
    
function addFilledClassInputMask(inputMask){
	if ($(inputMask).val().length == 18){
		$(inputMask).addClass("ui-state-filled");
	}
}

/**Number counter lbl number*/
function countNumber(){
	$('.count-number').each(function () {
	    $(this).prop('Counter',0).animate({
	        Counter: $(this).text()
	    }, {
	        duration: 2500,
	        easing: 'swing',
	        step: function (now) {
	            $(this).text(Math.ceil(now));
	        }
	    });
	});
}

/**Number counter lbl percentage */
function countPercentNumber(){
	$('.count').each(function (){
		$(this).prop('Counter',0).animate({
			Counter: $(this).text()
		}, {
			duration:500,
			easing: 'swing',
			step: function (now) {
				$(this).text(now.toFixed(0).replace(/[^\d]+/g,'') + '%');
			}
		});
	});
}

/**Number counter lbr use this function*/
function formatReal(mixed) {
	var int = parseInt(mixed.toFixed(2).toString().replace(/[^\d]+/g, ''));
	var tmp = int + '';
	tmp = tmp.replace(/([0-9]{2})$/g, ",$1");
	if (tmp.length > 6)
		tmp = tmp.replace(/([0-9]{3}),([0-9]{2}$)/g, ".$1,$2");

	return tmp;
}

/**Show and hidden chat. painel*/
function Mudarestado(el) {
    var display = document.getElementById(el).style.display;
    if(display == "none")
        document.getElementById(el).style.display = 'block';
    else
        document.getElementById(el).style.display = 'none';
}

/**hideen object for one period*/
function delay(objt,visible,time) {
	setTimeout(function(){
		document.getElementById(objt).style.visibility = visible;
	},time);
}

/** set datatable for position zero */
function resetTabView(tabViewId){
	//alert("activeindex="+$("[id='"+tabViewId+"_activeIndex']").val());
	$("[id='"+tabViewId+"_activeIndex']").val(0);
}

/** Validate fields without data table .*/
function validateFields(xhr, status, args, dlg) {
	if(args.validationFailed) {
		PF(dlg).jq.effect("shake", {times:5}, 100);
		PF(dlg).show();
	}
	else {
		PF(dlg).hide();
	}
}

/**Call popup with new window, verify if browser not block of action */
function popupwindow(url, title) {      
    window.open(url , title, "toolbar=no, scrollbars=yes, resizable=yes, top=170, left=170, width=800, height=600");        
} 









