function eliminar(id) {
    swal({
        title: "¿Está seguro de Eliminar?",
        text: "Una vez eliminado, no podrá deshacer esta acción.",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
    .then((OK) => {
        if (OK) {
            $.ajax({
                url: "/eliminar/" + id,
                success: function(res) {
                    console.log(res);
                }
            });
            swal("Poof! Tus datos han sido eliminados!", {
                icon: "success",
            }).then((ok) => {
                if (ok) {
                    location.href = "/listar";
                }
            });
        } else {
            swal("Tus datos están seguros!");
        }
    });
}
