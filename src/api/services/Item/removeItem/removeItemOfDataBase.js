const { item } = require("../../../../db/models");

const removeItem = async (id) => {
  return await item.destroy({
    where: { id },
  });
};

module.exports = removeItem;
