const {
  getTotalSurvivalNotInfectedAndInfected,
} = require("../../../../../src/api/helpers/Report");

describe("Test Report helper getTotalSurvivalNotInfectedAndInfected()", () => {
  const survivals = [
    {
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
    {
      id: 3,
      name: "Survival 001",
      age: "39",
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
      isInfected: false,
      reports: 1,
      createdAt: "2022-07-18T05:26:02.010Z",
      updatedAt: "2022-07-18T05:40:42.279Z",
      deletedAt: null,
    },
  ];
  const countAllSurvivals = 5;

  it("Should return percentage the survivals_infected and survival_not_infected", () => {
    const { survivals_infected, survivals_not_infected } =
      getTotalSurvivalNotInfectedAndInfected({ survivals, countAllSurvivals });

    expect(survivals_infected).toBe("20.00 %");
    expect(survivals_not_infected).toBe("20.00 %");
  });
});
