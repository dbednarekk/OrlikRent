const baseurl = "HTTPS://localhost:8181/OrlikRentPAS/api";
function login(){
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET",baseurl + "/all",true);
    xmlhttp.onreadystatechange = function() {
        if(xmlhttp.readyState ===4 && xmlhttp.status ===200){
            var persons = JSON.parse(xmlhttp.responseText);
            var tbltop = `<table>
			    <tr><th>Id</th><th>First Name</th><th>Last Name</th><th>Age</th></tr>`;
            //main table content we fill from data from the rest call
            var main ="";
            for (i = 0; i < persons.length; i++){
                main += "<tr><td>"+persons[i].id+"</td><td>"+persons[i].firstName+"</td><td>"+persons[i].lastName+"</td><td>"+persons[i].age+"</td></tr>";
            }
            var tblbottom = "</table>";
            var tbl = tbltop + main + tblbottom;
            document.getElementById("personinfo").innerHTML = tbl;
        }
    };
    xmlhttp.send();
}
window.onload = function(){
    loadPersons();
}