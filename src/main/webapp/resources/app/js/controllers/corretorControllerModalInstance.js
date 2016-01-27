angular.module("angle")
.controller("corretorControllerModalInstance", ["$scope", "$uibModalInstance", "corretorService", function($scope, $uibModalInstance, corretorService) {

	var self = this;

	self.dateToday = new Date();

	self.saveCorretor = function(corretor) {
		corretorService.saveCorretor(corretor).then(
				function sucessSaveCorretor(response) {
//					self.corretorSaved = response.data;
//					self.listCorretor.push(self.corretorSaved);
					$uibModalInstance.close(response.data);
					alert("Corretor Salvo com Sucesso!");
				},
				function errorSaveCorretor(response) {
					alert("Error - Ocorreu um Erro ao tentar salvar o corretor.");
				});
	};

	self.findAllCorretorAtivos = function() {
		corretorService.findAllCorretorAtivos().then(
				function sucessSaveCorretor(response) {
					alert("Consulta feita com sucesso!");
				},
				function errorSaveCorretor(response) {
					alert("Erro ao tentar realizar a consulta");
				});
	};

	self.findAllCorretorAtivos();

	self.ok = function () {

		$uibModalInstance.close(null);

	};

	self.cancel = function () {
		$uibModalInstance.dismiss('cancel');
	};

	self.statusDatePicker = {
			    opened: false
	 };

	self.openDatePicker = function($event) {
		self.statusDatePicker.opened = true;
	};

}]);