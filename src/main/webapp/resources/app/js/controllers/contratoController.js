/**
 * Created by Lins on 29/12/2015.
 */
angular.module("angle").controller("contratoController", ["$scope", "$uibModal", "$log", "$location", "SweetAlert", "clienteService", "imovelService", function ($scope, $uibModal, $log, $location, SweetAlert, clienteService, imovelService) {
    var self = this;

    self.listPessoas = [];

    self.imovelSelected = {};

    self.pessoaSelected = {
        idpessoa: 0
    };

    self.imovelSelectedEnderecoFull = "";

    self.dateToday = new Date();

    self.statusDatePicker = {
        opened: false
    };

    self.openDatePicker = function ($event) {
        self.statusDatePicker.opened = true;
    };

    self.findListPessoas = function () {
        clienteService.findAllPessoaVO().then(
            function sucessFindAllPessoaVO(response) {
                self.listPessoas = response.data;
            },
            function errorFindAllPessoaVO(response) {
                self.openErrorComunicateServer();
            }
        );
    };

    self.findListPessoas();


    /*
     Modal Selecionar Imóvel
     */
    self.openSelecionarImovel = function (size) {
        $log.info("Abrindo Modal Selecionar Imovel");
        var uibModalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'modalSelecionarImovel.html',
            controller: 'contratoControllerModalInstance as contratoModalInstanceCtrl',
            size: size,
            resolve: {}
        });


        uibModalInstance.result.then(function (imovelSelected) {
            if (imovelSelected != null) {
                self.imovelSelected = imovelSelected;
                self.imovelSelectedEnderecoFull = imovelSelected.endereco.logradouro + ", " + imovelSelected.endereco.bairro
                $log.info("Imóvel Selecionado: " + imovelSelected.idimovel);
            } else {
                $log.info("Nenhum imóvel foi selecionado no Modal!");
            }
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };


    /**
     * Mensagens de Alerts e Erros
     */
    self.openErrorComunicateServer = function () {
        SweetAlert.swal("Desculpe...", "Ocorreu um erro ao tentar tentar conectar no servidor, por favor verifique o seu acesso a internet. " +
            "Caso o problema persista, entre em contato com o administrador do sistema!", "error");
    }


}])