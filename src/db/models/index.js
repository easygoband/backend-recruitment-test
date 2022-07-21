const item = require("./item");
const survival = require("./survival");
const inventary = require("./inventary");
const inventaryHasItems = require("./inventaryHastItems");

/*
 * Relationship survival - inventary
 */

survival.hasOne(inventary, {
  foreignKey: "survivalId",
});

inventary.belongsTo(survival, {
  foreignKey: "survivalId",
});

/**
 *  Relationship items - inventary - inventaryHasItems
 */

item.hasMany(inventaryHasItems, {
  foreignKey: "itemId",
  as: "inventaryHasItems",
});

inventaryHasItems.belongsTo(item, {
  foreignKey: "itemId",
  as: "item",
});

inventary.hasMany(inventaryHasItems, {
  foreignKey: "inventaryId",
  as: "inventaryHasItems",
});

inventaryHasItems.belongsTo(inventary, {
  foreignKey: "inventaryId",
  as: "inventary",
});

module.exports = {
  item,
  survival,
  inventary,
  inventaryHasItems,
};
