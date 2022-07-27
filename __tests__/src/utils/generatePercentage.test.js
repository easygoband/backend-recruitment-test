const { generatePercentage } = require("../../../src/utils");

describe("Test utils generatePercentage()", () => {
  const countAllSurvivals = 10;
  const survivalsInfected = 2;

  it("Should return a percentage of 20", () => {
    const percentageSurvivalInfected = generatePercentage(
      countAllSurvivals,
      survivalsInfected
    );
    expect(percentageSurvivalInfected).toBe("20.00 %");
  });
});
