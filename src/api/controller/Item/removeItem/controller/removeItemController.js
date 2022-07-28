const removeItemInDB = require("../../../../services/Item/removeItem/removeItemOfDataBase");

const removeItemController = async (id) => {
  return await removeItemInDB(id);
};

module.exports = { removeItemController };
