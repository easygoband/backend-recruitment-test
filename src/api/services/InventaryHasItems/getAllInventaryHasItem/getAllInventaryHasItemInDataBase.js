const {
  inventaryHasItems,
  item,
  inventary,
  survival,
} = require("../../../../db/models");

const getAllInventaryHasItemInDb = async () => {
  return await inventaryHasItems.findAndCountAll({
    include: [
      { model: item, as: "item" },
      {
        model: inventary,
        as: "inventary",
        include: { model: survival, as: "Survival" },
      },
    ],
  });
};

module.exports = getAllInventaryHasItemInDb;
