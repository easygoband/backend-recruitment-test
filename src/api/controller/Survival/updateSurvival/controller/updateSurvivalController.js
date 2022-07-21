const updateSurvivalToDataBase = require("../../../../services/Survival/updateSurvival/updateSurvivalOfDataBase");

const updateSurvivalController = async (id, params) => {
  return await updateSurvivalToDataBase(id, params);
};

module.exports = { updateSurvivalController };
