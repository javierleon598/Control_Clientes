<div id="agregarClienteModal" class="modal fade">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-info text-white">
				<h5 class="modal-title">Agregar Cliente</h5>
				<button class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
			</div>

			<form
				action="${pageContext.request.contextPath}/ServletControlador?accion=insertar"
				method="POST">
				<div class="modal-body">
					<div class="form-group">
						<label for="nombre">Nombre</label> <input type="text"
							name="nombre" class="form-control" required>
					</div>
					<div class="form-group">
						<label for="nombre">Apellido</label> <input type="text"
							name="apellido" class="form-control" required>
					</div>
					<div class="form-group">
						<label for="nombre">Email</label> <input type="email" name="email"
							class="form-control" required>
					</div>
					<div class="form-group">
						<label for="nombre">Telefono</label> <input type="number"
							name="telefono" class="form-control" required>
					</div>
					<div class="form-group">
						<label for="nombre">Saldo</label> <input type="number"
							name="saldo" class="form-control" required step="any">
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" type="submit">Guardar</button>
				</div>
			</form>

		</div>
	</div>
</div>