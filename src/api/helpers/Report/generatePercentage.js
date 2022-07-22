const generatePercentage = ({ survivals, countAllSurvivals }) => {
  const survivalsInfected = survivals.reduce((ac, currentSurvival) => {
    if (currentSurvival.isInfected) ac++;
    return ac;
  }, 0);

  const survivalsNotInfected = survivals.reduce((ac, currentSurvival) => {
    if (!currentSurvival.isInfected) ac++;
    return ac;
  }, 0);

  return {
    survivals_infected: `${(survivalsInfected / countAllSurvivals) * 100} %`,
    survivals_not_infected: `${
      (survivalsNotInfected / countAllSurvivals) * 100
    } %`,
  };
};

module.exports = { generatePercentage };
