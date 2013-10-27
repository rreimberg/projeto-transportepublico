using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract]
    public class Suggestion
    {
        public Suggestion()
        {
            Application = new Application();
            Device = new Device();
        }

        [DataMember]
        public virtual int Id { get; set; }
        [DataMember]
        public virtual Device Device { get; set; }
        [DataMember]
        public virtual Application Application { get; set; }
        [DataMember]
        public virtual string Email { get; set; }
        [DataMember]
        public virtual string Message { get; set; }
        [DataMember]
        public virtual DateTime DateTimeOfSendEmail { get; set; }
        [DataMember]
        public virtual DateTime DateTimeOfCreation      { get; set; }
    }
}
