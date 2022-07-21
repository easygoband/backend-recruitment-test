const { item } = require("../../../../db/models");

const updateItem = async ({ id, updateData }) => {
  return await item.update(updateData, {
    where: { id },
  });
};

module.exports = updateItem;
