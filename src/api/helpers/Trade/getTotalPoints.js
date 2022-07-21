const getTotalPoint = ({ items, resources }) => {
  return items.reduce((ac, currentItem) => {
    const item =
      resources.find((resource) => resource.itemId === currentItem.itemId).item
        .point * currentItem.amount;

    return ac + item;
  }, 0);
};

module.exports = { getTotalPoint };
