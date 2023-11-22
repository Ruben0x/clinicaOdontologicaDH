window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del paciente
    const formulario = document.querySelector('#update_pacientes_form');

    formulario.addEventListener('submit', function (event) {

        event.preventDefault();


        let pacienteId = document.querySelector('#paciente_id').value;

        //creamos un JSON que tendrá los datos del paciente
        //a diferencia de un paciente nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nueva
        const formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#nombrePaciente').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#pacienteDni').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,

            domicilioEntradaDto:{
            id:document.querySelector('#domicilio_Id').value,
            calle:document.querySelector('#calle').value,
            numero:document.querySelector('#numero').value,
            localidad:document.querySelector('#localidad').value,
            provincia:document.querySelector('#provincia').value,

            }
        };

        console.log(formData);

        //invocamos utilizando la función fetch la API peliculas con el método PUT que modificará
        //la película que enviaremos en formato JSON
        const url = 'http://localhost:8081/pacientes/actualizar';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => {
          console.log(response)
          response.json()
          })
          .then(data => console.log(data))

    })
 })

    //Es la funcion que se invoca cuando se hace click sobre el id de una pelicula del listado
    //se encarga de llenar el formulario con los datos de la pelicula
    //que se desea modificar
    function findBy(id) {
          const url = '/pacientes/buscar'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let paciente = data;
              document.querySelector('#paciente_id').value = paciente.id;
              document.querySelector('#nombrePaciente').value = paciente.nombre;
              document.querySelector('#apellido').value = paciente.apellido;
              document.querySelector('#pacienteDni').value = paciente.dni;

              document.querySelector('#fechaIngreso').value = paciente.fechaIngreso;
              document.querySelector('#domicilio_Id').value = paciente.domicilioSalidaDto.id;
              document.querySelector('#calle').value = paciente.domicilioSalidaDto.calle;
              document.querySelector('#numero').value = paciente.domicilioSalidaDto.numero;
              document.querySelector('#localidad').value = paciente.domicilioSalidaDto.localidad;
              document.querySelector('#provincia').value = paciente.domicilioSalidaDto.provincia;

              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_paciente_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }