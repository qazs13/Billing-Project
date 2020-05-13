var userNumber = parseInt(document.getElementById('usersNumber').textContent);
var rateNumber = parseInt(document.getElementById('rateNumber').textContent);
var serviceNumber = parseInt(document.getElementById('servicesNumber').textContent);
var cdrNumber = parseInt(document.getElementById('cdrNumber').textContent);

var userCircle  = document.getElementById('userscircle2').style.strokeDashoffset = 440 - (440 * userNumber ) / 100;
var rateCircle = document.getElementById('ratecircle2').style.strokeDashoffset = 440 - (440 * rateNumber ) / 100;
var serviceCircle = document.getElementById('servicescircle2').style.strokeDashoffset = 440 - (440 * serviceNumber ) / 100;
var cdrCircle = document.getElementById('cdrcircle2').style.strokeDashoffset = 440 - (440 * cdrNumber ) / 100;