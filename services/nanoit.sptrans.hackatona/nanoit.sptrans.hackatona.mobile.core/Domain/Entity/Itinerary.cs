using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract]
    public class Itinerary
    {
        public Itinerary()
        {
            Line = new Line();
        }

        public virtual int Id { get; set; }
        public virtual Line Line { get; set; }
        public virtual int Sequence { get; set; }
        public virtual string StartNumber { get; set; }
        public virtual string EndNumber { get; set; }

        [DataMember(Name = "Street")]
        public virtual string Adress { get; set; }

        [DataMember(Name = "Number")]
        public virtual string NumberFull { get; set; }
    }
}
