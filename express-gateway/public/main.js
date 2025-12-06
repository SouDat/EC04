// ===============================
// CONFIG - Change if backend URL changes
// ===============================
const BASE_URL = "http://localhost:8080"; // Spring Boot backend


// ===============================
// LOGIN
// ===============================
async function login() {
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    if (!username || !password) {
        alert("Please enter both username & password.");
        return;
    }

    const response = await fetch(`${BASE_URL}/api/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    });

    const data = await response.json();

    if (!response.ok) {
        alert(data.message || "Invalid credentials");
        return;
    }

    localStorage.setItem("token", data.token);
    window.location.href = "/orders.html";
}



// ===============================
// REGISTER
// ===============================
async function registerUser() {
    const username = document.getElementById("regUsername").value.trim();
    const password = document.getElementById("regPassword").value.trim();

    if (!username || !password) {
        alert("Please enter username and password");
        return;
    }

    const response = await fetch(`${BASE_URL}/api/auth/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    });

    const data = await response.json();

    if (!response.ok) {
        alert(data.message || "Registration failed");
        return;
    }

    alert("Registration successful! Please login.");
    window.location.href = "/login.html";
}



// ===============================
// LOAD ORDERS AFTER AUTH
// ===============================
async function loadOrders() {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("You must login first.");
        window.location.href = "/login.html";
        return;
    }

    const response = await fetch(`${BASE_URL}/api/orders`, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });

    if (response.status === 401) {
        alert("Session expired. Login again.");
        logout();
        return;
    }

    const data = await response.json();

    const container = document.getElementById("ordersContainer");

    if (!data || data.length === 0) {
        container.innerHTML = "<p>No orders found.</p>";
        return;
    }

    let html = "<h3>Your Orders:</h3>";
    html += "<ul>";

    data.forEach(o => {
        html += `<li>
            <b>Order ID:</b> ${o.id}<br>
            <b>Item:</b> ${o.item}<br>
            <b>Quantity:</b> ${o.quantity}
        </li><br>`;
    });

    html += "</ul>";

    container.innerHTML = html;
}



// ===============================
// LOGOUT
// ===============================
function logout() {
    localStorage.removeItem("token");
    window.location.href = "/login.html";
}
