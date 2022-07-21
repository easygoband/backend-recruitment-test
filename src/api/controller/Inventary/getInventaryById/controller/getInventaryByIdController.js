const getInventaryByIdInDB = require("../../../../services/Inventary/getInventaryById/getInventaryByIdInDataBase");

const getInventaryByIdController = async (id) => {
  const inventary = await getInventaryByIdInDB(id);

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

module.exports = { getInventaryByIdController };
