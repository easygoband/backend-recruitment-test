const { createItemController } = require("./controller/addItemController");

const { validateInputs } = require("../../../../utils/validateInputs");
const {
  actionData,
  messageData,
} = require("../../../services/Item/validationRequestItem/schemaItem");

const addItem = async (req, res) => {
  try {
    const { item, point } = req.body;

    const isValidateJson = await validateInputs(
      { item, point },
      actionData,
      messageData
    );
    if (!isValidateJson.matched) {
      return res.status(422).json({
        status: false,
        message: isValidateJson.errors,
      });
    }

    const response = await createItemController({ product: item, point });

    return res.status(201).json({
      success: true,
      message: "Item added",
      data: response,
    });
  } catch (err) {
    return res.status(500).json({
      success: true,
      message: err.message,
    });
  }
};

module.exports = { addItem };
