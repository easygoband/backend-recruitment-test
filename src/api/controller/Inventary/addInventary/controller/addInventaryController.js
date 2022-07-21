const addInventaryInDB = require("../../../../services/Inventary/addInventary/addInventaryInDataBase");

const addInventaryController = async (suvivalId) => {
  return await addInventaryInDB(suvivalId);
};

module.exports = { addInventaryController };
