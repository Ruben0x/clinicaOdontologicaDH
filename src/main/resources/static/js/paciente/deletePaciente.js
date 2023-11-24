function deleteBy(id) {
  //con fetch invocamos a la API de odontologo con el método DELETE
  //pasandole el id en la URL
  console.log("eliminando Paciente " + id);
  const url = "http://localhost:8081/pacientes/eliminar/" + id;
  const settings = {
    method: "DELETE",
  };

  fetch(url, settings)
    .then((response) => {
      console.log(response);
      response.json();
      //borrar la fila de la paciente eliminada
      let row_id = "#tr_" + id;
      document.querySelector(row_id).remove();
    })
    .then((data) => {
      console.log(data);
    })
    .catch((error) => alert("Error al borrar :" + error));
}
