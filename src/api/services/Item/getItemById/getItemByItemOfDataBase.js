const { item } = require("../../../../db/models");

const getItemById = async (id) => {
  return await item.findByPk(id);
};

module.exports = getItemById;
