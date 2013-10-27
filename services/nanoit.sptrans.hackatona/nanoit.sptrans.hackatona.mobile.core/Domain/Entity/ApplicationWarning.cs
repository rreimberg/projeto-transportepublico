using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract]
    public class ApplicationWarning
    {
        public ApplicationWarning()
        {
            Application = new Application();
        }

        public virtual int Id { get; set; }
        public virtual Application Application { get; set; }
        
        [DataMember]
        public virtual string Message { get; set; }
        
        public virtual DateTime DateTimeOfStart { get; set; }
        public virtual DateTime DateTimeOfEnd { get; set; }
        public virtual DateTime DateTimeOfCreation { get; set; }
    }
}
