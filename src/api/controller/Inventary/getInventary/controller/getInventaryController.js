const getAllInventaryInDB = require("../../../../services/Inventary/getInventary/getAllInventaryInDataBase");

const getAllInventaryController = async () => {
  return await getAllInventaryInDB();
};

module.exports = { getAllInventaryController };
