using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract]
    public class Application
    {
        public Application()
        {
            Plataform = new Plataform();
        }

        [DataMember]
        public virtual int Id { get; set; }
        [DataMember]
        public virtual Plataform Plataform { get; set; }
        [DataMember]
        public virtual string Name { get; set; }
        [DataMember]
        public virtual string Description { get; set; }
        [DataMember]
        public virtual string ApplicationVersion { get; set; }
        [DataMember]
        public virtual string StoreVersion { get; set; }
        [DataMember]
        public virtual DateTime DateTimeOfCreation { get; set; }
    }
}
