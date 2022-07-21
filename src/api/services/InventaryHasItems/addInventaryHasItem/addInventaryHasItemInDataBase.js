const { inventaryHasItems } = require("../../../../db/models");

const createInventaryHasItem = async (items) => {
  return await inventaryHasItems.bulkCreate(items);
};

module.exports = createInventaryHasItem;
