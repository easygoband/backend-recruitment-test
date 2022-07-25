const getAllInventary = require("../../../../services/Inventary/getInventary/getAllInventaryInDataBase");
const getAllSurvivals = require("../../../../services/Survival/getSurvival/getAllSurvivalOfDataBase");
const {
  getTotalSurvivalNotInfectedAndInfected,
  getAverageForResources,
  getAveragePointsLostForSurvival,
} = require("../../../../helpers/Report");

const getReportController = async () => {
  const survivals = await getAllSurvivals();
  const inventaries = await getAllInventary();

  const { survivals_infected, survivals_not_infected } =
    getTotalSurvivalNotInfectedAndInfected({
      countAllSurvivals: survivals.count,
      survivals: survivals.rows,
    });

  const {
    Average_Food_Survival,
    Average_Medicine_Survival,
    Average_Munition_Survival,
    Average_Water_Survival,
  } = getAverageForResources(inventaries.rows, survivals.count);

  const totalSuvivalInfected = survivals.rows.reduce((ac, survival) => {
    if (survival.isInfected) ac++;
    return ac;
  }, 0);

  const pointsLostForSurvivalInfected = await getAveragePointsLostForSurvival({
    survivalAndInventary: inventaries.rows,
    totalSurvivals: survivals.count,
    totalSurvivalInfected: totalSuvivalInfected,
  });

  return {
    status: 200,
    success: true,
    message: "Todo bien",
    data: {
      survivals_infected,
      survivals_not_infected,
      averageToResources: {
        Average_Food_Survival,
        Average_Medicine_Survival,
        Average_Munition_Survival,
        Average_Water_Survival,
      },
      pointsLostForSurvivalInfected,
    },
  };
};

module.exports = { getReportController };
