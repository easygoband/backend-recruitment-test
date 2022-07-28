const actionData = {
  name: "required|string",
  age: "required|integer",
  gender: "required|string",
  lastLocation: "required|object",
  resources: "required|array",
};

const messageData = {
  "name.required": "name is required",
  "name.string": "name is type string",
  name: "",
  "age.required": "age is required",
  "age.integer": "age is type integer",
  age: "",
  "gender.required": "gender is required",
  "gender.string": "gender is type string",
  gender: "",
  "lastLocation.required": "lastLocation is required",
  "lastLocation.object": "lastLocation is type object",
  lastLocation: "",
  "resources.required": "resources is required",
  "resources.array": "resources is type array",
  resources: "",
};

module.exports = {
  actionData,
  messageData,
};
