document.getElementById('generateBill').addEventListener("click",function(e)
{    
    console.log(e);
    if (e.path[1][0].value != "" && e.path[1][1].value != "" && e.path[1][2].value != "")
    {
        e.preventDefault();
        
        url = "/BillingModule/Billing_Module/billing/onePerson";
        var xmlhttp;
        if (window.XMLHttpRequest)
        {
            xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
        }
        else
        {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
        }

        xmlhttp.open("GET",url+"?"+$('form').serialize(),true);

        xmlhttp.onreadystatechange = function()
        {
            if(xmlhttp.readyState === XMLHttpRequest.DONE) 
            {
                var status = xmlhttp.status;
                if (status === 0 || (status >= 200 && status < 400)) 
                {
                    window.location.assign("/billingPortal/pages/PDFListAll.jsp");
                    alert ("Bill is made Successfully");
                }
                else
                {
                    alert("No Bill For This Customer");
                }
            }
        };
                    
        xmlhttp.send();
    }
});