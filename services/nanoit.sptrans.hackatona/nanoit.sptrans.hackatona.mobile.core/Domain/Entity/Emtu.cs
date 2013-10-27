using System.Collections.Generic;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    public class Ponto
    {
        public int id { get; set; }
        public double latitude { get; set; }
        public double longitude { get; set; }
        public int raio { get; set; }
        public string endereco { get; set; }
    }

    public class Rota
    {
        public int id { get; set; }
        public string codigoLinha { get; set; }
        public string sentido { get; set; }
        public List<Ponto> pontos { get; set; }
        public string destino { get; set; }
        public string encode { get; set; }
    }

    public class Veiculo
    {
        public object serialModulo { get; set; }
        public object consorcio { get; set; }
        public string empresa { get; set; }
        public object placa { get; set; }
        public string prefixo { get; set; }
        public object status { get; set; }
        public object numSequencia { get; set; }
        public string codigoLinha { get; set; }
        public string sentidoLinha { get; set; }
        public int idRota { get; set; }
        public object idLinhaPlanejada { get; set; }
        public object codigoLinhaPlanejada { get; set; }
        public object fornecedor { get; set; }
        public object telefoneSimcard { get; set; }
        public object idSimcard { get; set; }
        public object qtdTransmissoes { get; set; }
        public object qtdTransmissoesUltimoDia { get; set; }
        public object qtdTransmGPSInvalidoUltimoDia { get; set; }
        public object transmitiuSeq { get; set; }
        public object transmitiuHoje { get; set; }
        public object kmOperacionalUltimoDia { get; set; }
        public object kmUltimoDia { get; set; }
        public object dataUltimaTransmissao { get; set; }
        public object dataUltimoGpsValido { get; set; }
        public double latitude { get; set; }
        public double longitude { get; set; }
        public object sinalGSM { get; set; }
        public object gprsOnline { get; set; }
        public object qtdSatelitesGPS { get; set; }
        public object gpsValido { get; set; }
        public int seqPonto { get; set; }
        public object alerta { get; set; }
        public object alertaPanico { get; set; }
        public object alertaVelocidade { get; set; }
        public object alertaTempoParado { get; set; }
        public object alertaSemPontosCarregados { get; set; }
        public object alertaForaDeRota { get; set; }
    }

    public class Linha
    {
        public int id { get; set; }
        public string codigo { get; set; }
        public List<Rota> rotas { get; set; }
        public List<Veiculo> veiculos { get; set; }
    }

    public class LinhaRootObject
    {
        public List<Linha> linhas { get; set; }
    }
}
