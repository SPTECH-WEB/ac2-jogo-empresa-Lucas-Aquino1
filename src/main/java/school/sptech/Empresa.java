package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.JogoInvalidoException;
import school.sptech.exception.JogoNaoEncontradoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Empresa {
    private String nome;
    private List<Jogo> jogos;
    public static final LocalDate dataDeHoje = LocalDate.now();

    public Empresa() {
    }


    public Empresa(String nome, List<Jogo> jogos) {
        this.nome = nome;
        this.jogos = new ArrayList<>(jogos);
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void adicionarJogo(Jogo jogo) throws JogoInvalidoException {

        if(jogo == null){
            throw new JogoInvalidoException("ERRO: O jogo não pode ser nulo");
        }
        if(jogo.getCodigo() == null || jogo.getCodigo().isEmpty()){
            throw new JogoInvalidoException("ERRO: O código do jogo não pode ser nulo ou vazio");
        }
        if (jogo.getNome() == null || jogo.getNome().isEmpty()){
            throw new JogoInvalidoException("ERRO: O nome do jogo não pode ser nulo ou vazio");
        }
        if(jogo.getGenero() == null || jogo.getGenero().isEmpty()){
            throw new JogoInvalidoException("ERRO: O gênero do jogo não pode ser nulo ou vazio");
        }
        if(jogo.getPreco() == null || jogo.getPreco() <= 0){
            throw new JogoInvalidoException("ERRO: O preço do jogo não pode ser nulo ou menor que 0");
        }
        if(jogo.getAvaliacao() < 0 || jogo.getAvaliacao() > 5){
            throw new JogoInvalidoException("ERRO: A avaliação do jogo deve estar dentro de um intervalo de 0 a 5");
        }
        if(jogo.getDataLancamento() == null || jogo.getDataLancamento().isAfter(dataDeHoje)){
            throw new JogoInvalidoException("ERRO: A data de lançamento não pode ser nula ou futura");
        }

        jogos.add(jogo);


    }

    public Jogo buscarJogoPorCodigo(String codigo) throws ArgumentoInvalidoException, JogoNaoEncontradoException {
        if(codigo == null ||  codigo.equals(" ") || codigo.isEmpty()){
            throw new ArgumentoInvalidoException("ERRO: Código inválido");
        }

        for (Jogo j : jogos){
            if(j.getCodigo().equals(codigo)){
                return j;
            } else{
                throw new JogoNaoEncontradoException("ERRO: O jogo não foi encontrado");
            }
        }

        return null;
    }


    public void removerJogoPorCodigo(String codigo) throws ArgumentoInvalidoException, JogoNaoEncontradoException{
        if(codigo == null || codigo.equals(" ") || codigo.isEmpty()){
            throw new ArgumentoInvalidoException("ERRO: Código inválido");
        }

        for(int i = 0; i < jogos.toArray().length; i++){
            if(buscarJogoPorCodigo(codigo).getCodigo().equals(codigo)){
                jogos.remove(i);
            } else {
                throw new JogoNaoEncontradoException("ERRO: O jogo não foi encontrado");
            }
        }

    }

    public Jogo buscarJogoComMelhorAvaliacao() throws JogoNaoEncontradoException{
        Jogo maiorAvaliacao = null;

        for (Jogo j : jogos) {
            if (j.getAvaliacao() == 5) {
                j = maiorAvaliacao;
            } else{
                throw new JogoNaoEncontradoException("ERRO: Jogo não encontrado");
            }

        }
            return maiorAvaliacao;

    }
    public List<Jogo> buscarJogoPorPeriodo(LocalDate dataInicio, LocalDate dataFim) throws ArgumentoInvalidoException {

        if (dataInicio == null || dataFim == null || dataInicio.isAfter(dataFim)) {
            throw new ArgumentoInvalidoException("ERRO: Argumentos invalidos para a data");
        }
            System.out.println("Lista de Jogos no periodo de " + dataInicio + " a " + dataFim + ".");

            Jogo jogosEncontrados = null;
            for (Jogo j : jogos) {
                if (j.getDataLancamento().isAfter(dataInicio) && j.getDataLancamento().isBefore(dataFim)) {
                    System.out.println(j);

                    jogosEncontrados = j;

                }
                    return Collections.singletonList(jogosEncontrados);
            }
        return List.of(jogosEncontrados);
    }

}
