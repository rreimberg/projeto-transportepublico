using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract]
    public class Bus
    {
        public Bus()
        {
            BusType = new BusType();
        }

        [DataMember]
        public virtual int Id { get; set; }
        [DataMember]
        public virtual BusType BusType { get; set; }
        [DataMember]
        public virtual string PrefixFull { get; set; }
        [DataMember]
        public virtual Boolean AcceptSpecialPerson { get; set; }
        [DataMember]
        public virtual DateTime DateTimeOfLastUpdate { get; set; }
        [DataMember]
        public virtual DateTime DateTimeOfCreation { get; set; }

    }
}
