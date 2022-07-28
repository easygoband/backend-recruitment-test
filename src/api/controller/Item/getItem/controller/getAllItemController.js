const getAllItemsInDB = require("../../../../services/Item/getItem/getItemOfDataBase");

const getAllItemsController = async () => {
  const getAllItems = await getAllItemsInDB();

  if (getAllItems.length === 0) {
    return {
      status: 404,
      success: false,
      message: "Does not have enough items",
    };
  }

  return {
    status: 200,
    success: true,
    message: "items found",
    data: getAllItems,
  };
};

module.exports = {
  getAllItemsController,
};
