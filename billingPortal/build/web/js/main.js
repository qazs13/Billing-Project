document.getElementById('su').addEventListener("click",function(e){
    e.preventDefault();
    url = "/billingPortal/loginCheck";
    var xmlhttp;
    if (window.XMLHttpRequest)
    {
        xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
    }
    else
    {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
    }
    xmlhttp.open("POST",url,true);    
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState === XMLHttpRequest.DONE) 
        {
            var status = xmlhttp.status;
            if (status === 0 || (status >= 200 && status < 400)) 
            {
                window.location.assign("pages/mainPage.jsp");
            }
            else
            {
                alert("you have entered a wornd username or password");
            }
        }
    };
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send($('form').serialize());
});