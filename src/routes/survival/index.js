const express = require("express");
const router = express.Router();

const {
  addSurvival,
  getAllSurvivals,
  getSurvivalById,
  removeSurvival,
  updateSurvival,
  reportSurvival,
} = require("../../api/controller/Survival");

router.get("/survival", getAllSurvivals);
router.get("/survival/:id", getSurvivalById);
router.post("/survival", addSurvival);
router.put("/survival/:id", updateSurvival);
router.delete("/survival/:id", removeSurvival);
router.post("/survival/report-infected", reportSurvival);

module.exports = router;
