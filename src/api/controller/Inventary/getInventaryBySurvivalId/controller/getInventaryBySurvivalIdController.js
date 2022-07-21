const getInventaryBySurvivalId = require("../../../../services/Inventary/getInventaryBySurvivalId/getInventaryBySurvivalIdInDataBase");

const getInventaryBySurvivalIdController = async (survivalId) => {
  const inventary = await getInventaryBySurvivalId(survivalId);

  if (inventary.Survival.isInfected) {
    return {
      status: 403,
      success: false,
      message: "Survival infected",
    };
  }

  return {
    status: 200,
    success: true,
    message: "The inventary is found",
    data: inventary,
  };
};

module.exports = { getInventaryBySurvivalIdController };
