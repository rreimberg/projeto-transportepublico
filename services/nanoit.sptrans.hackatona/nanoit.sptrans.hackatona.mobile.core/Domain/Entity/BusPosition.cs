using System;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract(Name = "V")]
    public class BusPosition
    {
        [DataMember(Name = "a")]
        public bool AcceptSpecial { get; set; }

        [DataMember(Name = "p")]
        public String Bus { get; set; }

        [DataMember(Name = "px")]
        public double XPosition { get; set; }

        [DataMember(Name = "py")]
        public double YPosition { get; set; }

        [DataMember(Name = "type")]
        public int Type { get; set; }
    }
}