const getTotalForEachResources = (SurvivalAndInventary) => {
  let totalMedicationAmount = 0;
  let totalWaterAmount = 0;
  let totalFoodAmount = 0;
  let totalAmmunitionAmount = 0;

  SurvivalAndInventary.forEach((SurvivalItems) => {
    SurvivalItems.inventaryHasItems.forEach((resources) => {
      if (resources.item.item === "Medication") {
        totalMedicationAmount = totalMedicationAmount + resources.amount;
      }
      if (resources.item.item === "Food") {
        totalFoodAmount = totalFoodAmount + resources.amount;
      }
      if (resources.item.item === "Water") {
        totalWaterAmount = totalWaterAmount + resources.amount;
      }
      if (resources.item.item === "Ammunition") {
        totalAmmunitionAmount = totalAmmunitionAmount + resources.amount;
      }
    });
  });

  return {
    totalMedicationAmount,
    totalWaterAmount,
    totalFoodAmount,
    totalAmmunitionAmount,
  };
};

module.exports = { getTotalForEachResources };
