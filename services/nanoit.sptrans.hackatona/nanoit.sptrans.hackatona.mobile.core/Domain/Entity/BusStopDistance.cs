using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace nanoit.sptrans.hackatona.mobile.core.Domain.Entity
{
    public class BusStopDistance
    {
        public double Status { get; set; }
        public double Distance { get; set; }
        public string NameSource { get; set; }
        public string NameTarget { get; set; }
        public string Prefix { get; set; }
        public string PrefixType { get; set; }
        public int BusType { get; set; }
    }

    public class BusStopDistanceReturn
    {
        public int OlhoVivoId { get; set; }
        public double Distance { get; set; }
        public int BusPrefix { get; set; }
        public string NameSource { get; set; }
        public string NameTarget { get; set; }
        public string Prefix { get; set; }
        public string PrefixType { get; set; }
        public int BusType { get; set; }
    }
}
