const express = require('express');
const router = express.Router();
const usuarioController = require('../controllers/usuarioController');

router.get('/', usuarioController.getUsuario);

router.get('/:id', usuarioController.getUsuariosById);

router.post('/', usuarioController.postUsuario);

router.put('/:id', usuarioController.putUsuario);

router.delete('/:id', usuarioController.deleteUsuario);



module.exports = router;