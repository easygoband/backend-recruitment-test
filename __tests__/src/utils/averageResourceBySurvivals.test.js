const { averageResourceBySurvivals } = require("../../../src/utils");

describe("Test utils averageResourceBySurvivals()", () => {
  const totalResources = 20;
  const totalSurvivals = 5;

  it("Should return an average 4", () => {
    const averageResources = averageResourceBySurvivals(
      totalResources,
      totalSurvivals
    );
    expect(averageResources).toBe(4);
  });
});
