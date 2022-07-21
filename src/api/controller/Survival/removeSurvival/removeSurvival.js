const {
  removeSurvivalController,
} = require("./controller/removeSurvivalController");

const removeSurvival = async (req, res) => {
  try {
    const { id } = req.params;

    const response = await removeSurvivalController(id);

    return res.status(200).json({
      success: true,
      message: "remove survival",
      data: response,
    });
  } catch (err) {
    return res.status(500).json({
      success: false,
      message: err.message,
    });
  }
};

module.exports = { removeSurvival };
