const getSurvivalById = require("../../../../services/Survival/getSurvivalById/getSurvivalByIdOfDataBase");
const updateSurvivalInDB = require("../../../../services/Survival/updateSurvival/updateSurvivalOfDataBase");

const reportSurvivalController = async (
  survivalIdToReport,
  survivalIdReporting
) => {
  let params = {};
  let response = {
    status: "200",
    success: true,
    message: "",
  };
  const searchSurvivalToReport = await getSurvivalById(survivalIdToReport);
  const searchSurvivalReporting = await getSurvivalById(survivalIdReporting);

  if (searchSurvivalToReport === null || searchSurvivalReporting === null) {
    return (response = {
      status: "404",
      success: false,
      message: "Survival not exist",
    });
  }

  if (searchSurvivalToReport.reports < 3) {
    params.reports = searchSurvivalToReport.reports + 1;
    response.message = "Thanks for report a survival";
  }
  if (searchSurvivalToReport.reports === 3) {
    params.isInfected = true;
    response.message = "New survival infected, pls be careful";
  }

  await updateSurvivalInDB(survivalIdToReport, params);

  return response;
};

module.exports = { reportSurvivalController };
