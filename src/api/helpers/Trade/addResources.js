const getInventaryBySurvivalId = require("../../services/Inventary/getInventaryBySurvivalId/getInventaryBySurvivalIdInDataBase");
const addInventaryHasItem = require("../../services/InventaryHasItems/addInventaryHasItem/addInventaryHasItemInDataBase");
const updateInventaryHasItem = require("../../services/InventaryHasItems/updateInventaryHasItem/updateInventaryHasItemInDataBase");

const addResourceFromInventary = async ({
  survivalId,
  itemsToRecived,
  inventarySurvival,
}) => {
  let addItemOnMyInventary = [];
  let itemToUpdateInMyInventary = [];

  for (const item of itemsToRecived) {
    const existItemOnInventary = inventarySurvival.find((inventaryItem) => {
      return item.itemId === inventaryItem.itemId;
    });
    if (existItemOnInventary === undefined) {
      addItemOnMyInventary.push({
        itemId: item.itemId,
        amount: item.amount,
        inventaryId: inventarySurvival[0].inventaryId,
      });
    }
    if (existItemOnInventary) {
      const newAmount = item.amount + existItemOnInventary.amount;
      itemToUpdateInMyInventary.push({
        amount: newAmount,
        id: existItemOnInventary.id,
      });
    }
  }

  if (addItemOnMyInventary.length) {
    await addInventaryHasItem(addItemOnMyInventary);
  }
  if (itemToUpdateInMyInventary.length) {
    await updateInventaryHasItem(itemToUpdateInMyInventary);
  }

  const { inventaryHasItems } = await getInventaryBySurvivalId(survivalId);

  return inventaryHasItems;
};

module.exports = { addResourceFromInventary };
