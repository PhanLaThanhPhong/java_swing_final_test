const express = require("express");
const sharp = require("sharp");
const path = require("path");
const { v4: uuidv4 } = require("uuid");
const { Buffer } = require("buffer");
const fs = require("fs");
const app = express();

const imagesPath = path.join(__dirname, "images");
app.use(express.static(imagesPath));
app.use(
    express.json({
        limit: "20mb",
    })
);
app.use(express.urlencoded({ extended: true }));
app.get("/", (req, res) => {
    res.send(`
        hello world
    `);
});
app.get("/images/:filename", async (req, res) => {
    const { filename } = req.params;
    const { width, height } = req.query;
    const filePath = path.join(imagesPath, filename);
    if (!fs.existsSync(filePath)) {
        res.status(404).send("File not found");
        return;
    }
    let pipeline = sharp(filePath);
    if (width || height) {
        pipeline = pipeline.resize(parseInt(width), parseInt(height), {
            fit: "inside",
            withoutEnlargement: true,
        });
    }
    res.set("Content-Type", "image/jpeg");
    pipeline.pipe(res);
});

app.post("/images/upload", (req, res) => {
    const { imageBase64 } = req.body;
    const buffer = Buffer.from(imageBase64.split(",")[1], "base64");
    const filename = `${uuidv4()}.jpg`;
    const filePath = path.join(imagesPath, filename);
    fs.writeFile(filePath, buffer, "binary", (e) => {
        console.log(e);
    });
    res.send({ filename });
});

app.listen(3333, () => {
    console.log("Server running at http://localhost:3333");
});
