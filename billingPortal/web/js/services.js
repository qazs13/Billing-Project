document.getElementById('su').addEventListener("click",function(e)
{   
    if (e.path[2][0].value != "" && (e.path[2][1].value != e.path[2][2].value))
    {
        e.preventDefault();
        url = "/billingPortal/Add_Services";
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
                    location.reload();
                    alert("Service is Added Successfully");
                }
                else
                {
                    alert("Service is Already Exist Please See The Table First");
                }
            }
        };
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        xmlhttp.send($('form').serialize());
    }
    else
    {
        alert("You Can't Select Recurring and One Time");
    }
});