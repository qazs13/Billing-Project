profileName = "";
document.getElementById('firstPageProfileSubmit').addEventListener("click",function(e)
{
    if (e.path[2][0].value != "" && e.path[2][1].value != 0 && e.path[2][2].value != 0)
    {
        console.log(e.path[2][1].value);
        e.preventDefault();
        url = "/billingPortal/Add_Profile";
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
                    alert ("First Step Profile Added Successfully");                    
                    e.path[2].style.display = "none";
                    document.getElementById('secondPageProfile').style.display = "block";
                    profileName = document.getElementById('profileName').value;
                    document.getElementById('secondProfilePageTitle').textContent += profileName;
                }
                else
                {
                    alert("Profile is Already Exist or Maybe Something Went Wrong");
                }
            }
        };
    
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        
        xmlhttp.send($('form').serialize());        
    }
});

servicesCounter = 0;
allServicesName = [];

document.getElementById('addServiceButton').addEventListener("click",function(e)
{
    selectedService = document.getElementById('selectService').value;
    allSelectedServicesDiv = document.getElementById('allSelectedServices');    
    if (!allServicesName.includes(selectedService))
    {
        servicesCounter++;
        allServicesName.push(selectedService);
        allSelectedServicesDiv.innerHTML += '<section><span>'+selectedService+'</span>'+
        '<label>Round Unit</label><input type="number" value="0" min="1" name="serviceRound'+selectedService+'"><h6>Seconds/SMSs/MBs</h6>'+
        '<label>Fees For Same Local Operator</label><input type="number" value="0" min="0" name="serviceFeesSameLocal'+selectedService+'"><h6>LE</h6>'+
        '<label>Fees For Others Local Operator</label><input type="number" value="0" min="0" name="serviceFeesOtherLocal'+selectedService+'"><h6>LE</h6>'+
        '<label>Fees For International</label><input type="number" value="0" min="0" name="serviceFeesInternational'+selectedService+'"><h6>LE</h6></section>';
    }
    else
    {
        alert("Sorry You Have Added this Serivce Already");
    }
});

document.getElementById('secondPageProfileSubmit').addEventListener("click",function(e)
{
    e.preventDefault();
    if (servicesCounter > 0)
    {
        document.getElementById('allServicesNames').value = allServicesName;
        document.getElementById('profileNameServices').value = profileName;
        
        url = "/billingPortal/Add_Services_To_Profile";
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
                    alert ("Second Step Services Added to Profile Successfully");
                    e.path[2].style.display = "none";
                    document.getElementById('thirdPageProfile').style.display = "block";
                    document.getElementById('thirdPageProfilePageTitle').textContent += profileName;                    
                }
                else
                {
                    alert("Services to This Profile Maybe Already Exist or Maybe Something went wrong");
                }
            }
        };
    
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        
        xmlhttp.send($('form').serialize());
    }
    else
    {
        alert("Please Add At Lease One Serivce");
    }
});

document.getElementById('thirdPageProfileSubmit').addEventListener("click",function(e)
{
    if (e.path[2][0].value != 0 && e.path[2][1].value != 0 && e.path[2][2].value != 0 && e.path[2][3].value != 0)
    {      
        e.preventDefault();  
        url = "/billingPortal/Add_Free_Units";

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
                    window.location.assign("allProfilesList.jsp");
                    alert ("Third Step Free Units Added to Profile Successfully");
                }
                else
                {
                    alert("Something Went wrong");
                }
            }
        };
    
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        
        xmlhttp.send($('form').serialize());
    }
});