const { addInventary } = require("./addInventary/addInventary");
const { getInventary } = require("./getInventary/getInventary");
const { removeInventary } = require("./removeInventary/removeInventary");
const { updateInventary } = require("./updateInventary/updateInventary");
const { getInventaryById } = require("./getInventaryById/getInventaryById");
const {
  getInventaryBySurvivalId,
} = require("./getInventaryBySurvivalId/getInventaryBySurvivalId");

module.exports = {
  addInventary,
  getInventary,
  removeInventary,
  updateInventary,
  getInventaryById,
  getInventaryBySurvivalId,
};
