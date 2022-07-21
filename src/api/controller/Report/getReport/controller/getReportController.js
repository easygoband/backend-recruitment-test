const getInventaryHasItemInDB = require("../../../../services/InventaryHasItems/getAllInventaryHasItem/getAllInventaryHasItemInDataBase");

const getReportController = async () => {
  const inventaries = await getInventaryHasItemInDB();

  return {
    status: 200,
    success: true,
    message: "Todo bien",
    data: inventaries,
  };
};

module.exports = { getReportController };
