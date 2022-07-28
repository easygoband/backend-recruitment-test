const addItemInDatabase = require("../../../../services/Item/addItem/addItemInDataBase");

const createItemController = async ({ item, point }) => {
  return await addItemInDatabase({ product: item, point });
};

module.exports = { createItemController };
