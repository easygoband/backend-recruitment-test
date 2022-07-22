const { getTotalPoint } = require("./getTotalPoints");
const { subtractResourcesFromInventary } = require("./subtractResources");
const { addResourceFromInventary } = require("./addResources");
const {
  updateInventaryAndReturnInventaryUpdate,
} = require("./updateInventaryAndReturnInventary");

module.exports = {
  getTotalPoint,
  addResourceFromInventary,
  subtractResourcesFromInventary,
  updateInventaryAndReturnInventaryUpdate,
};
