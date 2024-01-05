import Auth from './auth.js'; // Import klasy Auth

document.addEventListener("DOMContentLoaded", function() {
   
    // Sprawdź, czy użytkownik jest zalogowany
    if (!Auth.getToken()) {
        window.location.href = '/view/login.html'; // Przekieruj do logowania, jeśli nie ma tokenu
        return;
    }
});