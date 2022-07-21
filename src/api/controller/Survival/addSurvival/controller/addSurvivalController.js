const createSurvivalInDB = require("../../../../services/Survival/addSurvival/addSurvivalOfDataBase");
const {
  addInventaryController,
} = require("../../../Inventary/addInventary/controller/addInventaryController");
const {
  addInventaryHasItemController,
} = require("../../../InventaryHasItems/addInventaryHasItems/controller/addInventaryHasItemController");

const createSurvivalController = async ({
  name,
  age,
  gender,
  lastLocation,
  resources,
}) => {
  const survival = await createSurvivalInDB({
    name,
    age,
    gender,
    lastLocation,
  });

  const inventary = await addInventaryController(survival.id);

  const addInventaryIdInResources = resources.map((resource) => {
    resource.inventaryId = inventary.id;

    return resource;
  });

  await addInventaryHasItemController(addInventaryIdInResources);

  return survival;
};

module.exports = { createSurvivalController };
