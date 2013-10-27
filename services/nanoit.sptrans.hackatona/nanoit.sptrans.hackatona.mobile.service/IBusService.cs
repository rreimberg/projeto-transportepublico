using System.ServiceModel;
using System.ServiceModel.Web;
using System.ServiceModel.Channels;

namespace nanoit.sptrans.hackatona.service
{
    [ServiceContract]
    public interface IBusService
    {
        [WebGet(UriTemplate = "PesquisaPontosPorCoordenadas/latitude/{latitude}/longitude/{longitude}/direction/{direction}/distanceMax/{distanceMax}", ResponseFormat = WebMessageFormat.Json)]
        Message PesquisaPontosPorCoordenadas(string latitude, string longitude, string direction, string distanceMax);

        [WebGet(UriTemplate = "PesquisaPrefixoPorPonto/stopId/{stopId}", ResponseFormat = WebMessageFormat.Json)]
        Message PesquisaPrefixoPorPonto(string stopId);

        [OperationContract]
        [WebGet(UriTemplate = "PesquisarLinha/Linha/{Linha}", ResponseFormat = WebMessageFormat.Json)]
        Message PesquisarLinha(string Linha);

        [OperationContract]
        [WebGet(UriTemplate = "PesquisarPosicaoOnibusLinha/OlhoVivoId/{OlhoVivoId}/Empresa/{Empresa}/Sentido/{Sentido}", ResponseFormat = WebMessageFormat.Json)]
        Message PesquisarPosicaoOnibusLinha(string OlhoVivoId, string Empresa, string Sentido);

        [OperationContract]
        [WebGet(UriTemplate = "ChecarPosicaoOnibusLinha", ResponseFormat = WebMessageFormat.Json)]
        void ChecarPosicaoOnibusLinha();

        [OperationContract]
        [WebGet(UriTemplate = "PesquisarItinerario/OlhoVivoId/{OlhoVivoId}", ResponseFormat = WebMessageFormat.Json)]
        Message PesquisarItinerario(string OlhoVivoId);

        [OperationContract]
        [WebGet(UriTemplate = "PesquisarLinhaRota/OlhoVivoId/{OlhoVivoId}/Empresa/{Empresa}", ResponseFormat = WebMessageFormat.Json)]
        Message PesquisarLinhaRota(string OlhoVivoId,string Empresa);

        [OperationContract]
        [WebGet(UriTemplate = "PesquisarOnibusTipo/Prefixo/{Prefixo}", ResponseFormat = WebMessageFormat.Json)]
        Message PesquisarOnibusTipo(string Prefixo);

        [OperationContract]
        [WebGet(UriTemplate = "PesquisarLinhaDetalhe/OlhoVivoId/{OlhoVivoId}", ResponseFormat = WebMessageFormat.Json)]
        Message PesquisarLinhaDetalhe(string OlhoVivoId);

        [OperationContract]
        [WebGet(UriTemplate = "PesquisarPontosPorLinha/Prefixo/{Prefixo}", ResponseFormat = WebMessageFormat.Json)]
        Message PesquisarPontosPorLinha(string Prefixo);

        [OperationContract]
        [WebGet(UriTemplate = "GetBusStopDistance/OlhoVivoId/{OlhoVivoId}/Empresa/{Empresa}/Sentido/{Sentido}/StopId/{StopId}", ResponseFormat = WebMessageFormat.Json)]
        Message GetBusStopDistance(string OlhoVivoId, string Empresa, string Sentido, string StopId);

        [OperationContract]
        [WebGet(UriTemplate = "GetTweeter/Perfil/{Perfil}", ResponseFormat = WebMessageFormat.Json)]
        Message GetTweeter(string Perfil);
    }
}
