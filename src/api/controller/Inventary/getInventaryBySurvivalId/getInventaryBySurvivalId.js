const {
  getInventaryBySurvivalIdController,
} = require("./controller/getInventaryBySurvivalIdController");

const getInventaryBySurvivalId = async (req, res) => {
  try {
    const { id } = req.params;

    const { message, status, success, data } =
      await getInventaryBySurvivalIdController(id);

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

module.exports = { getInventaryBySurvivalId };
