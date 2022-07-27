const { getTotalPoint } = require("../../../../../src/api/helpers/Trade");

describe("Test Helper Trade getTotalPoints()", () => {
  const items = [
    { amount: 2, itemId: 4, inventaryId: 3 },
    { amount: 1, itemId: 1, inventaryId: 3 },
  ];
  const resources = [
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

  it("Should return a total points", () => {
    const points = getTotalPoint({ items, resources });

    expect(points).toBe(6);
  });
});
