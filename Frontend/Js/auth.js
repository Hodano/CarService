// auth.js
class Auth {
    static setToken(token) {
        localStorage.setItem('jwtToken', token);
    }

    static getToken() {
        return localStorage.getItem('jwtToken');
    }

    static logOut() {
        localStorage.removeItem('jwtToken');
        window.location.href = 'file:///D:/Studia_Semestr_7/Zaawansowane%20technologie%20projektowania%20aplikacji%20internetowyuch/Project/Frontend/view/login.html'; 
    }
}

// Eksportuj, aby używać w innych skryptach
export default Auth;