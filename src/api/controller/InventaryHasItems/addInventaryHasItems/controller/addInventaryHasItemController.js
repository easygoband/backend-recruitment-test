const addInventaryHasItemInDB = require("../../../../services/InventaryHasItems/addInventaryHasItem/addInventaryHasItemInDataBase");

const addInventaryHasItemController = async (items) => {
  return await addInventaryHasItemInDB(items);
};

module.exports = { addInventaryHasItemController };
