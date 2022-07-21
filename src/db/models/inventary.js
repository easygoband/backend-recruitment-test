"use strict";
const { DataTypes } = require("sequelize");
const { sequelize } = require("../index");

const inventary = sequelize.define(
  "Inventary",
  {},
  {
    timestamps: true,
    paranoid: true,
    charset: "utf8",
    collate: "utf8_general_ci",
    sequelize,
    tableName: "Inventary",
  }
);

module.exports = inventary;
