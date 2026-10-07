const btnTema2= document.querySelector('#ajch_btn_cambio');
const padre= document.querySelector('.ajch_divmain');
btnTema2.addEventListener('click',() =>{
    padre.classList.toggle('modo_transparente');
    if(padre.classList.contains('modo_transparente')){
        btnTema2.textContent= ' OPACO :)';
    }else{
        btnTema2.textContent = ' TRANSPARENTE :)';

    }
    console.log('Se aplicó transparente',padre.classList.contains('modo_transparente'));

});