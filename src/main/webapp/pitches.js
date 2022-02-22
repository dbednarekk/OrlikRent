async function loadIntoTable(url, table){
    const tableBody = table.querySelector("tbody");
const response = await fetch(url, {
    headers:{
        'Authorization': `Bearer ${"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYmVkbmFyZWsiLCJhdXRoIjoiQWRtaW4iLCJpc3MiOiJPcmxpa1JlbnQiLCJleHAiOjE2NDU0OTMxMDF9.hSY-NSplqvGJzKG7IEyThhuTlQt3Q0ftCYIewguo0RE"}`
    }});
const data = await response.json();


tableBody.innerHTML = "";
for (const row of data){
    const rowElement = document.createElement("tr");

    for (const cellText of row){
        const cellElement = document.createElement("td");

        cellElement.textContent = cellText;
        rowElement.appendChild(cellElement);
    }

    tableBody.appendChild(rowElement);
}
}
loadIntoTable("https://localhost:8181/OrlikRentPAS/api/Pitches/basketballPitches" , document.querySelector("table"));