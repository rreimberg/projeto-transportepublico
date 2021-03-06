﻿using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract]
    public class Passage
    {
        public Passage()
        { }

        [DataMember]
        public virtual int Id { get; set; }
        [DataMember]
        public virtual string Name { get; set; }
        [DataMember]
        public virtual DateTime DateTimeOfCreation { get; set; }
    }
}
