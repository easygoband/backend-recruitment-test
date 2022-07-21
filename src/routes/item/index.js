const express = require("express");
const router = express.Router();

const {
  addItem,
  removeItem,
  updateItem,
  getAllItems,
  getItemById,
} = require("../../api/controller/Item");

router.get("/item", getAllItems);
router.get("/item/:id", getItemById);
router.post("/item", addItem);
router.put("/item/:id", updateItem);
router.delete("/item/:id", removeItem);

module.exports = router;
