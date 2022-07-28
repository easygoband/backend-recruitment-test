const actionData = {
  item: "required|string",
  point: "required|integer",
};

const messageData = {
  "item.required": "item is required",
  "item.string": "item is type string",
  item: "",
  "point.required": "point is required",
  "point.integer": "point is type integer",
  point: "",
};

module.exports = {
  actionData,
  messageData,
};
