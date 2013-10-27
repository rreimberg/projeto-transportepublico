using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract]
    public class Feature
    {
        public Feature()
        {
            Application = new Application();
        }

        [DataMember]
        public virtual int Id { get; set; }
        [DataMember]
        public virtual Application Application { get; set; }
        [DataMember]
        public virtual string Name { get; set; }
        [DataMember]
        public virtual DateTime DateTimeOfAccess { get; set; }
    }
}
