"use strict";
const { DataTypes } = require("sequelize");
const { sequelize } = require("../index");

const item = sequelize.define(
  "Items",
  {
    item: {
      type: DataTypes.STRING,
    },
    point: {
      type: DataTypes.INTEGER,
    },
  },
  {
    timestamps: true,
    paranoid: true,
    charset: "utf8",
    collate: "utf8_general_ci",
    sequelize,
    tableName: "Items",
  }
);

module.exports = item;
