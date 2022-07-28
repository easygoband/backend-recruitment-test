const { getItemByIdController } = require("./controller/getItemByIdController");

const getItemById = async (req, res) => {
  try {
    const { id } = req.params;

    const { message, status, success, data } = await getItemByIdController(id);

    return res.status(status).json({
      success,
      message,
      data,
    });
  } catch (err) {
    return res.status(500).json({
      success: true,
      message: err.message,
    });
  }
};

module.exports = { getItemById };
