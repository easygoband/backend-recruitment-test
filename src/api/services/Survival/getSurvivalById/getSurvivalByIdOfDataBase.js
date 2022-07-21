const { survival } = require("../../../../db/models");

const getSurvivalById = async (id) => {
  const searchSurvivalById = await survival.findByPk(id);

  return searchSurvivalById;
};

module.exports = getSurvivalById;
