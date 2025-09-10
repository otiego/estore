const express = require("express");
const productCategories = require("./routes/productCategories");
const products = require("./routes/products");
const cors = require("cors");
const user = require("./routes/users");
const bodyParser = require("body-parser");
const orders = require("./routes/orders");
const checkout = require("./routes/checkout");
const app = express();
const PORT = 5001;

app.use(cors());
app.use(bodyParser.json());

app.use("/productCategories", productCategories);
app.use("/products", products);
app.use("/users", user);
app.use("/orders", orders);
app.use("/checkout", checkout);

const server = app.listen(PORT, () => {
  console.log(`App is running on http://localhost:${PORT}`);
});
