/**
 * Created by Lins on 13/02/2016.
 */
angular.module("angle").factory("contratoService", ["$http", "$log", function($http, $log) {

    return {

        saveContrato: function(contrato) {
            $log.info("Chamando o Servico: contratoService.saveContrato");
            return $http.post("http://localhost:8080/locaimob/contrato/save",contrato);
        }
    }
}])
