// Función para cambiar el tema de la página
const btnTema2 = document.querySelector('#btn_modo');
const body = document.body;
const imagen1 = document.getElementById('miImagen1');
const imagen2 = document.getElementById('miImagen2');
const imagen3 = document.getElementById('miImagen3');
const imagen4 = document.getElementById('miImagen4');
;

btnTema2.addEventListener('click', () => {
  body.classList.toggle('modo_transparente');

  if (body.classList.contains('modo_transparente')) {
    btnTema2.textContent = ' OPACO (: ';
  } else {
    btnTema2.textContent = ' TRANSPARENTE :) ';
  }
  console.log('Se aplico transparente:', body.classList.contains
    ('modo_transparente'));
});


// 1. Cambia a la nueva imagen al pasar el cursor por encima
imagen1.addEventListener('mouseenter', () => {
    imagen1.src = './img/imagen1-1.webp';
});

// 2. Regresa a la imagen original al quitar el cursor
imagen1.addEventListener('mouseleave', () => {
    imagen1.src = './img/imagen1.webp';
});




imagen2.addEventListener('mouseenter', () => {
    imagen2.style.opacity = '0';
});

// 2. Regresa a la imagen original al quitar el cursor
imagen2.addEventListener('mouseleave', () => {
    imagen2.style.opacity = '1';
});



// Aplica una transición directamente al estilo del elemento
imagen3.style.transition = 'filter 0.3s ease';

imagen3.addEventListener('mouseenter', () => {
    // Convierte la imagen a blanco y negro y la desenfoca un poco
    imagen3.style.filter = 'grayscale(100%) drop-shadow(0px 0px 40px #e00e1c)';
});

imagen3.addEventListener('mouseleave', () => {
    // Devuelve la imagen a su estado original
    imagen3.style.filter = 'grayscale(0%) ';
});


//////////////////////////////////////////////////
//efecto zoom
/////////////////////////////////////////////////


// Al pasar el cursor: cambia la imagen y aplica zoom (escala 1.2 = 20% más grande)
imagen4.addEventListener('mouseenter', () => {
    imagen4.style.transform = 'scale(1.15)';
});

// Al quitar el cursor: regresa a la imagen original y tamaño normal
imagen4.addEventListener('mouseleave', () => {
    imagen4.style.transform = 'scale(0.75)';
});




