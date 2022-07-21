const {
  getInventaryByIdController,
} = require("./controller/getInventaryByIdController");

const getInventaryById = async (req, res) => {
  try {
    const { id } = req.params;

    const { message, status, success, data } = await getInventaryByIdController(
      id
    );

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

module.exports = { getInventaryById };
