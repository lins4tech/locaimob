/**
 * Created by Lins on 31/12/2015.
 */
/**
 * Created by Lins on 25/12/2015.
 */
angular.module("angle").factory("imovelService", ["$http","$log", function($http, $log) {

    return {
        findAllImovelDisponivel: function() {
        	 $log.info("Chamando o serviço: imovelService.findAllImovelDisponivel()");
            return $http.get("http://localhost:8080/locaimob/imovel/findDisponivel");
        }
    }
}])
