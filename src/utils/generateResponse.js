const generateResponse = (code, data) => {
  return {
    statusCode: code,
    headers: {
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Headers":
        "Origin, X-Requested-With, Content-Type, Accept",
    },
    body: { ...data },
  };
};

module.exports.generateResponse = generateResponse;
