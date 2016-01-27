/**
 * Created by Lins on 14/12/2015.
 */
angular.module("angle").controller("corretorController", ["$scope", "$uibModal", "$log", "corretorService", function($scope, $uibModal, $log, corretorService) {

    var self = this;

    self.listCorretor = [];

    self.lastCorretorSaved = {};

    self.dateToday = new Date();

    self.statusDatePicker = {
        opened: false
    };

    self.openDatePicker = function($event) {
        self.statusDatePicker.opened = true;
    };


    self.open = function (size) {
        var uibModalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'myModalContent.html',
            controller: 'corretorControllerModalInstance as corretorCtrlModal',
            size: size,
            resolve: {
                items: function () {
                    return $scope.items;
                }
            }
        });

        uibModalInstance.result.then(function (corretorSalvo) {
            if(corretorSalvo != null) {
                self.lastCorretorSaved = corretorSalvo;
            }
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };
    self.findAllCorretorAtivos = function () {
        corretorService.findAllCorretorAtivos().then(
            function sucessFindAllCorretorAtivos(response) {
                self.listCorretor = response.data;
                self.gridOptions.data = self.listCorretor;
            },
            function errorFindAllCorretorAtivos(response) {
                alert("Error - Ocorreu um Erro ao tentar realizar a consulta de corretores.");
            });
    };

    self.findAllCorretorAtivos();

    self.gridOptions = {
        enableSorting: true,
        enableGridMenu: true,
        columnDefs: [
            { name: "Nome Completo", field: 'nomeCorretor', minWidth: 200, width: 250, enableColumnResizing: true },
            { name: "E-Mail", field: 'email', width: '30%', maxWidth: 200, minWidth: 70 },
            { name: "Data de Nascimento", field: 'dataNascimento', width: '20%' }
        ]
    };



} ])