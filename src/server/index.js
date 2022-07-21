const express = require("express");
const survival = require("../routes/survival");
const item = require("../routes/item");
const inventary = require("../routes/inventary");
const trade = require("../routes/trade");
const report = require("../routes/report");
const app = express();

app.use(express.json());

app.use("/api/v1", survival);
app.use("/api/v1", item);
app.use("/api/v1", inventary);
app.use("/api/v1", trade);
app.use("/api/v1", report);

module.exports = app;
