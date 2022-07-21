const { survival } = require("../../../../db/models");

const getAllSurvivalInDB = async () => {
  const searchSurvivals = await survival.findAll();

  return searchSurvivals;
};

module.exports = getAllSurvivalInDB;
