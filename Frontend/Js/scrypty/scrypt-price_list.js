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
    const navTitle = document.getElementById("navService")
    const navUl = document.getElementById("formService")

    navTitle.addEventListener("click", () => {
        if(navUl.classList.contains("form-service-hidden"))
        {
            navUl.classList.remove("form-service-hidden")
        }
        else
        {
            navUl.classList.add("form-service-hidden")
        }
    })
})
