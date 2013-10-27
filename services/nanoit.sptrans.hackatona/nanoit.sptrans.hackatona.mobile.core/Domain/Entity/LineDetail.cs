using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    [DataContract]
    public class LineDetail
    {
        public LineDetail()
        {
            Line = new Line();
        }

        [DataMember]
        public virtual int Id { get; set; }
        [DataMember]
        public virtual Line Line { get; set; }
        [DataMember]
        public virtual string Extension { get; set; }
        [DataMember]
        public virtual string MediumInterval { get; set; }
        [DataMember]
        public virtual string BusinessDayOperation { get; set; }
        [DataMember]
        public virtual string SaturdayOperation { get; set; }
        [DataMember]
        public virtual string SundayOperation { get; set; }
        [DataMember]
        public virtual DateTime DateTimeOfCreation { get; set; } 
        [DataMember]
        public virtual DateTime DateTimeOfLastUpdate { get; set; }

    }
}
