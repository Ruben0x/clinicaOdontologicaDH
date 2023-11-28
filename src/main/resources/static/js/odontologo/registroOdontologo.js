window.addEventListener("load", function () {
  const formulario = document.forms[0];
  const url = "http://localhost:8081/odontologos/registrar";
  formulario.addEventListener("submit", function (evento) {
    evento.preventDefault();
    agregarOdontologo();
  });

  function capturarDatos() {
    const numeroMatricula = document.querySelector("#numeroMatricula");
    const nombreOdontologo = document.querySelector("#nombreOdontologo");
    const apellidoOdontologo = document.querySelector("#apellidoOdontologo");



    const odontologo = {
      matricula: numeroMatricula.value,
      nombre: nombreOdontologo.value,
      apellido: apellidoOdontologo.value,

    };
    return odontologo;
  }

  function agregarOdontologo() {
    const data = capturarDatos();

    const conf = {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };

    fetch(url, conf)
      .then((res) =>
      res.json())
      .then((data) => {
        console.log(data);
      });

    formulario.reset();
  }
});
