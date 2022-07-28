const {
  createSurvivalController,
} = require("./controller/addSurvivalController");
const { validateInputs } = require("../../../../utils/validateInputs");
const {
  actionData,
  messageData,
} = require("../../../services/Survival/ValidationRequestSurvival/schemaSurvival");

/**
 *
 * @param { name, age, gender, lastLocation, resources } req.body
 * @returns { status, message, data }
 */

const addSurvival = async (req, res) => {
  try {
    const { name, age, gender, lastLocation, resources } = req.body;
    const isValidateJson = await validateInputs(
      { name, age, gender, lastLocation, resources },
      actionData,
      messageData
    );
    if (!isValidateJson.matched) {
      return res.status(422).json({
        status: false,
        message: isValidateJson.errors,
      });
    }

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
