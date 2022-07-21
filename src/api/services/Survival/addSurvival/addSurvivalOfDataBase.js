const { survival } = require("../../../../db/models");

const createSurvivalInDB = async ({ name, age, gender, lastLocation }) => {
  const addSurvival = await survival.create({
    name,
    age,
    gender,
    lastLocation,
  });

  return addSurvival;
};

module.exports = createSurvivalInDB;
