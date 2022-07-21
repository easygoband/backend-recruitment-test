const { getTotalPoint } = require("./getTotalPoints");
const { subtractResourcesFromInventary } = require("./subtractResources");
const { addResourceFromInventary } = require("./addResources");
const { restoreResource } = require("./restoreResources");

module.exports = {
  getTotalPoint,
  restoreResource,
  addResourceFromInventary,
  subtractResourcesFromInventary,
};
