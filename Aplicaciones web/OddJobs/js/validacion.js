const validate = new window.JustValidate(document.querySelector('#formulario-taller'));

validate
  .addField(document.querySelector('#nombre-inventor'), [
    {
      rule: 'required',
      errorMessage: 'Debe ingresar su nombre',
    },
    {
      rule: 'minLength',
      value: 3,
    },
    {
      rule: 'maxLength',
      value: 15,
    },

  ])
  .addField(document.querySelector('#correo-inventor'), [
    {
      rule: 'required',
      errorMessage: 'Debe ingresar su corero dimensional',
    },
    {
      rule: 'email',
      errorMessage: 'Debe ingresar un correo dimensional válido',
    },
  ])
        .addField('#nombre-invento', [
        {
            rule: 'required',
            errorMessage: 'Debes ponerle nombre a tu invento.'
        },
        {
            rule: 'minLength',
            value: 3,
            errorMessage: 'El nombre debe tener al menos 3 caracteres.'
        },
        {
            rule: 'maxLength',
            value: 40,
            errorMessage: 'El nombre no puede superar los 40 caracteres.'
        }
    ])

    
    .addField(document.querySelector('#categoria-invento'), [
        {
            rule: 'required',
            errorMessage: 'Debes seleccionar una categoría.'
        }
    ])


    .addField(document.querySelector('#descripcion-invento'), [
        {
            rule: 'required',
            errorMessage: 'Describe cómo funciona tu invento.'
        },
        {
            rule: 'minLength',
            value: 10,
            errorMessage: 'La descripción debe tener al menos 10 caracteres.'
        },
        {
            rule: 'maxLength',
            value: 500,
            errorMessage: 'La descripción no puede superar los 500 caracteres.'
        }
    ])
    


  .addRequiredGroup('#grupo-estado-invento', 'Debes seleccionar la etapa de tu idea.', {
    tooltip: {
      position: 'bottom',
      errorMessage: 'Debes seleccionar la etapa de tu idea.'
    },
  })
    ;