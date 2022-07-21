const {
  getAllInventaryController,
} = require("./controller/getInventaryController");

const getInventary = async (req, res) => {
  try {
    const response = await getAllInventaryController();

    return res.status(200).json({
      success: true,
      message: "Inventaries found",
      data: response,
    });
  } catch (err) {
    return res.status(500).json({
      success: false,
      message: err.message,
    });
  }
};

module.exports = { getInventary };
