using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;

namespace nanoit.sptrans.hackatona.admpanel.core.Domain.Entity
{
    [DataContract]
    public class ServiceResponse
    {
        
        [DataMember]
        public int Code { get; set; }


        [DataMember]
        public string Description { get; set; }


        [DataMember]
        public int ReturnValue { get; set; }

    }
}
