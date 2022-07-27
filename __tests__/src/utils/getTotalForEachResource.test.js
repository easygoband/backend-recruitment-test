const { getTotalForEachResources } = require("../../../src/utils");

describe("Test utils getTotalForEachResources()", () => {
  const exampleInventary = [
    {
      id: 1,
      createdAt: "2022-07-18T04:13:19.469Z",
      updatedAt: "2022-07-18T04:13:19.469Z",
      deletedAt: null,
      survivalId: 1,
      Survival: {
        id: 1,
        name: "German Juarez Baeza",
        age: "56",
        gender: "Masculino",
        lastLocation: {
          crs: {
            type: "name",
            properties: {
              name: "EPSG:4326",
            },
          },
          type: "Point",
          coordinates: [17.98689, -92.93028],
        },
        isInfected: true,
        reports: 3,
        createdAt: "2022-07-18T04:13:19.415Z",
        updatedAt: "2022-07-18T05:36:56.522Z",
        deletedAt: null,
      },
      inventaryHasItems: [
        {
          id: 2,
          amount: 5,
          createdAt: "2022-07-18T04:13:19.472Z",
          updatedAt: "2022-07-18T04:13:19.472Z",
          deletedAt: null,
          itemId: 3,
          inventaryId: 1,
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
          id: 1,
          amount: 5,
          createdAt: "2022-07-18T04:13:19.472Z",
          updatedAt: "2022-07-18T04:13:19.472Z",
          deletedAt: null,
          itemId: 1,
          inventaryId: 1,
          item: {
            id: 1,
            item: "Water",
            point: 4,
            createdAt: "2022-07-18T04:12:33.173Z",
            updatedAt: "2022-07-18T04:12:33.173Z",
            deletedAt: null,
          },
        },
      ],
    },
  ];

  it("Should return an amount foreach resource", () => {
    const {
      totalMedicationAmount,
      totalWaterAmount,
      totalFoodAmount,
      totalAmmunitionAmount,
    } = getTotalForEachResources(exampleInventary);

    expect(totalMedicationAmount).toBe(5);
    expect(totalWaterAmount).toBe(5);
    expect(totalFoodAmount).toBe(0);
    expect(totalAmmunitionAmount).toBe(0);
  });
});
