const {
  inventary,
  survival,
  inventaryHasItems,
  item,
} = require("../../../../db/models");

const getInventaryBySurvivalIdInDB = async (id) => {
  return await inventary.findOne({
    include: [
      { model: survival, as: "Survival" },
      {
        model: inventaryHasItems,
        as: "inventaryHasItems",
        include: { model: item, as: "item" },
      },
    ],
    where: { survivalId: id },
  });
};

module.exports = getInventaryBySurvivalIdInDB;
