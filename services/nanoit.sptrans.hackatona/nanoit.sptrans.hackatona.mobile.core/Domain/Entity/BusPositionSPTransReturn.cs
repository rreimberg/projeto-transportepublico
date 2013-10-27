using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract(Name = "PosicaoLinhaResult")]
    public class BusPositionSPTransReturn
    {
        [DataMember(Name = "hr")]
        public string DateTimeLastUpdate { get; set; }

        [DataMember(Name = "vs")]
        public List<BusPosition> BusPosition { get; set; }
    }
}
