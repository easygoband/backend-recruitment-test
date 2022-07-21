const { addSurvival } = require("./addSurvival/addSurvival");
const { getAllSurvivals } = require("./getSurvival/getAllSurvivals");
const { getSurvivalById } = require("./getSurvivalById/getSurvivalById");
const { removeSurvival } = require("./removeSurvival/removeSurvival");
const { updateSurvival } = require("./updateSurvival/updateSurvival");
const { reportSurvival } = require("./reportSurvival/reportSurvival");

module.exports = {
  addSurvival,
  updateSurvival,
  removeSurvival,
  getAllSurvivals,
  getSurvivalById,
  reportSurvival,
};
