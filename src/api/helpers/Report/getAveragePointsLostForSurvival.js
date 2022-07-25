const { getTotalForEachResources } = require("../../../utils");
const getAllItems = require("../../services/Item/getItem/getItemOfDataBase");

const getAveragePointsLostForSurvival = async ({
  survivalAndInventary,
  totalSurvivals,
  totalSurvivalInfected,
}) => {
  let totalFoodWithPoints = 0;
  let totalWaterWithPoints = 0;
  let totalMedicationWithPoints = 0;
  let totalAmmunitionWithPoints = 0;

  const resources = await getAllItems();

  const {
    totalAmmunitionAmount,
    totalFoodAmount,
    totalMedicationAmount,
    totalWaterAmount,
  } = getTotalForEachResources(survivalAndInventary);

  resources.forEach((resource) => {
    if (resource.item === "Medication") {
      totalMedicationWithPoints = totalMedicationAmount * resource.point;
    }
    if (resource.item === "Food") {
      totalFoodWithPoints = totalFoodAmount * resource.point;
    }
    if (resource.item === "Water") {
      totalWaterWithPoints = totalWaterAmount * resource.point;
    }
    if (resource.item === "Ammunition") {
      totalAmmunitionWithPoints = totalAmmunitionAmount * resource.point;
    }
  });

  const totalPoints =
    totalFoodWithPoints +
    totalMedicationWithPoints +
    totalWaterWithPoints +
    totalAmmunitionWithPoints;

  const averagePointsForSurvivalInfected =
    (totalPoints * totalSurvivalInfected) / totalSurvivals;

  return averagePointsForSurvivalInfected.toFixed(2);
};

module.exports = { getAveragePointsLostForSurvival };
