/**
 * Created by Lins on 25/12/2015.
 */
angular.module("angle").factory("clienteService", ["$http","$log", function($http, $log) {

    return {
        savePessoaFisica: function(pessoaFisica) {
            //corretor.isAtivo = true;
            $log.info("Chamando o Servico: clienteService.savePessoaFisica");
            return $http.post("http://localhost:8080/locaimob/pf/save",pessoaFisica);
        },
        savePessoaJuridica: function(pessoaJuridica) {
            //corretor.isAtivo = true;
            $log.info("Chamando o Servico: clienteService.savePessoaJuridica");
            return $http.post("http://localhost:8080/locaimob/pj/save",pessoaJuridica);
        },
        findAllPessoaFisica: function() {
            $log.info("Chamando o Servico: clienteService.findAllPessoaFisica");
            return $http.get("http://localhost:8080/locaimob/pf/findAll");
        },
        findAllPessoaJuridica: function() {
            $log.info("Chamando o Servico: clienteService.findAllPessoaJuridica");
            return $http.get("http://localhost:8080/locaimob/pj/findAll");
        },
        findAllPessoaVO: function() {
            $log.info("Chamando o Servico: clienteService.findAllPessoaVO");
            return $http.get("http://localhost:8080/locaimob/pessoa/findAll");
        }
    }
}])