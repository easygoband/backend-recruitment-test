const { removeItemController } = require("./controller/removeItemController");

const removeItem = async (req, res) => {
  try {
    const { id } = req.params;

    const response = await removeItemController(id);

    return res.status(200).json({
      success: true,
      message: "Item deleted",
      data: response,
    });
  } catch (err) {
    return res.status(500).json({
      success: true,
      message: err.message,
    });
  }
};

module.exports = { removeItem };
