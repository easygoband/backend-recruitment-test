const { updateItemController } = require("./controller/updateItemController");

const updateItem = async (req, res) => {
  try {
    const { id } = req.params;
    const params = req.body;

    const response = await updateItemController({ id, updateData: params });

    return res.status(200).json({
      success: true,
      message: "Item updated",
      data: response,
    });
  } catch (err) {
    return res.status(500).json({
      success: true,
      message: err.message,
    });
  }
};

module.exports = { updateItem };
