using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract]
    public class RouterReturn
    {
        [DataMember]
        public int Type { get; set; }

        [DataMember]
        public List<Coordenates> Coordenates { get; set; }
    }
}
