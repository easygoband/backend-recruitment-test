const { survival } = require("../../../../db/models");

const removeSurvival = async (id) => {
  const removeLogicSurvival = await survival.destroy({ where: { id } });

  return removeLogicSurvival;
};

module.exports = removeSurvival;
