const { inventary } = require("../../../../db/models");

const createInventaryInDB = async (survivalId) => {
  return await inventary.create({ survivalId });
};

module.exports = createInventaryInDB;
