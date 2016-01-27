/**
 * Created by Lins on 24/12/2015.
 */
angular.module("angle").factory("enderecoService", ["$http", "$log", function($http, $log) {

    return {

        findAllEstados: function() {
            $log.info("Chamando Servico: enderecoService.findAllEstados");
            return $http.get("http://localhost:8080/locaimob/endereco/findAllEstado");
        },
        findCidadeByEstado: function(idEstado) {
            $log.info("Chamando Servico: enderecoService.findCidadeByEstado");
            return $http.get("http://localhost:8080/locaimob/endereco/findCidadeByEstado?idEstado="+idEstado);
        },
        findBairroByCidade: function(idCidade) {
            $log.info("Chamando Servico: enderecoService.findBairroByCidade");
            return $http.get("http://localhost:8080/locaimob/endereco/findBairroByCidade?idCidade="+idCidade);
        },
        findEnderecoByCep: function(cep) {
            $log.info("Chanado o WebService: https://viacep.com.br/ws/"+ cep +"/json/");
            return $http.get("https://viacep.com.br/ws/"+ cep.replace("-","") +"/json/");
        }
    }
}])