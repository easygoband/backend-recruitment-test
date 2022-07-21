const { survival } = require("../../../../db/models");

const updateSurvival = async (id, updateData) => {
  return await survival.update(updateData, { where: { id } });
};

module.exports = updateSurvival;
