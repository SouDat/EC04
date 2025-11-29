// Save JWT after login
function login() {
    const username = document.getElementById("loginUsername").value;
    const password = document.getElementById("loginPassword").value;

    fetch("/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    })
    .then(async res => {
        if (!res.ok) throw new Error("Invalid credentials");
        const data = await res.json();
        localStorage.setItem("jwtToken", data.token);
        window.location.href = "index.html"; // redirect to main page
    })
    .catch(err => {
        document.getElementById("loginMsg").textContent = err.message;
    });
}


// Register
function register() {
    const username = document.getElementById("regUsername").value;
    const password = document.getElementById("regPassword").value;

    fetch("/api/auth/register", {   // <-- note /api/auth/
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    })
    .then(async res => {
        const data = await res.json(); // parse JSON response
        document.getElementById("regMsg").textContent = data.message;
    })
    .catch(err => {
        document.getElementById("regMsg").textContent = "Registration failed";
        console.error(err);
    });
}

// Fetch Orders (protected API)
function fetchOrders() {
    const token = localStorage.getItem("jwt");
    if (!token) {
        window.location.href = "/login.html";
        return;
    }

    fetch("/orders", {
        headers: { "Authorization": "Bearer " + token }
    })
    .then(res => res.json())
    .then(data => {
        const list = document.getElementById("ordersList");
        list.innerHTML = "";
        data.forEach(order => {
            const li = document.createElement("li");
            li.textContent = `Order ${order.id}: ${order.product} - $${order.price}`;
            list.appendChild(li);
        });
    })
    .catch(err => console.error(err));
}

// Logout
function logout() {
    localStorage.removeItem("jwt");
    window.location.href = "/login.html";
}
