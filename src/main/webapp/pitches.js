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
    data["end_date_rental"] = document.getElementById("endData").value;
    data["pitchID"] = document.getElementById("PitchID").value;
    data["start_date_rental"] = document.getElementById("startData").value;
    console.log(data);
    postData("HTTPS://localhost:8181/OrlikRentPAS/api/Rentals", data).then(r => alert("Rented"))

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
loadIntoTable("HTTPS://localhost:8181/OrlikRentPAS/api/Pitches/basketballPitches" , document.querySelector("table"));
loadIntoTable("HTTPS://localhost:8181/OrlikRentPAS/api/Pitches/footballPitches" , document.querySelector(".table1"));
loadIntoTable("HTTPS://localhost:8181/OrlikRentPAS/api/Account/clients" , document.querySelector(".tableUsers"));
loadIntoTable("HTTPS://localhost:8181/OrlikRentPAS/api/Rentals" , document.querySelector(".tableRents"));