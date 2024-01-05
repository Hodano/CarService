const form = document.querySelector("form");

const emailInput = form.querySelector('input[name="email"]')

const confirmedPasswordInput = form.querySelector('input[name="confirmedPassword"]');


function isEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

function arePasswordsSame(password, confirmedPassword) {
    return password === confirmedPassword;
}

function markValidation(element, condition) {
    !condition ? element.classList.add('no-valid') : element.classList.remove('no-valid');
}
function validateEmail() {
    setTimeout(function () {
            markValidation(emailInput, isEmail(emailInput.value));
        },
        300
    );
}

function validatePassword() {
    setTimeout(function () {
        const passwordInput = confirmedPasswordInput.parentElement.previousElementSibling.querySelector('input');
        const condition = arePasswordsSame(passwordInput.value, confirmedPasswordInput.value);
        markValidation(confirmedPasswordInput, condition);
    }, 300);
}

emailInput.addEventListener('keyup', validateEmail);
confirmedPasswordInput.addEventListener('keyup', validatePassword);