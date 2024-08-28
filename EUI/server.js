/* eslint-disable-next-line */
const express = require('express');

const app = express();

const port = process.env.port || 3010;

app.use(express.static('build'));

app.get('/', (req, res) => {
  res.sendFile(`${__dirname}/build/index.html`);
});

app.listen(port, () => {
  /* eslint-disable-next-line */
  console.log(
    `MF Service - "E-UI SDK Skeleton" is running on port http://localhost:${port}`,
  );
});
