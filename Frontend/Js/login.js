import {pathLogin} from './path.js';

document.addEventListener("DOMContentLoaded", function() {
    const loginForm = document.getElementById('logInForm');
    


    loginForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Zatrzymaj domyślne przesyłanie formularza

        const email = document.querySelector('.field-email').value;
        const password = document.querySelector('.field-password').value;

        const requestData = {
            email: email,
            password: password

        
        };
        
        fetch('http://localhost:8080/auth/authenticate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)
        })
        
        .then(response => {
            if(!response.ok) {
                throw new Error('Network response was not ok'); // Rzuć błąd, jeśli odpowiedź nie jest ok
            }
            return response.json(); // Przekształć odpowiedź w JSON
        })
        .then(data => {
            console.log("Received data:", data); // Wyświetl otrzymane dane dla celów debugowania
             const jwtToken = data.token;
            console.log("JWT Token:", jwtToken); // Wyświetl token JWT
            if(jwtToken) {
                localStorage.setItem('jwtToken', jwtToken); // Przechowaj token w localStorage
            }
        })
        .then(data => {
            console.log(data);
            window.location.href = pathLogin; // Przekieruj na główną stronę
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
    
    });
});