/**
 * Created by Lins on 29/12/2015.
 */
angular.module("angle").controller("contratoController", ["$scope", "$uibModal", "$log", "$location", "SweetAlert", "contratoService", "clienteService", "imovelService", "corretorService", function ($scope, $uibModal, $log, $location, SweetAlert, contratoService, clienteService, imovelService, corretorService) {
    var self = this;

    self.listPessoas = [];

    self.listCorretores = [];

    self.newContrato = {
        porcentagemMultaAtraso: 5,
        porcentagemJurosMensal: 1,
        diaVencimento: 1,
        formaPagamento: "Boleto",
        qtdAluguelMultaDescumprimento: 3,
        principalIndiceReajuste: "IGP-M",
        tipoAluguel: "Residencial"
    };

    self.imovelSelected = {
        idimovel: 0
    };
    //LOCATÁRIO
    self.pessoaSelected = {
        idPessoa: 0
    };

    self.corretorResponsavelSelected = {
        nomeCorretor: "Nenhum Corretor Selecionado",
        idcorretor: 0
    };

    self.fiadorSelected = {
        nome: "Nenhum Fiador Selecionado",
        idPessoa: 0
    };

    self.isSemFianca = false;

    self.dateToday = new Date();

    self.statusDateInicioContratoPicker = {
        opened: false
    };

    self.statusDateTerminoContratoPicker = {
        opened: false
    };

    self.statusDateAssinaturaContratoPicker = {
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
    self.duracaoContratoString = "01A"; //01A11M29D
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

    self.openDateAssinaturaContratoPicker = function () {
        self.statusDateAssinaturaContratoPicker.opened = true;
    };

    self.changeDefaultTerminoDate = function () {
        if (self.newContrato.dataInicioPeriodo != null && self.newContrato.dataInicioPeriodo instanceof Date) {
            self.newContrato.dataFimPeriodo = new Date(self.newContrato.dataInicioPeriodo.getTime());
            self.newContrato.dataFimPeriodo.setFullYear(self.newContrato.dataFimPeriodo.getFullYear() + self.duracaoContratoAnos);
            self.newContrato.dataFimPeriodo.setMonth(self.newContrato.dataFimPeriodo.getMonth() + self.duracaoContratoMeses);
            self.newContrato.dataFimPeriodo.setDate(self.newContrato.dataFimPeriodo.getDate() + self.duracaoContratoDias);
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

    self.changeTipoFiancaContrato = function () {
        self.fiadorSelected = { nome: "Nenhum Fiador Selecionado", idPessoa: 0 };
        self.newContrato.fiador = null;
        self.valorCaucao = null;
        self.newContrato.seguradoraSeguroFianca = null;
        self.newContrato.apoliceSeguroFianca = null;
    };

    self.changeDuracaoDoContrato = function () {
        self.labelDuracaoContrato = "";
        self.duracaoContratoString = "";
        var isQtdAnoSelected = self.duracaoContratoAnos > 0;
        var isQtdMesSelected = self.duracaoContratoMeses > 0;
        var isQtdDiaSelected = self.duracaoContratoDias > 0;
        if (isQtdAnoSelected) {
            if (self.duracaoContratoAnos == 1) {
                self.labelDuracaoContrato = "1 Ano";
                self.duracaoContratoString = "01A";
            } else {
                self.labelDuracaoContrato = self.duracaoContratoAnos + " Anos";
                if(self.duracaoContratoAnos < 10) {
                    self.duracaoContratoString = "0" + self.duracaoContratoAnos + "A";
                }else{
                    self.duracaoContratoString = self.duracaoContratoAnos + "A";
                }
            }
        }
        if (isQtdMesSelected) {
            if (isQtdAnoSelected) {
                self.labelDuracaoContrato = self.labelDuracaoContrato + " e ";
            }
            if (self.duracaoContratoMeses == 1) {
                self.labelDuracaoContrato = self.labelDuracaoContrato + "1 Mês";
                self.duracaoContratoString = self.duracaoContratoString + "01M";
            } else {
                self.labelDuracaoContrato = self.labelDuracaoContrato + self.duracaoContratoMeses + " Meses";
                if(self.duracaoContratoMeses < 10) {
                    self.duracaoContratoString = self.duracaoContratoString + "0" + self.duracaoContratoMeses + "M";
                }else{
                    self.duracaoContratoString = self.duracaoContratoString + self.duracaoContratoMeses + "M";
                }
            }
        }
        if (isQtdDiaSelected) {
            if (isQtdAnoSelected || isQtdMesSelected) {
                self.labelDuracaoContrato = self.labelDuracaoContrato + " e ";
            }
            if (self.duracaoContratoDias == 1) {
                self.labelDuracaoContrato = self.labelDuracaoContrato + self.duracaoContratoDias + "1 Dia";
                self.duracaoContratoString = self.duracaoContratoString + "01D";
            } else {
                self.labelDuracaoContrato = self.labelDuracaoContrato + self.duracaoContratoDias + " Dias";
                if(self.duracaoContratoDias < 10) {
                    self.duracaoContratoString = self.duracaoContratoString + "0" + self.duracaoContratoDias + "D";
                }else{
                    self.duracaoContratoString = self.duracaoContratoString + self.duracaoContratoDias + "D";
                }
            }
        }
        self.changeDefaultTerminoDate(); //Atualizar Data de Termino
    };

    self.getCpfOrCnpjWithMask = function(cpfOrCnpj) {
        if(cpfOrCnpj != null) {
            if(cpfOrCnpj.length == 11) {
                return cpfOrCnpj.slice(0,3)+"."+cpfOrCnpj.slice(3,6)+"."+cpfOrCnpj.slice(6,9)+"-"+cpfOrCnpj.slice(9);
            }
            if(cpfOrCnpj.length == 14) {
                return cpfOrCnpj.slice(0,2)+"."+cpfOrCnpj.slice(2,5)+"."+cpfOrCnpj.slice(5,8)+"/"+cpfOrCnpj.slice(8,12)+"-"+cpfOrCnpj.slice(12);
            }
            return cpfOrCnpj;
        }
        return "";
    };

    self.getEnderecoImovelFull = function(endereco) {
        if(endereco != null) {
            return endereco.logradouro + ", " + endereco.bairro;
        }
        return "";
    };

    self.changeIsSemCorretor = function () {
        if (self.isSemCorretor) {
            self.corretorResponsavelSelected = {idcorretor: 0};
        }
    };

    /*
     ##FIM DE FUNÇÕES/MÉTODOS UTILITÁRIOS##
     */


    /**
     * ## INÍCIO DE REQUISIÇÔES/SOLICITAÇÕES DE SERVIÇOS
     */

    self.saveNewContrato = function () {
        self.newContrato.locatario = {idpessoa: self.pessoaSelected.idPessoa};
        self.newContrato.imovel = self.imovelSelected;
        if(self.isSemFianca) {
            self.newContrato.tipoFianca = null;
            self.newContrato.fiador = null;
            self.valorCaucao = null;
            self.newContrato.seguradoraSeguroFianca = null;
            self.newContrato.apoliceSeguroFianca = null;
        }else{
            if(self.newContrato.tipoFianca == "Fiador" && self.fiadorSelected.idPessoa > 0){
                self.newContrato.fiador = {idpessoa: self.fiadorSelected.idPessoa};
            }
        }
        if(self.fiadorSelected.idPessoa > 0 && self.isSemFianca == false) {
            self.newContrato.fiador = {idpessoa: self.fiadorSelected.idPessoa};
        }
        if(self.corretorResponsavelSelected.idPessoa > 0 && self.isSemCorretor == false) {
            self.newContrato.corretor = {idcorretor: self.corretorResponsavelSelected.idcorretor};
        }
        self.newContrato.duracaoContratoString = self.duracaoContratoString;
        if(self.validateFormToSaveNewContrato()) {
            contratoService.saveContrato(self.newContrato).then(
                function sucessSaveContrato(response) {
                    self.openConfirmAlertSaveContrato();
                },
                function errorSaveContrato(response) {
                    self.openErrorComunicateServer();
                }
            );
        }
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
                $log.info("Imóvel Selecionado: " + imovelSelected.idimovel);
            } else {
                $log.info("Nenhum imóvel foi selecionado no Modal!");
            }
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };

    /**
     * Validar entrada no FormInquilino
     * @returns {boolean}
     */
    self.validateEntryFormInquilino = function () {
        if (self.imovelSelected.idimovel > 0) {
            return true;
        } else {
            self.openAlertErrorMessage("É necessário selecionar o IMÓVEL do contrato!");
            return false;
        }
    };

    /**
     * Validar entrada no FormFianca
     * @returns {boolean}
     */
    self.validateEntryFormFianca = function () {
        if (self.validateEntryFormInquilino()) {
            if (self.pessoaSelected.idPessoa > 0) {
                return true;
            } else {
                self.openAlertErrorMessage("É necessário selecionar o LOCATÁRIO/INQUILINO do contrato!");
            }
        } else {
            return false;
        }
    };

    /**
     * Validar entrada no FormDadosDoContrato
     * @returns {boolean}
     */
    self.validateEntryFormDadosDoContrato = function () {
        if (self.validateEntryFormInquilino() && self.validateEntryFormFianca()) {
            if (self.isSemFianca) {
                return true;
            } else {
                if (self.newContrato.tipoFianca == null) {
                    self.openAlertErrorMessage("É necessário selecionar o Tipo de Fiança do contrato!");
                    return false;
                }
                if (self.newContrato.tipoFianca == "Fiador") {
                    if (self.fiadorSelected.idPessoa > 0) {
                        return true;
                    } else {
                        self.openAlertErrorMessage("É necessário selecionar o FIADOR do contrato!");
                        return false;
                    }
                }
                if (self.newContrato.tipoFianca == "Seguro-Fiança") {
                    if (self.newContrato.seguradoraSeguroFianca != null && self.newContrato.seguradoraSeguroFianca.length > 0 && self.newContrato.apoliceSeguroFianca != null && self.newContrato.apoliceSeguroFianca.length > 0) {
                        return true;
                    } else {
                        if (self.newContrato.seguradoraSeguroFianca == null || self.newContrato.seguradoraSeguroFianca.length <= 0) {
                            self.openAlertErrorMessage("É necessário informar o nome da SEGURADORA do Seguro-Fiança!");
                        } else {
                            self.openAlertErrorMessage("É necessário informar o número da APÓLICE do Seguro-Fiança!");
                        }
                        return false;
                    }
                }
                if (self.newContrato.tipoFianca == "Caução") {
                    if (self.newContrato.valorCaucao != null) {
                        return true;
                    } else {
                        self.openAlertErrorMessage("É necessário informar o valor do CAUÇÃO do contrato!");
                        return false;
                    }
                }
                return false;
            }
        } else {
            return false;
        }
    };

    self.validateFormToSaveNewContrato = function () {
        if(self.validateEntryFormInquilino() && self.validateEntryFormFianca() && self.validateEntryFormDadosDoContrato()) {
            var validationMessageError = "";
            if(self.corretorResponsavelSelected.idcorretor == 0 && self.isSemCorretor == false) {
                validationMessageError = validationMessageError + "*Corretor Responsável<br>";
            }
            if(self.newContrato.valorBaseAluguel == null || self.newContrato.valorBaseAluguel <= 0 ) {
                validationMessageError = validationMessageError + "*Valor Do Aluguel<br>";
            }
            if(self.newContrato.dataInicioPeriodo == null) {
                validationMessageError = validationMessageError + "*Data de Início do Contrato<br>";
            }
            if(self.newContrato.dataAssinatura == null) {
                validationMessageError = validationMessageError + "*Data da Assinatura do Contrato<br>";
            }
            if(validationMessageError != "") {
                self.openAlertValidationMessage(validationMessageError);
                return false;
            }
        }else{
            return false;
        }
        return true;
    };


    /**
     * Mensagens de Alerts e Erros
     */
    self.openErrorComunicateServer = function () {
        SweetAlert.swal("Desculpe...", "Ocorreu um erro ao tentar tentar conectar no servidor, por favor verifique o seu acesso a internet. " +
            "Caso o problema persista, entre em contato com o administrador do sistema!", "error");
    };

    self.openAlertErrorMessage = function (errorMessage) {
        SweetAlert.swal(errorMessage, null, "error");
    };

    self.openAlertValidationMessage = function (validationMessageFields) {
        //SweetAlert.swal("Por favor, preencha os seguintes campos:", validationMessageFields, "error");
        validationMessageFields = '<span style="color:#ff0002">' + validationMessageFields + '<span>';
        SweetAlert.swal({
            title: "Por favor, preencha os seguintes campos:",
            text: validationMessageFields,
            type: "error",
            html: true })
    };


    self.openConfirmAlertSaveContrato = function () {
        SweetAlert.swal("Contrato Cadastrado com Sucesso!", "Informações do Contrato", "success");
    };


}])