using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract]
    public class Error
    {
        [DataMember]
        public virtual int Id { get; set; }
        [DataMember]
        public virtual string ErrorMessage { get; set; }
        [DataMember]
        public virtual string Device { get; set; }
        [DataMember]
        public virtual string OperationSystem { get; set; }
        [DataMember]
        public virtual DateTime DateTimeOfError { get; set; }
    }
}
