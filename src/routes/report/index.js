const express = require("express");
const router = express.Router();

const { getReport } = require("../../api/controller/Report");

router.get("/report", getReport);

module.exports = router;
