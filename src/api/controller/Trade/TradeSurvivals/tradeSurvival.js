const {
  tradeSurvivalController,
} = require("./controller/tradeSurvivalController");

const tradeSurvival = async (req, res) => {
  try {
    const { itemsToSend, itemsToRecived } = req.body;

    const { message, status, success, data } = await tradeSurvivalController({
      itemsToSend,
      itemsToRecived,
    });

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

module.exports = { tradeSurvival };
