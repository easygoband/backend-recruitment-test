const {
  averageResourceBySurvivals,
  getTotalForEachResources,
} = require("../../../utils");

const getAverageForResources = (SurvivalAndInventary, totalSurvivals) => {
  const {
    totalAmmunitionAmount,
    totalFoodAmount,
    totalMedicationAmount,
    totalWaterAmount,
  } = getTotalForEachResources(SurvivalAndInventary);

  const Average_Medicine_Survival = averageResourceBySurvivals(
    totalMedicationAmount,
    totalSurvivals
  );
  const Average_Food_Survival = averageResourceBySurvivals(
    totalFoodAmount,
    totalSurvivals
  );
  const Average_Water_Survival = averageResourceBySurvivals(
    totalWaterAmount,
    totalSurvivals
  );
  const Average_Munition_Survival = averageResourceBySurvivals(
    totalAmmunitionAmount,
    totalSurvivals
  );

  return {
    Average_Medicine_Survival: Average_Medicine_Survival.toFixed(2),
    Average_Food_Survival: Average_Food_Survival.toFixed(2),
    Average_Water_Survival: Average_Water_Survival.toFixed(2),
    Average_Munition_Survival: Average_Munition_Survival.toFixed(2),
  };
};

module.exports = { getAverageForResources };
