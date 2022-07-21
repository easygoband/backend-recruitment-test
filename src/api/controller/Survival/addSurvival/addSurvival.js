const {
  createSurvivalController,
} = require("./controller/addSurvivalController");

/**
 *
 * @param { name, age, gender, lastLocation, resources } req.body
 * @returns { status, message, data }
 */

const addSurvival = async (req, res) => {
  try {
    const { name, age, gender, lastLocation, resources } = req.body;
    const response = await createSurvivalController({
      name,
      age,
      gender,
      lastLocation,
      resources,
    });

    return res.status(201).json({
      status: true,
      message: "survival registered",
      data: response,
    });
  } catch (err) {
    return res.status(500).json({
      status: false,
      message: err.message,
    });
  }
};

module.exports = { addSurvival };
