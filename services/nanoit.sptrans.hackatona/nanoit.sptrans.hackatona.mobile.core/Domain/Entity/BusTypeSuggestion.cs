using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract]
    public class BusTypeSuggestion
    {
        public BusTypeSuggestion()
        {
            BusType = new BusType();
            Device = new Device();
            Bus = new Bus();
        }

        [DataMember]
        public virtual int Id { get; set; }
        [DataMember]
        public virtual BusType BusType { get; set; }
        [DataMember]
        public virtual Device Device { get; set; }
        [DataMember]
        public virtual Bus Bus { get; set; }
        [DataMember]
        public virtual DateTime DateTimeOfCreation { get; set; }
    }
}
