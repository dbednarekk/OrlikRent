async function postData(url, data) {
    const response = await fetch(url, {
        method: 'POST',
        mode: 'cors', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: {
            'Content-Type': 'application/json'

        },
        redirect: 'follow', // manual, *follow, error
        body: JSON.stringify(data)
    });
    return response.json();
}

function onRent(){
    const data = {}
    data["accountID"] =  document.getElementById("AccountID").value;
    data["active"] = true;
    data["end_date_rental"] = document.getElementById("endData").value+":00.000Z";
    data["pitchID"] = document.getElementById("PitchID").value;
    data["start_date_rental"] = document.getElementById("startData").value+":00.000Z";
    console.log(data);
    postData("http://localhost:8080/OrlikRentPAS/api/Rentals/addRent", data).then(r => alert("Rented"))

}
async function loadIntoTable(url, tables){
    const tableBody = tables.querySelector("tbody");
const response = await fetch(url);
const data = await response.json();
console.log(data)

tableBody.innerHTML = "";
    let cellElements;
    for (const row of data) {
        const rowElement = document.createElement("tr");
        cellElements = "<td>" + row.id +"<td>";
        for (let cellText of Object.values(row)) {
            const cellElement = document.createElement("td");

            cellElement.textContent = cellText;
            rowElement.appendChild(cellElement);
        }
        const button = document.createElement("button")
        button.textContent = "Remove";
        button.className = "deleteBtn";
        rowElement.appendChild(button)
     /*   const button2 = document.createElement("button")
        button2.textContent = "Add";
        button2.className = "addBtn";
        rowElement.appendChild(button2)
        const button3= document.createElement("button")
        button3.textContent = "Update";
        button3.className = "updateBtn";
        rowElement.appendChild(button3)*/
        tableBody.appendChild(rowElement);

    }
}

function onDelete(e){
    if(!e.target.classList.contains("deleteBtn")){
        return;
    }
    const row = e.target;

    const btn = e.target;
    alert(btn.closest("tr").id)
}

document.querySelector("table").addEventListener('click', onDelete);
document.querySelector(".table1").addEventListener('click', onDelete);
loadIntoTable("http://localhost:8080/OrlikRentPAS/api/Pitches/basketballPitches" , document.querySelector("table"));
loadIntoTable("http://localhost:8080/OrlikRentPAS/api/Pitches/footballPitches" , document.querySelector(".table1"));
loadIntoTable("http://localhost:8080/OrlikRentPAS/api/Account/clients" , document.querySelector(".tableUsers"));
loadIntoTable("http://localhost:8080/OrlikRentPAS/api/Rentals" , document.querySelector(".tableRents"));