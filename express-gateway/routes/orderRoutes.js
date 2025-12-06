const router = require("express").Router();
const axiosClient = require("../config/axiosClient");
const jwtMiddleware = require("../middleware/jwtMiddleware");

router.get("/", jwtMiddleware, async (req, res) => {
    try {
        const token = req.cookies.token;

        const apiResponse = await axiosClient.get("/api/orders", {
            headers: { Authorization: "Bearer " + token }
        });

        res.json(apiResponse.data);
    } catch (err) {
        res.status(500).json({ message: "Error fetching orders" });
    }
});

module.exports = router;
