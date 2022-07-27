const {
  addResourceFromInventary,
} = require("../../../../../src/api/helpers/Trade");

describe("Test Helper Trade addResourceFromInventary()", () => {
  const exampleDataToInventaryHasItems = [
    {
      id: 3,
      amount: 7,
      createdAt: "2022-07-18T05:25:19.757Z",
      updatedAt: "2022-07-22T05:00:01.697Z",
      deletedAt: null,
      itemId: 3,
      inventaryId: 2,
      item: {
        id: 3,
        item: "Medication",
        point: 2,
        createdAt: "2022-07-18T04:12:54.233Z",
        updatedAt: "2022-07-18T04:12:54.233Z",
        deletedAt: null,
      },
    },
    {
      id: 4,
      amount: 9,
      createdAt: "2022-07-18T05:25:19.757Z",
      updatedAt: "2022-07-22T05:00:01.726Z",
      deletedAt: null,
      itemId: 4,
      inventaryId: 2,
      item: {
        id: 4,
        item: "Ammunition",
        point: 1,
        createdAt: "2022-07-18T04:13:03.033Z",
        updatedAt: "2022-07-18T04:13:03.033Z",
        deletedAt: null,
      },
    },
    {
      id: 7,
      amount: 8,
      createdAt: "2022-07-21T01:35:40.165Z",
      updatedAt: "2022-07-22T05:00:01.728Z",
      deletedAt: null,
      itemId: 1,
      inventaryId: 2,
      item: {
        id: 1,
        item: "Water",
        point: 4,
        createdAt: "2022-07-18T04:12:33.173Z",
        updatedAt: "2022-07-18T04:12:33.173Z",
        deletedAt: null,
      },
    },
  ];
  const exampleCreateResponse = {
    id: 17,
    amount: 5,
    itemId: 2,
    inventaryId: 1,
  };
  const exampleUpdateInventary = [[1], [1]];
  const survivalId = 2;
  const itemsToRecived = [
    { amount: 2, itemId: 4, inventaryId: 3 },
    { amount: 1, itemId: 1, inventaryId: 3 },
  ];
  const inventarySurvival = [
    {
      id: 3,
      amount: 3,
      createdAt: "2022-07-18T05:25:19.757Z",
      updatedAt: "2022-07-22T05:00:01.697Z",
      deletedAt: null,
      itemId: 3,
      inventaryId: 2,
      item: {
        id: 3,
        item: "Medication",
        point: 2,
        createdAt: "2022-07-18T04:12:54.233Z",
        updatedAt: "2022-07-18T04:12:54.233Z",
        deletedAt: null,
      },
    },
    {
      id: 4,
      amount: 7,
      createdAt: "2022-07-18T05:25:19.757Z",
      updatedAt: "2022-07-22T05:00:01.726Z",
      deletedAt: null,
      itemId: 4,
      inventaryId: 2,
      item: {
        id: 4,
        item: "Ammunition",
        point: 1,
        createdAt: "2022-07-18T04:13:03.033Z",
        updatedAt: "2022-07-18T04:13:03.033Z",
        deletedAt: null,
      },
    },
    {
      id: 7,
      amount: 6,
      createdAt: "2022-07-21T01:35:40.165Z",
      updatedAt: "2022-07-22T05:00:01.728Z",
      deletedAt: null,
      itemId: 1,
      inventaryId: 2,
      item: {
        id: 1,
        item: "Water",
        point: 4,
        createdAt: "2022-07-18T04:12:33.173Z",
        updatedAt: "2022-07-18T04:12:33.173Z",
        deletedAt: null,
      },
    },
  ];

  beforeEach(() => {
    jest.mock(
      "../../../../../src/api/services/InventaryHasItems/addInventaryHasItem/addInventaryHasItemInDataBase"
    ),
      () => {
        return exampleCreateResponse;
      };

    jest.mock(
      "../../../../../src/api/services/InventaryHasItems/updateInventaryHasItem/updateInventaryHasItemInDataBase"
    ),
      () => {
        return exampleUpdateInventary;
      };

    jest.mock(
      "../../../../../src/api/services/Inventary/getInventaryBySurvivalId/getInventaryBySurvivalIdInDataBase"
    ),
      () => {
        return exampleDataToInventaryHasItems;
      };
  });

  it("Should return inventary updated", async () => {
    const survivalInventaryUpdate = await addResourceFromInventary({
      survivalId,
      itemsToRecived,
      inventarySurvival,
    });

    expect(survivalInventaryUpdate.length).toBeGreaterThan(0);
    expect(survivalInventaryUpdate[0]).toHaveProperty("dataValues");
  });
});
