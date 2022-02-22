async function createAccount(url, data) {
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

function createUser(){
    const data = {}
    data["login"] =  document.getElementById("login").value;
    data["password"] = document.getElementById("password").value;
    data["email"] = document.getElementById("email").value;
    data["first_name"] = document.getElementById("firstName").value;
    data["last_name"] = document.getElementById("lastName").value;
    console.log(data);
    createAccount("HTTPS://localhost:8181/OrlikRentPAS/api/Account/client", data).then(r => alert("Created"))

}

function createManager(){
    const data = {}
    data["login"] =  document.getElementById("login").value;
    data["password"] = document.getElementById("password").value;
    data["email"] = document.getElementById("email").value;
    data["salary"] = document.getElementById("salary").value;
    data["numberOfShifts"] = document.getElementById("numberOfShifts").value;
    console.log(data);
    createAccount("HTTPS://localhost:8181/OrlikRentPAS/api/Account/manager", data).then(r => alert("Created"))

}

function createAdmin(){
    const data = {}
    data["login"] =  document.getElementById("login").value;
    data["password"] = document.getElementById("password").value;
    data["email"] = document.getElementById("email").value;
    console.log(data);
    createAccount("HTTPS://localhost:8181/OrlikRentPAS/api/Account/admin", data).then(r => alert("Created"))

}