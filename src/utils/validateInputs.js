const { Validator } = require("node-input-validator");
const niv = require("node-input-validator");

const validateInputs = async (object, squema, customMessage) => {
  const v = new Validator(object, squema);

  niv.addCustomMessages(customMessage);

  const matched = await v.check();
  return { matched, errors: v.errors };
};

module.exports = {
  validateInputs,
};
