async function createPitch(url, data) {
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

function createBasketball(){
    const data = {}
    data["name"] =  document.getElementById("name").value;
    data["price"] = document.getElementById("price").value;
    data["lights"] = document.getElementById("lights").value;
    data["sector"] = document.getElementById("sector").value;
    data["min_people"] = document.getElementById("min_people").value;
    data["max_people"] =  document.getElementById("max_people").value;
    data["numberOfBaskets"] = document.getElementById("numberOfBaskets").value;
    console.log(data);
    createAccount("HTTPS://localhost:8181/OrlikRentPAS/api/Pitches/addFootballPitch", data).then(r => alert("Created"))

}

function createFootball(){
    const data = {}
    data["name"] =  document.getElementById("name").value;
    data["price"] = document.getElementById("price").value;
    data["lights"] = document.getElementById("lights").value;
    data["sector"] = document.getElementById("sector").value;
    data["min_people"] = document.getElementById("min_people").value;
    data["max_people"] =  document.getElementById("max_people").value;
    data["grass_type"] = document.getElementById("grass_type").value;
    data["goal_nets"] = document.getElementById("goal_nets").value;
    console.log(data);
    createAccount("HTTPS://localhost:8181/OrlikRentPAS/api/Pitches/addBasketballPitch", data).then(r => alert("Created"))

}