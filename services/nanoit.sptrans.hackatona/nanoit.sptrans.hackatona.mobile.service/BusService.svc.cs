using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel.Web;
using System.Text;
using System.Web;
using System.ServiceModel.Channels;
using System.Net;
using System.Configuration;
using System.IO;
using System.Runtime.Serialization.Json;
using System.Drawing.Imaging;
using System.Drawing;
using System.Web.Hosting;
using nanoit.sptrans.hackatona.core.domain.extensions;
using nanoit.sptrans.hackatona.core.application;
using nanoit.sptrans.hackatona.core.domain.entity;
using nanoit.sptrans.hackatona.core.domain.service;
using nanoit.sptrans.hackatona.mobile.core.Domain.Service.Service;
using nanoit.sptrans.hackatona.mobile.core.Domain.Entity;
using System.Web.Script.Serialization;

namespace nanoit.sptrans.hackatona.service
{
    public class BusService : IBusService
    {
        string DeviceID = "teste";
        int ApplicationId = 1;

        public Message PesquisaPontosPorCoordenadas(string latitude, string longitude, string direction, string distanceMax)
        {
            var result = new StopApplication().GetStops(latitude, longitude, Convert.ToInt32(direction),Convert.ToInt32(distanceMax));

            return RetornarJson(result);
        }

        public Message PesquisaPrefixoPorPonto(string stopId)
        {
            var result = new StopApplication().GetLineByStop(Convert.ToInt32(stopId));
            return RetornarJson(result);
        }


        


        public Message PesquisarLinha(string Linha)
        {
            Linha = Linha.Replace(",", ".");
            try
            {
                try
                {
                    new LogSearchApplication().SaveOrUpdateLogSearch(new LogSearch()
                    {
                        Application = new Application { Id = ApplicationId },
                        Device = new Device { Id = DeviceID },
                        Text = Linha,
                        DateTimeOfCreation = DateTimeExtensions.UTCBrazilDateTime
                    });
                }
                catch (Exception ex)
                {
                    new Log().ExceptionLog(ex);
                }

                var linhas = new LineApplication().FindManyLine(Linha);

                foreach (var linha in linhas)
                {
                    if (linha.Company == 2)
                        linha.OlhoVivoId = linha.OlhoVivoId + "-" + linha.Direction.ToString();
                }
                return RetornarJson(linhas);
            }
            catch (Exception ex)
            {
                new Log().ExceptionLog(ex);
                return null;
            }
        }

        public void ChecarPosicaoOnibusLinha()
        {
            var OlhoVivoIds = new string[] { "46", "111", "1256", "1437", "1345", "1585", "2029",  };
            Boolean isSpTransServiceOk = false;

            for (int i = 0; i < OlhoVivoIds.Length; i ++)
            {
                try
                {
                    var url = new Uri(string.Format(ConfigurationManager.AppSettings["url_posicaoOnibus"], i));
                    var urlSessao = new Uri(string.Format(ConfigurationManager.AppSettings["url_posicaoOnibusSessao"], i));

                    var vResult = GetRequest(urlSessao.AbsoluteUri, url.AbsoluteUri);

                    var coordenates = setJsonToObject<BusPositionSPTransReturn>(vResult).BusPosition;

                    if (coordenates != null && coordenates.Count > 0)
                    {
                        isSpTransServiceOk = true;
                        break;
                    }
                }
                catch (Exception ex)
                {

                }
            }

            new ApplicationWarningApplication().ChecarPosicaoOnibusLinha(isSpTransServiceOk);

        }

        public Message PesquisarLinhaRota(string OlhoVivoId,string empresa)
        {
            try
            {
                var routerReturn = new RouterReturn();
                routerReturn.Coordenates = new List<Coordenates>();
                if (empresa == "1")
                {
                    routerReturn.Type = (int)RouterReturnType.Rota;
                    var routers = new LineRouteApplication().FindManyLineRoute(OlhoVivoId);

                    foreach (var item in routers)
                    {
                        routerReturn.Coordenates.Add(new Coordenates
                            {
                                Latitude = item.Latitude.ToCoordenate(),
                                Longitude = item.Longitude.ToCoordenate()
                            });
                    }

                    return RetornarJson(routerReturn);
                }

                routerReturn.Type = (int)RouterReturnType.Pontos;

                var Sentido = OlhoVivoId.Split('-')[1];

                var emtuCoordenates = PegaPosicaoRotaEmtu(OlhoVivoId);

                var rotaEmtu = (from rotas in emtuCoordenates.linhas.FirstOrDefault().rotas
                           from pontos in rotas.pontos
                                where rotas.sentido == (Sentido == "1" ? "ida" : "volta")
                           select new Coordenates
                               {
                                   Latitude = pontos.latitude.ToString().ToCoordenate(),
                                   Longitude = pontos.longitude.ToString().ToCoordenate()
                               });

                routerReturn.Coordenates.AddRange(rotaEmtu);

                return RetornarJson(routerReturn);
            }
            catch (Exception ex)
            {
                new Log().ExceptionLog(ex);
                return null;
            }
        }

        public Message PesquisarPosicaoOnibusLinha(string OlhoVivoId,string Empresa,string Sentido)
        {
            try
            {
                var vWeb = new WebClient { Encoding = Encoding.UTF8 };
                var vResult = "";

                if (Empresa == "1")
                {
                    var coordenates = new List<BusPosition>();
                    try
                    {
                        var url = new Uri(string.Format(ConfigurationManager.AppSettings["url_posicaoOnibus"], OlhoVivoId));
                        var urlSessao = new Uri(string.Format(ConfigurationManager.AppSettings["url_posicaoOnibusSessao"], OlhoVivoId));

                        vResult = GetRequest(urlSessao.AbsoluteUri, url.AbsoluteUri);

                        coordenates = setJsonToObject<BusPositionSPTransReturn>(vResult).BusPosition; 
                        
                        //if(coordenates == null)
                        //    coordenates = setJsonToObject<BusPositionReturn>(vResult).BusPositionSPTransReturn.BusPosition;
                    }
                    catch (WebException webException)
                    {
                        //vResult = vWeb.DownloadString(string.Format(ConfigurationManager.AppSettings["url_posicaoOnibus2"], OlhoVivoId));
                    }

                    foreach (var busPosition in coordenates)
                    {
                        var type = new BusApplication().GetBus(int.Parse(busPosition.Bus));
                        busPosition.Type = type.BusType.Id;
                    }

                    return WebOperationContext.Current.CreateJsonResponse(coordenates);
                }

                var emtuCoordenates = PegaPosicaoRotaEmtu(OlhoVivoId);
                //&& rota.pontos[veiculo.seqPonto].raio < DistanciaEntreLatLog(veiculo.latitude, veiculo.longitude, rota.pontos[veiculo.seqPonto].latitude, rota.pontos[veiculo.seqPonto].longitude)
                var emtuBusPositions = (from linha in emtuCoordenates.linhas
                                        from veiculo in linha.veiculos
                                        from rota in linha.rotas.Where(x => x.sentido == (Sentido == "1" ? "ida" : "volta"))
                                        where veiculo.sentidoLinha == (Sentido == "1" ? "ida" : "volta")
                                        && veiculo.seqPonto > 0
                                        select new 
                                            {
                                                YPosition = veiculo.latitude,
                                                XPosition = veiculo.longitude,
                                                Bus = veiculo.prefixo,
                                                AcceptSpecial = false,
                                                Type = 1,
                                                Data = new DateTime(1970, 1, 1).AddSeconds(double.Parse(veiculo.dataUltimaTransmissao.ToString().Substring(0,10))).AddHours(-3)
                                            }).ToList();

                

                var emtuResult = (from emtu in emtuBusPositions
                                  where (DateTimeExtensions.UTCBrazilDateTime - emtu.Data).Minutes <= 10
                          select new BusPosition
                              {
                                  YPosition = emtu.YPosition,
                                  XPosition = emtu.XPosition,
                                  Bus = emtu.Bus,
                                  AcceptSpecial = false,
                                  Type = emtu.Type,
                              });
                return WebOperationContext.Current.CreateJsonResponse(emtuResult);
            }
            catch (Exception ex)
            {
                new Log().ExceptionLog(ex);
                return null;
            }
        }

        public Message PesquisarLinhaDetalhe(string OlhoVivoId)
        {
            try
            {
                var vWeb = new WebClient { Encoding = Encoding.UTF8 };
                var vResult = "";
                try
                {
                    vResult = vWeb.DownloadString(string.Format(ConfigurationManager.AppSettings["url_buscaLinha"], OlhoVivoId));
                }
                catch (WebException webException)
                {
                    
                }

                var coordenates = setJsonToObject<BusLineDetail>(vResult);

                return WebOperationContext.Current.CreateJsonResponse(coordenates);
            }
            catch (Exception ex)
            {
                new Log().ExceptionLog(ex);
                return null;
            }
        }

        public Message PesquisarItinerario(string OlhoVivoId)
        {
            try
            {
                return RetornarJson(new ItineraryApplication().FindManyItinerary(OlhoVivoId));
            }
            catch (Exception ex)
            {
                new Log().ExceptionLog(ex);
                return null;
            }
        }

        public Message PesquisarOnibusTipo(string Prefixo)
        {
            try
            {
                var vBus = new BusApplication().GetBus(int.Parse(Prefixo));
                return RetornarJson<BusType>(vBus.BusType);
            }
            catch (Exception ex)
            {
                new Log().ExceptionLog(ex);
                return null;
            }
        }

        public Message CriarImagem(string latitude, string longitude, string tipomapa)
        {
            string imageName = string.Empty;
            try
            {
                longitude = longitude.Replace(",", ".");
                latitude = latitude.Replace(",", ".");

                int zoom = 16;
                string size = "400x400";
                string url = "";

                //if (tipomapa == "0")
                url = "http://maps.googleapis.com/maps/api/staticmap?center={0},{1}&zoom={2}&size={3}&sensor=true&scale=2&markers=icon:http://www.nanoitbrasil.com.br/ViewBusHom/Icone/icon_bus_blue.png%7C{0},{1}";
                //else
                //url = "http://pafciu17.dev.openstreetmap.org/?module=map&lat={0}&lon={1}&width=150&height=150&zoom=14&points={1},{0}&pointImageUrl=www.nanoitbrasil.com.br/ViewBusHom/Icone/icon_bus_blue_osm.png";

                string imageURL = string.Format(url, latitude, longitude, zoom, size);

                var web = new WebClient();

                var data = GetImageStream(imageURL);

                Image img = System.Drawing.Image.FromStream(data);
                imageName = string.Format("Checkin_{0}_tipomapa_{1}.Jpeg", Guid.NewGuid().ToString(), tipomapa);
                img.Save(Path.Combine(HostingEnvironment.MapPath(@"/CoO/Service/Images"), imageName), ImageFormat.Jpeg);
            }
            catch (Exception ex)
            {
                new Log().ExceptionLog(ex);
            }

            return WebOperationContext.Current.CreateJsonResponse(@"http://cadeoonibus.com.br/CoO/Service/Images/" + imageName);
        }


        private Stream GetImageStream(string url)
        {
            //Create a stream for the file
            Stream stream = null;

            //This controls how many bytes to read at a time and send to the client
            int bytesToRead = 10000;

            // Buffer to read bytes in chunk size specified above
            byte[] buffer = new Byte[bytesToRead];

            // The number of bytes read
            try
            {
                //Create a WebRequest to get the file
                HttpWebRequest fileReq = (HttpWebRequest)HttpWebRequest.Create(url);

                //Create a response for this request
                HttpWebResponse fileResp = (HttpWebResponse)fileReq.GetResponse();

                if (fileReq.ContentLength > 0)
                    fileResp.ContentLength = fileReq.ContentLength;

                //Get the Stream returned from the response
                stream = fileResp.GetResponseStream();
                return stream;
            }
            catch (Exception ex)
            {
                new Log().ExceptionLog(ex);
                return null;
            }
        }

        private Message RetornarJson<T>(T pObject)
        {
            return WebOperationContext.Current.CreateJsonResponse(pObject);
        }

        private T setJsonToObject<T>(string pJSon)
        {
            using (var vMS = new MemoryStream(Encoding.Unicode.GetBytes(pJSon)))
            {
                var vDataContractJsonSerializer = new DataContractJsonSerializer(typeof(T));
                return (T)vDataContractJsonSerializer.ReadObject(vMS);
            }
        }

        public string GetRequest(string link1, string link2)
        {
            var requestData = Encoding.UTF8.GetBytes("");
            var cookies = new CookieContainer();

            var request = (HttpWebRequest)WebRequest.Create(link1);

            request.CookieContainer = cookies;
            request.Method = "POST";
            request.AllowAutoRedirect = false;
            request.UserAgent = "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13";
            request.ContentType = "application/x-www-form-urlencoded";
            request.ContentLength = requestData.Length;

            var dataStream = request.GetRequestStream();
            dataStream.Write(requestData, 0, requestData.Length);

            var response = request.GetResponse();
            dataStream = response.GetResponseStream();

            if (dataStream != null)
            {
                var reader = new StreamReader(dataStream);
                var responseFromServer = reader.ReadToEnd();
                reader.Close();
                dataStream.Close();
                response.Close();
            }

            var request2 = (HttpWebRequest)WebRequest.Create(link2);
            request2.CookieContainer = cookies;
            request2.ContentType = "application/json";
            var response2 = request2.GetResponse();
            var dataStream2 = response2.GetResponseStream();

            if (dataStream2 != null)
            {
                var reader2 = new StreamReader(dataStream2);
                var responseFromServer = reader2.ReadToEnd();
                reader2.Close();
                dataStream2.Close();
                response2.Close();

                return HttpUtility.HtmlDecode(responseFromServer);
            }

            return string.Empty;
        }

        private LinhaRootObject PegaPosicaoRotaEmtu(string id)
        {
            var OlhoVivoId = id.Split('-')[0];
            string emtuLink = "http://bustime.noxxonsat.com.br/bustime?linha=" + OlhoVivoId;
            var vWeb = new WebClient { Encoding = Encoding.UTF8 };
            var vResult = vWeb.DownloadString(emtuLink);
            var emtuCoordenates = setJsonToObject<LinhaRootObject>(vResult);

            return emtuCoordenates;
        }

        
        public Double DistanciaEntreLatLog(double latIni, double lonIni,double latFim,double lonFim)
        {
            const double auxPi = 0.0174532925199433;
            var result = Math.Round((40030 * ((180 / Math.PI) *
            Math.Acos(Math.Cos((90 - latFim) * auxPi) *
            Math.Cos((90 - latIni) * auxPi) +
            Math.Sin((90 - latFim) * auxPi) *
            Math.Sin((90 - latIni) * auxPi) *
            Math.Cos((lonFim - lonIni) * auxPi)))) / 360, 2);

            return result;
        }



        public Message PesquisarPontosPorLinha(string Prefixo)
        {
            try
            {
                var stops = new StopApplication().GetStopByLine(Prefixo);

                return RetornarJson(stops);
            }
            catch (Exception ex)
            {
                new Log().ExceptionLog(ex);
                return null;
            }
        }


        public Message GetBusStopDistance(string OlhoVivoId,string Empresa,string Sentido,string StopId)
        {
            try
            {
                DistanceNotificationService service = new DistanceNotificationService();
                    

                var vWeb = new WebClient { Encoding = Encoding.UTF8 };
                var vResult = "";

                if (Empresa == "1")
                {
                    List<BusStopDistanceReturn> busStopDistanceReturns = new List<BusStopDistanceReturn>();

                    foreach (var item in OlhoVivoId.Split(','))
                    {
                        if (string.IsNullOrEmpty(item))
                            continue;

                        var coordenates = new List<BusPosition>();
                        try
                        {
                            var url = new Uri(string.Format(ConfigurationManager.AppSettings["url_posicaoOnibus"], OlhoVivoId));
                            var urlSessao = new Uri(string.Format(ConfigurationManager.AppSettings["url_posicaoOnibusSessao"], OlhoVivoId));

                            vResult = GetRequest(urlSessao.AbsoluteUri, url.AbsoluteUri);

                            coordenates = setJsonToObject<BusPositionSPTransReturn>(vResult).BusPosition;
                        }
                        catch (WebException webException)
                        {
                        }

                        foreach (var busPosition in coordenates)
                        {
                            var type = new BusApplication().GetBus(int.Parse(busPosition.Bus));
                            busPosition.Type = type.BusType.Id;
                        }

                        busStopDistanceReturns.Add(service.GetDistance(coordenates, int.Parse(item), int.Parse(StopId)));
                    }

                    BusStopDistanceReturn result = null;

                    foreach (var distances in busStopDistanceReturns)
                    {
                        if (result == null)
                        {
                            result = distances;
                            continue;
                        }

                        if (distances.Distance < result.Distance)
                        {
                            result = distances;
                        }
                    }

                    if (result.Distance == double.MaxValue)
                        result.Distance = -1;

                    return WebOperationContext.Current.CreateJsonResponse(result);
                    
                }

                var emtuCoordenates = PegaPosicaoRotaEmtu(OlhoVivoId);
                //&& rota.pontos[veiculo.seqPonto].raio < DistanciaEntreLatLog(veiculo.latitude, veiculo.longitude, rota.pontos[veiculo.seqPonto].latitude, rota.pontos[veiculo.seqPonto].longitude)
                var emtuBusPositions = (from linha in emtuCoordenates.linhas
                                        from veiculo in linha.veiculos
                                        from rota in linha.rotas.Where(x => x.sentido == (Sentido == "1" ? "ida" : "volta"))
                                        where veiculo.sentidoLinha == (Sentido == "1" ? "ida" : "volta")
                                        && veiculo.seqPonto > 0
                                        select new
                                        {
                                            YPosition = veiculo.latitude,
                                            XPosition = veiculo.longitude,
                                            Bus = veiculo.prefixo,
                                            AcceptSpecial = false,
                                            Type = 1,
                                            Data = new DateTime(1970, 1, 1).AddSeconds(double.Parse(veiculo.dataUltimaTransmissao.ToString().Substring(0, 10))).AddHours(-3)
                                        }).ToList();



                var emtuResult = (from emtu in emtuBusPositions
                                  where (DateTimeExtensions.UTCBrazilDateTime - emtu.Data).Minutes <= 10
                                  select new BusPosition
                                  {
                                      YPosition = emtu.YPosition,
                                      XPosition = emtu.XPosition,
                                      Bus = emtu.Bus,
                                      AcceptSpecial = false,
                                      Type = emtu.Type,
                                  });

                return WebOperationContext.Current.CreateJsonResponse(
                    service.GetDistance(emtuResult.ToList(), int.Parse(OlhoVivoId), int.Parse(StopId)));
            }
            catch (Exception ex)
            {
                new Log().ExceptionLog(ex);
                return null;
            }
        }

        public Message GetTweeter(string Perfil)
        {
            try
            {
                var vWeb = new WebClient { Encoding = Encoding.UTF8 };

                var tweets = vWeb.DownloadString(@"http://tweet-mirror.herokuapp.com/" + Perfil);

                JavaScriptSerializer serializer = new JavaScriptSerializer();
                var obj = serializer.Deserialize<RootObject>(tweets);

                    
                return WebOperationContext.Current.CreateJsonResponse(obj);

            }
            catch (Exception ex)
            {
                new Log().ExceptionLog(ex);
                return null;
            }
        }
    }
}
