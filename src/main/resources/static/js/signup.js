document.getElementById("signupForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // Prevent default form submission

    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const phoneNumber = document.getElementById("phoneNumber").value;


    const userData = {
        name: username,
        email: email,
        password: password,
        phoneNumber: phoneNumber,
        role: "USER" // Default role
    };

    try {
        const response = await fetch("http://localhost:8080/auth/test/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(userData)
        });

        const data = await response.json();
        if (response.ok) {
            alert("Signup successful! Redirecting to explore page...");
            window.location.href = "/explore"; // Redirect to explore page
        } else {
            alert(data.message || "Signup failed. Please try again.");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Something went wrong. Please try again.");
    }
});
