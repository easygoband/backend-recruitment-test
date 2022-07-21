const updateInventaryHasItem = require("../../services/InventaryHasItems/updateInventaryHasItem/updateInventaryHasItemInDataBase");
const getInventaryBySurvivalId = require("../../services/Inventary/getInventaryBySurvivalId/getInventaryBySurvivalIdInDataBase");

const subtractResourcesFromInventary = async ({
  itemsOnInventary,
  itemsToSubstract,
  survivalId,
}) => {
  let response = {
    success: true,
    inventaryWithSubstracResources: [],
  };

  let items = [];

  for (const inventaryItem of itemsOnInventary) {
    for (const substractItem of itemsToSubstract) {
      if (inventaryItem.itemId === substractItem.itemId) {
        const newAmount = inventaryItem.amount - substractItem.amount;

        if (newAmount < 0) {
          return (response = {
            status: 400,
            success: false,
            message: "Does not have sufficient resources in its inventory",
          });
        }

        items.push({ amount: newAmount, id: inventaryItem.id });
      }
    }
  }

  await updateInventaryHasItem(items);

  const { inventaryHasItems } = await getInventaryBySurvivalId(survivalId);

  response.inventaryWithSubstracResources = inventaryHasItems;

  return response;
};

module.exports = { subtractResourcesFromInventary };
