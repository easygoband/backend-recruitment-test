const {
  subtractResourcesFromInventary,
} = require("../../../../../src/api/helpers/Trade");

describe("Test Helper Trade subtractResourcesFromInventary()", () => {
  const itemsOnInventary = [
    {
      id: 5,
      amount: 4,
      createdAt: "2022-07-18T05:26:02.073Z",
      updatedAt: "2022-07-22T05:00:01.714Z",
      deletedAt: null,
      itemId: 1,
      inventaryId: 3,
      item: {
        id: 1,
        item: "Water",
        point: 4,
        createdAt: "2022-07-18T04:12:33.173Z",
        updatedAt: "2022-07-18T04:12:33.173Z",
        deletedAt: null,
      },
    },
    {
      id: 8,
      amount: 7,
      createdAt: "2022-07-21T01:38:38.758Z",
      updatedAt: "2022-07-22T05:00:01.737Z",
      deletedAt: null,
      itemId: 3,
      inventaryId: 3,
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
      id: 6,
      amount: 7,
      createdAt: "2022-07-18T05:26:02.073Z",
      updatedAt: "2022-07-22T05:00:01.739Z",
      deletedAt: null,
      itemId: 4,
      inventaryId: 3,
      item: {
        id: 4,
        item: "Ammunition",
        point: 1,
        createdAt: "2022-07-18T04:13:03.033Z",
        updatedAt: "2022-07-18T04:13:03.033Z",
        deletedAt: null,
      },
    },
  ];
  const itemsToSubstract = [
    { amount: 2, itemId: 4, inventaryId: 3 },
    { amount: 1, itemId: 1, inventaryId: 3 },
  ];

  it("Should retorn an object { success, inventaryWithSubstracResources }", async () => {
    const { success, inventaryWithSubstracResources } =
      await subtractResourcesFromInventary({
        itemsOnInventary,
        itemsToSubstract,
      });

    expect(success).toBe(true);
    expect(JSON.stringify(inventaryWithSubstracResources)).toBe(
      JSON.stringify([
        { amount: 3, id: 5 },
        { amount: 5, id: 6 },
      ])
    );
  });
});

describe("Test Error Helper Trade subtractResourcesFromInventary()", () => {
  const itemsOnInventary = [
    {
      id: 5,
      amount: 2,
      createdAt: "2022-07-18T05:26:02.073Z",
      updatedAt: "2022-07-22T05:00:01.714Z",
      deletedAt: null,
      itemId: 1,
      inventaryId: 3,
      item: {
        id: 1,
        item: "Water",
        point: 4,
        createdAt: "2022-07-18T04:12:33.173Z",
        updatedAt: "2022-07-18T04:12:33.173Z",
        deletedAt: null,
      },
    },
    {
      id: 8,
      amount: 7,
      createdAt: "2022-07-21T01:38:38.758Z",
      updatedAt: "2022-07-22T05:00:01.737Z",
      deletedAt: null,
      itemId: 3,
      inventaryId: 3,
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
      id: 6,
      amount: 1,
      createdAt: "2022-07-18T05:26:02.073Z",
      updatedAt: "2022-07-22T05:00:01.739Z",
      deletedAt: null,
      itemId: 4,
      inventaryId: 3,
      item: {
        id: 4,
        item: "Ammunition",
        point: 1,
        createdAt: "2022-07-18T04:13:03.033Z",
        updatedAt: "2022-07-18T04:13:03.033Z",
        deletedAt: null,
      },
    },
  ];
  const itemsToSubstract = [
    { amount: 2, itemId: 4, inventaryId: 3 },
    { amount: 1, itemId: 1, inventaryId: 3 },
  ];

  it("Should retorn an object { status, success, message }", async () => {
    const { success, status, message } = await subtractResourcesFromInventary({
      itemsOnInventary,
      itemsToSubstract,
    });

    expect(success).toBe(false);
    expect(status).toBe(400);
    expect(message).toBe("Does not have sufficient resources in its inventory");
  });
});
