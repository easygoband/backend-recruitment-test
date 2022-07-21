const { inventaryHasItems } = require("../../../../db/models");

const updateInventaryHasItem = async (updateData) => {
  let response = [];
  for (const item of updateData) {
    response.push(
      await inventaryHasItems.update(
        { amount: item.amount },
        { where: { id: item.id } }
      )
    );
  }

  return response;
};

module.exports = updateInventaryHasItem;
