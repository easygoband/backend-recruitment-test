const subtractResourcesFromInventary = async ({
  itemsOnInventary,
  itemsToSubstract,
}) => {
  let response = {
    success: true,
    inventaryWithSubstracResources: [],
  };

  for (const inventaryItem of itemsOnInventary) {
    for (const substractItem of itemsToSubstract) {
      if (inventaryItem.itemId === substractItem.itemId) {
        const newAmount = inventaryItem.amount - substractItem.amount;

        if (newAmount < 0) {
          return (response = {
            status: 400,
            success: false,
            message: "Does not have sufficient resources in its inventory",
          });
        }

        response.inventaryWithSubstracResources.push({
          amount: newAmount,
          id: inventaryItem.id,
        });
      }
    }
  }

  return response;
};

module.exports = { subtractResourcesFromInventary };
