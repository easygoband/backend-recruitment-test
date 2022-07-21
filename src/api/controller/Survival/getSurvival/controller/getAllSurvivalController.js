const getAllSurvival = require("../../../../services/Survival/getSurvival/getAllSurvivalOfDataBase");

const getAllSurvivalController = async () => {
  return await getAllSurvival();
};

module.exports = { getAllSurvivalController };
