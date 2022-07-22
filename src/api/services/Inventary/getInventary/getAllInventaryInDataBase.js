const {
  inventary,
  survival,
  inventaryHasItems,
  item,
} = require("../../../../db/models");

const getAllInventaryInDB = async () => {
  return await inventary.findAndCountAll({
    include: [
      { model: survival, as: "Survival" },
      {
        model: inventaryHasItems,
        as: "inventaryHasItems",
        include: { model: item, as: "item" },
      },
    ],
  });
};

module.exports = getAllInventaryInDB;
