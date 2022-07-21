const express = require("express");
const router = express.Router();

const { tradeSurvival } = require("../../api/controller/Trade");

router.post("/trade-survivals", tradeSurvival);

module.exports = router;
