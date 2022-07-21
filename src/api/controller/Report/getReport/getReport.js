const { getReportController } = require("./controller/getReportController");

const getReport = async (req, res) => {
  try {
    const { success, message, status, data } = await getReportController();

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

module.exports = { getReport };
