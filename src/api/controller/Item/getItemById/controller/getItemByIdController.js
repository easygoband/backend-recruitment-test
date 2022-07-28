const getItemByIdInDB = require("../../../../services/Item/getItemById/getItemByItemOfDataBase");

const getItemByIdController = async (id) => {
  const searchItemById = await getItemByIdInDB(id);

  if (searchItemById === null) {
    return {
      status: 404,
      success: false,
      message: "Item not found",
    };
  }

  return {
    status: 200,
    success: true,
    message: "Item found",
    data: searchItemById,
  };
};

module.exports = {
  getItemByIdController,
};
