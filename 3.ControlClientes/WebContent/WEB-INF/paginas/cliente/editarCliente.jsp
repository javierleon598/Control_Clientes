<!DOCTYPE html>
<html>
	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"  integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
		<!-- Font Awesome -->
		<script src="https://kit.fontawesome.com/fa8ddfa445.js" crossorigin="anonymous"></script>
		
		<title>Editar Cliente</title>
	</head>
	<body>
		<!-- Cabecero HEADER-->
		<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />
	
		<form action="${pageContext.request.contextPath}/ServletControlador?accion=modificar&idCliente=${cliente.idCliente}"
              method="POST" class="was-validated">
		
			<!-- Botones de navegacion SECTION-->
			<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionEdicion.jsp" />
		
			<!-- Botones de navegacion BODY-->
			<section id="details">
				<div class="container">
					<div class="row">
						<div class="col">
							<div class="card">
								<div class="card-header">
									<h4>Editar Cliente</h4>
								</div>								
								<div class="card-body">								
									<div class="form-group">
										<label for="nombre">Nombre</label>							
										<input type="text" name="nombre" class="form-control" required value="${cliente.nombre}">										 
									</div>
									<div class="form-group">
										<label for="nombre">Apellido</label>						 
										<input type="text" name="apellido" class="form-control" required value="${cliente.apellido}"> 
									</div>
									<div class="form-group">
										<label for="nombre">Email</label> 
										<input type="email" name="email" class="form-control" required value="${cliente.email}"> 
									</div>
									<div class="form-group">
										<label for="nombre">Telefono</label> 
										<input type="number" name="telefono" class="form-control" required value="${cliente.telefono}">
									</div>
									<div class="form-group">
										<label for="nombre">Saldo</label> 
										<input type="number" name="saldo" class="form-control" required value="${cliente.saldo}" step="any">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>	
					
		</form>
		
		<!-- Pie de pagina FOOTER-->
		<jsp:include page="/WEB-INF/paginas/comunes/footer.jsp" />
	
	
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	
	</body>
</html>