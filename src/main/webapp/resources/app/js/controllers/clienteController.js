/**
 * Created by Lins on 15/12/2015.
 */
angular.module("angle").controller("clienteController", ["$scope", "$log", "$location", "SweetAlert", "clienteService", "enderecoService", function ($scope, $log, $location, SweetAlert, clienteService, enderecoService) {
    var self = this;

    self.listEstados = [];
    self.listCidades = [];
    self.listBairros = [];

    self.resultWebServiceCEP = null;
    self.isPreencherByCEP = true;
    self.estadoSelected = null;
    self.cidadeSelected = null;
    self.bairroSelected = null;

    self.newPessoaFisica = {
        estadoCivil: "Solteiro",
        nacionalidade: "Brasileiro",
        endereco: {
            pais: "Brasil"
        }
    };

    self.newPessoaJuridica = {
        endereco: {
            pais: "Brasil"
        }
    };

    self.redirecionarDashboard = function () {
        $location.path("/app/dashboard");
    };

    /* ## START: Métodos que chamam serviços. ##*/
    self.clickSalvarClientePF = function () {
        self.newPessoaFisica.endereco.estado = self.estadoSelected.nomeEstado;
        self.newPessoaFisica.endereco.cidade = self.cidadeSelected.nomeCidade;
        self.newPessoaFisica.endereco.bairro = self.bairroSelected.nomeBairro;
        self.newPessoaFisica.tipoPessoa = "PF";
        clienteService.savePessoaFisica(self.newPessoaFisica).then(
            function sucessSavePessoaFisica(response) {
                self.openConfirmAlertSavePF(response.data.nomeCompleto);
                self.redirecionarDashboard();
            },
            function errorSavePessoaFisica(response) {
                self.openErrorAlertSave();
            }
        )
    };

    self.clickSalvarClientePJ = function () {
        self.newPessoaJuridica.endereco.estado = self.estadoSelected.nomeEstado;
        self.newPessoaJuridica.endereco.cidade = self.cidadeSelected.nomeCidade;
        self.newPessoaJuridica.endereco.bairro = self.bairroSelected.nomeBairro;
        self.newPessoaJuridica.tipoPessoa = "PJ";
        clienteService.savePessoaJuridica(self.newPessoaJuridica).then(
            function sucessSavePessoaJuridica(response) {
                self.openConfirmAlertSavePJ(response.data.razaoSocial);
                self.redirecionarDashboard();
            },
            function errorSavePessoaJuridica(response) {
                self.openErrorAlertSave();
            }
        )
    };

    self.clickFindEnderecoByCep = function (cep) {
        enderecoService.findEnderecoByCep(cep).then(
            function sucessFindAllEstados(response) {
                if (response.data.error == true) {
                    return;
                }
                self.resultWebServiceCEP = response.data;
                if (self.newPessoaFisica.endereco.cep != null) {
                    self.newPessoaFisica.endereco.logradouro = self.resultWebServiceCEP.logradouro;
                }else {
                    self.newPessoaJuridica.endereco.logradouro = self.resultWebServiceCEP.logradouro;
                }
                self.selectEstadoValue(self.resultWebServiceCEP.uf);
                self.changeEstadoSelected();
            },
            function errorFindAllEstados(response) {
                alert("Error - Ocorreu um erro ao obter a lista de estados.");
            }
        );
    };

    self.findListEstados = function () {
        enderecoService.findAllEstados().then(
            function sucessFindAllEstados(response) {
                self.listEstados = response.data;
            },
            function errorFindAllEstados(response) {
                alert("Error - Ocorreu um erro ao obter a lista de estados.");
            }
        );
    };

    self.findListCidadesByEstado = function () {
        if (self.estadoSelected != null) {
            enderecoService.findCidadeByEstado(self.estadoSelected.idEstado).then(
                function sucessFindCidadesByEstado(response) {
                    self.listCidades = response.data;
                    if (self.isPreencherByCEP && self.resultWebServiceCEP != null) {
                        self.selectCidadeValue(self.resultWebServiceCEP.localidade);
                        self.changeCidadeSelected();
                    }
                },
                function errorFindCidadesByEstado(response) {
                    alert("Error - Ocorreu um erro ao obter a lista de cidades.");
                }
            );
        }
    };

    self.findListBairrosByCidade = function () {
        if (self.cidadeSelected != null) {
            enderecoService.findBairroByCidade(self.cidadeSelected.idCidade).then(
                function sucessFindBairrosByCidade(response) {
                    self.listBairros = response.data;
                    if (self.isPreencherByCEP && self.resultWebServiceCEP != null) {
                        self.selectBairroValue(self.resultWebServiceCEP.bairro);
                    }
                },
                function errorFindBairrosByCidade(response) {
                    alert("Error - Ocorreu um erro ao obter a lista de bairros.");
                }
            );
        }
    };

    self.findListEstados();
    /** ## FIM: Métodos que chamam serviços. ## */


    self.changeEstadoSelected = function () {
        self.cidadeSelected = null;
        self.bairroSelected = null;
        if (self.estadoSelected != null) {
            self.findListCidadesByEstado();
        }
    };

    self.changeCidadeSelected = function () {
        self.bairroSelected = null;
        if (self.cidadeSelected != null) {
            self.findListBairrosByCidade();
        }
    };

    self.selectEstadoValue = function (estado) {
        if (estado != null && estado.length > 0 && self.listEstados.length > 0) {
            var index = 0;
            if (estado.length == 2) {
                for (index = 0; index < self.listEstados.length; index++) {
                    if (self.listEstados[index].sigla == estado) {
                        self.estadoSelected = self.listEstados[index];
                        break;
                    }
                }
            } else {
                for (var estadoVO in self.listEstados) {
                    for (index = 0; index < self.listEstados.length; index++) {
                        if (self.listEstados[index].nomeEstado == estado) {
                            self.estadoSelected = self.listEstados[index];
                            break;
                        }
                    }
                }
            }
        }
    };

    self.selectCidadeValue = function (cidade) {
        if (cidade != null && cidade.length > 0 && self.listCidades.length > 0) {
            var index = 0;
            for (index = 0; index < self.listCidades.length; index++) {
                if (self.listCidades[index].nomeCidade == cidade) {
                    self.cidadeSelected = self.listCidades[index];
                    break;
                }
            }
        }
    };

    self.selectBairroValue = function (bairro) {
        if (bairro != null && bairro.length > 0 && self.listBairros.length > 0) {
            var index = 0;
            for (index = 0; index < self.listBairros.length; index++) {
                if (self.listBairros[index].nomeBairro == bairro) {
                    self.bairroSelected = self.listBairros[index];
                    break;
                }
            }
        }
    };

    /** ## START: Usado Apenas para o DatePicker ## */
    self.dateToday = new Date();

    self.statusDatePicker = {
        opened: false
    };

    self.openDatePicker = function ($event) {
        self.statusDatePicker.opened = true;
    };
    /** ## FIM: Usado Apenas para o DatePicker ## */

    /*
     Modal Confirmar Save
     */
    self.openConfirmAlertSavePF = function (nomePessoa) {
        SweetAlert.swal("Cliente Cadastrado com Sucesso!", nomePessoa, "success");
    };

    self.openConfirmAlertSavePJ = function (nomeEmpresa) {
        SweetAlert.swal("Empresa Cadastrada com Sucesso!", nomeEmpresa, "success");
    };

    self.openErrorAlertSave = function () {
        SweetAlert.swal("Desculpe...", "Ocorreu um erro ao tentar cadastrar o cliente, entre em contato com o administrador do Sistema!", "error");
    }

}])