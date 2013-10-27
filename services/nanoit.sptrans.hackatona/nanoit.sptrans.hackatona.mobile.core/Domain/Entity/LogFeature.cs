using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract]
    public class LogFeature
    {
        public LogFeature()
        {
            Feature = new Feature();
            Device = new Device();
        }

        [DataMember]
        public virtual int Id { get; set; }
        [DataMember]
        public virtual Feature Feature { get; set; }
        [DataMember]
        public virtual Device Device { get; set; }
        [DataMember]
        public virtual DateTime DateTimeOfAccess { get; set; }
    }
}
