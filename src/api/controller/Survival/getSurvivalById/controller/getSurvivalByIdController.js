const searchSurvivalById = require("../../../../services/Survival/getSurvivalById/getSurvivalByIdOfDataBase");

const getSurvivalByIdController = async (id) => {
  const survival = await searchSurvivalById(id);

  if (survival === null) {
    return {
      status: "404",
      success: false,
      message: "Survival not exist",
    };
  }

  return {
    status: "200",
    success: true,
    message: "Survival found",
    data: survival,
  };
};

module.exports = { getSurvivalByIdController };
