const getInventaryBySurvivalId = require("../../../../services/Inventary/getInventaryBySurvivalId/getInventaryBySurvivalIdInDataBase");
const {
  getTotalPoint,
  subtractResourcesFromInventary,
  addResourceFromInventary,
} = require("../../../../helpers/Trade");

const tradeSurvivalController = async ({ itemsToSend, itemsToRecived }) => {
  let response = {
    status: 200,
    success: true,
    message: "Trade complete",
  };

  const [firstSurvival, secondSurvival] = await Promise.all([
    getInventaryBySurvivalId(itemsToSend.survivalId),
    getInventaryBySurvivalId(itemsToRecived.survivalId),
  ]);

  if (firstSurvival.Survival.isInfected || secondSurvival.Survival.isInfected) {
    return (response = {
      status: 403,
      success: false,
      message: "A survivor is infected",
    });
  }

  const pointsFirstSurvival = getTotalPoint({
    items: itemsToSend.items,
    resources: firstSurvival.inventaryHasItems,
  });

  const pointsSecondSurvival = getTotalPoint({
    items: itemsToRecived.items,
    resources: secondSurvival.inventaryHasItems,
  });

  if (pointsFirstSurvival !== pointsSecondSurvival) {
    return (response = {
      status: 412,
      success: false,
      message: "The points not equals",
    });
  }

  const oldInventaryFirstSurvival = await subtractResourcesFromInventary({
    itemsOnInventary: firstSurvival.inventaryHasItems,
    itemsToSubstract: itemsToSend.items,
    survivalId: firstSurvival.Survival.id,
  });

  const oldInventarySecondSurvival = await subtractResourcesFromInventary({
    itemsOnInventary: secondSurvival.inventaryHasItems,
    itemsToSubstract: itemsToRecived.items,
    survivalId: secondSurvival.Survival.id,
  });

  if (!oldInventaryFirstSurvival.success) {
    return (response = {
      status: oldInventaryFirstSurvival.status,
      success: oldInventaryFirstSurvival.success,
      message: oldInventaryFirstSurvival.message,
    });
  }

  if (!oldInventarySecondSurvival.success) {
    return (response = {
      status: oldInventarySecondSurvival.status,
      success: oldInventarySecondSurvival.success,
      message: oldInventarySecondSurvival.message,
    });
  }

  const newInventaryFirstSurvival = await addResourceFromInventary({
    survivalId: firstSurvival.Survival.id,
    itemsToRecived: itemsToRecived.items,
    inventarySurvival: firstSurvival.inventaryHasItems,
  });

  const newInventarySecondSurvival = await addResourceFromInventary({
    survivalId: secondSurvival.Survival.id,
    itemsToRecived: itemsToSend.items,
    inventarySurvival: secondSurvival.inventaryHasItems,
  });

  response.data = {
    firstSurvival: {
      oldInventary: oldInventaryFirstSurvival.inventaryWithSubstracResources,
      newInventary: newInventaryFirstSurvival,
    },
    secondSurvival: {
      oldInventary: oldInventarySecondSurvival.inventaryWithSubstracResources,
      newInventary: newInventarySecondSurvival,
    },
  };

  return response;
};
module.exports = { tradeSurvivalController };
