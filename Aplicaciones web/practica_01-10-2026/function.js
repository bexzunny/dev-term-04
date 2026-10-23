const btnTema2= document.querySelector('#btn_modo');
const padre= document.getElementById('div_padre');
btnTema2.addEventListener('click',() =>{
    padre.classList.toggle('modo_transparente');
    if(padre.classList.contains('modo_transparente')){
        btnTema2.textContent= ' OPACO :)';
    }else{
        btnTema2.textContent = ' TRANSPARENTE :)';

    }
    console.log('Se aplicó transparente',padre.classList.contains('modo_transparente'));

});
const form = document.getElementById('formulario');
const nombreInput = document.getElementById('nombre');
const emailInput = document.getElementById('email');
const mesajeInput = document.getElementById('mensaje');
const celularInput = document.getElementById('celular');

form.addEventListener('submit', (event)=>{
    event.preventDefault //evita que el formulario se auto envíe
    // limpia los mensajes de error anteriores
    divrespuesta.innerHTML= '';

    //arreglo para almacenar los mensajes de error
    const errores = [];

    //validaciones
    if(nombreInput.value.trim() == ''){
        errores.push('El campo nombre es obligatorio');
    }

    // Validar el campo email
    if(emailInput.value.trim == ''){
        errores.push('El campo email es obligatorio');
    }else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(emailInput.value)){
        errores.push('El campo email no es válido')
    }
    if(mesajeInput.value.trim() == ''){
        errores.push('El campo mensaje es obligatorio');
    }

    if(celularInput.value.trim() == ''){
        errores.push('El campo celular es obligatorio');
    }else if(!/^d{10}$/.test(celularInput.value)){
        errores.push('El campo celular dee contener 10 digitos')
    }
    if(errores.length>0){
        divrespuesta.style.color= 'red';
        divrespuesta.innerHTML = errores.join('<br>');
    }else{
        divrespuesta.style.color = 'green';
        divrespuesta.innerHTML = 'Formulario enviado correctamente';
        form.reset()
    }
}


)