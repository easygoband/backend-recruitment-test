const {
  getSurvivalByIdController,
} = require("./controller/getSurvivalByIdController");

const getSurvivalById = async (req, res) => {
  try {
    const { id } = req.params;
    const { status, message, success, data } = await getSurvivalByIdController(
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

module.exports = { getSurvivalById };
