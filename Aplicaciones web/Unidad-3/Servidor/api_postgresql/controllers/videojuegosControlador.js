const {pool} = require('../configuracion/baseDatos');

const obtenerVideojuegos = async(req,res)=>{
    try {
        const sql = `SELECT * FROM videojuegos ORDER BY id ASC`;

        const resultado = await pool.query(sql);
        res.json({
            exito: true,
            mensage: '---videojuegos---',
            datos: resultado.rows,
            total: resultado.rows.length
        });
    } catch (error) {
        console.error('Error:', error);
        res.status(500).json({
            exito: false,
            mensage: 'Error al obtener los videojuegos',
            error: error.message
        });
    }
};


const obtenerVideojuegoById = async (req,res) => {
    try {
        const {id} = req.params;
        const sql = 'SELECT * FROM videojuegos WHERE id = $1';
        const resultado = await pool.query(sql, [id]);

        if(resultado.rows.length === 0){
            return res.status(404).json({
                exito: false,
                mensaje: 'Videojuego no encontrado'
            });
        }
        res.json({
                exito: true,
                mensaje: 'Videojuego encontrado',
                datos: resultado.rows[0]
        });
    } catch (error) {
        console.error('Error:', error);
        res.status(500).json({
            exito: false,
            mensage: 'Error al obtener el videojuego',
            error: error.message
        });
    }
}

const crearVideojuego = async (req,res) => {
    try {
        const {
            nombre,
            genero,
            plataforma,
            precio,
            fecha_lanzamiento,
            desarrollador,
            descripcion
        } = req.body;

        if (!nombre ||!genero ||!plataforma ||!precio) {
                return res.status(404).json({
                exito: true,
                mensaje: 'los campos nombre, genero, plataforma y precio son obligatorios '
            });
        }
                const sql = `
            INSERT INTO videojuegos (
                nombre,
                genero,
                plataforma,
                precio,
                fecha_lanzamiento,
                desarrollador,
                descripcion
            )
            VALUES ($1, $2, $3, $4, $5, $6, $7)
            RETURNING *
        `;

                const valores = [
            nombre,
            genero,
            plataforma,
            precio,
            fecha_lanzamiento,
            desarrollador,
            descripcion
        ];

        const resultado = await pool.query(sql, valores);



        res.status(201).json({
            exito: true,
            mensaje: 'Videojuego creado correctamente',
            datos: resultado.rows[0]
        });
    } catch (error) {
        console.error('Error:', error);
        res.status(500).json({
            exito: false,
            mensage: 'Error al crear el videojuego',
            error: error.message
        });
    }
}

const actualizarVideojuego = async (req, res) => {
    try {
        const {id}= req.params;
        const {
            nombre,
            genero,
            plataforma,
            precio,
            fecha_lanzamiento,
            desarrollador,
            descripcion
        } = req.body;
        const sql = `
        UPDATE videojuegos
        SET nombre = $1,
                genero = $2,
                plataforma = $3,
                precio = $4,
                fecha_lanzamiento = $5,
                desarrollador = $6,
                descripcion = $7
        WHERE id = $8
        RETURNING *`
                        const valores = [
            nombre,
            genero,
            plataforma,
            precio,
            fecha_lanzamiento,
            desarrollador,
            descripcion,
            id
        ];

        const resultado = await pool.query(sql, valores);


        if(resultado.rows.length === 0){
            return res.status(404).json({
                exito: false,
                mensaje: 'Videojuego no encontrado'
            });
        }
        res.status(201).json({
            exito: true,
            mensaje: 'Videojuego actualizado correctamente',
            datos: resultado.rows[0]
        });
    } catch (error) {
        console.error('Error:', error);
        res.status(500).json({
            exito: false,
            mensage: 'Error al actualizar el videojuego',
            error: error.message
        });
    }
}

const eliminarVideojuego = async (req, res) => {
    try {
        const { id } = req.params;

        const sql = 'DELETE FROM videojuegos WHERE id = $1 RETURNING *';

        const resultado = await pool.query(sql, [id]);

        if (resultado.rows.length === 0) {
            return res.status(404).json({
                exito: false,
                mensaje: 'Videojuego no encontrado'
            });
        }

        res.json({
            exito: true,
            mensaje: 'Videojuego eliminado correctamente',
            datos: resultado.rows[0]
        });

    } catch (error) {
        console.error('Error:', error);

        res.status(500).json({
            exito: false,
            mensaje: 'Error al eliminar el videojuego',
            error: error.message
        });
    }
};

module.exports = {
    obtenerVideojuegos,
    obtenerVideojuegoById,
    crearVideojuego,
    actualizarVideojuego,
    eliminarVideojuego
}
