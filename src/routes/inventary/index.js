const express = require("express");
const router = express.Router();

const {
  addInventary,
  getInventary,
  removeInventary,
  updateInventary,
  getInventaryById,
  getInventaryBySurvivalId,
} = require("../../api/controller/Inventary");

router.get("/inventary", getInventary);
router.get("/inventary/:id", getInventaryById);
router.post("/inventary", addInventary);
router.get("/survival/:id/inventary", getInventaryBySurvivalId);
router.put("/inventary/:id", updateInventary);
router.delete("/inventary/:id", removeInventary);

module.exports = router;
