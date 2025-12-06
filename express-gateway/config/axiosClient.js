const axios = require("axios");

module.exports = axios.create({
    baseURL: "http://localhost:8080", // your Spring Boot port
    timeout: 5000,
    headers: { "Content-Type": "application/json" }
});
