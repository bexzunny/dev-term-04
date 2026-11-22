
const usuarioModel = require('../models/usuarioModel')

module.exports={
    
    getUsuario:(req, res)=>{
        usuarioModel.getUsuarios((err, result)=>{
            if(err){
                res.status(500).json({error: err.message});
                return;
            }
            
            res.status(200).json({data: result});
        })
    },

    getUsuariosById:(req, res)=>{
        const id = req.params.id;
        usuarioModel.getUsuariosById(id, (err, result)=>{
            if(err){
                res.status(500).json({error: err.message});
                return;
            }
            if(result.length === 0){
                res.status(404).json({message: 'usuario no encontrado'});
                return;
            }
            
            res.status(200).json({data: result});
        })
    },

    postUsuario: (req, res) =>{

        const {id_rol, nombre,correo,pass,activo} = req.body;
        usuarioModel.postUsuario(id_rol, nombre,correo,pass,activo,(err, result)=>{
            if (err) {
                res.status(500).json({error: err.message});
                return;
            }
            res.status(201).json({message: 'Usuario registrado correctamente.', data: {idInsertado: result}});
        })
        

    },

    putUsuario: (req, res) => {
        const id = req.params.id;
        const { id_rol, nombre, correo, pass, activo } = req.body;

        usuarioModel.putUsuario(
            id_rol,
            nombre,
            correo,
            pass,
            activo,
            id,
            (err, result) => {
                if (err) {
                    return res.status(500).json({ error: err.message });
                }

                if (result.affectedRows === 0) {
                    return res.status(404).json({
                        message: 'Usuario no encontrado'
                    });
                }

                res.status(200).json({
                    message: 'Usuario actualizado correctamente.'
                });
            }
        );
    },
    
    deleteUsuario: (req, res) =>{
        const id = req.params.id;
        usuarioModel.deleteUsuario(id, (err, result) =>{
            if (err) {
                res.status(500).json({error: err.message});
                return;
            }
            if (result.affectedRows === 0) {
                res.status(404).json({message: 'Personaje no encontrado'});
                return;
            }
            res.status(200).json({message: 'Usuario eliminado correctamente.'});

        })
    }


}