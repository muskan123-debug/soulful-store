document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // Prevent default form submission

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const userData = {
        email: email,
        password: password
    };
    // Convert userData to URL-encoded format
    const formBody = new URLSearchParams(userData).toString();

    try {
        const response = await fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: formBody
        });
    
        let data = await response.text(); // Get response as text
    
        if (response.ok) {
            alert("Login successful! Redirecting to explore page...");
            window.location.href = "/postlogin"; // Redirect to explore page
        } else {
            alert(data?.message || data || "Login failed. Please try again.");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Something went wrong. Please try again.");
    }
    
});
