const mysql =  require('mysql2');

const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'root123',
    database: 'bdd_uno',
    port: 3306
});

db.connect((err)=>{
    if(err){
        throw err;
        return;
    }
    console.log('Conexión exitosa a la base de datos');
})

module.exports = db;