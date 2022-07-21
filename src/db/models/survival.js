"use strict";
const { DataTypes } = require("sequelize");
const { sequelize } = require("../index");

const survival = sequelize.define(
  "Survivals",
  {
    name: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    age: {
      type: DataTypes.STRING,
    },
    gender: {
      type: DataTypes.STRING,
    },
    lastLocation: {
      type: DataTypes.GEOMETRY("POINT"),
      allowNull: false,
    },
    isInfected: {
      type: DataTypes.BOOLEAN,
      defaultValue: false,
    },
    reports: {
      type: DataTypes.INTEGER,
      defaultValue: 0,
    },
  },
  {
    timestamps: true,
    paranoid: true,
    charset: "utf8",
    collate: "utf8_general_ci",
    sequelize,
    tableName: "Survivals",
  }
);

module.exports = survival;
