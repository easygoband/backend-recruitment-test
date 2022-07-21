const app = require("./src/server");
const { sequelize } = require("./src/db");

async function main() {
  try {
    await sequelize.sync();
    app.listen(3000, () => {
      console.log("Server on port 3000");
    });
  } catch (error) {
    console.log(error);
    console.log("Error to connected a DB");
  }
}

main();
