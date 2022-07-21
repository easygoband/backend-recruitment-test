const updateInventaryHasItem = require("../../services/InventaryHasItems/updateInventaryHasItem/updateInventaryHasItemInDataBase");

const restoreResource = async (itemsToRestore) => {
  const formatToUpdate = itemsToRestore.map((item) => {
    return { amount: item.amount, id: item.id };
  });

  return await updateInventaryHasItem(formatToUpdate);
};

module.exports = { restoreResource };
