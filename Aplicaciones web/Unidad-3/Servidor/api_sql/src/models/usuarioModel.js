const db = require('../config/dbconfig');

class Usuario{

    getUsuarios(callback){
        const sql = 'select * FROM usuario;';
        db.query(sql, callback);
    }
    
    getUsuariosById(id, callback){
        const sql = 'select * FROM usuario WHERE id_usuario=?;';
        db.query(sql,[id], callback);
    }
    
    postUsuario(rol,nombre,correo,pass,activo,callback){
        const sql = 'INSERT INTO usuario(id_rol, nombre_usuario, correo, password, activo) VALUES ( ?, ?, ?, ?, ?);';
        db.query(sql, [rol,nombre,correo,pass,activo], (err, result)=>{
            if (err) {
                return callback(err, null);
            }
            callback(null, result.insertId);
        });
    }

    putUsuario(rol,nombre,correo,pass,activo, id, callback){
        const sql = 'UPDATE usuario SET id_rol = ?, nombre_usuario = ?, correo = ?, password = ?, activo = ? WHERE id_usuario = ?;';
        db.query(sql, [rol,nombre,correo,pass,activo, id], callback);
    }
    deleteUsuario(id, callback){
        const sql = 'DELETE FROM usuario WHERE id_usuario = ?';
        db.query(sql, [id], callback);
    }
}

module.exports = new Usuario()