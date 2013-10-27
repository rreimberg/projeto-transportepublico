using System;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace nanoit.sptrans.hackatona.core.domain.entity
{
    public class Device
    {
        public Device() 
        { }

        public virtual string Id { get; set; }

        public virtual string IMEI { get; set; }
        public virtual Plataform Plataform { get; set; }
        public virtual string OperationSystem { get; set; }
        public virtual string DeviceModel { get; set; }
        public virtual DateTime DateTimeOfCreation { get; set; }
    }
}
