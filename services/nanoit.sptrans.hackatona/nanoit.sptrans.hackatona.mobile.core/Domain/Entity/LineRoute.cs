using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract]
    public class LineRoute
    {
        public LineRoute()
        {
            Line = new Line();
        }

        [DataMember]
        public virtual int Id { get; set; }
        [DataMember]
        public virtual Line Line { get; set; }
        [DataMember]
        public virtual string Latitude { get; set; }
        [DataMember]
        public virtual string Longitude { get; set; }
        [DataMember]
        public virtual int Sequence { get; set; }
    }
}
