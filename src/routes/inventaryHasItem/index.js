const express = require("express");
const router = express.Router();

router.get("/inventaryItem");
router.get("/inventaryItem/:id");
router.post("/inventaryItem");
router.put("/inventaryItem/:id");
router.delete("/inventaryItem/:id");

module.exports = router;
