
<!DOCTYPE html>
<html>
<head>
  <title>Administraci&oacute;n de eventos</title>
  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="estilos.css">
</head>


<body>
  <div class="container" id="titulo">
    <br>  
    <h1>Administración</h1>
    <br>
  </div>

  <?php 
  /**
   * PROCESAMIENTO DE ACCIONES
   */
  if(isset($_POST['Eliminar'])){
    
    $idEventoEliminar = $_POST['Eliminar'];
    
    $apiUrl = "http://217.71.206.218:8080/borrarEvento";
    $queryParams = array(
      "idEvento" => $_POST['Eliminar']
    );


  $ch = curl_init();


  $url = $apiUrl . '?' . http_build_query($queryParams);

  curl_setopt($ch, CURLOPT_URL, $url);
  curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE");
  curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

  $respuesta = curl_exec($ch);


  if (curl_errno($ch)) {
    echo 'Error en la solicitud: ' . curl_error($ch);
  } else {
  
    $respuestaProcesada = json_decode($respuesta, true);

    if($respuestaProcesada == true){
      echo '<br>
            <div class="container d-flex justify-content-center alert alert-success">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <h5>El evento fue eliminado correctamente.</h5>
            </div>';

    } else {
      echo '<br>
            <div class="container d-flex justify-content-center alert alert-danger">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <h5>Hubo errores al eliminar el evento.</h5>
            </div>';

    }
  }

  curl_close($ch);

  } else if(isset($_POST['Crear_evento'])){
    
    $apiUrl = "http://217.71.206.218:8080/crearEvento";
    $queryParams = array(
      "artista" => $_POST['nombre'],
      "fecha" => $_POST['fecha'],
      "ciudad" => $_POST['ciudad'],
      "lugar" => $_POST['lugar'],
      "entradas" => $_POST['entradas'],
      "estadio" => $_POST['estadio']
    );


  $ch = curl_init();


  $url = $apiUrl . '?' . http_build_query($queryParams);

  curl_setopt($ch, CURLOPT_URL, $url);
  curl_setopt($ch, CURLOPT_POST, 1);
  curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

  $respuesta = curl_exec($ch);


  if (curl_errno($ch)) {
    echo 'Error en la solicitud: ' . curl_error($ch);
  } else {
  
    $respuestaProcesada = json_decode($respuesta, true);

    if($respuestaProcesada == true){
      echo '<br>
            <div class="container d-flex justify-content-center alert alert-success">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <h5>El evento fue agregado correctamente.</h5>
            </div>';

    } else {
      echo '<br>
            <div class="container d-flex justify-content-center alert alert-danger">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <h5>Hubo errores al agregar el evento.</h5>
            </div>';

    }
  }

  curl_close($ch);
    
  } else if(isset($_POST['Editar_evento'])){
    echo '<br>
            <div class="container d-flex justify-content-center alert alert-danger">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <h5>La API REST para editar eventos no ha sido implementada todavía.</h5>
            </div>';
  }
  ?>
  
  <?php
  /**
   * CARGA DEL LISTADO DE EVENTOS
   */
  $apiURL = "http://217.71.206.218:8080/listarEventos";


  $ch = curl_init();
  curl_setopt($ch, CURLOPT_URL, $apiURL);
  curl_setopt($ch,CURLOPT_RETURNTRANSFER,true);

  $respuesta = curl_exec($ch);
  

  if (curl_errno($ch)) {
    echo 'Error en la solicitud: ' . curl_error($ch);
  } else {
    $listadoEventos = json_decode($respuesta, true);
  }

  curl_close($ch);
  
?>
  <div class="container span6">
  <table class="table table-hover">
  <thead>
    <tr>
      <th>#</th>
      <th>Artista</th>
      <th>Ciudad</th>
      <th>Lugar</th>
      <th>Fecha</th>
      <th>Numero de entradas</th>
      <th>Tipo de estadio</th>
      <th class="ancho_fijo_1"></th>
    </tr>
  </thead>

<?php
  foreach($listadoEventos as $evento){
    echo "<tr>";
      
      echo "<td>#";
      echo $evento['id'];
      echo "</td>";

      echo "<td>";
      echo $evento['artista'];
      echo "</td>";

      echo "<td>";
      echo $evento['ciudad'];
      echo "</td>";

      echo "<td>";
      echo $evento['lugar'];
      echo "</td>";

      echo "<td>";
      echo $evento['fecha'];
      echo "</td>";
      
      echo "<td>";
      echo $evento['entradas'];
      echo "</td>";
      
      echo "<td>";
      echo $evento['tipoEstadio'];
      echo "</td>";
      
      echo '<td><span class="pull-right">';
      echo '<a class="fa fa-trash" data-toggle="modal" data-target="#borrar' . $evento['id'] .'"></a>';
      echo '&nbsp';
      echo '<a class="fa fa-edit" data-toggle="modal" data-target="#editar' . $evento['id'] .'"></a>';
      echo '</span></td>';
            

    echo "<tr>";
  }
  
  ?>  

</table>

<div class="text-center">
  <button class="btn btn-large btn-success" data-toggle="modal" data-target="#crear" >
    <i class="fa fa-plus icon-white">&nbsp;</i>&nbsp;
      A&ntilde;adir evento
  </button>
</div>
<br><br><br>


<?php
foreach($listadoEventos as $evento){
?>
  
  <div class="modal" id="borrar<?php echo $evento['id']?>">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title"><?php echo "Borrar evento de <b>" . $evento['artista'] . "</b> en " . $evento['ciudad'] . " #" .  $evento['id']?></h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <p>¿Desea eliminar el evento?</p>
        </div>
        <div class="modal-footer">
          <form method="POST">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
          <button type="submit" name="Eliminar" value="<?php echo $evento['id']?>" class="btn btn-danger">Confirmar borrado</button>
          </form>
        </div>
      </div>
    </div>
  </div>

  <div class="modal" id="editar<?php echo $evento['id']?>">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Editar <?php echo $evento['id']?></h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
        <form method="post">
            <div class="form-group">
              <label for="nombre">Nombre Artista</label>
              <input value="<?php echo $evento['artista']?>" type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresa el nombre del artista">
            </div>
            <div class="form-group">
              <label for="ciudad">Ciudad evento</label>
              <input value="<?php echo $evento['ciudad']?>" type="text" class="form-control" id="ciudad" name="ciudad" placeholder="Ingresa la ciudad">
            </div>
            <div class="form-group">
              <label for="lugar">Lugar</label>
              <input value="<?php echo $evento['lugar']?>" type="text" class="form-control" id="lugar" name="lugar" placeholder="Ingresa el recinto">
            </div>
            <div class="form-group">
              <label for="fecha">Fecha</label>
              <input value="<?php echo $evento['fecha']?>" type="date" class="form-control" id="fecha" name="fecha" placeholder="Ingresa la fecha">
            </div>
            <div class="form-group">
              <label for="entradas">Número de entradas disponibles</label>
              <input value="<?php echo $evento['entradas']?>" type="number" class="form-control" id="entradas" name="entradas" placeholder="Introduce el número de entradas disponibles">
            </div>
            <div class="form-group">
              <label for="estadio">Tipo estadio</label>
              <select name="estadio" class="form-control" id="opciones">
                <option value="1" <?php if($evento['tipoEstadio'] == 1){echo "selected";}?>>Estadio tipo 1</option>
                <option value="2" <?php if($evento['tipoEstadio'] == 2){echo "selected";}?>>Estadio tipo 2</option>
                <option value="3" <?php if($evento['tipoEstadio'] == 3){echo "selected";}?>>Estadio tipo 3</option>
              </select>
            </div>
          
          
        </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
            <button type="submit" name="Editar_evento" class="btn btn-primary">Editar evento</button>
          </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  
<?php 
} 
?>

<div class="modal" id="crear">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Crear evento</h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <form method="post">
            <div class="form-group">
              <label for="nombre">Nombre Artista</label>
              <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresa el nombre del artista">
            </div>
            <div class="form-group">
              <label for="ciudad">Ciudad evento</label>
              <input type="text" class="form-control" id="ciudad" name="ciudad" placeholder="Ingresa la ciudad">
            </div>
            <div class="form-group">
              <label for="lugar">Lugar</label>
              <input type="text" class="form-control" id="lugar" name="lugar" placeholder="Ingresa el recinto">
            </div>
            <div class="form-group">
              <label for="fecha">Fecha</label>
              <input type="date" class="form-control" id="fecha" name="fecha" placeholder="Ingresa la fecha">
            </div>
            <div class="form-group">
              <label for="entradas">Número de entradas disponibles</label>
              <input type="number" class="form-control" id="entradas" name="entradas" placeholder="Introduce el número de entradas disponibles">
            </div>
            <div class="form-group">
              <label for="estadio">Tipo estadio</label>
              <select name="estadio" class="form-control" id="opciones">
                <option value="1">Estadio tipo 1</option>
                <option value="2">Estadio tipo 2</option>
                <option value="3">Estadio tipo 3</option>
              </select>
            </div>
          
          
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
          <button type="submit" name="Crear_evento" class="btn btn-primary">Crear evento</button>
        </form>
          
        </div>
      </div>
    </div>
  </div>

  <footer class="footer">
  <div class="container">
    <div class="row">
      <div class="col-lg-6 col-md-6 col-sm-12">
        <h4>Grupo 20. Sistemas distribuidos y servicios web</h4>
      </div>
      <div class="col-lg-6 col-md-6 col-sm-12">
        <h4>Miembros del grupo:</h4>
        <ul class="list-unstyled">
          <li>Arnal Ramos, Jaime</li>
          <li>Canovaca Bravo, Marcelino</li>
          <li>Vinuesa Serrano, Jesús</li>
        </ul>
      </div>
    </div>
  </div>
  <div class="text-center">
    <p>&copy; 2023 Ingeniería de las tecnologías de las telecomunicaciones. Mención en telemática.</p>
  </div>
</footer>

  
</body>
</html>
