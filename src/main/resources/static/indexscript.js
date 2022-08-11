(function () {


const input = document.querySelector("input");
const logInButton = document.querySelector("#loginButton");
logInButton.addEventListener("click", function () {openLoggedInPage()});


//CREATE USER FUNCTION

createUser = () => {
    console.log('create User called');
    fetch("/create", {
        method: "POST",
        body: JSON.stringify(
            {
                "endUserName": `${input.value}`,
            }
        ),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(res => res.json().then(body => console.log(body)))
        .catch(err => console.error(err + "WAGUAN"));
}





//UPDATE USER FUNCTION

async function login() { 
    let response = await fetch(`/update/${input.value}`, {
        method: "PATCH",
        body: JSON.stringify(
            {
                "isLoggedin": true
            }
        ),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(res => res.json().then(body => {console.log(body);}))
        .catch(err => console.error(err + "WAGUAN"));
        console.log(response, "login");
    return response;
}



async function openLoggedInPage() {

   await login().then(res => {window.open("loggedIn.html", "_self")});

}

})();