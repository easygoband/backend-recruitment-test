const getAllItemsInDB = require("../../../services/Item/getItem/getItemOfDataBase");

const getAllItems = async (req, res) => {
  try {
    const response = await getAllItemsInDB();

    return res.status(200).json({
      success: true,
      message: "Items found",
      data: response,
    });
  } catch (err) {
    return res.status(500).json({
      success: false,
      message: err.message,
    });
  }
};

module.exports = { getAllItems };
