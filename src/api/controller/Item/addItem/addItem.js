const addItemInDatabase = require("../../../services/Item/addItem/addItemInDataBase");

const addItem = async (req, res) => {
  try {
    const { item, point } = req.body;
    const response = await addItemInDatabase({ product: item, point });

    return res.status(201).json({
      success: true,
      message: "Item added",
      data: response,
    });
  } catch (err) {
    return res.status(500).json({
      success: true,
      message: err.message,
    });
  }
};

module.exports = { addItem };
