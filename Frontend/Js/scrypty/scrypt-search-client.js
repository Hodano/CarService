const search =  document.querySelector('input[placeholder="search client"]');
const clientContainer = document.querySelector(".client-section");
search.addEventListener("keyup", function (event){
    if (event.key === "Enter") {
        event.preventDefault();

        const data = {search: this.value};

        fetch("/search", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(function (response) {
            return response.json();
        }).then(function (clients) {
            clientContainer.innerHTML = "";
            loadClient(clients)
        });
    }
});

function loadClient(clients) {
    clients.forEach(client => {
        console.log(client);
        createClient(client);
    });

}

function createClient(client){
    const template = document.querySelector("#client-template");
    const clone = template.content.cloneNode(true);

    const name = clone.querySelector(".name");
    name.innerHTML = client.name;
    const address = clone.querySelector(".address");
    address.innerHTML = client.address;
    const phoneNumber = clone.querySelector(".phoneNumber");
    phoneNumber.innerHTML = client.phone_number;
    const email = clone.querySelector(".email");
    email.innerHTML = client.email;

    clientContainer.appendChild(clone);
}