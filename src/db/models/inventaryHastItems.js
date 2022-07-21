"use strict";
const { DataTypes } = require("sequelize");
const { sequelize } = require("../index");

const inventaryHasItems = sequelize.define(
  "inventaryHasItems",
  {
    amount: {
      type: DataTypes.INTEGER,
    },
  },
  {
    timestamps: true,
    paranoid: true,
    charset: "utf8",
    collate: "utf8_general_ci",
    sequelize,
    tableName: "inventaryHasItems",
  }
);

module.exports = inventaryHasItems;
