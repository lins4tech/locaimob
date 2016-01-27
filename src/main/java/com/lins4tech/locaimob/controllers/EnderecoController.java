package com.lins4tech.locaimob.controllers;

import com.lins4tech.locaimob.entities.vo.BairroVO;
import com.lins4tech.locaimob.entities.vo.CidadeVO;
import com.lins4tech.locaimob.entities.vo.EstadoVO;
import com.lins4tech.locaimob.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Lins on 09/01/2016.
 */
@RestController
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @RequestMapping(value = "endereco/findAllEstado", method = RequestMethod.GET)
    private List<EstadoVO> findAllEstado() {
        return enderecoRepository.findAllEstados();
    }

    @RequestMapping(value = "endereco/findCidadeByEstado", method = RequestMethod.GET)
    private List<CidadeVO> findCidadeByEstado(Integer idEstado) {
        return enderecoRepository.findCidadeByEstado(idEstado);
    }

    @RequestMapping(value = "endereco/findBairroByCidade", method = RequestMethod.GET)
    private List<BairroVO> findBairroByCidade(Integer idCidade) {
        return enderecoRepository.findBairroByCidade(idCidade);
    }

}
