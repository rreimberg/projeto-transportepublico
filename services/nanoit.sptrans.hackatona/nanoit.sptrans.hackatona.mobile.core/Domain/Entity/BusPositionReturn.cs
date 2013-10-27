using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract(Name="PosicaoLinha")]
    public class BusPositionReturn
    {
        [DataMember(Name="PosicaoLinhaResult")]
        public BusPositionSPTransReturn BusPositionSPTransReturn { get; set; }
    }
}
