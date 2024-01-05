document.addEventListener("DOMContentLoaded", () => {
    const navTitle = document.getElementById("navTitle")
    const navUl = document.getElementById("navUl")

    navTitle.addEventListener("click", () => {
        if(navUl.classList.contains("nav-ul-hidden"))
        {
            navUl.classList.remove("nav-ul-hidden")
        }
        else
        {
            navUl.classList.add("nav-ul-hidden")
        }
    })
})

document.addEventListener("DOMContentLoaded", () => {
    const navTitle = document.getElementById("navCar")
    const navUl = document.getElementById("formCar")

    navTitle.addEventListener("click", () => {
        if(navUl.classList.contains("form-car-hidden"))
        {
            navUl.classList.remove("form-car-hidden")
        }
        else
        {
            navUl.classList.add("form-car-hidden")
        }
    })
})
