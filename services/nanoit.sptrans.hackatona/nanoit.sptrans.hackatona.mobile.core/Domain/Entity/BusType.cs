using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract]
    public class BusType
    {
        public BusType()
        { }

        [DataMember]
        public virtual int Id { get; set; }
        [DataMember]
        public virtual string Name { get; set; }
        [DataMember]
        public virtual Boolean IsActive { get; set; }
        [DataMember]
        public virtual DateTime DateTimeOfCreation { get; set; }

        public override string ToString()
        {
            return Name;
        }
    }
}
