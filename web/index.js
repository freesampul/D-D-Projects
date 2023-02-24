const express = require("express");
const mongoose = require("mongoose")
import React from 'react';
import ReactDOM from 'react-dom';



const app = express();
const PORT = process.env.PORT || 3000;

app.use(express.urlencoded({extended: true}))
app.set("view engine", "ejs")
app.use(express.static('public'))

mongoose.connect("mongodb://localhost/27017")


app.post("/", async (req, res) => {
    
})

app.get("/", (req, res) => {
    res.render("chat")
})

app.listen(PORT, (req, res) =>{
    console.log("serving")
})