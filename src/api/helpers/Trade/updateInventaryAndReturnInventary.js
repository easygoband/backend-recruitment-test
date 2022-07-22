const updateInventarySurvival = require("../../services/InventaryHasItems/updateInventaryHasItem/updateInventaryHasItemInDataBase");
const getInventarySurvival = require("../../services/Inventary/getInventaryBySurvivalId/getInventaryBySurvivalIdInDataBase");

const updateInventaryAndReturnInventaryUpdate = async ({
  itemsToUpdate,
  survivalId,
}) => {
  await updateInventarySurvival(itemsToUpdate);

  const { inventaryHasItems } = await getInventarySurvival(survivalId);

  return inventaryHasItems;
};

module.exports = { updateInventaryAndReturnInventaryUpdate };
