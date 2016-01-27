angular.module("angle").factory("corretorService", ["$http", "$log", function($http, $log) {
	
	return {
		saveCorretor: function(corretor) {
			$log.info("Chamando Servico: corretorService.saveCorretor");
			corretor.isAtivo = true;
			return $http.post("http://localhost:8080/locaimob/corretor/save",corretor);
		},
		findAllCorretorAtivos: function() {
			$log.info("Chamando Servico: corretorService.findAllCorretorAtivos");
			return $http.get("http://localhost:8080/locaimob/corretor/findAllAtivos");
		}
	}
}])