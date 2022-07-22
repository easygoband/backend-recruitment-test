const getAllInventary = require("../../../../services/Inventary/getInventary/getAllInventaryInDataBase");
const getAllSurvivals = require("../../../../services/Survival/getSurvival/getAllSurvivalOfDataBase");
const { generatePercentage } = require("../../../../helpers/Report");

const getReportController = async () => {
  const survivals = await getAllSurvivals();

  const inventaries = await getAllInventary();

  const { survivals_infected, survivals_not_infected } = generatePercentage({
    countAllSurvivals: survivals.count,
    survivals: survivals.rows,
  });

  return {
    status: 200,
    success: true,
    message: "Todo bien",
    data: {
      survivals_infected,
      survivals_not_infected,
    },
  };
};

module.exports = { getReportController };
