const getItemByIdInDB = require("../../../services/Item/getItemById/getItemByItemOfDataBase");

const getItemById = async (req, res) => {
  try {
    const { id } = req.params;

    const response = await getItemByIdInDB(id);

    return res.status(200).json({
      success: true,
      message: "Item found",
      data: response,
    });
  } catch (err) {
    return res.status(500).json({
      success: true,
      message: err.message,
    });
  }
};

module.exports = { getItemById };
