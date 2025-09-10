const express = require("express");
const checkout = express.Router();
const stripe = require("stripe")(
  "sk_test_51S5kVwHvjiiHUsBkALzlAEiVgEf4eBvTYc7IFxmkZRXny93g4hsyHAcWe9HqkJzkg5lyKFVrAHE1XLoulAqEtyDC00Ke11ILXi"
);
const bodyParser = require("body-parser");
checkout.use(bodyParser.json());

checkout.post("/create-checkout-session", async (req, res) => {
  const { cartItems } = req.body;

  try {
    const lineItems = cartItems.map((item) => ({
      price_data: {
        currency: "usd",
        product_data: {
          name: item.name,
        },
        unit_amount: item.price * 100,
      },
      quantity: item.quantity,
    }));

    const session = await stripe.checkout.sessions.create({
      payment_method_types: ["card"],
      line_items: lineItems,
      mode: "payment",
      success_url: `http://localhost:4200/home/cart?status=success`,
      cancel_url: `http://localhost:4200/home/cart?status=cancel`,
    });
    res.json({ id: session.id });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

module.exports = checkout;
