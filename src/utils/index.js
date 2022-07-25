const { getTotalForEachResources } = require("./getTotalForEachResource");

const generatePercentage = (totalItems, foundItems) => {
  const percentage = (foundItems / totalItems) * 100;
  return `${percentage.toFixed(2)} %`;
};

const averageResourceBySurvivals = (totalResources, totalSurvival) => {
  return totalResources / totalSurvival;
};

module.exports = {
  generatePercentage,
  averageResourceBySurvivals,
  getTotalForEachResources,
};
