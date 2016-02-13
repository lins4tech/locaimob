/**
 * Created by Lins on 29/12/2015.
 */
angular.module("angle").controller("contratoController", ["$scope", "$uibModal", "$log", "$location", "SweetAlert", "clienteService", "imovelService", "corretorService", function ($scope, $uibModal, $log, $location, SweetAlert, clienteService, imovelService, corretorService) {
    var self = this;

    self.listPessoas = [];

    self.listCorretores = [];

    self.newContrato = {
        porcentagemMultaAtraso: 5,
        porcentagemJurosMensal: 1,
        diaVencimento: 1,
        formaPagamento: "Boleto",
        qtdAluguelMultaDescumprimento: 3,
        principalIndiceReajuste: "IGP-M"
    };

    self.imovelSelected = {
        idimovel: 0
    };

    self.pessoaSelected = {
        idPessoa: 0
    };

    self.corretorResponsavelSelected = {
        idPessoa: 0
    };

    self.fiadorSelected = {
        idPessoa: 0
    };

    self.isSemFianca = false;

    self.imovelSelectedEnderecoFull = "";

    self.dateToday = new Date();

    self.statusDateInicioContratoPicker = {
        opened: false
    };

    self.statusDateTerminoContratoPicker = {
        opened: false
    };

    self.duracaoContratoAnos = 1;
    self.duracaoContratoMeses = 0;
    self.duracaoContratoDias = 0;
    self.listDiasVencimento = [01, 05, 10, 15, 20, 25];
    self.listQtdAnosDuracao = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30];
    self.listQtdMesesDuracao = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11];
    self.listQtdDiasDuracao = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29];
    self.labelDuracaoContrato = "1 Ano";
    self.formasDePagamento = ["Boleto", "Transferência Bancária", "Em Espécie", "Cheques"];
    self.listQtdMultaPorDescumprimento = [0, 1, 2, 3];
    self.listIndicesReajuste = ["IGP-M", "INPC", "IPC"];
    self.isSemCorretor = false;
    /*
     ##FUNÇÕES/MÉTODOS UTILITÁRIOS USADOS NA INTERFACE(HTML)##
     */
    self.openDateInicioContratoPicker = function ($event) {
        self.statusDateInicioContratoPicker.opened = true;
    };

    self.openDateTerminoContratoPicker = function ($event) {
        self.statusDateTerminoContratoPicker.opened = true;
    };

    self.changeDefaultTerminoDate = function () {
        if (self.newContrato.dataInicioPeriodo != null && self.newContrato.dataInicioPeriodo instanceof Date) {
            self.newContrato.dataTerminoPeriodo = new Date(self.newContrato.dataInicioPeriodo.getTime());
            self.newContrato.dataTerminoPeriodo.setFullYear(self.newContrato.dataTerminoPeriodo.getFullYear() + self.duracaoContratoAnos);
            self.newContrato.dataTerminoPeriodo.setMonth(self.newContrato.dataTerminoPeriodo.getMonth() + self.duracaoContratoMeses);
            self.newContrato.dataTerminoPeriodo.setDate(self.newContrato.dataTerminoPeriodo.getDate() + self.duracaoContratoDias);

        }

    };

    self.clickAddNumericStepPorcentagemMulta = function (step, maxValuePermit) {
        if (self.newContrato.porcentagemMultaAtraso < maxValuePermit) {
            self.newContrato.porcentagemMultaAtraso = self.newContrato.porcentagemMultaAtraso + step;
        }
    };
    self.clickDecreaseNumericStepPorcentagemMulta = function (step, minValuePermit) {
        if (self.newContrato.porcentagemMultaAtraso > minValuePermit) {
            self.newContrato.porcentagemMultaAtraso = self.newContrato.porcentagemMultaAtraso - step;
        }
    };

    self.clickAddNumericStepJurosMensal = function (step, maxValuePermit) {
        if (self.newContrato.porcentagemJurosMensal < maxValuePermit) {
            self.newContrato.porcentagemJurosMensal = self.newContrato.porcentagemJurosMensal + step;
        }
    };
    self.clickDecreaseNumericStepJurosMensal = function (step, minValuePermit) {
        if (self.newContrato.porcentagemJurosMensal > minValuePermit) {
            self.newContrato.porcentagemJurosMensal = self.newContrato.porcentagemJurosMensal - step;
        }
    };

    self.changeDuracaoDoContrato = function () {
        self.labelDuracaoContrato = "";
        var isQtdAnoSelected = self.duracaoContratoAnos > 0;
        var isQtdMesSelected = self.duracaoContratoMeses > 0;
        var isQtdDiaSelected = self.duracaoContratoDias > 0;
        if (isQtdAnoSelected) {
            if (self.duracaoContratoAnos == 1) {
                self.labelDuracaoContrato = "1 Ano";
            } else {
                self.labelDuracaoContrato = self.duracaoContratoAnos + " Anos";
            }
        }
        if (isQtdMesSelected) {
            if (isQtdAnoSelected) {
                self.labelDuracaoContrato = self.labelDuracaoContrato + " e ";
            }
            if (self.duracaoContratoMeses == 1) {
                self.labelDuracaoContrato = self.labelDuracaoContrato + "1 Mês";
            } else {
                self.labelDuracaoContrato = self.labelDuracaoContrato + self.duracaoContratoMeses + " Meses";
            }
        }
        if (isQtdDiaSelected) {
            if (isQtdAnoSelected || isQtdMesSelected) {
                self.labelDuracaoContrato = self.labelDuracaoContrato + " e ";
            }
            if (self.duracaoContratoDias == 1) {
                self.labelDuracaoContrato = self.labelDuracaoContrato + self.duracaoContratoDias + "1 Dia";
            } else {
                self.labelDuracaoContrato = self.labelDuracaoContrato + self.duracaoContratoDias + " Dias";
            }
        }
        self.changeDefaultTerminoDate(); //Atualizar Data de Termino
    };

    self.changeIsSemCorretor = function () {
        if (self.isSemCorretor) {
            self.corretorResponsavelSelected = {idPessoa: 0};
        }
    };

    /*
     ##FIM DE FUNÇÕES/MÉTODOS UTILITÁRIOS##
     */
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

    self.findListCorretor = function () {
        corretorService.findAllCorretorAtivos().then(
            function sucessFindListCorretor(response) {
                self.listCorretores = response.data;
            },
            function errorFindListCorretor(response) {
                self.openErrorComunicateServer();
            }
        );
    };


    self.findListPessoas();
    self.findListCorretor();


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
    self.validateEntryFormInquilino = function () {
        if (self.imovelSelected.idimovel > 0) {
            return true;
        } else {
            self.openAlerErrorMessage("É necessário selecionar o IMÓVEL do contrato!");
            return false;
        }
    };

    self.validateEntryFormFianca = function () {
        if (self.validateEntryFormInquilino()) {
            if (self.pessoaSelected.idPessoa > 0) {
                return true;
            } else {
                self.openAlerErrorMessage("É necessário selecionar o LOCATÁRIO/INQUILINO do contrato!");
            }
        } else {
            return false;
        }
    };

    self.validateEntryFormDadosDoContrato = function () {
        if (self.validateEntryFormInquilino() && self.validateEntryFormFianca()) {
            if (self.isSemFianca) {
                return true;
            } else {
                if (self.newContrato.tipoFianca == null) {
                    self.openAlerErrorMessage("É necessário selecionar o Tipo de Fiança do contrato!");
                    return false;
                }
                if (self.newContrato.tipoFianca == "Fiador") {
                    if (self.fiadorSelected.idPessoa > 0) {
                        return true;
                    } else {
                        self.openAlerErrorMessage("É necessário selecionar o FIADOR do contrato!");
                        return false;
                    }
                }
                if (self.newContrato.tipoFianca == "Seguro-Fiança") {
                    if (self.newContrato.seguradoraSeguroFianca != null && self.newContrato.seguradoraSeguroFianca.length > 0 && self.newContrato.apoliceSeguroFianca != null && self.newContrato.apoliceSeguroFianca.length > 0) {
                        return true;
                    } else {
                        if (self.newContrato.seguradoraSeguroFianca == null || self.newContrato.seguradoraSeguroFianca.length <= 0) {
                            self.openAlerErrorMessage("É necessário informar o nome da SEGURADORA do Seguro-Fiança!");
                        } else {
                            self.openAlerErrorMessage("É necessário informar o número da APÓLICE do Seguro-Fiança!");
                        }
                        return false;
                    }
                }
                if (self.newContrato.tipoFianca == "Caução") {
                    if (self.newContrato.valorCaucao != null) {
                        return true;
                    } else {
                        self.openAlerErrorMessage("É necessário informar o valor do CAUÇÃO do contrato!");
                        return false;
                    }
                }
                return false;
            }
        } else {
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