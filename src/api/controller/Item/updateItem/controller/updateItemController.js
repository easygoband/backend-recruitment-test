const updateItemInDB = require("../../../../services/Item/updateItem/updateItemOfDataBase");

const updateItemController = async ({ id, updateData }) => {
  return await updateItemInDB({ id, updateData });
};

module.exports = { updateItemController };
