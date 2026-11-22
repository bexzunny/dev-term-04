const express = require('express');
const bodyParser = require('body-parser');
const usuarioRoutes = require('./src/routes/usuarioRoutes');

const app = express();
const port = 3002;

app.use(bodyParser.json());

//Rutas
app.use('/api/usuarios', usuarioRoutes);

// Iniciar el servidor 
app.listen(port, ()=>{
    console.log('Servidor iniciado en: http://localhost:'+ port);
});