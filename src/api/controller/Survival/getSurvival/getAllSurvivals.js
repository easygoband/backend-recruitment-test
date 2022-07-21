const {
  getAllSurvivalController,
} = require("./controller/getAllSurvivalController");

const getAllSurvivals = async (req, res) => {
  try {
    const response = await getAllSurvivalController();

    return res.status(200).json({
      success: true,
      message: "survivals found",
      data: response,
    });
  } catch (err) {
    return res.status(500).json({
      success: false,
      message: err.message,
    });
  }
};

module.exports = { getAllSurvivals };
