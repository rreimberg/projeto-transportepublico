using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;

namespace nanoit.sptrans.hackatona.core.application
{
    public class TrafficApplication
    {
        public IList<Trecho> GetTraffic(string id)
        {
            var webClient = new WebClient { Encoding = Encoding.UTF8 };
            var ds = new DataSet();
            var xmlBytes = webClient.DownloadData(ConfigurationManager.AppSettings["url_transito"]);
            var stream = new MemoryStream(xmlBytes);
            ds.ReadXml(stream);
            var corredor = ds.Tables["corredor"].AsEnumerable();
            var trechos = ds.Tables["trecho"].AsEnumerable();
            var corredores = (from c in corredor
                              select new Corredor
                              {
                                  Corredor_Id = Convert.ToInt32(c["Corredor_Id"]),
                                  MaiorExtensao = c["MaiorExtensao"].ToString(),
                                  TempoEstimadoBC = ToMinute(c["TempoEstimadoBC"].ToString()),
                                  TempoEstimadoCB = ToMinute(c["TempoEstimadoCB"].ToString()),
                                  VelocidadeEstimadaBC = string.Format("{0} Km/h", c["VelocidadeEstimadaBC"].ToString()),
                                  VelocidadeEstimadaCB = string.Format("{0} Km/h", c["VelocidadeEstimadaCB"].ToString()),
                                  nome = c["nome"].ToString(),
                                  id = c["id"].ToString(),
                                  Trechos = (from t in trechos
                                             where t["Corredor_Id"].ToString() == c["Corredor_Id"].ToString()
                                             orderby t["id"]
                                             select new Trecho
                                             {
                                                 Referencia = t["referencia"] != null ? t["referencia"].ToString() : "Sem referência",
                                                 Corredor_Id = Convert.ToInt32(t["Corredor_Id"].ToString()),
                                                 TempoEstimadoBC = ToMinute(t["TempoEstimadoBC"].ToString()),
                                                 TempoEstimadoCB = ToMinute(t["TempoEstimadoCB"].ToString()),
                                                 id = t["id"].ToString(),
                                                 VelocidadeEstimadaBC = string.Format("{0} Km/h", t["VelocidadeEstimadaBC"].ToString()),
                                                 VelocidadeEstimadaCB = string.Format("{0} Km/h", t["VelocidadeEstimadaCB"].ToString()),
                                                 extensaoBC = t["extensaoBC"].ToString(),
                                                 extensaoCB = t["extensaoCB"].ToString(),
                                                 idRuasBC = t["idRuasBC"].ToString(),
                                                 idRuasCB = t["idRuasCB"].ToString(),
                                                 CorBC = GetColor(int.Parse(string.IsNullOrEmpty(t["VelocidadeEstimadaBC"].ToString()) ? "0" : t["VelocidadeEstimadaBC"].ToString())),
                                                 CorCB = GetColor(int.Parse(string.IsNullOrEmpty(t["VelocidadeEstimadaCB"].ToString()) ? "0" : t["VelocidadeEstimadaCB"].ToString()))
                                             }).ToList()


                              }
                         ).ToList();

            var trechosReturn = (from c in corredores.OrderBy(x => x.nome).ToList()
                          from t in c.Trechos
                          where c.id.ToLower() == id.ToLower()
                          select t).ToList();

            return trechosReturn;
        }

        public IList<Corredor> GetCorridor()
        {
            var webClient = new WebClient { Encoding = Encoding.UTF8 };
            var ds = new DataSet();
            var xmlBytes = webClient.DownloadData(ConfigurationManager.AppSettings["url_transito"]);
            var stream = new MemoryStream(xmlBytes);
            ds.ReadXml(stream);
            var corredor = ds.Tables["corredor"].AsEnumerable();
            var expects = ConfigurationManager.AppSettings["transito_ignora_corredor"].Split(',');
            var corredores = (from c in corredor
                              where !(from e in expects
                                      select e).Contains(c["id"])

                              select new Corredor
                                  {
                                      Corredor_Id = Convert.ToInt32(c["Corredor_Id"]),
                                      MaiorExtensao = c["MaiorExtensao"].ToString(),
                                      TempoEstimadoBC = ToMinute(c["TempoEstimadoBC"].ToString()),
                                      TempoEstimadoCB = ToMinute(c["TempoEstimadoCB"].ToString()),
                                      VelocidadeEstimadaBC = string.Format("{0} Km/h", c["VelocidadeEstimadaBC"]),
                                      VelocidadeEstimadaCB = string.Format("{0} Km/h", c["VelocidadeEstimadaCB"]),
                                      nome = c["nome"].ToString(),
                                      id = c["id"].ToString()
                                  }
                                
                             ).OrderBy(x => x.nome).ToList();


            return corredores;
        }

        private string GetColor(int velocidade)
        {
            if (velocidade >= 18)
                return "Green";
            if (velocidade >= 13 && velocidade <= 17)
                return "Yellow";

            return "Red";
        }

        private string ToMinute(string format)
        {
            if (!format.Contains("h"))
                return "Sem Previsão";

            try
            {
                int hour = Convert.ToInt32(format.Split('h')[0]);
                int minute = Convert.ToInt32(format.Split('h')[1]);

                return string.Format("{0} minutos", minute + (hour * 60));
            }
            catch (Exception ex)
            {
                return "Sem previsão";
            }
        }
    }

    public class Trecho
    {
        public string id { get; set; }
        public string TempoEstimadoBC { get; set; }
        public string VelocidadeEstimadaBC { get; set; }
        public string idRuasBC { get; set; }
        public string extensaoBC { get; set; }
        public string TempoEstimadoCB { get; set; }
        public string VelocidadeEstimadaCB { get; set; }
        public string idRuasCB { get; set; }
        public string extensaoCB { get; set; }
        public string Referencia { get; set; }
        public int Corredor_Id { get; set; }
        public string CorBC { get; set; }
        public string CorCB { get; set; }
    }

    public class Corredor
    {
        public int Corredor_Id { get; set; }
        public string id { get; set; }
        public string nome { get; set; }
        public string VelocidadeEstimadaBC { get; set; }
        public string TempoEstimadoBC { get; set; }
        public string VelocidadeEstimadaCB { get; set; }
        public string TempoEstimadoCB { get; set; }
        public string MaiorExtensao { get; set; }
        public IList<Trecho> Trechos { get; set; }
    }
}
