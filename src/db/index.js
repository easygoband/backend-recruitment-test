require("dotenv").config();
const { Sequelize } = require("sequelize");

const DATABASE = process.env.DATABASE;
const USER = process.env.USER;
const PASSWORD = process.env.PASSWORD;

const sequelize = new Sequelize(DATABASE, USER, PASSWORD, {
  host: "localhost",
  dialect: "postgres",
});

module.exports = { sequelize };
