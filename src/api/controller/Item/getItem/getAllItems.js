const { getAllItemsController } = require("./controller/getAllItemController");

const getAllItems = async (req, res) => {
  try {
    const { status, message, success, data } = await getAllItemsController();

    return res.status(status).json({
      success,
      message,
      data,
    });
  } catch (err) {
    return res.status(500).json({
      success: false,
      message: err.message,
    });
  }
};

module.exports = { getAllItems };
