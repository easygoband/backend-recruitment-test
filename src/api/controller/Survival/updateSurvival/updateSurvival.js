const {
  updateSurvivalController,
} = require("./controller/updateSurvivalController");

const updateSurvival = async (req, res) => {
  try {
    const { id } = req.params;
    const params = req.body;

    const response = await updateSurvivalController(id, params);

    return res.status(200).json({
      success: true,
      message: "Survival updated",
      data: response,
    });
  } catch (err) {
    return res.status(500).json({
      success: false,
      message: err.message,
    });
  }
};

module.exports = { updateSurvival };
