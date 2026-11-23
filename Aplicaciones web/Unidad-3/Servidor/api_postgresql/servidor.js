const express = require('express');
const cors = require('cors');
require('dotenv').config();
const {probarConexion} = require('./configuracion/baseDatos');
const {obtenerVideojuegos,
    obtenerVideojuegoById,
    crearVideojuego,
    actualizarVideojuego,
    eliminarVideojuego} = require('./controllers/videojuegosControlador');


const app = express();
const port = process.env.PORT || 3001;

app.use(cors());
app.use(express.json());


app.get('/',(req,res)=>{
    res.send(`
        <!DOCTYPE html>
        <html lang="es">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>API videojuegos</title>
            <style>
                html{
                    height: 100%;
                }
                body{
                    margin: 0;
                    background-color: black;
                    color: aquamarine;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    height: 100%;
                    text-align: center;
                }
            </style>
        </head>
        <body>
            <div>
            <h1>Api de videojuegos funcionando</h1>
            <p>Servidor activo</p>
            </div>
        </body>
        </html>
`)
    /*
    res.json({
        mensaje: 'API De videojuegos funcionando',
        estado: 'Servidor activo'
    });
    */
});

app.get('/api/videojuegos', obtenerVideojuegos);
app.get('/api/videojuegos/:id', obtenerVideojuegoById);

app.post('/api/videojuegos', crearVideojuego);
app.put('/api/videojuegos/:id', actualizarVideojuego);
app.delete('/api/videojuegos/:id', eliminarVideojuego);

const iniciarServidor = async() =>{
    try {
        await probarConexion();
        app.listen(port, () =>{
            console.log(`Servidor ejecutandose en http://localhost:${port}`);
        });
    } catch (error) {
        console.error('Error al iniciar  postgress: '+error.message);
    }
};

iniciarServidor();