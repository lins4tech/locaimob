/**
 * Created by Lins on 14/12/2015.
 */
angular.module("angle").controller("corretorController", ["$scope", "$uibModal", "$log", "corretorService", "SweetAlert", function($scope, $uibModal, $log, corretorService, SweetAlert) {

    var self = this;

    self.newCorretor = {};

    self.listCorretor = [];

    self.dateToday = new Date();

    self.statusDatePicker = {
        opened: false
    };

    self.openDatePicker = function($event) {
        self.statusDatePicker.opened = true;
    };

    /**
     * ## Chamandas aos Services
     */

    self.saveCorretor = function () {
        if (self.validateFormSaveCorretor()) {
            corretorService.saveCorretor(self.newCorretor).then(
                function sucessSaveCorretor(response) {
                    SweetAlert.swal("Corretor cadastrado com Sucesso!", response.data.nomeCorretor, "success");
                },
                function errorSaveCorretor(response) {
                    self.openErrorAlertSave();
                }
            );
        }
    };

    self.findAllCorretorAtivos = function () {
        corretorService.findAllCorretorAtivos().then(
            function sucessFindAllCorretorAtivos(response) {
                self.listCorretor = response.data;
                self.gridOptions.data = self.listCorretor;
            },
            function errorFindAllCorretorAtivos(response) {
                alert("Error - Ocorreu um Erro ao tentar realizar a consulta de corretores.");
            }
        );
    };

    self.validateFormSaveCorretor = function () {
        var validationMessageError = "";
        if(self.newCorretor.nomeCorretor == null || self.newCorretor.nomeCorretor.length == 0) {
            validationMessageError = "*Nome do Corretor<br>";
        }
        if(self.newCorretor.email == null || self.newCorretor.email.length == 0) {
            validationMessageError = validationMessageError + "*E-mail<br>";
        }

        if(self.newCorretor.dataNascimento == null) {
            validationMessageError = validationMessageError + "*Data de Nascimento";
        }
        if(validationMessageError.length > 0) {
            self.openAlertValidationMessage(validationMessageError);
            return false;
        }
        return true;
    };

    self.openAlertValidationMessage = function (validationMessageFields) {
        validationMessageFields = '<span style="color:#ff0002">' + validationMessageFields + '<span>';
        SweetAlert.swal({
            title: "Por favor, preencha os seguintes campos corretamente:",
            text: validationMessageFields,
            type: "error",
            html: true })
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

    self.openErrorAlertSave = function () {
        SweetAlert.swal("Desculpe...", "Ocorreu um erro ao tentar cadastrar o corretor. Por favor, entre em contato com o administrador do Sistema!", "error");
    };



} ])