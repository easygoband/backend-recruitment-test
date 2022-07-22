const { survival } = require("../../../../db/models");

const getAllSurvivalInDB = async () => {
  const searchSurvivals = await survival.findAndCountAll();

  return searchSurvivals;
};

module.exports = getAllSurvivalInDB;
