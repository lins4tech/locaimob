/**
 * Created by Lins on 03/01/2016.
 */
angular.module("angle").controller("contratoControllerModalInstance", ["$scope", "$uibModalInstance", "$log", "imovelService", function($scope, $uibModalInstance, $log, imovelService){
    var self = this;
    self.listImoveis = [];
    self.imovelSelected = null;

    self.findListImoveis = function () {
        imovelService.findAllImovelDisponivel().then(
            function sucessFindAllImovelDisponivel(response) {
                self.listImoveis = response.data;
            }
        );
    };
    if(self.listImoveis.length == 0) {
        self.findListImoveis();
    };

    self.ok = function () {
        $uibModalInstance.close(self.imovelSelected);
    };

    self.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

}])