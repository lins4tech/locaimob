/**
 * Created by Lins on 29/12/2015.
 */
angular.module("angle").controller("contratoController", ["$scope", "$uibModal", "$log", "$location", "SweetAlert", "clienteService", "imovelService", function ($scope, $uibModal, $log, $location, SweetAlert, clienteService, imovelService) {
    var self = this;

    self.listPessoas = [];

    self.newContrato = {

    };

    self.imovelSelected = {
        idimovel: 0
    };

    self.pessoaSelected = {
        idPessoa: 0
    };

    self.fiadorSelected = {
        idPessoa: 0
    };

    self.isSemFianca = false;

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
     * Validação de Form
     */
    self.validateEntryFormInquilino = function() {
      if(self.imovelSelected.idimovel > 0) {
          return true;
      }else{
          self.openAlerErrorMessage("É necessário selecionar o IMÓVEL do contrato!");
          return false;
      }
    };

    self.validateEntryFormFianca = function() {
        if(self.validateEntryFormInquilino()) {
            if(self.pessoaSelected.idPessoa > 0) {
                return true;
            }else{
                self.openAlerErrorMessage("É necessário selecionar o LOCATÁRIO/INQUILINO do contrato!");
            }
        }else{
            return false;
        }
    };

    self.validateEntryFormDadosDoContrato = function() {
        if(self.validateEntryFormInquilino() && self.validateEntryFormFianca()) {
            if(self.isSemFianca) {
                return true;
            }else{
                if(self.newContrato.tipoFianca == null) {
                    self.openAlerErrorMessage("É necessário selecionar o Tipo de Fiança do contrato!");
                    return false;
                }
                if(self.newContrato.tipoFianca == "Fiador") {
                    if(self.fiadorSelected.idPessoa > 0){
                        return true;
                    }else{
                        self.openAlerErrorMessage("É necessário selecionar o FIADOR do contrato!");
                        return false;
                    }
                }
                if(self.newContrato.tipoFianca == "Seguro-Fiança") {
                    if(self.newContrato.seguradoraSeguroFianca != null && self.newContrato.seguradoraSeguroFianca.length > 0 && self.newContrato.apoliceSeguroFianca != null && self.newContrato.apoliceSeguroFianca.length > 0) {
                        return true;
                    }else{
                        if(self.newContrato.seguradoraSeguroFianca == null || self.newContrato.seguradoraSeguroFianca.length <= 0) {
                            self.openAlerErrorMessage("É necessário informar o nome da SEGURADORA do Seguro-Fiança!");
                        }else{
                            self.openAlerErrorMessage("É necessário informar o número da APÓLICE do Seguro-Fiança!");
                        }
                        return false;
                    }
                }
                if(self.newContrato.tipoFianca == "Caução") {
                    if(self.newContrato.valorCaucao != null) {
                        return true;
                    }else{
                        self.openAlerErrorMessage("É necessário informar o valor do CAUÇÃO do contrato!");
                        return false;
                    }
                }
                return false;
            }
        }else{
            return false;
        }
    };


    /**
     * Mensagens de Alerts e Erros
     */
    self.openErrorComunicateServer = function () {
        SweetAlert.swal("Desculpe...", "Ocorreu um erro ao tentar tentar conectar no servidor, por favor verifique o seu acesso a internet. " +
            "Caso o problema persista, entre em contato com o administrador do sistema!", "error");
    }

    self.openAlerErrorMessage = function (errorMessage) {
        SweetAlert.swal(errorMessage, null, "error");
    }


}])