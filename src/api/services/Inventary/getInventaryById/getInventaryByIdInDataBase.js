const {
  inventary,
  survival,
  inventaryHasItems,
  item,
} = require("../../../../db/models");

const getInventaryByIdInDB = async (id) => {
  return await inventary.findByPk(id, {
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

module.exports = getInventaryByIdInDB;
