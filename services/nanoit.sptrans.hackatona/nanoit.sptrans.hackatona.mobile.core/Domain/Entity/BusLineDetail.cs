using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    public class BusLineDetailList
    {
        public string CodigoLinhaInfotrans { get; set; }
        public int CodigoLinhaTipoInfotrans { get; set; }
        public double Extensao { get; set; }
        public int IntervaloMedio { get; set; }
        public string OperacaoDiaUtil { get; set; }
        public string OperacaoDomingo { get; set; }
        public string OperacaoSabado { get; set; }
    }

    public class BusLineDetail
    {
        public List<BusLineDetailList> CarregaDetalhesLinhaResult { get; set; }
    }
}
