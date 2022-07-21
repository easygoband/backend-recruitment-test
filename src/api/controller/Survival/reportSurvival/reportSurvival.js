const {
  reportSurvivalController,
} = require("./controller/reportSurvivalController");

const reportSurvival = async (req, res) => {
  try {
    const { survivalIdToReport, survivalIdReporting } = req.body;
    const { message, status, success } = await reportSurvivalController(
      survivalIdToReport,
      survivalIdReporting
    );

    return res.status(status).json({
      success,
      message,
    });
  } catch (err) {
    return res.status(500).json({
      success: false,
      message: err.message,
    });
  }
};

module.exports = { reportSurvival };
