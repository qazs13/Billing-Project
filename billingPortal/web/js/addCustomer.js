servicesCounter = 0;
allRecurringServicesName = [];

document.getElementById('addServiceButton').addEventListener("click",function(e)
{
    selectedService = document.getElementById('selectRecurringServices').value;
    allSelectedServicesDiv = document.getElementById('recurringServices');    
    if (!allRecurringServicesName.includes(selectedService))
    {
        servicesCounter++;
        allRecurringServicesName.push(selectedService);
        allSelectedServicesDiv.innerHTML += "<div>"+selectedService+"</div>";
    }
    else
    {
        alert("Sorry You Have Added this Serivce Already");
    }
});

document.getElementById('submitAddCustomer').addEventListener("click",function(e)
{
    console.log(allRecurringServicesName);
    if (servicesCounter > 0)
    {
        e.preventDefault();
        document.getElementById('allRecurringServices').value = allRecurringServicesName;
        
        url = "/billingPortal/Add_Customer";
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
                    alert ("Added to Customer Successfully");
                }
                else
                {
                    alert("This MSISDN is Already Existed or Maybe Something went wrong");
                }
            }
        };
    
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        
        xmlhttp.send($('form').serialize());
    }
    else if (e.path[1][0].value != "" && e.path[1][1].value != "" && e.path[1][2].value != "" && e.path[1][3].value != "")
    {
        e.preventDefault();
        alert("Please Add At Lease One Recurring Serivce");
    }
});
