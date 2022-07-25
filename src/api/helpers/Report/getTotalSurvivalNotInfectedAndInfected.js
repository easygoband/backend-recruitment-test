const { generatePercentage } = require("../../../utils");

const getTotalSurvivalNotInfectedAndInfected = ({
  survivals,
  countAllSurvivals,
}) => {
  const survivalsInfected = survivals.reduce((ac, currentSurvival) => {
    if (currentSurvival.isInfected) ac++;
    return ac;
  }, 0);

  const survivalsNotInfected = survivals.reduce((ac, currentSurvival) => {
    if (!currentSurvival.isInfected) ac++;
    return ac;
  }, 0);

  const survivals_infected = generatePercentage(
    countAllSurvivals,
    survivalsInfected
  );

  const survivals_not_infected = generatePercentage(
    countAllSurvivals,
    survivalsNotInfected
  );

  return {
    survivals_infected,
    survivals_not_infected,
  };
};

module.exports = { getTotalSurvivalNotInfectedAndInfected };
