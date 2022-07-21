const { inventary } = require("../../../../db/models");

const getAllInventaryInDB = async () => {
  return await inventary.findAll();
};

module.exports = getAllInventaryInDB;
