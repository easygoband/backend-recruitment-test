const removeLogicSurvival = require("../../../../services/Survival/removeSurvival/removeSurvivalOfDataBase");

const removeSurvivalController = async (id) => {
  return await removeLogicSurvival(id);
};

module.exports = { removeSurvivalController };
