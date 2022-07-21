const { item } = require("../../../../db/models");

const createIteInDB = async ({ product, point }) => {
  return await item.create({
    item: product,
    point,
  });
};

module.exports = createIteInDB;
