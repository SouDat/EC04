const router = require("express").Router();
const axiosClient = require("../config/axiosClient");
const jwt = require("jsonwebtoken");

router.post("/login", async (req, res) => {
    try {
        const springResponse = await axiosClient.post("/api/auth/login", req.body);

        const token = springResponse.data.token;

        // store token
        res.cookie("token", token, {
            httpOnly: true,
            secure: false,
            sameSite: "lax"
        });

        res.json({ message: "Login successful" });

    } catch (err) {
        res.status(401).json({ message: "Invalid credentials" });
    }
});

router.post("/logout", (req, res) => {
    res.clearCookie("token");
    res.json({ message: "Logged out" });
});

module.exports = router;
