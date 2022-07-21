const { item } = require("../../../../db/models");

const getAllItem = async () => {
  return await item.findAll();
};

module.exports = getAllItem;
